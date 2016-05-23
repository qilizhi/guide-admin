package com.mlx.guide.controller;

import java.util.List;

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
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideImgGroup;
import com.mlx.guide.service.GuideImgGroupService;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value="/admin/guideImgGroup")
@Controller
public class GuideImgGroupAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( GuideImgGroupAdminController.class );
	
	@Autowired
	private GuideImgGroupService guideImgGroupService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guideImgGroup_navbar", "am-active");
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideImgGroup
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model ){
		try {
		    GuideImgGroup guideImgGroup = new GuideImgGroup();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			PageList<GuideImgGroup> list = guideImgGroupService.getGuideImgGroupPageList( guideImgGroup, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("guideImgGroup", guideImgGroup);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/guideImgGroup/list";
    }
    
    /**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideImgGroup
	 * @param model
	 * @return
	 */		
    @RequestMapping(value="/asyncList", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request ){
	    PageList<GuideImgGroup> pageList = null;
	    JsonResult ajaxResult = null;
		try {
		    GuideImgGroup guideImgGroup = new GuideImgGroup();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			pageList = guideImgGroupService.getGuideImgGroupPageList( guideImgGroup, pageBounds );
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
	 * @param guideImgGroup
	 * @return
	 */	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable Integer id, Model model ){
		try {
			GuideImgGroup guideImgGroup = guideImgGroupService.getGuideImgGroupByPrimaryKey(id);
            model.addAttribute("guideImgGroup", guideImgGroup);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/guideImgGroup/form";
    }
 
   /**
	 * 更新
	 * 
	 * @param guideImgGroup
	 * @return
	 */
    @RequestMapping(value="/update", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult update(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    GuideImgGroup guideImgGroup = new GuideImgGroup();
			GuideImgGroup oldGuideImgGroup = guideImgGroupService.getGuideImgGroupByPrimaryKey(guideImgGroup.getId());
						oldGuideImgGroup.setUserNo(guideImgGroup.getUserNo());
			oldGuideImgGroup.setName(guideImgGroup.getName());
			oldGuideImgGroup.setFlag(guideImgGroup.getFlag());

			guideImgGroupService.updateGuideImgGroup(oldGuideImgGroup);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, oldGuideImgGroup );
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
        return "admin/guideImgGroup/create";
    }
    
   /**
	 * 创建
	 * 
	 * @param guideImgGroup
	 * @return
	 */
    @RequestMapping(value="/create", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
    @ResponseBody
    public JsonResult create(HttpServletRequest request){
		JsonResult ajaxResult = null;
		try {
		    GuideImgGroup guideImgGroup = new GuideImgGroup();
			guideImgGroupService.createGuideImgGroup(guideImgGroup);
			ajaxResult = new JsonResult( ExceptionCode.SUCCESSFUL, guideImgGroup );
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
			guideImgGroupService.deleteGuideImgGroup(id);
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
			guideImgGroupService.deleteGuideImgGroupBitch(idList);
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