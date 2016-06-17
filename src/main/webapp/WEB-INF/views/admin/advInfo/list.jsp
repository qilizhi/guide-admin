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
<title>广告管理列表</title>
<style type="text/css">
.abcde {
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis;
}
</style>
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
							class="caption-subject font-dark bold uppercase">广告管理列表</span>
					</div>
					<div class="actions">
						<a href="${ctx}/admin/advInfo/add"
							class="btn btn-sm green  btn-outline">新增</a>
					</div>
				</div>

				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/advInfo" method="get">
						<div class="row">
							<input type="hidden" name="pageNo" value="1">
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
								</select>
							</div>



							<div class="col-md-6" style="text-align: right;">
								<input type="text" class="form-filter input-sm "
									placeholder="位置编号" name="position" value="${advInfo.position}" />
								<button type="submit"
									class="btn btn-sm green btn-outline  filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>
							</div>
						</div>
					</form>
					<div class="table-scrollable">
						<table id="advInfo"
							class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col">推荐位置编号</th>
									<th scope="col">链接地址</th>
									<th scope="col">描述</th>
									<th scope="col">创建时间</th>
									<th scope="col">开始日期</th>
									<th scope="col">结束日期</th>
									<th scope="col">排序号</th>
									<th scope="col">状态</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.position}</td>
										<td>${item.href}</td>
										<td>${item.description}</td>
										<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${item.startDate}"
												pattern="yyyy-MM-dd " /></td>
										<td><fmt:formatDate value="${item.endDate}"
												pattern="yyyy-MM-dd " /></td>
										<td class="ext-sort">${item.sort}</td>
										<td>${statusMap[item.status]}</td>
										<td><a class="btn yellow btn-sm btn-outline"
											href="${ctx}/admin/advInfo/edit/${item.id}">编辑</a> <c:if
												test="${item.status== 3 || item.status==1}">
												<a href="${ctx}/admin/advInfo/OnOffLine/${item.id}/2"
													class="btn btn-sm green btn-outline onOff">上线</a>
											</c:if> <c:if test="${item.status==2}">
												<a href="${ctx}/admin/advInfo/OnOffLine/${item.id}/3"
													class="btn btn-sm green btn-outline onOff">下线</a>
											</c:if> <a data-href="${ctx}/admin/advInfo/delete/${item.id}"
											class="btn btn-sm red btn-outline del"	>删除</a>
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
	<div id="responaddsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<!--  <form action="${ctx}/admin/advInfo/add" id="modalForm" class="form-horizontal" method="post">-->
				<form action="${ctx}/admin/advInfo/add" id="addForm"
					class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>


					<div class="modal-header">



						<div class="portlet-body">



							<!-- BEGIN FORM-->

							<div class="form-body">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<!-- <div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div> -->
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">推荐位置编号 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="position" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">链接地址 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="href" />
										</div>
									</div>
								</div>
								<div class="form-group">
									<label class="control-label col-md-3">描述 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="description" />
										</div>
									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">排序号<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="sort" />
										</div>
									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">状态<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="status" />
										</div>
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
	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
		$(function() {
			$("#addForm button[type=submit]").on("click", function(e) {
				e.preventDefault(); //取消时间的默认动作
				$("#addForm").submit(); //
			});
			$(".del").on("click",function(e){
				var url=$(e.currentTarget).attr("data-href");
				del(url);
			})
			
		});
		
		/* 删除 */
		var del = function(url) {
					/* 设置按钮的语言 */
		//	bootbox.setLocale("zh_CN");
			comm.confirm("提示","你确定要删除这条记录吗?", function() {

					$.ajax({
						url : url,
						type : 'post',
						dataType : "json",
						success : function(result) {
							if (result.code == "200") {
								//addHide();
								comm.showMsg('success', '消息提示', '删除成功！');
								location.reload();
							} else {
								comm.showMsg('warning', '消息提示', '删除失败！'+result.result);

							}
						},
						error : function(e) {
							comm.showMsg('error', '消息提示', '删除出错，请求出问题了！');
						}

					});

			});
		}

		function addShow() {
			$("#responaddsive").modal('show');
			$("#ajax").hide();
		}
	</script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/datatables/datatables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#advInfo').DataTable({

				"paging" : false,
				"ordering" : true,
				"info" : false,
				"searching" : false,
				"columns" : [ {
					"orderable" : true
				}, {
					"orderable" : true
				}, null, {
					"orderable" : true
				}, null, null, null, {
					"orderable" : true
				}, {
					"orderable" : false
				} ]
			});
		});
	</script>
</body>
</html>