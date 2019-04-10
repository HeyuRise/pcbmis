package com.pcbwx.pcbmis.bean.channel;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class PcbFrameTolerances {
	@JsonProperty("id")
	@FieldMap
    private Integer innerId;

	@JsonProperty("name")
	@FieldMap
    private String ftName;

	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean enable;

	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
    private String innerCreateTime;

	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
    private String innerUpdateTime;

	@JsonProperty("min_long_tolerance")
	@FieldMap
    private BigDecimal minLongTolerance;

	@JsonProperty("max_long_tolerance")
	@FieldMap
    private BigDecimal maxLongTolerance;

	@JsonProperty("min_wide_tolerance")
	@FieldMap
    private BigDecimal minWideTolerance;

	@JsonProperty("max_wide_tolerance")
	@FieldMap
    private BigDecimal maxWideTolerance;

	@JsonProperty("position")
	@FieldMap
	private Integer position;

	@JsonProperty("code")
	@FieldMap
    private String innerCode;

    public BigDecimal getMinLongTolerance() {
        return minLongTolerance;
    }

    public void setMinLongTolerance(BigDecimal minLongTolerance) {
        this.minLongTolerance = minLongTolerance;
    }

    public BigDecimal getMaxLongTolerance() {
        return maxLongTolerance;
    }

    public void setMaxLongTolerance(BigDecimal maxLongTolerance) {
        this.maxLongTolerance = maxLongTolerance;
    }

    public BigDecimal getMinWideTolerance() {
        return minWideTolerance;
    }

    public void setMinWideTolerance(BigDecimal minWideTolerance) {
        this.minWideTolerance = minWideTolerance;
    }

    public BigDecimal getMaxWideTolerance() {
        return maxWideTolerance;
    }

    public void setMaxWideTolerance(BigDecimal maxWideTolerance) {
        this.maxWideTolerance = maxWideTolerance;
    }

	public Integer getInnerId() {
		return innerId;
	}

	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}

	public String getFtName() {
		return ftName;
	}

	public void setFtName(String ftName) {
		this.ftName = ftName;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
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

	public String getInnerCode() {
		return innerCode;
	}

	public void setInnerCode(String innerCode) {
		this.innerCode = innerCode;
	}
}