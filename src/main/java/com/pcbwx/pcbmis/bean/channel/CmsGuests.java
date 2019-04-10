package com.pcbwx.pcbmis.bean.channel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pcbwx.pcbmis.annotation.FieldMap;
import com.pcbwx.pcbmis.map.String2Date;

public class CmsGuests {
    @JsonProperty("id")
	@FieldMap
    private Integer innerId;

    @JsonProperty("name")
	@FieldMap
    private String guestName;

    @JsonProperty("abbreviation_cn")
	@FieldMap
    private String shortNameCn;

    @JsonProperty("abbreviation_e")
	@FieldMap
    private String shortNameEn;

    @JsonProperty("area")
	@FieldMap
    private String place;

//    @JsonProperty("销售人员工号")
//	@FieldMap
//    private String sellerCode;
//    private Integer sellerId;

//    @JsonProperty("客服人员工号")
//	@FieldMap
//    private String csCode;
//    private Integer csId;

    @JsonProperty("sell_e_mail")
	@FieldMap
    private String sellerEmail;

//    @JsonProperty("销售部门编号")
//	@FieldMap
//    private String sellDepart;
//    private Integer departmentId;

//    private Integer groupId;

//    private Integer createdbyId;

//    private Integer updatedbyId;

    @JsonProperty("code")
	@FieldMap
    private String guestCode;

    @JsonProperty("created_at")
	@FieldMap(using = String2Date.class)
    private String innerCreateTime;

    @JsonProperty("updated_at")
	@FieldMap(using = String2Date.class)
    private String innerUpdateTime;

//    private String gcode;

//	@Title("客户性质")
//	@FieldMap
//	private String guestType;
//    private Integer propertyId;

//    private Boolean iseligible;

    @JsonProperty("cooperate_id")
	@FieldMap
    private Integer guestState;		// 客户合作情况

    @JsonProperty("company_code")
	@FieldMap
    private String sellCompany;		// 销售公司id

//    private Boolean information;

//    private Integer ifneedId;

//    private Integer certifyId;

//    private Integer investigateTimeId;

//    private Date importDate;

//    private Integer parentId;		// 上级客户id

//    private Integer billingCycle; 	// 开票周期

//    private Integer paymentCycle;		// 回款周期

//    private Boolean potentialCustomer; 	// 是否潜在客户

//    private Boolean noncooperateCustomer; 	// 是不该合作用户

	public Integer getInnerId() {
		return innerId;
	}

	public void setInnerId(Integer innerId) {
		this.innerId = innerId;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public String getShortNameCn() {
		return shortNameCn;
	}

	public void setShortNameCn(String shortNameCn) {
		this.shortNameCn = shortNameCn;
	}

	public String getShortNameEn() {
		return shortNameEn;
	}

	public void setShortNameEn(String shortNameEn) {
		this.shortNameEn = shortNameEn;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getSellerEmail() {
		return sellerEmail;
	}

	public void setSellerEmail(String sellerEmail) {
		this.sellerEmail = sellerEmail;
	}

	public String getGuestCode() {
		return guestCode;
	}

	public void setGuestCode(String guestCode) {
		this.guestCode = guestCode;
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

	public Integer getGuestState() {
		return guestState;
	}

	public void setGuestState(Integer guestState) {
		this.guestState = guestState;
	}

	public String getSellCompany() {
		return sellCompany;
	}

	public void setSellCompany(String sellCompany) {
		this.sellCompany = sellCompany;
	}
}