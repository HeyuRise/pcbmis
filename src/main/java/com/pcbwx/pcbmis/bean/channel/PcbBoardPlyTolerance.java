package com.pcbwx.pcbmis.bean.channel;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class PcbBoardPlyTolerance {
	@JsonProperty("id")
	@FieldMap
	private Integer innerId;	// 主键ID
	
	@JsonProperty("name")
	@FieldMap
	private String toleranceName;	// 公差名称

	@JsonProperty("board_ply_type")
	@FieldMap
	private String boardPlyType;	// 公差类型

	@JsonProperty("max_board_ply_tolerance")
	@FieldMap
	private BigDecimal maxBoardPlyTolerance;	// 最大板厚公差

	@JsonProperty("min_board_ply_tolerance")
	@FieldMap
	private BigDecimal minBoardPlyTolerance;	// 最小板厚公差
	
	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
	private Boolean enable;	// 是否启用
	
	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime; // 创建时间
	
	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	//	更新时间

	public Integer getInnerId() {
		return innerId;
	}

	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public String getToleranceName() {
		return toleranceName;
	}

	public void setToleranceName(String toleranceName) {
		this.toleranceName = toleranceName;
	}

	public String getBoardPlyType() {
		return boardPlyType;
	}

	public void setBoardPlyType(String boardPlyType) {
		this.boardPlyType = boardPlyType;
	}

	public BigDecimal getMaxBoardPlyTolerance() {
		return maxBoardPlyTolerance;
	}

	public void setMaxBoardPlyTolerance(BigDecimal maxBoardPlyTolerance) {
		this.maxBoardPlyTolerance = maxBoardPlyTolerance;
	}

	public BigDecimal getMinBoardPlyTolerance() {
		return minBoardPlyTolerance;
	}

	public void setMinBoardPlyTolerance(BigDecimal minBoardPlyTolerance) {
		this.minBoardPlyTolerance = minBoardPlyTolerance;
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
