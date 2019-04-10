package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.PcbReportApertureCheck;

public interface PcbReportApertureCheckMapper extends BaseMapper<PcbReportApertureCheck>{
    List<PcbReportApertureCheck> selectByReportNum(@Param("reportNum") String reportNum);
    Integer deleteByReportNum(@Param("reportNum") String reportNum);
}