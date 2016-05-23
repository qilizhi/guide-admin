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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>红包活动</title>
<script type="text/javascript">
	window.mlx = {
		ctx : "${ctx}"
	};
</script>
</head>
<body>
	<!-- 	<div id="toast-container" class="toast-top-center" aria-live="polite"
		role="alert">
		<div class="toast toast-success">
			<button c lass="toast-close-button" role="button">×</button>
			<div class="toast-title">Toastr Notifications</div>
			<div class="toast-message">Gnome &amp; Growl type non-blocking
				notifications</div>
		</div>
	</div>  -->
	<div class="note note-success">

		<p>
			温馨提示<br />1.查询普通微信用户信息,管理平台微信用户信息。<br />2.查询导游用户请点击<a
				class="btn red btn-outline" href="${ctx}/admin/guideIntro/list"
				target="_blank">导游用户</a>
		</p>
	</div>

	<div class="row">
		<div class="col-md-12">


			<!-- BEGIN EXAMPLE TABLE PORTLET   portlet box purple -->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">红包活动列表</span>
					</div>
					<div class="actions">
						<a 	class="btn btn-sm green btn-outline addShow">新增</a>
						<!-- 	<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a>  -->
						<a class="btn btn-sm red btn-outline batDel">批量删除</a>
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/actInfo" method="get">
						<div class="row">
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-4">

								<select name="pageSize"
									class="form-control input-sm input-xsmall input-inline">
									<option value="5" <c:if test="${pageSize == 5}">selected</c:if>>5</option>
									<option value="10"
										<c:if test="${pageSize == 10}">selected</c:if>>10</option>
									<option value="20"
										<c:if test="${pageSize == 20}">selected</c:if>>20</option>
									<option value="50"
										<c:if test="${pageSize == 50}">selected</c:if>>50</option>
									<option value="100"
										<c:if test="${pageSize == 100}">selected</c:if>>100</option>
								</select>
							</div>
							<div class="col-md-4"></div>
							<div class="col-md-4" style="text-align: right;">
								<input type="text" class="form-filter input-sm"
									placeholder="活动名称" name="actName" value="${actInfo.actName}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>
							</div>
							</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" class="icheck" /></th>
									<th scope="col">活动名</th>
									<th scope="col">奖励总金额</th>
									<th scope="col">奖励金额</th>
									<th scope="col">总人数</th>
									<th scope="col">开始日期</th>
									<th scope="col">结束日期</th>
									<!-- <th scope="col">是否有效</th> -->
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="index">
									<tr role="row" >
										<td><input type="checkbox" id="${item.id}" class="icheck" /></td>
										<td>${item.actName}</td>
										<td>${item.totalPrice}</td>
										<td>${item.price}</td>
										<td>${item.totalNum}</td>
										<td><fmt:formatDate value="${item.startDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${item.endDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
											<td><a 
											title="详情" class="btn blue btn-sm btn-outline detailShow" id="${item.id}">详情</a> <a
											class="btn yellow btn-sm btn-outline editShow"
											id="${item.id }">编辑</a>
											<a class="btn red btn-sm btn-outline del"
											id="${item.id}">删除</a> <a
											class="btn dark btn-sm btn-outline showRecord"
											id="${item.id}">查看红包记录</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->
		</div>
	</div>
	<!-- 
	modal view -->

	<!-- /.modal -->
	<div id="responsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
					<div class="modal-body">
						<div class="portlet-body">
							<!-- BEGIN FORM-->
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<input type="hidden" class="form-control" name="id" />
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">活动名称 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="actName" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">奖厉总金额 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="totalPrice" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">单个金额 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="price" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">红包个数 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="totalNum" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">开始时间 <span
										class="required"> * </span></label>
									<div class="col-md-4">
										<div class="input-group date form_datetime"
											style="width: 207px;">
											<input type="text" name="startDate" size="14"
												class="form-control"> <span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">结束日期<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-group date form_datetime"
											style="width: 207px;">
											<input type="text" name="endDate" size="14"
												class="form-control"> <span class="input-group-btn">
												<button class="btn default date-set" type="button">
													<i class="fa fa-calendar"></i>
												</button>
											</span>
										</div>
										<!-- /input-group -->
									</div>
								</div>
							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<div style="float: left; line-height: 30px;" id="ajax">
							<img
								src="${ctx }/static/assets/global/img/loading-spinner-grey.gif"
								alt="" class="loading"> <span id="message">
								&nbsp;&nbsp;请稍等... </span>
						</div>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit" class="btn green">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- //提交加载中  -->

	<div id="detailResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
					<div class="modal-body">
						<div class="portlet-body">
							<!-- BEGIN FORM-->
							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<input type="hidden" class="form-control" name="id" />

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">活动名称: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="actName">
										</span> </label>

									</div>
								</div>

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">有效奖励总金额: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="totalPrice">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">单个奖励金额: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="price">
										</span> </label>

									</div>
								</div>

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">总人数: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="totalNum">
										</span> </label>

									</div>
								</div>
								<!-- <div class="form-group  margin-top-20">
									<label class="control-label col-md-6">是否有效: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="flag"> </span>
										</label>

									</div>
								</div> -->
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">开始时间: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="startDate">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">结束时间: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="endDate">
										</span> </label>

									</div>
								</div>
							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<%-- <div style="float: left; line-height: 30px;" id="ajax">
							<img
								src="${ctx }/static/assets/global/img/loading-spinner-grey.gif"
								alt="" class="loading"> <span id="message">
								&nbsp;&nbsp;请稍等... </span>
						</div> --%>
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<!-- 	<button type="submit" class="btn green">提交</button> -->
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/static/js/actInfo.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
</body>
</html>