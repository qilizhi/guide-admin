<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
<title>美丽行</title>
</head>
<body>
	<h4 class="modal-title" id="exampleModalLabel">${wxPublic.name}公众号</h4>
	<form:form action="${ctx}/admin/wxPublic/edit" id="inputForm"  modelAttribute="wxPublic"
		method="post" class="form-horizontal ">
		
		<form:hidden path="id"/>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>公众号名称：</label>
			<div class="col-lg-5">
			<form:input path="name" class="form-control" placeholder="这里输入公众号" required="required"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>APPID：</label>
			<div class="col-lg-5"> 
			<form:input path="appId" class="form-control" placeholder="这里输入APPID" required="required"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>appSecret：</label>
			<div class="col-lg-5">
			<form:input path="appSecret" class="form-control" placeholder="这里输入appSecret" required="required"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">服务认证地址：</label>
			<div class="col-lg-5">
			<form:input path="serverUrl" class="form-control" placeholder="这里输入服务认证地址"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">公众号Token：</label>
			<div class="col-lg-5">
			<form:input path="token" class="form-control" placeholder="这里输入公众号Token"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">公众号EncodingAESKey：</label>
			<div class="col-lg-5">
			<form:input path="encodingaeskey" class="form-control" placeholder="这里输入公众号EncodingAESKey"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">网页授权获取用户信息回调地址：</label>
			<div class="col-lg-5">
			<form:input path="redirectUrl" class="form-control" placeholder="这里输入回调地址"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">微信支付商家号：</label>
			<div class="col-lg-5">
			<form:input path="partnerId" class="form-control" placeholder="这里输入微信支付商家号"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">微信支付商家密钥：</label>
			<div class="col-lg-5">
			<form:input path="partnerKey" class="form-control" placeholder="这里输入微信支付商家密钥"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">支付成功回调地址：</label>
			<div class="col-lg-5">
			<form:input path="payCallbackUrl" class="form-control" placeholder="这里输入支付成功回调地址"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">图片：</label>
			<div class="col-lg-5">
			
			<form:input path="iconUrl" class="form-control" placeholder="这里输入图片地址" onblur="this.value=='http://'?this.value='':''" onfocus="this.value==''?this.value='http://':''"/>
		    <div class="form-control-focus"></div>
			</div>
		</div>
		
		<form:hidden path="flag"/>
		<%-- <div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">状态：</label>
			<div class="col-lg-5">
			<label for="flag1" style="margin-right:10px;"><form:radiobutton path="flag" value="0" id="flag1"/>无效</label>
			<label for="flag2"><form:radiobutton path="flag" value="1" id="flag2"/>有效</label>
			</div>
		</div> --%>
	
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">创建时间：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="createTime" readonly="readonly"
					value="<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${wxPublic.createTime}"/>" />
			<div class="form-control-focus"></div>
			</div>
		</div>

		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">更新时间：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="updateTime" readonly="readonly"
					value="<fmt:formatDate pattern="yyyy-MM-dd  HH:mm:ss" value="${wxPublic.updateTime}"/>" />
			</div>
		</div>
		<div class="text-center">
		
		<button type="submit" class="btn btn-primary" style="margin-right:20px;">提交</button>		
	  
	   <button type="button" class="btn btn-primary" onclick="history.back(-1);">返回</button>
	   </div>	
	</form:form>
	<script type="text/javascript">
	
	
	</script>
</body>
</html>