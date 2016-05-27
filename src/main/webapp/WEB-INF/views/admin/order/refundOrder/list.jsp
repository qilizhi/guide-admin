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
<title>退款列表</title>
<link href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet">
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>

	
	<div class="row">
		<div class="col-md-12">
			
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">退款列表</span>
					</div>
					
				</div>
				<div class="portlet-body">
   <form:form id="searchForm" action="${ctx}/admin/orderRefund" method="post" modelAttribute="orderRefundModel">
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
		<span class="pull-left" style="line-height:30px;margin-right:20px;">申请时间:</span>
		<%-- <div class="input-group date date-picker pull-left"  data-date-format="yyyy-mm-dd"  style="width:200px">
              <input type="text" class="form-control form-filter input-sm" readonly="readonly" placeholder="开始时间" name="startDate" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${orderRefundModel.startDate}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar"></i>
                  </button>
              </span>
          </div>
          <span class="pull-left" style="margin-top:6px;">—</span>
          <div class="input-group date date-picker pull-left" data-date-format="yyyy-mm-dd" style="width:200px">
              <input type="text" class="form-control form-filter input-sm " readonly="readonly"  placeholder="结束时间" name="endDate" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${orderRefundModel.endDate}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar "></i>
                  </button>
              </span>
          </div> --%>
            
       <div class="input-group input-large date-picker input-daterange pull-left" data-date="2015-5-10" 
				data-date-format="yyyy-mm-dd" data-date-language="zh-CN"  >
				<input type="text" class="WdatePicker form-control input-sm" name="startDate" placeholder="开始时间" value="${orderRefundModel.startDate}"> 
				<span class="input-group-addon"> 至 </span> 
				<input type="text" class="WdatePicker form-control input-sm" name="endDate" placeholder="结束时间" value="${orderRefundModel.endDate}">
			</div>
  
         
  <form:select path="refundStatus" items="${fns:RefundStatus()}" cssClass="form-control input-sm  input-inline pull-left" cssStyle="margin-left:10px;"/>
         <input type="text" class="form-filter input-sm pull-left ml10" placeholder="订单编号" name="orderId" value="${orderRefundModel.orderId }">
		
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
									<th>退款单号</th>
									<th>订单金额</th>
									<th>退款金额</th>
									<th>申请用户ID</th>
									<th>申请时间</th>
									<th>退款标识</th>
									<th>退款状态</th>
									<th>不通过原因</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list }"  var="item">
									<tr>
										<td>${item.orderId}</td>
										<td>${item.refundJnId}</td>
										<td>${item.payFee}</td>
										<td>${item.amount}</td>
										<td>${item.userId }</td>
										<td>
										${fns:longTimeToDate('yyyy-MM-dd HH:mm:ss',item.createTime)} </td>
										<td>
										<c:if test="${item.refundFlag==0 }">用户发起退款</c:if>
										<c:if test="${item.refundFlag==1 }">系统退款</c:if>
									
										</td>
										
										<td>${fns:RefundStatus()[item.refundStatus]}</td>
										<td>${item.remark }</td>
										<td>
											<a href="${ctx}/admin/orderLineInfo/detail?orderId=${item.orderId}&userId=${item.userId}" class="btn btn-sm yellow btn-outline" >详情</a>	
											
											<c:choose>
											<c:when test="${ item.refundStatus=='RR'}">
											<a onclick="audit('${item.refundJnId }',${item.payFee });" class="btn btn-sm blue btn-outline" >审核</a>
											</c:when>
											<c:otherwise>
											<a  class="btn btn-sm  btn-outline disabled" >已审</a>	
											</c:otherwise>
											</c:choose>
											
														
											<c:choose>
											<c:when test="${ item.refundStatus=='RC'}">
											<a class="btn btn-sm red btn-outline" onclick="refund(${item.refundJnId },${item.payFee });">退款</a>	
											</c:when>
											</c:choose>																									
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

<div id="responsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form  id="myform" action="${ctx}/admin/orderRefund/audit" method="POST" onsubmit="return validForm();">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
						<div class="portlet-body">
							<div class="form-body " style="height: 40px;">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
								</div>
								<input type="hidden" name="refundJnId"  />
								<input type="hidden" name="refundStatus" value="RR" />
								<input type="hidden" name="pageSize" value="${paginator.limit}" />
								<input type="hidden" name="pageNo" value="${paginator.page}" />
									
						</div>
						<div style="height:200px;">
						
						<div class="form-group form-md-line-input " >
										<label class="col-lg-3 row control-label text-right mr10">审核状态</label>
										<div class="col-lg-5">
										<label for="auditStatus2" style="white-space: nowrap;" onclick="show(1);">通过
										<input type="radio" id="auditStatus2" name="checkStatus" class="checkStatus1" value="1"  checked="checked" />
									</label>
									
									<label  for="auditStatus3" style="margin-left:100px;white-space: nowrap;" onclick="show(-1)">不通过
										<input type="radio" id="auditStatus3" name="checkStatus" class="checkStatus1" value="-1"   />
									</label>
										</div>
						</div>
						
						       <div class="form-group form-md-line-input " >
										<label class="col-lg-3 row control-label text-right mr10">订单金额:</label>
										<div class="col-lg-5">
										<span id="money"></span>
										</div>
									</div>
						
						            <div class="form-group form-md-line-input " id="amount">
										<label class="col-lg-3 row control-label text-right mr10">退款金额:</label>
										<div class="col-lg-5">
										<input type="text"  name="amount"  id="amount1" class="form-control" placeholder="退款金额"  />
											
											<div class="form-control-focus"></div>
										</div>
									</div>
                              <div class="form-group form-md-line-input none" id="remark" >
									<label class="col-lg-3 control-label row text-right mr10">不通过原因:</label>
									<div class="col-lg-5">
									<textarea name="remark" id="remark1" class="form-control"  rows="3" placeholder="不通过原因" ></textarea>
										<label for="form_control_1"></label>
									</div>
								</div>
								
								
						</div>
					</div>
					<div class="modal-footer">
					<span  id="my_error" class="ml10 text-danger"></span>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit"  class="btn green" >提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	<div id="responsive2"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form  id="myform" action="${ctx}/admin/orderRefund/audit" method="POST" onsubmit="return validForm2();">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
						<div class="portlet-body">
							<div class="form-body " style="height: 40px;">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
								</div>
								<input type="hidden" name="refundJnId"  />
							
								<input type="hidden" name="refundStatus" value="RC" />
								<input type="hidden" name="pageSize" value="${paginator.limit}" />
								<input type="hidden" name="pageNo" value="${paginator.page}" />
									
						</div>
						<div style="height:200px;">
						
						<div class="form-group form-md-line-input " >
										<label class="col-lg-3 row control-label text-right mr10">审核状态</label>
										<div class="col-lg-5">
										<label for="auditStatus4" style="white-space: nowrap;" onclick="show(1);">通过
										<input type="radio" id="auditStatus4" name="checkStatus" value="1" class="checkStatus2"  checked="checked" />
									</label>
									
									<label  for="auditStatus5" style="margin-left:100px;white-space: nowrap;" onclick="show(-1)">不通过
										<input type="radio" id="auditStatus5" name="checkStatus" value="-1"  class="checkStatus2" />
									</label>
										</div>
						</div>
						
						       <div class="form-group form-md-line-input " >
										<label class="col-lg-3 row control-label text-right mr10">订单金额:</label>
										<div class="col-lg-5">
										<span id="money2"></span>
										</div>
									</div>
						
						           
                              <div class="form-group form-md-line-input "  >
									<label class="col-lg-3 control-label row text-right mr10">不通过原因:</label>
									<div class="col-lg-5">
									<textarea name="remark" id="remark2" class="form-control"  rows="3" placeholder="不通过原因" ></textarea>
										<label for="form_control_1"></label>
									</div>
								</div>
								
								
						</div>
					</div>
					<div class="modal-footer">
					<span  id="my_error2" class="ml10 text-danger"></span>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit"  class="btn green" >提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	
	
	
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script> 
	<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" ></script>
	<script src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" ></script>
    <script src="${ctx}/static/js/handle.js"></script>
    
    <script>
       /*审核弹出模态框  */
       function audit(id,payFee){
    	 $("input[name='refundJnId']").val(id);
       money.innerHTML=payFee;
       my_error.innerHTML=null;
       $("textarea[name='remark']").val('');
       $("input[name='amount']").val('');
        $("#responsive").modal();
       }
       
       /*退款弹出模态框  */
       function refund(id,payFee){
    	   $("input[name='refundJnId']").val(id);
    	   money2.innerHTML=payFee;
    	   my_error2.innerHTML=null;
    	   $("#responsive2").modal();
    	   
       }

       function show(num){
    	   if(num==1){
    		   remark.style.display="none";
    		   amount.style.display="block";
    	   }else{
    		   remark.style.display="block";
    		   amount.style.display="none";
    		   
    	   }  
       }
       
       //审核检验
       function validForm(){
    	 var checkStatus=$(".checkStatus1:checked").val();
    	 var amount=amount1.value;
    	 var remark=remark1.value;
    	 var money=$("#money").html();
    	 console.log(checkStatus+"++"+amount+"++"+remark+"=="+money);
    	 var reg=/^(([0-9]+\d*)|([0-9]+\d*\.\d{1,2}))$/;
    	 if(checkStatus==1){
    		
    		if($.trim(amount)==''||amount==null){
    			my_error.innerHTML="退款金额不能为空";   		
    			return false;
    		}
    		if(!reg.test(amount)){
    			my_error.innerHTML="只能为数字,并且最多两位小数";
    			return false;
    		}
    		if(parseFloat(amount)>parseFloat(money)){
    			my_error.innerHTML="退款金额不能大于订单金额";
    			return false;
    		}
    	 }else {
    		 if($.trim(remark)==''||remark==null){
    			 my_error.innerHTML="备注不能为空";
    			 return false;
    		 }
    	 }
    	   return true;
       }
       
       //退款校验
       function validForm2 (){
    	   var checkStatus=$(".checkStatus2:checked").val();
    	   var remark=remark2.value;
    	   if(checkStatus!=1){
    			 my_error2.innerHTML="审核不通过,原因不能为空";
    		   return false;
    	   }
    	   return true;
       }
       
      $(function(){
    	  $('.date-picker').datepicker({
	             rtl: App.isRTL(),
	             autoclose: true
	         });
    	  
    	  if("${msg}"!=""){
    		  comm.successMsg("${msg}");
    	  }
    	  
      });
    </script>
</body>
</html>