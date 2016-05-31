package com.mlx.guide.controller.guideadmin;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.ELineType;
import com.mlx.guide.constant.ESignInStatus;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.GuideTuanGuest;
import com.mlx.guide.service.GuideTuanGuestService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

/**
 * 出团列表，出团成员列表
 * 
 * @author cyz
 * @category 出团列表/出团成员
 */

@Controller
@RequestMapping(value = "/guideAdmin/tuan")
public class GuideTuanController {

	private static Logger logger = LoggerFactory.getLogger(GuideTuanController.class);
	@Autowired
	private GuideTuanService guideTuanService;
	@Autowired
	private GuideTuanGuestService guideTuanGuestService;

	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("tuan_class", Const.MENU_SUB);
	}

	/**
	 * 列表
	 * @param pageNo
	 * @param pageSize
	 * @param guideTuan
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuan guideTuan,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();
		try {
			 guideTuan.setUserNo(shiroUser.getUserNo());
			PageList<GuideTuan> list = guideTuanService.getGuideTuanPageList(guideTuan,
					new PageBounds(pageNo, pageSize));
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("guideTuan", guideTuan);
			model.addAttribute("ETuanStatus", ETuanStatus.getByteMap());
			model.addAttribute("ELineType", ELineType.getByteMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "guideAdmin/guideTuan/list";
	}
	
	/**
	 * 获取出团信息
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/up/{id}")
	@ResponseBody
	public JsonResult up(@PathVariable Long id, Model model){
		try {
			GuideTuan tuan = guideTuanService.selectByPrimaryKey(id);
			return new JsonResult(ExceptionCode.SUCCESSFUL, tuan);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}
	/**
	 * 
	 * @param id
	 * @param status 出团状态
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}/{status}")
	@ResponseBody
	public JsonResult update(@PathVariable Long id,@PathVariable Byte status, Model model){
		try {
			GuideTuan tuan = guideTuanService.selectByPrimaryKey(id);
			tuan.setTuanStatus(status);
			guideTuanService.updateByPrimaryKeySelective(tuan);
			return new JsonResult(ExceptionCode.SUCCESSFUL, tuan);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new JsonResult(ExceptionCode.FAIL);
		}
	}
	
	/**
	 * 根据出团编号查询对应的成员名单
	 * @param tuanNo
	 * @param pageNo
	 * @param pageSize
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tuanGuest/{tuanNo}")
	public String tuanGuest(@PathVariable String tuanNo,
			@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, Model model) {
		GuideTuanGuest guest = new GuideTuanGuest();
		guest.setTuanNo(tuanNo);
		PageList<GuideTuanGuest> lsGuests = guideTuanGuestService.getGuideTuanGuestPageList(guest,
				new PageBounds(pageNo, pageSize));
		model.addAttribute("paginator", lsGuests != null ? lsGuests.getPaginator() : null);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("guest", guest);
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "guideAdmin/guideTuan/guest_list";
	}

	/**
	 * 成员名单查询
	 * @param pageNo
	 * @param pageSize
	 * @param guest
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/tuanGuest/search")
	public String searchGuest(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize, GuideTuanGuest guest,
			Model model) {
		PageList<GuideTuanGuest> lsGuests = guideTuanGuestService.getGuideTuanGuestPageList(guest,
				new PageBounds(pageNo, pageSize));
		model.addAttribute("paginator", lsGuests != null ? lsGuests.getPaginator() : null);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("guest", guest);
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "guideAdmin/guideTuan/guest_list";
	}
	
}
