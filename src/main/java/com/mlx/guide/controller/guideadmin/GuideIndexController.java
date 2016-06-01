package com.mlx.guide.controller.guideadmin;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideStrategy;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.PlatformMsg;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.GuideStrategyService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.service.PlatformMsgService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

/**
 * 导游后台主页
 * 
 * @author cyz
 * @category 主页
 *
 */
@Controller
@RequestMapping("/guideAdmin")
public class GuideIndexController {

	private static Logger logger = LoggerFactory.getLogger(GuideIndexController.class);

	@Autowired
	private GuideStrategyService guideStrategyService;
	@Autowired
	private GuideLineService guideLineService; 
	@Autowired
	private GuideTuanService guideTuanService;
	@Autowired
	private PlatformMsgService platformMsgService;
	@Autowired
	private GuideOrderService guideOrderService;
	/**
	 * 平台主页
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String main(Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {

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
			
			Map<String,Object> m_maps=new HashMap<String,Object>();
			m_maps.put("userId", "12345678");
			m_maps.put("startDate", timeStart);
			m_maps.put("endDate", timeEnd);
    	    String result = guideOrderService.getMemberList(m_maps);
			JsonResult jsonpObject = JSON.parseObject(result, JsonResult.class);
			
			// 攻略数
			GuideStrategy guideStrategy=new GuideStrategy();
			guideStrategy.setFlag(EFlag.VALID.getId());
			guideStrategy.setUserNo(shiroUser.getUserNo());
			List<GuideStrategy> lsStrategy = guideStrategyService.getGuideStrategyPageList(guideStrategy);
			model.addAttribute("lsStrategy", lsStrategy == null ? 0 : lsStrategy.size());

			//线路数
			GuideLine guideLine=new GuideLine();
			guideLine.setFlag(EFlag.VALID.getId());
			guideLine.setUserNo(shiroUser.userNo);
			List<GuideLine> lsLine = guideLineService.getGuideLinePageList(guideLine);
			model.addAttribute("lsLine", lsLine == null ? 0 : lsLine.size());
			// 本月订单数
			Integer monthOrders = jsonpObject.getTotal();
			model.addAttribute("monthOrders", monthOrders);
			// 本月销售额
			long monthSales = 0;
			/* 已支付 算销售额*/
			Map<String,Object> s_maps=new HashMap<String,Object>();
			s_maps.put("userId", "12345678");
			s_maps.put("orderStatus", "S");
			s_maps.put("startDate", timeStart);
			s_maps.put("endDate", timeEnd);
    	    String s_result = guideOrderService.getMemberList(s_maps);
			List<OrderModel> s_orders=JSONArray.parseArray(JSON.parseObject(s_result).get("result").toString(), OrderModel.class);
			for(OrderModel o:s_orders){
				monthSales+=o.getTotalSellPrice().longValue();
			}
			model.addAttribute("monthSales", monthSales);
			
			//出团提醒列表(出团状态为1)
			GuideTuan tuan=new GuideTuan();
			tuan.setUserNo(shiroUser.getUserNo());
			tuan.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
			List<GuideTuan> lsGuideTuan = guideTuanService.getGuideTuanPageList(tuan);
			model.addAttribute("lsGuideTuan", lsGuideTuan);
			//最新公告列表
			List<PlatformMsg> lsMsg = platformMsgService.getPlatformMsgList();
			model.addAttribute("lsMsg", lsMsg);
			
			//最新订单列表
			Map<String,Object> l_maps=new HashMap<String,Object>();
			l_maps.put("userId", "12345678");
			l_maps.put("startDate", timeStart);
			l_maps.put("endDate", timeEnd);
			String orderList = guideOrderService.getMemberList(l_maps);
			List<OrderModel> list=JSONArray.parseArray(JSON.parseObject(orderList).get("result").toString(), OrderModel.class);
			model.addAttribute("list", list);
			model.addAttribute("EGoodsType", EGoodsType.getMap());
			//系统通知列表
		
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		// 样式
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("home_indexclass", Const.MENU_SUB);
		return "guideAdmin/main";
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
}
