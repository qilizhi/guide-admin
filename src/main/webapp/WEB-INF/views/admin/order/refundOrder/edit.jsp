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
<title>修改</title>
</head>
<body>		
					
					<h4 class="modal-title" id="exampleModalLabel">修改订单</h4>				
					<form action="${ctx}/admin/orderRefund/editForm" id="inputForm"
						method="post" class="form-horizontal">
							<input type="hidden" id="id" name="id" value="${orderRefund.id}" />
						<div class="form-group">
							<label class="col-lg-3 control-label">订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderNo" id="orderNo" value="${orderRefund.orderNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">订单描述：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderBody" id="orderBody" value="${orderRefund.orderBody}"/>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">退款订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundNo" id="refundNo" value="${orderRefund.refundNo}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">退款金额：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundPrice" id="refundPrice" value="${orderRefund.refundPrice}"/>
							</div>
						</div>	
							<div class="form-group">
							<label class="col-lg-3 control-label">订单金额：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderPrice" id="orderPrice" value="${orderRefund.orderPrice}"/>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">收款人编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundUserNo" id="refundUserNo" value="${orderRefund.refundUserNo}"/>
							</div>
						</div>	<div class="form-group">
							<label class="col-lg-3 control-label">收款人：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundUserName" id="refundUserName" value="${orderRefund.refundUserName}"/>
							</div>
						</div>						
						<div class="form-group">
							<label class="col-lg-3 control-label">退款时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundTime" id="refundTime" 
								value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${orderRefund.refundTime}"/>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">退款状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundStatus" id="refundStatus" value="${orderRefund.refundStatus}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">创建时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="createTime" id="createTime" 
								value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${orderRefund.createTime}"/>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核人编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditUserNo" id="auditUserNo" value="${orderRefund.auditUserNo}"/>
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">审核人：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditUserName" id="auditUserName" value="${orderRefund.auditUserName}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditTime" id="auditTime" 
								value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${orderRefund.auditTime}"/>" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditStatus" id="auditStatus" value="${orderRefund.auditStatus}"/>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核说明：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditRemark" id="auditRemark" value="${orderRefund.auditRemark}"/>
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