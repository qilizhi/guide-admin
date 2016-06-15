
package com.mlx.guide.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.github.miemiedev.mybatis.paginator.domain.Paginator;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.ELineType;
import com.mlx.guide.constant.ESignInStatus;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.constant.OrderPayType;
import com.mlx.guide.entity.EmGroup;
import com.mlx.guide.entity.EmUser;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.GuideTuanGuest;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.EmUserModel;
import com.mlx.guide.model.OrderGoodsModel;
import com.mlx.guide.model.OrderGoodsModel.GoodsTourists;
import com.mlx.guide.model.OrderModel;
import com.mlx.guide.model.PreTuanModel;
import com.mlx.guide.service.EasemobClientService;
import com.mlx.guide.service.EmGroupService;
import com.mlx.guide.service.EmGroupUserService;
import com.mlx.guide.service.EmUserService;
import com.mlx.guide.service.GuideOrderService;
import com.mlx.guide.service.GuideTuanGuestService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.service.UserInfoService;
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
	private UserInfoService userInfoService;
	@Autowired
	private GuideOrderService guideOrderService;
	@Autowired
	private EasemobClientService easemobClientService;
	@Autowired
	private EmGroupService groupService;
	@Autowired
	private EmGroupUserService emGroupUserService;
	@Autowired
	private EmUserService emUserService;

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
			model.addAttribute("guideTuan",guideTuan);
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

		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		
		

		// 1 查询该团下的订单
		logger.info("1 查询该团下的订单");
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("orderStatus", "W");
		params.put("groupNo", gt.getTuanNo());
		params.put("pageSize", Integer.MAX_VALUE);
		// params.put("startDate", getPreMonthStartDate(6));// 暂定前后6个月
		// params.put("endDate", getNextMonthEndDate(6));
		String result = guideOrderService.getMangeList(params);
		logger.info("订单结果："+result);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
			return new JsonResult(ExceptionCode.FAIL, "调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		// 2. 将用户插入到tuanGuest
		logger.info("2 将用户插入到tuanGuest");
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
			return new JsonResult(ExceptionCode.FAIL, "客户信息为空！");
		}
        logger.info("查询该团数据！");
		List<GuideTuan> guideTuans = guideTuanService.getGuideTuanPageList(gt);
		GuideTuan opTuan = guideTuans.size() > 0 ? guideTuans.get(0) : null;
		opTuan.setTuanStatus(ETuanStatus.TOURED.getId().byteValue());
		opTuan.setPersonNum(guideTuanGuests.size());
		opTuan.setOrderNum(orders.size());
		//查询该 用户的环信账号如果没有则创建一个并插入到emGroup
/*		EmUser 	guideemUser=emUserService.selectByUserNo(opTuan.getUserNo());
		if(guideemUser==null){		
			//创建环信用户
			String userResult=easemobClientService.createUser(opTuan.getUserNo(), "123456",opTuan.getUserName());
		    if(userResult!=null){
		    	JSONArray userObject=(JSONArray) JSON.parseObject(userResult).get("entities");
		    	List<EmUserModel> emUser = JSONArray.parseArray(userObject.toString(),EmUserModel.class);
				// 插入表里
		    	EmUser temUser=new EmUser();
		    	temUser.setEmUuid(emUser.get(0).getUuid());
		    	temUser.setEmUser(emUser.get(0).getUsername());
		    	temUser.setUserNo(opTuan.getUserNo());
		    	guideemUser=temUser;
		    	emUserService.insertSelective(guideemUser);		    	
		    }
		}*/
		//从user表里查找
		UserInfo userinfo = new UserInfo();
		userinfo.setUserNo(opTuan.getUserNo());
		List<UserInfo> userInfos = userInfoService.getUserInfoPageList(userinfo);
		userinfo=userInfos.size()>0?userInfos.get(0):null;
		
		// 3.从环信创建一个群。
		EmGroup group = new EmGroup();
		String createResult = null;
		JSONObject data = null;
		if (opTuan != null) {
			try {
				createResult = easemobClientService.createGroup(opTuan.getTuanNo(),
						opTuan.getName() + "groupNo:" + opTuan.getTuanNo(), true, opTuan.getFullNum(), true,
						userinfo.getHuanxinAccount());
			} catch (Exception e) {
				logger.info("环信群创建失败：" + e.getMessage());
				return new JsonResult(ExceptionCode.FAIL);

			}
		}
		if (createResult != null) {

		}
		data = JSON.parseObject(createResult).getJSONObject("data");

		Long groupId = null;
		if (data != null) {
			groupId = data.getLong("groupid");
		}
		if (groupId == null) {
			return new JsonResult(ExceptionCode.FAIL, "调用环信接口失败！返回数据为空！");
		}
		group.setEmGname(gt.getUserName());
		group.setEmGdesc(gt.getName() + "groupNo:" + gt.getTuanNo());
		group.setEmGid(groupId);
		group.setCreateTime(new Date());
		// 应是当前登录的用户。 从shiroUser 里获取
		group.setUserNo(userinfo.getUserNo());
		logger.info("环信群创建成功:" + data.toJSONString());

		// 5.把订单人员拉入群里。
		// 5.1查找用户的环信用户帐号集合
		List<String> emuserNos = new ArrayList<String>();
		for (OrderModel order : orders) {
			UserInfo userinfo1 = new UserInfo();
			userinfo1.setUserNo(order.getUserId());
			List<UserInfo> userInfo = userInfoService.getUserInfoPageList(userinfo);
			if (userInfo.size() > 0) {
				String account = userInfo.get(0).getHuanxinAccount();
				if (!account.equals("") && account != null) {
					emuserNos.add(account);
				} else {
					logger.info("本地userInfo查找不到用户的环信账号");
				}

			} else {

				logger.info("找不到该用户：" + order.getUserId());
			}
		}
		// 5.2插入到群里
		// 调用接口将人拉入群里
		try {
			easemobClientService.addUsersGroup(emuserNos, data.getString("groupid"));
		} catch (Exception e1) {
			//
			// e1.printStackTrace();
			logger.info("调用把人拉入群里出错" + e1.getMessage());
			return new JsonResult(ExceptionCode.FAIL, e1.getMessage());
		}
		try {
			// 在本地建立关系。
			emGroupUserService.insertGroupUsers(emuserNos, groupId);
			// 插入到本地群里。
			groupService.insertSelective(group);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			logger.info("本地建立关系出错" + e1.getMessage());
			return new JsonResult(ExceptionCode.FAIL, e1.getMessage());
		}
		// 3.3.并修改状态为已出团,订单人数及实际人数
		try {

			guideTuanService.updateByPrimaryKeySelective(opTuan);
			logger.info("更新数据成功。data:" + opTuan.toString());
		} catch (Exception e) {
			logger.info("guideTuan数据库更新出错！");
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	@RequestMapping(value = "/submit/cancel", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult groupCancel(GuideTuan gt, Model model) {

		List<GuideTuan> guideTuans = guideTuanService.getGuideTuanPageList(gt);
		GuideTuan opTuan = guideTuans.size() > 0 ? guideTuans.get(0) : null;
		// 2.插入一条出团记录到出团表里，并修改状态为已取消出团；
		opTuan.setTuanStatus(ETuanStatus.CANCEL.getId().byteValue());
		guideTuanService.updateByPrimaryKeySelective(opTuan);
		// 4.申请退款
		// 4.1 查询该团下的订单
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("orderStatus", "W");
		params.put("groupNo", gt.getTuanNo());
		params.put("pageSize", Integer.MAX_VALUE);
		String result = guideOrderService.getMangeList(params);
		logger.info("订单查询返回的结果：" + result);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		// 4.3 调用退款接口退款
		for (OrderModel o : orders) {
			Map<String, Object> p = new HashMap<String, Object>();
			p.put("userId", o.getUserId());
			p.put("orderId", o.getOrderId());
			String applyResult = guideOrderService.refundApply(p);
			logger.info("申请退款返回的结果：" + applyResult);
			String resultCode = JSON.parseObject(applyResult).get("code").toString();
			if (!resultCode.equals("0000")) {
				logger.info("订单：" + o.getOrderId() + "申请退款失败！");
			}
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 出团详情 查看订单列表。
	 * 
	 * @param groupNo
	 * @param pageNo
	 * @param pageSize
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping("/order/detail/{groupNo}")
	public String detail(@PathVariable("groupNo") String groupNo,
			@RequestParam(defaultValue = Const.PAGE_NO, value = "pageNo") Integer pageNo,
			@RequestParam(defaultValue = Const.PAGE_SIZE, value = "pageSize") Integer pageSize, String startDate,
			String endDate, String mobile, String orderId, String orderStatus, String userName, Model model,
			HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();
		// params.put("orderStatus", "W");
		params.put("groupNo", groupNo);
		params.put("pageSize", Integer.MAX_VALUE);
		params.put("mobile", mobile);
		params.put("orderId", orderId);
		params.put("orderStatus", orderStatus);
		params.put("userName", userName);
		// params.put("startTripDate", startDate == null ?
		// getPreMonthStartDate(1) : startDate);
		// params.put("endTripDate", endDate == null ? getNextMonthEndDate(1) :
		// endDate);
		String result = guideOrderService.getMangeList(params);
		if ("".equals(result) || result == null) {
			logger.error("调用订单接口出错。返回数结果为空！请检查网络！");
		}
		List<OrderModel> orders = JSONArray.parseArray(JSON.parseObject(result).get("result").toString(),
				OrderModel.class);
		/*
		 * List<GuideTuanGuest> gtguests = new ArrayList<GuideTuanGuest>(); //
		 * 取出参团成员信息 for (OrderModel o : orders) { if (o.getOrderGoods() != null
		 * && o.getOrderGoods().size() > 0) { for (OrderGoodsModel og :
		 * o.getOrderGoods()) { for (GoodsTourists gts : og.getGoodsTourists())
		 * { GuideTuanGuest guideTuanGuest = new GuideTuanGuest();
		 * guideTuanGuest.setGuestName(gts.getTouristName());
		 * guideTuanGuest.setMobile(gts.getTouristMobile());
		 * guideTuanGuest.setTuanNo(og.getGroupNo());
		 * guideTuanGuest.setOrderNo(gts.getOrderGoodsId());
		 * gtguests.add(guideTuanGuest); } } } else { logger.info("订单id:" +
		 * o.getOrderId() + "的商品信息为空！"); } }
		 */
		Paginator p = new Paginator(pageNo, pageSize, orders.size());
		Integer pageEnd = pageNo * pageSize > p.getTotalCount() ? p.getTotalCount() : pageNo * pageSize;
		Integer pageStart = p.getOffset();
		model.addAttribute("list", orders.subList(pageStart, pageEnd));
		model.addAttribute("countlist", orders);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("groupNo", groupNo);
		GuideTuan t = new GuideTuan();
		t.setTuanNo(groupNo);
		List<GuideTuan> ts = guideTuanService.getGuideTuanPageList(t);
		model.addAttribute("tuan", ts.size() > 0 ? ts.get(0) : null);
		model.addAttribute("mobile", mobile);
		model.addAttribute("orderId", orderId);
		model.addAttribute("orderStatus", orderStatus);
		model.addAttribute("userName", userName);
		model.addAttribute("paginator", p);
		model.addAttribute("payStatus", OrderPayType.getMap());
		return "/admin/guideTuan/order_list";
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
		// params.put("startDate", getPreMonthStartDate(6));
		// params.put("endDate", getNextMonthEndDate(6));
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
			// logger.info("正在统计团编号:" + g);
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

		guest.setStatus(ESignInStatus.SIGNED.getId().byteValue());
		PageList<GuideTuanGuest> guests = guideTuanGuestService.getGuideTuanGuestPageList(guest,
				new PageBounds(1, Integer.MAX_VALUE));
		model.addAttribute("paginator", lsGuests != null ? lsGuests.getPaginator() : null);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("signNum", guests.size());
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);

		GuideTuan t = new GuideTuan();
		t.setTuanNo(tuanNo);
		List<GuideTuan> ts = guideTuanService.getGuideTuanPageList(t);
		model.addAttribute("tuan", ts.size() > 0 ? ts.get(0) : null);
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "admin/guideTuan/guest_list";
	}

	/**
	 * 签到
	 * 
	 * @param tuanNo
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tuanGuest/sign/{id}")
	@ResponseBody
	public JsonResult tuanGuest(@PathVariable("id") Long id) {
		GuideTuanGuest gtg = new GuideTuanGuest();
		gtg.setId(id);
		gtg.setStatus(ESignInStatus.SIGNED.getId().byteValue());
		int r = 0;
		try {
			r = guideTuanGuestService.updateByPrimaryKeySelective(gtg);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return new JsonResult(ExceptionCode.FAIL, "更新出错！");
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, r);
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
