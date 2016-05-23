package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 性别枚举
 * <pre>
 * 男、女、未知
 * </pre>
 * @author quan
 *
 */
public enum ESex {

	BOY(1,"男"),
	GIRL(2,"女"),
	UNKNOWN(0,"未知");
	
	private int id;
	private String name;
	
	private ESex(int id , String name){
		this.id = id;
		this.name = name;
	}
	
	public int getId() {
		return id;
	}

	public void setId( int id ) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}
	
	/**
	 * 返回map类型形式
	 * 
	 * @return
	 */
	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for( ESex status : ESex.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<ESex> getList() {
		return Arrays.asList( ESex.values() );
	}
		
	
}
