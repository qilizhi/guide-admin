<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${empty guideInfo.isAuditStatus?'导游列表':'导游申请' }</title>
  <link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
 
</head>
<body>
	<div class="row">
		<div class="col-md-12">

			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">${empty guideInfo.isAuditStatus?'导游列表':'导游申请列表' }</span>		
					</div>
				</div>
				<div class="portlet-body">
					<form:form id="searchForm" action="${ctx}/admin/guideUserInfo/list" modelAttribute="guideInfoModel" method="post" >
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
								
								<input type="hidden" name="pageNo" value="1">
								<input type="hidden" name="isAuditStatus" value="${guideInfo.isAuditStatus}">
		<div class="pull-right">
		           <c:if test="${empty guideInfo.isAuditStatus }">	        
		          <form:select path="auditStatus" class="form-control input-sm  input-inline pull-left">
		            <form:option value="">审核状态</form:option>
		           <form:option  value="1" >待审核</form:option>
	              <form:option value="2" >已通过</form:option>
	               <form:option value="3" >不通过</form:option>     
		          </form:select>
		           </c:if>
	      
          
         <input type="text" class="form-filter input-sm pull-left ml10" placeholder="姓名" name="realName" value="${guideInfo.realName }">
		 <input type="text" class="form-filter input-sm pull-left ml10" placeholder="导游证" name="guideCardNo" value="${guideInfo.guideCardNo }">
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		
		<i class="fa fa-search"></i>  搜索</button>
		
		<button type="button" onclick="formReset();" class="btn btn-sm green btn-outline filter-submit margin-bottom pull-left ml10">
		<i class="fa fa-mail-reply-all"></i> 重置</button>
		</div>
		
							</div>
							</div>
					</form:form>
				    <div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover text-center">
							<thead>
								<tr>
								<th></th>
									<th >编号</th>
									<th >真实姓名</th>
									<th>导游证号</th>
									<th >导游等级</th>
									<th >创建时间</th>
									<th >审核状态</th>
									
									<c:if test="${empty guideInfo.isAuditStatus }">
									<th>状态</th>		
									</c:if>						
									<th >操作</th>
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" varStatus="status">
									<tr>
									    <td class="add" onclick="toggleDetail(this)"></td>
										<td>${item.userNo}</td>
										<td>${item.realName}</td>			
										<td>${item.guideCardNo}</td>
										<td>${item.guideLevel}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${fns:EAuditStatus()[item.auditStatus]}</td>
										
										<c:if test="${empty guideInfo.isAuditStatus}">
										<td>${fns:EUserStatus()[item.status]}</td>
										</c:if>
										<td>
										  <c:if test="${empty guideInfo.isAuditStatus}">
											 <a href="${ctx}/admin/guideUserInfo/detail?userNo=${item.userNo}" class="btn green btn-outline btn-sm" >详情</a>	 
						                   </c:if>	
						                 <c:if test="${ not empty guideInfo.isAuditStatus }">	
										<a href="javascript:showModal(${item.id},2)"  class="btn blue btn-outline btn-sm "  >审核</a>
										 </c:if>
										 <c:if test="${empty guideInfo.isAuditStatus}">
										<c:choose>										
										<c:when test="${item.status==1}">
										<a href="javascript:editStatus(${item.id},2)"  class="btn red btn-outline btn-sm "  >冻结</a>
										</c:when>
										<c:otherwise>
										<a href="javascript:editStatus(${item.id},1)"  class="btn green btn-outline btn-sm "  >解禁</a>	
										</c:otherwise>
										</c:choose>	
										 </c:if>											
										</td>
									</tr>
									<tr  style="display: none;">
									  <td colspan="11">
									   <ul class="table-sub-ul">
									     <li><strong>年纪:</strong>${item.age} </li>
									     <li><strong>签名:</strong>${item.sign }</li>
									     <li><strong>标签:</strong>${item.tag}</li>
									     <li><strong>身份证:</strong>${item.idCard}</li>
									     <li><strong>工作年限:</strong>${item.workYear}</li>
									     <li><strong>背景图:</strong><img src="${item.bgImgUrl }"  data-preview='true' width="120px" alt="加载中.." title="背景图"/>
									                     <strong>身份证正面:</strong><img src="${item.idCardFrontPic }"  data-preview='true' width="120px" alt="加载中.." title="身份证正面"/>
									           <strong>身份证反面:</strong><img src="${item.idCardSidePic }"  data-preview='true' width="120px" alt="加载中.." title="身份证正面"/>
									     </li>
									     <li><strong>简介:</strong>${item.intro }</li>
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
	

	<div id="responsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form  id="myform">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
						<div class="portlet-body">
							<div class="form-body " style="height: 40px;">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
								</div>
								<input type="hidden" name="id" id="guideUserInfoId" />
								<!--审核  -->
								<div class="form-group  margin-top-20"  id="my_auditStatus" style="margin-left:120px;">
									<label class=" col-md-3" for="auditStatus2">通过
										<input type="radio" id="auditStatus2" name="auditStatus" value="2"   checked="checked"/>
									</label>
									
									<label class=" col-md-3" for="auditStatus3" style="margin-left:100px">不通过
										<input type="radio" id="auditStatus3" name="auditStatus" value="3"   />
									</label>
								</div>	
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="button" data-dismiss="modal" class="btn green" onclick="editAuditStatus()">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script src="${ctx}/static/js/handle.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script>
	      //弹出详情模态框
          function showModal(id,status){
           guideUserInfoId.value=id;
          	  $("#responsive").modal();
          	};
        //更改审核状态
           function editAuditStatus(auditStatus){
              $.post("${ctx}/admin/guideUserInfo/edit",$("#myform").serialize(),function(data){
                       comm.successMsg(data);
                        setTimeout(function(){
						         location.reload();
						       }, 1000)
                }); 
           }	
          //更改状态
          function editStatus(id,status){
           $.post("${ctx}/admin/guideUserInfo/edit",{"id":id,"status":status},function(data){
                      comm.successMsg(data);
                        setTimeout(function(){
						         location.reload();
						       }, 1000)
                }); 
          }
          	
          	
 	</script>
</body>
</html>