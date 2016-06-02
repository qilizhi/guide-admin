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
=======
import java.util.HashMap;
import java.util.Map;

public enum EGoodsType {

	A("local","地陪"),
	B("line","线路"),
	C("prelocal","地陪预订"),
	D("mall","商城"),
	E("hotel","酒店"),
	F("flight","机票");
	
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
	
>>>>>>> branch 'guide-admin-release' of http://gitlab.jszx.chineseml.com:3200/java/guide-admin.git
}
