package com.pcbwx.pcbmis.control;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.pcbmis.service.SmtService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/smt")
@Api(tags = "smt文档")
public class SmtController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(SmtController.class);

	@Autowired
	private SmtService smtService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	@ApiOperation("smt列表")
	public Map<String, Object> getSmtList(@RequestParam(value = "serialNumber", required = false) String serialNumber,
			@RequestParam(value = "orderNumber", required = false) String orderNumber,
			@RequestParam(value = "guestName", required = false) String guestName,
			@RequestParam(value = "productNum", required = false) Integer productNum,
			@RequestParam(value = "boardName", required = false) String boardName,
			@RequestParam(value = "contactName", required = false) String contactName,
			@RequestParam(value = "productDateStart", required = false) String productDateStartStr,
			@RequestParam(value = "prodcutDateEnd", required = false) String productDateEndStr,
			@RequestParam(value = "inspector", required = false) String inspector,
			@RequestParam(value = "reInspector", required = false) String reInspector,
			@RequestParam(value = "orderStatus", required = false) String orderStatus,
			@RequestParam(value = "reportStatus", required = false) Integer reportStatus,
			@RequestParam( value = "rows", defaultValue = "20", required = false) Integer rows,
			@RequestParam( value = "page", defaultValue = "1", required = false) Integer page) {
		if (StringUtil.isEmpty(serialNumber)) {
			serialNumber = null;
		}
		if (StringUtil.isEmpty(orderNumber)) {
			orderNumber = null;
		}
		if (StringUtil.isEmpty(guestName)) {
			guestName = null;
		}
		if (StringUtil.isEmpty(boardName)) {
			boardName = null;
		}
		if (StringUtil.isEmpty(contactName)) {
			contactName = null;
		}
		if (StringUtil.isEmpty(inspector)) {
			inspector = null;
		}
		if (StringUtil.isEmpty(reInspector)) {
			reInspector = null;
		}
		if (StringUtil.isEmpty(orderStatus)) {
			orderStatus = null;
		}
		Date productDateStart = DateTimeUtil.dateStr2date(productDateStartStr);
		Date productDateEnd = DateTimeUtil.dateStr2date(productDateEndStr);
		return smtService.getSmtService(serialNumber, orderNumber, guestName, productNum, boardName, contactName,
				productDateStart, productDateEnd, inspector, reInspector, orderStatus, reportStatus, page, rows);
	}
	
	@ApiOperation("获取报告详情")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getSmtReportDetail(@PathVariable Integer id){
		
		return smtService.getSmtReportDetail(id);
	}
	
	@ApiOperation("导出报告")
	@RequestMapping(value = "/file/{id}", method = RequestMethod.GET)
	@ResponseBody
	public void export(@PathVariable Integer id, HttpServletResponse response) {
		Workbook workbook = smtService.exportWork(id);
		if (workbook == null) {
			return;
		}
		try {
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/vnd.ms-excel");
			String fileName = "SMT出货检验报告-"
					+ DateTimeUtil.date2dateTimeStr(new Date(), "yyyyMMddHHmmss")
					+ ".xlsx";
			fileName = URLEncoder.encode(fileName, "UTF-8");
			response.addHeader("Content-Disposition", "attachment;filename="
					+ fileName);
			workbook.write(response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}

}
