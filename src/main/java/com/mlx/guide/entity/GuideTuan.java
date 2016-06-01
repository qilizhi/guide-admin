package com.mlx.guide.entity;

import java.util.Date;

public class GuideTuan {
    private Long id;

    private String name;

    private String tuanNo;

    private Date tuanDate;

    private String goodsType;

    private String goodsNo;

    private Date createTime;

    private Integer fullNum;

    private Integer personNum;

    private Integer orderNum;

    private String userNo;

    private String userName;

    private Byte tuanStatus;
    
	private Date updateTime;
	

    public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

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
}