package com.mlx.guide.controller;
import java.util.Date;
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
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.AdvInfo;
import com.mlx.guide.service.AdvInfoService;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value="/admin/advInfo")
@Controller
public class AdvInfoAdminController {
	
	private static Logger logger = LoggerFactory.getLogger( AdvInfoAdminController.class );
	
	@Autowired
	private AdvInfoService advInfoService;
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute( "contentclass", Const.MENU_FIRST );
		model.addAttribute( "content_advInfoclass", Const.MENU_SUB );
		model.addAttribute("EStatus", EStatus.getMap());
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		
	}
	
   /**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param advInfo
	 * @param model
	 * @return
	 */		
	@RequestMapping
    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize,AdvInfo advInfo, Model model ){
		try {
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			PageList<AdvInfo> list = advInfoService.getAdvInfoPageList( advInfo, pageBounds );
			model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
			model.addAttribute("list", list);
			model.addAttribute("advInfo", advInfo);
			model.addAttribute( "pageSize", pageSize );
			model.addAttribute("statusMap", EStatus.getMap());
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/advInfo/list";
    }
    
    /**
	 * 异步列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param advInfo
	 * @param model
	 * @return
	 */		
    @RequestMapping(value="/asyncList", method = RequestMethod.POST , produces = MediaType.APPLICATION_JSON_VALUE )
	@ResponseBody
    public JsonResult list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
	        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request ){
	    PageList<AdvInfo> pageList = null;
	    JsonResult ajaxResult = null;
		try {
		    AdvInfo advInfo = new AdvInfo();
		    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
			pageList = advInfoService.getAdvInfoPageList( advInfo, pageBounds );
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
	 * @param advInfo
	 * @return
	 */	
	@RequestMapping(value="/detail/{id}", method = RequestMethod.GET )
    public String detail(@PathVariable Integer id, Model model ){
		try {
			AdvInfo advInfo = advInfoService.getAdvInfoByPrimaryKey(id);
            model.addAttribute("advInfo", advInfo);
		} catch (Exception e) {
			logger.error( e.getMessage(),e );
		}
        return "admin/advInfo/form";
    }
 
	/**
	 * 编辑-根据id获取编辑内容
	 * 
	 * @param audioInfo
	 * @return
	 */

	@RequestMapping( value = "edit/{id}", method = RequestMethod.GET )
	public String edit( @PathVariable( value = "id" ) Integer id, Model model ) {
		AdvInfo advInfo = advInfoService.getAdvInfoByPrimaryKey( id );
		model.addAttribute("advInfo",advInfo);
		model.addAttribute("actionUrl", "/admin/advInfo/saveOrUpdate");
		model.addAttribute("title", "修改广告");
		return "admin/advInfo/form";
	}
	/*
	 * 修改成功并返回主页面
	 */
	@RequestMapping( value = "editForm", method = RequestMethod.POST )
	public String editForm( HttpServletRequest request,AdvInfo advInfo, Model model ) {
		int result=advInfoService.updateAdvInfoSelective( advInfo );
		String msg = result > 0 ? "修改成功" : "修改失败";
		model.addAttribute( "message", msg );
		return "redirect:/admin/advInfo";
	}
	
	/**
	 *  保存更新
	 * @param advInfo
	 * @param model
	 * @return
	 */
	@RequestMapping(value="saveOrUpdate",method=RequestMethod.POST)
	public String saveOrUpdate(AdvInfo advInfo,Model model){
		if(advInfo.getId()==null || advInfo.getId().equals("")){
			advInfo.setCreateTime(new Date());
			advInfo.setStatus(EStatus.EDIT.getId());
			advInfoService.createAdvInfo(advInfo);
		}else{
			advInfoService.updateAdvInfoSelective(advInfo);
		}
		return "redirect:/admin/advInfo";
	}

	/**
	 * 上下线
	 * @param id
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/OnOffLine/{id}/{status}")
	public String onOffLine(@PathVariable("id")Integer id,@PathVariable("status")Integer status){
		AdvInfo advInfo=new AdvInfo();
		advInfo.setId(id);
		advInfo.setStatus(status);
		advInfoService.updateAdvInfoSelective(advInfo);
		return "redirect:/admin/advInfo";
	}
	
	/**
	 * 新增
	 * 
	 * @param audioInfo
	 * @return
	 */
	@RequestMapping( value = "/add")
	public String create( Model model,AdvInfo advInfo ) {
		model.addAttribute("actionUrl", "/admin/advInfo/saveOrUpdate");
		model.addAttribute("title", "新增广告");
		return "admin/advInfo/form";
	}



	

	   /**
		 * 删除
		 * 
		 * @param id
		 * @return
		 */
	    @RequestMapping(value="/delete/{id}",method = RequestMethod.POST  )
	    @ResponseBody
	    public JsonResult delete(Model model,@PathVariable(value="id") Integer id){
	    	
	    	AdvInfo ad=advInfoService.getAdvInfoByPrimaryKey(id);
	    	if(ad.getStatus()==EStatus.ONLINE.getId()){
	    		return new JsonResult(ExceptionCode.FAIL,"已上线不能删除！");
	    	}
	    	int result=advInfoService.deleteAdvInfo( id );
	    	return new JsonResult(ExceptionCode.SUCCESSFUL,result);
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