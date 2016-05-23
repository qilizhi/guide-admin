package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 支付机构类型枚举
 * <pre>
 * 支付宝、微信支付
 * </pre>
 * @author quan
 * 
 */
public enum EPaySourceType {
	
	ALIPAY( 2, "支付宝" ), WEIXIN_PAY( 1, "微信支付" );

	private int id;

	private String name;

	private EPaySourceType(int id, String name) {
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
		for( EPaySourceType status : EPaySourceType.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EPaySourceType> getList() {
		return Arrays.asList( EPaySourceType.values() );
	}

}
