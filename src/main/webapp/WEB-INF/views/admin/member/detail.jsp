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
<title>会员详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="p10">
	<table class="data-table" width="100%" >
	   <tr>
	   <th>美丽行ID:</th>
	   <td>${userInfo.userNo}</td>
	   <th>openID</th>
	   <td>${userInfo.openId}</td>
	    <th>微信公众号ID:</th>
	     <td>${userInfo.weixinPublicId }</td>
	 
	   <tr>
	   <tr>
	    <th>头像:</th>
	     <td><a href="${userInfo.headImgUrl}"><img src="${userInfo.headImgUrl }" width="80px"></a></td>
	    <th>用户昵称:</th>
	   <td>${userInfo.nickName}</td>
	    
	     <th>性别:</th>
	     <td>${userInfo.sex==1?"女":"男" }</td>
	     
	   </tr>
	    <tr>
	     <th>国家:</th>
	     <td>${userInfo.country }</td>
	     <th>省份:</th>
	     <td>${userInfo.province }</td>
	     <th>城市:</th>
	     <td>${userInfo.city}</td>
	   </tr>
	   
	   <tr>
	     <th>用户特权:</th>
	     <td>${userInfo.privilege }</td>
	     <th>语种:</th>
	     <td>${userInfo.language }</td>
	     <th>会员级别:</th>
	     <td>${userInfo.level }</td>
	   </tr>
	   
	   <tr>
	     <th>手机号码:</th>
	     <td>${userInfo.mobile }</td>
	     <th>邮箱:</th>
	     <td>${userInfo.email }</td>
	     <th>用户状态:</th>
	     <td>
	     <c:choose>
			<c:when test="${userInfo.status==1}">
			<a
				class="btn red btn-outline btn-sm "
				id="${userInfo.id }">正常</a>
			</c:when>
			<c:otherwise>
			<a
				class="btn blue btn-outline btn-sm"
				id="${userInfo.id }">冻结</a>
			</c:otherwise>
			</c:choose>
	     
	     </td>
	   </tr>
	    <tr>
	     <th>环信账号:</th>
	     <td>${userInfo.huanxinAccount }</td>
	     <th>环信密码:</th>
	     <td>${userInfo.huanxinPwd }</td>
	     <th>创建时间:</th>
	 <td><fmt:formatDate value="${userInfo.createTime}" pattern="yyyy-MM-dd HH:mm:ss "/></td> 
	   </tr>
	   <tr>
	     <th>更新时间:</th>
	     <td colspan="5">
	  <fmt:formatDate value="${userInfo.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
	  </td>
	     
	   </tr>
	
	</table>
	
	
	</div>	
</body>
</html>