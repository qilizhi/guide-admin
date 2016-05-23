package com.mlx.guide.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.SysBizLog;
import com.mlx.guide.service.SysBizLogService;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value="/admin/sysBizLog")
@Controller
public class SysBizLogAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( SysBizLogAdminController.class );
	
	@Autowired
	private SysBizLogService sysBizLogService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
	
		model.addAttribute( "systemclass", Const.MENU_FIRST );
		model.addAttribute( "system_logclass", Const.MENU_SUB );
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param sysBizLog
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,SysBizLog sysBizLog,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model ){
		try {
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "create_time.desc" ) );
			PageList<SysBizLog> list = sysBizLogService.getSysBizLogPageList(sysBizLog, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("sysBizLog", sysBizLog);
			model.addAttribute("pageSize", pageSize);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/sysBizLog/list";
    }
    
   
    
}