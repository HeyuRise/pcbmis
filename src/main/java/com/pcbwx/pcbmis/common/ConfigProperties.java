package com.pcbwx.pcbmis.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import com.pcbwx.pcbmis.common.exception.BusinessException;
import com.pcbwx.pcbmis.common.exception.ExceptionType;
import com.pcbwx.pcbmis.enums.ConfigEnum;

/**
 * 初始化静态变量
 * @author 王海龙
 *
 */
public class ConfigProperties {
	private static Logger logger = Logger.getLogger(ConfigProperties.class);
	
	private final static String mySystemCode = "pcbmis";
	
	private static String mainUrl;
	private static Integer hisotryRecordMonths;
	private static Integer inStorageHisotryRecordMonths;
	private static String reportStorePath;
	private static String reportFileUrl;
	private static String behaviourRecordIp;
	private static String sysTem;
	private static String systemsUrl;
	private static String localFilePath;

	
	public static String getMySystemCode() {
		return mySystemCode;
	}
	
	public static Integer getHisotryRecordMonths() {
		return hisotryRecordMonths;
	}

	public static Integer getInStorageHisotryRecordMonths() {
		return inStorageHisotryRecordMonths;
	}

	public static String getReportStorePath() {
		return reportStorePath;
	}

	public static String getReportFileUrl() {
		return reportFileUrl;
	}
	
	public static String getBehaviourRecordIp() {
		return behaviourRecordIp;
	}
	
	public static String getSysTem() {
		return sysTem;
	}
	
	public static String getSystemsUrl() {
		return systemsUrl;
	}

	public static final Properties props = new Properties();
	static {
		String fileName = "config.properties";
		String configFile = System.getenv("CONFIG_SPACE") + "/pcbmis/" + fileName;
		logger.info(">>>>> config file >>>>>>>>>>" + configFile);
		
//		InputStream in = Config.class.getClassLoader().getResourceAsStream(fileName);
		InputStream in = null;
		try {
			in = new FileInputStream(new File(configFile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		if (in != null) {
			try {
				props.load(in);
			} catch (IOException e) {
				throw new BusinessException(ExceptionType.EXCEPTION_400, "未找到config.properties文件");
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
					}
				}
			}
		}
		
		mainUrl = ConfigProperties.getProperty(ConfigEnum.SERVICE_MAIN_URL);
		reportStorePath = ConfigProperties.getProperty(ConfigEnum.REPORT_STORE_PATH);
		reportFileUrl = ConfigProperties.getProperty(ConfigEnum.REPORT_FILE_URL);
		hisotryRecordMonths = ConfigProperties.getPropertyInt(ConfigEnum.HISOTRY_RECORD_MONTHS, 12);
		inStorageHisotryRecordMonths = ConfigProperties.getPropertyInt(ConfigEnum.IN_STORAGE_HISOTRY_RECORD_MONTHS, 1);
		behaviourRecordIp = ConfigProperties.getProperty(ConfigEnum.BEHAVIOUR_RECORD_URL);
		sysTem = ConfigProperties.getProperty(ConfigEnum.SYSTEM_CODE);
		systemsUrl = ConfigProperties.getProperty(ConfigEnum.SYSTEMS_URL);
		localFilePath = ConfigProperties.getProperty(ConfigEnum.LOCAL_FILE_PATH);
		//创建本地文件夹
		File file = new File(localFilePath);
		if(!file.exists()){
			file.mkdirs();
		}

	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static String getMainUrl() {
		return mainUrl;
	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static String getProperty(ConfigEnum config){
		return props.getProperty(config.getCode());
	}
	/**
	 * 获取静态变量参数
	 * @param constant
	 * @return
	 */
	public static String getProperty(ConfigEnum config, String defValue){
		String value = props.getProperty(config.getCode());
		if (value == null) {
			return defValue;
		}
		return value;
	}
	public static Integer getPropertyInt(ConfigEnum constant){
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			logger.error("找不到该配置:" + constant.getCode());
			return null;
		}
		return Integer.valueOf(value);
	}
	public static Integer getPropertyInt(ConfigEnum constant, Integer defValue){
		String value = props.getProperty(constant.getCode());
		if (value == null) {
			return defValue;
		}
		return Integer.valueOf(value);
	}
}
