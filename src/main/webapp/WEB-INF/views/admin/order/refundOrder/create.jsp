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
					<h4 class="modal-title" id="exampleModalLabel">新增退款订单</h4>				
					<form action="${ctx}/admin/orderRefund/save" id="inputForm"
						method="post" class="form-horizontal">
							
						<div class="form-group">
							<label class="col-lg-3 control-label">订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderNo" id="orderNo" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">订单描述：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderBody" id="orderBody" />
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">退款订单号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundNo" id="refundNo" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">退款金额：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundPrice" id="refundPrice" />
							</div>
						</div>	
							<div class="form-group">
							<label class="col-lg-3 control-label">订单金额：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="orderPrice" id="orderPrice" />
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">收款人编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundUserNo" id="refundUserNo" />
							</div>
						</div>	<div class="form-group">
							<label class="col-lg-3 control-label">收款人：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundUserName" id="refundUserName" />
							</div>
						</div>											
						<div class="form-group">
							<label class="col-lg-3 control-label">退款状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="refundStatus" id="refundStatus" />
							</div>
						</div>
				
						<div class="form-group">
							<label class="col-lg-3 control-label">审核人编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditUserNo" id="auditUserNo" />
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">审核人：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditUserName" id="auditUserName" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditTime" id="auditTime" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditStatus" id="auditStatus" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核说明：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditRemark" id="auditRemark" />
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