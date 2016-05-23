package com.mlx.guide.controller;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.mlx.guide.constant.Const;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.UserInfoService;

/**
 * 订单管理
 * @author FIVE
 *
 */
@RequestMapping(value="/admin/orderLineInfo")
@Controller
public class OrderLineInfoAdminController{
	
	private static Logger logger = LoggerFactory.getLogger( OrderLineInfoAdminController.class );
	
	
	@Autowired
	private GuideOrderService guideOrderService;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ModelAttribute
	public void common(Model model,OrderModel orderModel) {
		model.addAttribute( "orderclass", Const.MENU_FIRST );
		model.addAttribute( "order_lineclass", Const.MENU_SUB );
	}
	
	
	@RequestMapping
	public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,OrderModel orderModel,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize,Model model){
        try {
        	String timeStart="20130101";
        	String timeEnd=null;
        	String userName=null;
        	String orderStatus=null;
    		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
    		timeEnd=sf.format(new Date());

    		//参数查询
    	    if(StringUtils.isNotBlank(orderModel.getStartDate())&&StringUtils.isNotBlank(orderModel.getStartDate())){
    	    	timeStart=orderModel.getStartDate().replaceAll("-", "");
    	    	timeEnd=orderModel.getEndDate().replaceAll("-", "");
    		}
    	    
    	    if(StringUtils.isNotBlank(orderModel.getUserName())){
    	    	userName=orderModel.getUserName();
    	    }
    	    
    	    if(StringUtils.isNotBlank(orderModel.getOrderStatus())){
    	    	orderStatus=orderModel.getOrderStatus();
    	    }
    	    
    		String resultJson=guideOrderService.getManageList(null, orderModel.getOrderId(),orderStatus, timeStart, timeEnd, pageNo, pageSize);
    		logger.info(resultJson);
    		List<OrderModel> list=JSONArray.parseArray(JSON.parseObject(resultJson).get("result").toString(), OrderModel.class);
    		
    		//分页
    		Paginator paginator = new Paginator(pageNo, pageSize, Integer.parseInt((String) JSON.parseObject(resultJson).get("total")));
    		
    		model.addAttribute( "pageSize", pageSize );
    		model.addAttribute( "paginator", paginator );			
			model.addAttribute("list", list);
			model.addAttribute("orderModel",orderModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	  return "/admin/order/lineOrder/list";
		
	}
	
	
	@RequestMapping("/detail")
	public String detail(OrderModel orderModel,Model model){
		try {
			String json=guideOrderService.getDetail(orderModel.getUserId(), orderModel.getOrderId());
			OrderModel order=JSON.parseObject(JSON.parseObject(json).get("result").toString(),OrderModel.class);
		
			model.addAttribute("orderModel", order);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		 return "/admin/order/lineOrder/detail";

	}
	
	
	
	
	

}
