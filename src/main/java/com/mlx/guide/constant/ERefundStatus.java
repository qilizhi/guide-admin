package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 退款状态枚举
 * @author quan
 *
 */
public enum ERefundStatus {

	APPLY( 1, "申请" ),AUDIT( 2, "审核中" ), APPLY_OK( 3, "退款成功" );

	private int id;

	private String name;

	private ERefundStatus(int id, String name) {
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
		for( ERefundStatus status : ERefundStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<ERefundStatus> getList() {
		return Arrays.asList( ERefundStatus.values() );
	}
	
}
