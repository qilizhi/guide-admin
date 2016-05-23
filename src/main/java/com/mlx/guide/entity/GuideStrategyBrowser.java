package com.mlx.guide.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GuideStrategyBrowser{
	private Integer id;
	private String stragegyNo;
	private String userNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date browserTime;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStragegyNo() {
		return stragegyNo;
	}
	public void setStragegyNo(String stragegyNo) {
		this.stragegyNo = stragegyNo;
	}
	public String getUserNo() {
		return userNo;
	}
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	public Date getBrowserTime() {
		return browserTime;
	}
	public void setBrowserTime(Date browserTime) {
		this.browserTime = browserTime;
	}
}