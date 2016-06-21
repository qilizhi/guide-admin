<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css">
<title>万人群创建</title>
</head>
<body>
		<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">创建群</span>
					</div>

				</div>
				<div class="portlet-body">
	
	<form action="${ctx}/admin/group/add" id="inputForm" method="post"
		class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${group.id}" />
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>群名称：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="emGname" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">群分类：</label>
			<div class="col-lg-5">
				<select name="type" class="bs-select form-control ">
					<option value=0>玩家群</option>
					<option value=1>明星群</option>
				</select>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">群组创建人环信用户名：</label>
			<div class="col-lg-5">
				<!-- <input type="text" class="form-control " name="emGuser" /> -->

				<select class="selectpicker" name="emGuser" data-live-search="true">

				</select>
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>群组介绍：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="emGdesc" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>群组形象照：</label>
			<div class="col-lg-5">
				<div class="col-lg-7" id="supprogress">
					<input type="hidden" name="image" /> <span id="imageName"></span>
					<div class="progress">
						<div class="progress-bar progress-bar-success" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<img id="image" name="imgUrl" alt="" src=""> <span
						class="btn green fileinput-button pading"> <i
						class="fa fa-plus"></i> <span id="load">上传 </span> <input
						type="file" name="files[]" multiple>
					</span>
				</div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>群组人数上限：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="emMaxusers" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">是否公开：</label>
			<div class="col-lg-5">
				<select name="emPublic" class="bs-select form-control ">
					<option value=0>不公开</option>
					<option value=1>公开</option>
				</select>
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">是否需要验证才能加入：</label>
			<div class="col-lg-5">
				<select name="emAllowinvites" class="bs-select form-control ">
					<option value=0>不需要</option>
					<option value=1>需要</option>
				</select>
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label"><span style="color:red">*</span>进群规则：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="enterRule" />
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<div class="col-lg-9 col-lg-offset-3">
				<input id="submit_btn" type="submit" class="btn btn-primary"
					value="提交" /> <input id="cancel_btn" class="btn btn-primary "
					type="button" value="返回" onclick="history.back()" />
			</div>
		</div>
	</form>
	</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>

	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
<script type="text/javascript"
		src="${ctx}/static/js/emGroup.js"></script>

	<script type="text/javascript">
		$(function() {
			EmGroup.formInit(mlx.ctx+"/admin/group/create");
			EmGroup.selectInit("emGuser",$("#emGuser").val());
		})

	</script>
	
</body>
</html>