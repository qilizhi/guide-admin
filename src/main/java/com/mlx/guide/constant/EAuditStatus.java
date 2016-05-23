package com.mlx.guide.constant;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 审核状态(0未提交审核1待审核2审核通过3审核未通过)
 * @author qilizhi
 *
 */
public enum EAuditStatus {

	AUDIT_NOSUBMIT(0,"未提交审核"),
	AUDIT_ON(1,"待审核"),
	AUDIT_OK(2,"审核通过"),
	AUDIT_FAIL(3,"审核不通过");
	
	private Integer id;
	private String name;
	
	private EAuditStatus(Integer id , String name){
		this.id = id;
		this.name = name;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId( Integer id ) {
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
		for( EAuditStatus status : EAuditStatus.values() ) {
			map.put( status.getId(), status.getName() );
		}
		return map;
	}

	public static Map<Byte, String> getByteMap() {
		Map<Byte, String> map = new LinkedHashMap<Byte, String>();
		for( EAuditStatus status : EAuditStatus.values() ) {
			map.put( status.getId().byteValue(), status.getName() );
		}
		return map;
	}

	/**
	 * 返回list类型形式
	 * 
	 * @return
	 */
	public static List<EAuditStatus> getList() {
		return Arrays.asList( EAuditStatus.values() );
	}
	
	/**
	 * 根据id转成枚举
	 * @param id
	 * @return
	 */
	public static EAuditStatus getCategory( int id ) {
		for( EAuditStatus status : EAuditStatus.values() ) {
			if(status.getId() == id){
				return status;
			}
		}
		return EAuditStatus.AUDIT_ON;
	}
	
}
