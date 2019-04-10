package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

/**
 * 拼板方式
 * @author wanghl
 *
 */
public class PcbJoinBoardWay {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;		// 主键ID
	
	@JsonProperty("name")
	@FieldMap
	private String joinName;	// 参数名称
	
	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;		// 是否启用

	@JsonProperty("order_type_num")
	@FieldMap
	private Integer orderTypeNum;		// 子板类型数量
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	// 更新时间
	
	public Integer getInnerId() {
		return innerId;
	}
	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}
	public String getJoinName() {
		return joinName;
	}
	public void setJoinName(String joinName) {
		this.joinName = joinName;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Integer getOrderTypeNum() {
		return orderTypeNum;
	}
	public void setOrderTypeNum(Integer orderTypeNum) {
		this.orderTypeNum = orderTypeNum;
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
}
