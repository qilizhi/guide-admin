<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.imgUpload {
	right: 0;
	width: 70px;
	height: 22px;
	z-index: 22;
	/* float: right; */
	/* margin: 0; */
	margin-top: -20px;
	margin-left: -5px;
	opacity: 0;
	-ms-filter: 'alpha(opacity=0)';
	direction: ltr;
	cursor: pointer;
}

.pading {
	padding: 0;
}
</style>
<title>创建地陪</title>

<script
	src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"
	type="text/javascript"></script>
<script
	src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js"
	type="text/javascript"></script>
<script
	src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"
	type="text/javascript"></script>
<script src="${ctx}/static/assets/pages/scripts/upload.js"
	type="text/javascript"></script>
<script type="text/javascript" charset="utf-8"
	src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8"
	src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8"
src="${ctx }/static/assets/global/plugins/UEeditor/lang/zh-cn/zh-cn.js"></script>


<!-- 验证框架 -->
<script
	src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js"
	type="text/javascript"></script>
<script
	src="${ctx}/static/assets/global/plugins/jquery-validation/js/additional-methods.min.js"
	type="text/javascript"></script>

</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">地陪描述</span>
					</div>

				</div>
				<div class="form-wizard">
					<div class="form-body">
						<ul class="nav nav-pills nav-justified steps">
							<li class="active"><a href="#tab1" data-toggle="tab"
								class="step" aria-expanded="true"> <span class="number">
										1 </span> <span class="desc"> <i class="fa fa-check"></i> 导服
								</span>
							</a></li>
							<li><a href="#tab2" data-toggle="tab" class="step"> <span
									class="number"> 2 </span> <span class="desc"> <i
										class="fa fa-check"></i> 价格
								</span>
							</a></li>
							<!-- <li>
	                             <a href="#tab3" data-toggle="tab" class="step active">
	                                 <span class="number"> 3 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 行程 </span>
	                             </a>
	                         </li> -->
							<li><a href="#tab4" data-toggle="tab" class="step"> <span
									class="number"> 4 </span> <span class="desc"> <i
										class="fa fa-check"></i> 发布
								</span>
							</a></li>
						</ul>
						<div id="bar" class="progress progress-striped" role="progressbar">
							<div class="progress-bar progress-bar-success"
								style="width: 35%;"></div>
						</div>
						<div class="portlet-body">
							<!-- BEGIN FORM -->
							<form action="${ctx }/guideAdmin/guideService/add"
								id=form_sample_3 class="form-horizontal" method="post">
								<input type="hidden" name="id" value="${service.id }" /> <input
									type="hidden" name="serviceNo" value="${service.serviceNo }" />
								<div class="form-body">
									<div class="alert alert-danger display-hide">
										<button class="close" data-close="alert"></button>
										请完善以下信息！
									</div>
									<div class="alert alert-success display-hide">
										<button class="close" data-close="alert"></button>
										通过验证！
									</div>
									<div class="form-group">
										<!-- 编辑页面显示start 地陪编号不可修改-->
										<c:if test="${not empty service.id}">
											<label class="control-label col-md-3">地陪编号 <span
												class="required"> * </span>
											</label>
											<div class="col-md-4">
												<input type="text" name="lineNo" data-required="1"
													class="form-control" readonly="readonly"
													value="${service.serviceNo }" />
											</div>
										</c:if>
									</div>
									<!-- 编辑页面显示end -->
									<div class="form-group">
										<label class="control-label col-md-3">标题 <span
											class="required" aria-required="true"> * </span>
										</label>
										<div class="col-md-4">
											<input type="text" name="title" data-required="1"
												class="form-control" value="${service.title }" />
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">价格 <span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<input type="text" name="price" data-required="1"
												class="form-control" value="${service.price }" /> <input
												type="hidden" name="oldPrice" value="${service.price }" />
											<!-- 修改前先把旧的价格存起来，价格有改动就需要财务审核 -->
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">导服天数<span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<input type="text" name="totalDay" data-required="1"
												class="form-control" value="${service.totalDay }" /> <input
												type="hidden" name="oldTotalDay"
												value="${service.totalDay }" />
											<!-- 修改前先把旧的天数存起来，修改天数时对应把旧的行程删掉 -->
										</div>
									</div>
									<div class="form-group">
										<label class="control-label col-md-3">满员人数 <span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<input type="text" name="num" data-required="1"
												class="form-control" value="${service.num }" />
										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-3 control-label"><span
											style="color: red">*</span>选择服务的省-市：</label>
										<div class="col-lg-2">
											<select title="选择省" class="selectpicker form-control"
												name="serviceProvince" data-live-search="true"
												data-value="${service.serviceProvince}">
												<option value="">请选择</option>
											</select>
										</div>
										<div class="col-lg-2">
											<select title="选择市" class="selectpicker form-control"
												name="serviceCity" data-live-search="true"
												data-value="${service.serviceCity}">
												<option value="">请选择</option>
											</select>
										</div>

									</div>

									<div class="form-group">
										<label class="control-label col-md-3">简介 <span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<textarea class="form-control" name="description"
												id="description" rows="3" placeholder="这里添加描述">${service.description}</textarea>
											<div id="editor2_error"></div>
										</div>
									</div>

									<div class="form-group">
										<label class="control-label col-md-3">排序号 <span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<input type="text" name="sort" data-required="1"
												class="form-control" value="${service.sort }" />
										</div>
									</div>
									<%-- <div class="form-group">
								<label class="control-label col-md-3">背景图 <span
									class="required"> * </span>
								</label>
								<div class="col-xs-2 line-img">

									<input type="hidden" name="imgUrl" value="${guideService.imgUrl }"/> 
									<a class="grouped_elements" rel="group" href="${guideService.imgUrl }">
									<img id="image" alt="" src="${guideService.imgUrl }" class="img-thumbnail"/></a> <span
										class="btn green fileinput-button pading"> <i
										class="fa fa-plus"></i> <span id="load">上传 </span> <input
										class="imgUpload" type="file" name="files[]" multiple />
									</span>

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

								</div>
							</div> --%>

									<div class="form-group">
										<label class="col-lg-3 control-label">线路背景图：</label>
										<div class="col-lg-5">
											<div id="imgUrl">
												<div id="supprogress">
													<input type="hidden" name="imgUrl"
														value="${service.imgUrl }" /> <span class="imageName"></span>
													<div class="progress">
														<div class="progress-bar progress-bar-success"
															role="progressbar" aria-valuenow="40" aria-valuemin="0"
															aria-valuemax="100">
															<span class="sr-only">40% Complete (success)</span>
														</div>
													</div>
												</div>
												<div class="list">
													<img class="img-list" alt="" src="${service.imgUrl }" />
												</div>
												<span class="btn green fileinput-button pading list">
													<i class="fa fa-plus  i-list"></i> <span id="load">上传
												</span> <input class="imgUpload" type="file" name="files[]"
													multiple>
												</span>
											</div>

										</div>
									</div>
									<div class="form-group">
										<label class="col-lg-3 control-label">头像图片：</label>
										<div class="col-lg-5">
											<div id="smallImgUrl">
												<div id="supprogress">
													<input type="hidden" name="smallImgUrl"
														value="${service.smallImgUrl }" /> <span
														class="imageName"></span>
													<div class="progress">
														<div class="progress-bar progress-bar-success"
															role="progressbar" aria-valuenow="40" aria-valuemin="0"
															aria-valuemax="100">
															<span class="sr-only">40% Complete (success)</span>
														</div>
													</div>
												</div>
												<div class="list">
													<img class="img-list" alt="" src="${service.smallImgUrl }" />
												</div>
												<span class="btn green fileinput-button pading list">
													<i class="fa fa-plus  i-list"></i> <span id="load">上传
												</span> <input class="imgUpload" type="file" name="files[]"
													multiple>
												</span>
											</div>

										</div>
									</div>



									<div class="form-group">
										<label class="control-label col-md-3">体验说明 <span
											class="required"> * </span>
										</label>
										<div class="col-md-4">
											<textarea class="form-control textarea" id="buyNotice"
												name="remark" maxlength="1800" rows="8" cols="20"
												data-name="buyNotice">${service.remark }</textarea>
										</div>
									</div>


									<!-- <div class="form-group">
								<label class="control-label col-md-3">状态 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<select class="form-control select2me" name="status">
										<option value="1">编辑中</option>
										<option value="2">上线</option>
										<option value="3">下线</option>
									</select>
								</div>
							</div> -->
									<div class="form-group last">
										<label class="control-label col-md-3">内容 <span
											class="required"> * </span>
										</label>
										<div class="col-md-9">
											<script id="content" type="text/plain" name="content"
												style="width:600px;height:500px;">${service.content }</script>
											<div id="editor2_error"></div>
										</div>
									</div>

								</div>



								<div class="form-actions">
									<div class="row">
										<div class="col-md-offset-3 col-md-9">
											<button type="submit" class="btn green">保存</button>
											<!-- <a href="javascript:;" onClick="javascript:history.back(-1);"
										class="btn default">取消</a> -->
										</div>
									</div>
								</div>
							</form>
							<!-- END FORM-->







						</div>
					</div>
					<!-- END VALIDATION STATES-->
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
			<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
	<script>
		$(function() {

			initImgUpload("#imgUrl");
			initImgUpload("#smallImgUrl");
			initUEeditor();
			handleValidation3();
			initProvinceSelect("serviceProvince","${ctx}/guideAdmin/guideService/province");
		
			initCitySelect("serviceCity", "${ctx}/guideAdmin/guideService/city");

		});

		var initProvinceSelect = function(selectName, url) {
			//线路数据
			var $selectObject = $("select[name='" + selectName + "']");
			$
					.ajax({
						url : url,
						type : "post",
						dataType : "json",
						success : function(data) {
							var options = "";
							if (data.code == "200") {
								var users = data.result;
								//console.log(users);
								$
										.each(
												users,
												function(index, obj) {
													options += "<option value='"+obj.provinceName+"' id='"+obj.provinceId+"'>"
															+ obj.provinceName
															+ "</option>";
												});
							}
							//$selectObject.empty();
							$selectObject.append(options);
							$selectObject.selectpicker('refresh');
							$selectObject.selectpicker('val', $selectObject
									.attr("data-value"))
							//选 中后重新初始化级联市
							$selectObject
									.on(
											"changed.bs.select",
											function(e) {
												var provinceId = $(
														e.currentTarget).find(
														'option:selected')
														.attr("id");

												$("select[name='serviceCity']")
														.attr("data-value", "");
												initCitySelect(
														"serviceCity",
														 "${ctx}/guideAdmin/guideService/city?provinceId="
																+ provinceId);
												//$("input[name='userName']").val(userName);
											})

							//console.log(options);
						},
						error : function(e) {
							//console.log(e);
							comm.errorMsg("请求出错！");
						}

					});

		}
		var initCitySelect = function(selectName, url) {

			var $selectObject = $("select[name='" + selectName + "']");
			$
					.ajax({
						url : url,
						type : "post",
						dataType : "json",
						success : function(data) {
							var options = "";
							if (data.code == "200") {
								var users = data.result;
								//console.log(users);
								$
										.each(
												users,
												function(index, obj) {
													options += "<option value='"+obj.cityName+"' id='"+obj.cityId+"'>"
															+ obj.cityName
															+ "</option>";
												});
							}
							$selectObject.empty();
							$selectObject.append(options);
							$selectObject.selectpicker('refresh');
							$selectObject.selectpicker('val', $selectObject
									.attr("data-value"));
							/* //选 中后重新初始化级联市
							$selectObject.on("changed.bs.select", function(e) {
							var provinceId = $(e.currentTarget).find(
								'option:selected').attr("id"); */
							//	initCitySelect("serviceCity",mlx.ctx+"/admin/guideService/city?provinceId="+provinceId);
							//$("input[name='userName']").val(userName);
							//})
							//console.log(options);
						},
						error : function(e) {
							//console.log(e);
							comm.errorMsg("请求出错！");
						}

					});

		}

		/** 图片上传的控件 **/
		var initImgUpload = function(obj) {
			//图上传
			var $supprogress = $(obj + " #supprogress");
			var $dispalyName = $(obj + " #supprogress>span");
			var $hiddenName = $(obj + " #supprogress>input");
			var $imgSrc = $(obj + " .list img");
			var $barsuccess = $(obj + ' .progress .progress-bar-success');
			var $imgUpload = $(obj + ' .imgUpload');
			var $loadName = $(obj + ' #load');
			//console.log($hiddenName)
			//console.log($dispalyName)
			//console.log($supprogress);
			$supprogress.css('display', "none");
			$imgUpload.on('change', function(e) {
				var files = this.files;
				var fullname = $(this).val();
				$dispalyName.html(fullname
						.substring(fullname.lastIndexOf("\\") + 1));
				$supprogress.css('display', "block");
				$imgSrc.attr("src", "");
				$hiddenName.val("");
			})
			$imgUpload
					.fileupload({

						dataType : 'json',
						url : '${ctx}/upload',
						progressall : function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							$barsuccess.css('width', progress + '%');
							$barsuccess.text(progress + '%');
							//console.log(data);
						},

						done : function(e, data) {
							if (data.result.code == "200") {
								$supprogress.css('display', "none");
								//$("#image").attr("src",	data.result.result[0].filePath);
								$imgSrc.attr("src",
										data.result.result[0].filePath);
								$hiddenName.val(data.result.result[0].filePath);
								$loadName.html("重传");
							} else {
								$supprogress.text(data.result.msg);
							}
							//console.log(data);
							//$supprogress.text("done");
						}
					});
		}

		//自定义 表单验证规则
		jQuery.validator.addMethod("totalDay", function(value, element) {
			var totalDay = /^\+?[1-9]\d*$/;
			return this.optional(element) || (totalDay.test(value));
		}, "天数不能为0");

		//验证框架
		var handleValidation3 = function() {
			var form3 = $('#form_sample_3');
			var error3 = $('.alert-danger', form3);
			var success3 = $('.alert-success', form3);

			form3.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					title : {
						required : true,
						maxlength : 50
					},
					price : {
						required : true,
						number : true,
						maxlength : 10
					},
					totalDay : {
						required : true,
						digits : true,
						maxlength : 3,
						totalDay : true, //调用自定义的验证规则
						range : [ 1, 366 ]
					},
					num : {
						required : true,
						digits : true,
						maxlength : 10
					},
					description : {
						required : true,
						maxlength : 50
					},
					sort : {
						digits : true,
						maxlength : 10
					},
					remark : {
						required : true,
						maxlength : 255
					},
					num : {
						required : true,
						digits : true,
						maxlength : 5
					}
				},

				messages : { // custom messages for radio buttons and checkboxes
					title : {
						required : "不能为空",
						maxlength : "最多输入50个汉字"
					},
					price : {
						required : "不能为空",
						number : "请输入合法数字",
						maxlength : "最多输入10位数"
					},
					totalDay : {
						required : "不能为空",
						digits : "请输入整数",
						maxlength : "最多输入3位数",
						range : "请输入一个介于 {0} 和 {1} 之间的值"
					},
					num : {
						required : "不能为空",
						digits : "请输入整数",
						maxlength : "最多输入10位数"
					},
					description : {
						required : "不能为空",
						maxlength : "最多输入50个汉字"
					},
					sort : {
						digits : "请输入整数",
						maxlength : "最多输入10位数"
					},
					remark : {
						required : "不能为空",
						maxlength : "最多输入255个汉字"
					},
					num : {
						required : "不能为空",
						digits : "请输入整数",
						maxlength : "最多输入5位数"
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
					error3.hide();
					//验证UE编辑器是否为空
					if (UE.getEditor('content').hasContents() == false) {
						comm.infoMsg("内容不能为空", null, 150);
						return;
					}
					success3.show();
					form.submit(); // submit the form
				}

			});

		};

		/** UEeditor 的初始化**/
		var initUEeditor = function() {
			window.UEDITOR_HOME_URL = "${ctx}";
			UE.getEditor('content');
			//UE.getEditor('remark');
		}
	</script>



</body>
</html>