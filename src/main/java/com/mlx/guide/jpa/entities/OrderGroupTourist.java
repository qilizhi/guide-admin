package com.mlx.guide.jpa.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="order_group_tourist")
@Entity
public class OrderGroupTourist {
	private Long id;

	private String guideUserNo;

	private String linkmanUserNo;

	private String touristName;

	private Integer touristSex;

	private Integer touristType;

	private Integer touristCardType;

	private String touristCardNo;

	private String touristMobile;

	private String orderId;

	private String orderGoodsId;

	private String groupNo;

	private Integer isSign;

	private Date createTime;

	private Date updateTime;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name="guide_user_no")
	public String getGuideUserNo() {
		return guideUserNo;
	}

	public void setGuideUserNo(String guideUserNo) {
		this.guideUserNo = guideUserNo;
	}
	@Column(name="linknam_user_no")
	public String getLinkmanUserNo() {
		return linkmanUserNo;
	}

	public void setLinkmanUserNo(String linkmanUserNo) {
		this.linkmanUserNo = linkmanUserNo;
	}
	@Column(name="tourist_name")
	public String getTouristName() {
		return touristName;
	}

	public void setTouristName(String touristName) {
		this.touristName = touristName;
	}
	@Column(name="tourist_card_no")
	public String getTouristCardNo() {
		return touristCardNo;
	}

	public void setTouristCardNo(String touristCardNo) {
		this.touristCardNo = touristCardNo;
	}
	@Column(name="tourist_mobile")
	public String getTouristMobile() {
		return touristMobile;
	}

	public void setTouristMobile(String touristMobile) {
		this.touristMobile = touristMobile;
	}
	@Column(name="order_id")
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	@Column(name="order_goods_id")
	public String getOrderGoodsId() {
		return orderGoodsId;
	}

	public void setOrderGoodsId(String orderGoodsId) {
		this.orderGoodsId = orderGoodsId;
	}
	@Column(name="tourist_sex")
	public Integer getTouristSex() {
		return touristSex;
	}

	public void setTouristSex(Integer touristSex) {
		this.touristSex = touristSex;
	}

	@Column(name="tourist_type")
	public Integer getTouristType() {
		return touristType;
	}

	public void setTouristType(Integer touristType) {
		this.touristType = touristType;
	}
	@Column(name="tourist_card_type")
	public Integer getTouristCardType() {
		return touristCardType;
	}

	public void setTouristCardType(Integer touristCardType) {
		this.touristCardType = touristCardType;
	}
	@Column(name="is_sign")
	public Integer getIsSign() {
		return isSign;
	}

	public void setIsSign(Integer isSign) {
		this.isSign = isSign;
	}
	@Column(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Column(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name="group_no")
	public String getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
}