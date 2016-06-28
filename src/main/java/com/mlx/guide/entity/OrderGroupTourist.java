package com.mlx.guide.entity;

import java.util.Date;

public class OrderGroupTourist {
	private Long id;

	private Integer guideUserNo;

	private Integer linkmanUserNo;

	private String touristName;

	private Integer touristSex;

	private Integer touristType;

	private Integer touristCardType;

	private String touristCardNo;

	private String touristMobile;

	private String orderId;

	private Integer orderGoodsId;

	private String groupNo;

	private Integer isSign;

	private Date createTime;

	private Date updateTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getGuideUserNo() {
		return guideUserNo;
	}

	public void setGuideUserNo(Integer guideUserNo) {
		this.guideUserNo = guideUserNo;
	}

	public Integer getLinkmanUserNo() {
		return linkmanUserNo;
	}

	public void setLinkmanUserNo(Integer linkmanUserNo) {
		this.linkmanUserNo = linkmanUserNo;
	}

	public String getTouristName() {
		return touristName;
	}

	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}

	public String getTouristCardNo() {
		return touristCardNo;
	}

	public void setTouristCardNo(String touristCardNo) {
		this.touristCardNo = touristCardNo;
	}

	public String getTouristMobile() {
		return touristMobile;
	}

	public void setTouristMobile(String touristMobile) {
		this.touristMobile = touristMobile;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(Integer orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}

	public Integer getTouristSex() {
		return touristSex;
	}

	public void setTouristSex(Integer touristSex) {
		this.touristSex = touristSex;
	}

	public Integer getTouristType() {
		return touristType;
	}

	public void setTouristType(Integer touristType) {
		this.touristType = touristType;
	}

	public Integer getTouristCardType() {
		return touristCardType;
	}

	public void setTouristCardType(Integer touristCardType) {
		this.touristCardType = touristCardType;
	}

	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
}