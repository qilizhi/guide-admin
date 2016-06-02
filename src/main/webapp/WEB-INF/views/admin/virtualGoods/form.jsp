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
<title>${title }产品</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div class="portlet light portlet-fit portlet-form ">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-settings font-dark"></i> <span
					class="caption-subject font-dark sbold uppercase">${title }产品</span>
			</div>

		</div>
		<div class="portlet-body">
			<form action="${ctx}/admin/virtualGoods/saveOrUpdate" id="inputForm"
				method="post" class="form-horizontal">
				<input type="hidden" id="id" name="id" value="${item.id}" />

				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">名称: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="name"
							value="${item.name }" placeholder="这里输入标题" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">类别: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<select class="bs-select form-control bs-select-hidden" name="goodsType">
						<option value="1">美丽卡</option>
						<option value="2">课程 </option>
						</select>
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">描述: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="description"
							value="${item.description }" placeholder="这里输入描述" />
						<div class="form-control-focus"></div>
					</div>

				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">图片: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<div class="col-lg-7" id="supprogress">
							<input type="hidden" name="imgUrl" value="${item.imgUrl }" /> <span
								id="imageName"></span>
							<div class="progress">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100">
									<span class="sr-only">40% Complete (success)</span>
								</div>
							</div>
						</div>
						<div class="col-lg-2">
							<img id="image" name="imgUrl" alt="" src="${item.imgUrl}">
							<span class="btn green fileinput-button pading"> <i
								class="fa fa-plus"></i> <span id="load">上传 </span> <input
								type="file" name="files[]" multiple>
							</span>
						</div>
					</div>
				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">价格: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<div class="input-icon right">
							<input type="text" class="form-control" name="price"
								value="${item.price }" placeholder="这里输入价格" />
							<div class="form-control-focus"></div>
						</div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">库存: <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="num"
							value="${item.num }" placeholder="这里输入库存" />
						<div class="form-control-focus"></div>
					</div>
				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-4 control-label">排序号： <span
						class="required"> * </span></label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="sort"
							value="${item.sort }" placeholder="这里输入排序号" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="modal-footer">

					<div class="form-group form-md-line-input">
						<div class="col-lg-5 ">
							<input id="cancel_btn" class="btn btn-primary " type="button"
								value="返回" onclick="history.back()" />

						</div>
						<div class="col-lg-1 ">
							<input type="submit" class="btn btn-primary " vlue="提交">
						</div>
					</div>
				</div>


			</form>
		</div>
	</div>
	<%-- 	<script
		src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
		type="text/javascript"></script> --%>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			handleValidation();
			initImgUpload("imgUrl");
		})
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
			form3.submit(function(e) {
				e.preventDefault();
			});

			// console.log(form3.validate());
			form3.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					name : {
						required : true
					},
					imgUrl : {
						required : true
					},
					price : {
						required : true,
						number : true
					},
					num : {
						required : true,
						digits : true
					},
					description : {
						required : true
					},
					sort : {
						required : true,
						number : true
					}
				},

				messages : { // custom messages for radio buttons and checkboxes
					name : {
						required : "必填。"
					},
					price : {
						required : "必填。",
						digits : "只能输入数字"
					},
					num : {
						required : "必填。",
						digits : "只能输入数字"
					},
					description : {
						required : "必填。"
					},
					sort : {
						required : "必填。",
						digits : "只能输入数字"
					},
					imgUrl : {
						required : "请上传图片"
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
					console.log(element)
					$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
				},

				success : function(label) {
					label.closest('.form-group').removeClass('has-error'); // set success class to the control group
				},

				submitHandler : function(form) {
					success3.show();
					error3.hide();
					form.submit(); // submit the form
				}

			});

		};
	</script>
</body>
</html>