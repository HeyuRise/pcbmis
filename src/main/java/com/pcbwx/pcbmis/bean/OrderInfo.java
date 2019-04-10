package com.pcbwx.pcbmis.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.component.ServiceRouter;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.utils.DateTimeUtil;

public class OrderInfo {
    private String orderNum;

    private String guestName;
    
    private String boardName;

    private String factoryName;

    private String categoryGrade;

    private BigDecimal boardLong;

    private BigDecimal boardWide;

    private String boardPly;

    private String metallographyRequire;
    
    private String impedanceRequire;

    private Integer productionNumSet;

    private Integer productionNumPcs;

    private String orderDate;

    private String surfaceProcessName;

    private String deliveryMode;

    private String joinBoardWayName;

    private String belongCompanyName;

    public OrderInfo() {
    	
    }
    public OrderInfo(ProductOrder order) {
    	final CacheService cacheService = ServiceRouter.getCacheService();
    	
    	this.orderNum = order.getOrderNum();
    	final EdaGuest guest = cacheService.getGuest(order.getGuestCode());
    	if (guest != null) {
    		this.guestName = guest.getShortNameCn();
    	}
    	this.boardName = order.getBoardName();
    	final FactoryInfo factory = cacheService.getFactory(order.getFactoryId());
    	if (factory != null) {
    		this.factoryName = factory.getFactoryName();    		
    	}
    	final Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, order.getCategoryGradeId());
    	if (dictGrade != null) {
    		this.categoryGrade = dictGrade.getValueStr();    		
    	}

    	this.boardLong = order.getBoardLong();
    	this.boardWide = order.getBoardWide();
    	this.boardPly = order.getBoardPly();
    	this.metallographyRequire = order.getMetallographyRequire();
    	this.impedanceRequire = order.getImpedanceRequire();
    	this.productionNumSet = order.getProductionNumSet();
    	this.productionNumPcs = order.getProductionNumPcs();
    	Date orderD = order.getProductDate();
    	if (orderD != null) {
    		this.orderDate = DateTimeUtil.date2dateTimeStr(orderD, "yyyy-MM-dd");
		}
    	this.deliveryMode = order.getDeliveryMode();

    	final SurfaceProcess surfaceProcess = cacheService.getSurfaceProcess(order.getSurfaceProcessId());
    	if (surfaceProcess != null) {
    		this.surfaceProcessName = surfaceProcess.getProcessName();    		
    	}
    	final JoinBoardWay joinBoardWay = cacheService.getJoinBoardWay(order.getJoinBoardWayId());
    	if (joinBoardWay != null) {
    		this.joinBoardWayName = joinBoardWay.getJoinName();    		
    	}
    	final CompanyInfo company = cacheService.getCompany(order.getBelongCompanyId());
    	if (company != null) {
    		this.belongCompanyName = company.getCompanyName();    		
    	}
    }
    
	public String getImpedanceRequire() {
		return impedanceRequire;
	}
	public void setImpedanceRequire(String impedanceRequire) {
		this.impedanceRequire = impedanceRequire;
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
	public String getMetallographyRequire() {
		return metallographyRequire;
	}
	public void setMetallographyRequire(String metallographyRequire) {
		this.metallographyRequire = metallographyRequire;
	}
	public Integer getProductionNumSet() {
		return productionNumSet;
	}
	public void setProductionNumSet(Integer productionNumSet) {
		this.productionNumSet = productionNumSet;
	}
	public Integer getProductionNumPcs() {
		return productionNumPcs;
	}
	public void setProductionNumPcs(Integer productionNumPcs) {
		this.productionNumPcs = productionNumPcs;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	public String getSurfaceProcessName() {
		return surfaceProcessName;
	}
	public void setSurfaceProcessName(String surfaceProcessName) {
		this.surfaceProcessName = surfaceProcessName;
	}
	public String getDeliveryMode() {
		return deliveryMode;
	}
	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}
	public String getJoinBoardWayName() {
		return joinBoardWayName;
	}
	public void setJoinBoardWayName(String joinBoardWayName) {
		this.joinBoardWayName = joinBoardWayName;
	}
	public String getBelongCompanyName() {
		return belongCompanyName;
	}
	public void setBelongCompanyName(String belongCompanyName) {
		this.belongCompanyName = belongCompanyName;
	}
	
}
