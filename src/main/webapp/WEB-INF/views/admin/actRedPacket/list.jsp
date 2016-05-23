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
<title>红包记录</title>
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
				<!-- 
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i>
					</div>
					<div class="tools">
						<a href="javascript:;" class="reload" data-original-title=""
							title="刷新"> </a>
					</div>
				</div> -->

				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">红包记录列表</span>
					</div>
					<div class="actions">
						<!-- 	<a href="javascript:addShow()"
							class="btn btn-sm green btn-outline">新增</a>
							<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a> 
						<a href="javascript:batDel();" class="btn btn-sm red btn-outline">批量删除</a> -->
					</div>
				</div>

				<div class="portlet-body">

					<form id="searchForm" action="${ctx}/admin/actRedPacket"
						method="get">
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
									placeholder="订单号" name="orderNo"
									value="${actRedPacket.orderNo}"> <input type="text"
									class="form-filter input-sm" placeholder="活动名称" name="actName"
									value="${actRedPacket.actName}">
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

									<!-- <th scope="col"></th> -->
									<!-- <th scope="col">微信公众号id</th> -->
									<!-- <th scope="col">红包活动编号</th> -->
									<th scope="col">活动名称</th>
									<th scope="col">订单号</th>
									<th scope="col">接收的用户</th>
									<!-- <th scope="col">商户号</th> -->
									<th scope="col">付款金额</th>
									<th scope="col">发放总人数</th>
									<th scope="col">祝福语</th>
									<th scope="col">机器Ip地址</th>

									<th scope="col">发送时间</th>
									<th scope="col">备注</th>
									<th scope="col">状态</th>
									<!-- 	<th scope="col">创建时间</th> -->
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="index">
									<tr role="row" >

										<%-- 	<td><input type="checkbox" id="${item.id}" /></td> --%>
										<%-- <td>${item.wxAppId}</td> --%>
										<%-- 	<td>${item.actNo}</td> --%>
										<td>${item.actName}</td>
										<td>${item.orderNo}</td>
										<td>${item.openId}</td>
										<%-- <td>${item.partnerId}</td> --%>
										<td>${item.totalAmount}</td>
										<td>${item.totalNum}</td>
										<td>${item.wishing}</td>
										<td>${item.clientIp}</td>
										
										<%-- <td>${item.wishing}</td> --%>
										<%-- <td>${item.clientIp}</td> --%>

										<td><fmt:formatDate value="${item.sendTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
												<td>${item.remark}</td>
										<td><c:if test="${item.status==1 }">
												<span style="color: green;"> 发送成功</span>
											</c:if> <c:if test="${item.status==2 }">
												<span style="color: red;"> 待发送</span>
											</c:if></td>
										<%-- <td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td> --%>
										<td><a href="javascript:detailShow(${item.id})"
											title="详情" class="btn blue btn-sm btn-outline"">详情</a> <%-- <a
											class="btn yellow btn-sm btn-outline"
											href="javascript:editShow(${item.id})" target="_blank">编辑</a>
											<a class="btn red btn-sm btn-outline"
											href="javascript:del(${item.id})" target="_blank">删除</a> --%></td>
										</td>
									</tr>
									<%-- <tr class="child">
										<td class="child" colspan="7"><ul data-dtr-index="4">
												<li data-dtr-index="4"><span class="dtr-title">创建时间:</span>
													<span class="dtr-data"><fmt:formatDate value="${item.updateDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
												<li data-dtr-index="5"><span class="dtr-title">更新时间:</span>
													<span class="dtr-data"><fmt:formatDate value="${item.createDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></span></li>
												
											</ul></td>
									</tr> --%>
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
				<form action="#" id="modalForm" class="form-horizontal">
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
								<!-- <div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div> -->


								<input type="hidden" class="form-control" name="id" />
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">活动名称： <span
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
									<label class="control-label col-md-3"> <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="expiresIn" />
										</div>
									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">微信公众号<span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="weixinPublicId" />
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
	<!-- //提交加载中  -->

	<div id="detailResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="#" id="modalForm" class="form-horizontal">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>


				<!-- 	<div class="modal-header"> -->



						<div class="portlet-body">



							<!-- BEGIN FORM-->

							<div class="form-body  ">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>

								<!-- <div class="alert alert-success display-hide">
									<button class="close" data-close="alert"></button>
									Your form validation is successful!
								</div> -->


								<input type="hidden" class="form-control" name="id" />

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">活动编号: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="actNo">
										</span> </label>

									</div>
									<label class="control-label col-md-3">活动名: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="actName">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">订单号: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="orderNo">
										</span> </label>

									</div>
								
									<label class="control-label col-md-3">微信公众号ID: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="wxAppId">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">微信商户号: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="partnerId">
										</span> </label>

									</div>
								
									<label class="control-label col-md-3">用户编号: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="userNo">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">用户openId: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="openId">
										</span> </label>

									</div>
							
									<label class="control-label col-md-3">付款金额: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="totalAmount">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">人数: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="totalNum">
										</span> </label>

									</div>
								
									<label class="control-label col-md-3">祝福语: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="wishing">
										</span> </label>

									</div>
								</div>
									<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">备注: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="remark">
										</span> </label>

									</div>
							
									<label class="control-label col-md-3">调用接口的机器Ip: </label>
									<div class="col-md-3">
										<label class="control-label"><span id="clientIp"> </span> </label>

									</div>
								</div>
							<div class="form-group  margin-top-20">
								<label class="control-label col-md-3">发送时间: </label>
								<div class="col-md-3">
									<span id="sendTime"></span>
								</div>

								<label class="control-label col-md-3">创建时间: </label>
								<div class="col-md-3">
									<span id="createTime" >	</span>
								</div>
							</div>
							<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">红包订单的微信单号: </label>
									<div class="col-md-3">
									<label class="control-label">
										<span id="sendListid">
										</span>
									</label>
									</div>
								</div>
								
						<!-- 	</div> -->
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


	<!--DOC: Aplly "modal-cached" class after "modal" class to enable ajax content caching-->
<script type="text/javascript">
		window.mlx = {
			ctx : "${ctx}"
		};

		var getUrl = mlx.ctx + "/admin/actRedPacket";

	
	
		function detailShow(id) {
		
			var obj = get(id);
			if(obj==null){
			comm.showMsg('warning', '消息提示', '获取对像失败！');
			return ;
			}
				$("#detailResponsive").modal('show');
			//将对象属性 填充表单
			for ( var name in obj) {
				$("#modalForm span[id=" + name + "]").html(obj[name]);
			}
			/* $("#ajax").hide();
			$("#modalForm button[type='submit']").one("click", add); */
		}
	
		//获取对信息
		function get(id) {
			var url = getUrl + "/" + id;
			var data;
			$.ajax({
				url : url,
				type : 'get',
				async : false,
				timeout : 400,
				success : function(result) {
					if (result.code == "200") {
						data = result.result;
					} else {
						return false;
					}
				},
				error : function(e) {

					$("#message").html("请求出错！" + e);
				}
			});

			return data;

		}


	
	</script>

	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

</body>
</html>