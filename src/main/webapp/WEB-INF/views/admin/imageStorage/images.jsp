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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图库</title>
<style>
#main {
	position: relative;
	display: inline-block;
}

.box {
	padding: 15px 0 0 15px;
	float: left;
	display: inline-block;
	vertical-align: top;
}

.pic {
	padding: 15px;
	border: 1px solid #ccc;
	border-radius: 10px !important;
	box-shadow: 0 0 5px #ccc;
	display: inline-block;
}

.pic img {
	width: 200px;
	height: auto;
	display: inline-block;
}

.quick-add-img, .quick-save-img {
	display: block;
	height: 48px;
	width: auto;
	margin: 0 0 10px;
	border-width: 2px;
	border-style: dashed;
	overflow: hidden;
	position: relative;
	font-size: 0;
	text-align: center;
	cursor: pointer;
	background-clip: padding-box;
	border-color: #d9d9d9;
	background: #fff;
	padding: 0;
}

.quick-add-entry {
	margin: 0;
	padding: 0;
	display: block;
}

.c_tx3_ok, .c_tx3_add {
	height: auto;
	margin-top: 8px;
	display: block;
	color: #8c8c8c;
	text-decoration: none;
	cursor: auto;
}

.icon {
	margin: 4px 5px 0 0;
	width: 24px;
	height: 24px;
	vertical-align: middle;
	display: inline-block;
	background-repeat: no-repeat;
	font-style: normal;
	font-weight: normal;
}
.ok {
	background-image: url(/guide-admin/static/img/icenter-delay.png);
	background-position: -86px 1px;
}

.add {
	background-image: url(/guide-admin/static/img/icon.png);
	background-position: -302px -52px;
}

.icon-txt {
	margin: 4px 0 0;
	font-size: 12px;
	font-family: Microsoft Yahei;
	display: inline-block;
	vertical-align: middle;
	font-style: normal;
	font-weight: normal;
	color: #8c8c8c;
}

.tpl-item {
	margin-bottom: 10px;
	display: inline-block;
	padding: 0 !important;
	font-size: 0;
	margin: 0;
}

.tpl-item ul {
	display: block;
	position: relative;
	margin: 0;
	list-style: none;
	text-align: center;
}

.tpl-item ul li {
	overflow: hidden;
	transition: none;
	position: relative;
	width: 168px;
	height: 125px;
	display: block;
	padding: 0;
	float: left;
	margin: 2px 2px;
}

.tpl-item ul li .item {
	width: 100%;
	height: 100%;
	overflow: hidden;
	cursor: move;
	white-space: nowrap;
	background-color: #D6D6D6;
	margin: 0;
	padding: 0;
	display: block;
	position: relative;
}

.tpl-item ul li .img-remove {
	top: 0;
	right: 0;
	background: rgba(0, 0, 0, .75);
	z-index: 255;
	width: 24px;
	height: 24px;
	position: absolute;
	zoom: 1;
	text-decoration: none;
	color: #5d7895;
	cursor: auto;
}

.tpl-item ul li .img-remove .hide_clip {
	display: block;
	width: 24px;
	height: 24px;
	overflow: hidden;
	line-height: 99px;
	background-image: url(/guide-admin/static/img/icenter-delay.png);
	background-position: -36px -54px;
	font-style: normal;
	font-weight: normal;
}

 .img-load {
	font: initial;
	position: absolute;
	top: 50%;
	left: 50%;
	width: 16px;
	height: 16px;
	font-style: oblique;
	margin: -8px 0 0 -8px;
	background: url(/guide-admin/static/img/load.gif) 0 0 no-repeat;
}

.loading{
margin-left: 190px;
}
.none {
	display: none
}

.tpl-item .upload {
	color: #fff;
	border: 1px solid #50A5DA;
	background: -webkit-gradient(linear, 0% 0, 0% 100%, from(#55BAF1),
		to(#53B2E6));
	float: right;
	height: 22px;
	width: 48px;
	line-height: 22px;
	margin: 4px 5px 0 0;
	text-align: center;
	text-decoration: none;
	cursor: pointer;
	border-radius: 2px;
	text-decoration: none;
	cursor: pointer;
}
</style>
</head>

<body>

	<div class="tpl-item">
		<ul>

		</ul>


	</div>
	<div id="is_ok" class="quick-save-img none">
		<div class="quick-add-entry">
			<a class="c_tx3_ok"> <i class="icon ok"></i><i class="icon-txt">确定</i></a>
		</div>
	</div>
	<div class="quick-add-img">
		<div class="quick-add-entry">
			<a href="javascript:void(0)" class="c_tx3_add"> <i
				class="icon add"></i><i class="icon-txt">添加</i></a>
		</div>
	</div>



	<div id="main">

		 <c:forEach var="item" items="${list}">
			<div class="box">
				<div class="pic">
					<a href="${item.imgUrl}"><img alt="${item.imgUrl}"
						src="${item.imgUrl}"></a>
				</div>
			</div>

		</c:forEach> 

	</div>
	<input class="_uploadFile none" type="file" style="opacity: 0;"
		name="files[]" multiple="multiple" />
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/vendor/jquery.ui.widget.js"></script>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload.js"></script>
	<%-- 	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/jquery-file-upload/js/jquery.fileupload-process.js"></script> --%>
	<script type="text/javascript">
		var inputFileButton = $("._uploadFile");
		var img_ul = $(".tpl-item ul");

		//确定提交
		$(".c_tx3_ok").on("click", function(e, data) {
              
	       loading("show");
			var imgSrcArray = new Array();
			$("ul li img").each(function(index, data) {
				//console.log(data);
				imgSrcArray[index] = $(data).attr("src");
			});
			$.ajax({
				type : "POST",
				url : mlx.ctx + "/admin/imageInfo/save",
				dataType : "json",
				contentType : "application/json",
				data : JSON.stringify(imgSrcArray),
				success : function(data) {
					if (data.code == "200") {
						loading("hide");
						comm.successMsg("提交成功");
						//提交成功。
						setTimeout(function(){window.location.reload();},1000);
						
					} else {
						loading("hide");
						//提交失败。
						comm.errorMsg(data.result);
					}
				}
			}); 

		});
		
		
		//加载中的modal
		var loading=function(action){
			var $modal = $("#basic-modals");
			//func = func || function(e){$modal.modal("hide");};
			$modal.find(".modal-header,.modal-footer").remove();
			$modal.find(".modal-body").html("<i class='img-load'></i><span class='loading'>正在提交中...</span>");
			$modal.modal(action);
		}
		//删除选择的图片
		$("ul").on("click", '.img-remove', function(e, data) {
			//获取上传图片的URL
			$(e.currentTarget).parent().remove();
			//隐藏 确定框
			$('.quick-save-img').toggleClass("none",$(".tpl-item ul li").length<=0);
		})
		//点击上传
		$(".c_tx3_add")
				.on(
						"click",
						function() {
							inputFileButton.click();
							inputFileButton
									.fileupload({
										type : "post",
										dataType : 'json',
										//singleFileUploads:false,//设置多文件上传
										/* sequentialUploads: true, *///是否按顺序上传
										//limitMultiFileUploads:10, //限制上传文件的个数。
										url : mlx.ctx + '/upload',
										//autoUpload:false, 
										change : function(e, data) {
											$
													.each(
															data.files,
															function(index,
																	file) {

																var imageObject = '<li id="'+file.name+'"><div class="item"><img title="'+file.name+'" width="168"></div>'
																		+ ' <i class="img-load"><span id>0</span>%</i> <a class="img-remove" '
																		+ 'href="javascript:void(0)" title="删除图片"> <i class="hide_clip">移除</i> </a> </li>';
																img_ul
																		.append(imageObject);
																//  console.log('Selected file: ' + file.name);
															});
											$('.quick-save-img').toggleClass("none",$(".tpl-item ul li").length<=0);
										},
										progress : function(e, data) {
											//console.log(data);
											var progress = parseInt(data.loaded
													/ data.total * 100, 10);
											$
													.each(
															data.files,
															function(index,
																	file) {
																var progressbar = $("li[id='"
																		+ file.name
																		+ "'] i span");
																if (progress == 100) {
																	progressbar
																			.text(progress - 1);
																} else {
																	progressbar
																			.text(progress);
																}
															});

										},
										done : function(e, data) {
											//上传完成。
											/* alert("upload OK");*/
											var objectRusult = data.result;
											var progress = $(".img-load");
											if (objectRusult.code == "200") {
												progress.css('display', "none");
												var img = $("li[id='"
														+ objectRusult.result[0].fileName
														+ "'] .item img");
												img
														.attr(
																"src",
																objectRusult.result[0].filePath);
											} else {
												$(
														'.progress .progress-bar-success')
														.text(objectRusult.msg);
											}
										}
									});

						});
	</script>
    <!-- 图片展示区 -->
	<script>
		var g_page_no = 2;

		window.onload = function() {

			waterfall('main', 'box');

			//var dataInt={'data':[{'src':'1.jpg'},{'src':'2.jpg'},{'src':'3.jpg'},{'src':'4.jpg'}]};
			 window.onscroll = function() {
				
				if (checkscrollside) {
					$.ajax({
						url : mlx.ctx+"/admin/imageInfo/getImgs",
						dataType : "json",
						type : "get",
						data : {
							"pageNo" : g_page_no
						},
						success : function(data) {
							//alert("122")
							//console.log("df"+data)
							var oParent = document.getElementById('main');// 父级对象
							for (var i = 0; i < data.result.length; i++) {
								var oPin = document.createElement('div'); //添加 元素节点
								oPin.className = 'box'; //添加 类名 name属性
								oParent.appendChild(oPin); //添加 子节点
								var oBox = document.createElement('div');
								oBox.className = 'pic';
								oPin.appendChild(oBox);
								var oImg = document.createElement('img');
								oImg.src = data.result[i].imgUrl;
								oBox.appendChild(oImg);
								// 	'<a href="+'data.result[i].imgUrl'+">'
							}
							console.log(data);
							if (data.result.length > 0) {
								g_page_no++;
								console.log(g_page_no);
							}
							waterfall('main', 'box');
						},
						error : function(e) {
                             //alert(e);
                             //console.log(e);
						}
					});

				};
			} 
		}

		/*
		    parend 父级id
		    pin 元素id
		 */
		function waterfall(parent, pin) {
			var oParent = document.getElementById(parent);// 父级对象
			//console.log(oParent);
			var aPin = getClassObj(oParent, pin);// 获取存储块框pin的数组aPin
			//console.log(aPin);
			var iPinW = aPin[0].offsetWidth;// 一个块框pin的宽
			var num = Math.floor(document.documentElement.clientWidth / iPinW);//每行中能容纳的pin个数【窗口宽度除以一个块框宽度】
			oParent.style.cssText = 'width:' + (iPinW * num)
					+ 'px;margin:0 auto;';//设置父级居中样式：定宽+自动水平外边距
			var pinHArr = [];//用于存储 每列中的所有块框相加的高度。
			for (var i = 0; i < aPin.length; i++) {//遍历数组aPin的每个块框元素
				var pinH = aPin[i].offsetHeight;
				if (i < num) {
					pinHArr[i] = pinH; //第一行中的num个块框pin 先添加进数组pinHArr
				} else {
					var minH = Math.min.apply(null, pinHArr);//数组pinHArr中的最小值minH
					var minHIndex = getminHIndex(pinHArr, minH);
					aPin[i].style.position = 'absolute';//设置绝对位移
					aPin[i].style.top = minH + 'px';
					aPin[i].style.left = aPin[minHIndex].offsetLeft + 'px';
					//数组 最小高元素的高 + 添加上的aPin[i]块框高
					pinHArr[minHIndex] += aPin[i].offsetHeight;//更新添加了块框后的列高
				}
			}
			var maxH = Math.max.apply(null, pinHArr);//数组pinHArr中的最大值maxH
			$("div.page-content").height(maxH+100);//改变页面高度 ,加100 是因为加了上传的控件
		}

		/****
		 *通过父级和子元素的class类 获取该同类子元素的数组
		 */
		function getClassObj(parent, className) {
			var obj = parent.getElementsByTagName('*');//获取 父级的所有子集
			var pinS = [];//创建一个数组 用于收集子元素
			for (var i = 0; i < obj.length; i++) {//遍历子元素、判断类别、压入数组
				if (obj[i].className == className) {
					pinS.push(obj[i]);
				}
			}
			;
			return pinS;
		}
		/****
		 *获取 pin高度 最小值的索引index
		 */
		function getminHIndex(arr, minH) {
			for ( var i in arr) {
				if (arr[i] == minH) {
					return i;
				}
			}
		}

		function checkscrollside() {
			var oParent = document.getElementById('main');
			var aPin = getClassObj(oParent, 'box');
			var lastPinH = aPin[aPin.length - 1].offsetTop
					+ Math.floor(aPin[aPin.length - 1].offsetHeight / 2);//创建【触发添加块框函数waterfall()】的高度：最后一个块框的距离网页顶部+自身高的一半(实现未滚到底就开始加载)
			var scrollTop = document.documentElement.scrollTop
					|| document.body.scrollTop;//注意解决兼容性
			var documentH = document.documentElement.clientHeight;//页面高度
			return (lastPinH < scrollTop + documentH) ? true : false;//到达指定高度后 返回true，触发waterfall()函数
		}
	</script>






</body>
</html>