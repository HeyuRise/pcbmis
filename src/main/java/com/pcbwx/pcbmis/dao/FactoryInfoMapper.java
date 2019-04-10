package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.FactoryInfo;

public interface FactoryInfoMapper extends BaseMapper<FactoryInfo> {
	List<FactoryInfo> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from factory_info")
	Date selectLastRecordTime();
	
	List<FactoryInfo> selectByFactory_name(@Param("factoryName") String factoryName);
	
	List<FactoryInfo> listByInnerIds(List<Integer> ids);
}