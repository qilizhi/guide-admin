package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum EProductNoPrefix {
	Line("LI", "线路"), Service("SE", "导服"), Strategy("ST", "攻略"), Order("OR", "订单"), Tuan("TU", "团"), VirtualGoods("VI",
			"虚拟商品");

	String prefix;
	String name;

	private EProductNoPrefix(String prefix, String name) {
		this.prefix = prefix;
		this.name = name;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 返回map类型形式
	 * 
	 * @return
	 */
	public static Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (EProductNoPrefix status : EProductNoPrefix.values()) {
			map.put(status.getPrefix(), status.getName());
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EProductNoPrefix> getList() {
		return Arrays.asList(EProductNoPrefix.values());
	}

}
