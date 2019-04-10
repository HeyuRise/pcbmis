package com.pcbwx.pcbmis.utils;

import java.lang.reflect.Method;

/**
 * 枚举工具类
 * 
 * @author 孙贺宇
 *
 */
public class EnumUtil {

	/**
	 * 根据值获取枚举对象
	 * @param clazz			枚举类
	 * @param mothedName	获取值对应的方法名
	 * @param code  		值
	 * @return
	 */
	public static <T extends Enum<T>> T getEnumByCode(Class<T> clazz,
			String mothedName, String code) {
		T result = null;
		try {
			T[] arr = clazz.getEnumConstants();
			Method method = clazz.getDeclaredMethod(mothedName);
			String typeCode = null;
			for (T t : arr) {
				typeCode = method.invoke(t).toString();
				if (typeCode.contentEquals(code)) {
					result = t;
					break;
				}
			}
		} catch (Exception e) {
			return null;
		}

		return result;
	}

}
