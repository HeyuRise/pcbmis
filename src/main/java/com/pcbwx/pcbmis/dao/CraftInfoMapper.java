package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.CraftInfo;

public interface CraftInfoMapper extends BaseMapper<CraftInfo> {
	List<CraftInfo> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from factory_info")
	Date selectLastRecordTime();

	List<CraftInfo> listByInnerIds(List<Integer> ids);
}