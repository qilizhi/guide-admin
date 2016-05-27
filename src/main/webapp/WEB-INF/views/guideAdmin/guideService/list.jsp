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
<title>地陪</title>
</head>

<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">地陪列表</span>
					</div>
					<div class="actions">
		<a href="${ctx}/guideAdmin/guideService/create"  class="btn btn-sm green btn-outline" >新增</a>
		<!-- <a href="javascript:;" target="deletes" class="btn btn-sm red btn-outline" >删除</a>	 -->				
					</div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/guideService" method="post">
	<div class="row">
		<div class="col-md-4">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<div class="col-md-4"></div>
		<div class="col-md-4" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="地陪标题" name="title" value="${guideService.title}">	
		<input type="text" class="form-filter input-sm" placeholder="地陪编号" name="serviceNo" value="${guideService.serviceNo}">		
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
			
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<!-- <th scope="col"><input type="checkbox" class=" group-checkable check-all"></th> -->
									
									<th scope="col">地陪编号</th>
									<th scope="col">地陪标题</th>
									<th scope="col">背景图</th>
									<!-- <th scope="col">简介</th> -->
									<th scope="col">创建时间</th>
									<th scope="col">价格</th>
									<th scope="col">满员人数</th>
									<!-- <th scope="col">体验说明</th> -->
									<th scope="col">地陪状态</th>
									<th scope="col">审核说明</th>
									<th scope="col">审核时间</th>
									<th scope="col">审核状态</th>
									<th scope="col">排序号</th>
									<th scope="col">操作</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<%-- <td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td> --%>
										<td>${item.serviceNo}</td>
										<td>${item.title}</td>
										<td><img src="${item.imgUrl}" title="${item.title}" height="50px" width="50px" /></td>
										<%-- <td>${item.description}</td> --%>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.price}</td>
										<td>${item.num}</td>
										<%-- <td>${item.remark}</td> --%>
										<td>${EStatus[item.status]}</td>
										<td>${item.auditRemark}</td>
										<td><fmt:formatDate value="${item.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${EAuditStatus[item.auditStatus]}</td>
										<td>${item.sort}</td>
										<td>															
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/guideService/edit/${item.id}">编辑</a>
											<a class="btn blue btn-outline" href="javascript:upShow(${item.id})">上线</a>
											<a class="btn yellow btn-outline" href="${ctx}/guideAdmin/guideService/detail/${item.id}">预览</a>
											<input type="hidden" name="serviceNo" value="${item.serviceNo}"/>
											
										</td>
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
	
	<!-- 修改上线下线状态 -->
	<div id="detailResponsive"
		class="modal fade draggable-modal ui-draggable" tabindex="-1"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<form action="${ctx}/guideAdmin/guideService/upAndDown" id="modalForm" class="form-horizontal">
				<input type="hidden" name="id" value=""/>
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
								
								<div class="form-group  margin-top-20">
									<label class="control-label col-md-3">上线</label>
									<div class="col-md-3">
										<input type="radio" name="status" value="2" style="margin-left: -10px;" checked="checked">
									</div>
									
									<label class="control-label col-md-3">下线</label>
									<div class="col-md-3">
										<input type="radio" name="status" value="3" style="margin-left: -10px;">
									</div>
								</div>
								
						
						<!-- 	</div> -->
							<!-- END FORM-->
						</div>
						<!-- END VALIDATION STATES-->
					</div>
					<div class="modal-footer">
						<button type="button" data-dismiss="modal"
							class="btn dark btn-outline">关闭</button>
						<button type="submit" class="btn green">提交</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- end -->
	
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	
	
	<script>
		window.mlx = {
			ctx : "${ctx}"
		};
		

		//上线
		function upShow(id) {
			//将id存起传给后台
			 var lineId=id;
			$("input[name='id']").val(lineId); 
			//上线前检查该线路是否已通过财务审核
			 $.ajax({
		    	url:"${ctx}/guideAdmin/guideService/up/"+id,
		    	dataType:"json",
		    	type:"get",
		    	success:function(data){
		    		console.log(data);  
		    		
		    		//判断是否已通过审核
		    		if(data.result.auditStatus==2){
		    			$("#detailResponsive").modal('show');
		    		}else{
		    			comm.showMsg('warning', '消息提示', '未审核，暂不能上线！');
		    		}
		    		
		    		
		    		
		    	},error:function(){
		    		comm.showMsg('warning', '消息提示', '请求出错！');
		    	}
		    });
		    
		}
		
	</script>





</body>

</html>