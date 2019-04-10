package com.pcbwx.pcbmis.bean;

import java.math.BigDecimal;

import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.component.ServiceRouter;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.PcbCheckReport;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.utils.DateTimeUtil;

public class CheckReport {
	private String reportNum;
    private String orderNum;
    private String guestName;
    private String boardName;
    private String factoryName;
    private String categoryGrade;
    private BigDecimal boardLong;
    private BigDecimal boardWide;
    private String boardPly;
    private Integer productionNumPcs;
    private String reportMakerName;
    private String makeTime;
    private String reportAuditorName;
    private String auditDate;
    private String status;
    private Integer checkable;			// 是否可点击
    private String href;				// 跳转界面
    
    public CheckReport() {
    	
    }
	public CheckReport(PcbCheckReport pcbCheckReport) {
		final CacheService cacheService = ServiceRouter.getCacheService();
		if (pcbCheckReport != null) {
			this.reportNum = pcbCheckReport.getReportNum();
			final EdaGuest guest = cacheService.getGuest(pcbCheckReport.getGuestCode());
	    	if (guest != null) {
	    		this.guestName = guest.getShortNameCn();
	    	}
	    	this.orderNum = pcbCheckReport.getOrderNum();
	    	this.boardName = pcbCheckReport.getBoardName();
	    	final FactoryInfo factory = cacheService.getFactory(pcbCheckReport.getFactoryId());
	    	if (factory != null) {
	    		this.factoryName = factory.getFactoryName();    		
	    	}
	    	final Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, pcbCheckReport.getCategoryGradeId());
	    	if (dictGrade != null) {
	    		this.categoryGrade = dictGrade.getValueStr();    		
	    	}
	    	this.boardLong = pcbCheckReport.getBoardLong();
	    	this.boardWide = pcbCheckReport.getBoardWide();
	    	this.boardPly = pcbCheckReport.getBoardPly();
	    	this.productionNumPcs = pcbCheckReport.getProductionNumPcs();
	    	String reportMakerCode = pcbCheckReport.getReportMakerCode();
	    	if (reportMakerCode != null) {
	    		WxtbUser wxtbUser = cacheService.getWxtbUser(reportMakerCode);
	    		if (wxtbUser != null) {
		    		this.reportMakerName = wxtbUser.getUsername();
				}
			}
	    	String reportAutditorCode = pcbCheckReport.getReportAuditorCode();
	    	if (reportAutditorCode != null) {
				WxtbUser wxtbUser = cacheService.getWxtbUser(reportAutditorCode);
				if (wxtbUser != null) {
					this.reportAuditorName = wxtbUser.getUsername();
				}
			}
	    	this.makeTime = DateTimeUtil.date2dateStr(pcbCheckReport.getMakeTime());
	    	this.auditDate = DateTimeUtil.date2dateStr(pcbCheckReport.getAuditDate());
	    	this.status = pcbCheckReport.getStatus();
		}
	}

	public String getReportNum() {
		return reportNum;
	}
	public void setReportNum(String reportNum) {
		this.reportNum = reportNum;
	}
	public String getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
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
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getCategoryGrade() {
		return categoryGrade;
	}
	public void setCategoryGrade(String categoryGrade) {
		this.categoryGrade = categoryGrade;
	}
	public BigDecimal getBoardLong() {
		return boardLong;
	}
	public void setBoardLong(BigDecimal boardLong) {
		this.boardLong = boardLong;
	}
	public BigDecimal getBoardWide() {
		return boardWide;
	}
	public void setBoardWide(BigDecimal boardWide) {
		this.boardWide = boardWide;
	}
	public String getBoardPly() {
		return boardPly;
	}
	public void setBoardPly(String boardPly) {
		this.boardPly = boardPly;
	}
	public Integer getProductionNumPcs() {
		return productionNumPcs;
	}
	public void setProductionNumPcs(Integer productionNumPcs) {
		this.productionNumPcs = productionNumPcs;
	}
	public String getReportMakerName() {
		return reportMakerName;
	}
	public void setReportMakerName(String reportMakerName) {
		this.reportMakerName = reportMakerName;
	}
	public String getMakeTime() {
		return makeTime;
	}
	public void setMakeTime(String makeTime) {
		this.makeTime = makeTime;
	}
	public String getReportAuditorName() {
		return reportAuditorName;
	}
	public void setReportAuditorName(String reportAuditorName) {
		this.reportAuditorName = reportAuditorName;
	}
	public String getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}
//	public String getIsReport() {
//		return isReport;
//	}
//	public void setIsReport(String isReport) {
//		this.isReport = isReport;
//	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Integer getCheckable() {
		return checkable;
	}
	public void setCheckable(Integer checkable) {
		this.checkable = checkable;
	}
	public String getHref() {
		return href;
	}
	public void setHref(String href) {
		this.href = href;
	}

}
