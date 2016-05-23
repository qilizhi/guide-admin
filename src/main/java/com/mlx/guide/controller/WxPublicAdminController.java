package com.mlx.guide.controller;


import javax.servlet.http.HttpServletRequest;

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

import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.entity.WxPublic;
import com.mlx.guide.service.WxPublicService;

/**
 *  微信公众号管理
 * @author FIVE
 *
 */
@RequestMapping(value="/admin/wxPublic")
@Controller
public class WxPublicAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( WxPublicAdminController.class );
	
	@Autowired
	private WxPublicService wxPublicService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model,WxPublic wxPublic) {
		model.addAttribute( "weixinclass", Const.MENU_FIRST );
		model.addAttribute( "weixin_publicmgrclass", Const.MENU_SUB );
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param wxPublic
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,WxPublic wxPublic,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model ){
		try {
		    PageBounds pageBounds = new PageBounds(pageNo,pageSize,Order.formString( "id.desc"));
			PageList<WxPublic> list = wxPublicService.getWxPublicPageList(wxPublic, pageBounds);
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("wxPublic",wxPublic);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/wxPublic/list";
    }

   /**
    * 跳转
    * @param id
    * @param model
    * @param request
    * @return
    */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit (@RequestParam(value="id",required=false) Long id ,Model model,HttpServletRequest request){
		try {
			if(id!=null){
				WxPublic wxPublic=wxPublicService.getWxPublicById(id);
				 model.addAttribute("wxPublic", wxPublic);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		
		 return "admin/wxPublic/edit";
				
	}
	
	/**
	 * 修改&添加
	 * @param wxPublic
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit (WxPublic wxPublic ,Model model,HttpServletRequest request){
		try {
			if(wxPublic.getId()!=null){
				wxPublicService.updateWxPublic(wxPublic);
			}else{
				wxPublicService.insertWxpublic(wxPublic);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		 return "redirect:/admin/wxPublic";
				
	}
	
	/**
	 * 删除
	 * @param id
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/delete/{id}",method=RequestMethod.POST)
	@ResponseBody
	public String delete (@PathVariable Long id ,Model model,HttpServletRequest request){
      try {
    	  wxPublicService.deleteWxPublicById(id);
	  } catch (Exception e) {
		logger.error(e.getMessage(),e);
		 return "抱歉,删除操作失败,请重试!";
	  }
		 return "操作成功";
				
	  }
    
	
	
	
   
    
}