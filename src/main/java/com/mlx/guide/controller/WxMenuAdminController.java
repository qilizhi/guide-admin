package com.mlx.guide.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mlx.guide.constant.Const;
import com.mlx.guide.entity.WxPublic;
import com.mlx.guide.model.WxMenuModel;
import com.mlx.guide.service.WeiXinInterface;
import com.mlx.guide.service.WxPublicService;

/**
 * 微信菜单管理
 * @author FIVE
 *
 */
@RequestMapping(value="/admin/wxMenu")
@Controller
public class WxMenuAdminController{
	
	private static Logger logger = LoggerFactory.getLogger( WxMenuAdminController.class );
	
	@Autowired
	private WxPublicService wxPublicService;
	
	@Autowired
	private WeiXinInterface weiXinInterface;
	
	@ModelAttribute
	public void common(Model model,WxPublic wxPublic) {
		model.addAttribute( "weixinclass", Const.MENU_FIRST );
		model.addAttribute( "weixin_menumgrclass", Const.MENU_SUB );
	}
	
	@RequestMapping
	public String list(Model model){
        try {
        	List<WxPublic> list=wxPublicService.getAllwxPublic();
        	model.addAttribute("list", list);
        	
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
	  return "/admin/wxMenu/list";
		
	}
	
	
	@RequestMapping("/detail/{id}")
	@ResponseBody
	public String detail(@PathVariable Long id,Model model){
		try {
			WxPublic wxPublic=wxPublicService.getWxPublicById(id);
			return weiXinInterface.selectMenu(wxPublic.getAppId(), wxPublic.getAppSecret());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;

	}
	
	
	
	@RequestMapping(value="/edit")
	@ResponseBody
	public String edit(@RequestParam(value="id",required=true) Long id,WxMenuModel wxMenuModel,Model model){
		try {
			WxPublic wxPublic=wxPublicService.getWxPublicById(id);
			return weiXinInterface.createMenu(wxPublic.getAppId(), wxPublic.getAppSecret(), wxMenuModel);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
		
		
	}
	

}
