package com.pcbwx.pcbmis.control;

import javax.servlet.http.HttpServletRequest;

import com.pcbwx.pcbmis.bean.CommonEnumBean;
import com.pcbwx.pcbmis.bean.CommonStrEnumBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.pcbwx.pcbmis.service.UtilService;

import io.swagger.annotations.ApiOperation;

import java.util.List;

@Controller
@RequestMapping("/util")
public class UtilController extends BaseController{
	
	@Autowired
	private UtilService utilService;
	
	@ApiOperation("角色下拉框")
	@RequestMapping( value = "/roles",method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getRoles(HttpServletRequest request){
		return utilService.getRoles(request);
	}
	
	@ApiOperation("权限下拉框")
	@RequestMapping( value = "/auths",method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getAuths(HttpServletRequest request){
		return utilService.getAuths(request);
	}
	
	@ApiOperation("厂家下拉框")
	@RequestMapping( value = "/factory", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getFactorys(){
		return utilService.getFactoryList();
	}
	
	@ApiOperation("等级下拉框")
	@RequestMapping( value = "/grade", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getGrades(){
		return utilService.getGrade();
	}
	
	@ApiOperation("检验内容下拉框")
	@RequestMapping( value = "/checkContent", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getCheckContent(){
		return utilService.getCheckContent();
	}
	
	@ApiOperation("检验状态下拉框")
	@RequestMapping( value = "/checkState", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getCheckStateList(){
		return utilService.getCheckStateList();
	}
	
	@ApiOperation("报告状态下拉框")
	@RequestMapping( value = "/reportState", method = RequestMethod.POST)
	@ResponseBody
	public JSONArray getReportStateList(){
		return utilService.getReportStateList();
	}

	@ApiOperation("处置方式")
	@RequestMapping( value = "/disposalMethod", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getDisposalMethodList(){
		return utilService.getDisposalMethodList();
	}

	@ApiOperation("产品类型")
	@RequestMapping( value = "/productType", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getProductTypeList(){
		return utilService.getProductTypeList();
	}

	@ApiOperation("不合格品来源")
	@RequestMapping( value = "/unqualifiedSource", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getUnqualifiedSourceList(){
		return utilService.getUnqualifiedSourceList();
	}

	@ApiOperation("产品等级")
	@RequestMapping( value = "/productLevel", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getProductLevelList(){
		return utilService.getProductLevelList();
	}
	
	@ApiOperation("模板列表")
	@RequestMapping( value = "/template", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getPcbTemplate(){
		return utilService.getTemplateList();
	}
	
	@ApiOperation("用户")
	@RequestMapping( value = "/user", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonStrEnumBean> getUserList() {
		return utilService.getUserList();
	}
	
	@ApiOperation("SMT工单状态")
	@RequestMapping( value = "/smtOrderStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getSmtOrderStatus() {
		return utilService.getSMTOrderStatus();
	}
	
	@ApiOperation("COL工单状态")
	@RequestMapping( value = "/colOrderStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getColOrderStatus() {
		return utilService.getCOLOrderStatus();
	}
	
	@ApiOperation("colSmt报告状态")
	@RequestMapping( value = "/colSmtReportStatus", method = RequestMethod.GET)
	@ResponseBody
	public List<CommonEnumBean> getReportStatus() {
		return utilService.getReportStatus();
	}
	
}
