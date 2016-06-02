<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单</title>
<link rel="stylesheet" href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css">
<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>

</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">订单列表</span>
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/guideOrder" method="post">
	<div class="row">
	
	
	
		<div class="col-md-6">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<%-- <option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option> --%>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		<select class="select2me input-sm input-xsmall input-inline" name="orderStatus">
			<option value="">全部</option>
			<!-- <option value="C1">预登记</option> -->
			<option value="W" <c:if test="${orderModel.orderStatus=='W'}">selected</c:if>>未支付 </option>
			<option value="S" <c:if test="${orderModel.orderStatus=='S'}">selected</c:if>>已支付</option>
			<option value="F" <c:if test="${orderModel.orderStatus=='F'}">selected</c:if>>交易失败</option>
			<option value="C2" <c:if test="${orderModel.orderStatus=='C2'}">selected</c:if>>已取消</option>
<!-- 			<option value="SF">已完成</option>
			<option value="RC">退款审核中</option>
			<option value="RW">等待退款</option>
			<option value="RS">已退款</option>
			<option value="RP">部分退款</option>
			<option value="RF">全额退款</option>
			<option value="D">已删除</option> -->
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-3">
		<div class="input-group input-large date-picker input-daterange" data-date="2012-11-10" data-date-format="yyyy-mm-dd">
			<input type="text" class="form-control" name="startDate" value="${orderModel.startDate}" style="height:30px;"> 
			<span class="input-group-addon"> to </span> 
			<input type="text" class="form-control" name="endDate" value="${orderModel.endDate}" style="height:30px;">
		</div>	
		</div> 
		<div class="col-md-3 text-right" >
		<input type="text" class="form-filter input-sm" placeholder="订单号" name="orderId" value="${orderModel.orderId}">
		<%-- <input type="text" class="form-filter input-sm" placeholder="用户名" name="userName" value="${userName}">
		<input type="text" class="form-filter input-sm" placeholder="手机号码" name="mobile" value="${mobile}"> --%>	
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>	
		
	</div>
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!-- <th scope="col">订单编号</th>
									<th scope="col">用户编号</th>
									<th scope="col">用户名</th>
									<th scope="col">订单类型</th>
									<th scope="col">订单流水日期</th>
									<th scope="col">订单流水时间</th>
									<th scope="col">订单流水状态</th>
									<th scope="col">订单市场总额</th>
									<th scope="col">订单总销售额</th>
									<th scope="col">应付金额</th>
									<th scope="col">退款标识</th>
									<th scope="col">退款金额</th>
									<th scope="col">币种</th>
									<th scope="col">失效时间</th>
									<th scope="col">下单终端渠道</th>
									<th scope="col">创建时间</th>
									<th scope="col">修改时间</th>
									<th scope="col">操作</th> -->
									
									<th>订单编号</th>
									<th>商品名称</th>
									<th>订单类型</th>
									<th>订单支付总额</th>
									<th>购买人</th>
									<th>下单时间</th>
									<th>支付时间</th>
									<th>支付状态</th>
									<th>操作</th>
									
								</tr>
							</thead>
							<tbody>
							<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.orderId }</td>
										<td>
										<c:forEach var="goods" items="${item.orderGoods }">
										   ${goods.goodsName }
										</c:forEach>
										</td>
										<td>${EGoodsType[item.orderType]}</td>
										<td>${item.payFee}</td>
										<td>${item.orderDescribe.contactsName }</td>
										<td>
										
										<fmt:parseDate value="${item.orderDate }" var="orderDate" pattern="yyyyMMdd"></fmt:parseDate>
										<fmt:formatDate value="${orderDate }" pattern="yyyy-MM-dd " />  </td>
										<td>
										
										<fmt:parseDate value="${item.payDate}" pattern="yyyyMMdd" var="payDate"/>
										<fmt:parseDate value="${item.payTime}"  pattern="HHmmss" var="payTime"/>
										<fmt:formatDate value="${payDate}" pattern="yyyy-MM-dd"/>&nbsp;
										<fmt:formatDate value="${payTime}" pattern="HH:mm:ss"/>
										</td>
										<td>${OrderPayType[item.orderStatus]}</td>
										<td>
											<a  href="${ctx}/guideAdmin/guideOrder/detail?orderId=${item.orderId}&userId=${item.userId}" class="btn btn-sm yellow btn-outline" >详情</a>																								
										</td>
									</tr>
								</c:forEach>
							
							
							
							<%-- 	<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.orderId}</td>
										<td>${item.userId}</td>
										<td>${item.userName}</td>
										<td>${item.orderType}</td>
										<td>${item.orderDate}</td>
										<td>${item.orderTime}</td>
										<td>${item.orderStatus}</td>
										<td>${item.totalMarketPrice}</td>
										<td>${item.totalSellPrice}</td>
										<td>${item.payFee}</td>
										<td>${item.refundFlag}</td>
										<td>${item.refundFee}</td>
										<td>${item.currency}</td>
										<td>${item.expTime}</td>
										<td>${item.sysCnl}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/guideOrder/detail?orderId=${item.orderId}&userId=${item.userId}">详情</a>
										</td>
										
									</tr>
								</c:forEach> --%>
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
//初始化时间日历
$(function(){
	$(".date-picker input").datepicker({
		format: "yyyy-mm-dd"
	});
})
</script>

</body>

</html>