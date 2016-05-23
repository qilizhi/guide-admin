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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建日志</title>
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.iframe-transport.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js" type="text/javascript"></script>
<script src="${ctx}/static/assets/pages/scripts/upload.js" type="text/javascript"></script>
</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">日志描述</span>
					</div>

				</div>
				<div class="portlet-body">
					<!-- BEGIN FORM-->
					<form action="${ctx }/guideAdmin/log/add" id="form_sample_3"
						class="form-horizontal" method="post"
						enctype="multipart/form-data">
						<input type="hidden" name="id" value="${guideLog.id }" />
						<div class="form-body">
							<div class="alert alert-danger display-hide">
								<button class="close" data-close="alert"></button>
								You have some form errors. Please check below.
							</div>
							<div class="alert alert-success display-hide">
								<button class="close" data-close="alert"></button>
								Your form validation is successful!
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">地址 <span
									class="required"> </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="address" data-required="1"
										class="form-control" value="${guideLog.address }" />
								</div>
							</div>
							
							<div class="form-group">
									<label class="control-label col-md-3">图片 <span
										class="required"> * </span>
									</label>
									<div class="col-xs-2 line-img">
										<div class="thumbnail relative">
											<div
												style="height: 150px; border: 1px dashed #ddd; line-height: 150px; text-align: center">
												<img alt="" src="" id="imgPre" width="100%" height="100%"
													class="line-img-content" />
													<input type="hidden" name="imgUrl" value="${guideLogImg.imgUrl}">
											</div>
											<p class="margin-top-10">
												<input type="file" name="file" id="file" data-required="1" class="form-control" />
											</p>
										</div>
										<div class="progress">
			                                <div class="progress-bar progress-bar-info" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="100" style="width: 20%">
			                                    <span class="sr-only"> 10% Complete </span>
			                                </div>
			                            </div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">描述 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<input type="text" name="description" data-required="1"
											class="form-control required" value="" />
									</div>
								</div>
							
							
							
							<div class="form-group last">
								<label class="control-label col-md-3">内容 <span
									class="required"> * </span>
								</label>
								<div class="col-md-9">
									<textarea class="ckeditor form-control required" name="content"
										rows="6" data-error-container="#editor2_error">${guideLog.content }</textarea>
									<div id="editor2_error"></div>
								</div>
							</div>
						</div>
						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
									<button type="submit" class="btn green" >保存</button>
									<a href="javascript:;" onClick="javascript:history.back(-1);"
										class="btn default">取消</a>
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
$(function(){
	$("[type=file]").customUpload({ 
        limit:function(data){return false;},//判断是否阻止上传,如果返回值是true,则阻止
        submit:function(data){},//提交文件时触发
        progressSigle:function(progress){
        	$("[role=progressbar]").css("width",progress + "%");
        },//单文件上传进度
        result:function(index,file,count){
        	$("#imgPre").attr("src",file.filePath);
        	$("[name=imgUrl]").val(file.filePath);
        },//上传完成回调
        sequentialUploads: true,/*顺序上传*/
        singleFileUploads: true,/*单个单个上传*/
        maxNumberOfFiles:1,//限制最大上传文件数量
        maxFileSize:1048576,//限制最大上传文件大小只允许单文件最大默认10MB。
        limitedFileType:1,//0表示图片和文件都可以上传,1表示只能上传图片,2表示只能上传非图片的符合条件的文件
        auto:true});
});

</script>
</body>
</html>