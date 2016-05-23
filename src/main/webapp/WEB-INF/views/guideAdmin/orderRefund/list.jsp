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
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">订单退款列表</span>
					</div>
					
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/orderRefund/list" method="post">
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
		<input type="text" class="form-filter input-sm" placeholder="收款人" name="searchStr" value="${searchStr}">		
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
				
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col">订单号</th>
									<th scope="col">订单描述</th>
									<th scope="col">退款订单号</th>
									<th scope="col">退款金额</th>
									<th scope="col">订单金额</th>
									<th scope="col">收款人编号</th>
									<th scope="col">收款人</th>
									<th scope="col">退款时间</th>
									<th scope="col">退款状态</th>
									<th scope="col">创建时间</th>
									<th scope="col">审核人编号</th>
									<th scope="col">审核人</th>
									<th scope="col">审核时间</th>
									<th scope="col">审核状态</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.orderNo}</td>
										<td>${item.orderBody}</td>
										<td>${item.refundNo}</td>
										<td>${item.refundPrice}</td>
										<td>${item.orderPrice}</td>
										<td>${item.refundUserNo}</td>
										<td>${item.refundUserName}</td>
										<td><fmt:formatDate value="${item.refundTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${ERefundStatus[item.refundStatus] }</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.auditUserNo}</td>
										<td>${item.auditUserName}</td>
										<td><fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.auditUserName}</td>
										<td>${EAuditStatus[item.auditStatus]}</td>
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