package com.pcbwx.pcbmis.service;

import com.pcbwx.pcbmis.bean.channel.OutStorage;
import com.pcbwx.pcbmis.model.OutStorageOrder;

public interface PushService {
	
	/**
	 * 验证推送消息
	 * @param outStorage  推送信息
	 * @return
	 */
	OutStorageOrder getOutStorageNumber(OutStorage outStorage);

	/**
	 * 操作推送信息
	 * @return
	 */
	boolean operatePush(OutStorageOrder outStorage);
	
	/**
	 * 插入推送信息
	 * @param outStorage
	 */
	void insertPush(OutStorageOrder outStorage, boolean isSuccess);
}
