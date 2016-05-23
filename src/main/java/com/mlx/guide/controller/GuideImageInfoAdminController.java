package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EFlag;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.GuideImgInfo;
import com.mlx.guide.service.GuideImgInfoService;
import com.mlx.guide.shiro.ShiroDbRealm;
import com.mlx.guide.shiro.ShiroDbRealm.ShiroUser;

@Controller
@RequestMapping("/admin/imageInfo")
public class GuideImageInfoAdminController {

	//private static Logger logger = LoggerFactory.getLogger(this.get);
	Logger logger =LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GuideImgInfoService guideImgInfoService;
	@ModelAttribute
	public void comm(Model model){
		model.addAttribute("contentclass", Const.MENU_FIRST);
		model.addAttribute("content_imagesclass", Const.MENU_SUB);
	}
	
	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue ="50") Integer pageSize,
			HttpServletRequest request, Model model) {
		GuideImgInfo guideImgInfo=new GuideImgInfo();
		PageList<GuideImgInfo> list = guideImgInfoService.getGuideImgInfoPageList(guideImgInfo, new PageBounds(pageNo, pageSize));
		model.addAttribute("list", list);
		return "admin/imageStorage/images";
	}
	
	/**
	 * 获取图库图片
	 * @param pageNo
	 * @param pageSize
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getImgs")
	@ResponseBody
	public JsonResult getImgs(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = Const.PAGE_SIZE) Integer pageSize,
			HttpServletRequest request, Model model) {
		// 获取当前用户
		ShiroUser shiroUser = ShiroDbRealm.getLoginUser();

		JsonResult jsonResult = new JsonResult();
		try {
			GuideImgInfo guideImgInfo=new GuideImgInfo();
			guideImgInfo.setUserNo("weixin4");
			PageList<GuideImgInfo> list = guideImgInfoService.getGuideImgInfoPageList(guideImgInfo, new PageBounds(pageNo, pageSize));
			model.addAttribute("list", list);
			jsonResult = new JsonResult(ExceptionCode.SUCCESSFUL, list);
		} catch (Exception e) {
			jsonResult = new JsonResult(ExceptionCode.FAIL, "内部出现错误");
		}
		return jsonResult;
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public JsonResult uploadImage(@RequestBody List<String> images,HttpServletRequest request){
		
		List<GuideImgInfo> guideImgInfos=new ArrayList<GuideImgInfo>();
		for(String image:images){
			GuideImgInfo guideImgInfo =new GuideImgInfo();
			guideImgInfo.setCreateTime(new Date());
			guideImgInfo.setFlag(EFlag.VALID.getId());
			//当前用户，shiroUser 里获得,这里临时写死
			guideImgInfo.setUserNo("qilizhi");
			guideImgInfo.setImgUrl(image);
			guideImgInfoService.createGuideImgInfoSelective(guideImgInfo);
			guideImgInfos.add(guideImgInfo);
			
		}
		
		return new JsonResult(ExceptionCode.SUCCESSFUL,guideImgInfos );
	}

}
