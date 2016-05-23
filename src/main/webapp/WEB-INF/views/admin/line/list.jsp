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
<title>线路列表</title>
</head>
<body>
	<div class="note note-success">
		<p>
			温馨提示<br />1.查询普通微信用户信息,管理平台微信用户信息。<br />2.查询导游用户请点击<a
				class="btn red btn-outline" href="${ctx}/admin/guideUserInfo/list"
				target="_blank">导游用户</a>
		</p>
	</div>

	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">线路列表</span>
					</div>
					<div class="actions">
						<%-- <a href="${ctx}/admin/guideService/create"
							class="btn btn-sm green btn-outline">新增</a> --%>
						<!-- <a class="btn btn-sm dark btn-outline batAudit">审核</a> <a
							class="btn btn-sm red btn-outline batDel">批量删除</a> -->
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/guideLine">
						<div class="row">
							<div class="col-md-6">
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
								</select> <select name="auditStatus"
									class="form-control input-sm input-xsmall input-inline">
									<!-- 0未提交审核1待审核2审核通过3审核未通过 -->
									<option value="">全部</option>
									<option value="0"
										<c:if test="${auditStatus == 0}">selected</c:if>>未提交审核</option>
									<option value="1"
										<c:if test="${auditStatus == 1}">selected</c:if>>待审核</option>
									<option value="2"
										<c:if test="${auditStatus == 2}">selected</c:if>>审核通过</option>
									<option value="3"
										<c:if test="${auditStatus == 3}">selected</c:if>>审核不通过</option>

								</select> <select name="status"
									class="form-control input-sm input-xsmall input-inline">
									<!-- 	1编辑中2上线3下线 -->
									<option value="">全部</option>
									<option value="1" <c:if test="${status == 1}">selected</c:if>>编辑中</option>
									<option value="2" <c:if test="${status == 2}">selected</c:if>>上线</option>
									<option value="3" <c:if test="${status == 3}">selected</c:if>>下线</option>

								</select>
							</div>
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-6" style="text-align: right;">
								<input type="text" class="form-filter input-sm" placeholder="编号"
									name="lineNo" value="${lineNo}"> <input type="text"
									class="form-filter input-sm" placeholder="标题" name="title"
									value="${title}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>
							</div>
						</div>
					</form>
					<div class="table-scrollable">
						<table id="line"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!--  <th scope="col"><input type="checkbox" class="icheck" /></th>  -->
									<th scope="col">编号</th>
									<th scope="col">标题</th>
									<th scope="col">背景图</th>
									<th scope="col">服务者No</th>
									<th scope="col">价格</th>
									<th scope="col">状态</th>
									<th scope="col">审核状态</th>
									<th scope="col">排序号</th>
									<th scope="col">创建时间</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
										<%--  <td><input type="checkbox" id="${item.id}" class="icheck" /></td> --%>
										<td>${item.lineNo}</td>
										<td>${item.title}</td>
										<td><img src="${item.imgUrl}" title="${item.title}"
											height="50px" width="50px" /></td>
										<td>${item.userNo}</td>
										<td>${item.price}</td>
										<td>${EStatus[item.status]}</td>
										<td>${EAuditStatus[item.auditStatus]}</td>
										<td class="ext-sort">${item.sort}</td>
										<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td id="${item.id }"><a
											href="${ctx}/admin/guideLine/detail/${item.id}"
											class="btn btn-sm yellow btn-outline detail">详情</a> <a
											class="btn btn-sm dark btn-outline audit">审核 </a> 
											<c:if test="${item.status== 3 || item.status==1}">
											<a status="2" class="btn btn-sm green btn-outline onOff">上线</a>
											</c:if>
											<c:if test="${item.status==2}">
											<a status="3" class="btn btn-sm green btn-outline onOff">下线</a>
											</c:if> 
											<a	class="btn btn-sm red btn-outline del">删除</a> <a
											class="btn btn-sm blue btn-outline preView">预览</a></td>
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

	<!-- / 审核 .modal -->
	<div id="auditResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="auditForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
					<div class="modal-header">
						<div class="portlet-body">

							<!-- BEGIN FORM-->

							<div class="form-body">

								<div class="form-group">
									<label class="col-md-3 control-label">审核 <span
										class="required"> * </span></label>
									<div class="col-md-9">
										<div class="radio-list">
											<label class="radio-inline"> <input type="radio"
												name="auditStatus" value="2" checked> 通过
											</label> <label class="radio-inline"> <input type="radio"
												name="auditStatus" value="3"> 不通过
											</label>
										</div>
									</div>
								</div>
								<div class="form-group form-md-line-input">
									<label class="col-lg-3 control-label">说明：</label>
									<div class="col-lg-5">
										<textarea class="form-control" name="auditRemark"
											id="description" rows="3" placeholder="这里添加描述"></textarea>
										<label for="form_control_1"></label>
									</div>
								</div>
							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit" class="btn green">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- / 上下线 .modal -->
	<div id="onOffResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form id="onOffForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
					<div class="modal-header">
						<div class="portlet-body">

							<!-- BEGIN FORM-->

							<div class="form-body">

								<div class="form-group">
									<label class="col-md-3 control-label">状态：<span
										class="required"> * </span></label>
									<div class="col-md-9">
										<div class="radio-list">
											<label class="radio-inline"> <input type="radio"
												name="status" value="2" checked> 上线
											</label> <label class="radio-inline"> <input type="radio"
												name="status" value="3">下线
											</label>
										</div>
									</div>
								</div>
								<!-- <div class="form-group form-md-line-input">
									<label class="col-lg-3 control-label">说明：</label>
									<div class="col-lg-5">
										<textarea class="form-control" name="remark" id="description"
											rows="3" placeholder="这里添加描述"></textarea>
										<label for="form_control_1"></label>
									</div>
								</div> -->
							</div>
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit" class="btn green">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 预览  modal-->
	<div id="preViewResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="col-md-12 col-md-offset-2 text-center">
					<div
						style="background: url('/guide-admin/static/img/mobile.gif'); width: 362px; height: 674px;">
						<iframe scrolling="no"
							style="height: 569px; width: 320px; border: 0px solid #000; margin: 0 auto; margin-top: 33px;"></iframe>
					</div>
					<div class="col-md-1 col-md-offset-3"
						style="    margin-top: -52px; margin-left: 163px;">
						<a class="close" style="background-position-x: 10px;
    					background-position-y: 10px; width: 30px;height: 30px;" data-dismiss="modal" aria-hidden="true">
							<!-- <button type="button" class="close"></button> -->
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/datatables/datatables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#line').DataTable({

				"paging" : false,
				"ordering" : true,
				"info" : false,
				"searching" : false,
				"columns" : [ {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : false
				}, null, null, null, null, null, null, {
					"orderable" : false
				} ]
			});
		});
	</script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script type="text/javascript" src="${ctx}/static/js/guideLine.js"></script>
</body>
</html>