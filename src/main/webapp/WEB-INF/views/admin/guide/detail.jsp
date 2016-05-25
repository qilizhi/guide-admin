<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>会员详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row">
	<div class="col-xs-6 ">
	<table class="data-table"  >
	   <tr>
	   <th>编号:</th>
	   <td>${guideInfo.userNo }</td>
	   <th>姓名:</th>
	   <td>${guideInfo.realName}</td>
	   
	   </tr>
	    <tr>
	    <th>级别:</th>
	   <td>${guideInfo.guideLevel }</td>
	   <th>年纪:</th>
	   <td>${guideInfo.age }</td>
	   </tr>
	   <tr>
	   <th>签名:</th>
	   <td>${guideInfo.sign }</td>
	   <th>标签:</th>
	   <td>${guideInfo.tag }</td>
	   </tr>
	   
	   <tr>
	   <th>身份证号:</th>
	   <td>${guideInfo.idCard }</td>
	   <th>审核状态:</th>
	   <td>${fns:EAuditStatus()[guideInfo.auditStatus]} </td>
	   <th>状态:</th>
	   <td>${fns:EUserStatus()[guideInfo.status]} </td>
	   </tr>
	   <tr>
	   <th>工作年限:</th>
	   <td>${guideInfo.workYear }</td>
	   
	   <th>导游证件号:</th>
	   <td>${guideInfo.guideCardNo }</td>
	   <th>简介:</th>
	   <td>${guideInfo.intro }</td>
	   </tr>
	   
	
	   <tr>
	   <th>背景图:</th>
	   <td><a href="${guideInfo.bgImgUrl}"><img src="${guideInfo.bgImgUrl }" width="120px" alt="加载中.." title="背景图"/></a></td>
	   <th>身份证正面:</th>
	   <td><a href="${guideInfo.idCardFrontPic}"><img src="${guideInfo.idCardFrontPic }" width="120px" alt="加载中.." title="身份证正面"/></a></td>
	   <th>身份证反面:</th>
	   <td><a href="${guideInfo.idCardSidePic}"><img src="${guideInfo.idCardSidePic }" width="120px" alt="加载中.." title="身份证正面"/></a></td>
	   </tr>
	</table>
	
	<div class="text-center">
	
	
	</div>
	</div>
	<div class="col-xs-6">
	23423
	</div>
	
	</div>
	
</body>
</html>