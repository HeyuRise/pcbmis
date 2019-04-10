package com.pcbwx.pcbmis.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.pcbmis.common.ConfigProperties;
import com.pcbwx.pcbmis.dao.ActionLogMapper;
import com.pcbwx.pcbmis.dao.LogMapper;
import com.pcbwx.pcbmis.model.ActionLog;
import com.pcbwx.pcbmis.model.Log;
import com.pcbwx.pcbmis.service.LogService;

/**
 * 日志接口实现类
 * 
 * @author 王海龙
 *
 */
@Service("logService")
public class LogServiceImpl implements LogService {

	private static Logger logger = Logger.getLogger(LogServiceImpl.class);

	@Autowired
	private LogMapper logMapper;
	@Autowired
	private ActionLogMapper actionLogMapper;

	@Override
	public int addAction(String code, String action, String user, String value) {
		try {
			ActionLog record = new ActionLog();
			record.setServiceCode(ConfigProperties.getMySystemCode());
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value, String param1) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return 0;
	}

	@Override
	public int addAction(String code, String action, String user, String value, String param1, String param2) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setParam2(param2);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return 0;
	}
	@Override
	public int addAction(String code, String action, String user, String value, String param1, String param2, String param3) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			record.setParam1(param1);
			record.setParam2(param2);
			record.setParam3(param3);
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
		}		
		return 0;
	}
	
	@Override
	public int addAction(String code, String action, String user, String value, List<String> params) {
		try {
			ActionLog record = new ActionLog();
			record.setCode(code);
			record.setAction(action);
			record.setUser(user);
			record.setValue(value);
			for (int i = 0; i < params.size(); i++) {
				if (i == 0) {
					record.setParam1(params.get(i));
				} if (i == 1) {
					record.setParam2(params.get(i));
				} if (i == 2) {
					record.setParam3(params.get(i));
				} if (i == 3) {
					record.setParam4(params.get(i));
				} else {
					break;
				}
			}
			record.setRecordTime(new Date());
			return actionLogMapper.insertSelective(record);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
		}		
		return 0;
	}

	@Override
	public int addLog(Log log) {
		try {
			return logMapper.insertSelective(log);
		} catch (Exception e) {
			// TODO: handle exception
//			logger.error(e.toString());
		}
		return 0;
	}
	
}
