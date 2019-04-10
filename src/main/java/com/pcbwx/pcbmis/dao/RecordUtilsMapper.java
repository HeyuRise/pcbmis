package com.pcbwx.pcbmis.dao;

import com.pcbwx.pcbmis.model.RecordUtils;

public interface RecordUtilsMapper extends BaseMapper<RecordUtils> {
	int updateByName(RecordUtils record);
	
	RecordUtils selectByName(String recordName);
}