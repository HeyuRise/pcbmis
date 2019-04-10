package com.pcbwx.pcbmis.map;

import com.pcbwx.pcbmis.utils.DateCalcUtil;

public class MinuteInteger2HourFloat extends ChannelDeserialize<Float> {

	@Override
	public Float deserialize(Object src) throws Exception {
		if (src == null) {
			return null;
		}
		Integer value = (Integer) src;
		return DateCalcUtil.minuteInteger2HourFloat(value);
	}
}
