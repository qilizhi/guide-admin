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
<title>虚拟商品详情</title>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
</head>
<body>
	<div class="portlet light portlet-fit portlet-form ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-settings font-dark"></i> <span
							class="caption-subject font-dark sbold uppercase">虚拟产品详情</span>
					</div>

				</div>
				<div class="portlet-body">
	<form action="${ctx}/admin/guideService/update" id="inputForm"
		method="post" class="form-horizontal mlx-form">
		<%-- <input type="hidden" id="id" name="id" value="${guideService.id}" /> --%>
	
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">名称：</label>
			<div class="col-lg-5">
				<label class="control-label"><span id="title">${item.name}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">类别：</label>
			<div class="col-lg-5">
				<label class="control-label"><span id="title">${goodsType[item.goodsType]}</span></label>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">描述：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${item.description}</span></label>
			</div>

		</div>
			<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">图片：</label>
			<div class="col-lg-5">
			<label class="control-label">	<img src="${item.imgUrl}" /></label>
			</div>
		</div>
		
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">价格:</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${item.price}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">库存:</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${item.num}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">审核状态：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${AuditStatus[item.auditStatus]}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">审核说明：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${item.auditRemark }</span></label>
			</div>
		</div>

		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">审核时间：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>
				<fmt:formatDate value="${item.auditTime }"
												pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
				</label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">创建时间：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>
				<fmt:formatDate value="${item.createTime }"
												pattern="yyyy-MM-dd HH:mm:ss" />
				</span>
				</label>
			</div>
		</div>

		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">发布时间：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>
				<fmt:formatDate value="${item.pubTime}"
												pattern="yyyy-MM-dd HH:mm:ss" />
				</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">状态：</label>
			<div class="col-lg-5">
			<label class="control-label">	<span>${Status[item.status]}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">排序号：</label>
			<div class="col-lg-5">
				<label class="control-label">	<span>${item.sort}</span></label>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<div class="col-lg-9 col-lg-offset-3">
				 <input
					id="cancel_btn" class="btn btn-primary " type="button" value="返回"
					onclick="history.back()" />
			</div>

		</div>

	</form>
	</div>
	</div>
</body>
</html>