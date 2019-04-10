package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.EdaGuest;

public interface EdaGuestMapper extends BaseMapper<EdaGuest> {
	List<EdaGuest> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from eda_guest")
	Date selectLastRecordTime();
	List<EdaGuest> selectByShortName(@Param("shortName") String shortName);

	List<EdaGuest> listByInnerIds(List<Integer> ids);
	List<EdaGuest> listByCodes(List<String> codes);
}