package com.mlx.guide.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.mlx.guide.constant.Const;
import com.mlx.guide.constant.EGroupType;
import com.mlx.guide.constant.ExceptionCode;
import com.mlx.guide.constant.JsonResult;
import com.mlx.guide.entity.EmGroup;
import com.mlx.guide.entity.UserInfo;
import com.mlx.guide.service.EmGroupService;
import com.mlx.guide.service.UserInfoService;
import com.mlx.guide.util.EasemobClientUtil;

/**
 * 
 * @author QiQi-04-PC
 *
 * @category 万人群
 */
@Controller
@RequestMapping("/admin/group")
public class EmGroupController {

	/**
	 * 是否公开
	 * 
	 * @param isP
	 * @return
	 */
	public boolean isPublic(Byte isP) {
		return isP.intValue() == 1 ? true : false;
	}

	/**
	 *  是否需要验证
	 * @param isAp
	 * @return
	 */
	public boolean isApproval(Byte isAp) {
		return isAp.intValue() == 1 ? true : false;
	}

	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private EmGroupService groupService;

	@Autowired
	private UserInfoService userInfoService;

	@Value("${imagePrefix}")
	private String aliyunPrefix;

	/**
	 * 读取公共的参数值和设置,根据界面设置的参数值来选择页面菜单选中效果
	 * 
	 * @param menuBar
	 * @param model
	 */
	@ModelAttribute
	public void common(Model model) {
		model.addAttribute("contentclass", Const.MENU_FIRST);
		model.addAttribute("content_groupclass", Const.MENU_SUB);
	}

	/**
	 * 新增跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "create",method=RequestMethod.GET)
	public String create() {
		return "admin/group/create";

	}

	/**
	 * 编辑跳转
	 * 
	 * @return
	 */
	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public String edit(Model model,@PathVariable("id")Long id) {
		EmGroup eg = groupService.selectByPrimaryKey(id);
		model.addAttribute("group", eg);
		return "admin/group/edit";

	}

	/**
	 * 列表
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping
	public String list(EmGroup group, Model model, @RequestParam(defaultValue = "1") Integer pageNo,
			@RequestParam(defaultValue = "15") Integer pageSize) {
		Map<String, Object> userMap = new HashMap<String, Object>();
		List<UserInfo> users = userInfoService.getUserInfoList();
		for (UserInfo user : users) {
			userMap.put(user.getUserNo(), user.getNickName());
		}
		PageList<EmGroup> groups = groupService.listBySelective(group, new PageBounds(pageNo, pageSize));
		model.addAttribute("list", groups);
		model.addAttribute("paginator", groups != null ? groups.getPaginator() : null);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("userMap", userMap);
		model.addAttribute("group", group);
		model.addAttribute("groupType", EGroupType.getMap());
		model.addAttribute("aliyunPrefix", aliyunPrefix);
		return "admin/group/list";
	}

	/**
	 * 列表
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public JsonResult list(EmGroup group) {
		List<EmGroup> groups = new ArrayList<EmGroup>();
		try {
			groups = groupService.listBySelective(group);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, e.getMessage());
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, groups);
	}

	/**
	 * 创建万人群
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value="/create",method=RequestMethod.POST)
	@ResponseBody
	public JsonResult add(EmGroup group) {
		int result = -1;
		try {
			// 请求环信接口创建群
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupname", group.getEmGname());
			params.put("desc", group.getEmGdesc());
			params.put("public", isPublic(group.getEmPublic()));
			params.put("maxusers", group.getEmMaxusers());
			params.put("approval", isApproval(group.getEmAllowinvites()));
			params.put("owner", group.getEmGuser());
			String o = EasemobClientUtil.post("chatgroups", JSON.toJSONString(params));
			JSONObject resultMap = JSON.parseObject(o);
			JSONObject data = resultMap.getJSONObject("data");
			Long groupId = data.getLong("groupid");
			if (groupId == null) {
				return new JsonResult(ExceptionCode.FAIL, "调用环信接口失败！");
			}
			group.setEmGid(groupId);
			group.setCreateTime(new Date());
			//应是当前登录的用户。 从shiroUser 里获取
			group.setUserNo("qilizhi_linlin");
			logger.info("环信群创建成功:" + data.toJSONString());
			result = groupService.insertSelective(group);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new JsonResult(ExceptionCode.FAIL, result);
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 编辑万人群
	 * 
	 * @param group
	 * @return
	 */
	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	@ResponseBody
	public JsonResult edit(EmGroup group) {
		int result = -1;
		try {
			// 请求环信接口修改群
			if (group.getEmGid() == null) {
				return new JsonResult(ExceptionCode.FAIL, "环信群ID为空");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("groupname", group.getEmGname());
			params.put("description", group.getEmGdesc());
			params.put("maxusers", group.getEmMaxusers());
			params.put("approval", isApproval(group.getEmAllowinvites()));
			params.put("owner", group.getEmGuser());
			String o = EasemobClientUtil.put("chatgroups/" + group.getEmGid(), JSON.toJSONString(params));
			JSONObject resultMap = JSON.parseObject(o);
			JSONObject data = resultMap.getJSONObject("data");
			if (data == null) {
				logger.info("调用环信接口出错");
				return new JsonResult(ExceptionCode.SUCCESSFUL, "调用环信接口出错");

			}
			logger.info("环信群数据更新:" + data.toJSONString());
			result = groupService.updateByPrimaryKeySelective(group);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new JsonResult(ExceptionCode.FAIL, result);
		}

		return new JsonResult(ExceptionCode.SUCCESSFUL, result);
	}

	/**
	 * 删除万人群
	 * 
	 * @param ids
	 *            本地库ids
	 * @return
	 */
	@RequestMapping("/del/{ids}")
	@ResponseBody
	public JsonResult delete(@PathVariable("ids") String ids) {
		String[] idsArray = ids.split(",");
		try {
			// 请求环信接口删除群
			for (String id : idsArray) {
				EmGroup eg = groupService.selectByPrimaryKey(Long.valueOf(id));
				if (eg == null || eg.getEmGid() == null) {
					logger.info("环信群ID为空");
					return new JsonResult(ExceptionCode.FAIL, "环信群ID为空");
				}
				String o = EasemobClientUtil.delete("chatgroups/" + eg.getEmGid());
				JSONObject resultMap = JSON.parseObject(o);
				JSONObject data = resultMap.getJSONObject("data");
				boolean isSuccess = data.getBoolean("success");
				if (!isSuccess) {
					logger.info("调用环信接口出错");
					return new JsonResult(ExceptionCode.FAIL, "调用环信接口出错");
				}
				logger.info("环信群:groupid=" + data.getLongValue("groupid") + "已删除！");
				// 删除本地的数据
				groupService.deleteByPrimaryKey(eg.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info(e.getMessage());
			new JsonResult(ExceptionCode.FAIL, "删除出错!");
		}
		return new JsonResult(ExceptionCode.SUCCESSFUL, "删除成功");

	}

}
