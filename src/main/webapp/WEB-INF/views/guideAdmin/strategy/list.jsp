<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <style>
  .add:before{
	content: '+';
    background-color: #b0c1d2;
    box-shadow: none!important;
    font-weight: 300;
    vertical-align: middle;
    line-height: 16px;
    border: 0;
    top: 8px;
    left: 4px;
    height: 16px;
    width: 16px;
    display: block;
    position: absolute;
    color: white;
    border: 2px solid white;
    border-radius: 16px;
    text-align: center;
    box-shadow: 0 0 3px #444;
    box-sizing: content-box;
}
  
  </style>
<title>攻略</title>
</head>
<body>

	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">导游攻略列表</span>
					</div>
					<div class="actions">
		<a href="${ctx}/guideAdmin/strategy/create" class="btn btn-sm green btn-outline" >新增</a>
	<!-- 	<a href="javascript:;" target="deletes" class="btn btn-sm red btn-outline" >删除</a>			 -->		
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/strategy/list" method="post">
	<div class="row">
		<div class="col-md-4">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-4"></div>
		<div class="col-md-4" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="标题" name="title" value="${guideStrategy.title}">	
		<button type="submit" class="btn btn-sm green btn-outline  filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>		
		</div>		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								   <!--  <th scope="col" colspan="2"><input type="checkbox" class=" group-checkable check-all"></th>  -->
								    <th></th>
									<th scope="col">编号</th>
									<th scope="col">作者</th>
									<th scope="col">标题</th>
									<th scope="col">图片</th>
									<th scope="col">排序号</th>
									<th scope="col">创建时间</th>
									<th scope="col">更新时间</th>
									<th scope="col">审核状态</th>
									<th scope="col">状态</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr class="odd">
									  <%--  <td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td>  --%>
									    <td class="add" onclick="toggleDetail(this)"></td>
										<td>${item.id}</td>
										<td>${item.userName}</td>
										<td>${item.title}</td>
										<td><img src="${item.imgUrl}" title="${item.title}" height="50px" width="50px" /></td>
										<td class="ext-sort" data-href="${ctx}/admin/guideStrategy/updateSort/${item.id}">${item.sort}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${EAuditStatus[item.auditStatus]}</td>
										<td>${EStatus[item.status]}</td>
										<td>	
											<a href="${ctx}/guideAdmin/strategy/edit/${item.id}" class="btn yellow btn-outline" >编辑</a>
											<a href="${ctx}/guideAdmin/strategy/detail/${item.id}" class="btn red btn-outline" >详情</a>
											<c:choose>
											<c:when test="${item.status==2}">
											<a href="javascript:onLine(${item.id},${item.auditStatus},3)"  class="btn red btn-outline" >下线</a>
											</c:when>
											<c:otherwise>
											<a href="javascript:onLine(${item.id},${item.auditStatus},2)"  class="btn green  btn-outline" >上线</a>
											</c:otherwise>
											</c:choose>				
										<%-- 	<a href="${ctx}/guideAdmin/strategy/delete/${item.id}" target="delete" class="btn btn-sm red btn-outline" >删除</a>	 --%>
										</td>
									</tr>
									<tr class="child">
										<td class="child" colspan="10">
										<ul>
												<li><span class="dtr-title">描述:</span>
													<span class="dtr-data">${item.description}</span>
												</li>
												<li><span class="dtr-title">推荐理由:</span>
													<span class="dtr-data">${item.recommendInfo}</span>
												</li>
												<li><span class="dtr-title">审核说明:</span>
													<span class="dtr-data">${item.auditTime}</span>
												</li>
												<li><span class="dtr-title">审核时间:</span>
													<span class="dtr-data">${item.auditRemark}</span>
												</li>
												<%-- <li><span class="dtr-title">攻略内容:</span>
													<span class="dtr-data">${item.content}</span>
												</li> --%>
										</ul>
										</td>
									</tr>							
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->

		</div>
	</div>
	

	
	
<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>


<script type="text/javascript">
//上线下线
function onLine(id, auditStatu, status) {
	if (auditStatu != 2) {
		comm.warningMsg("审核通过后才能操作");
		return;
	}
	comm.confirm("提示", "确定执行该操作吗?", function() {
		$.post("${ctx}/guideAdmin/strategy/on", {
			"id" : id,
			"status" : status
		}, function(data) {
			comm.successMsg(data);
			setTimeout(function() {
				location.reload();
			}, 1000)

		})

	})
}
	//展开详情事件
	function toggleDetail(dom){
		  $(dom).toggleClass("active").parent("tr").next("tr").toggle();
	  }
</script>
</body>
</html>