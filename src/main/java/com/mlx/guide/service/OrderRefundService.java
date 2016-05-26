package com.mlx.guide.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mlx.guide.controller.OrderRefundAdminController;
import com.mlx.guide.model.OrderRefundModel;
import com.mlx.guide.util.OrderSignUtil;
import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;


@Component
public class OrderRefundService {

	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundService.class );
	
	@Value("${order_api_host}")
	private  String ORDER_API_HOST;
	
	
 /**
 * 
 * @param startDate Y 开始时间
 * @param endDate  Y 结束时间
 * @param pageNo N
 * @param pageSize N
 * @param refundJnId N  退款订单流水号
 * @param refundStatus N   退款订单状态
 * @return
 */
	public String getList(String startDate,String endDate,Integer pageNo,Integer pageSize,String refundJnId,String refundStatus){
		String url = ORDER_API_HOST+"/order/refund/list";
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("refundJnId", refundJnId); 
		params.put("refundStatus", refundStatus); 
		params.put("methodType", "refundList");
		return  OrderSignUtil.post(url, params);
	}
	/**
	 * 退款审核
	 * @return
	 */
	public String audit(){
		
		return ORDER_API_HOST;
		
		
	}
	
	/**
	 * 退款详情
	 * @return
	 */
	public OrderRefundModel getByRefundJnId(String refundJnId){
		
		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
		String endDate=sf.format(new Date());
		String url = ORDER_API_HOST+"/order/refund/list";
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("startDate", "20130101");
		params.put("endDate", endDate);
		params.put("refundJnId", refundJnId); 
		params.put("methodType", "refundList");
		String result=OrderSignUtil.post(url, params);
		List<OrderRefundModel> list= JSONObject.parseArray(JSONObject.parseObject(result).getString("result"), OrderRefundModel.class);
		return list.get(0);
		
		
		
	}
	
	public String audit(OrderRefundModel order) throws Exception {
	    //校验参数
		if(StringUtils.isBlank(order.getCheckStatus())){
			throw new Exception("审核状态为空");
		}else if(StringUtils.isBlank(order.getRefundJnId())){
			throw new Exception("退款流水号为空");
		}else if(StringUtils.isBlank(order.getRefundStatus())){
			throw new Exception("退款状态为空");
		}else if(StringUtils.isBlank(order.getAmount())){
			throw new Exception("退款金额为空,小数不能超过两位");
		}else if(StringUtils.isBlank(order.getUserId())){
			throw new Exception("用户ID为空");		
		}
		
		//校验当审核不通过时,备注参数不能为空
		if(order.getCheckStatus().equals("-1")&&StringUtils.isBlank(order.getRemark())){
			throw new Exception("审核不通过,备注不能为空");
		}
		
		OrderRefundModel orderRefundModel=this.getByRefundJnId(order.getRefundJnId());
		
		if(Double.parseDouble(order.getAmount())>Double.parseDouble(orderRefundModel.getPayFee())){
			throw new Exception("退款额大于订单额");	
		}

		//调用退款审核接口
           String resultJson=this.auditInterface(order);
           if(!JSONObject.parseObject(resultJson).get("code").equals("0000")){
        	   logger.info(resultJson);
        	  throw new Exception("调用订单退款接口失败");
           }
		return "操作成功";
	}
	
	public String auditInterface(OrderRefundModel order){
		String url=ORDER_API_HOST+"/order/refund/check";
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", order.getUserId());
		params.put("refundJnId", order.getRefundJnId());
		params.put("amount", order.getAmount()); 
		params.put("checkStatus", order.getCheckStatus());
		params.put("refundStatus", order.getRefundStatus());
		params.put("remark", order.getRemark());
		params.put("methodType", "orderRefundCheck");
		return  OrderSignUtil.post(url, params);
		
		
	}
	
	public static void main(String[] args) {
		String str = "123.02";
		/*String regex="/^\d+(\.\d{1,2})?$/";
		str.matches(regex)
		new re
		str.matches(/^\d+(\.\d{1,2})?$/);
	    String match = str.match(/^\d+(\.\d{1,2})?$/)[0];
	    alert(match);
	    
	    String a="123.02";
	    a.matches("/^\d+(\.\d{1,2})?$/");*/
	   
	    boolean result=Pattern.compile("/^\\d+(\\.\\d{1,2})$/").matcher(str).matches();
	    System.out.println(result);
	}
}
