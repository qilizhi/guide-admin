package com.mlx.guide.entity;

import java.util.Date;


public class WxPublic{
	private Long id;
	private String name ;
	private Date updateTime;
	private Date createTime;
	private String flag;
	private String appId;
	private String appSecret;
	private String serverUrl;
	private String token;
	private String encodingaeskey;
	private String redirectUrl;
	private String partnerId;
	private String partnerKey;
	private String payCallbackUrl;
	private String iconUrl;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppSecret() {
		return appSecret;
	}
	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
	public String getServerUrl() {
		return serverUrl;
	}
	public void setServerUrl(String serverUrl) {
		this.serverUrl = serverUrl;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	public String getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	
	public String getEncodingaeskey() {
		return encodingaeskey;
	}
	public void setEncodingaeskey(String encodingaeskey) {
		this.encodingaeskey = encodingaeskey;
	}
	public String getPayCallbackUrl() {
		return payCallbackUrl;
	}
	public void setPayCallbackUrl(String payCallbackUrl) {
		this.payCallbackUrl = payCallbackUrl;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	
}