package com.pcbwx.pcbmis.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.pcbmis.service.AccountService;
import com.pcbwx.pcbmis.service.SupportService;

/**
 * 用户会话模块业务接口实现类
 * 
 * @author 孙贺宇
 *
 */

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	
//	private static Logger logger = Logger.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private SupportService supportService;

	@Override
	public Boolean getButtonAppear(String userCode, Integer buttonId) {
		// 获取用户所有权限
		Set<Integer> authIds = supportService.getUserAuths(userCode);
		if (authIds == null) {
			return false;
		}
		// 查看用户权限是否包含这个按钮的权限
		if (authIds.contains(buttonId)) {
			return true;
		}
		return false;
	}
	
}

