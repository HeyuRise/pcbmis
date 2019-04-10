package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;
import com.pcbwx.pcbmis.map.String2DateTime;
import com.pcbwx.pcbmis.map.String2Integer;

/**
 * 入库单
 * @author wanghl
 *
 */
public class OissInStorageOrder {
//	@JsonProperty("id")
//	@FieldMap
//	private Integer innerId;	// 主键ID
	
	@JsonProperty("product_period")
	@FieldMap
	private Integer productPeriod;	// 	生产周期
	
	@JsonProperty("in_storage_date")
	@FieldMap(using = String2Date.class)
	private String inStorageDate;	// 入库日期

	@JsonProperty("set_in_amount")
	@FieldMap(using = String2Integer.class)
	private String inAmountSet;	// 入库数量set

	@JsonProperty("in_amount")
	@FieldMap
	private Integer inAmountPcs;	// 入库数量pcs
	
	@JsonProperty("batch_number")
	@FieldMap
	private String batchNumber;	// 批次号
	
	@JsonProperty("creater_jobNumber")
	@FieldMap
	private String createrCode;	// 创建人工号
	
	@JsonProperty("amount_checkout")
	@FieldMap
	private Integer amountCheckoutPcs;	// 入库数量校验pcs
	
	@JsonProperty("set_amount_checkout")
	@FieldMap(using = String2Integer.class)
	private String amountCheckoutSet;	// 入库数量校验set

//	@JsonProperty("notes")
//	@FieldMap
//	private String notes;	// 甩期原因及备注
	
	@JsonProperty("state")
	@FieldMap
	private String state;	// 入库单状态
	
	@JsonProperty("order_number")
	@FieldMap
	private String orderNumber;	// 入库单号
	
//	@JsonProperty("is_next_day_shipment")
//	@FieldMap(using = Boolean2Integer.class)
//	private Boolean isNextDayShipment;	// 是否为隔天出货
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	// 更新时间

	@JsonProperty("order_type")
	@FieldMap
	private String orderType;	// 工单类型

	@JsonProperty("product_order_orderNum")
	@FieldMap
	private String productOrderNum;	// pcb工单号

	@JsonProperty("join_board_order_code")
	@FieldMap
	private String joinBoardOrderCode;	// pcb子板单code

//	@JsonProperty("order_type")
//	@FieldMap
//	private String orderType;	// 对应工单类名

//	@JsonProperty("storage_batch_number")
//	@FieldMap
//	private String storageBatchNumber;	// 入库的系统编号

	public Integer getProductPeriod() {
		return productPeriod;
	}

	public void setProductPeriod(Integer productPeriod) {
		this.productPeriod = productPeriod;
	}

	public String getInStorageDate() {
		return inStorageDate;
	}

	public void setInStorageDate(String inStorageDate) {
		this.inStorageDate = inStorageDate;
	}

	public Integer getInAmountPcs() {
		return inAmountPcs;
	}

	public void setInAmountPcs(Integer inAmountPcs) {
		this.inAmountPcs = inAmountPcs;
	}

	public String getBatchNumber() {
		return batchNumber;
	}

	public void setBatchNumber(String batchNumber) {
		this.batchNumber = batchNumber;
	}

	public String getCreaterCode() {
		return createrCode;
	}

	public void setCreaterCode(String createrCode) {
		this.createrCode = createrCode;
	}

	public Integer getAmountCheckoutPcs() {
		return amountCheckoutPcs;
	}

	public void setAmountCheckoutPcs(Integer amountCheckoutPcs) {
		this.amountCheckoutPcs = amountCheckoutPcs;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getInnerCreateTime() {
		return innerCreateTime;
	}

	public void setInnerCreateTime(String innerCreateTime) {
		this.innerCreateTime = innerCreateTime;
	}

	public String getInnerUpdateTime() {
		return innerUpdateTime;
	}

	public void setInnerUpdateTime(String innerUpdateTime) {
		this.innerUpdateTime = innerUpdateTime;
	}

	public String getAmountCheckoutSet() {
		return amountCheckoutSet;
	}

	public void setAmountCheckoutSet(String amountCheckoutSet) {
		this.amountCheckoutSet = amountCheckoutSet;
	}

	public String getInAmountSet() {
		return inAmountSet;
	}

	public void setInAmountSet(String inAmountSet) {
		this.inAmountSet = inAmountSet;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getProductOrderNum() {
		return productOrderNum;
	}

	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}

	public String getJoinBoardOrderCode() {
		return joinBoardOrderCode;
	}

	public void setJoinBoardOrderCode(String joinBoardOrderCode) {
		this.joinBoardOrderCode = joinBoardOrderCode;
	}

}
