package com.mlx.guide.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class WxJsapiTicket{
	private Long id;
	private String jsapiTicket;
	private Long expiresIn;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateDate;
	private Long weixinPublicId;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJsapiTicket() {
		return jsapiTicket;
	}
	public void setJsapiTicket(String jsapiTicket) {
		this.jsapiTicket = jsapiTicket;
	}
	public Long getExpiresIn() {
		return expiresIn;
	}
	public void setExpiresIn(Long expiresIn) {
		this.expiresIn = expiresIn;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Long getWeixinPublicId() {
		return weixinPublicId;
	}
	public void setWeixinPublicId(Long weixinPublicId) {
		this.weixinPublicId = weixinPublicId;
	}
}