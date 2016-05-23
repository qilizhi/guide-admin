<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包记录</title>

</head>

<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">我的粉丝</span>
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/guideFans/list" method="post">
	<div class="row">
		<div class="col-md-9">
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
		
		<div class="col-md-1" style="text-align: right;">
        <button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover text-center">
							<thead>
								<tr>
									<td>粉丝头像</td>
									<td>粉丝名</td>
									<td>关注时间</td>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td><img alt="" src="${item.headImgUrl}" height="50px" width="50px"/></td>
										<td>${item.nickName}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										
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



</body>
</html>