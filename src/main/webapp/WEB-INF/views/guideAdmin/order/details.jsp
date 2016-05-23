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
                <span class="caption-subject font-dark sbold uppercase">订单详情</span>
            </div>
            
        </div>
        <div class="portlet-body">
        <div class="col-md-12 col-sm-12">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <span class="caption-subject font-blue bold uppercase">订单号：${orderDetails.orderId }</span>
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
										  <dt>用户ID:</dt>
										  <dd>${orderDetails.userId }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>用户名称:</dt>
										  <dd>${orderDetails.userName }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>用户客户端IP:</dt>
										  <dd>${orderDetails.clientIp }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>订单流水日期:</dt>
										  <dd>${orderDetails.orderDate}</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>订单流水时间:</dt>
										  <dd>${orderDetails.orderTime }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>订单类型:</dt>
										  <dd>${orderDetails.orderType }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>订单流水状态:</dt>
										  <dd>${orderDetails.orderStatus }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>市场总额:</dt>
										  <dd>${orderDetails.totalMarketPrice }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>总销售额:</dt>
										  <dd>${orderDetails.totalSellPrice }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>应付金额:</dt>
										  <dd>${orderDetails.payFee }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>退款标识:</dt>
										  <dd>${orderDetails.refundFlag }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>退款金额:</dt>
										  <dd>${orderDetails.refundFee }</dd>
										</dl>
										
                                	</div>
                                	<div class="col-md-6 col-sm-6">
                                	
                                		<dl class="dl-horizontal">
										  <dt>币种:</dt>
										  <dd>${orderDetails.currency }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>失效时间:</dt>
										  <dd>${orderDetails.expTime }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>下单终端渠道:</dt>
										  <dd>${orderDetails.sysCnl }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>返回码:</dt>
										  <dd>${orderDetails.respCode }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>返回描述:</dt>
										  <dd>${orderDetails.respMsg }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>备注:</dt>
										  <dd>${orderDetails.remark }</dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>创建时间:</dt>
										  <dd> <fmt:formatDate value="${orderDetails.createTime }" pattern="yyyy-MM-dd"/></dd>
										</dl>
										<dl class="dl-horizontal">
										  <dt>修改时间:</dt>
										  <dd> <fmt:formatDate value="${orderDetails.updateTime }" pattern="yyyy-MM-dd"/></dd>
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