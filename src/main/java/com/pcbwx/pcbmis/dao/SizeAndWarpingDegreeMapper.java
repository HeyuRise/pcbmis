package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.SizeAndWarpingDegree;

public interface SizeAndWarpingDegreeMapper extends BaseMapper<SizeAndWarpingDegree>{
    List<SizeAndWarpingDegree> selectByCheckNum(@Param("checkNum") String checkNum);
    Integer deleteByCheckNum(@Param("checkNum") String checkNum);
    
    /**
     * 获取不合格的数量
     * @return
     */
    Integer selectUnJudgeByCheckNum(@Param("checkNum") String checkNum);
}