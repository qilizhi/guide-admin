<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统日志列表</title>s
</head>
<body>

	<div class="row">
		<div class="col-md-12">
			<div class="portlet light portlet-fit bordered">
				<div class="portlet-title">
					<div class="caption">
						<i class="fa fa-cogs"></i><span
							class="caption-subject font-dark bold uppercase">系统日记列表</span>
					</div>
				</div>
				<div class="portlet-body">
					<form:form id="searchForm" action="${ctx}/admin/sysBizLog" method="post" modelAttribute="sysBizLog">
						
							<input type="hidden" name="pageNo" value="1">
							<div class="col-md-12" style="margin-bottom:20px;">
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
	<%-- 						<span class="pull-left" style="line-height:30px;margin-right:20px;">操作时间:</span>
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
							 &nbsp; &nbsp; &nbsp; &nbsp; --%>
							 <form:select path="bizType" cssClass="form-control input-sm  input-inline pull-left " items="${fns:BizType()}"/>&nbsp;
							 
								<input type="text" class="form-filter input-sm"
									placeholder="操作人" name="operatPerson" value="${sysBizLog.operatPerson}">
								
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
					</</form:form>
					<div class="table-scrollable">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>业务类型</th>
									<th>操作人编号</th>
									<th>操作人名称</th>
									<th>操作日志</th>
								    <th>操作时间</th> 
								</tr>
							</thead>
							<tbody>

								<c:forEach items="${list}" var="item" >
									<tr>
									   
										<td>${fns:BizType()[item.bizType]}</td>
										<td>${item.operatUserNo}</td>
										<td>${item.operatPerson}</td>
										<td>${item.content}</td>
										<td>
										<fmt:formatDate value="${item.createTime}" pattern="yyy-MM-dd hh:mm:ss"/>
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
	<script src="${ctx}/static/assets/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
	<script src="${ctx}/static/assets/pages/scripts/form-validation.min.js"></script>
	<tg:pagination searchFormId="searchForm" paginator="${paginator}"></tg:pagination>
	<script src="${ctx}/static/js/handle.js"></script>
</body>
</html>