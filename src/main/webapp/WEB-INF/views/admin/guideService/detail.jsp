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
<title>地陪详情</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div class="portlet light portlet-fit portlet-form ">
		<div class="portlet-title">
			<div class="caption">
				<i class="icon-settings font-dark"></i> <span
					class="caption-subject font-dark sbold uppercase">地陪详情</span>
			</div>

		</div>
		<div class="portlet-body">
			<form action="${ctx}/admin/guideService/update" id="inputForm"
				method="post" class="form-horizontal mlx-form">
				<%-- <input type="hidden" id="id" name="id" value="${guideService.id}" /> --%>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">地陪编号：</label>
					<div class="col-lg-5">
						<label class="control-label"><span>${guideService.serviceNo}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">标题：</label>
					<div class="col-lg-5">
						<label class="control-label"><span id="title">${guideService.title}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">行程天数：</label>
					<div class="col-lg-5">
						<label class="control-label"><span id="title">${guideService.totalDay}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">描述：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.description}</span></label>
					</div>

				</div>
				<%-- 		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">内容：</label>
			<div class="col-lg-5">
				<label class="control-label"><span>${guideService.content}</span> </label>
			</div>

		</div> --%>

				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">背景图片：</label>
					<div class="col-lg-5">
						<label class="control-label"> <img
							src="${guideService.imgUrl}" /></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">头像图片：</label>
					<div class="col-lg-5">
						<label class="control-label"> <img
							src="${guideService.smallImgUrl}" title="详情头像图" /></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">用户编号：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.userNo}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">用户名称：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.userName}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">价格:</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.price}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">满员人数:</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.num}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">审核状态：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${EAuditStatus[guideService.auditStatus]}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">审核说明：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.auditRemark }</span></label>
					</div>
				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">创建时间：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span> <fmt:formatDate
									value="${guideService.createTime }"
									pattern="yyyy-MM-dd HH:mm:ss" />
						</span>
						</label>
					</div>
				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">更新时间：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span> <fmt:formatDate
									value="${guideService.updateTime}"
									pattern="yyyy-MM-dd HH:mm:ss" />
						</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">状态：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${Status[guideService.status]}</span></label>
					</div>
				</div>
				<div class="form-group form-md-line-input">
					<label class="col-lg-3 control-label">排序号：</label>
					<div class="col-lg-5">
						<label class="control-label"> <span>${guideService.sort}</span></label>
					</div>
				</div>


				<div class="form-group form-md-line-input">
					<label class="col-lg-2 control-label"></label>
					<div class="col-lg-8">

						<!-- <div class="portlet light bordered"> -->
						<div class="portlet box green">
							<div class="portlet-title">
								<div class="caption font-red-intense">
									<i class="icon-speech font-red-intense"></i> <span
										class="caption-subject bold uppercase">内容</span> <span
										class="caption-helper"></span>
								</div>
								<div class="tools">
									<a href="" class="collapse" title="展开/关闭"> </a>
									<!--  <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a> -->
									<!--  <a href="" class="reload" data-original-title="" title=""> </a>
                                        <a href="" class="fullscreen" data-original-title="" title=""> </a>
                                        <a href="" class="remove" data-original-title="" title=""> </a> -->
								</div>
							</div>
							<div class="portlet-body" >
								<div id="context2" data-toggle="context"
									data-target="#context-menu">${guideService.content}</div>

							</div>
						<!-- 	<div class="portlet-title">
									<div class="caption font-red-intense">
									<i class="icon-speech font-red-intense"></i> <span
										class="caption-subject bold uppercase">内容</span> <span
										class="caption-helper"></span>
								</div>
								<div class="tools">
									<a href="" class="collapse" title="展开/关闭"> </a>
									 <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
									 <a href="" class="reload" data-original-title="" title=""> </a>
                                        <a href="" class="fullscreen" data-original-title="" title=""> </a>
                                        <a href="" class="remove" data-original-title="" title=""> </a>
								</div>
							</div> -->

						</div>
					</div>

				</div>

				<div class="form-group form-md-line-input">
					<label class="col-lg-2 control-label"></label>
					<div class="col-lg-8">

<!-- 						<div class="portlet light bordered"> -->
					<div class="portlet box yellow">
							<div class="portlet-title">
								<div class="caption font-red-intense">
									<i class="icon-speech font-red-intense"></i> <span
										class="caption-subject bold uppercase">体验说明</span> <span
										class="caption-helper"></span>
								</div>
								<div class="tools">
									<a href="" class="collapse" title="展开/关闭"> </a>
									<!--  <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a> -->
									<!--  <a href="" class="reload" data-original-title="" title=""> </a>
                                        <a href="" class="fullscreen" data-original-title="" title=""> </a>
                                        <a href="" class="remove" data-original-title="" title=""> </a> -->
								</div>
							</div>
							<div class="portlet-body" >
								<div id="context2" data-toggle="context"
									data-target="#context-menu">${guideService.remark}</div>

							</div>
						<!-- 	<div class="portlet-title">
									<div class="caption font-red-intense">
									<i class="icon-speech font-red-intense"></i> <span
										class="caption-subject bold uppercase">内容</span> <span
										class="caption-helper"></span>
								</div>
								<div class="tools">
									<a href="" class="close" title="展开/关闭"> </a>
									 <a href="#portlet-config" data-toggle="modal" class="config" data-original-title="" title=""> </a>
									 <a href="" class="reload" data-original-title="" title=""> </a>
                                        <a href="" class="fullscreen" data-original-title="" title=""> </a>
                                        <a href="" class="remove" data-original-title="" title=""> </a>
								</div>
							</div>
 -->
						</div>
					</div>

				</div>



				<div class="form-group form-md-line-input">
					<div class="col-lg-9 col-lg-offset-3">
						<input id="cancel_btn" class="btn btn-primary " type="button"
							value="返回" onclick="history.back()" />
					</div>

				</div>

			</form>
		</div>
	</div>
</body>
</html>