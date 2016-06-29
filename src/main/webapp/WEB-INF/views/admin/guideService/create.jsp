<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="${ctx}/static/assets/global/plugins/bootstrap-select/css/bootstrap-select.min.css">
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

.green {
	border-color: black;
	background-color: green;
	width: 20% ；;
	background-color: green;
}

.pading {
	padding: 0;
}
</style>
<title>${operaTitle}</title>

<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />

</head>
<body>
	<!-- 	<h3>第一步</h3> -->
	<div class="portlet light portlet-fit portlet-form ">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-settings font-dark"></i> <span
					class="caption-subject font-dark sbold uppercase">${operaTitle}：</span>
			</div>

		</div>
		<div class="portlet-body">
			<form id="inputForm" action="${ctx}/admin/guideService/saveOrUpdate"
				method="post" class="form-horizontal mlx-form">
				<input type="hidden" id="id" name="id" value="${guideS.id}" />
				<c:if test="${guideS.id !=null }">
					<div class="form-group form-md-line-input">
						<label class="col-lg-3 control-label">导服编号：</label>
						<div class="col-lg-5">
							<input type="text" class="form-control" name="serviceNo"
								id="title" value="${guideS.serviceNo}" placeholder="这里输入标题"
								readonly="readonly" />
							<div class="form-control-focus"></div>
						</div>
					</div>
				</c:if>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>标题：</label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="title" id="title"
							value="${guideS.title}" placeholder="这里输入标题" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>描述：</label>
					<div class="col-lg-5">
						<textarea class="form-control" name="description" id="description"
							rows="3" placeholder="这里添加描述">${guideS.description}</textarea>
						<label for="form_control_1"></label>
					</div>
				</div>


				<div class="form-group">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>背景图片：</label>
					<div class="col-lg-5">
						<div id="imgUrl">
							<div id="supprogress">
								<input type="hidden" name="imgUrl" value="${guideS.imgUrl }" />
								<span class="imageName"></span>
								<div class="progress">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</div>
							</div>
							<div class="list">
								<img class="img-list" alt="" src="${guideS.imgUrl }">
							</div>
							<span class="btn green fileinput-button pading list"> <i
								class="fa fa-plus  i-list"></i> <span id="load">上传 </span> <input
								class="imgUpload" type="file" name="files[]" multiple>
							</span>
						</div>

					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>头像图片：</label>
					<div class="col-lg-5">
						<div id="smallImgUrl">
							<div id="supprogress">
								<input type="hidden" name="smallImgUrl"
									value="${guideS.smallImgUrl }" /> <span class="imageName"></span>
								<div class="progress">
									<div class="progress-bar progress-bar-success"
										role="progressbar" aria-valuenow="40" aria-valuemin="0"
										aria-valuemax="100">
										<span class="sr-only">40% Complete (success)</span>
									</div>
								</div>
							</div>
							<div class="list">
								<img class="img-list" alt="" src="${guideS.smallImgUrl }">
							</div>
							<span class="btn green fileinput-button pading list"> <i
								class="fa fa-plus  i-list"></i> <span id="load">上传 </span> <input
								class="imgUpload" type="file" name="files[]" multiple>
							</span>
						</div>

					</div>
				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>价格：</label>
					<div class="col-lg-5">
						<input type="text" class="form-control " name="price"
							value="${guideS.price}" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>导服天数：</label>
					<div class="col-lg-5">
						<input type="text" class="form-control " name="totalDay"
							value="${guideS.totalDay}" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>满员人数：</label>
					<div class="col-lg-5">
						<input type="text" class="form-control " name="num"
							value="${guideS.num}" />
						<div class="form-control-focus"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>选择导游：</label>
					<div class="col-lg-5">
						<input type="hidden" name="userName" value="${guideS.userName}" />
						<select title="根据名字及导游证号搜索" class="selectpicker form-control"
							name="userNo" data-live-search="true"
							data-value="${guideS.userNo}">
							<option value="">请选择</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>选择服务的省-市：</label>
					<div class="col-lg-2">
						<select title="选择省" class="selectpicker form-control"
							name="serviceProvince" data-live-search="true"
							data-value="${guideS.serviceProvince}">
							<option value="">请选择</option>
						</select>
					</div>
					<div class="col-lg-2">
						<select title="选择市" class="selectpicker form-control"
							name="serviceCity" data-live-search="true"
							data-value="${guideS.serviceCity}">
							<option value="">请选择</option>
						</select>
					</div>

				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>内容文本：</label>
					<div class="col-lg-5">
						<%-- 	<input type="text" class="form-control " name="content" id="content"
					value="${guideS.content}" /> --%>
						<script id="content" type="text/plain" name="content"
							style="width: 600px; height: 500px;">${guideS.content}</script>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label"><span
						style="color: red">*</span>体验说明：</label>
					<div class="col-lg-5">
						<script id="remark" type="text/plain" name="remark"
							style="width: 600px; height: 500px;">${guideS.remark}</script>
						<%-- <input type="text" class="form-control " name="remark"
							id="recommendInfo" value="${guideS.remark}" />
						<div class="form-control-focus"></div> --%>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">排序号：</label>
					<div class="col-lg-5">
						<input type="text" class="form-control" name="sort" id="sort"
							value="${guideS.sort}" />
					</div>
				</div>
				<div class="form-group">
					<div class="col-lg-9 col-lg-offset-3">
						<button id="submit_btn" type="submit" data-loading-text="提交中..."
							autocomplete="off" class="btn btn-primary">保存</button>
						<input id="cancel_btn" class="btn btn-primary " type="button"
							value="返回" onclick="history.back()" />
					</div>

				</div>

			</form>
		</div>
	</div>
	<script type="text/javascript" charset="utf-8"
		src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.config.js"></script>
	<script type="text/javascript" charset="utf-8"
		src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.all.min.js">
		
	</script>
	<!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
	<!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
	<script type="text/javascript" charset="utf-8"
		src="${ctx }/static/assets/global/plugins/UEeditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>

	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<script type="text/javascript">
		$(function() {
			//加载用户数据并放到页面上去
			initImgUpload("#imgUrl");
			initImgUpload("#smallImgUrl");
			initUEeditor();
			//initLineSelect("relatLineNo");
			initGuideSelect("userNo");
			initProvinceSelect("serviceProvince", mlx.ctx
					+ "/admin/guideService/province");
			initCitySelect("serviceCity", mlx.ctx + "/admin/guideService/city");
			handleValidation3();
		})
		/**下拉框的初始化**/
		var initGuideSelect = function(selectName) {
			var $selectObject = $("select[name='" + selectName + "']");
			//导游 用户数据
			$.ajax({
				url : mlx.ctx + "/admin/guideService/guide/list",
				type : "get",
				dataType : "json",
				success : function(data) {
					var options = "";
					if (data.code == "200") {
						var users = data.result;
						//console.log(users);
						$.each(users, function(index, obj) {
							options += "<option value='"+obj.userNo+"'>"
									+ obj.realName + " " + obj.guideCardNo
									+ "</option>";
						});
					}

					//$selectObject.empty();
					$selectObject.append(options);
					//	$('.selectpicker').selectpicker('render');
					$selectObject.selectpicker('refresh');
					//加载初始化值 
					$selectObject.selectpicker('val', $selectObject
							.attr("data-value"))
					$selectObject.on("changed.bs.select", function(e) {
						var userName = $(e.currentTarget).find(
								'option:selected').text();
						$("input[name='userName']").val(userName);
					})
					//console.log(options);
				},
				error : function(e) {
					//console.log(e);
					comm.errorMsg("请求出错！");
				}

			});
		}
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
												.attr("data-value","");
												initCitySelect(
														"serviceCity",
														mlx.ctx
																+ "/admin/guideService/city?provinceId="
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

		/** UEeditor 的初始化**/
		var initUEeditor = function() {
			window.UEDITOR_HOME_URL = "${ctx}";
			UE.getEditor('content');
			UE.getEditor('remark');
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

		//表单校验。
		var handleValidation3 = function() {
			var form3 = $('#inputForm');
			var error3 = $('.alert-danger', form3);
			var success3 = $('.alert-success', form3);
			form3.submit(function(e) {
				e.preventDefault();
			});

			form3.validate({
				errorElement : 'span', //default input error message container
				errorClass : 'help-block help-block-error', // default input error message class
				focusInvalid : false, // do not focus the last invalid input
				ignore : "", // validate all fields including form hidden input
				rules : {
					title : {
						required : true
					},

					imgUrl : {
						required : true
					},
					smallImgUrl : {
						required : true
					},
					description : {
						required : true
					},
					recommendInfo : {
						required : true
					},
					sort : {
						digits : true,
						maxlength : 10
					},
					price : {
						required : true,
						number : true,
						maxlength : 10
					},
					num : {
						required : true,
						digits : true,
						maxlength : 10
					},
					userName : {
						required : true
					},
					totalDay : {
						required : true,
						digits : true,
						maxlength : 3,
						range : [ 1, 366 ]
					}
				},

				messages : { // custom messages for radio buttons and checkboxes
					title : {
						required : "不能为空",
					},

					imgUrl : {
						required : "不能为空",
					},
					smallImgUrl : {
						required : "不能为空",
					},
					description : {
						required : "不能为空",
					},
					recommendInfo : {
						required : "不能为空",
					},
					sort : {
						digits : "请输入正整数",
						maxlength : "最多输入10位数"
					},
					price : {
						required : "不能为空",
						munber : "请输入正整数",
						maxlength : "最多输入10位数"
					},
					num : {
						required : "不能为空",
						digits : "请输入正整数",
						maxlength : "最多输入10位数"
					},
					userName : {
						required : "不能为空"
					},
					totalDay : {
						required : "不能为空",
						digits : "请输入整数",
						maxlength : "最多输入3位数",
						range : "请输入一个介于 {0} 和 {1} 之间的值"
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
					//验证UE编辑器是否为空
					if (UE.getEditor('remark').hasContents() == false) {
						comm.infoMsg("体验说明不能为空", null, 150);
						return;
					}
					success3.show();
					form.submit(); // submit the form
				}

			});

		};
	</script>
</body>
</html>