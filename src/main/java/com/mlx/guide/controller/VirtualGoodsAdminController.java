package com.mlx.guide.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.constant.VirtualGoodsType;
import com.mlx.guide.entity.AdvInfo;
import com.mlx.guide.entity.VirtualGoods;
import com.mlx.guide.service.VirtualGoodsService;

@Controller
@RequestMapping("/admin/virtualGoods")
public class VirtualGoodsAdminController {


	@Autowired
	private VirtualGoodsService virtualGoodsService;
	
	@ModelAttribute
	public void common(Model model){
		model.addAttribute("productclass", Const.MENU_FIRST);
		model.addAttribute("product_virtualGoodsclass", Const.MENU_SUB);
		model.addAttribute("AuditStatus", EAuditStatus.getByteMap());
		model.addAttribute("Status", EStatus.getMap());
		model.addAttribute("goodsType", VirtualGoodsType.getMap());
		
	}
	
	@RequestMapping
	public String list(VirtualGoods vGoods,@RequestParam(defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer pageSize,Model model){
		
		vGoods.setFlag(EFlag.VALID.getId().byteValue());;
		PageList<VirtualGoods> virtualGoods=virtualGoodsService.listByExample(vGoods,new PageBounds(page,pageSize));
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("page", page);
		model.addAttribute("auditStatus", vGoods.getAuditStatus());
		model.addAttribute("status", vGoods.getStatus());
		
		model.addAttribute("paginator", virtualGoods.getPaginator());
		model.addAttribute("list",virtualGoods);
		return "/admin/virtualGoods/list";
	}
	
	@RequestMapping(value="detail/{id}")
	public String detail(@PathVariable("id")Long id,Model model){
		VirtualGoods virtualGoods=virtualGoodsService.selectByPrimaryKey(id);
		model.addAttribute("item",virtualGoods);
		return "/admin/virtualGoods/detail";
	}
	
	
	/**
	 * 新增页面转发
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.GET)
	public String create(Model model){
		model.addAttribute("title", "新增");
		return "/admin/virtualGoods/form";
	}
	
	/**
	 * 编辑页面转发
	 * @return
	 */
	@RequestMapping(value="/edit/{id}",method=RequestMethod.GET)
	public String create(@PathVariable("id")Long id,Model model){
		VirtualGoods virtualGoods=virtualGoodsService.selectByPrimaryKey(id);
		model.addAttribute("title", "编辑");
		model.addAttribute("item", virtualGoods);
		return "/admin/virtualGoods/form";
	}
	/**
	 * 更新或者保存
	 * @param virtualGoods
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/saveOrUpdate",method=RequestMethod.POST)
	public String saveOrUpdate(VirtualGoods virtualGoods,Model model){
		if(virtualGoods.getId()==null || virtualGoods.getId().equals("")){
			virtualGoods.setCreateTime(new Date());
			virtualGoods.setStatus(EStatus.EDIT.getId());
			virtualGoodsService.insertSelective(virtualGoods);
		}else{
			
			
			virtualGoodsService.updateByPrimaryKeySelective(virtualGoods);
		}
		return "redirect:/admin/virtualGoods";
	}
	/**
	 * 上下线功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/onOff/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult onOff(@PathVariable("id") Long id, Integer status) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		
		//检查是否审核通过
		if(virtualGoodsService.selectByPrimaryKey(id).getAuditStatus().intValue()!=EAuditStatus.AUDIT_OK.getId()){
			return new JsonResult(ExceptionCode.FAIL, "审核不通过不能上下线！");
		}
		VirtualGoods gs = new VirtualGoods();
		gs.setId(id);
		gs.setStatus(status);
		if(status==EStatus.ONLINE.getId()){
			gs.setPubTime(new Date());
		}
		// 标志删除
		int result = 0;
		try {
			result = virtualGoodsService.updateByPrimaryKeySelective(gs);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 审核功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/audit/{id}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult audit(@PathVariable("id") Long id, String auditRemark, Byte auditStatus) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		
		if(EAuditStatus.AUDIT_NOSUBMIT.getId()==virtualGoodsService.selectByPrimaryKey(id).getAuditStatus().intValue()){
			return new JsonResult(ExceptionCode.FAIL, "未提交审核，不能审核");
		}
		VirtualGoods gs = new VirtualGoods();
		gs.setId(id);
		gs.setAuditRemark(auditRemark);
		gs.setAuditStatus(auditStatus);
		// 标志删除
		int result = 0;
		try {
			result = virtualGoodsService.updateByPrimaryKeySelective(gs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());

		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 删除功能。
	 * 
	 * @param ids
	 *            以 ,分隔
	 * @return
	 */
	@RequestMapping(value = "/delete/{ids}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult delete(@PathVariable("ids") String ids) {
		if (ids == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		String[] idsArray = ids.split(",");
		// 标志删除
		int result = 0;
		try {
			result = virtualGoodsService.batDelByflag(idsArray);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	

}
