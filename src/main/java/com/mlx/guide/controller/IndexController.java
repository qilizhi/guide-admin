package com.mlx.guide.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.model.OrderGoodsModel;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.GuideServiceService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.util.OrderUtil;

/**
 * 平台后台主页
 * 
 * @author quan
 *
 */
@Controller
@RequestMapping("/admin")
public class IndexController {

	/**
	 * 平台主页
	 * 
	 * @param model
	 * @return
	 */
	@Autowired
	private GuideInfoService guideInfoService;
	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private UserInfoService userInfoService;
	@Autowired
	private GuideTuanService guideTuanService;
	@Autowired
	private GuideServiceService guideServiceService;
	@Autowired
	private GuideOrderService guideOrderService;
	@ModelAttribute
	public void comm(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
	}

	@RequestMapping
	public String main(Model model) {

		model.addAttribute("home_indexclass", Const.MENU_SUB);
		// 本月
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		// 获取当前月
		Integer month = calendar.get(Calendar.MONTH) + 1;
		Integer monthStart = calendar.getMinimum(Calendar.DATE);
		Integer monthEnd = calendar.getMaximum(Calendar.DATE);
		calendar.set(Calendar.DATE, monthStart);
		// 获取当月月初的年月日yyMMdd
		String timeStart = simpledateformat.format(calendar.getTime());
		calendar.set(Calendar.DATE, monthEnd);
		// 获取当月月末的年月日
		String timeEnd = simpledateformat.format(calendar.getTime());

		String result=guideOrderService.getManageList(null, null, null, timeStart, timeEnd, null, null);
		JsonResult jsonpObject = JSON.parseObject(result, JsonResult.class);
		GuideInfo g = new GuideInfo();
		// 本月销售额
		long monthSales = 0;
		/* 已支付 算销售额*/
		String s_result=guideOrderService.getManageList(null, null,"S", timeStart, timeEnd, null, null);
		List<OrderModel> s_orders=JSONArray.parseArray(JSON.parseObject(s_result).get("result").toString(), OrderModel.class);
		for(OrderModel o:s_orders){
			monthSales+=o.getTotalSellPrice().longValue();
		}
		// 本月导服
        Long monthServices=guideServiceService.countByDate(month);		
		
		// 本月出团
		Long monthTuans = guideTuanService.countByDate(month);
		// 本月新增导游数
		Long monthNewGuides = guideInfoService.countByDate(month);

		// 本月订单数
		Integer monthOrders = jsonpObject.getTotal();
		// 导游总人数
		Integer totalGuides = guideInfoService.getGuideInfoList(g).size();
		// 用户总数量
		Integer totalMembers = userInfoService.getUserInfoList().size();
		// 线路数量
		Integer totalLines = guideLineService.getGuideLineList().size();
		// 待处理导游
		g.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
		List<GuideInfo> guidesInfos = guideInfoService.getGuideInfoList(g);
		/** 待处理订单**/		
		//未支付W
		String w_result=guideOrderService.getManageList(null, null,"W", timeStart, timeEnd, null, null);
		//退款审核中RC
		String rc_result=guideOrderService.getManageList(null, null,"RC", timeStart, timeEnd, null, null);
		//等待退款:RW 
		String rw_result=guideOrderService.getManageList(null, null,"RW", timeStart, timeEnd, null, null);
		List<OrderModel> w_orders=JSONArray.parseArray(JSON.parseObject(w_result).get("result").toString(), OrderModel.class);
		List<OrderModel> rc_orders=JSONArray.parseArray(JSON.parseObject(rc_result).get("result").toString(), OrderModel.class);
		List<OrderModel> rw_orders=JSONArray.parseArray(JSON.parseObject(rw_result).get("result").toString(), OrderModel.class);
		model.addAttribute("monthSales", monthSales);
		model.addAttribute("monthServices", monthServices);
		model.addAttribute("monthTuans", monthTuans);
		model.addAttribute("monthNewGuides", monthNewGuides);
		model.addAttribute("monthOrders", monthOrders);
		model.addAttribute("totalGuides", totalGuides);
		model.addAttribute("totalMembers", totalMembers);
		model.addAttribute("totalLines", totalLines);
		model.addAttribute("guidesInfos", guidesInfos);
		model.addAttribute("w_orders", w_orders);
		model.addAttribute("rc_orders", rc_orders);
		model.addAttribute("rw_orders", rw_orders);
		return "admin/main";
	}

	/**
	 * 获取系统消息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMsg")
	@ResponseBody
	public JsonResult getMsg(Model model) {

		return new JsonResult(ExceptionCode.FAIL);
	}

	/**
	 * 预览
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/review")
	public String review(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("home_reviewclass", Const.MENU_SUB);
		return "admin/review/view";
	}

}
