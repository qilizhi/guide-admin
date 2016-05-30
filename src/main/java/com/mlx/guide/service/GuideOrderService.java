package com.mlx.guide.service;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.mlx.guide.util.OrderUtil;

/**
 * 
 * @author QiQi-04-PC
 * 订单接口
 * @category 订单Service
 */
@Service
public class GuideOrderService {

	//请求类型 管理员
	private final static String methodType_manage="orderListManage";
	//会员 类型
	private final static String methodType_member="orderList";
	
	private final static String methodType_detail="orderDetail";
	//订单批量查询 接口uri
	@Value("${uri.order.listmanage}")
	private String orderManageList;
	//订单详情
	@Value("${uri.order.detail}")
	private String orderDetail;
	//接口主域 
	@Value("${order_api_host}")
	private  String ORDER_API_HOST;
	/**
	 * 订单批量查询（管理查询） 
	 * @param userId  用户ID 必传N
	 * @param orderId 订单 ID  必传N
	 * @param orderStatus 订单状态  必传N
	 * @param startDate 开始时间  必传Y
	 * @param endDate  结束时间 必传Y
	 * @param pageNo 必传 N 默认1
	 * @param pageSize 必传N 默认 10
	 * @return
	 */
	public String getManageList(String userId,String orderId,String orderStatus,String startDate,String endDate,Integer pageNo,Integer pageSize){
		String url = ORDER_API_HOST+orderManageList;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		params.put("orderStatus", orderStatus);
		params.put("startDate", startDate);
		params.put("endDate", endDate); 
		params.put("pageNo", pageNo); 
		params.put("pageSize", pageSize); 
		params.put("methodType", methodType_manage);
		String result = OrderUtil.post(url, params);
		return result;
	}
	/**
	 * 订单批量查询（会员查询）
	 * @param userId
	 * @param orderId
	 * @param orderStatus
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public String getOrderList(String userId,String orderId,String orderStatus,String startDate,String endDate,Integer pageNo,Integer pageSize){
		String url = ORDER_API_HOST+orderManageList;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		params.put("orderStatus", orderStatus);
		params.put("startDate", startDate);
		params.put("endDate", endDate); 
		params.put("pageNo", pageNo); 
		params.put("pageSize", pageSize); 
		params.put("methodType", methodType_member);
		String result = OrderUtil.get(url, params);
		return result;
	}

	/**
	 * 获取订单详情
	 * @param userId
	 * @param orderId
	 * @return
	 */
	public String getDetail(String userId,String orderId){
		String url = ORDER_API_HOST+orderDetail;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		params.put("methodType", methodType_detail);
		String result = OrderUtil.post(url, params);
		return result;
	}
	
	/**
	 * 订单查询
	 * @param userId
	 * @param orderId
	 * @param orderStatus
	 * @param startDate
	 * @param endDate
	 * @param pageNo
	 * @param pageSize
	 * @param contactsName
	 * @param mobile
	 * @return
	 */
	public String getManageListTwo(String userId,String orderId,String orderStatus,String startDate,String endDate,Integer pageNo,Integer pageSize,String contactsName,String mobile){
		String url = ORDER_API_HOST+orderManageList;
		TreeMap<String, Object> params = new TreeMap<String, Object>();
		params.put("userId", userId);
		params.put("orderId", orderId);
		params.put("orderStatus", orderStatus);
		params.put("startDate", startDate);
		params.put("endDate", endDate); 
		params.put("pageNo", pageNo); 
		params.put("pageSize", pageSize); 
		params.put("contactsName", contactsName); 
		params.put("mobile", mobile); 
		params.put("methodType", methodType_manage);
		String result = OrderUtil.post(url, params);
		return result;
	}
	
	/**
	 * 订单查询通用接口
	 * @param maps <String,Object> map 的参数。
	 * @param uri 接口的相对地址
	 * @return
	 */
	public String getMangeList(Map<String, Object> maps){
		String url=ORDER_API_HOST+orderManageList;
		
		TreeMap<String, Object> treeMapParams=new TreeMap<String,Object>();
		for(String key:maps.keySet()){
			treeMapParams.put(key, maps.get(key));			
		}
		treeMapParams.put("methodType", methodType_manage);
		String result=OrderUtil.post(url, treeMapParams);
		return result;
	}
	


}
