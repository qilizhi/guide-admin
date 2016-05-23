package com.mlx.guide.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.model.UserInfoModel;
import com.mlx.guide.service.UserInfoService;


/**
 * 会员列表
 * @author FIVE
 *
 */
@Controller
@RequestMapping("/admin/member")
public class MemberAdminController {
	private static Logger logger = LoggerFactory.getLogger(MemberAdminController.class);
	
	
	
	@Autowired
	private UserInfoService userInfoService;
	
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute( "memberclass", Const.MENU_FIRST );
		model.addAttribute( "member_listclass", Const.MENU_SUB );
	}
	/**
	 * 会员列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model, UserInfoModel userInfoModel) {
		try {
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<UserInfo> list = userInfoService.getUserInfoPageList(userInfoModel, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("userInfo", userInfoModel);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/member/list";

	}
	
	/**
	 * 会员修改
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(UserInfo userInfo){
		try {
			 userInfoService.updateUserInfoSelective(userInfo);
			 return "操作成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		   return "系统异常,请稍后再试";	
	}
	
	
	/**
	 * 会员详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam(required=true)Long id,HttpServletRequest request, Model model){
		try {
			UserInfo u= userInfoService.getUserInfoById(id);
			model.addAttribute("userInfo", u);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/member/detail";
		
	}
	
	
}
