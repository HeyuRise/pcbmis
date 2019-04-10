package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.pcbwx.pcbmis.model.SystemLog;

public interface SystemLogMapper extends BaseMapper<SystemLog>{
	List<SystemLog> selectByCreatTime(@Param("lastRecordTime") Date lastRecordTime);
	List<SystemLog> selectByIsrecord();
}