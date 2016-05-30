package com.mlx.guide.controller.guideadmin;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
/**
 * 图库
 * @author cyz
 * @category 图库
 *
 */
@RequestMapping(value = "/guideAdmin/imgStorage")
@Controller
public class ImgStorageController {

	@Autowired
	private GuideImgInfoService guideImgInfoService;
	// 获取当前用户
	ShiroUser shiroUser = ShiroDbRealm.getLoginUser();

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("guide_imgclass", Const.MENU_FIRST);
	}

	@RequestMapping
	public String list(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue ="30") Integer pageSize,
			HttpServletRequest request, Model model) {
		
		GuideImgInfo guideImgInfo=new GuideImgInfo();
		guideImgInfo.setUserNo(shiroUser.getUserNo());
		PageList<GuideImgInfo> list = guideImgInfoService.getGuideImgInfoPageList(guideImgInfo, new PageBounds(pageNo, pageSize));
		model.addAttribute("list", list);
		return "guideAdmin/imgStorage/images";
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
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize,
			HttpServletRequest request, Model model) {
		
		JsonResult jsonResult = new JsonResult();
		try {
			GuideImgInfo guideImgInfo=new GuideImgInfo();
			guideImgInfo.setUserNo(shiroUser.getUserNo());
			PageList<GuideImgInfo> list = guideImgInfoService.getGuideImgInfoPageList(guideImgInfo, new PageBounds(pageNo, pageSize));
			model.addAttribute("list", list);
			jsonResult = new JsonResult(ExceptionCode.SUCCESSFUL, list);
		} catch (Exception e) {
			jsonResult = new JsonResult(ExceptionCode.FAIL, "内部出现错误");
		}
		return jsonResult;
	}
	/**
	 * 新增图片
	 * @param images
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public JsonResult uploadImage(@RequestBody List<String> images,HttpServletRequest request){
		
		List<GuideImgInfo> guideImgInfos=new ArrayList<GuideImgInfo>();
		for(String image:images){
			GuideImgInfo guideImgInfo =new GuideImgInfo();
			guideImgInfo.setCreateTime(new Date());
			guideImgInfo.setFlag(EFlag.VALID.getId());
			//当前用户，shiroUser 里获得
			guideImgInfo.setUserNo(shiroUser.getUserNo());
			guideImgInfo.setImgUrl(image);
			guideImgInfoService.createGuideImgInfoSelective(guideImgInfo);
			guideImgInfos.add(guideImgInfo);
			
		}
		
		return new JsonResult(ExceptionCode.SUCCESSFUL,guideImgInfos );
	}
	
	
}
