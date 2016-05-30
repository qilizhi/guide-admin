package com.mlx.guide.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.alibaba.fastjson.JSONObject;
import com.mlx.guide.model.OrderRefundModel;
import com.mlx.guide.util.OrderUtil;


@Component
public class OrderRefundService {

	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundService.class );
	
	@Value("${order_api_host}")
	private  String ORDER_API_HOST;
	
	

	
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
		String result=OrderUtil.post(url, params);
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
		}
		//当审核通过时金额不能为空,并且是数字
		else if(order.getCheckStatus().equals("1")&&order.getRefundStatus().equals("RR")&&StringUtils.isBlank(order.getAmount())&&!order.getAmount().matches("^(([0-9]+\\d*)|([0-9]+\\d*\\.\\d{1,2}))$")){
			throw new Exception("金额不能为空,并且只能两位小数");
		}
		
		//校验当审核不通过时,备注参数不能为空
		else if(order.getCheckStatus().equals("-1")&&StringUtils.isBlank(order.getRemark())){
			throw new Exception("审核不通过,备注不能为空");
		}
		OrderRefundModel orderRefundModel=this.getByRefundJnId(order.getRefundJnId());
		
		if(order.getCheckStatus().equals("1")){
			order.setRemark(null);
		}
		if(order.getCheckStatus().equals("-1")){
			order.setAmount("0.01");
		}
		//如果是财务退款,并且通过,则设置退款金额就是审核金额
		if(order.getCheckStatus().equals("1")&&order.getRefundStatus().equals("RC")){
			order.setAmount(orderRefundModel.getAmount());
		}
		if(StringUtils.isNotBlank(order.getAmount())&&Double.parseDouble(order.getAmount())>Double.parseDouble(orderRefundModel.getPayFee())){
			throw new Exception("退款额大于订单额");	
		}
		
		
		order.setUserId(orderRefundModel.getUserId());

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
		return  OrderUtil.post(url, params);
		
		
	}
	
	
	
	public String getList(Integer pageNo,Integer pageSize,OrderRefundModel orderRefundModel) {
		String url = ORDER_API_HOST+"/order/refund/list";
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("startDate", orderRefundModel.getStartDate().replace("-", ""));
		params.put("endDate", orderRefundModel.getEndDate().replace("-", ""));
		params.put("pageNo", pageNo);
		params.put("pageSize", pageSize);
		params.put("refundStatus", orderRefundModel.getRefundStatus()); 
		params.put("orderId", orderRefundModel.getOrderId()); 
		params.put("refundStatus", orderRefundModel.getRefundStatus()); 
		params.put("methodType", "refundList");
		return  OrderUtil.post(url, params);
	}
}
