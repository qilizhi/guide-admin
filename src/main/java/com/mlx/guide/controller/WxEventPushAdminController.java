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
import com.mlx.guide.entity.WxEventPush;
import com.mlx.guide.service.WxEventPushService;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value="/admin/wxEventPush")
@Controller
public class WxEventPushAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( WxEventPushAdminController.class );
	
	@Autowired
	private WxEventPushService wxEventPushService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute( "weixinclass", Const.MENU_FIRST );
		model.addAttribute( "weixin_publicmgrclass", Const.MENU_SUB );
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param wxEventPush
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model ){
		try {
		    WxEventPush wxEventPush = new WxEventPush();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			PageList<WxEventPush> list = wxEventPushService.getWxEventPushPageList( wxEventPush, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("wxEventPush", wxEventPush);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/wxEventPush/list";
    }
    
    /**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param wxEventPush
	 * @param model
	 * @return
	 */		
    @RequestMapping(value="/asyncList", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request ){
	    PageList<WxEventPush> pageList = null;
	    JsonResult ajaxResult = null;
		try {
		    WxEventPush wxEventPush = new WxEventPush();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			pageList = wxEventPushService.getWxEventPushPageList( wxEventPush, pageBounds );
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
	 * @param wxEventPush
	 * @return
	 */	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable Long id, Model model ){
		try {
			WxEventPush wxEventPush = wxEventPushService.getWxEventPushByPrimaryKey(id);
            model.addAttribute("wxEventPush", wxEventPush);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/wxEventPush/form";
    }
 
   /**
	 * 更新
	 * 
	 * @param wxEventPush
	 * @return
	 */
    @RequestMapping(value="/update", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult update(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    WxEventPush wxEventPush = new WxEventPush();
			WxEventPush oldWxEventPush = wxEventPushService.getWxEventPushByPrimaryKey(wxEventPush.getId());
						oldWxEventPush.setCreateDate(wxEventPush.getCreateDate());
			oldWxEventPush.setUpdateDate(wxEventPush.getUpdateDate());
			oldWxEventPush.setEventType(wxEventPush.getEventType());
			oldWxEventPush.setEventCode(wxEventPush.getEventCode());
			oldWxEventPush.setEventName(wxEventPush.getEventName());
			oldWxEventPush.setEventKey(wxEventPush.getEventKey());
			oldWxEventPush.setEventDesc(wxEventPush.getEventDesc());
			oldWxEventPush.setWeixinPublicId(wxEventPush.getWeixinPublicId());

			wxEventPushService.updateWxEventPush(oldWxEventPush);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, oldWxEventPush );
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
        return "admin/wxEventPush/create";
    }
    
   /**
	 * 创建
	 * 
	 * @param wxEventPush
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public JsonResult create(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    WxEventPush wxEventPush = new WxEventPush();
			wxEventPushService.createWxEventPush(wxEventPush);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, wxEventPush );
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
    public JsonResult delete(@PathVariable Long id){
		JsonResult ajaxResult = null;
		try {
			wxEventPushService.deleteWxEventPush(id);
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
		    List<Long> idList = StringUtil.generateListLong(ids);
			wxEventPushService.deleteWxEventPushBitch(idList);
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
		List<Long> lsIds = StringUtil.generateListLong( ids );
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