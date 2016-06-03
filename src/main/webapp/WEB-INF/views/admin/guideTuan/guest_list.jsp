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
<style type="text/css">
.actions span {
	margin-left: 10px;
}
</style>
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">成员名单</span>
					</div>
					<div class="actions">
						<span class="caption-subject font-dark bold "> 出团日期：<fmt:formatDate
								value="${tuan.tuanDate }" pattern="yyyy-MM-dd" />
						</span> <span class="caption-subject font-dark bold  ">
							导游 ：${tuan.userName}</span> <span
							class="caption-subject font-dark bold  "> 出团编 号
							：${tuan.tuanNo}</span> <span
							class="caption-subject font-dark bold  ">
							签到人数：${signNum} </span> <span
							class="caption-subject font-dark bold  ">
							出行人数：${paginator.totalCount}</span> <span
							class="caption-subject font-dark bold  ">满员人数：${tuan.fullNum }</span>
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm"
						action="${ctx}/admin/guideTuan/tuanGuest/${tuan.tuanNo}" method="post">
						<div class="row">

							<div class="col-md-4">
								<input type="hidden" name="pageNo"> <select
									name="pageSize"
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

							<%-- 	<div class="col-md-8" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="手机号码" name="mobile" value="${mobile}">
		<input type="text" class="form-filter input-sm" placeholder="姓名" name="guestName" value="${guestName}">
		<input type="text" class="form-filter input-sm" placeholder="订单号" name="orderNo" value="${orderNo}">
		<input type="text" class="form-filter input-sm" placeholder="订单号" name="tuanNo" value="${tuanNo}"> 
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
			
		</div>		
		--%>
						</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
								<!-- 	<th scope="col"><input type="checkbox"
										class=" group-checkable check-all"></th> -->
									<th scope="col">出团编号</th>
									<th scope="col">成员名称</th>
									<th scope="col">手机号码</th>
									<th scope="col">所属订单号</th>
									<th scope="col">签到时间</th>
									<th scope="col">签到状态</th>
									<!-- <th scope="col">备注</th> -->
									<th scope="col">操作</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item">
									<tr>
								<%-- 		<td><input type="checkbox"
											class="group-checkable check-all-item" value="${item.id}"></td> --%>
										<td>${item.tuanNo}</td>
										<td>${item.guestName}</td>
										<td>${item.mobile}</td>
										<td>${item.orderNo}</td>
										<td><fmt:formatDate value="${item.updateTime}" /></td>
										<td>${ESignInStatus[item.status]}</td>
										<%-- <td>${item.remark}</td> --%>
										<td><c:if test="${item.status!=2}">
												<a
													data-href="${ctx}/admin/guideTuan/tuanGuest/sign/${item.id}"
													class="btn btn-sm green btn-outline sign">签到</a>
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


	<script type="text/javascript">
		$(function() {

			OCsubmit(".sign");
		})
		//数据提交。
		var OCsubmit = function(_class) {
			$(_class).on("click",function(e) {
				var $hrefUrl = $(e.currentTarget).attr("data-href");
				$.post($hrefUrl, function(e) {
					//console.log(e) 
					if (e.code == '200') {
						comm.showMsg("success", "提示", "操作成功")
						setTimeout(function() {
							location.reload();
						}, 500)
					} else {

						comm.showMsg("error", "提示", "更新错误 ！");
					}
				}, "json")
				
			});

		}
	</script>





</body>

</html>