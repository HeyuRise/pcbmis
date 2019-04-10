package com.pcbwx.pcbmis.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.request.PcbCheckTemplateBean;
import com.pcbwx.pcbmis.bean.request.PcbReportTemplateBean;
import com.pcbwx.pcbmis.bean.response.PcbTemplateBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.PcbCheckTemplateMapper;
import com.pcbwx.pcbmis.dao.PcbReportTemplateMapper;
import com.pcbwx.pcbmis.dao.PcbTemplateMapper;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.PcbCheckTemplate;
import com.pcbwx.pcbmis.model.PcbReportTemplate;
import com.pcbwx.pcbmis.model.PcbTemplate;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.TemplateService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;

@Service("templateService")
public class TemplateServiceImpl implements TemplateService {
	private static Logger logger = Logger.getLogger(TemplateServiceImpl.class);

	@Autowired
	private PcbTemplateMapper pcbTemplateMapper;
	@Autowired
	private PcbCheckTemplateMapper pcbCheckTemplateMapper;
	@Autowired
	private PcbReportTemplateMapper pcbReportTemplateMapper;

	@Autowired
	private CacheService cacheService;

	@Override
	public Map<String, Object> getTemplateList(String name, Integer page, Integer size) {
		Map<String, Object> response = new HashMap<>();
		Integer start = (page - 1) * size;
		List<PcbTemplateBean> pcbTemplateBeans = new ArrayList<>();
		Integer count = pcbTemplateMapper.countLikeByName(name);
		response.put("total", count);
		response.put("rows", pcbTemplateBeans);
		if (count == 0) {
			return response;
		}
		List<PcbTemplate> pcbTemplateList = pcbTemplateMapper.selectByName(name, start, size);
		PcbTemplateBean pcbTemplateBean = null;
		for (PcbTemplate pcbTemplate : pcbTemplateList) {
			pcbTemplateBean = new PcbTemplateBean();
			pcbTemplateBean.setId(pcbTemplate.getId());
			pcbTemplateBean.setName(pcbTemplate.getName());
			pcbTemplateBean.setEnable(pcbTemplate.getEnable() ? "启用" : "禁用");
			String createTimeStr = DateTimeUtil.date2dateTimeStr(pcbTemplate.getCreateTime(), "yyyy-MM-dd HH:mm");
			String updateTimeStr = DateTimeUtil.date2dateTimeStr(pcbTemplate.getUpdateTime(), "yyyy-MM-dd HH:mm");
			pcbTemplateBean.setCreateTime(createTimeStr);
			pcbTemplateBean.setUpdateTime(updateTimeStr);
			WxtbUser creatorWxtbUser = cacheService.getWxtbUser(pcbTemplate.getCreator());
			if (creatorWxtbUser != null) {
				pcbTemplateBean.setCreator(creatorWxtbUser.getUsername());
			}
			WxtbUser updaterWxtbUser = cacheService.getWxtbUser(pcbTemplate.getUpdater());
			if (updaterWxtbUser != null) {
				pcbTemplateBean.setUpdater(updaterWxtbUser.getUsername());
			}
			pcbTemplateBeans.add(pcbTemplateBean);
		}
		return response;
	}

	@Override
	public ErrorCodeEnum addTempalte(String name, AkAuthUser loginUser) {
		Integer count = pcbTemplateMapper.countByName(name);
		if (count > 0) {
			return ErrorCodeEnum.TEMPALTE_NAME;
		}
		Date date = new Date();
		PcbTemplate pcbTemplate = new PcbTemplate();
		pcbTemplate.setName(name);
		pcbTemplate.setCreator(loginUser.getUserCode());
		pcbTemplate.setCreateTime(date);
		pcbTemplateMapper.insertSelective(pcbTemplate);
		Integer templateId = pcbTemplate.getId();
		PcbCheckTemplate pcbCheckTemplate = new PcbCheckTemplate();
		PcbReportTemplate pcbReportTemplate = new PcbReportTemplate();
		pcbCheckTemplate.setTemplateName(name);
		pcbCheckTemplate.setCreateTime(date);
		pcbCheckTemplate.setTemplateId(templateId);
		pcbReportTemplate.setTemplateName(name);
		pcbReportTemplate.setCreateTime(date);
		pcbReportTemplate.setTemplateId(templateId);
		pcbCheckTemplateMapper.insertSelective(pcbCheckTemplate);
		pcbReportTemplateMapper.insertSelective(pcbReportTemplate);
		reloadCheck();
		reloadReport();
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public ErrorCodeEnum modifyEnable(Integer id, AkAuthUser loginUser) {
		PcbTemplate pcbTemplate = pcbTemplateMapper.selectByPrimaryKey(id);
		if (pcbTemplate == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		if (pcbTemplate.getEnable()) {
			pcbTemplate.setEnable(false);
		} else {
			pcbTemplate.setEnable(true);
		}
		pcbTemplate.setUpdater(loginUser.getUserCode());
		pcbTemplate.setUpdateTime(null);
		pcbTemplateMapper.updateByPrimaryKeySelective(pcbTemplate);
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public Map<String, Object> getCheckTemplate(Integer templateId, Integer type) {
		Map<String, Object> response = new HashMap<>();
		PcbCheckTemplate pcbCheckTemplate = pcbCheckTemplateMapper.selectByTemplateId(templateId);
		if (pcbCheckTemplate == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String json = JsonUtil.obj2json(pcbCheckTemplate);
		PcbCheckTemplateBean pcbCheckTemplateBean = (PcbCheckTemplateBean) JsonUtil.json2obj(json,
				PcbCheckTemplateBean.class);
		if (Objects.equals(1, type)) {
			pcbCheckTemplateBean.init();
		}
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("data", pcbCheckTemplateBean);
		return response;
	}

	@Override
	public Map<String, Object> getReportTemplate(Integer templateId) {
		Map<String, Object> response = new HashMap<>();
		PcbReportTemplate pcbReportTemplate = pcbReportTemplateMapper.selectByTemplateId(templateId);
		if (pcbReportTemplate == null) {
			response.put("result", ErrorCodeEnum.SYSTEM_ERROR.getCode());
			response.put("msg", ErrorCodeEnum.SYSTEM_ERROR.getDescr());
			return response;
		}
		String json = JsonUtil.obj2json(pcbReportTemplate);
		PcbReportTemplateBean pcbReportTemplateBean = (PcbReportTemplateBean) JsonUtil.json2obj(json,
				PcbReportTemplateBean.class);
		response.put("result", ErrorCodeEnum.SUCCESS.getCode());
		response.put("data", pcbReportTemplateBean);
		return response;
	}

	@Override
	public ErrorCodeEnum modifyCheck(Integer templateId, AkAuthUser loginUser, PcbCheckTemplateBean checkTemplate) {
		PcbCheckTemplate pcbCheckTemplate = pcbCheckTemplateMapper.selectByTemplateId(templateId);
		if (pcbCheckTemplate == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		Date now = new Date();
		String json = JsonUtil.obj2json(checkTemplate);
		json = json.replaceAll("<div>", "<br>");
		json = json.replaceAll("</div>", "");
		PcbCheckTemplate pcbCheckTemplateNew = (PcbCheckTemplate) JsonUtil.json2obj(json, PcbCheckTemplate.class);
		pcbCheckTemplateNew.setId(pcbCheckTemplate.getId());
		pcbCheckTemplateNew.setTemplateId(templateId);
		pcbCheckTemplateNew.setTemplateName(pcbCheckTemplate.getTemplateName());
		pcbCheckTemplateNew.setCreateTime(pcbCheckTemplate.getCreateTime());
		pcbCheckTemplateMapper.updateByPrimaryKeySelective(pcbCheckTemplateNew);
		// 更新pcb_template
		PcbTemplate pcbTemplate = new PcbTemplate();
		pcbTemplate.setId(templateId);
		pcbTemplate.setCreator(loginUser.getUserCode());
		pcbTemplate.setUpdateTime(now);
		pcbTemplateMapper.updateByPrimaryKeySelective(pcbTemplate);
		reloadCheck();
		reloadReport();
		return ErrorCodeEnum.SUCCESS;
	}

	@Override
	public ErrorCodeEnum modifyReport(Integer templateId, AkAuthUser loginUser, PcbReportTemplateBean reportTemplate) {
		PcbReportTemplate pcbReportTemplate = pcbReportTemplateMapper.selectByTemplateId(templateId);
		if (pcbReportTemplate == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		Date now = new Date();
		String json = JsonUtil.obj2json(reportTemplate);
		json = json.replaceAll("<div>", "<br>");
		json = json.replaceAll("</div>", "");
		PcbReportTemplate pcbReportTemplateNew = (PcbReportTemplate) JsonUtil.json2obj(json, PcbReportTemplate.class);
		pcbReportTemplateNew.setId(pcbReportTemplate.getId());
		pcbReportTemplateNew.setTemplateId(templateId);
		pcbReportTemplateNew.setTemplateName(pcbReportTemplate.getTemplateName());
		pcbReportTemplateNew.setCreateTime(pcbReportTemplate.getCreateTime());
		pcbReportTemplateMapper.updateByPrimaryKeySelective(pcbReportTemplateNew);
		// 更新pcb_template
		PcbTemplate pcbTemplate = new PcbTemplate();
		pcbTemplate.setId(templateId);
		pcbTemplate.setCreator(loginUser.getUserCode());
		pcbTemplate.setUpdateTime(now);
		pcbTemplateMapper.updateByPrimaryKeySelective(pcbTemplate);
		reloadCheck();
		reloadReport();
		return ErrorCodeEnum.SUCCESS;
	}
	
	private void reloadCheck() {
		List<PcbCheckTemplate> pcbCheckTemplates = pcbCheckTemplateMapper.load();
		cacheService.reloadPcbCheckTemplate(pcbCheckTemplates);
	}

	private void reloadReport() {
		List<PcbReportTemplate> pcbReportTemplates = pcbReportTemplateMapper.load();
		cacheService.reloadPcbReportTemplate(pcbReportTemplates);
	}
}
