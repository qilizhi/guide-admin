package com.mlx.guide.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GuideFans{
	private Integer id;
	private String parentUserNo;
	private String subUserNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getParentUserNo() {
		return parentUserNo;
	}
	public void setParentUserNo(String parentUserNo) {
		this.parentUserNo = parentUserNo;
	}
	public String getSubUserNo() {
		return subUserNo;
	}
	public void setSubUserNo(String subUserNo) {
		this.subUserNo = subUserNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}