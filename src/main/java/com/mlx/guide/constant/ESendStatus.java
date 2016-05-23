package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 发送状态枚举
 * 
 * <pre>
 * 待发送、发送成功
 * </pre>
 * 
 * @author quan
 * 
 */
public enum ESendStatus {

	SEND_NO( 1, "发送成功" ), SEND_OK( 2, "待发送" );

	private int id;

	private String name;

	private ESendStatus(int id, String name) {
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
		for( ESendStatus status : ESendStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<ESendStatus> getList() {
		return Arrays.asList( ESendStatus.values() );
	}

}
