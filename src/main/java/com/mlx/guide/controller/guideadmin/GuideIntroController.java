package com.mlx.guide.controller.guideadmin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mlx.guide.constant.Const;
import com.mlx.guide.entity.GuideIntro;
import com.mlx.guide.service.GuideIntroService;

/**
 * 我的故事
 * @author cyz
 * @category 我的故事
 *
 */
@Controller
@RequestMapping(value="/guideAdmin/myStory")
public class GuideIntroController {

	private static Logger logger = LoggerFactory.getLogger(GuideIntroController.class);
	@Autowired
	private GuideIntroService guideIntroService;

	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("myStory_class", Const.MENU_SUB);
	}
	/**
	 * 我的故事
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String myStory(Model model) {
		try {
			GuideIntro myStory = guideIntroService.getGuideIntroByUserNo("weixin4");
			model.addAttribute("myStory", myStory);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/myStory/myStory";
	}
	/**
	 * 更新
	 * @param guideIntro
	 * @return
	 */
	@RequestMapping(value="/update")
	public String update(GuideIntro guideIntro){
		try {
			guideIntroService.updateByPrimaryKeySelective(guideIntro);			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin";
	}

}
