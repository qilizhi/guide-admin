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
<title>导游详情</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/static/assets/layouts/layout5/css/layout.min.css" type="text/css">
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
<link href="${ctx }/static/assets/layouts/layout5/css/profile.min.css" rel="stylesheet" type="text/css"/>
</head>
<body>
<div class="row">
	<div class="page-content" style="min-height:956px;padding-top:0px;">
                    <div class="theme-panel hidden-xs hidden-sm">
                    <div class="row">
                        <div class="col-md-12">
                            <div class="profile-sidebar">
                                <div class="portlet light profile-sidebar-portlet ">

                                    <div class="profile-userpic">
                                        <img src="${guideInfo.bgImgUrl }" class="img-responsive" data-preview="true" alt=""> </div>
                                  
                                    <div class="profile-usertitle">
                                        <div class="profile-usertitle-name"> ${guideInfo.realName } </div>
                                        <div class="profile-usertitle-job">签名: ${guideInfo.sign } </div>
                                    </div>
                                  
                                    <div class="profile-userbuttons">
                                        <button type="button" class="btn btn-circle green btn-sm">等级:${guideInfo.guideLevel }</button>
                                        <button type="button" class="btn btn-circle red btn-sm">年纪:${guideInfo.age }</button>
                                    </div>
                                  
                                    <div class="profile-usermenu">
                                        <ul class="nav">
                                            <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>标签：${guideInfo.tag } </a>
                                            </li>
                                            <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>导游证：${guideInfo.guideCardNo } </a>
                                            </li>
                                            <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>身份证：${guideInfo.idCard } </a>
                                            </li>
                                            <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>审核状态：${fns:EAuditStatus()[guideInfo.auditStatus]}</a>
                                            </li>
                                             <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>状态：${fns:EUserStatus()[guideInfo.status]}</a>
                                            </li>
                                            <li >
                                                <a >
                                                    <i class="fa fa-circle-o"></i>注册时间：<fmt:formatDate value="${guideInfo.createTime}"  pattern="yyyy-MM-dd HH:mm:ss"/></a>
                                            </li>
                                        </ul>
                                    </div>
                                </div>
                                <div class="portlet light ">
                                    <div class="row list-separated profile-stat">
                                        <div class="col-md-4 col-sm-4 col-xs-6">
                                            <div class="uppercase profile-stat-title"> ${guideLineList.size() } </div>
                                            <div class="uppercase profile-stat-text"> 线路数量 </div>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-6">
                                            <div class="uppercase profile-stat-title"> ${guideServiceList.size() } </div>
                                            <div class="uppercase profile-stat-text"> 地陪数量 </div>
                                        </div>
                                        <div class="col-md-4 col-sm-4 col-xs-6">
                                            <div class="uppercase profile-stat-title"> ${guideStrategyList.size() } </div>
                                            <div class="uppercase profile-stat-text"> 攻略数量 </div>
                                        </div>
                                    </div>
                                 
                                    <div class="pb20">
                                        <h4 class="profile-desc-title ">简介:</h4>
                                        <small class="profile-desc-text"> ${guideInfo.intro }</small>
                                        
                                    </div>
                                    <div>
                                      <span class="profile-desc-title">身份证正面:</span>
                                      <img src="${guideInfo.idCardFrontPic }" class="img-thumbnail"  data-preview="true" title="身份证正面" alt="加载中.." width="150px"/>
                                     </div>
                                     <div>
                                      <span class="profile-desc-title">身份证反面:</span>
                                     <img src="${guideInfo.idCardSidePic }" class="img-thumbnail" data-preview="true" title="身份证反面" alt="加载中.." width="150px"/>
                                    </div>
                                </div>
                            </div>
                            <div class="profile-content">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="portlet light ">
                                            <div class="portlet-title tabbable-line">
                                                <div class="caption caption-md">
                                                    <i class="icon-globe theme-font hide"></i>
                                                    <span class="caption-subject font-blue-madison bold uppercase"></span>
                                                </div>
                                                <ul class="nav nav-tabs">
                                                    <li class="active">
                                                        <a href="#tab_1_1" data-toggle="tab" aria-expanded="true">故事</a>
                                                    </li>
                                                    <li class="">
                                                        <a href="#tab_1_2" data-toggle="tab" aria-expanded="false">线路</a>
                                                    </li>
                                                    <li class="">
                                                        <a href="#tab_1_3" data-toggle="tab" aria-expanded="false">地陪</a>
                                                    </li>
                                                    <li class="">
                                                        <a href="#tab_1_4" data-toggle="tab" aria-expanded="false">攻略</a>
                                                    </li>
                                                </ul>
                                            </div>
                                            <div class="portlet-body">
                                                <div class="tab-content">
                                                <!--故事  -->
                                                    <div class="tab-pane active" id="tab_1_1">
                                                    <c:if test="${empty guideIntro }"><div class="text-center text-danger">没有相关数据</div></c:if>
                                                        <div id="accordion1" class="panel-group">
                                                        <div class="panel panel-success">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion2" href="#accordion1_1">标题: ${guideIntro.title } </a>
                                                                    </h4>
                                                                </div>
                                                           <div id="accordion1_1" class="panel-collapse collapse  in ">
                                                           <div class="panel-body">
                                                           <div class="text-center">  
                                            <strong style="position: relative;top:-60px;">背景图:</strong>  <img src="${guideIntro.imgUrl}" class="img-thumbnail" data-preview="true" alt="" width="150px"/> </div>
                                                          <table class="table mt10 my-table">
                                                            <tr>
                                                            <th>标题:</th>
                                                            <td>${guideIntro.title}</td>
                                                            <tr>
                                                            <tr>
                                                              <th>上线时间:</th>
                                                              <td><fmt:formatDate value="${guideIntro.updateTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                              
                                                               <th>创建时间:</th>
                                                              <td><fmt:formatDate value="${guideIntro.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                            </tr>
                                                            <tr>
                                                              <th>审核时间:</th>
                                                              <td><fmt:formatDate value="${guideIntro.auditTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                                              <th>审核说明:</th>
                                                              <td>${ guideIntro.auditRemark}</td>
                                                            </tr>
                                                            <tr>
                                                              
                                                              <th>审核状态:</th>
                                                              <td>${fns:EAuditStatus()[guideIntro.auditStatus]}</td>
                                                              <th>状态:</th>
                                                              <td>
                                                              <c:if test="${guideIntro.status==0}">待上线</c:if>
                                                              ${fns:EStatus()[guideIntro.status]}</td>
                                                            </tr>
                                                            
                                                            
                                                            
                                                            <tr>
                                                              <th>推荐理由:</th>
                                                            <td colspan="5">${guideIntro.recommendInfo }</td>
                                                            </tr>
                                                            
                                                            <tr>
                                                            <th>描述:</th>
                                                            <td colspan="5">${guideIntro.description }</td>
                                                            </tr>
                                                            <tr>
                                                            <th>
                                                                                                                                                                         故事内容文本:
                                                            </th>
                                                            <td colspan="5">
                                                            ${guideIntro.content}
                                                            </td>
                                                            </tr>
                                                            
                                                          </table> 
                                                          </div>
 
                                                            </div>
                                                           </div>
                                                            
                                                        </div>
                                                    </div>
                                                  <!--线路  -->
                                                    <div class="tab-pane" id="tab_1_2">
                                                      <c:if test="${empty guideLineList }"><div class="text-center text-danger">没有相关数据</div></c:if>
                                                        <div id="accordion2" class="panel-group">
                                                        <c:forEach var="item" varStatus="status" items="${guideLineList}">
                                                            <div class="panel panel-success">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion2" href="#accordion2_${status.count }">${status.count }. ${item.title } </a>
                                                                    </h4>
                                                                </div>
                                                                <div id="accordion2_${status.count }" class="panel-collapse collapse  ${status.count==1?'in':''} ">
                                                                    <div class="panel-body">
                                                                        
                                                                        <div class="text-center">  
                                                          <strong style="position: relative;top:-60px;">背景图:</strong>  <img src="${item.imgUrl}" class="img-thumbnail" data-preview="true" alt="加载中" width="150px"/> </div>
                                                                        
                                                                        <table class="table my-table">
                                                                        <th>标题:</th>
                                                            <td>${item.title }</td>
                                                                         <tr>
                                                                           <th>线路类型</th>
                                                                           <td>
                                                                           <c:if test="${item.lineType==1 }">国内</c:if>
                                                                           <c:if test="${item.lineType==2 }">出境</c:if>
                                                                           </td>
                                                                           <th>线路编号</th>
                                                                           <td>
                                                                           ${item.lineNo} 
                                                                           </td>
                                                                          
                                                                         </tr>
                                                                            <tr>
                                                                             
                                                                              <th>价格</th>
                                                                             <td>${item.price }</td>
                                                                             <th>满员人数</th>
                                                                             <td>${item.num }</td>
                                                                            </tr>
                                                                            <tr>
                                                                            
                                                                              <th>是否有效</th>
                                                                             <td><c:if test="${item.flag==1 }" >有效</c:if>
                                                                             <c:if test="${item.flag==0 }" >无效</c:if>
                                                                             </td>
                                                                             <th>状态</th>
                                                                             <td>${fns:EStatus()[item.status]}</td>
                                                                             
                                                                            </tr>
                                                                            <tr>
                                                                            
                                                                              <th>审核时间</th>
                                                                             <td><fmt:formatDate value="${item.auditTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                             <th>审核状态</th>
                                                                             <td>${fns:EAuditStatus()[item.auditStatus]}</td>
                                                                             
                                                                            </tr>
                                                                            <tr>
                                                                             <th>创建时间</th>
                                                                             <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                              <th>更新时间</th>
                                                                             <td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                             
                                                                             
                                                                            </tr>
                                                                            <tr>
                                                                            
                                                                             <th>体验说明</th>
                                                                             <td colspan="5">${item.remark}</td>
                                                                            </tr>
                                                                       
                                                                            <tr>
                                                                             <th>审核说明</th>
                                                                             <td colspan="5">${item.auditRemark}</td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                             <th>线路简介</th>
                                                                           <td colspan="5">
                                                                           ${item.description} 
                                                                           </td>
                                                                            </tr>
                                                                             <tr>
                                                                             <th>线路内容文本</th>
                                                                             <td colspan="5">${item.content }</td>
                                                                            </tr>
                                                                            
                                                                            
                                                                        </table>
                                                                        
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </c:forEach>
                                                        </div>
                                                    </div>
                                                    <!-- 地陪  -->
                                                    <div class="tab-pane" id="tab_1_3">
                                                    <c:if test="${empty guideServiceList }"><div class="text-center text-danger">没有相关数据</div></c:if>
                                                        <div id="accordion3" class="panel-group">
                                                           <c:forEach var="item" varStatus="status" items="${guideServiceList }">
                                                            <div class="panel panel-success">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion3" href="#accordion3_${status.count }"> ${status.count }. ${item.title } </a>
                                                                    </h4>
                                                                </div>
                                                                <div id="accordion3_${status.count }" class="panel-collapse collapse ${status.count==1?'in':'' }">
                                                                    <div class="panel-body">
                                                                        <div class="text-center">  
                                                          <strong style="position: relative;top:-60px;">背景图:</strong>  <img src="${item.imgUrl}" class="img-thumbnail" data-preview="true" alt="加载中" width="150px"/> </div>
                                                                   
                                                                   <table class="table my-table">
                                                                          <tr>
                                                                           <th>标题</th>
                                                                           <td colspan="6">${item.title }</td>
                                                                          </tr>
                                                                         
                                                                         
                                                                            <tr>
                                                                              <th>价格</th>
                                                                             <td>${item.price }</td>
                                                                             <th>满员人数</th>
                                                                             <td>${item.num }</td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                              <th>是否有效</th>
                                                                             <td><c:if test="${item.flag==1 }">有效</c:if>
                                                                             <c:if test="${item.flag==0 }" >无效</c:if>
                                                                             </td>
                                                                             <th>状态</th>
                                                                             <td>${fns:EStatus()[item.status]}</td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                              <th>审核时间</th>
                                                                             <td><fmt:formatDate value="${item.auditTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                             <th>审核状态</th>
                                                                             <td>${fns:EAuditStatus()[item.auditStatus]}</td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                             <th>创建时间</th>
                                                                             <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                              <th>更新时间</th>
                                                                             <td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                            </tr>
                                                                            <tr>
                                                                            <th>审核说明</th>
                                                                             <td colspan="5">${item.auditRemark}</td>
                                                                            </tr>

                                                                            <tr>
                                                                             <th>体验说明</th>
                                                                             <td colspan="5">${item.remark}</td>
                                                                            </tr>
                                                                            
                                                                            <tr>
                                                                            <th>地陪简介</th>
                                                                            <td colspan="5">
                                                                           ${item.description} 
                                                                            </td>
                                                                            </tr>
                                                                            <tr>
                                                                            <th>地陪内容文本</th>
                                                                             <td colspan="5">${item.content }</td>
                                                                             </tr>
                                                                        </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </c:forEach>
                                                           
                                                            
                                                            
                                                        </div>
                                                    </div>
                                                   
                                                   
                                                    <div class="tab-pane" id="tab_1_4">
                                                    <c:if test="${empty guideStrategyList }"><div class="text-center text-danger">没有相关数据</div></c:if>
                                                        <div id="accordion4" class="panel-group">
                                                        <c:forEach var="item" varStatus="status" items="${guideStrategyList}">
                                                            <div class="panel panel-success">
                                                                <div class="panel-heading">
                                                                    <h4 class="panel-title">
                                                                        <a class="accordion-toggle accordion-toggle-styled" data-toggle="collapse" data-parent="#accordion4" href="#accordion4_${status.count}"> ${status.count}. ${item.title } </a>
                                                                    </h4>
                                                                </div>
                                                                <div id="accordion4_${status.count}" class="panel-collapse collapse ${status.count==1?'in':'' }">
                                                                    <div class="panel-body">
                                                                       <div class="text-center">
                                                                          <img src="${item.imgUrl }" width="150px" class="img-thumbnail" data-preview="true"/>
                                                                       </div>
                                                                       
                                                                       <table class="table my-table">
                                                                         
                                                                         <tr> 
                                                                         <th>标题</th>
                                                                         <td colspan="5">${item.title }</td>
                                                                         </tr>
                                                                         <tr>
                                                                           <th>关联的线路编号</th>
                                                                           <td>${item.relatLineNo }</td>
                                                                           <th>审核状态</th>
                                                                           <td>${fns:EAuditStatus()[item.auditStatus]}</td>
                                                                         <tr>
                                                                         <tr>
                                                                           <th>状态</th>
                                                                           <td ><c:if test="${item.flag==1 }">有效</c:if>
                                                                             <c:if test="${item.flag==0 }" >无效</c:if></td>
                                                                         
                                                                           <th>审核时间</th>
                                                                           <td><fmt:formatDate value="${item.auditTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                         <tr>
                                                                         <tr>
                                                                           <th>创建时间</th>
                                                                           <td><fmt:formatDate value="${item.createTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                           <th>更新时间</th>
                                                                           <td><fmt:formatDate value="${item.updateTime }" pattern="yyyy-MM-dd HH:mm:ss" /></td>
                                                                         <tr>
                                                                         
                                                                         <tr>
                                                                           <th>审核说明</th>
                                                                           <td colspan="5">${item.auditRemark}</td>
                                                                         <tr>
                                                                         <tr>
                                                                           <th>推荐理由</th>
                                                                           <td colspan="5">${item.recommendInfo }</td>
                                                                         <tr>
                                                                         <tr>
                                                                           <th>描述</th>
                                                                           <td colspan="5">${item.description }</td>
                                                                         <tr>
                                                                         <tr>
                                                                           <th>攻略内容文本</th>
                                                                           <td colspan="5">${item.content }</td>
                                                                           
                                                                         <tr>
                                                                        
                                                                       </table>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            </c:forEach>       
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                          
                        </div>
                    </div>
                </div>
	
</body>
</html>