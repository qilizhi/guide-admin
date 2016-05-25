package com.mlx.guide.constant;

import java.util.LinkedHashMap;
import java.util.Map;



public enum RefundStatus {
	O("","请选择"),
	A("RR","退款申请中"),
	B("RC","退款审核中"),
	C("RW","等待退款"),
	D("RS","退款成功"),
	E("F","交易失败");
	
	private String code;
	
	private String name;

	private RefundStatus(String code, String name) {
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

	public void setName(String name) {
		this.name = name;
	}
	
	
	public static Map<String, String> getMap() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for( RefundStatus status : RefundStatus.values() ) {
			map.put(status.getCode(), status.getName() );
		}
		return map;
	}
	

}
