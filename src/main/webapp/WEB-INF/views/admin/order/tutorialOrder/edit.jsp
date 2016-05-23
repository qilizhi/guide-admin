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
					<h4 class="modal-title" id="exampleModalLabel">新增智库订单</h4>				
					<form action="${ctx}/admin/orderTutorial/editForm" id="inputForm"
						method="post" class="form-horizontal">
						<input type="hidden" id="id" name="id" value="${orderTutorial.id}" />
						<div class="form-group">
							<label class="col-lg-3 control-label">订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderNo" id="orderNo" value="${orderTutorial.orderNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">提供教程的用户编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="tutorialUserNo" id="tutorialUserNo" value="${orderTutorial.tutorialUserNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">智库编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="tutorialNo" id="tutorialNo" value="${orderTutorial.tutorialName}"/>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">教程名称：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="tutorialName" id="tutorialName" value="${orderTutorial.tutorialName}"/>
							</div>
						</div>										
						<div class="form-group">
							<label class="col-lg-3 control-label">数量：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="totalNum" id="totalNum" value="${orderTutorial.totalNum}">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">价格：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="price" id="price" value="${orderTutorial.price}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">总价：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="totalPrice" id="totalPrice" value="${orderTutorial.totalPrice}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="createTime" id="createTime" 
								value="<fmt:formatDate pattern="yyyy-MM-dd" value="${orderTutorial.createTime}"/>" />	
					
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">支付时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="payTime" id="payTime"
								value="<fmt:formatDate pattern="yyyy-MM-dd" value="${orderTutorial.payTime}"/>" />								
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">支付机构类型：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="paySourceType" id="paySourceType" value="${orderTutorial.paySourceType}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">返回的订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="outTradeNo" id="outTradeNo" value="${orderTutorial.outTradeNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">购买人用户编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="userNo" id="userNo" value="${orderTutorial.userNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">备注：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="remark" id="remark" value="${orderTutorial.remark}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="status" id="status" value="${orderTutorial.status}"/>
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