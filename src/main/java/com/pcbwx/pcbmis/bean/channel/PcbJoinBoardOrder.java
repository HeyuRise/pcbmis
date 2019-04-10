package com.pcbwx.pcbmis.bean.channel;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2DateTime;
import com.pcbwx.pcbmis.map.String2Integer;

/**
 * 拼板
 * @author wanghl
 *
 */
public class PcbJoinBoardOrder {
	@JsonProperty("code")
	@FieldMap
	private String joinBoardCode;	// 子板的系统编号
	
	@JsonProperty("product_order_num")
	@FieldMap
	private String productOrderNum;	// 	pcb工单号
	
	@JsonProperty("join_board_name")
	@FieldMap
	private String joinBoardName;	// 	子板板名
	
	@JsonProperty("join_board_long")
	@FieldMap
	private BigDecimal joinBoardLong;	// 子板长
	
	@JsonProperty("join_board_wide")
	@FieldMap
	private BigDecimal joinBoardWide;	// 子板宽
	
	@JsonProperty("join_board_area")
	@FieldMap
	private BigDecimal joinBoardArea;	// 子板面积
	
	@JsonProperty("character")
	@FieldMap
	private String boardChar;	// 子板字符的系统编号

	@JsonProperty("single_board_hole_num")
	@FieldMap
	private Integer singleBoardHoleNum;	// 子板单板孔数量
	
	@JsonProperty("min_aperture")
	@FieldMap(using = String2Integer.class)
	private String minAperture;	// 子板最小孔径
	
	@JsonProperty("gong_length")
	@FieldMap
	private BigDecimal gongLength;	// 子板锣程长度
	
	@JsonProperty("min_line_width")
	@FieldMap
	private BigDecimal minLineWidth;	// 子板最小线宽
	
	@JsonProperty("min_line_distance")
	@FieldMap
	private BigDecimal minLineDistance;	// 子板最小线距

	@JsonProperty("prevent_smt")
	@FieldMap
	private String preventSmt;	// 子板阻焊

	@JsonProperty("product_num")
	@FieldMap
	private Integer productNum;	// 子板生产数量

	@JsonProperty("optimal_product_num")
	@FieldMap(using = String2Integer.class)
	private String optimalProductNum;	// 子板最优生产数量

	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
	private String innerCreateTime;	// 创建时间
	
	@JsonProperty("updated_at")	
	@FieldMap(using = String2DateTime.class)
	private String innerUpdateTime;	// 更新时间

	public String getJoinBoardName() {
		return joinBoardName;
	}

	public void setJoinBoardName(String joinBoardName) {
		this.joinBoardName = joinBoardName;
	}

	public BigDecimal getJoinBoardLong() {
		return joinBoardLong;
	}

	public void setJoinBoardLong(BigDecimal joinBoardLong) {
		this.joinBoardLong = joinBoardLong;
	}

	public BigDecimal getJoinBoardWide() {
		return joinBoardWide;
	}

	public void setJoinBoardWide(BigDecimal joinBoardWide) {
		this.joinBoardWide = joinBoardWide;
	}

	public BigDecimal getJoinBoardArea() {
		return joinBoardArea;
	}

	public void setJoinBoardArea(BigDecimal joinBoardArea) {
		this.joinBoardArea = joinBoardArea;
	}

	public String getBoardChar() {
		return boardChar;
	}

	public void setBoardChar(String boardChar) {
		this.boardChar = boardChar;
	}

	public Integer getSingleBoardHoleNum() {
		return singleBoardHoleNum;
	}

	public void setSingleBoardHoleNum(Integer singleBoardHoleNum) {
		this.singleBoardHoleNum = singleBoardHoleNum;
	}

	public String getMinAperture() {
		return minAperture;
	}

	public void setMinAperture(String minAperture) {
		this.minAperture = minAperture;
	}

	public BigDecimal getGongLength() {
		return gongLength;
	}

	public void setGongLength(BigDecimal gongLength) {
		this.gongLength = gongLength;
	}

	public BigDecimal getMinLineWidth() {
		return minLineWidth;
	}

	public void setMinLineWidth(BigDecimal minLineWidth) {
		this.minLineWidth = minLineWidth;
	}

	public BigDecimal getMinLineDistance() {
		return minLineDistance;
	}

	public void setMinLineDistance(BigDecimal minLineDistance) {
		this.minLineDistance = minLineDistance;
	}

	public String getProductOrderNum() {
		return productOrderNum;
	}

	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
	}

	public String getPreventSmt() {
		return preventSmt;
	}

	public void setPreventSmt(String preventSmt) {
		this.preventSmt = preventSmt;
	}

	public String getJoinBoardCode() {
		return joinBoardCode;
	}

	public void setJoinBoardCode(String joinBoardCode) {
		this.joinBoardCode = joinBoardCode;
	}

	public Integer getProductNum() {
		return productNum;
	}

	public void setProductNum(Integer productNum) {
		this.productNum = productNum;
	}

	public String getOptimalProductNum() {
		return optimalProductNum;
	}

	public void setOptimalProductNum(String optimalProductNum) {
		this.optimalProductNum = optimalProductNum;
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
