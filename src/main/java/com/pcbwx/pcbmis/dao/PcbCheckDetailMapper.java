package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbCheckDetail;

public interface PcbCheckDetailMapper extends BaseMapper<PcbCheckDetail>{
	
	/**
	 * 获取检验详情
	 * @param checkNum   检验单号
	 * @param optionName 检验项名字
	 * @return
	 */
    List<PcbCheckDetail> selectByCheckNumAndOptionName(@Param("checkNum") String checkNum, @Param("optionName") String optionName);
    
    /**
     * 删除检验详情
     * @param checkNum  检验单号
     * @return
     */
    Integer deleteByCheckNum(@Param("checkNum") String checkNum);
    
    /**
     * 获取检验单不良数量个数
     * @param checkNum  检验单号
     * @return
     */
    Integer selectSumBadNumByChecknum(@Param("checkNum") String checkNum);
}