<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>线路订单列表</title>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>

	
	<div class="row">
		<div class="col-md-12">
			
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">订单列表</span>
					</div>
					
				</div>
				<div class="portlet-body">
   <form:form id="searchForm" action="${ctx}/admin/orderLineInfo" method="post" modelAttribute="orderModel">
	<div class="row">
		<div class="col-md-12">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
	
		<input type="hidden" name="pageNo" value="1">
		<div class="pull-right">
		<span class="pull-left" style="line-height:30px;margin-right:20px;">下单时间:</span>
		<div class="input-group date date-picker pull-left"  data-date-format="yyyy-mm-dd"  style="width:200px">
              <input type="text" class="form-control form-filter input-sm" readonly="readonly" placeholder="开始时间" name="startDate" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${orderModel.startDate}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar"></i>
                  </button>
              </span>
          </div>
          <span class="pull-left" style="margin-top:6px;">—</span>
          <div class="input-group date date-picker pull-left" data-date-format="yyyy-mm-dd" style="width:200px">
              <input type="text" class="form-control form-filter input-sm " readonly="readonly"  placeholder="结束时间" name="endDate" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${orderModel.endDate}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar "></i>
                  </button>
              </span>
          </div>
            
          <form:select path="orderStatus" items="${fns:OrderPayType()}" cssClass="form-control input-sm  input-inline pull-left" cssStyle="margin-left:10px;">
  
          </form:select>

         <input type="text" class="form-filter input-sm pull-left ml10" placeholder="订单编号" name="orderId" value="${orderModel.orderId }">
		
		  <input type="text" class="form-filter input-sm pull-left ml10" placeholder="联系人" name="userName" value="${orderModel.userName }">
		
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		
		<i class="fa fa-search"></i>  搜索</button>
		
		<button type="button" onclick="formReset();" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		<i class="fa fa-mail-reply-all"></i> 重置</button>
		</div>	
		</div>
	</div>
	</form:form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>订单编号</th>
									<th>商品名称</th>
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
										<td>${fns:OrderPayType()[item.orderStatus]}</td>
										<td>
											<a  href="${ctx}/admin/orderLineInfo/detail?orderId=${item.orderId}&userId=${item.userId}" class="btn btn-sm yellow btn-outline" >详情</a>																								
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

	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/static/assets/pages/scripts/form-validation.min.js"></script>
    <script src="${ctx}/static/js/handle.js"></script>
</body>
</html>