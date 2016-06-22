package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
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
import org.springframework.web.bind.annotation.SessionAttributes;

import com.alibaba.fastjson.JSON;
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
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideLineTrip;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.entity.GuideServicePrice;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideServiceService;
import com.mlx.guide.util.StringUtil;

@Controller
@RequestMapping(value = "/admin/guideService")
public class GuideServiceAdminController {

	private Logger logger = LoggerFactory.getLogger(GuideServiceAdminController.class);
	@Autowired
	private GuideServiceService guideService;
	@Autowired
	private GuideInfoService guideInfoService;
	@Autowired
	private GuideLineDatePriceService guideDPService;
	@Autowired
	private GuideLineDatePriceService guideLineDatePriceService;
	@Autowired
	private GuideLineDatePriceMapper priceMapper;

	@ModelAttribute
	public void commod(Model model) {
		model.addAttribute("productclass", Const.MENU_FIRST);
		model.addAttribute("product_guideServiceclass", Const.MENU_SUB);
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		model.addAttribute("Status", EStatus.getMap());
	}

	/**
	 * 详情页面跳转
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		GuideService gs = guideService.selectByPrimaryKey(id);
		model.addAttribute("guideService", gs);

		return "/admin/guideService/detail";
	}

	/**
	 * 创建跳转
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("operaTitle", "新增");
		return "admin/guideService/create";
	}

	/**
	 * 查询导游用户列表下拉树。
	 * 
	 * @param guideInfo
	 * @return
	 */
	@RequestMapping(value = "/guide/list", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult getUserList(GuideInfo guideInfo) {

		List<GuideInfo> guides = new ArrayList<GuideInfo>();
		try {
			guides = guideInfoService.getGuideInfoList(guideInfo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("获取用户出错：" + e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, guides);
	}

	/**
	 * 列表
	 * 
	 * @param gs
	 * @param page
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(GuideService gs, @RequestParam(defaultValue = Const.PAGE_NO) Integer pageNo,
			@RequestParam(defaultValue = Const.PAGE_SIZE) Integer pageSize, Model model) {
		gs.setFlag(EFlag.VALID.getId());
		PageList<GuideService> list = guideService.listByExample(gs, new PageBounds(pageNo, pageSize));
		model.addAttribute("paginator", list != null ? list.getPaginator() : null);
		model.addAttribute("list", list);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("EStatus", EStatus.getMap());
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		model.addAttribute("status", gs.getStatus());
		model.addAttribute("auditStatus", gs.getAuditStatus());
		model.addAttribute("title", gs.getTitle());
		model.addAttribute("serviceNo", gs.getServiceNo());
		return "admin/guideService/list";
	}

	/**
	 * 编辑跳转
	 * 
	 * @param audioInfo
	 * @return
	 */

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "id") Long id, Model model) {
		GuideService guideS = guideService.selectByPrimaryKey(id);
		model.addAttribute("guideS", guideS);
		model.addAttribute("statusMap", EStatus.getMap());
		model.addAttribute("flagMap", EFlag.getMap());
		model.addAttribute("auditStatusMap", EAuditStatus.getMap());
		model.addAttribute("operaTitle", "编辑");
		return "admin/guideService/create";
	}

	/**
	 * 保存信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String save(GuideService guideS) {

		try {
			if (guideS.getId() != null) {
				guideS.setUpdateTime(new Date());
				guideService.updateByPrimaryKeySelective(guideS);
				return "redirect:/admin/guideService/price/" + guideS.getServiceNo();

			} else {
				String gServiceNo = StringUtil.generateProductSerialNumber(EProductNoPrefix.Service.getPrefix());
				guideS.setStatus(EStatus.EDIT.getId());
				guideS.setServiceNo(gServiceNo);
				guideS.setCreateTime(new Date());
				guideS.setUpdateTime(new Date());
				guideS.setFlag(EFlag.VALID.getId());
				guideS.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideService.insertSelective(guideS);
				return "redirect:/admin/guideService/price/" + guideS.getServiceNo();
			}
			// guideS=guideService.selectByPrimaryKey(id.longValue());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 保存价格
	 * 
	 * @param linePrices
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping(value = "/price/save/{serviceNo}", method =
	 * RequestMethod.POST) public JsonResult savePrice(@RequestParam("params")
	 * String linePrices,
	 * 
	 * @PathVariable("serviceNo") String serviceNo,Model model) { try {
	 * List<GuideLineDatePrice> lsGuideLineDatePrices =
	 * JSON.parseArray(linePrices, GuideLineDatePrice.class);
	 * guideLineDatePriceService.saveGuideLineDatePriceByServiceNo(
	 * lsGuideLineDatePrices, serviceNo);
	 * 
	 * } catch (Exception e) { logger.error(e.getMessage(), e); }
	 * 
	 * return new JsonResult(ExceptionCode.SUCCESSFUL); }
	 */

	@ResponseBody
	@RequestMapping(value = "/price/save/{lineNo}", method = RequestMethod.POST)
	public JsonResult savePrice(@RequestParam("params") String linePrices,
			@PathVariable(value = "lineNo") String serviceNo, Model model) {
		try {
			List<GuideLineDatePrice> lsGuideLineDatePrices = JSON.parseArray(linePrices, GuideLineDatePrice.class);
			guideLineDatePriceService.saveGuideLineDatePriceByServiceNo(lsGuideLineDatePrices, serviceNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 上一步,返回导服页面
	 * 
	 * @param serviceNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToSercice/{serviceNo}")
	public String backToSercice(@PathVariable String serviceNo, Model model) {
		try {
			GuideService service = guideService.getGuideServiceByServiceNo(serviceNo);
			model.addAttribute("guideS", service);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guideService/create";
	}

	/**
	 * 上一步,返回价格页面
	 * 
	 * @param serviceNo
	 * @param startDate
	 * @param endDate
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToPrice/{serviceNo}")
	public String backToPrice(@PathVariable String serviceNo, @RequestParam String startDate,
			@RequestParam String endDate, Model model) {
		try {
			// 获取导服
			GuideService service = guideService.getGuideServiceByServiceNo(serviceNo);
			// 获取对应的价格表
			List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
					.getGuideLineDatePriceByLineNo(serviceNo);
			String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
			model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));
			model.addAttribute("service", service);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guideService/price";
	}

	/**
	 * 更新信息
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping(value = "/price/{guideServiceNo}", method =
	 * RequestMethod.GET) public String
	 * editPrice(@PathVariable("guideServiceNo") String guideServiceNo, Model
	 * model) { GuideService guideS =
	 * guideService.getGuideServiceByServiceNo(guideServiceNo); //
	 * 根据线路no获取对应的价格表 List<GuideLineDatePrice> lsGuideLineDatePrices =
	 * guideDPService.getGuideLineDatePriceByLineNo(guideServiceNo); String
	 * jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices,
	 * "yyyy-MM-dd"); // 查询当前线路价格的开始时间和结束时间 Map<String, Date> map = new
	 * HashMap<>(); try { map = priceMapper.getLineDateByLineNo(guideServiceNo);
	 * } catch (Exception e) { logger.error(e.getMessage(), e); } Date startDate
	 * = map.get("startDate"); Date endDate = map.get("endDate");
	 * model.addAttribute("guideS", guideS);
	 * model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData,
	 * "[]")); model.addAttribute("startDate", startDate);
	 * model.addAttribute("endDate", endDate); return
	 * "/admin/guideService/price"; }
	 */
	/**
	 * 编辑价格，根据线路编号获取价格表
	 * 
	 * @param guideLineDatePrice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/price/{lineNo}", method = RequestMethod.GET)
	public String editPrice(GuideLineDatePrice guideLineDatePrice, Model model) {

		String lineNo = guideLineDatePrice.getLineNo();
		// 根据导服no获取对应的导服
		GuideService service = guideService.getGuideServiceByServiceNo(lineNo);
		// 根据导服no获取对应的价格表
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

		model.addAttribute("service", service);
		model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));

		return "admin/guideService/price";
	}

	/**
	 * 跳转
	 * 
	 * @return
	 */
	/*
	 * @RequestMapping("/submit/{id}") public String submit(@PathVariable("id")
	 * Long id, Model model) { GuideService gs =
	 * guideService.selectByPrimaryKey(id); // 价格 List<GuideLineDatePrice>
	 * lsGuideLineDatePrices = guideLineDatePriceService
	 * .getGuideLineDatePriceByLineNo(gs.getServiceNo());
	 * model.addAttribute("line", gs); model.addAttribute("lsPrices",
	 * lsGuideLineDatePrices); return "/admin/guideService/submit";
	 * 
	 * }
	 */
	@RequestMapping(value = "/submit")
	public String submit(@RequestParam("serviceNo") String serviceNo, @RequestParam String startDate,
			@RequestParam String endDate, Model model) {
		try {
			model.addAttribute("serviceNo", serviceNo);
			// 导服
			GuideService service = guideService.getGuideServiceByServiceNo(serviceNo);
			model.addAttribute("line", service);
			// 价格
			List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
					.getGuideLineDatePriceByLineNo(serviceNo);
			model.addAttribute("lsPrices", lsGuideLineDatePrices);
			model.addAttribute("startDate", startDate);
			model.addAttribute("endDate", endDate);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "admin/guideService/submit";
	}

	@RequestMapping("/submitOn/{id}")
	public String submit(@PathVariable("id") Long id, GuideService gs) {
		gs.setId(id);
		gs.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
		// 价格
		guideService.updateByPrimaryKeySelective(gs);
		return "redirect:/admin/guideService";

	}

	/**
	 * 上下线功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/onOff/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult onOff(@PathVariable("id") Long id, Integer status) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}

		// 检查是否审核通过
		if (guideService.selectByPrimaryKey(id).getAuditStatus() != EAuditStatus.AUDIT_OK.getId()) {
			if (status == EStatus.ONLINE.getId())
				return new JsonResult(ExceptionCode.FAIL, "审核不通过不能上线！");
			if (status == EStatus.OFFLINE.getId())
				return new JsonResult(ExceptionCode.FAIL, "审核不通过不能下线！");
		}
		GuideService gs = new GuideService();
		gs.setId(id);
		gs.setStatus(status);
		// 标志删除
		int result = 0;
		try {
			result = guideService.updateByPrimaryKeySelective(gs);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 审核功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/audit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult audit(@PathVariable("id") Long id, String auditRemark, Integer auditStatus) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}

		if (EAuditStatus.AUDIT_NOSUBMIT.getId() == guideService.selectByPrimaryKey(id).getAuditStatus()) {
			return new JsonResult(ExceptionCode.FAIL, "未提交审核，不能审核");
		}
		GuideService gs = new GuideService();
		gs.setId(id);
		gs.setAuditRemark(auditRemark);
		gs.setAuditStatus(auditStatus);
		// 标志删除
		int result = 0;
		try {
			result = guideService.updateByPrimaryKeySelective(gs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());

		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 删除功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(@PathVariable("ids") String ids) {
		if (ids == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		String[] idsArray = ids.split(",");
		// 标志删除
		int result = 0;
		try {
			result = guideService.batDelByflag(idsArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

}
