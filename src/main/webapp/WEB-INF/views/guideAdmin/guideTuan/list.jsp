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
</head>

<body>


	<div class="row">
		<div class="col-md-12">
			<!-- BEGIN EXAMPLE TABLE PORTLET-->
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span class="caption-subject font-dark bold uppercase">出团列表</span>
					</div>
					<div class="actions"></div>
				</div>
				<div class="portlet-body">
				<form id="searchForm" action="${ctx}/guideAdmin/tuan" method="post">
	<div class="row">
		<div class="col-md-4">
		<select name="pageSize" class="form-control input-sm input-xsmall input-inline">
		<option value="5" <c:if test="${pageSize == 5}">selected</c:if> >5</option>
		<option value="10" <c:if test="${pageSize == 10}">selected</c:if> >10</option>
		<option value="20" <c:if test="${pageSize == 20}">selected</c:if> >20</option>
		<option value="50" <c:if test="${pageSize == 50}">selected</c:if> >50</option>
		<option value="100" <c:if test="${pageSize == 100}">selected</c:if> >100</option>
		</select>
		<select class="select2me input-sm input-xsmall input-inline" name="tuanStatus">
			<option value="">全部</option>
			<option value="1" <c:if test="${guideTuan.tuanStatus==1}"></c:if> >待出团</option>
			<option value="2" <c:if test="${guideTuan.tuanStatus==2}"></c:if> >已出团</option>
			<option value="3" <c:if test="${guideTuan.tuanStatus==3}"></c:if> >已取消</option>
		</select>
		</div>
		<input type="hidden" name="pageNo" value="1">
		<input type="hidden" name="id" value="${guideTuan.id}">
		
		<div class="col-md-8" style="text-align: right;">
		<input type="text" class="form-filter input-sm" placeholder="线路编号" name="lineNo" value="${guideTuan.lineNo}">
		<input type="text" class="form-filter input-sm" placeholder="出团名称" name="name" value="${guideTuan.name}">	
		<button type="submit" class="btn btn-sm green btn-outline filter-submit margin-bottom">
		<i class="fa fa-search"></i> 查询</button>
			
		</div>		
		
	</div>
	</form>				
					<div class="table-scrollable">
						<table id="tuan" class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th scope="col"><input type="checkbox" class=" group-checkable check-all"></th>
									<th scope="col">出团名称</th>
									<th scope="col">团编号</th>
									<th scope="col">出团日期</th>
									<th scope="col">线路类型</th>
									<th scope="col">线路编号</th>
									<th scope="col">创建时间</th>
									<th scope="col">满员人数</th>
									<th scope="col">实际人数</th>
									<th scope="col">订单数</th>
									<th scope="col">出团状态</th>
									<th scope="col">操作</th>
									
								</tr>
							</thead>
							<tbody>
							
								<c:forEach items="${list}" var="item">
									<tr>
										<td><input type="checkbox" class="group-checkable check-all-item" value="${item.id}"></td>
										<td><a href="${ctx}/guideAdmin/tuan/tuanGuest/${item.tuanNo}">${item.name}</a></td>
										<td>${item.tuanNo}</td>
										<td><fmt:formatDate value="${item.tuanDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${ELineType[item.lineType]}</td>
										<td>${item.lineNo}</td>
										<td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
										<td>${item.fullNum}</td>
										<td>${item.personNum}</td>
										<td>${item.orderNum}</td>
										<td class="smark <c:if test='${item.tuanStatus==1}'>text-danger</c:if>" >${ETuanStatus[item.tuanStatus]} </td>
										
										
										<td>
											<a class="btn blue btn-outline" href="javascript:tour(${item.id},2)">出团</a>
											<a class="btn yellow btn-outline" href="javascript:tour(${item.id},3)">取消</a>
											
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
	
	
	
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	
	
	<script type="text/javascript" src="${ctx}/static/assets/global/plugins/datatables/datatables.js"></script>
	<script>

	$(document).ready(function() {
	    $('#tuan').DataTable({
	    
	    		"paging":   false,
	            "ordering": true,
	            "info":  false,
	            "searching":false
	            }
	    );
	} );
	
	//出团或取消
	function tour(id,status){
		
		comm.confirm("提示","该操作不可逆,确定现在提交吗?",function(){
			
			$.post('${ctx}/guideAdmin/tuan/up/' + id,function(data){
				if(data.result.tuanStatus!="1"){
					comm.infoMsg("该团已出或已取消",null,150);
					return;
				}else{
					$.ajax({
						url:"${ctx}/guideAdmin/tuan/update/"+id+"/"+status,
						dataType:"json",
						type:"get",
						success:function(data){
							location.reload();
						},error:function(){
							comm.infoMsg("出错啦",null,150);
						}
					});
				}
			},"json");	
			
		});
		
	}
	</script>





</body>

</html>