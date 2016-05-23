package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 客人类型
 * <pre>
 * 成人、儿童
 * </pre>
 * @author quan
 *
 */
public enum EGuestType {

	ADULT( 1, "成人" ), CHILD( 2, "儿童" );

	private int id;

	private String name;

	private EGuestType(int id, String name) {
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
		for( EGuestType status : EGuestType.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EGuestType> getList() {
		return Arrays.asList( EGuestType.values() );
	}

}
