package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 签到状态(1未签到2已签到)
 * 
 * @author cyz
 *
 */
public enum ESignInStatus {

	NOT_SIGNED(1, "未签到"), SIGNED(2, "已签到");

	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private ESignInStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for (ESignInStatus status : ESignInStatus.values()) {
			map.put(status.getId(), status.getName());
		}
		return map;
	}

	public static Map<Byte, String> getByteMap() {
		Map<Byte, String> map = new LinkedHashMap<Byte, String>();
		for (ESignInStatus status : ESignInStatus.values()) {
			map.put(status.getId().byteValue(), status.getName());
		}
		return map;
	}

	public static List<ESignInStatus> getList() {
		return Arrays.asList(ESignInStatus.values());
	}
}
