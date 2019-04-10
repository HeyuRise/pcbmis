package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.BoardPlyTolerance;

public interface BoardPlyToleranceMapper extends BaseMapper<BoardPlyTolerance> {
	List<BoardPlyTolerance> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from board_ply_tolerance")
	Date selectLastRecordTime();
	
	List<BoardPlyTolerance> listByInnerIds(List<Integer> ids);
}