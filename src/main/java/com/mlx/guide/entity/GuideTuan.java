package com.mlx.guide.entity;

import java.math.BigDecimal;
import java.util.Date;

public class GuideTuan {
    private Long id;

    private String name;

    private String tuanNo;

    private Date tuanDate;
    private Date tuanEndDate;

    private String goodsType;

    private String goodsNo;

    private Date createTime;

    private Date updateTime;

    private Integer fullNum;

    private Integer personNum;

    private Integer orderNum;

    private String userNo;

    private String userName;

    private Byte tuanStatus;

    private Long groupId;

    private BigDecimal adultPrice;

    private BigDecimal childPrice;

    private BigDecimal roomDiffPrice;

    private BigDecimal safePrice;

    private BigDecimal visaPrice;

    private Integer num;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTuanNo() {
        return tuanNo;
    }

    public void setTuanNo(String tuanNo) {
        this.tuanNo = tuanNo;
    }

    public Date getTuanDate() {
        return tuanDate;
    }

    public void setTuanDate(Date tuanDate) {
        this.tuanDate = tuanDate;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsNo() {
        return goodsNo;
    }

    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
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

    public Integer getFullNum() {
        return fullNum;
    }

    public void setFullNum(Integer fullNum) {
        this.fullNum = fullNum;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Byte getTuanStatus() {
        return tuanStatus;
    }

    public void setTuanStatus(Byte tuanStatus) {
        this.tuanStatus = tuanStatus;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public BigDecimal getAdultPrice() {
        return adultPrice;
    }

    public void setAdultPrice(BigDecimal adultPrice) {
        this.adultPrice = adultPrice;
    }

    public BigDecimal getChildPrice() {
        return childPrice;
    }

    public void setChildPrice(BigDecimal childPrice) {
        this.childPrice = childPrice;
    }

    public BigDecimal getRoomDiffPrice() {
        return roomDiffPrice;
    }

    public void setRoomDiffPrice(BigDecimal roomDiffPrice) {
        this.roomDiffPrice = roomDiffPrice;
    }

    public BigDecimal getSafePrice() {
        return safePrice;
    }

    public void setSafePrice(BigDecimal safePrice) {
        this.safePrice = safePrice;
    }

    public BigDecimal getVisaPrice() {
        return visaPrice;
    }

    public void setVisaPrice(BigDecimal visaPrice) {
        this.visaPrice = visaPrice;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

	public Date getTuanEndDate() {
		return tuanEndDate;
	}

	public void setTuanEndDate(Date tuanEndDate) {
		this.tuanEndDate = tuanEndDate;
	}
}