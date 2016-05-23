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
<title>万人群</title>
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
				class="btn red btn-outline" href="${ctx}/admin/guideUserInfo/list"
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
							class="caption-subject font-dark bold uppercase">环信万人群列表</span>
					</div>
					<div class="actions">
						<a class="btn btn-sm green btn-outline addShow" href="${ctx}/admin/group/create">新增</a>
						<!-- 	<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a>  -->
						<a class="btn btn-sm red btn-outline batDel" href="javascript:batDel()
						">批量删除</a>
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/group" method="get">
						<div class="row">
							<input type="hidden" name="page" value="1">
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
									placeholder="环信群id" name="emGid" value="${group.emGid}">
								<input type="text" class="form-filter input-sm"
									placeholder="群组名称" name="emGname" value="${group.emGname}">
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
									<th scope="col">userNo/用户名</th>
									<th scope="col">群分类</th>
									<th scope="col">环信群id</th>
									<th scope="col">群组创建人环信用户名</th>
									<th scope="col">群组名称</th>
									<th scope="col">群组介绍</th>
									<th scope="col">群组形象照</th>
									<th scope="col">群组人数</th>
									<th scope="col">创建时间</th>
									<th scope="col">更新时间</th>
									<!-- <th scope="col">是否有效</th> -->
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="index">
									<tr role="row">
										<td><input type="checkbox" id="${item.id}" class="icheck" /></td>
										<td>${item.userNo}/${userMap[item.userNo]}</td>
										<td>${groupType[item.type]}</td>
										<td>${item.emGid}</td>
										<td>${item.emGuser}</td>
										<td>${item.emGname}</td>
										<td>${item.emGdesc}</td>
										<td><img width="50" height="50"
											src="${item.image}"></td>
										<td>${item.emAffiliationsCount}</td>
										<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${item.updateTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><a class="btn yellow btn-sm btn-outline editShow"
											href="${ctx}/admin/group/edit/${item.id}">编辑</a> <a
											class="btn red btn-sm btn-outline del" id="${item.id}"
											href="javascript:Groupdel(${item.id})">删除</a></td>
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
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script type="text/javascript">
	$(function(){
		initSelectAll();
	})
	
	var initSelectAll=function (){
		
		$("thead tr th input[type='checkbox']").on(
				"click",
				function(event) {
				//	console.log(event);
					//console.log(event.currentTarget.checked);
					if (event.currentTarget.checked==true) {
						$("tbody tr input[type='checkbox']").prop(
								"checked", true);
					} 
					
					if(event.currentTarget.checked==false) {
						$("tbody tr input[type='checkbox']").prop(
								"checked",false);
					}
				});
		}
	
	/**获取表格上选 中的ids**/
	 var getCheckIds=function() {
		//获取删 除的ids
		var ids = "";
		var obj = $("tbody tr td input[type='checkbox']:checked");
		$.each(obj, function(index, data) {
			if (obj.length == index + 1) {
				ids += $(data).attr("id");
			} else {
				ids += $(data).attr("id") + ",";
			}
		});
		return ids;
	}

	/**  批量删除 **/
	var  batDel=function() {
		var ids = getCheckIds();
		if (ids == "") {
			comm.showMsg('warning', '消息提示', '没有选 中，请选择！');
			return;
		}
		var url = mlx.ctx+ "/admin/group/del/" + ids;
		/* 设置按钮的语言 */
		comm.confirm("提示", "确定要删除这条记录吗？", function() {
				$.get(url, function(result) {
					if (result.code == "200") {
						location.reload()
					} else {
						comm.errorMsg(result.result);
					}
				},"json")
			})

	}

	//单个删除
		var Groupdel = function(id) {
			comm.confirm("提示", "确定要删除这条记录吗？", function() {
				$.get(mlx.ctx + "/admin/group/del/" + id, function(result) {
					if (result.code =="200") {
						location.reload()
					} else {
						comm.errorMsg("信息："+result.result);
					}
				},"json")
			})
		}
	</script>
</body>
</html>