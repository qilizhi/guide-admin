<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${title}</title>
<link
	href="${ctx}/static/assets/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
<link
	href="${ctx}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css">
</head>
<body>
	<div class="portlet light portlet-fit portlet-form ">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-settings font-dark"></i> <span
					class="caption-subject font-dark sbold uppercase">${title}</span>
			</div>

		</div>
		<div class="portlet-body">
			<%-- <h4 class="modal-title" id="exampleModalLabel">${title}</h4> --%>
			<form action="${ctx}${actionUrl}" id="inputForm" method="post"
				class="form-horizontal">
				<input type="hidden" id="id" name="id" value="${advInfo.id }" />

				<div class="form-group  margin-top-20">
					<label class="control-label col-md-3">推荐位置编号 <span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="position" value="${advInfo.position }" />
						</div>
					</div>
				</div>

				<div class="form-group">
					<label class="control-label col-md-3">链接地址 <span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="href" value="${advInfo.href }" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">图片 <span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<%-- 	<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="href" value="${advInfo.href }" />
						</div> --%>
						<input type="hidden" name="imgUrl" value="${advInfo.imgUrl}" />
						<div class="col-lg-7" id="supprogress">
							<span id="imageName"></span>
							<div class="progress">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
						<div class="col-lg-2">
							<img id="image" name="imgUrl" alt="" src="${advInfo.imgUrl}">
							<span class="btn green fileinput-button pading"> <i
								class="fa fa-plus"></i> <span id="load">上传 </span> <input
								type="file" name="files[]" multiple />
							</span>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">描述 <span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="description" value="${advInfo.description }" />
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">开始日期<span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly="" name="startDate"
								value="<fmt:formatDate value="${advInfo.startDate}"
												pattern="yyyy-MM-dd " />"
								class="form-control"> <span class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-md-3">结束日期 <span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-group date form_datetime">
							<input type="text" size="16" readonly="" name="endDate"
								value="<fmt:formatDate value="${advInfo.endDate}"
												pattern="yyyy-MM-dd " />"
								class="form-control"> <span class="input-group-btn">
								<button class="btn default date-set" type="button">
									<i class="fa fa-calendar"></i>
								</button>
							</span>
						</div>
					</div>
				</div>
				<c:if test="${advInfo.id != null }">
					<div class="form-group  margin-top-20">
						<label class="control-label col-md-3">状态<span
							class="required"> * </span>
						</label>
						<div class="col-md-4">
							<select name="status">
								<c:forEach items="${EStatus.keySet()}" var="item">
									<option value="${item}"
										<c:if test="${advInfo.status==item}" >selected</c:if>>${EStatus[item]}</option>
								</c:forEach>
							</select>
						</div>
					</div>
				</c:if>
				<div class="form-group  margin-top-20">
					<label class="control-label col-md-3">排序号<span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="sort" value="${advInfo.sort }" />
						</div>
					</div>
				</div>

				<%-- 			<div class="form-group  margin-top-20">
					<label class="control-label col-md-3">状态<span
						class="required"> * </span>
					</label>
					<div class="col-md-4">
						<div class="input-icon right">
							<i class="fa"></i> <input type="text" class="form-control"
								name="status" value="${advInfo.status }" />
						</div>
					</div>
				</div> --%>

				<div class="modal-footer">

					<div class="form-group form-md-line-input">
						<div class="col-lg-5 ">
							<input id="cancel_btn" class="btn btn-primary " type="button"
								value="返回" onclick="history.back()" />
							<button type="submit" class="btn btn-primary ">提交</button>
						</div>

					</div>
				</div>

			</form>
		</div>
	</div>

	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			handleValidation();
			initImgUpload("imgUrl");
		})
		$(".form_datetime").datepicker({
			format : 'yyyy-mm-dd',
			language : 'zh-CN'
		});

		/** 图片上传的控件 **/
		var initImgUpload = function(dataInputName) {
			var inputFile = $("input[type='file']");
			var $dataInput = $("input[name='" + dataInputName + "']");
			//图上传
			$("#supprogress").css('display', "none");
			$("input[type='file']").css({
				'right' : '0',
				'width' : '70px',
				'height' : '22px',
				'z-index' : '22',
				'margin-top' : '-20px',
				'margin-left' : '-5px',
				'opacity' : '0',
				'-ms-filter' : 'alpha(opacity=0)',
				'direction' : 'ltr',
				'cursor' : 'pointer',
			});
			$("input[type='file']").parent().css({

				'padding' : '0',

			});
			inputFile.on('change', function(e) {
				var files = this.files;
				var fullname = $(this).val();
				$("#imageName").html(
						fullname.substring(fullname.lastIndexOf("\\") + 1));
				$("#supprogress").css('display', "block");
				$("#image").attr("src", "");
				$dataInput.val("");
			})
			inputFile
					.fileupload({
						type : "post",
						dataType : 'json',
						url : mlx.ctx + '/upload',
						progressall : function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$('.progress .progress-bar-success').css('width',
									progress + '%');
							$('.progress .progress-bar-success').text(
									progress + '%');
							//console.log(data);
						},

						done : function(e, data) {
							if (data.result.code == "200") {
								$("#supprogress").css('display', "none");
								$("#image").attr("src",
										data.result.result[0].filePath);
								$dataInput.val(data.result.result[0].filePath);
								$("#load").html("重传");
							} else {
								$('.progress .progress-bar-success').text(
										data.result.msg);
							}
							//console.log(data);
							$('.progress .progress-bar-success').text("done");
						}
					});
		}
		//验证框架
		var handleValidation = function() {
			var form3 = $('#inputForm');
			var error3 = $('.alert-danger', form3);
			var success3 = $('.alert-success', form3);

			form3.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					position : {
						required : true,
						digits : true
					},
					href : {
						required : true

					},
					description : {
						required : true
					},
					sort : {
						required : true,
						digits : true,
						maxlength : 10
					},
					startDate : {
						required : true
					},
					endDate : {
						required : true
					},
					imgUrl : {
						required : true
					}
				},

				messages : { // custom messages for radio buttons and checkboxes
					position : {
						required : "不能为空！",
						digits : "请输入数字！"
					},
					href : {
						required : "不能为空！"

					},
					description : {
						required : "不能为空！"
					},
					sort : {
						required : "不能为空！",
						digits : "请输入数字！"
					},
					startDate : {
						required : "不能为空！"
					},
					endDate : {
						required : "不能为空！"
					},
					imgUrl : {
						required : "不能为空！"
					}
				},

				errorPlacement : function(error, element) { // render error placement for each input type
					if (element.parent(".input-group").size() > 0) {
						error.insertAfter(element.parent(".input-group"));
					} else if (element.attr("data-error-container")) {
						error.appendTo(element.attr("data-error-container"));
					} else if (element.parents('.radio-list').size() > 0) {
						error.appendTo(element.parents('.radio-list').attr(
								"data-error-container"));
					} else if (element.parents('.radio-inline').size() > 0) {
						error.appendTo(element.parents('.radio-inline').attr(
								"data-error-container"));
					} else if (element.parents('.checkbox-list').size() > 0) {
						error.appendTo(element.parents('.checkbox-list').attr(
								"data-error-container"));
					} else if (element.parents('.checkbox-inline').size() > 0) {
						error.appendTo(element.parents('.checkbox-inline')
								.attr("data-error-container"));
					} else {
						error.insertAfter(element); // for other inputs, just perform default behavior
					}
				},

				invalidHandler : function(event, validator) { //display error alert on form submit   
					success3.hide();
					error3.show();
					App.scrollTo(error3, -200);
				},

				highlight : function(element) { // hightlight error inputs
					$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
				},

				unhighlight : function(element) { // revert the change done by hightlight
					$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
				},

				success : function(label) {
					label.closest('.form-group').removeClass('has-error'); // set success class to the control group
				},

				submitHandler : function(form) {
					success3.show();
					error3.hide();
					form[0].submit(); // submit the form
				}

			});

		};
	</script>

</body>
</html>