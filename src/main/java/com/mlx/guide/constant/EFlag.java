package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 是否有效
 * 
 * <pre>
 * 无效、有效
 * </pre>
 * 
 * @author quan
 * 
 */
public enum EFlag {

	INVALID( 0, "无效" ), VALID( 1, "有效" );

	private Integer id;

	private String name;

	private EFlag(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
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
		for( EFlag status : EFlag.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EFlag> getList() {
		return Arrays.asList( EFlag.values() );
	}

}
