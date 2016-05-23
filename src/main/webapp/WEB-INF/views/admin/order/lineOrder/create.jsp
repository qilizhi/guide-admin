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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>新增</title>
</head>
<body>		
					<h4 class="modal-title" id="exampleModalLabel">添加订单</h4>				
					<form action="${ctx}/admin/orderLineInfo/save" id="inputForm"
						method="post" class="form-horizontal">

						<div class="form-group">
							<label class="col-lg-3 control-label">订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderNo" id="orderNo"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">父订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="parentOderNo" id="parentOderNo"/>
							</div>
						</div>						
						<div class="form-group">
							<label class="col-lg-3 control-label">线路名称：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="proName" id="proName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">供应商：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="proSupplierName" id="proSupplierName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">支付人名称：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="payUserName" id="payUserName"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">联系电话：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="payUserMobile" id="payUserMobile"/>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">总额：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="totalPrice" id="totalPrice"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">数量：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="totalNum" id="totalNum"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">支付状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="payStatus" id="payStatus"/>
							</div>
						</div>				
						<div class="form-group">
							<div class="col-lg-9 col-lg-offset-3">
								<input id="submit_btn" type="submit" class="btn btn-primary" value="提交" />
								 <input id="cancel_btn" class="btn btn-primary "
					type="button" value="返回" onclick="history.back()" />
							</div>
							
						</div>
					</form>
</body>
</html>