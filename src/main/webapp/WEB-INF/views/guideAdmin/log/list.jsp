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
<title>导游后台主页</title>
</head>
<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">导游日志列表</span>
					</div>
					<div class="actions">
		<a href="${ctx}/guideAdmin/log/create"  class="btn btn-sm green btn-outline" >新增</a>
		<a href="javascript:;" target="deletes" class="btn btn-sm red btn-outline" >删除</a>					
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/log/list" method="post">
	
	<div class="row">
		<div class="col-md-8">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-2" style="text-align: right;">
		<div class="input-group input-large date-picker input-daterange" data-date="2012-10-11" data-date-format="yyyy-mm-dd">
			<input type="text" class="form-control" name="beginCreateTime" value="${beginCreateTime}"> 
			<span class="input-group-addon"> to </span> 
			<input type="text" class="form-control" name="endCreateTime" value="${endCreateTime}">
			
		</div>
		</div>
		
		<div class="col-md-2" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="内容" name="content" value="${content}">
        <button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" class=" group-checkable check-all"></th>
									<th scope="col">用户编号</th>
									<th scope="col">地址</th>
									<th scope="col">内容</th>
									<th scope="col">创建时间</th>
									<th scope="col">操作</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td>
										<td>${item.userNo}</td>
										<td>${item.address}</td>
										<td>${item.content}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>															
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/log/edit/${item.id}">编辑</a>
											<a class="btn green btn-outline" target="delete" href="${ctx}/guideAdmin/log/delete/${item.id}">删除</a>
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
		window.mlx = {
			ctx : "${ctx}"
		};
	</script>





</body>

</html>