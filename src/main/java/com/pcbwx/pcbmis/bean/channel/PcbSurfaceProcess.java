package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

/**
 * 表面处理
 * @author wanghl
 *
 */
public class PcbSurfaceProcess {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;		// 主键ID
	
	@JsonProperty("name")
	@FieldMap
	private String processName;	// 参数名称
	
	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;	// 是否启用
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	// 更新时间
	
	@JsonProperty("is_gold_plated")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean isGoldPlated;	// 是否化金
	
	@JsonProperty("position")
	@FieldMap
	private Integer position;	// 序列号
	
	@JsonProperty("is_gold_convert")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean isGoldConvert;	// 是否化金
	
	@JsonProperty("is_add_delivery")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean isAddDelivery;	// 是否新增交期
	
	public Integer getInnerId() {
		return innerId;
	}
	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
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
	public Boolean getIsGoldPlated() {
		return isGoldPlated;
	}
	public void setIsGoldPlated(Boolean isGoldPlated) {
		this.isGoldPlated = isGoldPlated;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Boolean getIsGoldConvert() {
		return isGoldConvert;
	}
	public void setIsGoldConvert(Boolean isGoldConvert) {
		this.isGoldConvert = isGoldConvert;
	}
	public Boolean getIsAddDelivery() {
		return isAddDelivery;
	}
	public void setIsAddDelivery(Boolean isAddDelivery) {
		this.isAddDelivery = isAddDelivery;
	}
}
