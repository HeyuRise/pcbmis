package com.pcbwx.pcbmis.bean;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.component.ServiceRouter;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.EdaGuest;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.JoinBoardWay;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.SurfaceProcess;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.utils.DateTimeUtil;

public class CheckInfo {
	private String id;
	private String checkNum;
	private String orderNum;
	private String guestName;
	private String boardName;
	private String factoryName;
	private String categoryGrade;
	private BigDecimal boardLong;
    private BigDecimal boardWide;
    private String boardPly;
    private String metallographyRequire;
    private Integer productionNumSet;
    private Integer productionNumPcs;
    private Integer amountCheckoutPcs;
    private Integer spotCheckNumPcs;
    private String sendDate;
    private String receviceDate;
    private String receviceTime;
    private String status;
    private String inspector;
    private String checkDate;
    private String auditor;
    private String auditDate;
    private String inStorageDate;					// 入库时间
    private String checkContent;					// 检验内容
    private Integer checkable;						// 是否可点击
    private String href;							// 跳转页面
    // 以下字段为excel导出部分字段
    private String isError;			// 是否异常
    private Integer errorNum;		// 异常数量
    private String errorQuestion;	// 异常问题
    private String serialNumber;	// 流水号
    private String companyName;		// 所属公司
    private String productDate;		// 投产日期
    private String surfaceProcess;	// 表面处理
    private String joinBoardWay;	// 拼版方式
    private String deliveryMode;	// 出货方式
    private String reportMakerName;	// 编辑报告人员
    private String dispatchDate;	// 出货日期
    private String productNote;     // 备注
    private String receiveType;		// 来料类型
    private String mistakeNote;     // 错检漏检
    private String reportError;     // 报告错误
	
	public void init(ProductOrder order, PcbCheckOrder pcbCheckOrder) {
		final CacheService cacheService = ServiceRouter.getCacheService();
		this.id = pcbCheckOrder.getProductOrderNum();
		this.checkNum = pcbCheckOrder.getCheckNum();
		this.orderNum = pcbCheckOrder.getProductOrderNum();
		if (order != null) {
			final EdaGuest guest = cacheService.getGuest(order.getGuestCode());
	    	if (guest != null) {
	    		this.guestName = guest.getShortNameCn();
	    	}
	    	this.boardName = pcbCheckOrder.getBoardName();
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
			this.productionNumSet = order.getProductionNumSet();
			this.productionNumPcs = order.getProductionNumPcs();
			
			// 以下部分代码为导出检验单列表设计
			
			// 所属公司
			Integer companyId = order.getBelongCompanyId();
			if (companyId != null) {
				CompanyInfo companyInfo = cacheService.getCompany(companyId);
				if (companyInfo != null) {
					this.companyName = companyInfo.getCompanyName();
				}
			}
			
			// 投产日期
			Date productDate2 = order.getProductDate();
			if (productDate2 != null) {
				this.productDate = DateTimeUtil.date2dateTimeStr(productDate2, "yyyy-MM-dd");
			}
			
			// 表面处理
			Integer surfaceProcessId = order.getSurfaceProcessId();
			if (surfaceProcessId != null) {
				SurfaceProcess process = cacheService.getSurfaceProcess(surfaceProcessId);
				if (process != null) {
					this.surfaceProcess = process.getProcessName();
				}
			}
			
			// 拼版方式
			Integer joinBoardWayId = order.getJoinBoardWayId();
			if (joinBoardWayId != null) {
				JoinBoardWay joinBoardWay2 = cacheService.getJoinBoardWay(joinBoardWayId);
				if (joinBoardWay2 != null) {
					this.joinBoardWay = joinBoardWay2.getJoinName();
				}
			}
			
			// 出货方式
			this.deliveryMode = order.getDeliveryMode();
			String content = null;
			// 检验内容
			try {
				String contentIds = pcbCheckOrder.getContentId();
				List<Integer> contentIdList = null;
				Gson g = new Gson();
				Type type = (Type) new TypeToken<List<Integer>>() {}.getType();
				contentIdList = g.fromJson(contentIds, type);
				Dictionary dictionary = null;
				for (Integer integer : contentIdList) {
					dictionary = cacheService.getDictionary(DictionaryEnum.CHECK_CONTENT, integer);
					if (content == null) {
						content = dictionary.getValueStr();
					}else {
						content = content + "," + dictionary.getValueStr();
					}
				}
			} catch (Exception e) {
				
			}
			this.checkContent = content;
		}
		if (Objects.equals(1, pcbCheckOrder.getBadRecord())) {
			this.isError = "是";
		}else {
			this.isError = "否";
		}
		
		this.amountCheckoutPcs = pcbCheckOrder.getInAmountPcs();
		this.spotCheckNumPcs = pcbCheckOrder.getSpotCheckNumPcs();

		Date recDate = pcbCheckOrder.getReceiveDate();
		if (recDate != null) {
			this.receviceDate = DateTimeUtil.date2dateTimeStr(recDate, "yyyy-MM-dd");
			this.receviceTime = DateTimeUtil.date2dateTimeStr(recDate, "HH:mm:ss");
		}
		
		Date inStorageDate = pcbCheckOrder.getInStorageDate();
		this.inStorageDate = DateTimeUtil.date2dateTimeStr(inStorageDate, "yyyy-MM-dd");
		this.status = pcbCheckOrder.getCheckState();
		Date chcDate = pcbCheckOrder.getCheckDate();
		if (chcDate != null) {
			this.checkDate = DateTimeUtil.date2dateTimeStr(chcDate, "yyyy-MM-dd");
		}
		if (pcbCheckOrder.getInspector() != null) {
			WxtbUser wxtbUser = cacheService.getWxtbUser(pcbCheckOrder.getInspector());
			if (wxtbUser != null) {
				this.inspector = wxtbUser.getUsername();
			}
		}
		String auditCode = pcbCheckOrder.getAuditor();
		if (auditCode != null) {
			WxtbUser wxtbUser = cacheService.getWxtbUser(auditCode);
			if (wxtbUser != null) {
				this.auditor = wxtbUser.getUsername();
			}
		}
		Date audDate = pcbCheckOrder.getAuditDate();
		if (audDate != null) {
			this.auditDate = DateTimeUtil.date2dateTimeStr(audDate, "yyyy-MM-dd");
		}
		// 下面代码为导出excel所用
		this.serialNumber = pcbCheckOrder.getSerialNumber();	// 流水号
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCheckNum() {
		return checkNum;
	}

	public void setCheckNum(String checkNum) {
		this.checkNum = checkNum;
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

	public Integer getAmountCheckoutPcs() {
		return amountCheckoutPcs;
	}

	public void setAmountCheckoutPcs(Integer amountCheckoutPcs) {
		this.amountCheckoutPcs = amountCheckoutPcs;
	}

	public Integer getSpotCheckNumPcs() {
		return spotCheckNumPcs;
	}

	public void setSpotCheckNumPcs(Integer spotCheckNumPcs) {
		this.spotCheckNumPcs = spotCheckNumPcs;
	}

	public String getSendDate() {
		return sendDate;
	}

	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}

	public String getReceviceDate() {
		return receviceDate;
	}

	public void setReceviceDate(String receviceDate) {
		this.receviceDate = receviceDate;
	}

	public String getReceviceTime() {
		return receviceTime;
	}

	public void setReceviceTime(String receviceTime) {
		this.receviceTime = receviceTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInspector() {
		return inspector;
	}

	public void setInspector(String inspector) {
		this.inspector = inspector;
	}

	public String getCheckDate() {
		return checkDate;
	}

	public void setCheckDate(String checkDate) {
		this.checkDate = checkDate;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public String getAuditDate() {
		return auditDate;
	}

	public void setAuditDate(String auditDate) {
		this.auditDate = auditDate;
	}

	public String getInStorageDate() {
		return inStorageDate;
	}

	public void setInStorageDate(String inStorageDate) {
		this.inStorageDate = inStorageDate;
	}

	public String getCheckContent() {
		return checkContent;
	}

	public void setCheckContent(String checkContent) {
		this.checkContent = checkContent;
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

	public String getIsError() {
		return isError;
	}

	public void setIsError(String isError) {
		this.isError = isError;
	}

	public Integer getErrorNum() {
		return errorNum;
	}

	public void setErrorNum(Integer errorNum) {
		this.errorNum = errorNum;
	}

	public String getErrorQuestion() {
		return errorQuestion;
	}

	public void setErrorQuestion(String errorQuestion) {
		this.errorQuestion = errorQuestion;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProductDate() {
		return productDate;
	}

	public void setProductDate(String productDate) {
		this.productDate = productDate;
	}

	public String getSurfaceProcess() {
		return surfaceProcess;
	}

	public void setSurfaceProcess(String surfaceProcess) {
		this.surfaceProcess = surfaceProcess;
	}

	public String getJoinBoardWay() {
		return joinBoardWay;
	}

	public void setJoinBoardWay(String joinBoardWay) {
		this.joinBoardWay = joinBoardWay;
	}

	public String getDeliveryMode() {
		return deliveryMode;
	}

	public void setDeliveryMode(String deliveryMode) {
		this.deliveryMode = deliveryMode;
	}

	public String getReportMakerName() {
		return reportMakerName;
	}

	public void setReportMakerName(String reportMakerName) {
		this.reportMakerName = reportMakerName;
	}

	public String getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(String dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public String getProductNote() {
		return productNote;
	}

	public void setProductNote(String productNote) {
		this.productNote = productNote;
	}

	public String getReceiveType() {
		return receiveType;
	}

	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	public String getMistakeNote() {
		return mistakeNote;
	}

	public void setMistakeNote(String mistakeNote) {
		this.mistakeNote = mistakeNote;
	}

	public String getReportError() {
		return reportError;
	}

	public void setReportError(String reportError) {
		this.reportError = reportError;
	}

	@Override
	public String toString() {
		return "CheckInfo [id=" + id + ", checkNum=" + checkNum + ", orderNum=" + orderNum + ", guestName=" + guestName
				+ ", boardName=" + boardName + ", factoryName=" + factoryName + ", categoryGrade=" + categoryGrade
				+ ", boardLong=" + boardLong + ", boardWide=" + boardWide + ", boardPly=" + boardPly
				+ ", metallographyRequire=" + metallographyRequire + ", productionNumSet=" + productionNumSet
				+ ", productionNumPcs=" + productionNumPcs + ", amountCheckoutPcs=" + amountCheckoutPcs
				+ ", spotCheckNumPcs=" + spotCheckNumPcs + ", sendDate=" + sendDate + ", receviceDate=" + receviceDate
				+ ", receviceTime=" + receviceTime + ", status=" + status + ", inspector=" + inspector + ", checkDate="
				+ checkDate + ", auditor=" + auditor + ", auditDate=" + auditDate + ", inStorageDate=" + inStorageDate
				+ ", checkContent=" + checkContent + ", checkable=" + checkable + ", href=" + href + ", isError="
				+ isError + ", errorNum=" + errorNum + ", errorQuestion=" + errorQuestion + ", serialNumber="
				+ serialNumber + ", companyName=" + companyName + ", productDate=" + productDate + ", surfaceProcess="
				+ surfaceProcess + ", joinBoardWay=" + joinBoardWay + ", deliveryMode=" + deliveryMode
				+ ", reportMakerName=" + reportMakerName + ", dispatchDate=" + dispatchDate + ", productNote="
				+ productNote + ", receiveType=" + receiveType + ", mistakeNote=" + mistakeNote + ", reportError="
				+ reportError + "]";
	}
	
}
