<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建订单</title>
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN VALIDATION STATES-->
    <div class="portlet light portlet-fit portlet-form ">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject font-dark sbold uppercase">退款订单</span>
            </div>
            
        </div>
        <div class="portlet-body">
            <!-- BEGIN FORM-->
            <form action="${ctx}/guideAdmin/orderRefund/add" id="form_sample_3" class="form-horizontal" method="post">
            <input type="hidden" name="id" value="${orderRefund.id }"/>
                <div class="form-body">
                    <div class="alert alert-danger display-hide">
                        <button class="close" data-close="alert"></button> You have some form errors. Please check below. </div>
                    <div class="alert alert-success display-hide">
                        <button class="close" data-close="alert"></button> Your form validation is successful! </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">订单号
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="orderNo" data-required="1" class="form-control required" value="${orderRefund.orderNo }"/> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">订单描述
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                        	<input type="text" name="orderBody" data-required="1" class="form-control required" value="${orderRefund.orderBody }"/></div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">退款订单号
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="refundNo" data-required="1" class="form-control required" value="${orderRefund.refundNo }" /> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">退款金额
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" maxlength="10" name="refundPrice" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " data-required="1" class="form-control required" value="${orderRefund.refundPrice }"/><span class="required"> (只能输入数字且不能大于订单金额) </span> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">订单金额
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" maxlength="10" name="orderPrice" onkeyup="this.value=this.value.replace(/[^\d]/g,'') " data-required="1" class="form-control required" value="${orderRefund.orderPrice }"/><span class="required"> (只能输入数字) </span> </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="control-label col-md-3">收款人编号
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="refundUserNo" data-required="1" class="form-control required" value="${orderRefund.refundUserNo }"/> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">收款人
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="refundUserName" data-required="1" class="form-control required" value="${orderRefund.refundUserName }"/> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">退款状态
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <select class="form-control select2me" name="refundStatus">
                                <option value="5">申请退款</option>
                                <option value="6">退款成功</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">审核人编号
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="auditUserNo" data-required="1" class="form-control required" value="${orderRefund.auditUserNo }"/> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">审核人
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="auditUserName" data-required="1" class="form-control" value="${orderRefund.auditUserName }"/> </div>
                    </div>
                  <div class="form-group">
                        <label class="control-label col-md-3">审核说明
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <textarea class="form-control required textarea" id="buyNotice" name="auditRemark" maxlength="1800" rows="8" cols="20" data-name="buyNotice">${orderRefund.auditRemark }</textarea> </div>
                 </div>
                 <div class="form-group">
                        <label class="control-label col-md-3">审核状态
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <select class="form-control select2me" name="auditStatus">
                                <option value="1">待审核</option>
                                <option value="2">审核通过</option>
                                <option value="3">审核未通过</option>
                            </select>
                        </div>
                    </div>
                    
                </div>
                 
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <a href="javascript:;" onClick="javascript:history.back(-1);" class="btn default">取消</a>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
        <!-- END VALIDATION STATES-->
		</div>
	</div>
</div>
</body>
</html>