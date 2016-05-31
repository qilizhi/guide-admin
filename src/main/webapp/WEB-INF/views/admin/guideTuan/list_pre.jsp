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
			<div class="portlet box red">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-gift"></i>出团列表
					</div>
			<!-- 		<ul class="nav nav-tabs">
						<li class=""><a href="#portlet_tab_3" data-toggle="tab"
							aria-expanded="false">近期出团</a></li>
						<li class=""><a href="#portlet_tab_2" data-toggle="tab"
							aria-expanded="false">已出团</a></li>
						<li class="active"><a href="#portlet_tab_1" data-toggle="tab"
							aria-expanded="true">已取消</a></li>
					</ul> -->
					<ul class="nav nav-tabs">
						<li class="active"><a href="${ctx}/admin/guideTuan/pre">近期出团</a></li>
						<li class=""><a href="${ctx}/admin/guideTuan/success" >已出团</a></li>
						<li class=""><a href="${ctx}/admin/guideTuan/cancel" >已取消</a></li>
					</ul>
				</div>
				<div class="portlet-body active">
					<div class="tab-content">
			
						<div class="tab-pane active" id="portlet_tab_3">
							<div class="portlet-body">
								<form id="searchForm" action="${ctx}/admin/guideTuan/pre"
									method="post">
									<div class="row">
										<div class="col-md-4">
											<select name="pageSize"
												class="form-control input-sm input-xsmall input-inline">
												<option value="5"
													<c:if test="${pageSize == 5}">selected</c:if>>5</option>
												<option value="10"
													<c:if test="${pageSize == 10}">selected</c:if>>10</option>
												<option value="20"
													<c:if test="${pageSize == 20}">selected</c:if>>20</option>
												<option value="50"
													<c:if test="${pageSize == 50}">selected</c:if>>50</option>
												<option value="100"
													<c:if test="${pageSize == 100}">selected</c:if>>100</option>
											</select> <!-- <select
												class="form-control input-sm input-xsmall input-inline"
												name="tuanStatus">
												<option value="1">待出团</option>
												<option value="2">已出团</option>
												<option value="3">已取消</option>
											</select> -->
										</div>
										<input type="hidden" name="pageNo" value="1">

										<div class="col-md-8" style="text-align: right;">

											<input type="text" class="form-filter input-sm"
												placeholder="出团编号" name="groupNo" value="${groupNo}">
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
												<!-- <th scope="col">出团名称</th> -->
												<th scope="col">团编号</th>
												<th scope="col">出团日期</th>
												<th scope="col">产品名称</th>
												<th scope="col">产品类型</th>
												<th scope="col">订单数</th>
												<th scope="col">参团人数</th>
												<th scope="col">操作</th>

											</tr>
										</thead>
										<tbody>

											<c:forEach items="${list}" var="item">
												<tr>
													<td>${item.groupNo}</td>
													<%-- <td>${item.groupNo}</td> --%>
													<td>${item.tripDate}</td>
													<td>${item.goodsName}</td>
													<td>${item.goodsType}</td>
													<td>${item.orderNum}</td>
													<td>${item.touristNum}</td>
													<td> 
											<a  href="${ctx}/admin/guideTuan/detail/${item.groupNo}" class="btn btn-sm yellow btn-outline detail">详情</a>
											<a  href="${ctx}/admin/guideTuan/submit/${item.groupNo}" class="btn btn-sm red btn-outline detail">出团</a>
											<a class="btn btn-sm dark btn-outline audit">取消 </a>
													</td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
							<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
						</div>
					</div>
				</div>
			</div>
			<!-- END EXAMPLE TABLE PORTLET-->

		</div>
	</div>

	<script type="text/javascript"
		src="${ctx}/static/assets/global/plugins/datatables/datatables.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#tuan').DataTable({

				"paging" : false,
				"ordering" : true,
				"info" : false,
				"searching" : false,
				 "columns": [
		                        { "orderable": true },
		                        { "orderable": true },
		                        { "orderable": true },
		                        { "orderable": true },
		                        { "orderable": false }
		                     
		                       
		                      ]
			});
		});
	</script>



</body>

</html>