package com.mlx.guide.constant;

import java.util.LinkedHashMap;
import java.util.Map;



public enum SysCnlStatus {
	O("WAP","wap"),
	A("IOS","苹果"),
	B("ANDROID","安卓"),
	C("WEB","网站");
	
	
	private String code;
	
	private String name;

	private SysCnlStatus(String code, String name) {
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
		for( SysCnlStatus status : SysCnlStatus.values() ) {
			map.put(status.getCode(), status.getName() );
		}
		return map;
	}
	

}
