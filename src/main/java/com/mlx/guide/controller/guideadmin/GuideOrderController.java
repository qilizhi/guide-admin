package com.mlx.guide.controller.guideadmin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.service.GuideOrderService;
/**
 * 订单
 * @author cyz
 * @category 订单列表
 *
 */
@Controller
@RequestMapping("/guideAdmin/guideOrder")
public class GuideOrderController {

	private static Logger logger = LoggerFactory.getLogger(GuideOrderController.class);

	@Autowired
	private GuideOrderService guideOrderService;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guide_orderclass", Const.MENU_FIRST);
	}

	/**
	 * 列表
	 * @param pageNo
	 * @param pageSize
	 * @param orderModel
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, OrderModel orderModel,
			HttpServletRequest request, Model model) {
		try {
		
			String timeStart="20130101";
        	String timeEnd=null;
       
    		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
    		timeEnd=sf.format(new Date());

    		//参数查询
    	    if(StringUtils.isNotBlank(orderModel.getStartDate())&&StringUtils.isNotBlank(orderModel.getStartDate())){
    	    	timeStart=orderModel.getStartDate().replaceAll("-", "");
    	    	timeEnd=orderModel.getEndDate().replaceAll("-", "");
    		}
    	    Map<String,Object> maps=new HashMap<String,Object>();
    	    maps.put("userId", "12345678");
    	    maps.put("orderId", orderModel.getOrderId());
    	    maps.put("orderStatus", orderModel.getOrderStatus());
    	    maps.put("startDate", timeStart);
    	    maps.put("endDate", timeEnd);
    	    maps.put("pageNo", pageNo);
    	    maps.put("pageSize", pageSize);
    	    String orderList = guideOrderService.getMemberList(maps);
			
			JSONObject jsonObject = JSON.parseObject(orderList);
			List<OrderModel> list = JSONArray.parseArray(jsonObject.get("result").toString(),OrderModel.class);
			Paginator paginator = new Paginator(pageNo, pageSize, jsonObject.getInteger("total"));
			
			model.addAttribute("list", list);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("orderModel", orderModel);
			model.addAttribute("EGoodsType", EGoodsType.getMap());
			model.addAttribute("paginator", paginator);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/order/list";
	}
	
	/**
	 * 订单详情
	 * @param orderModel
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(OrderModel orderModel,Model model){
		try {
			String json=guideOrderService.getDetail(orderModel.getUserId(), orderModel.getOrderId());
			OrderModel order=JSON.parseObject(JSON.parseObject(json).get("result").toString(),OrderModel.class);
		
			model.addAttribute("orderModel", order);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		 return "guideAdmin/order/detail";

	}
}
