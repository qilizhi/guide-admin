package com.mlx.guide.controller.guideadmin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashMap;
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
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EProductNoPrefix;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.dao.GuideLineDatePriceMapper;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideLineTrip;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideLineTripService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;
import com.mlx.guide.util.StringUtil;

/**
 * 导游线路管理
 * 
 * @author cyz
 * @category 线路
 *
 */
@Controller
@RequestMapping("/guideAdmin/line")

public class GuideLineController {

	private static Logger logger = LoggerFactory.getLogger(GuideLineController.class);
	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private GuideLineDatePriceService guideLineDatePriceService;
	@Autowired
	private GuideLineTripService guideLineTripService;
	@Autowired
	private GuideLineDatePriceMapper priceMapper;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guide_lineclass", Const.MENU_FIRST);
	}

	/**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideLine
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideLine guideLine,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			guideLine.setFlag(EFlag.VALID.getId());
			guideLine.setUserNo(shiroUser.getUserNo());
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideLine> list = guideLineService.getGuideLinePageList(guideLine, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("guideLine", guideLine);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("EStatus", EStatus.getMap());
			model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/list";
	}

	/**
	 * 新增或更新
	 * 
	 * @param model
	 * @param guideLine
	 * @param oldPrice
	 * @param oldTotalDay
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOrUpdate(Model model, GuideLine guideLine, @RequestParam(value = "oldPrice") BigDecimal oldPrice,
			@RequestParam(value = "oldTotalDay") Integer oldTotalDay) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			if (guideLine.getId() != null) {
				// 比较价格
				if (oldPrice.compareTo(guideLine.getPrice()) != 0) {
					guideLine.setAuditStatus(EAuditStatus.AUDIT_ON.getId());// 每次修改价格后审核状态都改为待审核
				}
				// 比较天数，如果天数有改变就删除旧行程
				if (guideLine.getTotalDay() != oldTotalDay.intValue()) {
					guideLineTripService.deleteGuideLineTripByLineNo(guideLine.getLineNo());
					// 按照线路天数重新添加行程
					GuideLineTrip trip = new GuideLineTrip();
					for (int i = 1; i <= guideLine.getTotalDay(); i++) {
						trip.setLineNo(guideLine.getLineNo());
						trip.setDay(i);
						trip.setCreateTime(new Date());
						guideLineTripService.insertSelective(trip);
					}
				}
				// 更新
				guideLineService.updateGuideLineSelective(guideLine);
				return "redirect:/guideAdmin/line/editPrice/" + guideLine.getLineNo();
			} else {
				// 新增
				// 随机生成线路编号
				guideLine.setLineNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Line.getPrefix()));
				guideLine.setUserNo(shiroUser.getUserNo());
				guideLine.setUserName(shiroUser.getName());
				guideLine.setCreateTime(new Date());
				guideLine.setStatus(EStatus.EDIT.getId());
				guideLine.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideLineService.createGuideLineSelective(guideLine);
				// 按照线路天数添加行程
				GuideLineTrip trip = new GuideLineTrip();
				for (int i = 1; i <= guideLine.getTotalDay(); i++) {
					trip.setLineNo(guideLine.getLineNo());
					trip.setDay(i);
					trip.setCreateTime(new Date());
					guideLineTripService.insertSelective(trip);
				}
			}

			model.addAttribute("guideLine", guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/line/editPrice/" + guideLine.getLineNo(); // 重定向到线路价格页面
	}

	/**
	 * 编辑价格，根据线路编号获取价格表
	 * 
	 * @param guideLineDatePrice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editPrice/{lineNo}", method = RequestMethod.GET)
	public String editPrice(GuideLineDatePrice guideLineDatePrice, Model model) {

		try {
			String lineNo = guideLineDatePrice.getLineNo();
			// 根据线路no获取对应的线路
			GuideLine guideLine = guideLineService.getGuideLineByLineNo(lineNo);
			// 根据线路no获取对应的价格表
			List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
					.getGuideLineDatePriceByLineNo(lineNo);
			String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
			// 查询当前线路价格的开始时间和结束时间
			Map<String, Date> map = priceMapper.getLineDateByLineNo(lineNo);
			if (map != null) {
				Date startDate = map.get("startDate");
				Date endDate = map.get("endDate");
				model.addAttribute("startDate", startDate);
				model.addAttribute("endDate", endDate);
			}
			model.addAttribute("guideLine", guideLine);
			model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/price";
	}

	/**
	 * 跳转到线路修改页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			model.addAttribute("guideLine", guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/create";
	}

	/**
	 * 保存价格
	 * 
	 * @param linePrices
	 * @param lineNo
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save/{lineNo}", method = RequestMethod.POST)
	public JsonResult savePrice(@RequestParam("params") String linePrices, @PathVariable("lineNo") String lineNo,
			Model model) {
		try {
			// 先删除旧价格，再保存
			List<GuideLineDatePrice> lsGuideLineDatePrices = JSON.parseArray(linePrices, GuideLineDatePrice.class);
			guideLineDatePriceService.saveGuideLineDatePriceByLineNo(lsGuideLineDatePrices, lineNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 上一步,返回线路页面
	 * 
	 * @param lineNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToLine/{lineNo}")
	public String backToLine(@PathVariable String lineNo, Model model) {
		// 根据线路no获取对应的线路
		try {
			GuideLine guideLine = guideLineService.getGuideLineByLineNo(lineNo);
			model.addAttribute("guideLine", guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/create";
	}

	/**
	 * 上一步,返回价格页面
	 * 
	 * @param lineNo
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToPrice/{lineNo}")
	public String backToPrice(@PathVariable String lineNo, @RequestParam String startDate, @RequestParam String endDate,
			Model model) {
		try {
			// 根据线路no获取对应的线路
			GuideLine guideLine = guideLineService.getGuideLineByLineNo(lineNo);
			// 根据线路no获取对应的价格表
			List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
					.getGuideLineDatePriceByLineNo(lineNo);
			String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
			model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));
			model.addAttribute("guideLine", guideLine);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/price";
	}

	/**
	 * 上一步，返回到行程页面
	 * 
	 * @param lineNo
	 * @param guideLineTrip
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToTrip/{lineNo}")
	public String backToTrip(@PathVariable String lineNo, GuideLineTrip guideLineTrip, @RequestParam String startDate,
			@RequestParam String endDate, Model model) {
		try {
			PageBounds pageBounds = new PageBounds(1, Integer.MAX_VALUE, Order.formString("day.asc"));
			List<GuideLineTrip> list = guideLineTripService.getGuideLineTripPageList(guideLineTrip, pageBounds);
			model.addAttribute("list", list);
			model.addAttribute("lineNo", lineNo);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/line/lineTrip";
	}

	/**
	 * 删除对应线路的价格，按时间段删除
	 * 
	 * @param lineNo
	 * @param beginTime
	 * @param endTime
	 * @param request
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/delLinePrcie/{lineNo}", method =
	 * RequestMethod.POST)
	 * 
	 * @ResponseBody public JsonResult delLinePrice(@PathVariable String
	 * lineNo, @RequestParam("beginTime") String beginTime,
	 * 
	 * @RequestParam("endTime") String endTime, HttpServletRequest request) {
	 * try { if (!beginTime.isEmpty() && !endTime.isEmpty()) { Map<String,
	 * Object> map = new LinkedHashMap<String, Object>(); map.put("beginTime",
	 * beginTime); map.put("endTime", endTime); map.put("lineNo", lineNo);
	 * guideLineDatePriceService.deleteGuideLineDatePriceByDate(map); return new
	 * JsonResult(ExceptionCode.SUCCESSFUL); } else { return new
	 * JsonResult(ExceptionCode.FAIL); }
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage(), e); return new
	 * JsonResult(ExceptionCode.FAIL); } }
	 */

	/**
	 * 修改上线，下线状态
	 * 
	 * @param guideLine
	 * @return
	 */
	@RequestMapping(value = "/on")
	@ResponseBody
	public String on(GuideLine guideLine) {
		try {
			guideLineService.updateGuideLineSelective(guideLine);
			return "操作成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "系统异常,请稍后再试";
	}

	/**
	 * 跳转到线路新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String add() {
		return "guideAdmin/line/create";
	}

	/**
	 * 删除线路
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			guideLine.setFlag(EFlag.INVALID.getId());
			guideLineService.updateGuideLineSelective(guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/line/list";
	}

}
