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
<title>导游故事</title>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="row">
		<div class="col-md-12">
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">导游故事列表</span>
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/admin/guideIntro/list" method="post">
	<div class="row">
		<div class="col-md-12">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline ">
		
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		
		<input type="hidden" name="pageNo" value="1">
		<div class="pull-right">
		<span class="pull-left" style="line-height:30px;margin-right:20px;">上线时间:</span>
		<div class="input-group date date-picker pull-left"  data-date-format="yyyy-mm-dd"  style="width:200px">
              <input type="text" class="form-control form-filter input-sm" readonly="readonly" placeholder="开始时间" name="startTime" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${guideIntroModel.startTime}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar"></i>
                  </button>
              </span>
          </div>
          <span class="pull-left" style="margin-top:6px;">—</span>
          <div class="input-group date date-picker pull-left" data-date-format="yyyy-mm-dd" style="width:200px">
              <input type="text" class="form-control form-filter input-sm " readonly="readonly"  placeholder="结束时间" name="endTime" aria-required="true" aria-invalid="false" aria-describedby="datepicker-error" value="${guideIntroModel.endTime}">
              <span class="input-group-btn">
                  <button class="btn default" type="button" style="line-height: 17px;">
                      <i class="fa fa-calendar "></i>
                  </button>
              </span>
          </div>
          
         <input type="text" class="form-filter input-sm pull-left ml10" placeholder="标题" name="title" value="${guideIntroModel.title }">
		 <input type="text" class="form-filter input-sm pull-left ml10" placeholder="作者" name="userName" value="${guideIntroModel.userName }">
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
									<th>标题</th>
									<th>图片</th>
									<th>作者</th>
									<th>创建时间</th>
									<th>上线时间</th>
									<th>排序</th>
									<th>审核状态</th>
									<th>状态</th>
								    <th>操作</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
									    <td class="add" onclick="toggleDetail(this)"></td>
										<td>${item.title }</td>
										<td><a href="${item.imgUrl }" title="背景图片" ><img src="${item.imgUrl }" alt="加载中.." width="80px"></a></td>
										<td>${item.userName}</td>
										<td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.sort }</td>
										<td>
										${fns:EAuditStatus()[item.auditStatus]}</td>
										<td>${fns:EStatus()[item.status]}</td>
										<td>
										<a href="javascript:showModal(${item.id })"  class="btn blue btn-outline btn-sm">审核</a>
										
									<c:choose>
									<c:when test="${item.status==2}">
									<a href="javascript:showModal2(${item.id},${item.auditStatus},3)"  class="btn red btn-outline btn-sm" >下线</a>
									</c:when>
									<c:otherwise>
									<a href="javascript:showModal2(${item.id},${item.auditStatus},2)"  class="btn green  btn-outline btn-sm" >上线</a>
									</c:otherwise>
									</c:choose>
										<%-- <a href="javascript:showModal2(${item.id},${item.auditStatus},${item.status })"  class="btn ${item.status==2?'red':'green'}  btn-outline btn-sm" >${item.status==2?'下线':'上线' }</a> --%>
										
									<%-- 	<a href="javascript:showModal2(${item.id})" class="btn blue btn-outline btn-sm">下线</a>		 --%>			
										<a href="javascript:void(0);"  class="btn blue btn-outline btn-sm" >预览</a>
										</td>
									</tr>
									<tr  style="display: none;">
									  <td colspan="11">
									   <ul class="table-sub-ul">
									     <li><strong>用户编号:</strong>${item.userNo} </li>
									     <li><strong>是否有效:</strong>${item.flag==0?'无效':'有效' }</li>
									     <li><strong>推荐理由:</strong>${item.recommendInfo}</li>
									     <li><strong>审核说明:</strong>${item.auditRemark}</li>
									     <li><strong>描述:</strong>${item.description}</li>
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
	<!--审核  -->
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
								<input type="hidden" name="id" id="guideIntroId" />
								<!--审核  -->
								<div class="form-group  margin-top-20"  id="my_auditStatus" style="margin-left:120px;">
									<label class="control-label col-md-3" for="auditStatus2">通过
										<input type="radio" id="auditStatus2" name="auditStatus" value="2"   checked="checked"/>
									</label>
									
									<label class="control-label col-md-3" for="auditStatus3" style="margin-left:100px">不通过
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
	
	<!--上线  -->
	<div id="responsive2"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form  id="myform2">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-hidden="true"></button>
					</div>
						<div class="portlet-body">
							<div class="form-body " style="height: 40px;">
								<div class="alert alert-danger display-hide">
									<button class="close" data-close="alert"></button>
								</div>
								<input type="hidden" name="id" id="guideIntroId2" />
							
								
								<div class="form-group  margin-top-20" id="my_status" style="margin-left:120px;">
									<label class="control-label col-md-3" for="status2">上线
										<input type="radio" name="status" value="2" id="status2"  checked="checked">
									</label>
									
									<label class="control-label col-md-3" for="status3" style="margin-left:100px;">下线
										<input type="radio" name="status" id="status3" value="3"  >
									</label>
								</div>
								
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="button" data-dismiss="modal" class="btn green" onclick="editStatus()">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script
		src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"
		type="text/javascript"></script>
	
	
	<script src="${ctx}/static/assets/pages/scripts/form-validation.min.js"></script>
	<script src="${ctx}/static/js/handle.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"/>
	
	<script>
	     //弹出审核模态框
	     function showModal(id){
	     $("#responsive").modal();
	       guideIntroId.value=id;
	      
	     }
	     
	     //修改审核状态
	     function editAuditStatus (){
	      	    
	     $.post("${ctx}/admin/guideIntro/edit",$("#myform").serialize(),function(data){
	        comm.successMsg(data);
	        setTimeout(function(){
	         location.reload();
	        }, 1500)
	     
	     }) 
	     }
	     //弹出状态模态框
	     function showModal2(id,auditStatu,status){    
	     if(auditStatu!=2){
	        comm.warningMsg("审核通过后才能操作");
	        return;
	     }
	     comm.confirm("提示", "确定执行该操作吗?", function(){
	      $.post("${ctx}/admin/guideIntro/edit",{"id":id,"status":status},function(data){
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