<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>创建订单</title>
<style>
.details{border:1px solid #ccc;width:1000px;margin:auto;}
.details dl{padding:10px;}
</style>
</head>

<body>
<div class="row">
    <div class="col-md-12">
        <!-- BEGIN VALIDATION STATES-->
    <div class="portlet light portlet-fit portlet-form ">
        <div class="portlet-title">
            <div class="caption">
                <i class="icon-settings font-dark"></i>
                <span class="caption-subject font-dark sbold uppercase">红包活动详情</span>
            </div>
            
        </div>
        <div class="portlet-body">
        <div class="col-md-12 col-sm-12">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <span class="caption-subject font-blue bold uppercase">活动名称：${actRedPacket.actName }</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                           <a href="javascript:;" onClick="javascript:history.back(-1);">返回</a>
                                            
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                	<div class="col-md-6 col-sm-6">
                                		<dl class="dl-horizontal">
										  <dt>红包活动编号:</dt>
										  <dd>${actRedPacket.actNo }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>订单号:</dt>
										  <dd>${actRedPacket.orderNo }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>微信公众号ID:</dt>
										  <dd>${actRedPacket.wxAppId }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>商户号:</dt>
										  <dd>${actRedPacket.partnerId }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>接受收红包的用户:</dt>
										  <dd>${actRedPacket.openId }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>付款金额:</dt>
										  <dd>${actRedPacket.totalAmount }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>红包数:</dt>
										  <dd>${actRedPacket.totalNum }</dd>
										</dl>
										
										
                                	</div>
                                	<div class="col-md-6 col-sm-6">
                                	
                                		<dl class="dl-horizontal">
										  <dt>祝福语:</dt>
										  <dd>${actRedPacket.wishing }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>活动名称:</dt>
										  <dd>${actRedPacket.actName }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>备注:</dt>
										  <dd>${actRedPacket.remark }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>创建时间:</dt>
										  <dd><fmt:formatDate value="${actRedPacket.createTime }" pattern="yyyy-MM-dd"/></dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>红包发送时间:</dt>
										  <dd><fmt:formatDate value="${actRedPacket.sendTime }" pattern="yyyy-MM-dd"/></dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>红包订单的微信单号:</dt>
										  <dd>${actRedPacket.sendListid }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>发送状态:</dt>
										  <c:if test="${actRedPacket.status==1}">
										  <dd>发送成功</dd>
										  </c:if>
										  <c:if test="${actRedPacket.status==2}">
										  <dd>发送失败</dd>
										  </c:if>
										</dl>
                                	
                                	</div>
                                
                                    <div class="scroller-footer">
                                        <div class="btn-arrow-link pull-right">
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