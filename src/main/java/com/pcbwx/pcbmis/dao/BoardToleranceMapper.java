package com.pcbwx.pcbmis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.BoardTolerance;

public interface BoardToleranceMapper extends BaseMapper<BoardTolerance>{
    List<BoardTolerance> selectByCheckNum(@Param("checkNum") String checkNum);
    Integer deleteByCheckNum(@Param("checkNum") String checkNum);
}