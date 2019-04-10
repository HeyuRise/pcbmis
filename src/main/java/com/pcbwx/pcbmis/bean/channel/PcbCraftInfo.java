package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.Boolean2Integer;
import com.pcbwx.pcbmis.map.String2DateTime;

public class PcbCraftInfo {
	@JsonProperty("id")
	@FieldMap
    private Integer innerId;

	@JsonProperty("name")
	@FieldMap
    private String craftName;

	@JsonProperty("enable")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean enable;

	@JsonProperty("exsit_unequal_thickness")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean exsitUnequalThickness;

	@JsonProperty("position")
	@FieldMap
    private Integer position;

	@JsonProperty("is_special_order")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean isSpecialOrder;

	@JsonProperty("is_soft_hard")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean isSoftHard;

	@JsonProperty("is_maimang")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean isMaimang;

	@JsonProperty("is_hdi")
	@FieldMap(using = Boolean2Integer.class)
    private Boolean isHdi;

	@JsonProperty("created_at")
	@FieldMap(using = String2DateTime.class)
    private String innerCreateTime;

	@JsonProperty("updated_at")
	@FieldMap(using = String2DateTime.class)
    private String innerUpdateTime;

    public Integer getInnerId() {
        return innerId;
    }

    public void setInnerId(Integer innerId) {
        this.innerId = innerId;
    }

    public String getCraftName() {
        return craftName;
    }

    public void setCraftName(String craftName) {
        this.craftName = craftName == null ? null : craftName.trim();
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

	public Boolean getExsitUnequalThickness() {
		return exsitUnequalThickness;
	}

	public void setExsitUnequalThickness(Boolean exsitUnequalThickness) {
		this.exsitUnequalThickness = exsitUnequalThickness;
	}

	public Boolean getIsSpecialOrder() {
		return isSpecialOrder;
	}

	public void setIsSpecialOrder(Boolean isSpecialOrder) {
		this.isSpecialOrder = isSpecialOrder;
	}

	public Boolean getIsSoftHard() {
		return isSoftHard;
	}

	public void setIsSoftHard(Boolean isSoftHard) {
		this.isSoftHard = isSoftHard;
	}

	public Boolean getIsMaimang() {
		return isMaimang;
	}

	public void setIsMaimang(Boolean isMaimang) {
		this.isMaimang = isMaimang;
	}

	public Boolean getIsHdi() {
		return isHdi;
	}

	public void setIsHdi(Boolean isHdi) {
		this.isHdi = isHdi;
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