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

		<h4 class="modal-title" id="exampleModalLabel">添加音频</h4>				
					<form action="${ctx}/admin/audioInfo/save" id="inputForm"
						method="post" class="form-horizontal">
						<div class="form-group">
							<label class="col-lg-3 control-label">标题：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="title" id="title" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">作者：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="author" id="author" />
							</div>
						</div>
						
						
											
						<div class="form-group">
							<label class="col-lg-3 control-label">时长：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="longTime" id="longTime"  />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">链接地址：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="url" id="url" />
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">背景图地址：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="bgImgUrl" id="bgImgUrl" " />
							</div>
						</div>	
						
						<div class="form-group">
							<label class="col-lg-3 control-label">排序号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="sort" id="sort""/>
							</div>
						</div>
						
						<div class="form-group">
							<label class="col-lg-3 control-label">有效号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="flag" id="flag" "/>
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