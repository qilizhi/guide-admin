package com.mlx.guide.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.entity.PlatformMsg;
import com.mlx.guide.service.PlatformMsgService;
/**
 * 公告管理
 * @author FIVE
 *
 */
@RequestMapping(value="/admin/sysNotice")
@Controller
public class SysNoticeAdminController {
   
	private static Logger logger = LoggerFactory.getLogger(SysNoticeAdminController.class);
	
	@Autowired
	private  PlatformMsgService platformMsgService;
	
	@ModelAttribute
	public void common(Model model,PlatformMsg platformMsg) {
		model.addAttribute("systemclass", Const.MENU_FIRST );
		model.addAttribute( "system_noticeclass", Const.MENU_SUB );
	
	}
	
	/**
	 * 列表
	 * @return
	 */
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model, PlatformMsg platformMsg){
		try {
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("create_time.desc"));
			PageList<PlatformMsg> list = platformMsgService.getPageList(platformMsg, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("platformMsg", platformMsg);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return "admin/sysNotice/list";
		
		
	}
	
	/**
	 * 跳转
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.GET)
	public String edit (Model model,@RequestParam(value="id",required=false)Long id){
     try {
    	 if(id!=null){
    	 PlatformMsg p=platformMsgService.getPlatformMsgById(id);
    		model.addAttribute("platformMsg", p);
    	 }
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
 	return "admin/sysNotice/edit";
		
	}
	
	/**
	 * 修改&添加
	 * @param model
	 * @param platformMsg
	 * @return
	 */
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	public String edit (Model model,PlatformMsg platformMsg,RedirectAttributes redire){
     try {
    	 if(platformMsg.getId()!=null){
    		 platformMsgService.updateByPrimaryKeySelective(platformMsg);
			}else{
			 platformMsgService.insertSelective(platformMsg);
			}
    	 redire.addFlashAttribute("msg", "操作成功!");
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
 	return "redirect:/admin/sysNotice";
		
	}
}
