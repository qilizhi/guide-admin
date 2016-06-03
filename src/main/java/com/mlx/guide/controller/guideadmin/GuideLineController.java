package com.mlx.guide.controller.guideadmin;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
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
import com.mlx.guide.constant.EGoodsType;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideLineTrip;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideLineTripService;
import com.mlx.guide.service.GuideTuanService;
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
	private GuideTuanService guideTuanService;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
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
	 * @param searchStr
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
	 * @param file
	 * @param request
	 * @param model
	 * @param guideLine
	 * @param oldPrice
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String saveOrUpdate(Model model, GuideLine guideLine,
			@RequestParam(value = "oldPrice") BigDecimal oldPrice) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			if (guideLine.getId() != null) {
				// 比较价格
				if (oldPrice.compareTo(guideLine.getPrice()) != 0) {
					guideLine.setAuditStatus(EAuditStatus.AUDIT_ON.getId());// 每次修改价格后审核状态都改为待审核
				}
				// 更新
				guideLineService.updateGuideLineSelective(guideLine);
				return "redirect:/guideAdmin/line/editPrice/" + guideLine.getLineNo();
			} else {
				// 新增
				// 随机生成线路编号
				guideLine.setLineNo(StringUtil.generateSerialNumber("L"));
				guideLine.setUserNo(shiroUser.getUserNo());
				guideLine.setUserName(shiroUser.getName());
				guideLine.setCreateTime(new Date());
				guideLine.setStatus(EStatus.EDIT.getId());
				guideLine.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideLineService.createGuideLineSelective(guideLine);
				model.addAttribute("guideLine", guideLine);
				return "redirect:/guideAdmin/line/editPrice/" + guideLine.getLineNo(); // 重定向到线路价格页面
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "guideAdmin/line/create";
		}
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

		String lineNo = guideLineDatePrice.getLineNo();
		// 根据线路no获取对应的线路
		GuideLine guideLine = guideLineService.getGuideLineByLineNo(lineNo);
		// 根据线路no获取对应的价格表
		List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
				.getGuideLineDatePriceByLineNo(lineNo);
		String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
		model.addAttribute("guideLine", guideLine);
		model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));

		return "guideAdmin/line/price";
	}

	/**
	 * 跳转到线路修改页面
	 * 
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
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save/{lineNo}", method = RequestMethod.POST)
	public JsonResult savePrice(@RequestParam("params") String linePrices, @PathVariable("lineNo") String lineNo) {
		try {
			// 获取当前用户
			ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
			//先删除旧价格，再保存新价格
			List<GuideLineDatePrice> lsGuideLineDatePrices = JSON.parseArray(linePrices, GuideLineDatePrice.class);
			guideLineDatePriceService.saveGuideLineDatePriceByLineNo(lsGuideLineDatePrices, lineNo);
			//获取当前线路
			GuideLine line = guideLineService.getGuideLineByLineNo(lineNo);
			//插入团信息
			GuideTuan tuan=new GuideTuan();
			for (GuideLineDatePrice g : lsGuideLineDatePrices) {
				tuan.setName(line.getTitle());
				int num=(int)(Math.random()*(9999-1000+1))+1000;
				tuan.setTuanNo("T"+System.currentTimeMillis()+num);
				tuan.setTuanDate(g.getLineDate());
				tuan.setGoodsType(EGoodsType.B.getCode());
				tuan.setGoodsNo(lineNo);
				tuan.setCreateTime(new Date());
				tuan.setFullNum(line.getNum());
				tuan.setTuanStatus(ETuanStatus.TOUR.getId().byteValue());
				tuan.setUserNo(shiroUser.getUserNo());
				tuan.setUserName(shiroUser.getName());
				guideTuanService.insertSelective(tuan);
			}

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
	 * @param guideLineDatePrice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToPrice/{lineNo}")
	public String backToPrice(@PathVariable String lineNo, GuideLineDatePrice guideLineDatePrice,
			@RequestParam String startDate, @RequestParam String endDate, Model model) {
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
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToTrip/{lineNo}")
	public String backToTrip(@PathVariable String lineNo, GuideLineTrip guideLineTrip, @RequestParam String startDate,
			@RequestParam String endDate, Model model) {
		try {
			List<GuideLineTrip> list = guideLineTripService.getGuideLineTripPageList(guideLineTrip);
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
	@RequestMapping(value = "/delLinePrcie/{lineNo}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delLinePrice(@PathVariable String lineNo, @RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime, HttpServletRequest request) {
		try {
			if (!beginTime.isEmpty() && !endTime.isEmpty()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("beginTime", beginTime);
				map.put("endTime", endTime);
				map.put("lineNo", lineNo);
				guideLineDatePriceService.deleteGuideLineDatePriceByDate(map);
				return new JsonResult(ExceptionCode.SUCCESSFUL);
			} else {
				return new JsonResult(ExceptionCode.FAIL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}

	/**
	 * 根据id获取线路信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/up/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult up(@PathVariable Integer id) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			return new JsonResult(ExceptionCode.SUCCESSFUL, guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}

	/**
	 * 修改上线，下线状态
	 * 
	 * @param guideLine
	 * @return
	 */
	@RequestMapping(value = "upAndDown")
	public String upAndDown(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideLine guideLine,
			Model model) {
		try {
			guideLineService.updateGuideLineSelective(guideLine);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/line/list";
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
