package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EProductNoPrefix;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.GuideServiceService;
import com.mlx.guide.util.StringUtil;

@Controller
@RequestMapping(value = "/admin/guideService")
@SessionAttributes(value="test")
public class GuideServiceAdminController {

	private Logger logger = LoggerFactory.getLogger(GuideServiceAdminController.class);
	@Autowired
	private GuideServiceService guideService;
	@Autowired
	private GuideInfoService guideInfoService;

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
	public String list(GuideService gs, @RequestParam(defaultValue = Const.PAGE_NO) Integer page,
			@RequestParam(defaultValue = Const.PAGE_SIZE) Integer pageSize, Model model) {
		gs.setFlag(EFlag.VALID.getId());
		PageList<GuideService> list = guideService.listByExample(gs, new PageBounds(page, pageSize));
		model.addAttribute("paginator", list != null ? list.getPaginator() : null);
		model.addAttribute("list", list);
		model.addAttribute("page", page);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("EStatus", EStatus.getMap());
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		model.addAttribute("status", gs.getStatus());
		model.addAttribute("auditStatus", gs.getAuditStatus());
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
		if (guideS == null) {
			return null;
		}
		try {
			if (guideS.getId() != null ) {
				guideS.setUpdateTime(new Date());
				guideService.updateByPrimaryKeySelective(guideS);
			} else {
				guideS.setStatus(EStatus.EDIT.getId());
				String gServiceNo=StringUtil.generateProductSerialNumber(EProductNoPrefix.Service.getPrefix());
				guideS.setServiceNo(gServiceNo);
				guideS.setCreateTime(new Date());
				guideS.setUpdateTime(new Date());
				guideS.setFlag(EFlag.VALID.getId());
				guideS.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideService.insertSelective(guideS);
			}
			return "redirect:/admin/guideService";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return null;
		}
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
		
		//检查是否审核通过
		if(guideService.selectByPrimaryKey(id).getAuditStatus()!=EAuditStatus.AUDIT_OK.getId()){
			return new JsonResult(ExceptionCode.FAIL, "审核不通过不能上线！");
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
		
		if(EAuditStatus.AUDIT_NOSUBMIT.getId()==guideService.selectByPrimaryKey(id).getAuditStatus()){
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
