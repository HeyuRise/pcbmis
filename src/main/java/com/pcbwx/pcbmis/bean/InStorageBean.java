package com.pcbwx.pcbmis.bean;

public class InStorageBean {
	private String inStorageNum;		// 出入库单号
	private String productOrderNum;		// 工单号
	private String guestName;			// 客户
	private String boardName;			// 板名
	private String boardNameNew;		// 新板名
	private String factoryName;			// 生产厂家
	private String grade;				// 等级
//	private BigDecimal boardlong;
//	private BigDecimal boardwide;
//	private String boardPly;
	private String boardSize;			// 规格
	private Integer productNumSet;		// 生产数量Set
	private Integer productNumPCS;		// 生产数量PCS
	private Integer inAmountPCS;		// 入库数量PCS
	private String content;				// 检验内容
	private String inStorageDate;		// 入库日期
	private String receiveDate;			// 接受日期
	private String receiveTime;			// 接受时间
	private String receiver;			// 接收人
	
	public String getInStorageNum() {
		return inStorageNum;
	}
	public void setInStorageNum(String inStorageNum) {
		this.inStorageNum = inStorageNum;
	}
	public String getProductOrderNum() {
		return productOrderNum;
	}
	public void setProductOrderNum(String productOrderNum) {
		this.productOrderNum = productOrderNum;
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
	public String getBoardNameNew() {
		return boardNameNew;
	}
	public void setBoardNameNew(String boardNameNew) {
		this.boardNameNew = boardNameNew;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
//	public BigDecimal getBoardlong() {
//		return boardlong;
//	}
//	public void setBoardlong(BigDecimal boardlong) {
//		this.boardlong = boardlong;
//	}
//	public BigDecimal getBoardwide() {
//		return boardwide;
//	}
//	public void setBoardwide(BigDecimal boardwide) {
//		this.boardwide = boardwide;
//	}
//	public String getBoardPly() {
//		return boardPly;
//	}
//	public void setBoardPly(String boardPly) {
//		this.boardPly = boardPly;
//	}
	public String getBoardSize() {
		return boardSize;
	}
	public void setBoardSize(String boardSize) {
		this.boardSize = boardSize;
	}
	public Integer getProductNumSet() {
		return productNumSet;
	}
	public void setProductNumSet(Integer productNumSet) {
		this.productNumSet = productNumSet;
	}
	public Integer getProductNumPCS() {
		return productNumPCS;
	}
	public void setProductNumPCS(Integer productNumPCS) {
		this.productNumPCS = productNumPCS;
	}
	public Integer getInAmountPCS() {
		return inAmountPCS;
	}
	public void setInAmountPCS(Integer inAmountPCS) {
		this.inAmountPCS = inAmountPCS;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getInStorageDate() {
		return inStorageDate;
	}
	public void setInStorageDate(String inStorageDate) {
		this.inStorageDate = inStorageDate;
	}
	public String getReceiveDate() {
		return receiveDate;
	}
	public void setReceiveDate(String receiveDate) {
		this.receiveDate = receiveDate;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
}
