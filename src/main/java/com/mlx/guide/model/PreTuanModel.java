package com.mlx.guide.model;

import java.io.Serializable;

/**
 * 近期出团
 * @author QiQi-04-PC
 *
 */
public class PreTuanModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String groupNo;
	private String goodsId;
	private String goodsType;
	private String goodsName;
	private Integer orderNum;
	private Integer touristNum;
	private String tripDate;

	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	
	public String getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(String groupNo) {
		this.groupNo = groupNo;
	}
	public Integer getTouristNum() {
		return touristNum;
	}
	public void setTouristNum(Integer touristNum) {
		this.touristNum = touristNum;
	}
	public String getTripDate() {
		return tripDate;
	}
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	
}
