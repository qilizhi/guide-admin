package com.mlx.guide.controller.guideadmin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mlx.guide.constant.Const;

/**
 * 预览
 * @author cyz
 * @category 主页预览
 *
 */
@Controller
@RequestMapping("/guideAdmin/preView")
public class PreViewController {
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("pre_class", Const.MENU_SUB);
	}
	
	@RequestMapping
	public String preView(Model model){
		
		return "guideAdmin/preview/preview";
	}
}
