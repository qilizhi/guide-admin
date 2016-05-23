package com.mlx.guide.controller;

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
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.ELineType;
import com.mlx.guide.constant.ESignInStatus;
import com.mlx.guide.constant.ETuanStatus;
import com.mlx.guide.entity.GuideTuan;
import com.mlx.guide.entity.GuideTuanGuest;
import com.mlx.guide.service.GuideTuanGuestService;
import com.mlx.guide.service.GuideTuanService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;


/**
 * 
 * @author QiQi-04-PC
 * 
 * @category 出团列表
 *
 */
@Controller
@RequestMapping("/admin/guideTuan")
public class GuideTuanAdminController {
	Logger logger =LoggerFactory.getLogger(GuideTuanAdminController.class);
	@Autowired
	private GuideTuanService guideTuanService;
	@Autowired
	private GuideTuanGuestService guideTuanGuestService;
	
	@ModelAttribute
	public void comm(Model model){
		model.addAttribute("homeclass", Const.MENU_FIRST);
		model.addAttribute("home_tuanclass", Const.MENU_SUB);
	}
	/**
	 * 出团列表
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
			// guideTuan.setUserNo(shiroUser.getUserNo());
			PageList<GuideTuan> list = guideTuanService.getGuideTuanPageList(guideTuan,
					new PageBounds(pageNo, pageSize));
			model.addAttribute("paginator", list != null ? list.getPaginator() : null);
			model.addAttribute("pageNo", pageNo);
			model.addAttribute("pageSize", pageSize);
			model.addAttribute("list", list);
			model.addAttribute("name", guideTuan.getName());
			model.addAttribute("lineNo", guideTuan.getLineNo());
			model.addAttribute("ETuanStatus", ETuanStatus.getByteMap());
			model.addAttribute("ELineType", ELineType.getByteMap());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return "admin/guideTuan/list";
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
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "admin/guideTuan/guest_list";
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
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("list", lsGuests);
		model.addAttribute("mobile", guest.getMobile());
		model.addAttribute("guestName", guest.getGuestName());
		model.addAttribute("orderNo", guest.getOrderNo());
		model.addAttribute("tuanNo", guest.getTuanNo());
		model.addAttribute("ESignInStatus", ESignInStatus.getByteMap());
		return "admin/guideTuan/guest_list";
	}


}
