package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
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
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EAuditStatus;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.EStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideLine;
import com.mlx.guide.entity.GuideLineDatePrice;
import com.mlx.guide.entity.GuideService;
import com.mlx.guide.service.GuideLineDatePriceService;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;
import com.mlx.guide.util.StringUtil;

@RequestMapping(value = "/admin/guideLine")
@Controller
public class GuideLineAdminController {

	private static Logger logger = LoggerFactory.getLogger(GuideLineAdminController.class);

	@Autowired
	private GuideLineService guideLineService;
	@Autowired
	private GuideLineDatePriceService guideLineDatePriceService;
	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("productclass", Const.MENU_FIRST);
		model.addAttribute("product_lineclass", Const.MENU_SUB);
		model.addAttribute("EStatus", EStatus.getMap());
		model.addAttribute("EAuditStatus", EAuditStatus.getMap());
	}

	/**
	 * 线路下拉树
	 * 
	 * @return
	 */
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public JsonResult listAll() {
		List<GuideLine> lines = new ArrayList<GuideLine>();
		try {
			lines = guideLineService.getGuideLineList();
		} catch (Exception e) {
			e.printStackTrace();
			new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, lines);
	}
	/**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param searchStr
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideLine guideLine,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		//ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			guideLine.setFlag(EFlag.VALID.getId());
			//guideLine.setUserNo(shiroUser.getUserNo());
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideLine> list = guideLineService.getGuideLinePageList(guideLine, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("guideLine", guideLine);
			model.addAttribute("title", guideLine.getTitle());
			model.addAttribute("lineNo", guideLine.getLineNo());
			model.addAttribute("status", guideLine.getStatus());
			model.addAttribute("auditStatus", guideLine.getAuditStatus());
			model.addAttribute("pageSize", pageSize);
		
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/line/list";
	}

	
	
	/**
	 * 编辑页面跳转
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, Model model) {
		GuideLine gs = guideLineService.getGuideLineByPrimaryKey(id);
		model.addAttribute("guideService", gs);
		return "/admin/line/detail";
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
	public JsonResult onOff(@PathVariable("id") Integer id, Integer status) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		
		//检查是否审核通过
		if(guideLineService.getGuideLineByPrimaryKey(id).getAuditStatus()!=EAuditStatus.AUDIT_OK.getId()){
			return new JsonResult(ExceptionCode.FAIL, "审核不通过不能上线！");
		}
		GuideLine gs = new GuideLine();
		gs.setId(id);
		gs.setStatus(status);
		// 标志删除
		int result = 0;
		try {
			result = guideLineService.updateGuideLineSelective(gs);
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
	public JsonResult audit(@PathVariable("id") Integer id, String auditRemark, Integer auditStatus) {
		if (id == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		
		if(EAuditStatus.AUDIT_NOSUBMIT.getId()==guideLineService.getGuideLineByPrimaryKey(id).getAuditStatus()){
			return new JsonResult(ExceptionCode.FAIL, "未提交审核，不能审核");
		}
		GuideLine gs = new GuideLine();
		gs.setId(id);
		gs.setAuditRemark(auditRemark);
		gs.setAuditStatus(auditStatus);
		// 标志删除
		int result = 0;
		try {
			result = guideLineService.updateGuideLineSelective(gs);
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
	public JsonResult batdDlete(@PathVariable("ids") String ids) {
		if (ids == null) {
			return new JsonResult(ExceptionCode.FAIL, "ids不能为空！");
		}
		String[] idsArray = ids.split(",");
		List<Integer> idsInteger=new ArrayList<Integer>();
		for(String id:idsArray){
			if(id!=null&&!id.equals(""))
			idsInteger.add(Integer.parseInt(id));
		}
		// 标志删除
		
		try {
			 guideLineService.deleteGuideLineBitch(idsInteger);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}
	
	
	/**
	 * 新增
	 * 
	 * @param guideLine
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String saveOrUpdate(@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, Model model, GuideLine guideLine) {
		// 获取当前用户
		//ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {

			if (guideLine.getId() != null) {
				// 更新
				guideLine.setAuditStatus(1);// 每次修改审核状态都改为待审核
				guideLineService.updateGuideLineSelective(guideLine);
				return "redirect:/guideAdmin/line/list";
			} else {
				// 新增
				// 随机生成线路编号
				int num = (int) (Math.random() * 10000);
				guideLine.setLineNo("MLX_" + num + System.currentTimeMillis());
				//guideLine.setUserNo(shiroUser.getUserNo());
				//guideLine.setUserName(shiroUser.getName());
				guideLine.setCreateTime(new Date());
				guideLine.setFlag(1);
				guideLine.setStatus(EStatus.EDIT.getId());
				guideLine.setAuditStatus(1);
				guideLineService.createGuideLineSelective(guideLine);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/admin/guideLine/editPrice/" + guideLine.getLineNo(); // 重定向到线路价格页面
	}

	/**
	 * 编辑价格，根据线路编号获取价格表
	 * 
	 * @param guideLineDatePrice
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editPrice/{lineNo}", method = RequestMethod.GET)
	public String editPrice(GuideLineDatePrice guideLineDatePrice, Model model) {

		String lineNo = guideLineDatePrice.getLineNo();
		// 根据线路no获取对应的线路
		GuideLine guideLine = guideLineService.getGuideLineByLineNo(lineNo);
		// 根据线路no获取对应的价格表
		List<GuideLineDatePrice> lsGuideLineDatePrices = guideLineDatePriceService
				.getGuideLineDatePriceByLineNo(lineNo);
		String jsonData = JSON.toJSONStringWithDateFormat(lsGuideLineDatePrices, "yyyy-MM-dd");
		model.addAttribute("guideLine", guideLine);
		model.addAttribute("lineDataPrices", StringUtil.stringValue(jsonData, "[]"));

		return "admin/line/price";
	}

	/**
	 * 跳转到线路修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			model.addAttribute("guideLine", guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "admin/line/create";
	}

	/**
	 * 保存价格
	 * 
	 * @param linePrices
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public JsonResult savePrice(@RequestParam("params") String linePrices) {
		try {
			List<GuideLineDatePrice> lsGuideLineDatePrices = JSON.parseArray(linePrices, GuideLineDatePrice.class);
			guideLineDatePriceService.saveGuideLineDatePriceBitch(lsGuideLineDatePrices);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL);
	}

	/**
	 * 删除对应线路的价格，按时间段删除
	 * 
	 * @param lineNo
	 * @param beginTime
	 * @param endTime
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/delLinePrcie/{lineNo}", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult delLinePrice(@PathVariable String lineNo, @RequestParam("beginTime") String beginTime,
			@RequestParam("endTime") String endTime, HttpServletRequest request) {
		try {
			if (!beginTime.isEmpty() && !endTime.isEmpty()) {
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				map.put("beginTime", request.getParameter("beginTime"));
				map.put("endTime", request.getParameter("endTime"));
				guideLineDatePriceService.deleteGuideLineDatePriceByDate(map);
				return new JsonResult(ExceptionCode.SUCCESSFUL);
			} else {
				return new JsonResult(ExceptionCode.FAIL);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}

	/**
	 * 根据id获取线路信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/up/{id}", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult up(@PathVariable Integer id) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			return new JsonResult(ExceptionCode.SUCCESSFUL,guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}
	
	/**
	 * 修改上线，下线状态
	 * @param guideLine
	 * @return
	 */
	@RequestMapping(value="upAndDown")
	public String upAndDown(GuideLine guideLine){
		guideLineService.updateGuideLineSelective(guideLine);
		return "redirect:/guideAdmin/line/list";
	}
	
	

	/**
	 * 跳转到线路新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String add() {
		return "admin/line/create";
	}

	/**
	 * 删除线路
	 * 
	 * @param id
	 * @return
	 */
	/*@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		try {
			GuideLine guideLine = guideLineService.getGuideLineByPrimaryKey(id);
			guideLine.setFlag(EFlag.INVALID.getId());
			guideLineService.updateGuideLineSelective(guideLine);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/line/list";
	}*/
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
/*	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable(value = "id") Integer id) {
		if (id == null || id.intValue() < 1) {
			model.addAttribute("message", "数据异常");
		}
		int result = guideLineService.deleteGuideLine(id);
		;
		String msg = result > 0 ? "删除成功" : "删除失败";
		model.addAttribute("message", msg);
		return "redirect:/admin/guideLine";
	}*/

	/**
	 * 批量删除
	 * 
	 * @param ids
	 *            id集合,例如:1,2,3,4
	 * @return
	 */
	@RequestMapping(value = "/deletes/{ids}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public JsonResult delete(@PathVariable String ids) {
		JsonResult ajaxResult = null;
		try {
			List<Integer> idList = StringUtil.generateListInteger(ids);
			guideLineService.deleteGuideLineBitch(idList);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

	/**
	 * 批量审核
	 * 
	 * @param ids
	 *            id集合,例如:1,2,3,4
	 * @param auditRemark
	 *            审核备注说明
	 * @param auditStatus
	 *            审核状态
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audits", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public JsonResult audits(@RequestParam String ids, @RequestParam String auditRemark,
			@RequestParam Integer auditStatus) {
		List<Integer> lsIds = StringUtil.generateListInteger(ids);
		JsonResult ajaxResult = null;
		try {
			// TODO:change db table data
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return ajaxResult;
	}

}