package com.pcbwx.pcbmis.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.pcbwx.pcbmis.model.SystemLog;

public class QuarterSendLogs {
	
	@Value("#{settings}")
	private Properties allProperties;
	
	@Autowired
//	private HttpConnectionManager cm;
	
	public void postSystemLog(List<SystemLog> logs) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("=====日志发送给用户行为记录系统开始=====" + new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("userName", "testName");
		map.put("sysType", allProperties.getProperty("sysType"));	// "TBOS1511170006"代表是绩效考核系统
		map.put("funcTime", sdf.format(new Date()));
		map.put("funcName", "functionName");
		map.put("funcType", "funcType");
		map.put("userBrowserVer", "userBrowserVer");
		map.put("userIP", "127.0.0.1");
		map.put("serverIP", "127.0.0.1");
		map.put("uawTime", String.valueOf(System.currentTimeMillis()));	// 时间戳
		// 测试参数
		map.put("type", "clientTest");
//		String result = LogHistory.upload(cm.getHttpClient(), map, allProperties.getProperty("ip"));
//		JSONObject resultJson = (JSONObject) JSONObject.parse(result);
//		if (resultJson == null || resultJson.isEmpty() || !resultJson.getBooleanValue("status") || resultJson.getIntValue("content") != 0) {
//			System.out.println("连接服务器失败！");
//			return;
//		}
//		for (SystemLog systemLog : logs) {
//			map.put("userName", systemLog.getCreatedBy() == null ? "" : systemLog.getCreatedBy().getUsername());
//			map.put("sysType", allProperties.getProperty("sysType"));	// "TBOS1511170006"代表是绩效考核系统
//			map.put("funcTime", systemLog.getCreatedAt() == null ? "" : sdf.format(systemLog.getCreatedAt()));
//			map.put("funcName", systemLog.getDescription());
//			map.put("funcType", systemLog.getMethodType() == null ? "" : systemLog.getMethodType().toString());
//			map.put("userBrowserVer", systemLog.getBrowser());
//			map.put("userIP", systemLog.getRequestIp());
//			map.put("serverIP", systemLog.getHostIp());
//			map.put("uawTime", String.valueOf(System.currentTimeMillis() + systemLog.getId()));	// 时间戳
//			// 传输参数
//			map.put("type", "client");
//			result = LogHistory.upload(cm.getHttpClient(), map, allProperties.getProperty("ip"));
//			resultJson = (JSONObject) JSONObject.parse(result);
//			if (resultJson.getBooleanValue("status")) {
//				systemLog.setCron(true);
//				systemLogDao.update(systemLog);
//			} else {
//				System.out.println(resultJson.getString("content") + "错误");
//			}
//		}
		System.out.println("=====日志发送给用户行为记录系统结束=====" + new Date());
	}
}
