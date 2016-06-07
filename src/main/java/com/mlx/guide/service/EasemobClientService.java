package com.mlx.guide.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.mlx.guide.util.EasemobClientUtil;

/**
 * 环信 接口 客户Service
 * 
 * @author QiQi-04-PC
 *
 */
@Service
public class EasemobClientService {

	Logger logger = LoggerFactory.getLogger(EasemobClientService.class);

	@Value("${uri.easemob.groupUri}")
	private String groupUri;
	@Autowired
	private EasemobClientUtil easemobClientUtil;

	/**
	 * 创建环信群
	 * 
	 * @param groupname
	 *            群名字
	 * @param desc
	 *            群描述
	 * @param isPublic
	 *            true or false
	 * @param maxusers
	 *            最大用户数
	 * @param isApproval
	 *            true or false
	 * @param owner
	 *            拥有 者
	 * @return string
	 */
	public String createGroup(String groupname, String desc, boolean isPublic, Integer maxusers, boolean isApproval,
			String owner) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (groupname != null)
			params.put("groupname", groupname);
		if (desc != null)
			params.put("desc", desc);
		params.put("public", isPublic);
		if (maxusers != null)
			params.put("maxusers", maxusers);
		params.put("approval", isApproval);
		if (owner != null)
			params.put("owner", owner);
		return easemobClientUtil.post(groupUri, JSON.toJSONString(params));
	}

	/**
	 * 编缉万人群
	 * 
	 * @param groupId
	 * @param groupname
	 * @param desc
	 * @param maxusers
	 * @param isApproval
	 * @param owner
	 * @return
	 */
	public String editGroup(Long groupId, String groupname, String desc, Integer maxusers, boolean isApproval,
			String owner) {

		Map<String, Object> params = new HashMap<String, Object>();
		if (groupname != null)
			params.put("groupname", groupname);
		if (desc != null)
			params.put("desc", desc);
		// params.put("public",isPublic);
		if (maxusers != null)
			params.put("maxusers", maxusers);
		params.put("approval", isApproval);
		if (owner != null)
			params.put("owner", owner);
		return easemobClientUtil.put(groupUri + "/" + groupId, JSON.toJSONString(params));
	}

	/**
	 * 删除万人群
	 * 
	 * @param groupId
	 * @return
	 */
	public String deleteGroup(Long groupId) {

		return easemobClientUtil.delete(groupUri + "/" + groupId);
	}

	/**
	 * 批量将环信用户加入群。
	 * 
	 * @param userList
	 * @param groupId
	 * @return
	 */
	public String addUsersGroup(List<String> userList, String groupId) {
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("usernames", userList);
		logger.info("插群的id为:" + groupId + "用户数据：" + JSON.toJSONString(maps));
		return easemobClientUtil.post(groupUri + "/" + groupId + "/users", JSON.toJSONString(maps));
	}

	/**
	 * 创建 用户
	 * 
	 * @param userName
	 * @param password
	 * @return
	 */
	public String createUser(String userName, String password,String nickname) {
		Map<String, String> maps = new HashMap<String, String>();
		
		maps.put("username", userName);
		maps.put("password", password);
		if(nickname!=null)
		maps.put("nickname", nickname );
		return easemobClientUtil.post("users", JSON.toJSONString(maps));
	}
}
