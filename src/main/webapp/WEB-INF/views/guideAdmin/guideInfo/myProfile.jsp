<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本资料</title>
<style>
.img-thumbnail {
	with: 200px;
	height: 200px;
}
</style>
<link href="${ctx}/static/css/fancybox.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/jquery.fancybox-1.3.1.pack.js"></script>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN VALIDATION STATES-->
			<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">基本资料</span>
					</div>

				</div>
				<div class="portlet-body">
					<div class="col-md-12 col-sm-12">
						<div class="portlet light bordered">
							<div class="portlet-title">
								<div class="caption">
									<span class="caption-subject font-blue bold uppercase">我的资料</span>
								</div>
								<div class="actions">
									<div class="btn-group">
										<!-- <a href="javascript:;" onClick="javascript:history.back(-1);">返回</a> -->

									</div>
								</div>
							</div>
							<div class="portlet-body">
								<div class="col-md-12 col-sm-12">
									<div class="col-md-6 col-sm-6">
										<dl class="dl-horizontal">
											<dt>真实姓名:</dt>
											<dd>${guide.realName }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>级别:</dt>
											<dd>${guide.guideLevel }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>年纪:</dt>
											<dd>${guide.age}后</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>签名:</dt>
											<dd>${guide.sign }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>标签:</dt>
											<dd>${guide.tag }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>工作年限:</dt>
											<dd>${guide.workYear }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>简介:</dt>
											<dd>${guide.intro }</dd>
										</dl>


									</div>
									<div class="col-md-6 col-sm-6">

										<dl class="dl-horizontal">
											<dt>导游证号:</dt>
											<dd>${guide.guideCardNo }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>身份证号:</dt>
											<dd>${guide.idCard }</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>创建时间:</dt>
											<dd>
												<fmt:formatDate value="${guide.createTime }"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>更新时间:</dt>
											<dd>
												<fmt:formatDate value="${guide.updateTime }"
													pattern="yyyy-MM-dd HH:mm:ss" />
											</dd>
										</dl>

										<dl class="dl-horizontal">
											<dt>审核状态:</dt>
											<%-- <c:if test="${guide.auditStatus==1}">
												<dd>待审核</dd>
											</c:if>
											<c:if test="${guide.auditStatus==2}">
												<dd>审核通过</dd>
											</c:if>
											<c:if test="${guide.auditStatus==3}">
												<dd>审核未通过</dd>
											</c:if> --%>
											<dd>${EAuditStatus[guide.auditStatus]}</dd>
										</dl>
										<dl class="dl-horizontal">
											<dt>导游状态:</dt>
											<dd>${EUserStatus[guide.status]}</dd>
										</dl>
									</div>
								</div>

								<%-- <div class="col-md-12 col-sm-12">
									<div class="col-md-6 col-sm-6">
										<dl class="dl-horizontal">
											<dt>背景图:</dt>
											<dd>
												<a href="${guide.bgImgUrl }"><img alt="背景图" src="${guide.bgImgUrl }" class="img-thumbnail" /></a>
											</dd>
										</dl>
									</div>
									<div class="col-md-6 col-sm-6"></div>
								</div> --%>

								<div class="col-md-12 col-sm-12 ">
									<div class="col-md-4 col-sm-4">
										<dl class="dl-horizontal">
											<dt>身份证正面:</dt>
											<dd>
											<a class="grouped_elements" rel="group" href="${guide.idCardFrontPic }">
											<img src="${guide.idCardFrontPic }" alt=""  class="img-thumbnail"></a>
											</dd>
										</dl>
									</div>
									<div class="col-md-4 col-sm-4">
										<dl class="dl-horizontal">
											<dt>身份证反面:</dt>
											<dd>
											<a class="grouped_elements" rel="group" href="${guide.idCardSidePic }">
											<img  data-show="true" src="${guide.idCardSidePic }" alt=""  class="img-thumbnail"></a>
											</dd>
										</dl>
									</div>
									<div class="col-md-4 col-sm-4">
										<dl class="dl-horizontal">
											<dt>背景图:</dt>
											<dd>
											<a class="grouped_elements" rel="group" href="${guide.bgImgUrl }">
											<img src="${guide.bgImgUrl }" alt=""  class="img-thumbnail"></a>
											</dd>
										</dl>
									</div>
								</div>

								<div class="scroller-footer">
									<div class="btn-arrow-link pull-right">
										<!-- <a href="javascript:;" onClick="javascript:history.back(-1);">返回</a> -->
									</div>
								</div>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<script type="text/javascript">
	$(function(){
		//初始化fancybox
		$("a.grouped_elements").fancybox({
		    'transitionIn'  : 'fade',  //设置动画效果. 可以设置为 'elastic', 'fade' 或 'none'
		    'transitionOut'  : 'fade',  //同上
		    'titlePosition'  : 'over',  //设置标题显示的位置.可以设置成 'outside', 'inside' 或 'over'
		    'cyclic': true,
		    'titleFormat'  : function(title, currentArray, currentIndex, currentOpts) { //可以自定义标题的格式
		     	return '<span id="fancybox-title-over">Image ' + (currentIndex + 1) + ' / ' + currentArray.length + (title.length ? ' &nbsp; ' + title : '') + '</span>';
		    }
		});
	});
	
	
	
	</script>
</body>
</html>