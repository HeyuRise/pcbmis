package com.pcbwx.pcbmis.exception;

/**
 * 获取流水号异常
 * @author  孙贺宇
 * @version 1.0
 * @since   2018-07-01
 */
@SuppressWarnings("serial")
public class SerialNumberException extends RuntimeException{

	/**
	 * 
	 */
	public SerialNumberException() {
	}

	/**
	 * @param message
	 */
	public SerialNumberException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public SerialNumberException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public SerialNumberException(String message, Throwable cause) {
		super(message, cause);
	}
}
