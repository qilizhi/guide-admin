package com.mlx.guide.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class OrderModel {
	private String clientIp;
	private Date createTime;
	private String currency;
	private String endDate;
	private String expTime;
	private String id;
	private String orderCancel;
	private String orderDate;
	private OrderDescribe orderDescribe;
	private List<OrderGoodsModel> orderGoods=new ArrayList<OrderGoodsModel>();
	private String orderId;
	private String orderStatus;
	private String orderTime;
	private String orderType;
	private String payFee;
	private String refundFee;
	private String refundFlag;
	private String remark;
	private String respCode;
	private String respMsg;
	private String startDate;
	private String sysCnl;
	private BigDecimal totalMarketPrice;
	private BigDecimal totalSellPrice;
	private Date updateTime;
	private String userId;
	private String userName;
	private String payDate;
	private String payTime;
	 
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderCancel() {
		return orderCancel;
	}
	public void setOrderCancel(String orderCancel) {
		this.orderCancel = orderCancel;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	
	public OrderDescribe getOrderDescribe() {
		return orderDescribe;
	}
	public void setOrderDescribe(OrderDescribe orderDescribe) {
		this.orderDescribe = orderDescribe;
	}
	public List<OrderGoodsModel> getOrderGoods() {
		return orderGoods;
	}
	public void setOrderGoods(List<OrderGoodsModel> orderGoods) {
		this.orderGoods = orderGoods;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	public String getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getPayFee() {
		return payFee;
	}
	public void setPayFee(String payFee) {
		this.payFee = payFee;
	}
	public String getRefundFee() {
		return refundFee;
	}
	public void setRefundFee(String refundFee) {
		this.refundFee = refundFee;
	}
	public String getRefundFlag() {
		return refundFlag;
	}
	public void setRefundFlag(String refundFlag) {
		this.refundFlag = refundFlag;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getSysCnl() {
		return sysCnl;
	}
	public void setSysCnl(String sysCnl) {
		this.sysCnl = sysCnl;
	}


	public BigDecimal getTotalMarketPrice() {
		return totalMarketPrice;
	}
	public void setTotalMarketPrice(BigDecimal totalMarketPrice) {
		this.totalMarketPrice = totalMarketPrice;
	}
	public BigDecimal getTotalSellPrice() {
		return totalSellPrice;
	}
	public void setTotalSellPrice(BigDecimal totalSellPrice) {
		this.totalSellPrice = totalSellPrice;
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
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getPayTime() {
		return payTime;
	}
	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}
	
	
	public class OrderDescribe {
		private String address;
		private String addressType;
		private String contactsName;
		private String createTime;
		private String favourableFee;
		private String favourableId;
		private String id;
		private String invoice;
		private String invoiceMethod;
		private String invoiceType;
		private String mobile;
		private String orderId;
		private String postcode;
		private String remark;
		private String tel;
		private String transportDayType;
		private String transportFee;
		private String transportId;
		private String transportNum;
		private String transportTime;
		private String updateTime;
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
		public String getAddressType() {
			return addressType;
		}
		public void setAddressType(String addressType) {
			this.addressType = addressType;
		}
		public String getContactsName() {
			return contactsName;
		}
		public void setContactsName(String contactsName) {
			this.contactsName = contactsName;
		}
		public String getCreateTime() {
			return createTime;
		}
		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}
		public String getFavourableFee() {
			return favourableFee;
		}
		public void setFavourableFee(String favourableFee) {
			this.favourableFee = favourableFee;
		}
		public String getFavourableId() {
			return favourableId;
		}
		public void setFavourableId(String favourableId) {
			this.favourableId = favourableId;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getInvoice() {
			return invoice;
		}
		public void setInvoice(String invoice) {
			this.invoice = invoice;
		}
		public String getInvoiceMethod() {
			return invoiceMethod;
		}
		public void setInvoiceMethod(String invoiceMethod) {
			this.invoiceMethod = invoiceMethod;
		}
		public String getInvoiceType() {
			return invoiceType;
		}
		public void setInvoiceType(String invoiceType) {
			this.invoiceType = invoiceType;
		}
		public String getMobile() {
			return mobile;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public String getOrderId() {
			return orderId;
		}
		public void setOrderId(String orderId) {
			this.orderId = orderId;
		}
		public String getPostcode() {
			return postcode;
		}
		public void setPostcode(String postcode) {
			this.postcode = postcode;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getTel() {
			return tel;
		}
		public void setTel(String tel) {
			this.tel = tel;
		}
		public String getTransportDayType() {
			return transportDayType;
		}
		public void setTransportDayType(String transportDayType) {
			this.transportDayType = transportDayType;
		}
		public String getTransportFee() {
			return transportFee;
		}
		public void setTransportFee(String transportFee) {
			this.transportFee = transportFee;
		}
		public String getTransportId() {
			return transportId;
		}
		public void setTransportId(String transportId) {
			this.transportId = transportId;
		}
		public String getTransportNum() {
			return transportNum;
		}
		public void setTransportNum(String transportNum) {
			this.transportNum = transportNum;
		}
		public String getTransportTime() {
			return transportTime;
		}
		public void setTransportTime(String transportTime) {
			this.transportTime = transportTime;
		}
		public String getUpdateTime() {
			return updateTime;
		}
		public void setUpdateTime(String updateTime) {
			this.updateTime = updateTime;
		}
		
		
        
    }
}

	
	
