package com.pcbwx.pcbmis.service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.ReportDetail;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;

public interface ModifyService {

	/**
	 * 修改检验单
	 * @param checkNum      检验单号
	 * @param checkDetail   修改内容
	 * @param wxtbUser      系统用户
	 * @return
	 */
	ErrorCodeEnum modifyCheckOrder(String checkNum, CheckDetail checkDetail, AkAuthUser wxtbUser);
	
	/**
	 * 修改报告单
	 * @param reportNum     报告单号
	 * @param reportDetail  修改内容
	 * @param wxtbUser      系统用户
	 * @return
	 */
	ErrorCodeEnum modifyReportOrder(String reportNum,  ReportDetail reportDetail, AkAuthUser wxtbUser);
}
