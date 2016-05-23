package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum EGroupType {
	STAR_GROUP(1, "明星群"), PLAYER_GROUP(0, "玩家群");
	private Integer id;
	private String name;

	private EGroupType(Integer id, String name) {
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

	public static Map<Integer,String> getMap(){
	 Map<Integer, String> map=new HashMap<Integer, String>();
	 for(EGroupType e:EGroupType.values()){
		 map.put(e.getId(),e.getName());
	 }
	return map;
		
	}
	public static List<EGroupType> getList(){
		return Arrays.asList(EGroupType.values());
	}
}
