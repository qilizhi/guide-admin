package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 订单类型 线路:line 酒店:hotel 机票:flight 商城:mall
 * 
 * @author CYZ
 *
 */
public enum EOrderType {

	L("LINE", "线路"), H("HOTEL", "酒店"), F("FLIGHT", "机票"), M("MALL", "商城");

	private String code;

	private String name;

	private EOrderType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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
		for( EOrderType status : EOrderType.values() ) {
			map.put( status.getCode(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EOrderType> getList() {
		return Arrays.asList( EOrderType.values() );
	}
}
