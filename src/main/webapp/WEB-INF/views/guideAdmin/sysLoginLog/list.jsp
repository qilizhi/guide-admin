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


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">登录日志</span>
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/sysLoginLog/list" method="post">
	
	<div class="row">
		<div class="col-md-9">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-2" style="text-align: right;">
		<div class="input-group input-large date-picker input-daterange" data-date="2012-10-11" data-date-format="yyyy-mm-dd">
			<input type="text" class="form-control" name="beginCreateTime" value="${beginCreateTime}"> 
			<span class="input-group-addon"> to </span> 
			<input type="text" class="form-control" name="endCreateTime" value="${endCreateTime}">
		</div>
		</div>
		
		<div class="col-md-1" style="text-align: right;">
        <button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col">用户编号</th>
									<th scope="col">登录的IP</th>
									<th scope="col">用户名称</th>
									<th scope="col">登录时间</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td>${item.userNo}</td>
										<td>${item.loginIp}</td>
										<td>${item.userName}</td>
										<td><fmt:formatDate value="${item.loginTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
	<!-- 弹出框start -->
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

						<div class="portlet-body">

							<div class="form-body  ">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
									You have some form errors. Please check below.
								</div>
								<input type="hidden" class="form-control" name="id" />

								<div class="form-group  margin-top-20">
									<label class="control-label col-md-2">用户编号: </label>
									<div class="col-md-2">
										<label class="control-label"><span id="userNo">
										</span> </label>

									</div>
									<label class="control-label col-md-2">登录的IP: </label>
									<div class="col-md-2">
										<label class="control-label"><span id="loginIp">
										</span> </label>

									</div>
								</div>
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-2">用户名称: </label>
									<div class="col-md-2">
										<label class="control-label"><span id="userName">
										</span> </label>

									</div>
									<label class="control-label col-md-2">登录时间: </label>
									<div class="col-md-2">
										<label class="control-label"><span id="loginTime">
										</span> </label>

									</div>
								</div>
								
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- 弹出框end -->
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>

<script type="text/javascript">
		window.mlx = {
			ctx : "${ctx}"
		};

		var getUrl = mlx.ctx + "/guideAdmin/sysLoginLog";

	
	
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




</body>
</html>