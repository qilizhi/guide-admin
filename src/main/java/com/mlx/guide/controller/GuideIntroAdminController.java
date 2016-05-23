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
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.GuideIntro;
import com.mlx.guide.model.GuideInfoModel;
import com.mlx.guide.model.GuideIntroModel;
import com.mlx.guide.service.GuideIntroService;


/**
 * 导游故事
 * @author FIVE
 *
 */
@Controller
@RequestMapping("/admin/guideIntro")
public class GuideIntroAdminController {
	private static Logger logger = LoggerFactory.getLogger(GuideIntroAdminController.class);
	
	
	
	@Autowired
	private GuideIntroService guideIntroService;
	
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guideclass", Const.MENU_FIRST );
		model.addAttribute("guide_intro_listclass",Const.MENU_SUB);
	}
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model, GuideIntroModel guideIntroModel) {
		try {
			
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideInfoModel> list = guideIntroService.getGuideIntroPageList(guideIntroModel, pageBounds);
			model.addAttribute("list", list);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("guideIntroModel", guideIntroModel);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guideIntro/list";

	}
	
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(GuideIntroModel guideIntroModel){
		try {
			guideIntroService.updateByPrimaryKeySelective(guideIntroModel);
			 return "操作成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		   return "系统异常,请稍后再试";	
	}
	
	
	/**
	 * 详情
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail(@RequestParam(required=true)Long id,HttpServletRequest request, Model model){
		try {
			GuideIntro guideIntro=guideIntroService.selectByPrimaryKey(id);
			model.addAttribute("guideIntro",guideIntro);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guideIntro/detail";
		
	}
	
	
	
	
}
