package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户状态枚举
 * <pre>
 * 正常、禁止
 * </pre>
 * @author quan
 *
 */
public enum EUserStatus {

	VALID( 1, "正常" ), INVALID( 2, "禁止" );

	private int id;

	private String name;

	private EUserStatus(int id, String name) {
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
		for( EUserStatus status : EUserStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EUserStatus> getList() {
		return Arrays.asList( EUserStatus.values() );
	}	
	
}
