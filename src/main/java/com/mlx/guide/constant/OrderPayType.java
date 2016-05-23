package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 业务类型
 * @author FIVE
 *
 */
public enum OrderPayType {
	O("","支付状态"),
	A("C1", "预登记" ), 
	B("W","未支付"),
	C("S","已支付"),
	D("SF","已完成"),
	E("C2","已取消"),
	F("RC","退款审核中"),
	G("RW","等待退款"),
	H("F","交易失败"),
	J("RS","已退款"),
	I("RP","部分退款"),
	K("RF","全额退款"),
	L("D","已删除");

	private String code;

	private String name;

	private OrderPayType(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}


	public void setCode(String code) {
		this.code = code;
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
	public static Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for( OrderPayType status : OrderPayType.values() ) {
			map.put( status.getCode(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<OrderPayType> getList() {
		return Arrays.asList( OrderPayType.values() );
	}

}
