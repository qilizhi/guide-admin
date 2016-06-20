<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的故事</title>
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
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/pages/scripts/upload.js" type="text/javascript"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.all.min.js"></script>
<script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script type="text/javascript" src="${ctx}/static/assets/global/plugins/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
<!-- 验证框架 -->
<script src="${ctx}/static/assets/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
<!-- fancybox -->
<script type="text/javascript" src="${ctx}/static/js/jquery.fancybox-1.3.1.pack.js"></script>
<link href="${ctx}/static/css/fancybox.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN VALIDATION STATES-->
    <div class="portlet light portlet-fit portlet-form ">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject font-dark sbold uppercase">我的故事</span>
            </div>
            
        </div>
        <div class="portlet-body">
            <!-- BEGIN FORM-->
            <form action="${ctx}/guideAdmin/myStory/update" id="form_sample_3" class="form-horizontal" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${myStory.id }"/>
                <div class="form-body">
                    <div class="alert alert-danger display-hide">
                        <button class="close" data-close="alert"></button> You have some form errors. Please check below. </div>
                    <div class="alert alert-success display-hide">
                        <button class="close" data-close="alert"></button> Your form validation is successful! </div>
                        
                   
                    <div class="form-group">
                        <label class="control-label col-md-3">标题
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="title" data-required="1" class="form-control" value="${myStory.title }"/> </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">描述
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                        	<textarea class="form-control textarea" id="buyNotice" name="description" maxlength="1800" rows="8" cols="20" data-name="buyNotice" >${myStory.description }</textarea></div>
                    </div>
                    <div class="form-group">
						<label class="control-label col-md-3">背景图
						 <span class="required"> * </span>
						</label>
						<div class="col-xs-2 line-img">
								<input type="hidden" name="imgUrl" value="${myStory.imgUrl }"/>
								<a class="grouped_elements" rel="group" href="${myStory.imgUrl }">
										<img id="image"  alt="" src="${myStory.imgUrl }" class="img-thumbnail" /></a> <span
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
					</div>
                    <div class="form-group">
                        <label class="control-label col-md-3">推荐理由
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <input type="text" name="recommendInfo" data-required="1" class="form-control" value="${myStory.recommendInfo }" /> </div>
                    </div>
                    <div class="form-group last">
                        <label class="control-label col-md-3">内容
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-9">
                            <script id="editor" type="text/plain" name="content"
								style="width:600px;height:500px;">${myStory.content}</script>
                            <div id="editor2_error"> </div>
                        </div>
                    </div>
                    
                    
                </div>
                 
                <div class="form-actions">
                    <div class="row">
                        <div class="col-md-offset-3 col-md-9">
                            <button type="submit" class="btn green">保存</button>
                            <a href="javascript:;" onClick="javascript:history.back(-1);" class="btn default">取消</a>
                        </div>
                    </div>
                </div>
            </form>
            <!-- END FORM-->
        </div>
        <!-- END VALIDATION STATES-->
		</div>
	</div>
</div>


<script>
		$(function() {
		
			initImgUpload();
			initUEeditor();
			handleValidation3();
			//初始化fancyBox
			$("a.grouped_elements").fancybox();
		
		})
		//上传图片
		var initImgUpload = function() {

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
					})
			$('.imgUpload').fileupload(
					{

						dataType : 'json',
						url : '${ctx}/upload',
						progressall : function(e, data) {
							var progress = parseInt(data.loaded / data.total
									* 100, 10);
							var $progressBar=$('#supprogress .progress .progress-bar-success');
							if(progress==100){
								$progressBar.css('width',
									(progress-1) + '%');
							$progressBar.text(
									(progress-1) + '%');
							}else{
								$progressBar.css('width',
										progress + '%');
								$progressBar.text(
										progress + '%');
							}
						},

						done : function(e, data) {
							if (data.result.code == "200") {
								$("#supprogress").css('display', "none");
								var imgUrl=data.result.result[0].filePath;
								$("input[name='imgUrl']").val(imgUrl);
								$("#image").attr("src",imgUrl);
								$("a.grouped_elements").attr("href",imgUrl);//fancyBox取值
								$("#load").html("重传");
							} else {
								$('.progress .progress-bar-success').text(
										data.result.msg);
							}
							//console.log(data);
							$('.progress .progress-bar-success').text("done");
						}
					});
		};
		

		
		//验证框架
	 var handleValidation3 = function() {
	            var form3 = $('#form_sample_3');
	            var error3 = $('.alert-danger', form3);
	            var success3 = $('.alert-success', form3);

	            form3.validate({
	                errorElement: 'span', //default input error message container
	                errorClass: 'help-block help-block-error', // default input error message class
	                focusInvalid: false, // do not focus the last invalid input
	                ignore: "", // validate all fields including form hidden input
	                rules: {
	                    title: {
	                        required: true,
	                        maxlength:50
	                    }, 
	                    description: {
	                        required: true,
	                        maxlength:50
	                    },
	                    recommendInfo: {
	                        required: true,
	                        maxlength:100
	                    }
	                },

	                messages: { // custom messages for radio buttons and checkboxes
	                	title: {
	                        required: "不能为空",
	                        maxlength:"最多输入50个汉字"
	                    },
	                    description: {
	                        required: "不能为空",
	                        maxlength:"最多输入50个汉字"
	                    },
	                    recommendInfo: {
	                    	required: "不能为空",
	                    	maxlength:"最多输入100个汉字"
	                    }
	                },

	                errorPlacement: function (error, element) { // render error placement for each input type
	                    if (element.parent(".input-group").size() > 0) {
	                        error.insertAfter(element.parent(".input-group"));
	                    } else if (element.attr("data-error-container")) { 
	                        error.appendTo(element.attr("data-error-container"));
	                    } else if (element.parents('.radio-list').size() > 0) { 
	                        error.appendTo(element.parents('.radio-list').attr("data-error-container"));
	                    } else if (element.parents('.radio-inline').size() > 0) { 
	                        error.appendTo(element.parents('.radio-inline').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-list').size() > 0) {
	                        error.appendTo(element.parents('.checkbox-list').attr("data-error-container"));
	                    } else if (element.parents('.checkbox-inline').size() > 0) { 
	                        error.appendTo(element.parents('.checkbox-inline').attr("data-error-container"));
	                    } else {
	                        error.insertAfter(element); // for other inputs, just perform default behavior
	                    }
	                },

	                invalidHandler: function (event, validator) { //display error alert on form submit   
	                    success3.hide();
	                    error3.show();
	                    App.scrollTo(error3, -200);
	                },

	                highlight: function (element) { // hightlight error inputs
	                   $(element)
	                        .closest('.form-group').addClass('has-error'); // set error class to the control group
	                },

	                unhighlight: function (element) { // revert the change done by hightlight
	                    $(element)
	                        .closest('.form-group').removeClass('has-error'); // set error class to the control group
	                },

	                success: function (label) {
	                    label
	                        .closest('.form-group').removeClass('has-error'); // set success class to the control group
	                },

	                submitHandler: function (form) {
	                    error3.hide();
	                  //验证UE编辑器是否为空
	                    if(UE.getEditor('editor').hasContents()==false){
	     	    		   comm.infoMsg("内容不能为空",null,150);
	     	    		   return ;
	     	    	   }
	                    success3.show();
	                    form[0].submit(); // submit the form
	                }

	            });

	             
	    }
	

		/** UEeditor 的初始化**/
		var initUEeditor = function() {
			window.UEDITOR_HOME_URL = "${ctx}";
			UE.getEditor('editor');
		}
	</script>
	
</body>
</html>