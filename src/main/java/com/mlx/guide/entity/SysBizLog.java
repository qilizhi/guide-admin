package com.mlx.guide.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class SysBizLog{
	private Integer id;
	private Integer bizType;
	private String operatUserNo;
	private String operatPerson;
	private String content;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer flag;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBizType() {
		return bizType;
	}
	public void setBizType(Integer bizType) {
		this.bizType = bizType;
	}
	public String getOperatUserNo() {
		return operatUserNo;
	}
	public void setOperatUserNo(String operatUserNo) {
		this.operatUserNo = operatUserNo;
	}
	public String getOperatPerson() {
		return operatPerson;
	}
	public void setOperatPerson(String operatPerson) {
		this.operatPerson = operatPerson;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Integer getFlag() {
		return flag;
	}
	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}