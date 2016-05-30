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
<title>会员列表</title>
  <link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">

			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">会员列表</span>		
					</div>
				</div>
				<div class="portlet-body">
					<form id="searchForm" action="${ctx}/admin/member/list" method="post">
						<div class="row">
						
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-12">
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
	
          
         <input type="text" class="form-filter input-sm pull-left ml10" placeholder="昵称" name="nickName" value="${userInfo.nickName }">
		 <input type="text" class="form-filter input-sm pull-left ml10" placeholder="手机号码" name="mobile" value="${userInfo.mobile }">
				 <input type="text" class="form-filter input-sm pull-left ml10" placeholder="邮箱" name="email" value="${userInfo.email }">
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		
		<i class="fa fa-search"></i>  搜索</button>
		
		<button type="button" onclick="formReset();" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		<i class="fa fa-mail-reply-all"></i> 重置</button>
		</div>
							</div>

							</div>
					</form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover text-center">
							<thead>
								<tr>
								    <th></th>
									<th width="70px">用户编号</th>
									<th >头像</th>
									<th >昵称</th>
									<th>openId</th>
									<th width="40px">性别</th>
									<th >级别</th>
								    <th >手机</th>
								    <th >邮箱</th>
								    <th >创建时间</th>
								    <th>更新时间</th>
								    <th width="40px">状态</th>
									<th >操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="status">
									<tr>
									   
									     <td class="add" onclick="toggleDetail(this)"></td>
										 <td>${item.userNo}</td>
										 <td><img src="${item.headImgUrl}" data-preview="true" width="50px"></td>
										<td>${item.nickName}</td>
										<td>${item.openId}</td>
										<td>
										   <c:choose>
										    <c:when test="${item.sex==1}">
										       女 
										    </c:when>
										    
										     <c:when test="${item.sex==2}">
										        男
										    </c:when>
										    <c:otherwise>
										     未填
										    </c:otherwise>
										   </c:choose>
										</td>
										<td>${item.level}</td>
									
										<td>${item.mobile}</td>
										<td>${item.email}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
										<td><fmt:formatDate value="${item.updateTime}" pattern="yyyy-MM-dd HH:mm:ss" />
									</td>
										<td>
										<c:choose>
										<c:when test="${item.status==1}">
										 正常
										</c:when>
										<c:otherwise>
										 冻结
										</c:otherwise>
									
										</c:choose>
										</td>
											<td> 
										<c:choose>
										<c:when test="${item.status==1}">
										<a
											class="btn red btn-outline btn-sm "
											 onclick="handle(${item.id },2)" >冻结</a>
										</c:when>
										<c:otherwise>
										<a
											class="btn blue btn-outline btn-sm"
											 onclick="handle(${item.id },1)">解禁</a>
										</c:otherwise>
										</c:choose>
										</td>
									</tr>
									
									<tr  style="display: none;">
									  <td colspan="11">
									   <ul class="table-sub-ul">
									     <li><strong>位置:</strong>${item.country}${item.province} ${item.city }</li>
									     <li><strong>特权信息:</strong>${item.privilege }</li>
									     <li><strong>语种:</strong>${item.language }</li>
									     <li><strong> 环信账号:</strong>${item.huanxinAccount}</li>
									     <li><strong>环信密码:</strong>${item.huanxinPwd}</li>
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
	
	
	
	
	
	<div id="responsive" class="modal fade draggable-modal ui-draggable"
		tabindex="-1" aria-hidden="true">
		<div class="modal-dialog" style="width:1000px">
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
								</div>
								<iframe id="myframe"  src="" width="100%" height="400px" marginwidth="0" marginheight="0" frameborder="0" scrolling="auto"   ></iframe>
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
	
	<script src="${ctx}/static/js/handle.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script>
	      
          
        //冻结，解冻操作
           function handle(id,status){
              $.post("${ctx}/admin/member/edit",{"id":id,"status":status},function(data){
                       comm.successMsg(data);
                       setTimeout(function(){
                        location.reload();
                       }, 1000)
                }); 
           }	
      
          	
        
        
          	
 	</script>
</body>
</html>