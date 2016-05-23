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
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">成员名单</span>
					</div>
					<div class="actions">
                        <div class="btn-group">
                           <a href="javascript:;" onClick="javascript:history.back(-1);">返回</a>
                            
                        </div>
                    </div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/tuan/tuanGuest/search" method="post">
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
		
		<div class="col-md-8" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="手机号码" name="mobile" value="${guest.mobile}">
		<input type="text" class="form-filter input-sm" placeholder="姓名" name="guestName" value="${guest.guestName}">
		<input type="text" class="form-filter input-sm" placeholder="订单号" name="orderNo" value="${guest.orderNo}">
		<input type="text" class="form-filter input-sm" placeholder="出团号" name="tuanNo" value="${guest.tuanNo}">
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
									<th scope="col">出团编号</th>
									<th scope="col">成员名称</th>
									<th scope="col">手机号码</th>
									<th scope="col">所属订单号</th>
									<th scope="col">签到时间</th>
									<th scope="col">签到状态</th>
									<th scope="col">备注</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td>
										<td>${item.tuanNo}</td>
										<td>${item.guestName}</td>
										<td>${item.mobile}</td>
										<td>${item.orderNo}</td>
										<td><fmt:formatDate value="${item.updateTime}"/> </td>
										<td>${ESignInStatus[item.status]} </td>
										<td>${item.remark}</td>
										
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

	</script>





</body>

</html>