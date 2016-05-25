package com.mlx.guide.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.mlx.guide.model.OrderRefundModel;
import com.mlx.guide.util.OrderSignUtil;


@Component
public class OrderRefundService {

	
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
	
	public String audit(OrderRefundModel order) {
	   
		return null;
	}
	
}
