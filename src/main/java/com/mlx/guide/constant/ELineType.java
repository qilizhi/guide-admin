package com.mlx.guide.constant;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 线路类型(1国内2出境)
 * @author cyz
 *
 */
public enum ELineType {

	INBOUND(1, "国内"), OUTBOUND(2, "出境");
	
	private Integer id;
	private String name;
	
	private ELineType(Integer id , String name){
		this.id = id;
		this.name = name;
	}

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

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for (ELineType status : ELineType.values()) {
			map.put(status.getId(), status.getName());
		}
		return map;
	}
	public static Map<Byte, String> getByteMap() {
		Map<Byte, String> map = new LinkedHashMap<Byte, String>();
		for (ELineType status : ELineType.values()) {
			map.put(status.getId().byteValue(), status.getName());
		}
		return map;
	}

}
