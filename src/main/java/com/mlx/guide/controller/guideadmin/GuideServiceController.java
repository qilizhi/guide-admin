package com.mlx.guide.controller.guideadmin;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideServiceService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;
import com.mlx.guide.util.StringUtil;

/**
 * 导服控制器
 * 
 * @author cyz
 * @category 导服
 *
 */
@RequestMapping(value = "/guideAdmin/guideService")
@Controller
public class GuideServiceController {

	private static Logger logger = LoggerFactory.getLogger(GuideServiceController.class);

	@Autowired
	private GuideServiceService guideServiceService;
	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private GuideLineDatePriceService guideLineDatePriceService;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guideService_localclass", Const.MENU_FIRST);
	}

	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			GuideService guideService, HttpServletRequest request, Model model) {

		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			guideService.setUserNo(shiroUser.getUserNo());
			guideService.setFlag(EFlag.VALID.getId());
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideService> list = guideServiceService.getGuideServicePageList(guideService, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("guideService", guideService);
			model.addAttribute("EStatus", EStatus.getMap());
			model.addAttribute("EAuditStatus", EAuditStatus.getMap());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "guideAdmin/guideService/list";
	}

	/**
	 * 新增或更新
	 * 
	 * @param file
	 * @param request
	 * @param model
	 * @param guideService
	 * @param oldPrice
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String add(Model model, GuideService guideService, @RequestParam(value = "oldPrice") BigDecimal oldPrice) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
	
			// 更新
			if (guideService.getId() != null) {
				// 获取修改前的价格，如果价格有改动就通知财务审核
				if (oldPrice.compareTo(guideService.getPrice()) != 0) {
					guideService.setAuditStatus(EAuditStatus.AUDIT_ON.getId());// 每次修改价格后审核状态都改为待审核
				}
				guideService.setUpdateTime(new Date());
				guideServiceService.updateByPrimaryKeySelective(guideService);
			} else {
				// 新增
				// 随机生成编号
				guideService.setServiceNo(StringUtil.generateProductSerialNumber(EProductNoPrefix.Service.getPrefix()));
				guideService.setUserNo(shiroUser.getUserNo());
				guideService.setUserName(shiroUser.getName());
				guideService.setCreateTime(new Date());
				guideService.setStatus(EStatus.EDIT.getId());
				guideServiceService.insertSelective(guideService);
			}
		return "redirect:/guideAdmin/guideService/editPrice/" + guideService.getServiceNo();
	}

	/**
	 * saveAndNext
	 */
	@RequestMapping(value = "/submit")
	public String submit(Model model) {
		try {
			// 导服
//			GuideService service = guideServiceService.getGuideServiceByServiceNo(serviceNo);
//			model.addAttribute("service", service);
			// 价格
//			List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
//					.getGuideLineDatePriceByLineNo(serviceNo);
//			model.addAttribute("lsPrices", lsGuideLineDatePrices);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "guideAdmin/guideService/submit";
	}

	/**
	 * 上一步,返回导服页面
	 * 
	 * @param lineNo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "backToSercice/{lineNo}")
	public String backToSercice(@PathVariable String serviceNo, Model model) {
		try {
			GuideService service = guideServiceService.getGuideServiceByServiceNo(serviceNo);
			model.addAttribute("service", service);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/guideService/create";
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
		// 根据导服no获取对应的导服
		GuideService service = guideServiceService.getGuideServiceByServiceNo(lineNo);
		// 根据导服no获取对应的价格表
		List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
				.getGuideLineDatePriceByLineNo(lineNo);
		String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
		model.addAttribute("service", service);
		model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));
		return "guideAdmin/guideService/price";
	}

	/**
	 * 保存导服价格
	 * 
	 * @param linePrices
	 *            价格集合
	 * @param serviceNo
	 *            导服编号
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save/{lineNo}", method = RequestMethod.POST)
	public JsonResult savePrice(@RequestParam("params") String linePrices,
			@PathVariable(value = "lineNo") String serviceNo, Model model) {
		try {
			// 先删除所有旧价格，再保存新价格
			List<GuideLineDatePrice> lsGuideLineDatePrices = JSON.parseArray(linePrices, GuideLineDatePrice.class);
			guideLineDatePriceService.saveGuideLineDatePriceByServiceNo(lsGuideLineDatePrices, serviceNo);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
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
			GuideService gService = guideServiceService.selectByPrimaryKey(new Long(id));
			return new JsonResult(ExceptionCode.SUCCESSFUL, gService);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}

	/**
	 * 修改上线，下线状态
	 * 
	 * @param guideService
	 * @return
	 */
	@RequestMapping(value = "/upAndDown")
	public String upAndDown(GuideService guideService) {
		try {
			guideServiceService.updateByPrimaryKeySelective(guideService);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/guideService";
	}

	/**
	 * 编辑
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		try {
			GuideService guideService = guideServiceService.selectByPrimaryKey(id);
			model.addAttribute("guideService", guideService);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "/guideAdmin/guideService/create";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/create")
	public String create() {
		return "/guideAdmin/guideService/create";
	}

	/**
	 * 详情预览
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model) {
		try {
			GuideService gs = guideServiceService.selectByPrimaryKey(id);
			model.addAttribute("guideService", gs);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "/guideAdmin/guideService/detail";
	}

}
