package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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
import com.mlx.guide.entity.Authority;
import com.mlx.guide.entity.AuthorityToResource;
import com.mlx.guide.entity.Resource;
import com.mlx.guide.model.Tree;
import com.mlx.guide.service.AuthorityService;
import com.mlx.guide.service.AuthorityToResourceService;
import com.mlx.guide.util.StringUtil;

/**
 * 
 * 权限controller
 * 
 * @author QiQi-04-PC
 *
 */
@RequestMapping("/admin/authority")
@Controller
public class AuthorityAdminController {

	@Autowired
	private AuthorityService authorityMapper;

	@Autowired
	private AuthorityToResourceService atrService;
	
	
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common( Model model ) {
		model.addAttribute( "systemclass", Const.MENU_FIRST );
		model.addAttribute( "system_perimclass", Const.MENU_SUB );
	}


	/**
	 * 列表
	 * 
	 * @param name
	 *            查询条件
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	public String list(Integer authorityId ,@RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageSize,Model model) {
		PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
		PageList<Resource> list = atrService. selectResourceByExample(authorityId, pageBounds);
		model.addAttribute("paginator", list != null ? list.getPaginator() : null);
		model.addAttribute("list", list);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("authorityId", authorityId);
		return "/admin/authority/list";
	}
	
	/** 新增
	 * 
	 * @param authority
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult insert(Authority authority) {
		try {
			authorityMapper.insertSelective(authority);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, authority);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, authority);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delete(Integer id) {

		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "Id不能为空！");
		}
		try {
			authorityMapper.deleteByPrimaryKey(id);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}


	/**
	 * 更新
	 * 
	 * @param auth
	 * @return
	 */
	@RequestMapping(value = "/update")
	@ResponseBody
	public JsonResult update(Authority auth) {
		if (auth.getId() == null) {
			return new JsonResult(ExceptionCode.FAIL, "id为空！");
		}
		try {
			authorityMapper.updateByPrimaryKeySelective(auth);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 跟据权限加载资源列表
	 * 
	 * @param authorityId
	 * @return
	 */
	@RequestMapping("/loadResource")
	public String loadResource(Integer authorityId, @RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "10") Integer pageS,Model model) {
		PageBounds pageBounds = new PageBounds(pageNo, pageS, Order.formString("id.desc"));
		PageList<Resource> list = atrService. selectResourceByExample(authorityId, pageBounds);
		model.addAttribute("paginator", list != null ? list.getPaginator() : null);
		model.addAttribute("list", list);
		model.addAttribute("pageS", pageS);
		return "/admin/authority/table";
	}

	/**
	 * 给权限设置资源
	 * @param resourceIds
	 * @param authorityId
	 * @return
	 */
	@RequestMapping("/setResource")
	@ResponseBody
	public JsonResult setResourceByAuthority(String resourceIds, Integer authorityId) {
		AuthorityToResource atr = new AuthorityToResource();
		atr.setAuthorityId(authorityId);
		if(authorityId==null){
			return new JsonResult(ExceptionCode.FAIL,"authorityId为空！");
			
		}
		Map<String,Object> params=new HashMap<String,Object>();
		List<Integer> idsList = StringUtil.generateListInteger(resourceIds);
		params.put("resourceIds", idsList);
		params.put("authorityId", authorityId);
		try {
		/*	先删后插入*/
			atrService.deleteBySelective(atr);	
			atrService.batInsert(params);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);

	}

	/**
	 * 权限树结构
	 * 
	 * @return
	 */
	@RequestMapping("/Alltree")
	@ResponseBody
	public JsonResult getAllTree() {

		List<Tree> tree = new ArrayList<>();
		try {
			tree = authorityMapper.getAllTree();
		} catch (Exception e) {

			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, tree);
	}

}
