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
import com.mlx.guide.entity.GuideStrategyBrowser;
import com.mlx.guide.service.GuideStrategyBrowserService;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value="/admin/guideStrategyBrowser")
@Controller
public class GuideStrategyBrowserAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( GuideStrategyBrowserAdminController.class );
	
	@Autowired
	private GuideStrategyBrowserService guideStrategyBrowserService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guideStrategyBrowser_navbar", "am-active");
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideStrategyBrowser
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model ){
		try {
		    GuideStrategyBrowser guideStrategyBrowser = new GuideStrategyBrowser();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			PageList<GuideStrategyBrowser> list = guideStrategyBrowserService.getGuideStrategyBrowserPageList( guideStrategyBrowser, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("guideStrategyBrowser", guideStrategyBrowser);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/guideStrategyBrowser/list";
    }
    
    /**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideStrategyBrowser
	 * @param model
	 * @return
	 */		
    @RequestMapping(value="/asyncList", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request ){
	    PageList<GuideStrategyBrowser> pageList = null;
	    JsonResult ajaxResult = null;
		try {
		    GuideStrategyBrowser guideStrategyBrowser = new GuideStrategyBrowser();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			pageList = guideStrategyBrowserService.getGuideStrategyBrowserPageList( guideStrategyBrowser, pageBounds );
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, pageList );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }    
	
   /**
	 * 详情
	 * 
	 * @param guideStrategyBrowser
	 * @return
	 */	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable Integer id, Model model ){
		try {
			GuideStrategyBrowser guideStrategyBrowser = guideStrategyBrowserService.getGuideStrategyBrowserByPrimaryKey(id);
            model.addAttribute("guideStrategyBrowser", guideStrategyBrowser);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/guideStrategyBrowser/form";
    }
 
   /**
	 * 更新
	 * 
	 * @param guideStrategyBrowser
	 * @return
	 */
    @RequestMapping(value="/update", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult update(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    GuideStrategyBrowser guideStrategyBrowser = new GuideStrategyBrowser();
			GuideStrategyBrowser oldGuideStrategyBrowser = guideStrategyBrowserService.getGuideStrategyBrowserByPrimaryKey(guideStrategyBrowser.getId());
						oldGuideStrategyBrowser.setStragegyNo(guideStrategyBrowser.getStragegyNo());
			oldGuideStrategyBrowser.setUserNo(guideStrategyBrowser.getUserNo());
			oldGuideStrategyBrowser.setBrowserTime(guideStrategyBrowser.getBrowserTime());

			guideStrategyBrowserService.updateGuideStrategyBrowser(oldGuideStrategyBrowser);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, oldGuideStrategyBrowser );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
   
   /**
	 * 创建
	 * 
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.GET )
    public String create( Model model ){
        return "admin/guideStrategyBrowser/create";
    }
    
   /**
	 * 创建
	 * 
	 * @param guideStrategyBrowser
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public JsonResult create(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    GuideStrategyBrowser guideStrategyBrowser = new GuideStrategyBrowser();
			guideStrategyBrowserService.createGuideStrategyBrowser(guideStrategyBrowser);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, guideStrategyBrowser );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
    
   /**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
    @RequestMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult delete(@PathVariable Integer id){
		JsonResult ajaxResult = null;
		try {
			guideStrategyBrowserService.deleteGuideStrategyBrowser(id);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
    
   /**
	 * 批量删除
	 * 
	 * @param ids id集合,例如:1,2,3,4
	 * @return
	 */
    @RequestMapping(value="/deletes/{ids}", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult delete(@PathVariable String ids){
		JsonResult ajaxResult = null;
		try {
		    List<Integer> idList = StringUtil.generateListInteger(ids);
			guideStrategyBrowserService.deleteGuideStrategyBrowserBitch(idList);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
    }
    
	/**
	 * 批量审核
	 * 
	 * @param ids id集合,例如:1,2,3,4
	 * @param auditRemark 审核备注说明
	 * @param auditStatus 审核状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping( value = "/audits", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE )
	public JsonResult audits( @RequestParam String ids, @RequestParam String auditRemark, @RequestParam Integer auditStatus ) {
		List<Integer> lsIds = StringUtil.generateListInteger( ids );
		JsonResult ajaxResult = null;
		try {
		    //TODO:change db table data
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL );
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
			ajaxResult = new JsonResult( ExceptionCode.FAIL, e.getMessage() );
		}
        return ajaxResult;
	}    
    
}