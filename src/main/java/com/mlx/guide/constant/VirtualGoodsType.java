package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum VirtualGoodsType {

		M(1, "美丽卡"), T(2, "课程 ");
		private Integer id;
		private String name;

		
		private VirtualGoodsType(Integer id, String name) {
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
		 for(VirtualGoodsType a:VirtualGoodsType.values()){
			 map.put(a.getId(),a.getName());
		 }
		return map;
			
		}
		public static List<VirtualGoodsType> getList(){
			return Arrays.asList(VirtualGoodsType.values());
		}
	

}
