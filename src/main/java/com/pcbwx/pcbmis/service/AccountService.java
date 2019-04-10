package com.pcbwx.pcbmis.service;


/**
 * 用户会话模块业务接口
 * @author 王海龙
 */

public interface AccountService {
	/**
	 * 获取按钮是否显示，是否能点击
	 * @param account
	 * @param buttonId
	 * @return
	 */
	Boolean getButtonAppear(String userCode, Integer buttonId);
}
