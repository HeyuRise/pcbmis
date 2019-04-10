package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;

public class OutStorage {

	@JsonProperty("id")
	@FieldMap
	private Integer orderId;
	
	@JsonProperty("out_sum")
	@FieldMap
	private Integer outSum;
	
	@JsonProperty("state")
	@FieldMap
	private String state;
	
	@JsonProperty("product_type")
	@FieldMap
	private String productType;
	
	@JsonProperty("order_number")
	@FieldMap
	private String orderNumber;
	
	@JsonProperty("board_name")
	@FieldMap
	private String boardName;
	
	@JsonProperty("out_storage_date")
	@FieldMap(using = String2Date.class)
	private String outStorageDate;

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getOutSum() {
		return outSum;
	}

	public void setOutSum(Integer outSum) {
		this.outSum = outSum;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getOutStorageDate() {
		return outStorageDate;
	}

	public void setOutStorageDate(String outStorageDate) {
		this.outStorageDate = outStorageDate;
	}

	@Override
	public String toString() {
		return "OutStorage [orderId=" + orderId + ", outSum=" + outSum + ", state=" + state + ", productType="
				+ productType + ", orderNumber=" + orderNumber + ", boardName=" + boardName + ", outStorageDate="
				+ outStorageDate + "]";
	}

}
