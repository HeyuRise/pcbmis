package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.FrameTolerance;

public interface FrameToleranceMapper extends BaseMapper<FrameTolerance> {
	List<FrameTolerance> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from board_ply_tolerance")
	Date selectLastRecordTime();
	
	List<FrameTolerance> listByInnerIds(List<Integer> ids);
}