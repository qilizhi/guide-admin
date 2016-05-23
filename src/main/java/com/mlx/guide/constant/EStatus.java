package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 上下线状态枚举
 * 
 * <pre>
 * 编辑中、上线、下线
 * </pre>
 * 
 * @author quan
 * 
 */
public enum EStatus {
	
	EDIT( 1, "编辑中" ), ONLINE( 2, "上线" ), OFFLINE( 3, "下线" );

	private int id;

	private String name;

	private EStatus(int id, String name) {
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
		for( EStatus status : EStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EStatus> getList() {
		return Arrays.asList( EStatus.values() );
	}

}
