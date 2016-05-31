package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EGoodsType{
	LOCAL("local", "地陪"), LINE("line", "线路");
	private String id;
	private String name;

	private EGoodsType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Map<String,String> getMap(){
	 Map<String, String> map=new HashMap<String, String>();
	 for(EGoodsType e:EGoodsType.values()){
		 map.put(e.getId(),e.getName());
	 }
	return map;
		
	}
	public static List<EGoodsType> getList(){
		return Arrays.asList(EGoodsType.values());
	}
}
