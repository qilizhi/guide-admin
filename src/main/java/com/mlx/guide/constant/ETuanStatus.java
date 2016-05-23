package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 出团状态(1待出团2已出团3已取消)
 * 
 * @author cyz
 *
 */
public enum ETuanStatus {

	TOUR(1, "待出团"), TOURED(2, "已出团"), CANCEL(3, "已取消");

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

	private ETuanStatus(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for (ETuanStatus status : ETuanStatus.values()) {
			map.put(status.getId(), status.getName());
		}
		return map;
	}

	public static Map<Byte, String> getByteMap() {
		Map<Byte, String> map = new LinkedHashMap<Byte, String>();
		for (ETuanStatus status : ETuanStatus.values()) {
			map.put(status.getId().byteValue(), status.getName());
		}
		return map;
	}

	public static List<ETuanStatus> getList() {
		return Arrays.asList(ETuanStatus.values());
	}
}
