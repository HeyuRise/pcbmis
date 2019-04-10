package com.pcbwx.pcbmis.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.log4j.Logger;
/**
 * MD5加密工具类
 * @author 王海龙
 * @version 1.0
 *
 */
public class MD5Util {

	private static final Logger logger = Logger.getLogger(MD5Util.class);
	
	/**
	 * 只对密码进行MD5加密
	 * @param password
	 * @return
	 */
	public static String md5Digest(String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes());
			return new BigInteger(1,md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
	}	
	
	/**
	 * 采用用户名和密码对密码进行MD5加密,盐值加密
	 * @param username
	 * @param password
	 * @return
	 */
	public static String md5Digest(String username,String password){
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(username.getBytes());
			md.update(password.getBytes());
			return new BigInteger(1,md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage());
			return null;
		}
	}	

	
	
}
