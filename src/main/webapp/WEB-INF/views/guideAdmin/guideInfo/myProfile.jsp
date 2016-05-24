<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>基本资料</title>
<style>
.img-thumbnail{with:200px;height:200px;}
</style>
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
											<dd>${guide.age} 后</dd>
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
												<a href="${guide.idCardFrontPic }"><img alt="身份证正面" src="${guide.idCardFrontPic }" class="img-thumbnail"/></a>
											</dd>
										</dl>
									</div>
									<div class="col-md-4 col-sm-4">
										<dl class="dl-horizontal">
											<dt>身份证反面:</dt>
											<dd>
												<a href="${guide.idCardSidePic }"><img alt="身份证反面" src="${guide.idCardSidePic }" class="img-thumbnail"/></a>
											</dd>
										</dl>
									</div>
									<div class="col-md-4 col-sm-4">
										<dl class="dl-horizontal">
											<dt>背景图:</dt>
											<dd>
												<a href="${guide.bgImgUrl }"><img alt="背景图" src="${guide.bgImgUrl }" class="img-thumbnail" /></a>
											</dd>
										</dl>
									</div>
								</div>

								<div class="scroller-footer">
									<div class="btn-arrow-link pull-right">
										<a href="javascript:;" onClick="javascript:history.back(-1);">返回</a>
									</div>
								</div>

							</div>
						</div>
					</div>





				</div>
			</div>
		</div>
	</div>
</body>
</html>