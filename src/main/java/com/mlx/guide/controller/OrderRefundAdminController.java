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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.mlx.guide.constant.Const;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.model.OrderRefundModel;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.OrderRefundService;
import com.mlx.guide.service.SysBizLogService;

/**
 * 退款列表
 * @author FIVE
 *
 */
@RequestMapping(value="/admin/orderRefund")
@Controller
public class OrderRefundAdminController{
	
	private static Logger logger = LoggerFactory.getLogger( OrderRefundAdminController.class );
	
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private GuideOrderService guideOrderService;
	
	
	
	@ModelAttribute
	public void common(Model model,OrderRefundModel orderRefundModel) {
		model.addAttribute("orderclass", Const.MENU_FIRST );
		model.addAttribute("order_refundclass", Const.MENU_SUB );
	}
	
	
	@RequestMapping
	public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,OrderRefundModel orderRefundModel,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize,Model model){
        try {
        	String startDate="20130101";
        	
    		SimpleDateFormat sf= new SimpleDateFormat("yyyyMMdd");
    		String endDate=sf.format(new Date());
    		String starTime=orderRefundModel.getStartDate();
    		String endTime=orderRefundModel.getEndDate();
    		if(StringUtils.isBlank(orderRefundModel.getStartDate())){
    			orderRefundModel.setStartDate(startDate);
    		}
    		if(StringUtils.isBlank(orderRefundModel.getEndDate())){
    			orderRefundModel.setEndDate(endDate);
    		}
    		
    		String resultJson=orderRefundService.getList(pageNo,pageSize,orderRefundModel);
    		
    		List<OrderRefundModel> list= JSONObject.parseArray(JSONObject.parseObject(resultJson).getString("result"), OrderRefundModel.class);
    		//分页
    		Paginator paginator = new Paginator(pageNo, pageSize, JSONObject.parseObject(resultJson).getInteger("total"));
    		orderRefundModel.setStartDate(starTime);
    		orderRefundModel.setEndDate(endTime);
    		model.addAttribute( "pageSize", pageSize );
    		model.addAttribute( "paginator", paginator );			
			model.addAttribute("list", list);
			model.addAttribute("orderRefundModel",orderRefundModel);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	  return "/admin/order/refundOrder/list";
		
	}
	
/*	*//**
	 * 跳转审核页面
	 * @return
	 *//*
	@RequestMapping(value="/audit",method=RequestMethod.GET)
	public String audit(OrderRefundModel orderRefundModel,Model model){
		try {

			OrderRefundModel order=orderRefundService.getByRefundJnId(orderRefundModel.getRefundJnId());
			
			String msg=orderRefundService.audit(order);
			
			
			String json=guideOrderService.getDetail(orderRefundModel.getUserId(), orderRefundModel.getOrderId());
			OrderModel orderModel=JSON.parseObject(JSON.parseObject(json).get("result").toString(),OrderModel.class);
		
			model.addAttribute("orderModel", orderModel);
			model.addAttribute("orderRefundModel", order);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			
		}
		 return "/admin/order/refundOrder/audit";
		
		
	}*/
	
	
	/**
	 * 审核提交数据
	 * @return
	 */
	@RequestMapping(value="/audit",method=RequestMethod.POST)
	public String audit(OrderRefundModel orderRefundModel,@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,@RequestParam( value = "pageSize", defaultValue = "10" ) Integer pageSize,Model model,RedirectAttributes redirect){
		try {
			String msg=orderRefundService.audit(orderRefundModel);
			redirect.addFlashAttribute("msg", msg);
			

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			redirect.addFlashAttribute("msg", "系统异常,请联系管理员");
		}
		return "redirect:/admin/orderRefund?pageNo="+pageNo+"&pageSize"+pageSize;
		
		
	}
	
	
	@RequestMapping("/detail")
	public String detail(OrderModel orderModel,Model model){
		try {
			
		
		
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		 return "/admin/order/lineOrder/detail";

	}
	
}
