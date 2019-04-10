package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.SurfaceProcess;

public interface SurfaceProcessMapper extends BaseMapper<SurfaceProcess> {
	List<SurfaceProcess> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from surface_process")
	Date selectLastRecordTime();

	List<SurfaceProcess> listByInnerIds(List<Integer> ids);
}