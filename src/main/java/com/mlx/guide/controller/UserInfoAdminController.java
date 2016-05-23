package com.mlx.guide.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.mlx.guide.constant.ESex;
import com.mlx.guide.constant.EUserStatus;
import com.mlx.guide.constant.EUserType;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.Member;
import com.mlx.guide.model.Statistics;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value = "/admin/userInfo")
@Controller
public class UserInfoAdminController {

	private static Logger logger = LoggerFactory.getLogger(UserInfoAdminController.class);

	@Autowired
	private UserInfoService userInfoService;
	@Value("${imagePrefix}")
	private String aliyunImgPrefix;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("systemclass", Const.MENU_FIRST);
		model.addAttribute("system_userinfoclass", Const.MENU_SUB);
	}

	@RequestMapping("/forbidden/{ids}")
	@ResponseBody
	public JsonResult forbidden(@PathVariable("ids") String ids) {

		JsonResult aJsonResult = null;
		try {
			List<Long> idList = StringUtil.generateListLong(ids);
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			for (Long id : idList) {
				UserInfo u = new UserInfo();
				u.setId(id);
				if (u != null) {
					u.setStatus(EUserStatus.INVALID.getId());
					userInfos.add(u);
				}
			}
			userInfoService.updateUserInfoSelectiveBitch(userInfos);
			aJsonResult = new JsonResult(ExceptionCode.SUCCESSFUL);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			aJsonResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return aJsonResult;
	}

	@RequestMapping("/unforbidden/{ids}")
	@ResponseBody
	public JsonResult unforbidden(@PathVariable("ids") String ids) {

		JsonResult aJsonResult = null;
		try {
			List<Long> idList = StringUtil.generateListLong(ids);
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			for (Long id : idList) {
				UserInfo u = new UserInfo();
				u.setId(id);
				if (u != null) {
					u.setStatus(EUserStatus.VALID.getId());
					userInfos.add(u);
				}
			}
			userInfoService.updateUserInfoSelectiveBitch(userInfos);
			aJsonResult = new JsonResult(ExceptionCode.SUCCESSFUL);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			aJsonResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return aJsonResult;
	}

	/**
	 * 根据id获取导游信息
	 * 
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping("/{id}")
	@ResponseBody
	public JsonResult get(@PathVariable("id") Long id, HttpServletRequest request) {
		JsonResult ajaxResult = null;
		try {
			UserInfo user = userInfoService.getUserInfoByPrimaryKey(id);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL, user);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 普通用户列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, UserInfo userInfo,
			HttpServletRequest request, Model model) {
		try {

		//	userInfo.setAuditStatus(0);
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<UserInfo> list = userInfoService.getUserInfoPageList(userInfo, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("sexMap", ESex.getMap());
			model.addAttribute("auditMap", EAuditStatus.getMap());

		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}
		return "admin/userInfo/userlist";
	}

	/**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/asyncList", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request) {
		PageList<UserInfo> pageList = null;
		JsonResult ajaxResult = null;
		try {
			UserInfo userInfo = new UserInfo();
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			pageList = userInfoService.getUserInfoPageList(userInfo, pageBounds);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL, pageList);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 导游用户列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param userInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/guideUserInfo")
	public String guideUserInfoList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, UserInfo userInfo,
			HttpServletRequest request, Model model) {
		try {

			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
		//	userInfo.setUserType(EUserType.GUIDE_USER.getId());
			PageList<UserInfo> list = userInfoService.getUserInfoPageList(userInfo, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("sexMap", ESex.getMap());
			model.addAttribute("auditMap", EAuditStatus.getMap());
			model.addAttribute("userStatus", EUserStatus.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/userInfo/guidelist";
	}

	/**
	 * 详情
	 * 
	 * @param userInfo
	 * @return
	 */
	@RequestMapping(value = "/detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable Long id, Model model) {
		try {
			UserInfo userInfo = userInfoService.getUserInfoByPrimaryKey(id);
			model.addAttribute("userInfo", userInfo);
			model.addAttribute("sexMap", ESex.getMap());
			model.addAttribute("auditMap", EAuditStatus.getMap());
			model.addAttribute("imgPrex", aliyunImgPrefix);
			model.addAttribute("userStatus", EUserStatus.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/userInfo/detail";
	}

	/**
	 * 编辑-根据id获取编辑内容
	 * 
	 * @param audioInfo
	 * @return
	 */

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable(value = "id") Long id, Model model) {
		UserInfo userInfo = userInfoService.getUserInfoByPrimaryKey(id);
		model.addAttribute("userInfo", userInfo);
		model.addAttribute("sexMap", ESex.getMap());
		model.addAttribute("auditMap", EAuditStatus.getMap());
		model.addAttribute("imgPrex", aliyunImgPrefix);
		model.addAttribute("userStatus", EUserStatus.getMap());
		return "admin/userInfo/edit";
	}

	/*
	 * 修改成功并返回主页面
	 */
	@RequestMapping(value = "editForm", method = RequestMethod.POST)
	public String editForm(HttpServletRequest request, Model model,UserInfo userInfo) throws ParseException {
		int result = userInfoService.updateUserInfo(userInfo);
		String msg = result > 0 ? "修改成功" : "修改失败";
		model.addAttribute("message", msg);
		return "redirect:/admin/userInfo";
	}

	/**
	 * 跳转到新增页面
	 * 
	 * @param audioInfo
	 * @return
	 */
	@RequestMapping(value = "/create", method = { RequestMethod.POST, RequestMethod.GET })
	public String create(HttpServletRequest request, Model model, UserInfo userInfo) {
		/*
		 * AudioInfo audioInfo = ReflectionFromParamUtil.getObject( request,
		 * AudioInfo.class );
		 */
		int result = 0;
		String msg = result > 0 ? "添加成功" : "添加失败";
		model.addAttribute("message", msg);
		return "admin/guide/create";

	}

	/**
	 * 保存新增信息
	 * 
	 * @param audioInfo
	 * @return
	 */
	@RequestMapping(value = "/save")
	public String save(HttpServletRequest request, Model model, UserInfo userInfo) {
		/*
		 * AudioInfo audioInfo = ReflectionFromParamUtil.getObject( request,
		 * AudioInfo.class );
		 */
		userInfoService.createUserInfo(userInfo);
		;
		return "redirect:/admin/userInfo";

	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable(value = "id") Long id) {

		int result = userInfoService.deleteUserInfo(id);
		;
		String msg = result > 0 ? "删除成功" : "删除失败";
		model.addAttribute("message", msg);
		return "redirect:/admin/userInfo";
	}

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
	@ResponseBody
	@RequestMapping(value = "/audit/{ids}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResult audits(@PathVariable("ids") String ids, @RequestParam Integer auditStatus) {
		List<Long> lsIds = StringUtil.generateListLong(ids);
		JsonResult ajaxResult = new JsonResult();
		try {
			List<UserInfo> userInfos = new ArrayList<UserInfo>();
			for (Long id : lsIds) {
				UserInfo u = new UserInfo();
				u.setId(id);
				if (u != null) {
			//		u.setAuditStatus(auditStatus);
					userInfos.add(u);
				}
			}
			userInfoService.updateUserInfoSelectiveBitch(userInfos);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/* 用户统计 */

	@RequestMapping(value = "/statistics", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult statistics(Integer userType, HttpServletRequest request) {
		try {
			if (userType == null) {
				return new JsonResult(ExceptionCode.FAIL, "用户类型不能为空！");
			}
			List<Statistics> us = userInfoService.countUserGroupByDate(userType);
			return new JsonResult(ExceptionCode.SUCCESSFUL, us);
		} catch (Exception e) {
			return new JsonResult(ExceptionCode.FAIL, "查询出错");
		}
	}

	// 图表
	@RequestMapping(value = "/chart")
	public String chart() {
		return "/admin/userInfo/chart";
	}

}