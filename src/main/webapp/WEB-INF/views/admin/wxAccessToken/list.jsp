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
<title>公众号管理</title>
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
				class="btn red btn-outline" href="${ctx}/admin/guideUserInfo"
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
							class="caption-subject font-dark bold uppercase">公众号列表</span>
					</div>
					<div class="actions">
						<a href="javascript:addShow()"
							class="btn btn-sm green btn-outline">新增</a> 
						<!-- 	<a	href="javascript:;" class="btn btn-sm dark btn-outline">审核</a>  -->
							<a
							href="javascript:batDel();" class="btn btn-sm red btn-outline">批量删除</a>
					</div>
				</div>

				<div class="portlet-body">

					<form id="searchForm" action="${ctx}/admin/wxAccessToken"
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
									placeholder="凭证号" name="accessToken"
									value="${wxAccessToken.accessToken}"> <input
									type="text" class="form-filter input-sm" placeholder="微信公众号id"
									name="weixinPublicId" value="${wxAccessToken.weixinPublicId}">
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

									<th scope="col"><input type="checkbox" class="icheck"/></th>
									<th scope="col">编号</th>
									<th scope="col">凭证</th>
									<th scope="col">凭证有效时间</th>
									<th scope="col">微信公众号id</th>
									<th scope="col">操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="index">
									<tr role="row" >

										<td><input type="checkbox" id="${item.id}" class="icheck"/></td>
										<td>${item.id}</td>
										<td>${item.accessToken}</td>
										<td>${item.expiresIn}</td>
										<td>${item.weixinPublicId}</td>
										<td><fmt:formatDate value="${item.updateDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td><fmt:formatDate value="${item.createDate}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>

										<td><a href="javascript:detailShow(${item.id})"
											title="详情" class="btn blue btn-sm btn-outline"">详情</a> <a
											class="btn yellow btn-sm btn-outline"
											href="javascript:editShow(${item.id})" target="_blank">编辑</a>
											<a class="btn red btn-sm btn-outline"
											href="javascript:del(${item.id})" target="_blank">删除</a></td>
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
				<form  id="modalForm" class="form-horizontal">
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


								<input type="hidden" class="form-control" name="id" />
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">凭证 <span
										class="required"> * </span>
									</label>
									<div class="col-md-4">
										<div class="input-icon right">
											<i class="fa"></i> <input type="text" class="form-control"
												name="accessToken" />
										</div>
									</div>
								</div>

								<div class="form-group">
									<label class="control-label col-md-3">有效期 <span
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
				<form id="modalForm" class="form-horizontal">
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


								<input type="hidden" class="form-control" name="id" />

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">凭证: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="accessToken">
										</span> </label>

									</div>
								</div>

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">有效时间(秒): </label>
									<div class="col-md-6">
										<label class="control-label"><span id="expiresIn">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">微信公众号id: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="weixinPublicId">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">创建时间: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="createDate">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-6">更新时间: </label>
									<div class="col-md-6">
										<label class="control-label"><span id="updateDate">
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


	<!---->
	<script type="text/javascript">
	$(function(){
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
		
		var form1 = $('#responsive form');
		var error1 = $('.alert-danger', form1);
		var success1 = $('.alert-success', form1);

		form1.validate({
			errorElement : 'span', //default input error message container
			errorClass : 'help-block help-block-error', // default input error message class
			focusInvalid : false, // do not focus the last invalid input
			ignore : "", // validate all fields including form hidden input
			  messages : {
	            	 accessToken : {
	 					minlength : "至少要一个字符！",
	 					required : "不能为空！"
	 				},

	 				weixinPublicId : {
	 					number : '请输入数字',
	 					required : "不能为空！"
	 				},
	 				expiresIn : {
	 					required : "不能为空！",
	 					number : "请输入数字"
	 				}
	 				},
	             rules: {
	             	accessToken: {
	                     minlength: 2,
	                     required: true
	                 },
	                 weixinPublicId: {
	                 	 required: true,
	                      number: true
	                 },
	              
	                 expiresIn: {
	                     required: true,
	                     number: true
	                 },
	             },

			invalidHandler : function(event, validator) { //display error alert on form submit              
				success1.hide();
				error1.show();
				//App.scrollTo(error1, -200);
			},

			highlight : function(element) { // hightlight error inputs
				$(element).closest('.form-group').addClass('has-error'); // set error class to the control group
			},

			unhighlight : function(element) { // revert the change done by hightlight
				$(element).closest('.form-group').removeClass('has-error'); // set error class to the control group
			},

			success : function(label) {
				label.closest('.form-group').removeClass('has-error'); // set success class to the control group
			},

			submitHandler : function(form) {
				success1.show();
				error1.hide();
			}
		});
		
	})
	
	</script>


	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
		var addUrl = mlx.ctx + "/admin/wxAccessToken/create";
		var updateUrl = mlx.ctx + "/admin/wxAccessToken/update";
		var delUrl = mlx.ctx + "/admin/wxAccessToken/delete";
		var batdelUrl = mlx.ctx + "/admin/wxAccessToken/deletes";
		var getUrl = mlx.ctx + "/admin/wxAccessToken";

		function getCheckIds() {
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

		function batDel() {
			var ids = getCheckIds();
			if (ids == "") {
				showMsg('warning', '消息提示', '没有选 中，请选择！');
				return;
			}
			var url = batdelUrl + "/" + ids;
			/* 设置按钮的语言 */
			bootbox.setLocale("zh_CN");
			bootbox.confirm("你确定要删除这条记录吗?", function(result) {
				if (result) {

					$.ajax({
						url : url,
						type : 'post',
						success : function(result) {
							if (result.code == "200") {
								//addHide();
								showMsg('success', '消息提示', '删除成功！');
								location.reload();
							} else {
								showMsg('warning', '消息提示', '删除失败！');
							}
						},
						error : function(e) {
							showMsg('error', '消息提示', '删除出错，网络出问题了！');
						}

					});

				}
			});

		}

		function detailShow(id) {
			$("#detailResponsive").modal('show');
			var obj = get(id);
			//将对象属性 填充表单
			for ( var name in obj) {
				$("#modalForm span[id=" + name + "]").html(obj[name]);
			}
			/* $("#ajax").hide();
			$("#modalForm button[type='submit']").one("click", add); */
		}
		function addShow() {
			$("#responsive").modal('show');
			$("#modalForm").each(function() {
				this.reset();
			});
			$("#ajax").hide();
				//去除绑定事件
			$("#modalForm button[type='submit']").unbind();
			$("#modalForm button[type='submit']").on("click", add);
		}

		function editShow(id) {
			$("#modalForm").each(function() {
				this.reset();
			});
			//获取对象
			var obj = get(id);
			$("#ajax").hide();
			//将对象属性 填充表单
			for ( var name in obj) {
				$("#modalForm input[name=" + name + "]").val(obj[name]);
			}
				//去除绑定事件
			$("#modalForm button[type='submit']").unbind();
			$("#modalForm button[type='submit']").on("click", update);
			$("#responsive").modal('show');

		}

		/* 隐藏 */
		function modalHide() {
			$("#responsive").modal('hide');
			$("div.modal-backdrop").remove();
			//重设表单 
			$("#modalForm").each(function() {
				this.reset();
			});
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
					$("#message").html("请求出错！" );
				}
			});

			return data;

		}

		//新增
		function add() {
			var submitData = $("#responsive form").serialize();
			$("#ajax").show();
			$.ajax({
				url : addUrl,
				type : 'post',
				data : submitData,
				success : function(result) {
					if (result.code == "200") {
						modalHide();
						location.reload();

					} else {
						$("#message").html("创建失败！");
						$("#ajax").hide();
					}
				},
				error : function(e) {
					$("#message").html("请求出错！");
					$("#ajax").hide();
				}
			});

		}

		//提 交更新数据
		function update(id) {

			//打开modal
			editShow(id);
			var submitData = $("#responsive form").serialize();
			$("#ajax").show();
			$.ajax({
				url : updateUrl,
				type : 'post',
				data : submitData,
				success : function(result) {
					if (result.code == "200") {
						modalHide();
						location.reload();

					} else {
						$("#message").html("更新失败！");
						$("#ajax").hide();
					}
				},
				error : function(e) {
					$("#message").html("请求出错！");
					$("#ajax").hide();
				}
			});
		}

		/* 删除 */
		function del(id) {
			var url = delUrl + "/" + id;
			/* 设置按钮的语言 */
			bootbox.setLocale("zh_CN");
			bootbox.confirm("你确定要删除这条记录吗?", function(result) {
				if (result) {

					$.ajax({
						url : url,
						type : 'post',
						success : function(result) {
							if (result.code == "200") {
								//addHide();
								showMsg('success', '消息提示', '删除成功！');
								location.reload();
							} else {
								showMsg('warning', '消息提示', '删除失败！');

							}
						},
						error : function(e) {
							showMsg('error', '消息提示', '删除出错，网络出问题了！');
						}

					});

				}
			});
		}
	</script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

</body>
</html>