/**
 * 
 */
package com.pcbwx.pcbmis.common;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于Date字符串反序列化到Date对象
 * 
 * @author 王海龙
 *
 */
public class JsonDateDeserialize extends JsonDeserializer<Date> {
    private static Logger logger = LoggerFactory.getLogger(JsonDateDeserialize.class);

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		Date date = null;
		try {
			date = sdf.parse(jp.getText());
		} catch (ParseException e) {
			logger.error(e.getMessage(), e);
		}
		return date;
	}
}
