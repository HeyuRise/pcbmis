package com.pcbwx.pcbmis.map;

import com.pcbwx.pcbmis.utils.DateCalcUtil;

public class HourFloat2MinuteInteger extends ChannelDeserialize<Integer> {

	@Override
	public Integer deserialize(Object src) throws Exception {
		if (src == null) {
			return null;
		}
		Float value = (Float) src;
		return DateCalcUtil.hourFloat2MinuteInteger(value);
	}
}
