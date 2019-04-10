package com.pcbwx.pcbmis.service;

import java.util.Map;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.request.PcbCheckTemplateBean;
import com.pcbwx.pcbmis.bean.request.PcbReportTemplateBean;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;

public interface TemplateService {

	/**
	 * 获取模板列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	Map<String, Object> getTemplateList(String name, Integer page, Integer size);
	
	/**
	 * 添加模板
	 * @param name
	 * @return
	 */
	ErrorCodeEnum addTempalte(String name, AkAuthUser loginUser);
	
	/**
	 * 修改可用不可用
	 * @param id
	 * @return
	 */
	ErrorCodeEnum modifyEnable(Integer id, AkAuthUser loginUser);
	
	/**
	 * 查看检验单模板
	 * @param id
	 * @param loginUser
	 * @return
	 */
	Map<String, Object> getCheckTemplate(Integer templateId, Integer type);
	
	/**
	 * 查看报告单模板
	 * @param id
	 * @param loginUser
	 * @return
	 */
	Map<String, Object> getReportTemplate(Integer templateId);
	
	/**
	 * 修改检验单模板
	 * @param id
	 * @return
	 */
	ErrorCodeEnum modifyCheck(Integer templateId, AkAuthUser loginUser, PcbCheckTemplateBean checkTemplate);
	
	/**
	 * 修改报告单模板
	 * @param id
	 * @return
	 */
	ErrorCodeEnum modifyReport(Integer templateId, AkAuthUser loginUser, PcbReportTemplateBean reportTemplate);
}
