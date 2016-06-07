<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>导游后台主页</title>
<%--  <link href="${ctx}/static/assets/global/plugins/datatables/datatables.css" rel="stylesheet" type="text/css" />  --%>
<%-- <link href="${ctx}/static/assets/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.css" rel="stylesheet" type="text/css" /> --%>
</head>

<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">出团列表</span>
					</div>
					<div class="actions"></div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/guideTuan" method="post">
						<div class="row">
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
								</select> <select class="form-control input-sm input-xsmall input-inline"
									name="tuanStatus">
									<option value="">全部</option>
									<option value="1">待出团</option>
									<option value="2">已出团</option>
									<option value="3">已取消</option>
								</select>
							</div>
							<input type="hidden" name="pageNo" value="1">

							<div class="col-md-8" style="text-align: right;">

								<input type="text" class="form-filter input-sm"
									placeholder="线路编号" name="lineNo" value="${lineNo}"> <input
									type="text" class="form-filter input-sm" placeholder="出团名称"
									name="name" value="${name}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>

							</div>

						</div>
					</form>
					<div class="table-scrollable">
						<table id="tuan"
							class="table table-striped table-bordered table-hover"
							style="margin-bottom: 0;">
							<thead>
								<tr>

									<th scope="col">出团名称</th>
									<th scope="col">团编号</th>
									<th scope="col">出团日期</th>
									<th scope="col">产品编号</th>
									<th scope="col">产品类型</th>
									<!-- <th scope="col">线路类型</th>
									<th scope="col">线路编号</th> -->
									<!-- <th scope="col">导游名称</th> -->
									<!-- 	<th scope="col">创建时间</th> -->
									<th scope="col">满员人数</th>
									<th scope="col">实际人数</th>
									<!-- <th scope="col">订单数</th> -->
									<th scope="col">出团状态</th>
									<th scope="col">操作</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item">
									<tr>
										<td><a
											href="${ctx}/admin/guideTuan/tuanGuest/${item.tuanNo}">${item.name}</a></td>
										<td>${item.tuanNo}</td>
										<td><fmt:formatDate value="${item.tuanDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td>${item.goodsNo}</td>
										<td>${item.goodsType}</td>
										<%-- 	<td>${ELineType[item.lineType]}</td> --%>
										<%-- 	<td>${item.lineNo}</td>
										<td>${item.userName}</td> --%>
										<%-- 	<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
										<td>${item.fullNum}</td>
										<td>${item.personNum}</td>
										<%-- <td>${item.orderNum}</td> --%>
										<td>${ETuanStatus[item.tuanStatus]}</td>

										<td data-tuanNo="${item.tuanNo}"><a
											href="${ctx}/admin/guideTuan/order/detail/${item.tuanNo}?tuanName=${item.name}"
											class="btn btn-sm yellow btn-outline detail">详情</a> <c:if
												test="${item.tuanStatus==1}">
												<a data-href="${ctx}/admin/guideTuan/submit/out"
													class="btn btn-sm red btn-outline out">出团</a>
											</c:if> <c:if test="${item.tuanStatus==2||item.tuanStatus==1}">
												<a data-href="${ctx}/admin/guideTuan/submit/cancel"
													class="btn btn-sm dark btn-outline cancel">取消 </a>
											</c:if> <c:if test="${item.tuanStatus==2}">
												<a href="${ctx}/admin/guideTuan/tuanGuest/${item.tuanNo}"
													class="btn btn-sm green btn-outline">签到详情</a>
											</c:if></td>
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
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/datatables/datatables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tuan').DataTable({

				"paging" : false,
				"ordering" : true,
				"info" : false,
				"searching" : false,
				"columns" : [ {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : true
				}, {
					"orderable" : false
				}

				]
			});

			//初始化
			OCsubmit(".cancel");
			OCsubmit(".out");
		});

		//数据提交。
		var OCsubmit = function(_class) {
			$(_class).on("click", function(e) {
				var $a = $(e.currentTarget).parent();
				var $hrefUrl = $(e.currentTarget).attr("data-href");
				//sconsole.log($a);
				var data = new Object();
				data.tuanNo = $a.attr("data-tuanNo");
				$.post($hrefUrl, data, function(e) {
					//console.log(e)
					if (e.code == '200') {
						comm.showMsg("success", "提示", "操作成功")
						setTimeout(function() {
							location.reload();
						}, 500)
					} else {

						comm.showMsg("error", "提示",e.msg);
					}
				}, "json")
				//console.log(data);
			});

		}
	</script>



</body>

</html>