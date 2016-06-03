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
<!-- fancybox -->
<link href="${ctx}/static/css/fancybox.css" rel="stylesheet" type="text/css" />
<title>创建线路</title>

</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">线路描述</span>
					</div>

				</div>
				<div class="row">
				 <div class="form-wizard">
                    <div class="form-body">
						<ul class="nav nav-pills nav-justified steps">
	                         <li class="active">
	                             <a href="#tab1" data-toggle="tab" class="step" aria-expanded="true">
	                                 <span class="number"> 1 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 线路 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab2" data-toggle="tab" class="step">
	                                 <span class="number"> 2 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 价格 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab3" data-toggle="tab" class="step active">
	                                 <span class="number"> 3 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 行程 </span>
	                             </a>
	                         </li>
	                         <li>
	                             <a href="#tab4" data-toggle="tab" class="step">
	                                 <span class="number"> 4 </span>
	                                 <span class="desc">
	                                     <i class="fa fa-check"></i> 发布 </span>
	                             </a>
	                         </li>
	                     </ul>
                     <div id="bar" class="progress progress-striped" role="progressbar">
                         <div class="progress-bar progress-bar-success" style="width: 25%;"> </div>
                     </div>
                     <div class="tab-content">
                       <div class="alert alert-danger display-none">
                           <button class="close" data-dismiss="alert"></button> You have some form errors. Please check below. </div>
                       <div class="alert alert-success display-none">
                           <button class="close" data-dismiss="alert"></button> Your form validation is successful! </div>
                       <!-- 线路start -->
                       <div class="tab-pane active" id="tab1">
                           <h3 class="block">请填写线路详情</h3>
                  
                     
				
				</div>
				<div class="portlet-body">
					<!-- BEGIN FORM -->
					<form action="${ctx }/guideAdmin/line/add" id="form_sample_3"
						class="form-horizontal" method="post" enctype="multipart/form-data">
						<input type="hidden" name="id" value="${guideLine.id }" />
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
								<!-- 编辑页面显示start 线路编号不可修改-->
								<c:if test="${not empty guideLine.id}">
									<label class="control-label col-md-3">线路编号</label>
									<div class="col-md-4">
										<input type="text" name="lineNo" class="form-control" readonly="readonly"
											value="${guideLine.lineNo }" />
									</div>
								</c:if>
							</div>
							<!-- 编辑页面显示end -->
							<div class="form-group">
								<label class="control-label col-md-3">线路标题 <span class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="title" data-required="1"
										class="form-control" value="${guideLine.title }"/>
									
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">线路价格 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="price" data-required="1" class="form-control"
										value="${guideLine.price }"/>
										<input type="hidden" name="oldPrice" value="${guideLine.price }" /><!-- 修改前先把旧的价格存起来，价格有改动就需要财务审核 -->
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">线路天数<span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="totalDay" data-required="1" class="form-control" value="${guideLine.totalDay }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">推荐理由 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="recommendInfo" data-required="1"
										class="form-control" value="${guideLine.recommendInfo }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">线路简介 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text" name="description" data-required="1"
										class="form-control" value="${guideLine.description }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">排序号 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text"
										name="sort" data-required="1" class="form-control"
										value="${guideLine.sort }" />
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">满员人数 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<input type="text"
										name="num" data-required="1" class="form-control"
										value="${guideLine.num }"/>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">线路背景图 <span
									class="required"> * </span>
								</label>
								<div class="col-xs-2">
									<input type="hidden" name="imgUrl" value="${guideLine.imgUrl }"/>
									
									<a class="grouped_elements" rel="group" href="${guideLine.imgUrl }">
									<img id="image" alt="" src="${guideLine.imgUrl }" class="img-thumbnail"/></a><span
										class="btn green fileinput-button pading"> <i
										class="fa fa-plus"></i> <span id="load">上传 </span> 
										<input class="imgUpload" type="file" name="files"  />
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
								<label class="control-label col-md-3">小图 <span
									class="required"> * </span>
								</label>
								<div class="col-xs-2">
									<input type="hidden" name="smallImgUrl" value="${guideLine.smallImgUrl }"/>
									
									<a class="grouped_elements" rel="group" href="${guideLine.smallImgUrl }">
									<img id="image" alt="" src="${guideLine.smallImgUrl }" class="img-thumbnail"/></a><span
										class="btn green fileinput-button pading"> <i
										class="fa fa-plus"></i> <span id="load">上传 </span> 
										<input class="imgUpload" type="file" name="files"  />
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
							
							
							
							
							<%-- <div class="form-group">
								<label class="control-label col-md-3">小图 <span
									class="required"> * </span>
								</label>
								<div class="col-xs-2">
									<label class="control-label">Select File</label>
									<input  type="hidden" name="smallImgUrl" value="${guideLine.smallImgUrl}">
									<input id="input-1" type="file" class="file">
								</div>
							</div> 
 --%>

							<div class="form-group">
								<label class="control-label col-md-3">体验说明 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<textarea class="form-control textarea" id="buyNotice"
										name="remark" maxlength="1800" rows="8" cols="20"
										data-name="buyNotice">${guideLine.remark }</textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="control-label col-md-3">线路类型 <span
									class="required"> * </span>
								</label>
								<div class="col-md-4">
									<select class="form-control select2me" name="lineType">
										<option value="1">国内</option>
										<option value="2">出境</option>
									</select>
								</div>
							</div>
							<div class="form-group last">
		                        <label class="control-label col-md-3">内容
		                            <span class="required"> * </span>
		                        </label>
		                        <div class="col-md-9">
		                            <script id="editor" type="text/plain" name="content"
										style="width:600px;height:500px;">${guideLine.content }</script>
		                            <div id="editor2_error"> </div>
		                        </div>
		                    </div>
							
						</div>



						<div class="form-actions">
							<div class="row">
								<div class="col-md-offset-3 col-md-9">
										<button type="submit" class="btn green">保存并下一步</button>
									<!-- <a href="javascript:;" onClick="javascript:history.back(-1);"
										class="btn default">取消</a> -->
								</div>
							</div>
						</div>
					</form>
					<!-- END FORM-->






                     </div>
                     </div>
                     </div>
                     </div>



				</div>
				<!-- END VALIDATION STATES-->
			</div>
		</div>
	</div>
	<!-- 上传 -->
	<script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript" src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<!-- UE编辑器 -->
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
	<script>
	
	$(function() {
		
		initImgUpload();
		initUEeditor();
		handleValidation3();
		//初始化fancyBox
		$("a.grouped_elements").fancybox();
		
	});
	
	
	
	//上传图片
	var initImgUpload = function() {

		$("#supprogress").css('display', "none");
		$('.imgUpload').on('change',function(e) {
					var files = this.files;
					var fullname = $(this).val();
					$("#imageName").html(fullname.substring(fullname.lastIndexOf("\\") + 1));
					$("#supprogress").css('display', "block");
					$("#image").attr("src", "");
					
				})
		$('.imgUpload').fileupload({

					dataType : 'json',
					url : '${ctx}/upload',
					progressall : function(e, data) {
						console.log(e);
						console.log(data);
						var progress = parseInt(data.loaded / data.total * 100, 10);
						
						var $progressBar=$(e.target).parent().next().find(".progress-bar-success");
						//$('#supprogress .progress .progress-bar-success');
						if(progress==100){
							$progressBar.css('width',(progress-1) + '%');
							$progressBar.text((progress-1) + '%');
						}else{
							$progressBar.css('width',progress + '%');
							$progressBar.text(progress + '%');
						}
					},

					done : function(e, data) {
						console.log(e);
						console.log(data);
						var $supprogress = $(e.target).parent().next();
						if (data.result.code == "200") {
							$supprogress.css('display', "none");
							var imgUrl = data.result.result[0].filePath;
							//console.log(imgUrl)
							$supprogress.siblings("input[name='imgUrl']").val(imgUrl);
							//$("input[name='imgUrl']").val(imgUrl);
							//$("#image").attr("src", imgUrl);
							$supprogress.siblings("a").attr("href",imgUrl).find("img").attr("src", imgUrl);
							//$("a.grouped_elements").attr("href",imgUrl);//fancyBox取值
							$(e.target).prev().html("重传");
						} else {
							//$('#supprogress .progress .progress-bar-success').text(data.result.msg);
							$supprogress.find(".progress-bar-success").text(data.result.msg);
						}
						//$('#supprogress .progress .progress-bar-success').text("done");
					}
				});
	}
	

		
		
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
                   price: {
                       required: true,
                       number:true,
                       maxlength:10
                   },
                   totalDay: {
                	   required: true,
                	   digits:true,
                       maxlength:3
                   },
                   recommendInfo: {
                       required: true,
                       maxlength:255
                   },
                   description: {
                       required: true,
                       maxlength:50
                   },
                   sort: {
                       required: true,
                       digits:true,
                       maxlength:10
                   },
                   remark: {
                       required: true,
                       maxlength:255
                   },
                   num:{
                       required: true,
                       digits:true,
                       maxlength:5
                   }
               },

               messages: { // custom messages for radio buttons and checkboxes
               	title: {
                       required: "不能为空",
                       maxlength:"最多输入50个汉字"
                   },
                   price: {
                       required: "不能为空",
                       number:"请输入合法数字",
                       maxlength:"最多输入10位数"
                   },
                   totalDay: {
                	   required: "不能为空",
                	   digits:"请输入整数",
                       maxlength:"最多输入3位数"
                   },
                   recommendInfo: {
                       required: "不能为空",
                       maxlength:"最多输入255个汉字"
                   },
                   description: {
                       required: "不能为空",
                       maxlength:"最多输入50个汉字"
                   },
                   sort: {
                       required: "不能为空",
                       digits:"请输入整数",
                       maxlength:"最多输入10位数"
                   },
                   remark: {
                	   required: "不能为空",
                	   maxlength:"最多输入255个汉字"
                   },
                   num:{
                       required:"不能为空",
                       digits:"请输入整数",
                       maxlength:"最多输入5位数"
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

   }; 
			
		/** UEeditor 的初始化**/
		var initUEeditor = function() {
			window.UEDITOR_HOME_URL = "${ctx}";
			UE.getEditor('editor');
		}
	
	</script>



</body>
</html>