package com.mlx.guide.controller.guideadmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EUserStatus;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

/**
 * 基本资料
 * @author cyz
 * @category 基本资料
 *
 */

@RequestMapping(value="/guideAdmin/aboutMe")
@Controller
public class GuideInfoController {
	
	private static Logger logger = LoggerFactory.getLogger(GuideInfoController.class);
	
	@Autowired
	private GuideInfoService guideInfoService;

	/**
	 * 样式
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("aboutMe_class", Const.MENU_SUB);
	}
	/**
	 * 列表
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			GuideInfo guide = guideInfoService.getGuideInfoByUserNo(shiroUser.getUserNo());
			model.addAttribute("guide", guide);
			model.addAttribute("EUserStatus", EUserStatus.getMap());
			model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/guideInfo/myProfile";
	}

}
