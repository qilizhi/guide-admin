
package com.mlx.guide.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresUser;
import org.junit.internal.runners.statements.Fail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.ELineType;
import com.mlx.guide.constant.ESignInStatus;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.GuideTuanGuest;
import com.mlx.guide.model.OrderGoodsModel;
import com.mlx.guide.model.OrderGoodsModel.GoodsTourists;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.model.PreTuanModel;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.GuideServiceService;
import com.mlx.guide.service.GuideTuanGuestService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

/**
 * 
 * @author QiQi-04-PC
 * 
 * @category 出团列表
 *
 */
@Controller
@RequestMapping("/admin/guideTuan")
public class GuideTuanAdminController {
	Logger logger = LoggerFactory.getLogger(GuideTuanAdminController.class);
	@Autowired
	private GuideTuanService guideTuanService;
	@Autowired
	private GuideTuanGuestService guideTuanGuestService;
	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private GuideServiceService guideservice;
	@Autowired
	private GuideOrderService guideOrderService;

	@ModelAttribute
	public void comm(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("home_tuanclass", Const.MENU_SUB);
	}

	/**
	 * 出团列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideTuan
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuan guideTuan,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		// ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			// guideTuan.setUserNo(shiroUser.getUserNo());
			PageList<GuideTuan> list = guideTuanService.getGuideTuanPageList(guideTuan,
					new PageBounds(pageNo, pageSize));
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("name", guideTuan.getName());
			model.addAttribute("goodsNo", guideTuan.getGoodsNo());
			model.addAttribute("ETuanStatus", ETuanStatus.getByteMap());
			model.addAttribute("ELineType", ELineType.getByteMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "admin/guideTuan/list";
	}

	@RequestMapping("/submit/out")
	@ResponseBody
	public JsonResult groupOut(GuideTuan gt, Model model) {

		String userNo = "";
		String userName = "";
		// 1.查询线路及地陪信息
		if (gt.getGoodsType().equals(EGoodsType.LOCAL.getId())) {
			GuideService gss = guideservice.getGuideServiceByServiceNo(gt.getGoodsNo());
			if (gss == null) {
				logger.info("查到的地陪为空！");
				// return new JsonResult(ExceptionCode.FAIL,"查到的地陪为空!");
			} else {

				userNo = gss.getUserNo();
				userName = gss.getUserName();
			}
		} else if (gt.getGoodsType().equals(EGoodsType.LINE.getId())) {
			GuideLine gl = guideLineService.getGuideLineByLineNo(gt.getGoodsNo());
			if (gl == null) {
				logger.info("查到的线路为空！");
				// return new JsonResult(ExceptionCode.FAIL,"查到的地陪为空!");
			} else {
				userNo = gl.getUserNo();
				userName = gl.getUserName();
			}
		}
		// 2.插入一条出团记录到出团表里，并修改状态为已取消出团；
		gt.setUserName(userName);
		gt.setUserNo(userNo);
		gt.setTuanStatus(ETuanStatus.CANCEL.getId().byteValue());
	
		int id;
		try {
			id = guideTuanService.insertSelective(gt);
		} catch (Exception e) {
			logger.info("数据库更新出错！");
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		
		// 3.1 查询该团下的订单
				Map<String, Object> params = new HashMap<String, Object>();
				params.put("orderStatus", "W");
				params.put("groupNo", gt.getTuanNo());
				params.put("pageSize", Integer.MAX_VALUE);
				params.put("startDate", getPreMonthStartDate(6));// 暂定前后6个月
				params.put("endDate", getNextMonthEndDate(6));
				String result = guideOrderService.getMangeList(params);
				if ("".equals(result) || result == null) {
					logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
				}
				List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
						OrderModel.class);
		// 3.2 将用户插入到tuanGuest
				List<GuideTuanGuest> guideTuanGuests = new ArrayList<GuideTuanGuest>();
				for (OrderModel order : orders) {
					for (OrderGoodsModel orderGoods : order.getOrderGoods()) {
						for (GoodsTourists goodsT : orderGoods.getGoodsTourists()) {
							GuideTuanGuest gtg = new GuideTuanGuest();
							gtg.setGuestName(goodsT.getTouristName());
							gtg.setMobile(goodsT.getTouristMobile());
							gtg.setOrderNo(order.getOrderId());
							gtg.setTuanNo(orderGoods.getGroupNo());
							guideTuanGuests.add(gtg);
						}
					}
				}
				if (guideTuanGuests.size() > 0) {
					guideTuanGuestService.batInsertSelective(guideTuanGuests);
				} else {
					logger.info("客户信息为空！");
				}


		// 4.从环信创建一个群。
				

		// 5.把报团人员拉入群里。

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	@RequestMapping(value = "/submit/cancel", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult groupCancel(GuideTuan gt, Model model) {

		String userNo = "";
		String userName = "";
		// 1.查询线路及地陪信息
		if (gt.getGoodsType().equals(EGoodsType.LOCAL.getId())) {
			GuideService gss = guideservice.getGuideServiceByServiceNo(gt.getGoodsNo());
			if (gss == null) {
				logger.info("查到的地陪为空！");
				// return new JsonResult(ExceptionCode.FAIL,"查到的地陪为空!");
			} else {

				userNo = gss.getUserNo();
				userName = gss.getUserName();
			}
		} else if (gt.getGoodsType().equals(EGoodsType.LINE.getId())) {
			GuideLine gl = guideLineService.getGuideLineByLineNo(gt.getGoodsNo());
			if (gl == null) {
				logger.info("查到的线路为空！");
				// return new JsonResult(ExceptionCode.FAIL,"查到的地陪为空!");
			} else {
				userNo = gl.getUserNo();
				userName = gl.getUserName();
			}
		}
		// 2.插入一条出团记录到出团表里，并修改状态为已取消出团；
		gt.setUserName(userName);
		gt.setUserNo(userNo);
		gt.setTuanStatus(ETuanStatus.CANCEL.getId().byteValue());
	
		int id;
		try {
			id = guideTuanService.insertSelective(gt);
		} catch (Exception e) {
			logger.info("数据库更新出错！");
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		// 4.申请退款
		// 4.1 查询该团下的订单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderStatus", "W");
		params.put("groupNo", gt.getTuanNo());
		params.put("pageSize", Integer.MAX_VALUE);
		params.put("startDate", getPreMonthStartDate(6));// 暂定前后6个月
		params.put("endDate", getNextMonthEndDate(6));
		String result = guideOrderService.getMangeList(params);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		// 4.2 将用户插入到tuanGuest
		List<GuideTuanGuest> guideTuanGuests = new ArrayList<GuideTuanGuest>();
		for (OrderModel order : orders) {
			for (OrderGoodsModel orderGoods : order.getOrderGoods()) {
				for (GoodsTourists goodsT : orderGoods.getGoodsTourists()) {
					GuideTuanGuest gtg = new GuideTuanGuest();
					gtg.setGuestName(goodsT.getTouristName());
					gtg.setMobile(goodsT.getTouristMobile());
					gtg.setOrderNo(order.getOrderId());
					gtg.setTuanNo(orderGoods.getGroupNo());
					guideTuanGuests.add(gtg);
				}
			}
		}
		if (guideTuanGuests.size() > 0) {
			guideTuanGuestService.batInsertSelective(guideTuanGuests);
		} else {
			logger.info("客户信息为空！");
		}

		// 4.3 调用退款接口退款
		for (OrderModel o : orders) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("userId", o.getUserId());
			p.put("orderId", o.getOrderId());
			guideOrderService.refundApply(p);
			String resultCode = JSON.parseObject(result).get("code").toString();
			if (!resultCode.equals("0000")) {
				logger.info("订单：" + o.getOrderId() + "申请退款失败！");
			}
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, id);
	}

	/**
	 * 出团详情 查看出才人员。
	 * 
	 * @param groupNo
	 * @param pageNo
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping("/detail/{groupNo}")
	public String detail(@PathVariable("groupNo") String groupNo,
			@RequestParam(defaultValue = Const.PAGE_NO, value = "pageNo") Integer pageNo,
			@RequestParam(defaultValue = Const.PAGE_SIZE, value = "pageSize") Integer pageSize, String startDate,
			String endDate, Model model) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderStatus", "W");
		params.put("groupNo", groupNo);
		params.put("pageSize", Integer.MAX_VALUE);
		params.put("startDate", getPreMonthStartDate(6));
		params.put("endDate", getNextMonthEndDate(6));
		params.put("startTripDate", startDate == null ? getPreMonthStartDate(1) : startDate);
		params.put("endTripDate", endDate == null ? getNextMonthEndDate(1) : endDate);
		String result = guideOrderService.getMangeList(params);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		List<GuideTuanGuest> gtguests = new ArrayList<GuideTuanGuest>();
		// 取出参团成员信息
		for (OrderModel o : orders) {
			if (o.getOrderGoods() != null && o.getOrderGoods().size() > 0) {
				for (OrderGoodsModel og : o.getOrderGoods()) {
					for (GoodsTourists gts : og.getGoodsTourists()) {
						GuideTuanGuest guideTuanGuest = new GuideTuanGuest();
						guideTuanGuest.setGuestName(gts.getTouristName());
						guideTuanGuest.setMobile(gts.getTouristMobile());
						guideTuanGuest.setTuanNo(og.getGroupNo());
						guideTuanGuest.setOrderNo(gts.getOrderGoodsId());
						gtguests.add(guideTuanGuest);
					}
				}
			} else {
				logger.info("订单id:" + o.getOrderId() + "的商品信息为空！");
			}
		}
		Paginator p = new Paginator(pageNo, pageSize, gtguests.size());
		Integer pageEnd = pageNo * pageSize > p.getTotalCount() ? p.getTotalCount() : pageNo * pageSize;
		Integer pageStart = p.getOffset();
		model.addAttribute("list", gtguests.subList(pageStart, pageEnd));
		// model.addAttribute("list", gtguests);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("paginator", p);
		return "/admin/guideTuan/guest_list";
	}

	/**
	 * 近期出团
	 * 
	 * @param model
	 * @param pageNo
	 * @param pageSize
	 * @param groupNo
	 *            团编号
	 * @return
	 */
	@RequestMapping("/pre")
	public String pre(Model model, @RequestParam(defaultValue = Const.PAGE_NO, value = "pageNo") Integer pageNo,
			@RequestParam(defaultValue = Const.PAGE_SIZE, value = "pageSize") Integer pageSize, String groupNo,
			String startDate, String endDate) {
		// 根据订单接口查询出团
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderStatus", "W");
		params.put("groupNo", groupNo);
		params.put("pageSize", Integer.MAX_VALUE);
		params.put("startDate", getPreMonthStartDate(6));
		params.put("endDate", getNextMonthEndDate(6));
		params.put("startTripDate", startDate == null ? getPreMonthStartDate(1) : startDate);
		params.put("endTripDate", endDate == null ? getNextMonthEndDate(1) : endDate);
		String result = guideOrderService.getMangeList(params);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		Map<String, List<OrderGoodsModel>> groupNoMaps = new HashMap<String, List<OrderGoodsModel>>();
		// logger.info("订单总数：" + orders.size());
		// 根据groupNo 出团编号来分组。
		for (OrderModel o : orders) {

			if (o.getOrderGoods() != null && o.getOrderGoods().size() > 0) {
				for (OrderGoodsModel og : o.getOrderGoods()) {
					// logger.info("正在统计分组：" + og.getGroupNo());
					// logger.info("该商品的人数：" + og.getGoodsTourists().size());
					if (!groupNoMaps.containsKey(og.getGroupNo()))
						groupNoMaps.put(og.getGroupNo(), new ArrayList<OrderGoodsModel>());
					groupNoMaps.get(og.getGroupNo()).add(og);
				}
			} else {
				logger.info("订单id:" + o.getOrderId() + "的商品信息为空！");

			}

		}
		List<PreTuanModel> preTuanModels = new ArrayList<PreTuanModel>();
		// 根据分组来统做统计
		for (String g : groupNoMaps.keySet()) {
			//logger.info("正在统计团编号:" + g);
			Integer touristNum = 0;
			List<OrderGoodsModel> oms = groupNoMaps.get(g);
			PreTuanModel p = new PreTuanModel();
			p.setGroupNo(g);
			// 设置订单数
			p.setOrderNum(oms.size());
			// 统计该团下面的旅客人数
			for (OrderGoodsModel om : oms) {
				touristNum += om.getGoodsTourists().size();
				// logger.info("旅客人数：" + om.getGoodsTourists().size());
				p.setTripDate(om.getTripDate());
				p.setGoodsId(om.getGoodsId());
				p.setGoodsType(om.getGoodsType());
				p.setGoodsName(om.getGoodsName());
			}
			p.setTouristNum(touristNum);

			logger.info("该团总人数：" + touristNum);
			preTuanModels.add(p);

		}
		// 做是否出团过滤
		// 1.查询所有的团放到map里
		List<GuideTuan> gtuans = new ArrayList<GuideTuan>();
		Map<String, GuideTuan> gtuansMap = new HashMap<String, GuideTuan>();
		GuideTuan guideTuan = new GuideTuan();
		gtuans = guideTuanService.getGuideTuanPageList(guideTuan);
		for (GuideTuan gt : gtuans) {
			gtuansMap.put(gt.getTuanNo(), gt);
		}
		// 2.过滤
		Iterator<PreTuanModel> itRTM = preTuanModels.iterator();
		while (itRTM.hasNext()) {

			if (gtuansMap.get(itRTM.next().getGroupNo()) != null) {
				itRTM.remove();
			}
		}
		/*
		 * for (PreTuanModel ptm : preTuanModels) { // 只要本地库有这条记就说明有出团过，不显示 if
		 * (gtuansMap.get(ptm.getGroupNo()) != null) {
		 * preTuanModels.remove(ptm); } }
		 */
		Paginator p = new Paginator(pageNo, pageSize, preTuanModels.size());

		model.addAttribute("list", preTuanModels.subList(p.getOffset(),
				pageNo * pageSize > p.getTotalCount() ? p.getTotalCount() : pageNo * pageSize));
		model.addAttribute("paginator", p);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("startDate", startDate);
		model.addAttribute("endDate", endDate);
		return "/admin/guideTuan/list_pre";
	}

	/**
	 * 前几个月的开始时间
	 * 
	 * @return
	 */
	public String getPreMonthStartDate(Integer num) {
		String preMonthStart = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		// 前一个月
		Calendar calendarPre = Calendar.getInstance();
		calendarPre.add(Calendar.MONTH, -num);
		// 获取前一个月的开始时间
		Integer preMonthStartD = calendarPre.getMinimum(Calendar.DATE);
		calendarPre.set(Calendar.DATE, preMonthStartD);
		preMonthStart = simpledateformat.format(calendarPre.getTime());
		return preMonthStart;
	}

	/**
	 * 
	 * @param num
	 *            后几个月 的结束时间
	 * @return
	 */
	public String getNextMonthEndDate(Integer num) {
		String nextMonthEnd = "";
		SimpleDateFormat simpledateformat = new SimpleDateFormat("yyyyMMdd");
		// 下个月
		Calendar calendarNext = Calendar.getInstance();
		calendarNext.add(Calendar.MONTH, +num);
		// 获取下一个月的结束时间
		Integer nextMonthEndD = calendarNext.getMaximum(Calendar.DATE);
		calendarNext.set(Calendar.DATE, nextMonthEndD);
		nextMonthEnd = simpledateformat.format(calendarNext.getTime());
		return nextMonthEnd;
	}

	/**
	 * 已出团
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideTuan
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/success")
	public String success(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuan guideTuan,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		// ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			// guideTuan.setUserNo(shiroUser.getUserNo());
			guideTuan.setTuanStatus(ETuanStatus.TOURED.getId().byteValue());
			PageList<GuideTuan> list = guideTuanService.getGuideTuanPageList(guideTuan,
					new PageBounds(pageNo, pageSize));
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("name", guideTuan.getName());
			model.addAttribute("goodsNo", guideTuan.getGoodsNo());
			model.addAttribute("ETuanStatus", ETuanStatus.getByteMap());
			model.addAttribute("EGoodsType", EGoodsType.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "/admin/guideTuan/list_success";
	}

	/**
	 * 未出团
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideTuan
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancel")
	public String cancel(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuan guideTuan,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		// ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			// guideTuan.setUserNo(shiroUser.getUserNo());
			guideTuan.setTuanStatus(ETuanStatus.CANCEL.getId().byteValue());
			PageList<GuideTuan> list = guideTuanService.getGuideTuanPageList(guideTuan,
					new PageBounds(pageNo, pageSize));
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("name", guideTuan.getName());
			model.addAttribute("goodsNo", guideTuan.getGoodsNo());
			model.addAttribute("ETuanStatus", ETuanStatus.getByteMap());
			model.addAttribute("EGoodsType", EGoodsType.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "/admin/guideTuan/list_cancel";
	}

	/**
	 * 根据出团编号查询对应的成员名单
	 * 
	 * @param tuanNo
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tuanGuest/{tuanNo}")
	public String tuanGuest(@PathVariable String tuanNo,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, Model model) {
		GuideTuanGuest guest = new GuideTuanGuest();
		guest.setTuanNo(tuanNo);
		PageList<GuideTuanGuest> lsGuests = guideTuanGuestService.getGuideTuanGuestPageList(guest,
				new PageBounds(pageNo, pageSize));
		model.addAttribute("paginator", lsGuests != null ? lsGuests.getPaginator() : null);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "admin/guideTuan/guest_list";
	}

	/**
	 * 成员名单查询
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tuanGuest/search")
	public String searchGuest(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuanGuest guest,
			Model model) {
		PageList<GuideTuanGuest> lsGuests = guideTuanGuestService.getGuideTuanGuestPageList(guest,
				new PageBounds(pageNo, pageSize));
		model.addAttribute("paginator", lsGuests != null ? lsGuests.getPaginator() : null);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("mobile", guest.getMobile());
		model.addAttribute("guestName", guest.getGuestName());
		model.addAttribute("orderNo", guest.getOrderNo());
		model.addAttribute("tuanNo", guest.getTuanNo());
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "admin/guideTuan/guest_list";
	}

}
