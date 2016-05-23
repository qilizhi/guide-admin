package com.mlx.guide.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.mlx.guide.entity.GuideFans;
import com.mlx.guide.entity.UserInfo;

public class MyFans{
	private Integer id;
	private String parentUserNo;
	private String subUserNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String nickName;
	private String headImgUrl;
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
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
