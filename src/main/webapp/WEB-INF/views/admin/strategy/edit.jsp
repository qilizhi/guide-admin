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
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${operaTitle}</title>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/ueditor.all.min.js"> </script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="${ctx }/static/assets/global/plugins/UEeditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body>
	<h4 class="modal-title" id="exampleModalLabel">${operaTitle}：</h4>
	<form action="${ctx}/admin/guideStrategy/save" id="inputForm"
		method="post" class="form-horizontal mlx-form">
		<input type="hidden" id="id" name="id" value="${guideStrategy.id}" />
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">标题：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="title" id="title"
					value="${guideStrategy.title}"  placeholder="这里输入标题" />
				<div class="form-control-focus"></div>
			</div>
		</div>
		<div class="form-group form-md-line-input">
			<label class="col-lg-3 control-label">描述：</label>
			<div class="col-lg-5">
				<textarea class="form-control" name="description" id="description"
					value="${guideStrategy.description}" rows="3" placeholder="这里添加描述" ></textarea>
				<label for="form_control_1"></label>
			</div>

		</div>

		
		<div class="form-group">
			<label class="col-lg-3 control-label">背景图片：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="imgUrl" id="imgUrl"
					value="${guideStrategy.imgUrl}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">推荐理由：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="recommendInfo"
					id="recommendInfo" value="${guideStrategy.recommendInfo}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">关联的线路编号：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="relatLineNo"
					id="relatLineNo" value="${guideStrategy.relatLineNo}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">用户编号：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="userNo" id="userNo"
					value="${guideStrategy.userNo}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">用户名称：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="userName"
					id="userName" value="${guideStrategy.userName}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">审核说明：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="auditRemark"
					id="auditRemark" value="${guideStrategy.auditRemark}" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">审核时间：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="auditTime"
					id="auditTime"
					value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${guideStrategy.auditTime}"/>" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">攻略内容文本：</label>
			<div class="col-lg-5">
			<%-- 	<input type="text" class="form-control " name="content" id="content"
					value="${guideStrategy.content}" /> --%>
					<script id="editor" type="text/plain" name="content"  style="width:600px;height:500px;"></script>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">审核状态：</label>
			<div class="col-lg-5">
				<select class="form-control " name="auditStatus" id="auditStatus"
					value="${guideStrategy.auditStatus}">
					<c:forEach items="${auditStatusMap}" var="auditStatus">
						<option value="${auditStatus.key}"
							<c:if test="${guideStrategy.auditStatus == auditStatus.key}">selected</c:if>>${auditStatus.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">创建时间：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="createTime"
					id="createTime"
					value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${guideStrategy.createTime}"/>" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-lg-3 control-label">更新时间：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control " name="updateTime"
					id="updateTime"
					value="<fmt:formatDate pattern="yyyy-MM-dd, HH:mm:ss" value="${guideStrategy.updateTime}"/>" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">状态：</label>
			<div class="col-lg-5">
				<select class="form-control required" name="status" id="status"
					value="${guideStrategy.status}">
					<c:forEach items="${statusMap}" var="status">
						<option value="${status.key}"
							<c:if test="${guideStrategy.auditStatus == status.key}">selected</c:if>>${status.value}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-lg-3 control-label">排序号：</label>
			<div class="col-lg-5">
				<input type="text" class="form-control" name="sort" id="sort"
					value="${guideStrategy.sort}" />
			</div>
		</div>
		<div class="form-group">
			<div class="col-lg-9 col-lg-offset-3">
				<input id="submit_btn" type="submit" data-loading-text="提交中..."
					autocomplete="off" class="btn btn-primary" value="提交" /> <input
					id="cancel_btn" class="btn btn-primary " type="button" value="返回"
					onclick="history.back()" />
			</div>

		</div>
		
	</form>
	<script type="text/javascript">
	$(function(){
		window.UEDITOR_HOME_URL ="${ctx}";
		 UE.getEditor('editor');
		
	})
	
	</script>
</body>
</html>