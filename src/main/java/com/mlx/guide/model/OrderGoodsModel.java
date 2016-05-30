package com.mlx.guide.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * 订单商品model
 * @author QiQi-04-PC
 *@category 商品
 */
public class OrderGoodsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String goodsName;
	
	private String goodsType;
	private String groupId;
	private String id;
	private String orderId;
	private String remark;
	private String singleRoomNum;
	private String singleRoomPrice;
	private String skuId;
	private String tripDate;
	private String updateTime;
	private String visaNum;
	private String adultMarketPrice;
	
	private String visaPrice;
	
	private String adultNum;
	private String childSellCount;
	private String adultSellPrice;
	
	private String childMarketPrice;
	private String childNum;
	private String adultSellCount;
	
	private String childSellPrice;
	private String createTime;
	private String goodsId;
	
	
    private List<GoodsInsurances> goodsInsurances=new ArrayList<GoodsInsurances>();
    
    private List<GoodsTourists> goodsTourists = new ArrayList<GoodsTourists>();
	
     public String getVisaPrice() {
		return visaPrice;
	}
	public void setVisaPrice(String visaPrice) {
		this.visaPrice = visaPrice;
	}
	public List<GoodsInsurances> getGoodsInsurances() {
		return goodsInsurances;
	}
	public void setGoodsInsurances(List<GoodsInsurances> goodsInsurances) {
		this.goodsInsurances = goodsInsurances;
	}
	public List<GoodsTourists> getGoodsTourists() {
		return goodsTourists;
	}
	public void setGoodsTourists(List<GoodsTourists> goodsTourists) {
		this.goodsTourists = goodsTourists;
	}
	public String getAdultMarketPrice() {
		return adultMarketPrice;
	}
	public void setAdultMarketPrice(String adultMarketPrice) {
		this.adultMarketPrice = adultMarketPrice;
	}
	public String getAdultNum() {
		return adultNum;
	}
	public void setAdultNum(String adultNum) {
		this.adultNum = adultNum;
	}
	public String getChildSellCount() {
		return childSellCount;
	}
	public void setChildSellCount(String childSellCount) {
		this.childSellCount = childSellCount;
	}
	public String getAdultSellPrice() {
		return adultSellPrice;
	}
	public void setAdultSellPrice(String adultSellPrice) {
		this.adultSellPrice = adultSellPrice;
	}
	public String getChildMarketPrice() {
		return childMarketPrice;
	}
	public void setChildMarketPrice(String childMarketPrice) {
		this.childMarketPrice = childMarketPrice;
	}
	public String getChildNum() {
		return childNum;
	}
	public void setChildNum(String childNum) {
		this.childNum = childNum;
	}
	public String getAdultSellCount() {
		return adultSellCount;
	}
	public void setAdultSellCount(String adultSellCount) {
		this.adultSellCount = adultSellCount;
	}
	public String getChildSellPrice() {
		return childSellPrice;
	}
	public void setChildSellPrice(String childSellPrice) {
		this.childSellPrice = childSellPrice;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	
	public String getGoodsType() {
		return goodsType;
	}
	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSingleRoomNum() {
		return singleRoomNum;
	}
	public void setSingleRoomNum(String singleRoomNum) {
		this.singleRoomNum = singleRoomNum;
	}
	public String getSingleRoomPrice() {
		return singleRoomPrice;
	}
	public void setSingleRoomPrice(String singleRoomPrice) {
		this.singleRoomPrice = singleRoomPrice;
	}
	public String getSkuId() {
		return skuId;
	}
	public void setSkuId(String skuId) {
		this.skuId = skuId;
	}
	public String getTripDate() {
		return tripDate;
	}
	public void setTripDate(String tripDate) {
		this.tripDate = tripDate;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getVisaNum() {
		return visaNum;
	}
	public void setVisaNum(String visaNum) {
		this.visaNum = visaNum;
	}
	
	public  class  GoodsInsurances {
		private String createTime;
		private String id;
		private String insuranceId;
		private String insuranceNum;
		private String insurancePrice;
		private String orderGoodsId;
		private String updateTime;
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getInsuranceId() {
			return insuranceId;
		}
		public void setInsuranceId(String insuranceId) {
			this.insuranceId = insuranceId;
		}
		public String getInsuranceNum() {
			return insuranceNum;
		}
		public void setInsuranceNum(String insuranceNum) {
			this.insuranceNum = insuranceNum;
		}
		public String getInsurancePrice() {
			return insurancePrice;
		}
		public void setInsurancePrice(String insurancePrice) {
			this.insurancePrice = insurancePrice;
		}
		public String getOrderGoodsId() {
			return orderGoodsId;
		}
		public void setOrderGoodsId(String orderGoodsId) {
			this.orderGoodsId = orderGoodsId;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		
		
	}
	
	public class GoodsTourists{
		private String createTime;
		private String id;
		private String orderGoodsId;
		private String touristCardNo;
		private String touristCardType;
		private String touristMobile;
		private String  touristName;
		private String touristSex;
		private String touristType;
		private String updateTime;
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getOrderGoodsId() {
			return orderGoodsId;
		}
		public void setOrderGoodsId(String orderGoodsId) {
			this.orderGoodsId = orderGoodsId;
		}
		public String getTouristCardNo() {
			return touristCardNo;
		}
		public void setTouristCardNo(String touristCardNo) {
			this.touristCardNo = touristCardNo;
		}
		public String getTouristCardType() {
			return touristCardType;
		}
		public void setTouristCardType(String touristCardType) {
			this.touristCardType = touristCardType;
		}
		public String getTouristMobile() {
			return touristMobile;
		}
		public void setTouristMobile(String touristMobile) {
			this.touristMobile = touristMobile;
		}
		public String getTouristName() {
			return touristName;
		}
		public void setTouristName(String touristName) {
			this.touristName = touristName;
		}
		public String getTouristSex() {
			return touristSex;
		}
		public void setTouristSex(String touristSex) {
			this.touristSex = touristSex;
		}
		public String getTouristType() {
			return touristType;
		}
		public void setTouristType(String touristType) {
			this.touristType = touristType;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		
		
	}

}
