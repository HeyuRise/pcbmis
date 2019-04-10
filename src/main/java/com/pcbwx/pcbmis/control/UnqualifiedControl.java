package com.pcbwx.pcbmis.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.authkit.callback.AuthService;
import com.pcbwx.pcbmis.bean.UnqualifiedBean;
import com.pcbwx.pcbmis.bean.UnqualifiedHearBean;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.enums.ActionTypeEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.model.PcbFile;
import com.pcbwx.pcbmis.model.PcbUnqualified;
import com.pcbwx.pcbmis.service.LogService;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UnqualifiedService;
import com.pcbwx.pcbmis.utils.StringUtil;

/**
 * 不合格品处置单
 *
 * @author jisx
 * @date 2017-12-20
 */
@Controller
@RequestMapping("/unqualified")
public class UnqualifiedControl extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(UnqualifiedControl.class);

	@Autowired
	private UnqualifiedService unqualifiedService;
	@Autowired
	private AuthService authService;
	@Autowired
	private LogService logService;
	@Autowired
	private SupportService supportService;

	/**
	 * 创建不合格品处置单-录入不合格品数量和描述等基本信息
	 *
	 * @param request
	 * @param checkNumber
	 * @param unqualifiedDesc
	 * @param unqualifiedNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request, @RequestParam("check_num") String checkNumber,
			@RequestParam("desc") String unqualifiedDesc, @RequestParam("number") int unqualifiedNumber)
			throws Exception {
		final AkAuthUser wxtbUser = authService.getLoginUser();
		// 行为记录
		logService.addAction("", ActionTypeEnum.CREATE_UNQUALIFIED.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.CREATE_UNQUALIFIED.getDesc(), 0);
		Map<String, Object> response = new HashMap<>();
		boolean flag = false;
		PcbUnqualified unqualified = unqualifiedService.save(wxtbUser, checkNumber, unqualifiedNumber, unqualifiedDesc);
		if (unqualified != null && unqualified.getId() != null) {
			flag = true;
		}
		response.put("content", flag ? unqualified : null);
		response.put("msg", flag ? "" : "保存失败");
		response.put("result", flag ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
		return response;
	}

	/**
	 * 完善不合格品处置单-录入不合格品的描述，图片等信息
	 *
	 * @param request
	 * @param unqualifiedBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> saveDetail(HttpServletRequest request, @RequestBody UnqualifiedBean unqualifiedBean)
			throws Exception {
		AkAuthUser wxtbUser = authService.getLoginUser();
		// 行为记录
		logService.addAction("", ActionTypeEnum.PUT_UNQUALIFIED.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.PUT_UNQUALIFIED.getDesc(), 0);
		Map<String, Object> response = new HashMap<>();
		ErrorCodeEnum flag = unqualifiedService.saveDetail(wxtbUser, unqualifiedBean);
		response.put("content", null);
		response.put("msg", flag.getDescr());
		response.put("result", flag.getCode());
		return response;
	}

	/**
	 * 获取不合格品处置单的详情
	 *
	 * @param request
	 * @param serialNumber
	 * @param checkNumber
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getDetail(HttpServletRequest request, @RequestParam String serialNumber,
			@RequestParam String checkNumber) throws Exception {
		final AkAuthUser wxtbUser = authService.getLoginUser();

		// 行为记录
		logService.addAction("", ActionTypeEnum.GET_UNQUALIFIED.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.GET_UNQUALIFIED.getDesc(), 0);
		Map<String, Object> response = new HashMap<>();
		Map<String, Object> map = unqualifiedService.getDetail(wxtbUser, serialNumber, checkNumber);
		response.put("content", map);
		response.put("msg", map.size() > 0 ? "" : "获取详情失败");
		response.put("result", map.size() > 0 ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
		return response;
	}

	/**
	 * 获取不合格品处置单的列表
	 *
	 * @param request
	 * @param unqualifiedQueryMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getList(HttpServletRequest request,
			@RequestParam Map<String, String> unqualifiedQueryMap, @RequestParam Integer page,
			@RequestParam Integer rows) throws Exception {
		final AkAuthUser wxtbUser = authService.getLoginUser();

		// 行为记录
		logService.addAction("", ActionTypeEnum.GET_UNQUALIFIED_LIST.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.GET_UNQUALIFIED_LIST.getDesc(), 0);
		UnqualifiedQueryBean unqualifiedQueryBean = new UnqualifiedQueryBean();
		unqualifiedQueryBean.setBelongCompanyId(StringUtil.isEmpty(unqualifiedQueryMap.get("belongCompanyId")) ? null
				: Integer.valueOf(unqualifiedQueryMap.get("belongCompanyId")));
		unqualifiedQueryBean.setBoardName(
				unqualifiedQueryMap.get("boardName") == null ? null : unqualifiedQueryMap.get("boardName"));
		unqualifiedQueryBean
				.setCustomer(unqualifiedQueryMap.get("customer") == null ? null : unqualifiedQueryMap.get("customer"));
		unqualifiedQueryBean
				.setDisposal(unqualifiedQueryMap.get("disposal") == null ? null : unqualifiedQueryMap.get("disposal"));
		unqualifiedQueryBean.setFactoryId(StringUtil.isEmpty(unqualifiedQueryMap.get("factoryId")) ? null
				: Integer.valueOf(unqualifiedQueryMap.get("factoryId")));
		unqualifiedQueryBean.setInspector(
				unqualifiedQueryMap.get("inspector") == null ? null : unqualifiedQueryMap.get("inspector"));
		unqualifiedQueryBean.setOrderNumber(
				unqualifiedQueryMap.get("orderNumber") == null ? null : unqualifiedQueryMap.get("orderNumber"));
		unqualifiedQueryBean.setProductLevel(StringUtil.isEmpty(unqualifiedQueryMap.get("productLevel")) ? null
				: Integer.valueOf(unqualifiedQueryMap.get("productLevel")));
		unqualifiedQueryBean.setProductType(StringUtil.isEmpty(unqualifiedQueryMap.get("productType")) ? null
				: Integer.valueOf(unqualifiedQueryMap.get("productType")));
		unqualifiedQueryBean.setSubmitEndTime(StringUtil.isEmpty(unqualifiedQueryMap.get("submitEndTime")) ? null
				: unqualifiedQueryMap.get("submitEndTime") + " 23:59:59");
		unqualifiedQueryBean.setSubmitStartTime(StringUtil.isEmpty(unqualifiedQueryMap.get("submitStartTime")) ? null
				: unqualifiedQueryMap.get("submitStartTime") + " 00:00:00");
		Map<String, Object> response = new HashMap<>();
		List<Map<String, Object>> map = unqualifiedService.getList(unqualifiedQueryBean, page == null ? 1 : page,
				rows == null ? 100 : rows);
		int total = unqualifiedService.getTotalList(unqualifiedQueryBean);
		response.put("rows", map);
		response.put("total", total);
		response.put("result", map != null ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
		return response;
	}

	/**
	 * 保存审理结果
	 *
	 * @param request
	 * @param unqualifiedHearBean
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hear", method = RequestMethod.PUT)
	@ResponseBody
	public Map<String, Object> saveHear(HttpServletRequest request,
			@RequestBody UnqualifiedHearBean unqualifiedHearBean) throws Exception {
		AkAuthUser wxtbUser = authService.getLoginUser();
		// 行为记录
		logService.addAction("", ActionTypeEnum.PUT_UNQUALIFIED_HEAR.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.PUT_UNQUALIFIED_HEAR.getDesc(), 0);
		Map<String, Object> response = new HashMap<>();
		boolean flag = unqualifiedService.saveHear(unqualifiedHearBean, wxtbUser);
		response.put("content", null);
		response.put("msg", flag ? "" : "提交失败");
		response.put("result", flag ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
		return response;
	}

	/**
	 * 获取不合格品处置单的附件
	 *
	 * @param request
	 * @param unqualifiedId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/hear/file", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> getHearFile(HttpServletRequest request, @RequestParam int unqualifiedId)
			throws Exception {
		AkAuthUser wxtbUser = authService.getLoginUser();
		// 行为记录
		logService.addAction("", ActionTypeEnum.PUT_UNQUALIFIED_HEAR_FILE.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.PUT_UNQUALIFIED_HEAR_FILE.getDesc(),
				0);
		Map<String, Object> response = new HashMap<>();
		List<Map<String, Object>> hearFile = unqualifiedService.getHearFile(unqualifiedId);
		response.put("rows", hearFile);
		response.put("total", hearFile == null ? 0 : hearFile.size());
		response.put("result",
				hearFile != null ? ErrorCodeEnum.SUCCESS.getCode() : ErrorCodeEnum.SYSTEM_ERROR.getCode());
		return response;
	}

	/**
	 * 导出不合格品处置单
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	@ResponseBody
	public void exportUnqualifiedFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int unqualifiedId, @RequestParam int status) throws Exception {
		AkAuthUser wxtbUser = authService.getLoginUser();
		// 行为记录
		logService.addAction("", ActionTypeEnum.OUT_PUT_UNQUALIFIED_DETAIL.getCode(), wxtbUser.getUserCode(), "");
		supportService.doSystemLog(request, wxtbUser.getUsername(), ActionTypeEnum.OUT_PUT_UNQUALIFIED_DETAIL.getDesc(),
				0);
		Map<String, Object> map = unqualifiedService.getDetailForExport(unqualifiedId, status);
		List<PcbFile> pcbFiles = unqualifiedService.getImagesByUnqualifiedId(unqualifiedId);
		if (map == null || map.size() == 0) {
			new Exception("找不到数据，不能下载");
		}
		String ordernum = (String) map.get("order_number");
		supportService.doOperateLog("导出", ordernum, "不合格品处置单", wxtbUser.getUsername());
		FileInputStream in;
		ServletOutputStream outputStream;
		String inFilePath = URLDecoder
				.decode(this.getClass().getResource("/template/unqualified_template.xlsx").getPath(), "UTF-8");

		inFilePath = inFilePath.substring(1);
		in = new FileInputStream(File.separator + inFilePath);
		Workbook workbook = new XSSFWorkbook(in);
		Sheet sheet = workbook.getSheetAt(0);
		// 给excel的单元格赋值
		unqualifiedService.createExcelUnqualified(workbook, sheet, map, pcbFiles, status);
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/vnd.ms-excel");
		// 文件名，防止乱码
		String fileName = URLEncoder.encode("不合格品处置单" + ".xlsx", "UTF-8");
		response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
		outputStream = response.getOutputStream();
		workbook.write(outputStream);
		outputStream.flush();
		if (null != outputStream) {
			try {
				outputStream.close();
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

}
