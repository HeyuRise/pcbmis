package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbCheckReport;

public interface PcbCheckReportMapper extends BaseMapper<PcbCheckReport>{
    
    /*
     * 获取报告单列表总数
     */
    Integer selectByParamNum(@Param("orderNum") String orderNum,
    		@Param("boardName") String boardName,
    		@Param("gradeId") Integer gradeId,
    		@Param("factoryId") Integer factoryId,
    		@Param("statusId") Integer statusId,
    		@Param("makeTimeStart") Date makeTimeStart,
    		@Param("makeTimeEnd") Date makeTimeEnd,
    		@Param("auditTimeStart") Date auditTimeStart,
    		@Param("auditTimeEnd") Date auditTimeEnd,
    		@Param("guestCodes") List<String> guestCode);
    /*
     *获取报告单列表 
     */
    List<PcbCheckReport> selectByParam(@Param("orderNum") String orderNum,
    		@Param("boardName") String boardName,
    		@Param("gradeId") Integer gradeId,
    		@Param("factoryId") Integer factoryId,
    		@Param("statusId") Integer statusId,
    		@Param("makeTimeStart") Date makeTimeStart,
    		@Param("makeTimeEnd") Date makeTimeEnd,
    		@Param("auditTimeStart") Date auditTimeStart,
    		@Param("auditTimeEnd") Date auditTimeEnd,
    		@Param("guestCodes") List<String> guestCode,
			@Param("start") Integer start, 
			@Param("pageSize") Integer pageSize);
    
    PcbCheckReport selectByReportNum(@Param("reportNum") String reportNum);
    
    Integer getOperatOrAuditReportNum(@Param("toBe") Integer toBe,
										@Param("doing") Integer doing,
										@Param("orderNum") String orderNum,
										@Param("reportMaker") String reportMaker,
										@Param("auditor") String auditor);
    
    List<PcbCheckReport> getOperatOrAuditReport(@Param("toBe") Integer toBe,
    										@Param("doing") Integer doing,
    										@Param("orderNum") String orderNum,
    										@Param("reportMaker") String reportMaker,
    										@Param("auditor") String auditor,
    										@Param("start") Integer start, 
    										@Param("pageSize") Integer pageSize);
    
    List<PcbCheckReport> listByOrderNum(@Param("orderNum") String orderNum);
    List<PcbCheckReport> listByJoinOrderNum(@Param("joinBoardOrderCode") String joinBoardOrderCode);
    
    List<PcbCheckReport> selectByCheckNum(@Param("checkNum") String checkNum);
    
    /**
     * 按检验单号集合查找
     * @param checkNum
     * @return
     */
    List<PcbCheckReport> listByCheckNum(@Param("checkNums") Set<String> checkNum);
    
    /**
     * 按出入库单号查找
     * @param orderNumber
     * @return
     */
    List<PcbCheckReport> selectByOrderNumber(@Param("orderNumber") String orderNumber);
    
    Integer updatePcbBoardName(@Param("productionNumPcs") Integer productionNumPcs,
    		@Param("productOrderNum") String productOrderNum, 
    		@Param("boardName") String boardName);
	Integer updateJoinBoardName(@Param("joinBoardOrderCode") String joinBoardOrderCode, 
			@Param("boardName") String boardName);
}