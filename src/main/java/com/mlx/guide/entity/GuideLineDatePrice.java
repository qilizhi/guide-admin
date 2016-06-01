package com.mlx.guide.entity;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class GuideLineDatePrice{
	private Integer id;
	private String lineNo;
	private BigDecimal adultPrice;
	private BigDecimal childPrice;
	private BigDecimal roomDiffPrice;
	private BigDecimal safePrice;
	private BigDecimal visaPrice;
	private Integer num;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date lineDate;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLineNo() {
		return lineNo;
	}
	public void setLineNo(String lineNo) {
		this.lineNo = lineNo;
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

	public Date getLineDate() {
		return lineDate;
	}
	public void setLineDate(Date lineDate) {
		this.lineDate = lineDate;
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
}