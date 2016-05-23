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
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">公众号列表</span>
					</div>
					<div class="actions">
						<a href="${ctx}/admin/wxPublic/edit"
							class="btn btn-sm green btn-outline">新增</a> 
					</div>
				</div>
				<div class="portlet-body">

					<form id="searchForm" action="${ctx}/admin/wxPublic"
						method="post">
						<div class="row">
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-12" style="margin-bottom:10px;">
								<select name="pageSize"
									class="form-control input-sm input-xsmall input-inline">
									<option value="10"
										<c:if test="${pageSize == 10}">selected</c:if>>10</option>
									<option value="20"
										<c:if test="${pageSize == 20}">selected</c:if>>20</option>
									<option value="50"
										<c:if test="${pageSize == 50}">selected</c:if>>50</option>
									<option value="100"
										<c:if test="${pageSize == 100}">selected</c:if>>100</option>
								</select>
							
							
							<div class="pull-right">
								<input type="text" class="form-filter input-sm"
									placeholder="公众号名称" name="name"
									value="${wxPublic.name}">
								<input type="text" class="form-filter input-sm"
									placeholder="APPID" name="appId"
									value="${wxPublic.appId}"> <input
									type="text" class="form-filter input-sm" placeholder="密钥"
									name="appSecret" value="${wxPublic.appSecret}">
								<button type="submit"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-search"></i> 查询
								</button>
								<button type="button" onclick="formReset();"
									class="btn btn-sm green btn-outline filter-submit margin-bottom">
									<i class="fa fa-mail-reply-all"></i> 重置
								</button>
							</div>
						</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th ></th>
									<th >公众号名称</th>
									<th >图片</th>
									<th >APPID</th>
									<th >密钥</th>
									<th >创建时间</th>
									<th >操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${list}" var="item" varStatus="index">
									<tr >
										 <td class="add" onclick="toggleDetail(this)"></td>
										<td>${item.name}</td>
										<td><a href="${item.iconUrl }" target="_blank"><img src="${item.iconUrl }" width="70px" title=" ${item.name }" alt="加载中.."></a></td>
										<td>${item.appId}</td>
										<td>${item.appSecret}</td>
										<td><fmt:formatDate value="${item.createTime}"
												pattern="yyyy-MM-dd HH:mm:ss" /></td>
										<td></td>
										<td> <a
											class="btn yellow btn-sm btn-outline"
											href="${ctx}/admin/wxPublic/edit?id=${item.id}" >编辑</a>
											<a class="btn red btn-sm btn-outline"
											href="javascript:deleteById(${item.id});" >删除</a></td>
										
									</tr>
									<tr  style="display: none;">
									  <td colspan="11">
									   <ul class="table-sub-ul">
									     <li><strong>服务认证地址:</strong>${item.serverUrl} </li>
									     <li><strong>公众号token:</strong>${item.token }</li>
									     <li><strong>encodingAESKey:</strong>${item.encodingaeskey}</li>
									     <li><strong>微信支付商家号:</strong>${item.partnerId}</li>
									     <li><strong>微信支付商家密钥:</strong>${item.partnerKey}</li>
									     <li><strong>支付成功回调地址:</strong>${item.payCallbackUrl}</li>
									     <li><strong>网页授权获取用户信息回调地址:</strong>${item.redirectUrl}</li>
									     <li><strong>最后更新时间:</strong><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></li>
									   </ul>
									  
									  </td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script src="${ctx}/static/js/handle.js"></script>
    <script>
	  function deleteById(id){
	     comm.confirm("提示", "此操作不可逆,您确定要继续进行删除操作吗？", function(){
	     $.post("${ctx}/admin/wxPublic/delete/"+id,function(data){
	        comm.successMsg(data);
	        setTimeout(function(){
	         location.reload();
	        }, 1000)
	     })
	     	
	     })
	  }
	</script>
</body>
</html>