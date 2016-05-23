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
<title>新增页面</title>
</head>
<body>

		<h4 class="modal-title" id="exampleModalLabel">新增导游日志</h4>				
					<form action="${ctx}/admin/guideLog/save" id="inputForm"
						method="post" class="form-horizontal">

						<div class="form-group">
							<label class="col-lg-3 control-label">编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="id" id="id" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">用户编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="userNo" id="userNo" />
							</div>
						</div>						
						<div class="form-group">
							<label class="col-lg-3 control-label">地址：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="address" id="address"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">内容：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="content" id="content" />
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