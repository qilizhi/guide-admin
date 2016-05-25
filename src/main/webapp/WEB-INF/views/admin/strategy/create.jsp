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
	<h4 class="modal-title" id="exampleModalLabel">${operaTitle}：</h4>
	<form  id="inputForm" action="${ctx}/admin/guideStrategy/saveOrUpdate" method="post"
		 class="form-horizontal mlx-form">
		<input type="hidden" id="id" name="id" value="${guideStrategy.id}" />
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">标题：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control" name="title" id="title"
					value="${guideStrategy.title}" placeholder="这里输入标题" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">描述：</label>
			<div class="col-lg-5">
				<textarea class="form-control" name="description" id="description"
					rows="3" placeholder="这里添加描述">${guideStrategy.description}</textarea>
				<label for="form_control_1"></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">背景图片：</label>

			<div class="col-lg-5">
				<div class="col-lg-7" id="supprogress">
					<input type="hidden" name="imgUrl" value="${guideStrategy.imgUrl }"/> <span id="imageName"></span>
					<div class="progress">
						<div class="progress-bar progress-bar-success" role="progressbar"
							aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
							<span class="sr-only">40% Complete (success)</span>
						</div>
					</div>
				</div>
				<div class="col-lg-2">
					<img id="image" name="imgUrl" alt="" src="${guideStrategy.imgUrl }"> <span
						class="btn green fileinput-button pading"> <i
						class="fa fa-plus"></i> <span id="load">上传 </span> <input
						class="imgUpload" type="file" name="files[]" multiple>
					</span>
				</div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">推荐理由：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="recommendInfo"
					id="recommendInfo" value="${guideStrategy.recommendInfo}" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">关联的线路编号：</label>
			<div class="col-lg-5">
				<%-- <input type="text" class="form-control " name="relatLineNo"
					id="relatLineNo" value="${guideStrategy.relatLineNo}" /> --%>
					<select class="selectpicker" name="relatLineNo" data-live-search="true" data-value="${guideStrategy.relatLineNo}">
					<option value="">请选择</option>
				</select>
			</div>
		</div>
		<%-- 	<div class="form-group">
			<label class="col-lg-3 control-label">用户编号：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="userNo" id="userNo"
					value="${guideStrategy.userNo}" />
			</div>
		</div> --%>
		<div class="form-group">
			<label class="col-lg-3 control-label">用户名称：</label>
			<div class="col-lg-5">
					<input type="hidden" name="userName" value="${guideStrategy.userName}" /> 
				<select class="selectpicker" name="userNo" data-live-search="true" data-value="${guideStrategy.userNo}">
				<option value="" >请选择</option>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">攻略内容文本：</label>
			<div class="col-lg-5">
				<%-- 	<input type="text" class="form-control " name="content" id="content"
					value="${guideStrategy.content}" /> --%>
				<script id="editor" type="text/plain" name="content"
					style="width:600px;height:500px;">${guideStrategy.content}</script>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">排序号：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control" name="sort" id="sort"
					value="${guideStrategy.sort}" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-9 col-lg-offset-3">
				<button id="submit_btn" type="submit" data-loading-text="提交中..."
					autocomplete="off" class="btn btn-primary" >提交</button>  <input
					id="cancel_btn" class="btn btn-primary " type="button" value="返回"
					onclick="history.back()" />
			</div>

		</div>

	</form>
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
			initImgUpload();
			initUEeditor();
			initLineSelect("relatLineNo");
			initGuideSelect("userNo");
			handleValidation3();
		})
		/**下拉框的初始化**/
		var initGuideSelect = function(selectName) {
			var $selectObject=$("select[name='"+selectName+"']");
			//导游 用户数据
			$.ajax({
				url:mlx.ctx+"/admin/guideStrategy/guide/list",
				type:"get",
				dataType:"json",
				success:function(data){
					var options="";
					if(data.code=="200"){
					var users=data.result;
					//console.log(users);
					$.each(users,function(index,obj){
						options+="<option value='"+obj.userNo+"'>"+obj.realName+"</option>";
					});
					}
					
				
					//$selectObject.empty();
					$selectObject.append(options);
				//	$('.selectpicker').selectpicker('render');
					$selectObject.selectpicker('refresh');
					//加载初始化值 
					$selectObject.selectpicker('val',$selectObject.attr("data-value"))
					$selectObject.on("changed.bs.select",function(e){
						var userName=$(e.currentTarget).find('option:selected').text();
						$("input[name='userName']").val(userName);
					})
					//console.log(options);
				},error:function(e){
					//console.log(e);
					comm.errorMsg("请求出错！");
				}
				
			});}
			var initLineSelect = function(selectName) {
			//线路数据
			var $selectObject= 	$("select[name='"+selectName+"']");
			$.ajax({
				url:mlx.ctx+"/admin/guideLine/listAll",
				type:"post",
				dataType:"json",
				success:function(data){
					var options="";
					if(data.code=="200"){
					var users=data.result;
					//console.log(users);
					$.each(users,function(index,obj){
						options+="<option value='"+obj.lineNo+"'>"+obj.title+"</option>";
					});
					}
					//$selectObject.empty();
					$selectObject.append(options);
					$selectObject.selectpicker('refresh');
					$selectObject.selectpicker('val',$selectObject.attr("data-value"))
					//console.log(options);
				},error:function(e){
					//console.log(e);
					comm.errorMsg("请求出错！");
				}
				
			});
			
			
		}

		/** UEeditor 的初始化**/
		var initUEeditor = function() {
			window.UEDITOR_HOME_URL = "${ctx}";
			UE.getEditor('editor');
		}

		/** 图片上传的控件 **/
		var initImgUpload = function() {
			//图上传
			$("#supprogress").css('display', "none");
			$('.imgUpload').on(
					'change',
					function(e) {
						var files = this.files;
						var fullname = $(this).val();
						$("#imageName")
								.html(
										fullname.substring(fullname
												.lastIndexOf("\\") + 1));
						$("#supprogress").css('display', "block");
						$("#image").attr("src", "");
						$("input[name='imgUrl']").val("");
					})
			$('.imgUpload').fileupload(
					{

						dataType : 'json',
						url : '${ctx}/upload',
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
								$("input[name='imgUrl']").val(
										data.result.result[0].filePath);
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
		
	//表单校验。
	
	
		var handleValidation3 = function() {
			var form3 = $('#inputForm');
			var error3 = $('.alert-danger', form3);
			var success3 = $('.alert-success', form3);
            form3.submit(function(e){            	
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
					description : {
						required : true
					},
					recommendInfo : {
						required : true
					},
					sort : {
						required : true,
						digits : true,
						maxlength : 10
					},
					userName : {
						required : true
					},
					remark : {
						required : true
					},
					content : {
						required : true
					}
				},

				messages : { // custom messages for radio buttons and checkboxes
					title : {
						required : "不能为空",
					},
				
					imgUrl : {
						required : "不能为空",
					},
					description : {
						required : "不能为空",
					},
					recommendInfo : {
						required : "不能为空",
					},
					sort : {
						required : "不能为空",
						digits : "请输入整数",
						maxlength : "最多输入10位数"
					},
					userName : {
						required : "不能为空"
					},
					remark : {
						required : "不能为空"
					},
					content : {
						required : "攻略内容不能为空"
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
					if (UE.getEditor('editor').hasContents() == false) {
						comm.infoMsg("内容不能为空", null, 150);
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