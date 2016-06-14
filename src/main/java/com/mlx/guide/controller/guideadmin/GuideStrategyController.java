package com.mlx.guide.controller.guideadmin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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
import com.mlx.guide.entity.GuideStrategy;
import com.mlx.guide.service.GuideLineService;
import com.mlx.guide.service.GuideStrategyService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

/**
 * 攻略控制器
 * 
 * @author cyz
 * @category 攻略
 *
 */
@RequestMapping(value = "/guideAdmin/strategy")
@Controller
public class GuideStrategyController {

	private static Logger logger = LoggerFactory.getLogger(GuideStrategyController.class);

	@Autowired
	private GuideStrategyService guideStrategyService;
	@Autowired
	private GuideLineService guideLineService;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guide_strategyclass", Const.MENU_FIRST);
	}

	/**
	 * 列表
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param guideStrategy
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			GuideStrategy guideStrategy, HttpServletRequest request, Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			guideStrategy.setUserNo(shiroUser.getUserNo());
			guideStrategy.setFlag(EFlag.VALID.getId());
			PageBounds pageBounds = new PageBounds(pageNo, pageSize, Order.formString("id.desc"));
			PageList<GuideStrategy> list = guideStrategyService.getGuideStrategyPageList(guideStrategy, pageBounds);
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("list", list);
			model.addAttribute("guideStrategy", guideStrategy);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("EStatus", EStatus.getMap());
			model.addAttribute("EAuditStatus", EAuditStatus.getMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "guideAdmin/strategy/list";
	}

	/**
	 * 根据标题搜索线路，填充到下拉框
	 * @param title
	 * @return
	 */
	@RequestMapping(value = "search")
	@ResponseBody
	public JsonResult getLine(String title) {
		JsonResult jsonResult = new JsonResult();
		GuideLine guideLine = new GuideLine();
		guideLine.setTitle(title);
		try {
			List<GuideLine> guideLineList = guideLineService.getGuideLinePageList(guideLine);
			if (guideLineList.size() == 0) {
				jsonResult = new JsonResult(ExceptionCode.FAIL, "没有该线路");
			} else {
				jsonResult = new JsonResult(ExceptionCode.SUCCESSFUL, guideLineList.subList(0, 10));//下拉框只显示10条信息
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			jsonResult = new JsonResult(ExceptionCode.FAIL, "内部出现错误");
		}
		return jsonResult;
	}


	/**
	 * 跳转到新增页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "create", method = RequestMethod.GET)
	public String add() {
		return "guideAdmin/strategy/create";
	}

	/**
	 * 跳转到修改页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(@PathVariable("id") Integer id, Model model) {
		GuideStrategy guideStrategy = guideStrategyService.getGuideStrategyByPrimaryKey(id);
		model.addAttribute("guideStrategy", guideStrategy);
		return "guideAdmin/strategy/edit";
	}
	
	/**
	 * 详情
	 */
	@RequestMapping(value = "detail/{id}", method = RequestMethod.GET)
	public String detail(@PathVariable("id") Integer id, Model model) {
		GuideStrategy gs = guideStrategyService.getGuideStrategyByPrimaryKey(id);
		model.addAttribute("item", gs);
		model.addAttribute("EStatus", EStatus.getMap());
		return "/guideAdmin/strategy/detail";
	}
	

	/**
	 * 新增,更新
	 * 
	 * @param guideStrategy
	 * @param request
	 * @param file
	 * @return
	 */
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public String doAdd(GuideStrategy guideStrategy, HttpServletRequest request,
			@RequestParam(value = "file", required = false) MultipartFile file, Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();

		try {
			if (guideStrategy.getId() != null) {
				// 更新
				guideStrategy.setUpdateTime(new Date());
				guideStrategyService.updateGuideStrategySelective(guideStrategy);
				return "redirect:/guideAdmin/strategy/list";
			} else {
				// 新增
				guideStrategy.setUserName(shiroUser.getName());
				guideStrategy.setUserNo(shiroUser.getUserNo());
				guideStrategy.setCreateTime(new Date());
				guideStrategy.setAuditStatus(EAuditStatus.AUDIT_ON.getId());
				guideStrategyService.createGuideStrategySelective(guideStrategy);
				return "redirect:/guideAdmin/strategy/list";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "guideAdmin/strategy/create";
		}
	}
	
	/**
	 * 序号修改
	 * 
	 * @param id
	 * @param sort
	 * @return
	 */
	@RequestMapping(value = "updateSort/{id}")
	@ResponseBody
	public JsonResult updateSort(@PathVariable(value = "id") Integer id, @RequestParam Integer sort) {
		JsonResult ajaxResult = null;
		GuideStrategy guideStrategy = new GuideStrategy();
		guideStrategy.setId(id);
		guideStrategy.setSort(sort);
		try {
			guideStrategyService.updateGuideStrategySelective(guideStrategy);
			ajaxResult = new JsonResult(ExceptionCode.SUCCESSFUL);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			ajaxResult = new JsonResult(ExceptionCode.FAIL, "内部出现错误");
		}
		return ajaxResult;
	}

	/**
	 * 修改上线，下线状态
	 * 
	 * @param guideLine
	 * @return
	 */
	@RequestMapping(value="/on")
	@ResponseBody
	public String on(GuideStrategy guideStrategy){
		try {
			guideStrategyService.updateGuideStrategySelective(guideStrategy);
			 return "操作成功！";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "系统异常,请稍后再试";	
	}
	
	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		try {
			GuideStrategy guideStrategy = guideStrategyService.getGuideStrategyByPrimaryKey(id);
			guideStrategy.setFlag(EFlag.INVALID.getId());
			guideStrategyService.updateGuideStrategySelective(guideStrategy);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "redirect:/guideAdmin/strategy/list";
	}
}
