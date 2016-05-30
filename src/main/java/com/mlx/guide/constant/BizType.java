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
public enum BizType {
	GUIDE( 1, "导游" ), 
	STTATEGY(2,"攻略"),
	LINE(3,"线路"),
	DIPEI(4,"地陪"),
	OTHER(5,"其他"),
	REFUND(6,"退款操作日志");
	
	

	private int id;

	private String name;

	private BizType(int id, String name) {
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
		for( BizType status : BizType.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<BizType> getList() {
		return Arrays.asList( BizType.values() );
	}

}
