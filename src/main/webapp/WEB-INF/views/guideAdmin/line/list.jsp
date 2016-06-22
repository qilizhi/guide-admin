<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路</title>

</head>

<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">导游线路列表</span>
					</div>
					<div class="actions">
		<a href="${ctx}/guideAdmin/line/create"  class="btn btn-sm green btn-outline" >新增</a>
		
		<!-- <a href="javascript:;" target="deletes" class="btn btn-sm red btn-outline" >删除</a>	 -->				
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/line/list" method="post">
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
		<input type="text" class="form-filter input-sm" placeholder="线路编号" name="lineNo" value="${guideLine.lineNo}">		
		<input type="text" class="form-filter input-sm" placeholder="线路标题" name="title" value="${guideLine.title}">	
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
			
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!-- <th scope="col"><input type="checkbox" class=" group-checkable check-all"></th> -->
									<th scope="col">线路编号</th>
									<th scope="col">线路标题</th>
									<th scope="col">背景图片</th>
									<!-- <th scope="col">线路简介</th> -->
									<th scope="col">创建时间</th>
									<th scope="col">价格</th>
									<th scope="col">线路状态</th>
									<th scope="col">审核说明</th>
									<th scope="col">审核时间</th>
									<th scope="col">审核状态</th>
									<th scope="col">操作</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<%-- <td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td> --%>
										<td>${item.lineNo}</td>
										<td>${item.title}</td>
										<td><img src="${item.imgUrl}" title="${item.title}" height="50px" width="50px" /></td>
										<%-- <td>${item.description}</td> --%>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.price}</td>
										<td>${EStatus[item.status]}</td>
										<td>${item.auditRemark}</td>
										<td><fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${EAuditStatus[item.auditStatus]}</td>
										<td>															
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/line/edit/${item.id}">编辑</a>
											<c:choose>
											<c:when test="${item.status==2}">
											<a href="javascript:onLine(${item.id},${item.auditStatus},3)"  class="btn red btn-outline" >下线</a>
											</c:when>
											<c:otherwise>
											<a href="javascript:onLine(${item.id},${item.auditStatus},2)"  class="btn green  btn-outline" >上线</a>
											</c:otherwise>
											</c:choose>
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/line/detail/${item.id}">预览</a>
											<a class="btn red btn-outline" href="${ctx}/guideAdmin/line/editPrice/${item.lineNo}">价格编辑</a>
											<a class="btn blue btn-outline" href="${ctx}/guideAdmin/trip/edit/${item.lineNo}">行程</a>
											<input type="hidden" name="lineNo" value="${item.lineNo}"/>
											
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
	
	
	<script>

	
		//上线下线
		function onLine(id, auditStatu, status) {
			if (auditStatu != 2) {
				comm.warningMsg("审核通过后才能操作");
				return;
			}
			comm.confirm("提示", "确定执行该操作吗?", function() {
				$.post("${ctx}/guideAdmin/line/on", {
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
	</script>





</body>

</html>