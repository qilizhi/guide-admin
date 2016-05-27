package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideStrategy;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.GuideStrategyService;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value = "/admin/guideStrategy")
@Controller
public class GuideStrategyAdminController {

	private static Logger logger = LoggerFactory.getLogger(GuideStrategyAdminController.class);

	@Autowired
	private GuideStrategyService guideStrategyService;
	@Autowired
	private GuideInfoService guideInfoService;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("contentclass", Const.MENU_FIRST);
		model.addAttribute("content_strategyclass", Const.MENU_SUB);
		model.addAttribute("EStatus", EStatus.getMap());
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
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
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			GuideStrategy guideStrategy, HttpServletRequest request, Model model) {
		// 获取当前用户
		// ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			guideStrategy.setFlag(EFlag.VALID.getId());
			// guideStrategy.setUserNo(shiroUser.getUserNo());
			// guideStrategy.setUserNo("qilizhi");
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideStrategy> list = guideStrategyService.getGuideStrategyPageList(guideStrategy, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("guideStrategy", guideStrategy);
			model.addAttribute("title", guideStrategy.getTitle());
			model.addAttribute("status", guideStrategy.getStatus());
			model.addAttribute("auditStatus", guideStrategy.getAuditStatus());
			model.addAttribute("pageSize", pageSize);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/strategy/list";
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
	 * 编辑页面跳转
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, Model model) {
		GuideStrategy gs = guideStrategyService.getGuideStrategyByPrimaryKey(id);
		model.addAttribute("item", gs);
		return "/admin/strategy/detail";
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
	public JsonResult onOff(@PathVariable("id") Integer id, Integer status) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}

		// 检查是否审核通过
		if (guideStrategyService.getGuideStrategyByPrimaryKey(id).getAuditStatus() != EAuditStatus.AUDIT_OK.getId()) {
			return new JsonResult(ExceptionCode.FAIL, "审核不通过不能上线！");
		}
		GuideStrategy gs = new GuideStrategy();
		gs.setId(id);
		gs.setStatus(status);
		// 标志删除
		int result = 0;
		try {
			result = guideStrategyService.updateGuideStrategySelective(gs);
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
	public JsonResult audit(@PathVariable("id") Integer id, String auditRemark, Integer auditStatus) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}

		if (EAuditStatus.AUDIT_NOSUBMIT.getId() == guideStrategyService.getGuideStrategyByPrimaryKey(id)
				.getAuditStatus()) {
			return new JsonResult(ExceptionCode.FAIL, "未提交审核，不能审核");
		}
		GuideStrategy gs = new GuideStrategy();
		gs.setId(id);
		gs.setAuditRemark(auditRemark);
		gs.setAuditStatus(auditStatus);
		// 标志删除
		int result = 0;
		try {
			result = guideStrategyService.updateGuideStrategySelective(gs);
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
	public JsonResult batdDlete(@PathVariable("ids") String ids) {
		if (ids == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		String[] idsArray = ids.split(",");
		List<Integer> idsInteger = new ArrayList<Integer>();
		for (String id : idsArray) {
			if (id != null && !id.equals(""))
				idsInteger.add(Integer.parseInt(id));
		}
		// 标志删除

		try {
			guideStrategyService.deleteGuideStrategyBitch(idsInteger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 编辑
	 * 
	 * @param audioInfo
	 * @return
	 */

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "id") Integer id, Model model) {
		GuideStrategy guideStrategy = guideStrategyService.getGuideStrategyByPrimaryKey(id);
		model.addAttribute("guideStrategy", guideStrategy);
		model.addAttribute("statusMap", EStatus.getMap());
		model.addAttribute("flagMap", EFlag.getMap());
		model.addAttribute("auditStatusMap", EAuditStatus.getMap());
		model.addAttribute("operaTitle", "编辑");
		return "admin/strategy/create";
	}

	/**
	 * 保存信息
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdate", method = RequestMethod.POST)
	public String save(GuideStrategy guideStrategy) {
		if (guideStrategy == null) {
			return null;
		}
		try {
			if (guideStrategy.getId() != null && guideStrategy.getId() != 0) {
				guideStrategy.setUpdateTime(new Date());
				guideStrategyService.updateGuideStrategySelective(guideStrategy);
			} else {
				guideStrategy.setStatus(EStatus.EDIT.getId());
				guideStrategy.setCreateTime(new Date());
				guideStrategy.setUpdateTime(new Date());
				guideStrategy.setFlag(EFlag.VALID.getId());
				guideStrategy.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideStrategyService.createGuideStrategySelective(guideStrategy);
			}
			return "redirect:/admin/guideStrategy";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
	}

	/**
	 * 创建
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(Model model) {
		model.addAttribute("operaTitle", "新增");
		return "admin/strategy/create";
	}

	/**
	 * 序号修改
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping(value = "updateSort/{id}")
	@ResponseBody
	public JsonResult updateSort(@PathVariable(value = "id") Integer id, @RequestParam Integer sort) {
		JsonResult ajaxResult = null;
		GuideStrategy guideStrategy = new GuideStrategy();
		guideStrategy.setId(id);
		guideStrategy.setSort(sort);
		try {
			guideStrategyService.updateGuideStrategySelective(guideStrategy);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, "内部出现错误");
		}
		return ajaxResult;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable(value = "id") Integer id) {
		JsonResult ajaxResult = null;
		try {
			guideStrategyService.deleteGuideStrategy(id);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            id集合,例如:1,2,3,4
	 * @return
	 */
	@RequestMapping(value = "/deletes/{ids}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable String ids) {
		JsonResult ajaxResult = null;
		try {
			List<Integer> idList = StringUtil.generateListInteger(ids);
			guideStrategyService.deleteGuideStrategyBitch(idList);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 单个审核
	 * 
	 * @param id
	 *            例如:1
	 * @param auditRemark
	 *            审核备注说明
	 * @param auditStatus
	 *            审核状态
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping( value = "/audit/{id}", method = RequestMethod.POST,
	 * produces = MediaType.APPLICATION_JSON_VALUE ) public JsonResult
	 * audits( @PathVariable int id, @RequestParam String
	 * auditRemark, @RequestParam Integer auditStatus ) { JsonResult ajaxResult
	 * = null; try { guideStrategyService.updateAuditStatusBitch( auditRemark,
	 * auditStatus, id ); ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL
	 * ); } catch (Exception e) { logger.error( e.getMessage(),e ); ajaxResult =
	 * new JsonResult( ExceptionCode.FAIL, e.getMessage() ); } return
	 * ajaxResult; }
	 */

	/**
	 * 批量审核
	 * 
	 * @param ids
	 *            id集合,例如:1,2,3,4
	 * @param auditRemark
	 *            审核备注说明
	 * @param auditStatus
	 *            审核状态
	 * @return
	 */
	/*
	 * @ResponseBody
	 * 
	 * @RequestMapping( value = "/audits", method = RequestMethod.POST, produces
	 * = MediaType.APPLICATION_JSON_VALUE ) public JsonResult
	 * audits( @RequestParam String ids, @RequestParam String
	 * auditRemark, @RequestParam Integer auditStatus ) { JsonResult ajaxResult
	 * = null; try { int[] lsIds = StringUtil.generateIntArray( ids ); if(lsIds
	 * == null){ return new JsonResult( ExceptionCode.FAIL, "id参数出现错误" ); }
	 * guideStrategyService.updateAuditStatusBitch( auditRemark, auditStatus,
	 * lsIds ); ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL ); } catch
	 * (Exception e) { logger.error( e.getMessage(),e ); ajaxResult = new
	 * JsonResult( ExceptionCode.FAIL, e.getMessage() ); } return ajaxResult; }
	 */
}