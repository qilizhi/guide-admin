package com.mlx.guide.constant;


import java.util.HashMap;
import java.util.Map;

public enum EGoodsType {

	LOCAL("local","地陪"),
	LINE("line","线路"),
	PRELOCAL("prelocal","地陪预订"),
	MALL("mall","商城"),
	HOTEL("hotel","酒店"),
	FLIGHT("flight","机票");
	
	private String code;
	
	private String name;

	private EGoodsType(String code, String name) {
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



	public static Map<String,String> getMap(){
		Map<String,String> map= new HashMap<String,String>();
		for(EGoodsType e:EGoodsType.values()){
			map.put(e.getCode(), e.getName());	
		}
		return map;	
	}

}
