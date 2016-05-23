<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>平台公告</title>
</head>
<body>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">公告管理</span>
					</div>
					<div class="actions">
						<a href="${ctx}/admin/sysNotice/edit"
							class="btn btn-sm green btn-outline">新增</a> 
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/sysNotice" method="post">
						
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-12" style="margin-bottom:20px;">
								<select name="pageSize"
									class="form-control input-sm input-xsmall input-inline">	
									<option value="10"
										<c:if test="${pageSize == 10}">selected</c:if>>10</option>
									<option value="20"
										<c:if test="${pageSize == 20}">selected</c:if>>20</option>
									<option value="50"
										<c:if test="${pageSize == 50}">selected</c:if>>50</option>
									<option value="100"
										<c:if test="${pageSize == 100}">selected</c:if>>100</option>
								</select>
							
						
							<div class="pull-right">
								<input type="text" class="form-filter input-sm"
									placeholder="标题" name="title" value="${platformMsg.title }">

								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" onclick="formReset();"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-mail-reply-all"></i> 重置
								</button>
							</div>
							</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>标题</th>
									<th>内容</th>
									<th>链接地址</th>
									<th>创建时间</th>
									<th>状态</th>
									<th>操作</th>
								  
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" >
									<tr>
									   
										<td>${item.title }</td>
										<td>${item.content }</td>
										<td>${item.url }</td>
										<td>
										<fmt:formatDate value="${item.createTime}" pattern="yyy-MM-dd hh:mm:ss"/>
										</td>
										<td>
										  <c:choose>
										   <c:when test="${item.flag==0 }">
										     隐藏
										   </c:when>
										   <c:otherwise>
										     显示
										   </c:otherwise>
										  </c:choose>
										</td>
										<td> 
										<a  href="${ctx}/admin/sysNotice/edit?id=${item.id}" class="btn green btn-outline btn-sm">编辑</a>
										<c:choose>
										   <c:when test="${item.flag==0 }">
										<a class="btn green btn-outline btn-sm" onclick="editFlag(${item.id},1)">显示</a>
										   </c:when>
										   <c:otherwise>
										  <a class="btn red btn-outline btn-sm" onclick="editFlag(${item.id},0)">撤回</a>
										   </c:otherwise>
										  </c:choose>
										
										</td>									
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			
		</div>
	</div>
	<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/static/assets/pages/scripts/form-validation.min.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script src="${ctx}/static/js/handle.js"></script>
	
	<script>
	  $(function(){
		if(${not empty msg}){
		 comm.successMsg('${msg}');
		}
	  });
	  
	  function editFlag(id,flag){
	  $.post("${ctx}/admin/sysNotice/edit",{"id":id,"flag":flag},function(data){
	   comm.successMsg("操作成功!");
	   setTimeout(function(){
	   location.reload();
	   }, 1000)})
	  }
	</script>
</body>
</html>