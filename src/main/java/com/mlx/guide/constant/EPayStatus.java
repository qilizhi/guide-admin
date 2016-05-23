package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付
 * 
 * <pre>
 * 1未支付2已取消3已支付4支付失败5申请退款6退款成功
 * </pre>
 * 
 * @author quan
 * 
 */
public enum EPayStatus {
	
	PAY_NO( 1, "未支付" ), 
	PAY_CANCEL( 2, "已取消" ), 
	PAY_OK( 3, "已支付" ), 
	PAY_FAIL( 4, "支付失败" ), 
	PAY_REFUND( 5, "申请退款" ), 
	PAY_REFUND_OK( 6, "退款成功" );
	
	private int id;

	private String name;

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

	private EPayStatus(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public static Map<Integer, String> getMap() {
		Map<Integer, String> map = new LinkedHashMap<Integer, String>();
		for( EPayStatus status : EPayStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	public static List<EPayStatus> getList() {
		return Arrays.asList( EPayStatus.values() );
	}
}
