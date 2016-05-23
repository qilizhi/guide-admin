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
	<form  id="inputForm" action="${ctx}/admin/guideStrategy/save" method="post"
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
					value="${guideStrategy.description}" rows="3" placeholder="这里添加描述"></textarea>
				<label for="form_control_1"></label>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">背景图片：</label>

			<div class="col-lg-5">
				<div class="col-lg-7" id="supprogress">
					<input type="hidden" name="imgUrl" /> <span id="imageName"></span>
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
					<select class="selectpicker" name="relatLineNo" data-live-search="true">
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
				<%-- 	<input type="text" class="form-control " name="userName"
					id="userName" value="${guideStrategy.userName}" /> --%>
				<select class="selectpicker" name="userNo" data-live-search="true">

				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">攻略内容文本：</label>
			<div class="col-lg-5">
				<%-- 	<input type="text" class="form-control " name="content" id="content"
					value="${guideStrategy.content}" /> --%>
				<script id="editor" type="text/plain" name="content"
					style="width:600px;height:500px;"></script>
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
			initSelect();
		})
		/**下拉框的初始化**/
		var initSelect = function() {
			//用户数据
			$.ajax({
				url:mlx.ctx+"/admin/sysUser/listAll",
				type:"post",
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
					
				
					$("select[name='userNo']").empty();
					$("select[name='userNo']").append(options);
				//	$('.selectpicker').selectpicker('render');
					$("select[name='userNo']").selectpicker('refresh');

					//console.log(options);
				},error:function(e){
					//console.log(e);
					comm.errorMsg("请求出错！");
				}
				
			});
			//线路数据
			
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
					$("select[name='relatLineNo']").empty();
					$("select[name='relatLineNo']").append(options);
					$("select[name='relatLineNo']").selectpicker('refresh');
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
	
		
	</script>
</body>
</html>