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

 

                    <!-- END BREADCRUMBS -->
                    <!-- BEGIN PAGE BASE CONTENT -->
                    <!-- BEGIN DASHBOARD STATS 1-->
                    <div class="row">
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat blue">
                                <div class="visual">
                                    <i class="fa fa-comments"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="${lsStrategy}">0</span>
                                    </div>
                                    <div class="desc"> 攻略数 </div>
                                </div>
                                <a class="more" href="javascript:;"> 更多
                                    <i class="m-icon-swapright m-icon-white"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat red">
                                <div class="visual">
                                    <i class="fa fa-bar-chart-o"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="${lsLine}">0</span> </div>
                                    <div class="desc"> 线路数 </div>
                                </div>
                                <a class="more" href="javascript:;"> 更多
                                    <i class="m-icon-swapright m-icon-white"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat green">
                                <div class="visual">
                                    <i class="fa fa-shopping-cart"></i>
                                </div>
                                <div class="details">
                                    <div class="number">
                                        <span data-counter="counterup" data-value="${monthOrders}">0</span>
                                    </div>
                                    <div class="desc"> 月订单数</div>
                                </div>
                                <a class="more" href="javascript:;"> 更多
                                    <i class="m-icon-swapright m-icon-white"></i>
                                </a>
                            </div>
                        </div>
                        <div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
                            <div class="dashboard-stat purple">
                                <div class="visual">
                                    <i class="fa fa-globe"></i>
                                </div>
                                <div class="details">
                                    <div class="number"> 
                                        <span data-counter="counterup" data-value="${monthSales}">0</span>￥
                                    </div>
                                    <div class="desc"> 月销售额 </div>
                                </div>
                                <a class="more" href="javascript:;"> 更多
                                    <i class="m-icon-swapright m-icon-white"></i>
                                </a>
                            </div>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <!-- END DASHBOARD STATS 1-->
                    
                    
                    
                   
                   
                    <!-- example -->
                    <div class="row">
                        <div class="col-md-6 col-sm-6">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-share font-blue"></i>
                                        <span class="caption-subject font-blue bold uppercase">出团提醒</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;">
                                                <i class="fa fa-refresh"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                        <ul class="feeds">
                                        <c:forEach var="tuan" items="${lsGuideTuan}">
                                            <li>
                                            <a href="javascript:;">
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-check"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> ${tuan.name}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date" style="padding:0px"><fmt:formatDate value="${tuan.tuanDate}" pattern="yyyy-MM-dd"/></div>
                                                </div>
                                                </a>
                                            </li>
                                            </c:forEach>
                                        </ul>
                                    </div>
                                    <div class="scroller-footer">
                                        <div class="btn-arrow-link pull-right">
                                            <a href="${ctx}/guideAdmin/tuan">查看全部</a>
                                            <i class="icon-arrow-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-share font-blue"></i>
                                        <span class="caption-subject font-blue bold uppercase">公告消息</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;">
                                                <i class="fa fa-refresh"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                        <ul class="feeds">
                                           <c:forEach var="msg" items="${lsMsg}">
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-default">
                                                                <i class="fa fa-bell-o"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> ${msg.title}
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date" style="padding:0px"> <fmt:formatDate value="${msg.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/>  </div>
                                                </div>
                                            </li>
                                            </c:forEach>
                                            
                                        </ul>
                                    </div>
                                    <div class="scroller-footer">
                                        <div class="btn-arrow-link pull-right">&nbsp;
                                            <!-- <a href="javascript:;">查看全部</a> 
                                            <i class="icon-arrow-right"></i>-->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                   </div>
                   <!-- example -->
 
 					<div class="row">
                        <div class="col-md-6 col-sm-6">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-share font-blue"></i>
                                        <span class="caption-subject font-blue bold uppercase">最新订单</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;">
                                                <i class="fa fa-refresh"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                        <ul class="feeds">
                                          <c:forEach var="item" items="${list}"> 
                                            <li>
                                            <a href="${ctx}/guideAdmin/guideOrder/details/${item.orderId}">
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-shopping-cart"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> 订单号： ${item.orderId}
                                                                <span class="label label-sm label-success">订单类型: ${item.orderType} </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date" style="padding:0px"> ${item.orderDate} </div>
                                                </div>
                                                </a>
                                            </li>
 											</c:forEach>
                                        </ul>
                                    </div>
                                    <div class="scroller-footer">
                                        <div class="btn-arrow-link pull-right">
                                            <a href="${ctx}/guideAdmin/guideOrder">查看全部</a>
                                            <i class="icon-arrow-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-sm-6">
                            <div class="portlet light bordered">
                                <div class="portlet-title">
                                    <div class="caption">
                                        <i class="icon-share font-blue"></i>
                                        <span class="caption-subject font-blue bold uppercase">系统通知</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                            <a class="btn btn-sm blue btn-outline btn-circle" href="javascript:;">
                                                <i class="fa fa-refresh"></i>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                                <div class="portlet-body">
                                    <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                                        <ul class="feeds">
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-check"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> You have 4 pending tasks.
                                                                <span class="label label-sm label-warning "> Take action
                                                                    <i class="fa fa-share"></i>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> Just now </div>
                                                </div>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <div class="col1">
                                                        <div class="cont">
                                                            <div class="cont-col1">
                                                                <div class="label label-sm label-success">
                                                                    <i class="fa fa-bar-chart-o"></i>
                                                                </div>
                                                            </div>
                                                            <div class="cont-col2">
                                                                <div class="desc"> Finance Report for year 2013 has been released. </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col2">
                                                        <div class="date"> 20 mins </div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-danger">
                                                                <i class="fa fa-user"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> You have 5 pending membership that requires a quick review. </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 24 mins </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-shopping-cart"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> New order received with
                                                                <span class="label label-sm label-success"> Reference Number: DR23923 </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 30 mins </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-success">
                                                                <i class="fa fa-user"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> You have 5 pending membership that requires a quick review. </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 24 mins </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-default">
                                                                <i class="fa fa-bell-o"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> Web server hardware needs to be upgraded.
                                                                <span class="label label-sm label-default "> Overdue </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 2 hours </div>
                                                </div>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <div class="col1">
                                                        <div class="cont">
                                                            <div class="cont-col1">
                                                                <div class="label label-sm label-default">
                                                                    <i class="fa fa-briefcase"></i>
                                                                </div>
                                                            </div>
                                                            <div class="cont-col2">
                                                                <div class="desc"> IPO Report for year 2013 has been released. </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col2">
                                                        <div class="date"> 20 mins </div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-check"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> You have 4 pending tasks.
                                                                <span class="label label-sm label-warning "> Take action
                                                                    <i class="fa fa-share"></i>
                                                                </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> Just now </div>
                                                </div>
                                            </li>
                                            <li>
                                                <a href="javascript:;">
                                                    <div class="col1">
                                                        <div class="cont">
                                                            <div class="cont-col1">
                                                                <div class="label label-sm label-danger">
                                                                    <i class="fa fa-bar-chart-o"></i>
                                                                </div>
                                                            </div>
                                                            <div class="cont-col2">
                                                                <div class="desc"> Finance Report for year 2013 has been released. </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                    <div class="col2">
                                                        <div class="date"> 20 mins </div>
                                                    </div>
                                                </a>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-default">
                                                                <i class="fa fa-user"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> You have 5 pending membership that requires a quick review. </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 24 mins </div>
                                                </div>
                                            </li>
                                            <li>
                                                <div class="col1">
                                                    <div class="cont">
                                                        <div class="cont-col1">
                                                            <div class="label label-sm label-info">
                                                                <i class="fa fa-shopping-cart"></i>
                                                            </div>
                                                        </div>
                                                        <div class="cont-col2">
                                                            <div class="desc"> New order received with
                                                                <span class="label label-sm label-success"> Reference Number: DR23923 </span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col2">
                                                    <div class="date"> 30 mins </div>
                                                </div>
                                            </li>
 
                                        </ul>
                                    </div>
                                    <div class="scroller-footer">
                                        <div class="btn-arrow-link pull-right">
                                            <a href="javascript:;">See All Records</a>
                                            <i class="icon-arrow-right"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                   </div>
 
 
 
 
 
 
 
                    <!-- END PAGE BASE CONTENT -->


</body>

</html>