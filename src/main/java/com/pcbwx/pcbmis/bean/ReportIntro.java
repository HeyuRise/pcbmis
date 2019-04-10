package com.pcbwx.pcbmis.bean;

import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.component.ServiceRouter;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.PcbReportIntro;
import com.pcbwx.pcbmis.utils.DateTimeUtil;

public class ReportIntro {
    private String guestName;
    private String boardName;
    private String number;
    private String unit;
    private String productPeriod;
    private String acceptanceStandard;
    private String dispatchDate;
    private String serialNumber;
    private String audit;
    private String newDateKey;
    private String newDateValue;
    private Integer templateId;
    public ReportIntro(){
    	
    }
    public ReportIntro(PcbReportIntro pcbReportIntro, String period){
    	final CacheService cacheService = ServiceRouter.getCacheService();
    	final EdaGuest guest = cacheService.getGuest(pcbReportIntro.getGuestCode());
    	if (guest != null) {
    		this.guestName = guest.getShortNameCn();
    	}
    	boardName = pcbReportIntro.getBoardName();
    	number = pcbReportIntro.getNumber();
    	unit = pcbReportIntro.getUnit();
    	if (pcbReportIntro.getProductPeriod() == null) {
    		productPeriod = period;
		}else {
			productPeriod = pcbReportIntro.getProductPeriod();
		}
    	acceptanceStandard = pcbReportIntro.getAcceptanceStandard();
    	dispatchDate = DateTimeUtil.date2dateStr(pcbReportIntro.getDispatchDate());
    	serialNumber = pcbReportIntro.getSerialNumber();
    	audit = pcbReportIntro.getAudit();
    	newDateKey = pcbReportIntro.getNewDateKey();
    	newDateValue = pcbReportIntro.getNewDateValue();
    }
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getProductPeriod() {
		return productPeriod;
	}
	public void setProductPeriod(String productPeriod) {
		this.productPeriod = productPeriod;
	}
	public String getAcceptanceStandard() {
		return acceptanceStandard;
	}
	public void setAcceptanceStandard(String acceptanceStandard) {
		this.acceptanceStandard = acceptanceStandard;
	}
	public String getDispatchDate() {
		return dispatchDate;
	}
	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}
	public String getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}
	public String getAudit() {
		return audit;
	}
	public void setAudit(String audit) {
		this.audit = audit;
	}
	public String getNewDateKey() {
		return newDateKey;
	}
	public void setNewDateKey(String newDateKey) {
		this.newDateKey = newDateKey;
	}
	public String getNewDateValue() {
		return newDateValue;
	}
	public void setNewDateValue(String newDateValue) {
		this.newDateValue = newDateValue;
	}
	public Integer getTemplateId() {
		return templateId;
	}
	public void setTemplateId(Integer templateId) {
		this.templateId = templateId;
	}
}
