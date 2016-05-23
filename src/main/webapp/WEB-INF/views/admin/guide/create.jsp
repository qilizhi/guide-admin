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

		<h4 class="modal-title" id="exampleModalLabel">新增</h4>				
					<form action="${ctx}/admin/guide/save" id="inputForm"
						method="post" class="form-horizontal">

						<div class="form-group">
							<label class="col-lg-3 control-label">编号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="realName" id="realName"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">真实姓名：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="realName" id="realName" />
							</div>
						</div>						
						<div class="form-group">
							<label class="col-lg-3 control-label">性别：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="sex" id="sex" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">城市：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="city" id="city"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">头像：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="headImgUrl" id="headImgUrl" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">导游级别：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="guideLevel" id="guideLevel"  />
							</div>
						</div>	
						<div class="form-group">
							<label class="col-lg-3 control-label">导游证号：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="guideCardNo" id="guideCardNo"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">手机号码：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="mobile" id="mobile"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">审核状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="auditStatus" id="auditStatus"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">状态：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="status" id="status"  />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">更新时间：</label>
							<div class="col-lg-5">
								<input type="text" class="form-control " name="updateDate" id="updateDate"  />
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