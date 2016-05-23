package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.impl.Log4jLoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.mlx.guide.constant.ESex;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideInfo;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.entity.UserToRole;
import com.mlx.guide.model.Tree;
import com.mlx.guide.service.GuideInfoService;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.service.UserToRoleService;
import com.mlx.guide.util.StringUtil;

/**
 * 系统用户管理
 * @author QiQi-04-PC
 *
 */
@Controller
@RequestMapping("/admin/sysUser")
public class SysUserAdminController {
	Logger logger=new Log4jLoggerFactory().getLogger(this.getClass().getName());
	
	@Autowired
	private UserInfoService userService;
	@Autowired
	private GuideInfoService guideService;
	@Autowired
	private UserToRoleService userToRoleService;
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("systemclass", Const.MENU_FIRST);
		model.addAttribute("system_userclass", Const.MENU_SUB);
	}
	
	
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	@RequestMapping("/get/{id}")
	@ResponseBody
	public JsonResult get (@PathVariable("id")Long id){
		
		UserInfo u=new UserInfo();
		try {
			u = userService.getUserInfoByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			new JsonResult(ExceptionCode.SUCCESSFUL,e.getMessage());
		}
		return  new JsonResult(ExceptionCode.SUCCESSFUL,u);
	}
	
	@RequestMapping(value="/resetPassword",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult resetPassword(UserInfo user){
		if(user.getId()==null||user.getPassword()==null){
			return new JsonResult(ExceptionCode.FAIL,"user.id==null or password=null");
		}
		try {
			userService.resetPassword(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL,e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}
	
	   /**
		 * 列表
		 * 
		 * @param pageNo
		 * @param pageSize
		 * @param sysLoginLog
		 * @param model
		 * @return
		 */		
		@RequestMapping("list")
	    public String list(@RequestParam( value = "pageNo", defaultValue = "1" ) Integer pageNo,
		        @RequestParam( value = "pageSize", defaultValue = Const.PAGE_SIZE ) Integer pageSize, HttpServletRequest request, Model model,UserInfo userInfo ){
			try {
			    PageBounds pageBounds = new PageBounds( pageNo, pageSize, Order.formString( "id.desc" ) );
				PageList<UserInfo> list = userService.getUserInfoPageList(userInfo, pageBounds);
				model.addAttribute( "paginator", list != null ? list.getPaginator() : null );			
				model.addAttribute("list", list);
				model.addAttribute("sexMap", ESex.getMap());
				model.addAttribute("pageSize", pageSize);
				model.addAttribute("pageNo", pageNo);
				model.addAttribute("userInfo", userInfo);
				//查询导游信息Map
			    GuideInfo g=new GuideInfo();
				model.addAttribute("guideInfoMap", getGuideMapIndexByUserNo(g));
			} catch (Exception e) {
				logger.error( e.getMessage(),e );
			}
	        return "admin/sysUser/list";
	    }
		
		
		public Map<String,GuideInfo> getGuideMapIndexByUserNo(GuideInfo g){
			List<GuideInfo> guideInfos=guideService.getGuideInfoList(g);
			Map<String, GuideInfo> gMap=new HashMap<String ,GuideInfo>();
			for(GuideInfo guideInfo:guideInfos){
				gMap.put(guideInfo.getUserNo(), guideInfo);
			}
			return gMap;
		}
		/**
		 * 根据roleID 加载权限树 ,并打给树上checked
		 * 
		 * @return
		 */
		@RequestMapping("/roleTree")
		@ResponseBody
		public JsonResult loadTreeByRoleId(@RequestParam("userNo") String userNo) {
		
			List<Tree> roleTree;
			List<Tree> AT;
			// 查找已授权的树并添加标识
			List<UserToRole> utr;
			try {
				roleTree = userToRoleService.getAllTree();
				AT = new ArrayList<Tree>();
				utr = userToRoleService.selectByUserNo(userNo);
				AT=userToRoleService.tagTree(utr,roleTree);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO Auto-generated catch block
				return new JsonResult(ExceptionCode.FAIL,e.getMessage());
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL, AT);
		}

				
		@RequestMapping("/insertByUserNoAndRoleIds")
		@ResponseBody
		public JsonResult insertAuthorityToRole(String userNo,String roleIds){
			Map<String,Object> params=new HashMap<String,Object>();
			List<Integer> idsList = StringUtil.generateListInteger(roleIds);
			params.put("roleIds", idsList);
			params.put("userNo", userNo);
			try {
				/*先删除后插入*/
				userToRoleService.batDelete(params);
				userToRoleService.batInsert(params);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(ExceptionCode.FAIL);
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL);
		}
		@RequestMapping("/deleteByUserNoAndRoleIds")
		@ResponseBody
		public JsonResult deleteAuthorityToRole(String userNo,String roleIds){
			Map<String,Object> params=new HashMap<String,Object>();
			List<Integer> idsList = StringUtil.generateListInteger(roleIds);
			params.put("roleIds", idsList);
			params.put("userNo", userNo);
			try {
				userToRoleService.batDelete(params);
			} catch (Exception e) {
				e.printStackTrace();
				return new JsonResult(ExceptionCode.FAIL);
			}
			return new JsonResult(ExceptionCode.SUCCESSFUL);
		}
		
		

	    

}
