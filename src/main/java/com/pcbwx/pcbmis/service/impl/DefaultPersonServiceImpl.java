package com.pcbwx.pcbmis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.pcbmis.bean.request.FormDefaultPersonBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.FormDefaultPersonMapper;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.FormDefaultPerson;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.DefaultPersonService;
import com.pcbwx.pcbmis.service.SupportService;

@Service("defaultPersonService")
public class DefaultPersonServiceImpl implements DefaultPersonService {

//	private static Logger logger = Logger.getLogger(DefaultPersonServiceImpl.class);

	@Autowired
	private FormDefaultPersonMapper formDefaultPersonMapper;
	
	@Autowired
	private CacheService cacheService;
	@Autowired
	private SupportService supportService;

	@Override
	public Map<String, Object> getList(Integer page, Integer rows) {
		Map<String, Object> response = new HashMap<>();
		Integer count = formDefaultPersonMapper.countAll();
		List<FormDefaultPersonBean> defaultPersonBeans = new ArrayList<>();
		response.put("total", count);
		response.put("rows", defaultPersonBeans);
		if (count == 0) {
			return response;
		}
		Integer start = (page - 1) * rows;
		List<FormDefaultPerson> defaultPersons = formDefaultPersonMapper.selectByPage(start, rows);
		for (FormDefaultPerson formDefaultPerson : defaultPersons) {
			FormDefaultPersonBean formDefaultPersonBean = new FormDefaultPersonBean();
			defaultPersonBeans.add(formDefaultPersonBean);
			formDefaultPersonBean.setId(formDefaultPerson.getId());
			formDefaultPersonBean.setDefaultName(formDefaultPerson.getDefaultName());
			formDefaultPersonBean.setFieldName(formDefaultPerson.getFieldName());
			formDefaultPersonBean.setName(formDefaultPerson.getName());
			formDefaultPersonBean.setUserCode(formDefaultPerson.getUserCode());
		}
		return response;
	}

	@Override
	public ErrorCodeEnum modify(Integer id, String userCode) {
		FormDefaultPerson formDefaultPerson =  formDefaultPersonMapper.selectByPrimaryKey(id);
		if (formDefaultPerson == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		if (Objects.equals(userCode, formDefaultPerson.getUserCode())) {
			return ErrorCodeEnum.DEFAULT_NAME;
		}
		WxtbUser wxtbUser = cacheService.getWxtbUser(userCode);
		if (wxtbUser == null) {
			return ErrorCodeEnum.SYSTEM_ERROR;
		}
		formDefaultPerson.setUserCode(userCode);
		formDefaultPerson.setDefaultName(wxtbUser.getUsername());
		formDefaultPerson.setUpdateTime(null);
		formDefaultPersonMapper.updateByPrimaryKeySelective(formDefaultPerson);
		// 重载内存
		supportService.reloadFormDefaultPerson();
		return ErrorCodeEnum.SUCCESS;
	}
	
	

}
