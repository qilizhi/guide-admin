package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户类型枚举
 * <pre>
 * 普通用户、导游用户
 * </pre>
 * @author quan
 *
 */
public enum EUserType {

	USER( 1, "普通用户" ), 
	GUIDE_USER( 2, "导游用户" ),
	/**
	 * 此枚举类型值不用传递给数据库,只作为系统shiro判断存储使用
	 */
	PLATFORM_USER( 3, "平台用户" );

	private int id;

	private String name;

	private EUserType(int id, String name) {
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
		for( EUserType status : EUserType.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EUserType> getList() {
		return Arrays.asList( EUserType.values() );
	}	
	
}
