<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fns" uri="/static/fun/fns.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单详情</title>
<link href="${ctx}/static/css/overwrite.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="row">
    <div class="col-md-12">
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
                                        <span class="caption-subject font-blue bold uppercase">订单号：${orderModel.orderId }</span>
                                    </div>
                                    <div class="actions">
                                        <div class="btn-group">
                                         <a  class="btn default" href="javascript:history.go(-1);">返回</a>
                                            
                                        </div>
                                    </div>
                                </div>
                              
                              <table width="100%" class="data-table" >
                              <tr>
                                 <th colspan="10"><div class="text-center">支付详情</div></th>
                              </tr>
                              <tr>
                                <th>登录账号:</th>
                                <td>${orderModel.userName }</td>
                                <th>用户ID:</th>
                                <td>${orderModel.userId }</td>
                                <th>订单总销售额:</th>
                                <td>${orderModel.totalSellPrice }</td>
                              </tr>
                              <tr>
                                 <th>订单市场总额:</th>
                                 <td>${orderModel.totalMarketPrice }</td>
                                 <th>下单终端渠道:</th>
                                 <td>${fns:SysCnlStatus()[orderModel.sysCnl]}</td>
                                 <th>订单备注:</th>
                                 <td>${orderModel.remark }</td>
                               </tr>
                                <tr>
                                 <th>退款标识:</th>
                                 <td>
                                 <c:if test="${orderModel.refundFlag==0 }">用户发起退款</c:if>
                                 <c:if test="${orderModel.refundFlag==1 }">重复支付退款</c:if>
                                 </td>
                                 <th>退款金额:</th>
                                 <td>${orderModel.refundFee }</td>
                                 <th>应付金额:</th>
                                 <td>${orderModel.payFee }</td>
                               </tr>
                                <tr>
                                 <th>订单流水状态:</th>
                                 <td>${fns:OrderPayType()[orderModel.orderStatus]}</td>
                                 <th>用户客户端IP:</th>
                                 <td>${orderModel.clientIp }</td>
                                 <th>币种:</th>
                                 <td>${orderModel.currency }</td>
                               </tr>
                                <tr>
                                 <th>失效时间:</th>
                                 <td>${fns:longTimeToDate("yyyy-MM-dd HH:mm:ss",orderModel.expTime) }</td>
                                 <th>订单日期:</th>
                                 <td>
                                 <fmt:parseDate value="${orderModel.orderDate}" pattern="yyyyMMdd" var="orderDate"/>
                                 <fmt:parseDate value="${orderModel.orderTime}" pattern="HHmmss" var="orderTime"/>
                                 <fmt:formatDate value="${orderDate}" pattern="yyyy-MM-dd"/>&nbsp;
                                  <fmt:formatDate value="${orderTime}" pattern="HH:mm:ss"/>
                                
                                 </td>
                                 <th>支付日期:</th>
                                 <td>
                                  <fmt:parseDate value="${orderModel.payDate}" pattern="yyyyMMdd" var="payDate"/>
                                 <fmt:parseDate value="${orderModel.payTime}" pattern="HHmmss" var="payTime"/>
                                 <fmt:formatDate value="${payDate}" pattern="yyyy-MM-dd"/>&nbsp;
                                  <fmt:formatDate value="${payTime}" pattern="HH:mm:ss"/></td>
                               </tr>
                                
                                <tr>
                                <th colspan="10"><div class="text-center">订单详情</div></th>
                                </tr>
                                <tr>
                                  <th>联系人:</th>
                                  <td>${orderModel.orderDescribe.contactsName }</td>
                                  <th>手机号:</th>
                                  <td>${orderModel.orderDescribe.mobile }</td>
                                  <th>电话 :</th>
                                  <td>${orderModel.orderDescribe.tel }</td>
                                </tr>
                                 <tr>
                                  <th>地址:</th>
                                  <td>${orderModel.orderDescribe.address }</td>
                                  <th>地址类型:</th>
                                  <td>
                                  <c:if test="${orderModel.orderDescribe.addressType ==1}">公司</c:if>
                                  <c:if test="${orderModel.orderDescribe.addressType ==2}">家庭</c:if>
                                  <c:if test="${orderModel.orderDescribe.addressType ==3}">其他</c:if>
                                  </td>
                                  <th>优惠金额:</th>
                                  <td>${orderModel.orderDescribe.favourableFee }</td>
                                </tr>
                                 <tr>
                                  <th>优惠编号:</th>
                                  <td>${orderModel.orderDescribe.favourableId }</td>
                                  <th>发票抬头:</th>
                                  <td>${orderModel.orderDescribe.invoice }</td>
                                  <th>开票方式:</th>
                                  <td>
                                   <c:if test=" ${orderModel.orderDescribe.invoiceMethod==0 }">不开发票</c:if>
                                   <c:if test=" ${orderModel.orderDescribe.invoiceMethod==1 }">纸质发票</c:if>
                                   <c:if test=" ${orderModel.orderDescribe.invoiceMethod==2 }">电子发票</c:if>
                                 </td>
                                </tr>
                                 <tr>
                                  <th>发票类型:</th>
                                  <td>
                                   <c:if test=" ${orderModel.orderDescribe.invoiceType==0 }">个人</c:if>
                                    <c:if test=" ${orderModel.orderDescribe.invoiceType==1 }">公司</c:if>
                                 </td>
                                  <th>邮政编码:</th>
                                  <td>${orderModel.orderDescribe.postcode }</td>
                                  <th>备注:</th>
                                  <td>${orderModel.orderDescribe.remark }</td>
                                </tr>
                                 <tr>
                                  <th>收货时间类型:</th>
                                  <td>
                                   <c:if test="${orderModel.orderDescribe.transportDayType ==0}">周一至周五</c:if>
                                     <c:if test="${orderModel.orderDescribe.transportDayType ==1}">周一至周日均可</c:if>
                                       <c:if test="${orderModel.orderDescribe.transportDayType ==2}">周六日、节假日</c:if>
                                 </td>
                                  <th>配送金额:</th>
                                  <td>${orderModel.orderDescribe.transportFee }</td>
                                  <th>物流承运商编号:</th>
                                  <td>${orderModel.orderDescribe.transportId }</td>
                                </tr>
                                 <tr>
                                  <th>物流编号:</th>
                                  <td>${orderModel.orderDescribe.transportNum}</td>
                                  <th>配送时间:</th>
                                  <td>
                                  <c:if test=" ${orderModel.orderDescribe.transportTime==0 }">白天</c:if>
                                   <c:if test=" ${orderModel.orderDescribe.transportTime==1 }">全天</c:if>
                                 </td>
                                  <th>更新时间:</th>
                                  <td>
                                  ${fns:longTimeToDate('yyyy-MM-dd HH:mm:ss',orderModel.orderDescribe.updateTime)}
              
                                     </td>
                                </tr>
                                 <tr>
                                 <th colspan="10"><div class="text-center">商品信息</div></th>
                                 </tr>
                                 
                                 <c:forEach items="${orderModel.orderGoods }" var="item">
                                 <tr>
                                  <th>成人市场价格:</th>
                                  <td>${item.adultMarketPrice }</td>
                                  <th>成人人数:</th>
                                  <td>${item.adultNum }</td>
                                  <th>剩余儿童销售人数:</th>
                                  <td>${item.childSellCount }</td>
                                 </tr>
                                  <tr>
                                  <th>成人销售价格:</th>
                                  <td>${item.adultSellPrice }</td>
                                  <th>儿童市场价格:</th>
                                  <td>${item.childMarketPrice }</td>
                                  <th>儿童人数:</th>
                                  <td>${item.childNum }</td>
                                 </tr>
                                  <tr>
                                  <th>剩余成人销售人数:</th>
                                  <td>${item.adultSellCount }</td>
                                  <th>儿童销售价格</th>
                                  <td>${item.childSellPrice }</td>
                                  <th>商品ID</th>
                                  <td>${item.goodsId }</td>
                                 </tr>
                                 
                                 <tr>
                                   <th colspan="10"><div class="text-center">订单保险信息</div></th>
                                 </tr>
                                 <c:forEach var="s_item" items="${item.goodsInsurances }" >
                                   <tr>
                                    <th>保险ID</th>
                                    <td>${s_item.insuranceId }</td>
                                      <th>保险数量</th>
                                    <td>${s_item.insuranceNum }</td>
                                      <th>保险价格</th>
                                    <td>${s_item.insurancePrice }</td>
                                   </tr>
                                 </c:forEach>
                                 
                                 <tr>
                                   <th colspan="10"><div class="text-center">其他旅客信息</div></th>
                                 </tr>
                                 
                                 <c:forEach var="s_item" items="${item.goodsTourists }" >
                                   <tr>
                                    <th>姓名</th>
                                    <td>${s_item.touristName }</td>
                                      <th>性别</th>
                                    <td>
                                    <c:if test="${s_item.touristSex==1 }">男</c:if>
                                     <c:if test="${s_item.touristSex==2 }">女</c:if>
                                  </td>
                                      <th>旅客类型</th>
                                    <td>
                                    <c:if test=" ${s_item.touristType==1 }">成人</c:if>
                                    <c:if test=" ${s_item.touristType==1 }">儿童</c:if>
                                   </td>
                                   </tr>
                                   
                                   <tr>
                                     <th>证件号</th>
                                     <td>${s_item.touristCardNo }</td>
                                      <th>证件类型</th>
                                     <td>
                                  
                                     <c:if test="${s_item.touristCardType==0}">身份证</c:if>
                                      <c:if test="${s_item.touristCardType==1}">护照</c:if>
                                       <c:if test="${s_item.touristCardType==9}">其他</c:if>
                                     </td>
                                      <th>手机号码</th>
                                     <td>${s_item.touristMobile }</td>
                                   </tr>
                                 </c:forEach>
                                 
                                 </c:forEach>
                                 
                              </table>
                                 <div class="text-center "><a  class="btn default  mt10 "    href="javascript:history.go(-1);">返回</a></div>
                                </div>
                            </div>
                        </div>
		</div>
	</div>
</div>
</div>
</body>
</html>