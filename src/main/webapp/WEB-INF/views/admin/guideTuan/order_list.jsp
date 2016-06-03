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
<style type="text/css">

.actions span{

margin-left:10px;
}
</style>
</head>

<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase"> 出团名称：<%=request.getParameter("tuanName") != null ? request.getParameter("tuanName") : ""%></span>
					</div>
					<div class="actions">
						<!-- 统计 -->
						<c:set var="totalMoney" scope="session" value="0"></c:set>
						<c:set var="totalNum" scope="session" value="0"></c:set>
						<c:forEach items="${countlist}" var="item">
							<c:set var="totalMoney" value="${totalMoney+item.totalSellPrice}"></c:set>
							<c:forEach items="${item.orderGoods}" var="goods">
							   <c:set var="totalNum" value="${totalNum+fn:length(goods.goodsTourists)}"></c:set>
								<c:forEach items="${goods.goodsTourists}" var="tourist"></c:forEach>
							</c:forEach>
						</c:forEach>

						<span class="caption-subject font-dark bold ">
							出团日期：<fmt:formatDate value="${tuan.tuanDate }"
												pattern="yyyy-MM-dd" /></span> <span class="caption-subject font-dark bold  ">
							导游 ：${tuan.userName}</span> <span class="caption-subject font-dark bold ">
							出团编 号 ：${tuan.tuanNo}</span> <span class="caption-subject font-dark bold  ">
							总金额：${totalMoney} </span> <span
							class="caption-subject font-dark bold "> 出行人数：${totalNum}</span> <span
							class="caption-subject font-dark bold  ">满员人数：${tuan.fullNum }</span>
						

					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm"
						action="${ctx}/admin/guideTuan/order/detail/${groupNo}?tuanName=<%=request.getParameter("tuanName") != null ? request.getParameter("tuanName") : ""%>"
						method="post">
						<div class="row">

							<div class="col-md-4">
								<input type="hidden" name="pageNo"> <select
									name="pageSize"
									class="form-control input-sm input-xsmall input-inline">
									<option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
									<option value="10"
										<c:if test="${pageSize == 10}">selected</c:if>>10</option>
									<option value="20"
										<c:if test="${pageSize == 20}">selected</c:if>>20</option>
									<option value="50"
										<c:if test="${pageSize == 50}">selected</c:if>>50</option>
									<option value="100"
										<c:if test="${pageSize == 100}">selected</c:if>>100</option>
								</select> <select name="orderStatus"
									class="form-control input-sm input-xsmall input-inline">
									<option value="">全部</option>
									<option value="C1"
										<c:if test="${orderStatus =='C1'}">selected</c:if>>预登记</option>
									<option value="W"
										<c:if test="${orderStatus=='W'}">selected</c:if>>未支付</option>
									<option value="S"
										<c:if test="${orderStatus=='S'}">selected</c:if>>已支付</option>
									<option value="SF"
										<c:if test="${orderStatus=='SF'}">selected</c:if>>已完成</option>
									<option value="C2"
										<c:if test="${orderStatus=='C2'}">selected</c:if>>已取消</option>
									<option value="RC"
										<c:if test="${orderStatus=='RC'}">selected</c:if>>退款审核中</option>
									<option value="F"
										<c:if test="${orderStatus=='F'}">selected</c:if>>交易失败</option>
									<option value="RP"
										<c:if test="${orderStatus=='RP'}">selected</c:if>>部分退款</option>
									<option value="RF"
										<c:if test="${orderStatus=='RF'}">selected</c:if>>全部退款</option>
									<option value="D"
										<c:if test="${orderStatus=='D'}">selected</c:if>>已删除</option>
								</select>
							</div>

							<div class="col-md-8" style="text-align: right;">
								<input type="text" class="form-filter input-sm"
									placeholder="手机号码" name="mobile" value="${mobile}"> <input
									type="text" class="form-filter input-sm" placeholder="姓名"
									name="userName" value="${userName}"> <input type="text"
									class="form-filter input-sm" placeholder="订单号" name="orderId"
									value="${orderId}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>

							</div>

						</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!-- 		<th scope="col"><input type="checkbox"
										class=" group-checkable check-all"></th> -->
									<th scope="col">订单号</th>
									<th scope="col">预订ID</th>
									<th scope="col">预订人</th>
									<th scope="col">手机号码</th>
									<th scope="col">总价</th>
									<th scope="col">出行人</th>
									<th scope="col">预订时间</th>
									<th scope="col">支付时间</th>
									<th scope="col">状态</th>
									<th scope="col">操作</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item">

									<tr>
										<%-- 	<td><input type="checkbox"
											class="group-checkable check-all-item" value="${item.id}"></td> --%>
										<td>${item.orderId}</td>
										<td>${item.userId}</td>
										<td>${item.userName}</td>
										<td>${item.orderDescribe.mobile}</td>
										<td>${item.totalSellPrice}</td>
										<td><c:forEach items="${item.orderGoods}" var="goods">
												<c:forEach items="${goods.goodsTourists}" var="tourist">
										${tourist.touristName} 
										</c:forEach>
											</c:forEach></td>
										<td>${item.orderDate}</td>
										<td>${item.payDate }</td>
										<td>${payStatus[item.orderStatus]}</td>
										<td><a
											href="${ctx}/admin/orderLineInfo/detail?orderId=${item.orderId}&userId=${item.userId}"
											class="btn btn-sm green btn-outline cancel">详情</a></td>

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