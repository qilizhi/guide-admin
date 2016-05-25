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
import com.mlx.guide.model.GuideInfoModel;
import com.mlx.guide.service.GuideInfoService;


/**
 * 导游
 * @author FIVE
 *
 */
@Controller
@RequestMapping("/admin/guideUserInfo")
public class GuideUserInfoAdminController {
	private static Logger logger = LoggerFactory.getLogger(GuideUserInfoAdminController.class);
	
	
	
	@Autowired
	private GuideInfoService guideInfoService;
	
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guideclass", Const.MENU_FIRST );
		
	}
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model, GuideInfoModel guideInfoModel) {
		try {
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("create_time.desc"));
			  PageList<GuideInfoModel> list;
			  if("true".equals(guideInfoModel.getIsAuditStatus())){
				  model.addAttribute( "guide_auditclass", Const.MENU_SUB );
		       list = guideInfoService.getPageListByAuthstr (guideInfoModel, pageBounds);
			  }else{
				  model.addAttribute( "guide_listclass", Const.MENU_SUB );
				list = guideInfoService.getGuideInfoPageList(guideInfoModel, pageBounds);
			  } 
			model.addAttribute("list", list);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("guideInfo", guideInfoModel);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guide/list";

	}
	
	/**
	 * 修改
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public String edit(GuideInfo guideInfo){
		try {
			guideInfoService.updateByPrimaryKeySelective(guideInfo);
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
			GuideInfo guideInfo=guideInfoService.selectByPrimaryKey(id);
			model.addAttribute( "guide_listclass", Const.MENU_SUB );
			model.addAttribute("guideInfo",guideInfo);
			
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/guide/detail";
		
	}
	
	
	
	
}
