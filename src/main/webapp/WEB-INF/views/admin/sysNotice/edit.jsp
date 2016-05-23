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
	<h4 class="modal-title" id="exampleModalLabel">公告标题:<small>${platformMsg.title}</small></h4>
	<form:form action="${ctx}/admin/sysNotice/edit" id="inputForm"  modelAttribute="platformMsg"
		method="post" class="form-horizontal ">
		
		<form:hidden path="id"/>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>标题(50字内)：</label>
			<div class="col-lg-5">
			<form:input path="title" class="form-control" placeholder="这里输入标题" maxlength="50" required="required"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>内容：</label>
			<div class="col-lg-5"> 
	            	<form:textarea path="content" required="required" placeholder="这里添加内容" cssClass="form-control" cssStyle="height: 219px;width:593px;" />

				<label for="form-control-focus" style="width:593px;"></label>
			
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">链接地址：</label>
			<div class="col-lg-5">
			<form:input path="url" class="form-control" placeholder="请以http://开头" onfocus="this.value==''?this.value='http://':''" onblur="this.value=='http://'?this.value='':this.value=this.value"/>
				
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="text-center">
		<button type="submit" class="btn btn-primary" style="margin-right:20px;">提交</button>		
	  
	   <button type="button" class="btn btn-primary" onclick="history.back(-1);">返回</button>
	   </div>	
	</form:form>
   <script type="text/javascript">
    function text(dom){
       console.log(dom.value=="http://");
    }
   </script>
</body>
</html>