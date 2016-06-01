package com.mlx.guide.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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


	@Value("${uri.easemob.groupUri}")
	private String groupUri;
	@Autowired
	private EasemobClientUtil eClient;
 
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
		return eClient.post(groupUri, JSON.toJSONString(params));
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
		return eClient.put(groupUri + "/" + groupId, JSON.toJSONString(params));
	}

	/**
	 * 删除万人群
	 * 
	 * @param groupId
	 * @return
	 */
	public String deleteGroup(Long groupId) {
		return eClient.delete(groupUri + "/" + groupId);
	}
}
