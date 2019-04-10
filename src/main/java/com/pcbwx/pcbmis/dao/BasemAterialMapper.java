package com.pcbwx.pcbmis.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.pcbwx.pcbmis.model.BasemAterial;

public interface BasemAterialMapper extends BaseMapper<BasemAterial> {
	List<BasemAterial> load();
	@Select("select GREATEST(COALESCE(max(create_time)),COALESCE(max(update_time),0)) from basem_aterial")
	Date selectLastRecordTime();

	List<BasemAterial> listByInnerIds(List<Integer> ids);
}