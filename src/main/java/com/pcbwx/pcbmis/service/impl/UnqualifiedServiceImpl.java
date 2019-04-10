package com.pcbwx.pcbmis.service.impl;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pcbwx.authkit.bean.AkAuthUser;
import com.pcbwx.pcbmis.bean.SerialNumber;
import com.pcbwx.pcbmis.bean.UnqualifiedBean;
import com.pcbwx.pcbmis.bean.UnqualifiedHearBean;
import com.pcbwx.pcbmis.bean.UnqualifiedQueryBean;
import com.pcbwx.pcbmis.component.CacheService;
import com.pcbwx.pcbmis.dao.PcbCheckDetailMapper;
import com.pcbwx.pcbmis.dao.PcbCheckOrderMapper;
import com.pcbwx.pcbmis.dao.PcbFileMapper;
import com.pcbwx.pcbmis.dao.PcbRelationFileMapper;
import com.pcbwx.pcbmis.dao.PcbUnqualifiedMapper;
import com.pcbwx.pcbmis.dao.ProductOrderMapper;
import com.pcbwx.pcbmis.enums.DictionaryEnum;
import com.pcbwx.pcbmis.enums.DisposalMethodEnum;
import com.pcbwx.pcbmis.enums.ErrorCodeEnum;
import com.pcbwx.pcbmis.enums.FileEnum;
import com.pcbwx.pcbmis.enums.ProductTypeEnum;
import com.pcbwx.pcbmis.model.CompanyInfo;
import com.pcbwx.pcbmis.model.Dictionary;
import com.pcbwx.pcbmis.model.FactoryInfo;
import com.pcbwx.pcbmis.model.PcbCheckDetail;
import com.pcbwx.pcbmis.model.PcbCheckOrder;
import com.pcbwx.pcbmis.model.PcbFile;
import com.pcbwx.pcbmis.model.PcbRelationFile;
import com.pcbwx.pcbmis.model.PcbUnqualified;
import com.pcbwx.pcbmis.model.ProductOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.service.SupportService;
import com.pcbwx.pcbmis.service.UnqualifiedService;
import com.pcbwx.pcbmis.utils.DateTimeUtil;
import com.pcbwx.pcbmis.utils.StringUtil;

/**
 * 不合格品
 *
 * @author jisx
 */
@Service("unqualifiedService")
public class UnqualifiedServiceImpl implements UnqualifiedService {
	
	private static Logger logger = LoggerFactory.getLogger(UnqualifiedServiceImpl.class);
	
    @Autowired
    private PcbUnqualifiedMapper pcbUnqualifiedMapper;
    @Autowired
    private SupportService supportService;
    @Autowired
    private CacheService cacheService;
    @Autowired
    private PcbCheckOrderMapper pcbCheckOrderMapper;
    @Autowired
    private ProductOrderMapper productOrderMapper;
    @Autowired
    private PcbCheckDetailMapper pcbCheckDetailMapper;
    @Autowired
    private PcbRelationFileMapper relationFileMapper;
    @Autowired
    private PcbFileMapper fileMapper;

    @Override
    public PcbUnqualified save(AkAuthUser wxtbUser, String checkNumber, int unqualifiedNumber, String unqualifiedDesc) {
        PcbUnqualified unqualified = new PcbUnqualified();
        //根据检测记录id查询检测记录结果 为的是获取工单类型，根据工单类型判断入库数量。：如果是拼板，则取pcs 如果是非拼板，则选择set
        PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(checkNumber);
        //根据检测详情的 checknumber + batch_number 获取批次号
        List<PcbCheckDetail> pcbCheckDetailList = pcbCheckDetailMapper.selectByCheckNumAndOptionName(checkNumber, "batch_number");
        //获取工单号
        ProductOrder productOrder = productOrderMapper.selectByOrderNum(pcbCheckOrder.getProductOrderNum());
        unqualified.setCheckNum(checkNumber);
        unqualified.setOrderNumber(productOrder.getOrderNum());
        //工单类型
        unqualified.setOrderType(pcbCheckOrder.getOrderType());
        unqualified.setBoardName(pcbCheckOrder.getBoardName());
        //不合格批次
        if (pcbCheckDetailList != null && pcbCheckDetailList.size() > 0) {
            unqualified.setUnqualifiedBatch(pcbCheckDetailList.get(0).getCheckResult());
        }
        unqualified.setUnqualifiedNumber(unqualifiedNumber);
        unqualified.setUnqualifiedDesc(unqualifiedDesc);
        unqualified.setBelongCompanyId(productOrder.getBelongCompanyId());
        unqualified.setGuestCode(productOrder.getGuestCode());
        unqualified.setProductLevel(productOrder.getCategoryGradeId());
        unqualified.setFactoryId(productOrder.getFactoryId());
        unqualified.setInspector(wxtbUser.getUserCode());
        unqualified.setCreateTime(new Date());
        // 创建后不显示
        unqualified.setEnable(0);
        // 检验员提交了不合格品的数量和描述
        unqualified.setStatus(1);

        pcbUnqualifiedMapper.insert(unqualified);
        supportService.doOperateLog("新建", pcbCheckOrder.getProductOrderNum(), "不合格品处置单", wxtbUser.getUsername());
        return unqualified;
    }

    @Override
    public ErrorCodeEnum saveDetail(AkAuthUser wxtbUser, UnqualifiedBean unqualifiedBean) {
        PcbUnqualified unqualified = pcbUnqualifiedMapper.selectByPrimaryKey(unqualifiedBean.getUnqualifiedId());
        if (unqualified == null || unqualified.getId() == null) {
            return ErrorCodeEnum.SYSTEM_ERROR;
        }
        //获取工单号中的所属公司id
        ProductOrder productOrder = productOrderMapper.selectByOrderNum(unqualifiedBean.getOrderNumber());
        Integer companyId = productOrder.getBelongCompanyId();
        //获取所属公司id查询缓存中的公司code
        CompanyInfo companyInfo = cacheService.getCompany(companyId);
        if (companyInfo == null) {
            return ErrorCodeEnum.SYSTEM_ERROR;
        }
        //获取质量记录流水号
        SerialNumber serialNumber = supportService.getQrgsSerialNumber(wxtbUser.getInnerCode(), "不合格品处置单", new Date(), companyInfo.getOrgCode());
        if (serialNumber == null || 
        		StringUtil.isEmpty(serialNumber.getDocumentNumber()) || 
				StringUtil.isEmpty(serialNumber.getRevision()) || 
				StringUtil.isEmpty(serialNumber.getSn())) {
            //质量记录获取失败
            return ErrorCodeEnum.NO_SERIAL_NUMBER;
        }
        //设置质量记录流水号
        unqualified.setSerialNumber(serialNumber.getSn());
        unqualified.setRevision(serialNumber.getRevision());
        unqualified.setDocumentNumber(serialNumber.getDocumentNumber());
        //不合格来源
        unqualified.setUnqualifiedSource(unqualifiedBean.getUnqualifiedSource());
        //产品类型
        unqualified.setProductType(unqualifiedBean.getProductType());
        //产品等级
        unqualified.setProductLevel(unqualifiedBean.getProductLevel());
        //不合格特征描述
        unqualified.setUnqualifiedDesc(unqualifiedBean.getUnqualifiedDesc());
        unqualified.setSubmitTime(StringUtil.isEmpty(unqualifiedBean.getSubmitTime()) ? new Date() : DateTimeUtil.dateTimeStr2date(unqualifiedBean.getSubmitTime()));
        unqualified.setUnqualifiedBatch(StringUtil.isEmpty(unqualifiedBean.getUnqualifiedBatch()) ? null : unqualifiedBean.getUnqualifiedBatch());
        //检验员提交了不合格品板名，可能附带提交图片等更详细的描述
        unqualified.setStatus(2);
        pcbUnqualifiedMapper.updateByPrimaryKeySelective(unqualified);
        //把以前保存的图片废弃掉
        List<PcbRelationFile> pcbRelationFiles1 = relationFileMapper.selectByUnqualifiedId(unqualified.getId(), FileEnum.IMAGE.getType());
        for (PcbRelationFile pcbRelationFile : pcbRelationFiles1) {
            pcbRelationFile.setEnable(0);
            relationFileMapper.updateByPrimaryKey(pcbRelationFile);
            fileMapper.deleteOnStatus(pcbRelationFile.getFileId());
        }
        //保存新图片
        List<PcbRelationFile> pcbRelationFiles;
        PcbRelationFile pcbRelationFile;
        if (unqualifiedBean.getImages() != null && unqualifiedBean.getImages().length > 0) {
            pcbRelationFiles = new ArrayList<>();
            for (int i : unqualifiedBean.getImages()) {
                pcbRelationFile = new PcbRelationFile();
                pcbRelationFile.setFileId(i);
                pcbRelationFile.setUnqualifiedId(unqualified.getId());
                pcbRelationFile.setCreateTime(new Date());
                pcbRelationFile.setEnable(1);
                pcbRelationFiles.add(pcbRelationFile);
            }
            relationFileMapper.insertBatch(pcbRelationFiles);
        }
        return ErrorCodeEnum.SUCCESS;
    }

    @Override
    public Map<String, Object> getDetail(AkAuthUser wxtbUser, String serialNumber, String checkNumber) {
        Map<String, Object> map = new HashMap<>();
        PcbUnqualified unqualified;
        if (!StringUtil.isEmpty(serialNumber)) {
            unqualified = pcbUnqualifiedMapper.selectBySerialNumber(serialNumber);
        } else {
            unqualified = pcbUnqualifiedMapper.selectByCheckNumber(checkNumber);
        }
        if (unqualified == null) {
            //找不到不合格品处置单
            return map;
        }
        map.put("unqualifiedId", unqualified.getId());
        map.put("serialNumber", unqualified.getSerialNumber());
        map.put("revision", unqualified.getRevision());
        map.put("documentNumber", unqualified.getDocumentNumber());
        map.put("checkNum", unqualified.getCheckNum());
        map.put("orderNumber", unqualified.getOrderNumber());
        //获取工单号
        PcbCheckOrder pcbCheckOrder = pcbCheckOrderMapper.selectByCheckNum(unqualified.getCheckNum());
        map.put("storage_number", pcbCheckOrder.getInAmountPcs());
        map.put("boardName", unqualified.getBoardName());
        map.put("unqualifiedBatch", unqualified.getUnqualifiedBatch());
        map.put("unqualifiedNumber", unqualified.getUnqualifiedNumber());
        map.put("unqualifiedDesc", unqualified.getUnqualifiedDesc());
        final FactoryInfo factory = cacheService.getFactory(unqualified.getFactoryId());
        if (factory != null) {
            map.put("factoryName", factory.getFactoryName());
        }
        map.put("unqualifiedSource", unqualified.getUnqualifiedSource());
        map.put("productType", unqualified.getProductType());
        map.put("productLevel", unqualified.getProductLevel());
        if (unqualified.getInspector() != null) {
            WxtbUser user = cacheService.getWxtbUser(unqualified.getInspector());
            if (user != null) {
                map.put("inspector", user.getUsername());
            }
        }
        map.put("submitTime", unqualified.getSubmitTime());
        map.put("createTime", unqualified.getCreateTime());
        map.put("updateTime", unqualified.getUpdateTime());
        //审理部分
        map.put("adjudicating_opinions", unqualified.getAdjudicatingOpinions());
        if (StringUtil.isEmpty(unqualified.getDisposalMethod())) {
            map.put("disposal_method", new int[0]);
        } else {
            map.put("disposal_method", unqualified.getDisposalMethod().toCharArray());
        }
        map.put("trial_personnel", unqualified.getTrialPersonnel());
        map.put("trial_time", unqualified.getTrialTime());
        map.put("approval_time", unqualified.getApprovalTime());
        map.put("approval_personnel", unqualified.getApprovalPersonnel());
        List<PcbRelationFile> pcbRelationFiles = relationFileMapper.selectByUnqualifiedId(unqualified.getId(), FileEnum.IMAGE.getType());
        map.put("images", pcbRelationFiles);
        return map;
    }

    @Override
    public List<Map<String, Object>> getList(UnqualifiedQueryBean unqualifiedQueryBean, int page, int rows) {
        List<Map<String, Object>> pcbUnqualifieds = pcbUnqualifiedMapper.selectListByQuery(unqualifiedQueryBean, (page - 1) * rows, rows);
 //       StringBuffer descBuffer = new StringBuffer();
        for (Map<String, Object> pcbUnqualified : pcbUnqualifieds) {
            //处置方式转换成字符串
            intValueToStringValue(pcbUnqualified);
        }
        return pcbUnqualifieds;
    }

    @Override
    public int getTotalList(UnqualifiedQueryBean unqualifiedQueryBean) {
        return pcbUnqualifiedMapper.selectListTotalByQuery(unqualifiedQueryBean);
    }

    @Override
    public boolean saveHear(UnqualifiedHearBean unqualifiedHearBean, AkAuthUser wxtbUser) {
        PcbUnqualified unqualified = pcbUnqualifiedMapper.selectByPrimaryKey(unqualifiedHearBean.getUnqualifiedId());
        if (unqualifiedHearBean.getDisposalMethod() != null) {
            unqualified.setDisposalMethod(Arrays.toString(unqualifiedHearBean.getDisposalMethod()));
        }
        unqualified.setAdjudicatingOpinions(unqualifiedHearBean.getAdjudicatingOpinions());
        unqualified.setTrialTime(DateTimeUtil.dateStr2date(unqualifiedHearBean.getTrialTime()));
        unqualified.setTrialPersonnel(unqualifiedHearBean.getTrialPersonnel());
        unqualified.setApprovalTime(DateTimeUtil.dateStr2date(unqualifiedHearBean.getApprovalTime()));
        unqualified.setApprovalPersonnel(unqualifiedHearBean.getApprovalPersonnel());
        //提交了审核内容
        unqualified.setStatus(3);
        supportService.doOperateLog("提交", unqualified.getOrderNumber(), "不合格品审理内容", wxtbUser.getUsername());
        return pcbUnqualifiedMapper.updateByPrimaryKeySelective(unqualified) > 0;
    }

    @Override
    public List<Map<String, Object>> getHearFile(int unqualifiedId) {
        return relationFileMapper.selectByUnqualifiedIdForFileList(unqualifiedId, FileEnum.FILE.getType());
    }

    @Override
    public Map<String, Object> getDetailForExport(int unqualifiedId, int status) {
        Map<String, Object> map = pcbUnqualifiedMapper.selectByUnqualifiedIdAndStatus(unqualifiedId, status);

        return map;
    }

    @Override
    public List<PcbFile> getImagesByUnqualifiedId(int unqualifiedId) {
        List<PcbRelationFile> pcbRelationFiles = relationFileMapper.selectByUnqualifiedId(unqualifiedId, FileEnum.IMAGE.getType());
        List<Integer> images = new ArrayList<>();
        List<PcbFile> list = new ArrayList<>();
        for (PcbRelationFile pcbRelationFile : pcbRelationFiles) {
            images.add(pcbRelationFile.getFileId());
        }
        if (images.size() > 0) {
            list.addAll(fileMapper.selectBatch(images));
        }
        return list;
    }
    
    @Override
	public void createExcelUnqualified(Workbook workbook, Sheet sheet, Map<String, Object> map, List<PcbFile> pcbFiles,
			int status) {
		Font font = workbook.createFont();
		font.setFontName("宋体");
		font.setFontHeightInPoints((short) 10);
		font.setColor(XSSFFont.DEFAULT_FONT_COLOR);

		int[] code = { 0x2610, 0x2611, 0x2612 };
		Row row1 = sheet.getRow(1);
		// 设置质量记录流水号等信息
		row1.getCell(0)
				.setCellValue(map.get("serial_number") == null ? "NO:" : "NO:" + map.get("serial_number").toString());
		row1.getCell(5).setCellValue(map.get("revision") == null ? "版次：" : "版次：" + map.get("revision").toString());
		row1.getCell(7)
				.setCellValue(map.get("document_number") == null ? "" : "" + map.get("document_number").toString());

		// 工单号和产品名称
		Row row2 = sheet.getRow(2);
		row2.getCell(2).setCellValue(map.get("order_number") == null ? "" : "" + map.get("order_number").toString());
		row2.getCell(6).setCellValue(map.get("board_name") == null ? "" : "" + map.get("board_name").toString());
		// 产品数量
		Row row3 = sheet.getRow(3);
		row3.getCell(2)
				.setCellValue(map.get("storage_number") == null ? "" : "" + map.get("storage_number").toString());
		// 不合格品批次和不合格品数量
		Row row4 = sheet.getRow(4);
		row4.getCell(2)
				.setCellValue(map.get("unqualified_batch") == null ? "" : "" + map.get("unqualified_batch").toString());
		row4.getCell(6).setCellValue(
				map.get("unqualified_number") == null ? "" : "" + map.get("unqualified_number").toString());
		// 责任单位
		Row row5 = sheet.getRow(5);
		row5.getCell(2).setCellValue(map.get("factory_name") == null ? "" : "" + map.get("factory_name").toString());

		// 不合格品来源
		if (map.get("unqualified_source") != null) {
			Row row6 = sheet.getRow(6);
			Cell row6Cell2 = row6.getCell(2);
			row6Cell2.getCellStyle().setFont(font);
			int unqualified_source = Integer.parseInt(map.get("unqualified_source").toString());
			switch (unqualified_source) {
			case 1:
				row6Cell2.setCellValue(new String(code, 1, 1) + "采购/外包产品     □过程产品     □成品     □顾客产品");
				break;
			case 2:
				row6Cell2.setCellValue("□采购/外包产品     " + new String(code, 1, 1) + "过程产品     □成品     □顾客产品");
				break;
			case 3:
				row6Cell2.setCellValue("□采购/外包产品     □过程产品     " + new String(code, 1, 1) + "成品     □顾客产品");
				break;
			case 4:
				row6Cell2.setCellValue("□采购/外包产品     □过程产品     □成品     " + new String(code, 1, 1) + "顾客产品");
				break;
			}
		}

		// 产品类型
		if (map.get("product_type") != null) {
			Row row7 = sheet.getRow(7);
			Cell row7cell2 = row7.getCell(2);
			row7cell2.getCellStyle().setAlignment(XSSFCellStyle.ALIGN_CENTER);
			row7cell2.getCellStyle().setFont(font);
			int productType = Integer.parseInt(map.get("product_type").toString());
			switch (productType) {
			case 1:
				row7cell2.setCellValue(new String(code, 1, 1) + "PCB   □SMT   □COL   □快速接头   □金属结构件   □电连接器");
				break;
			case 2:
				row7cell2.setCellValue("□PCB   " + new String(code, 1, 1) + "SMT   □COL   □快速接头   □金属结构件   □电连接器");
				break;
			case 3:
				row7cell2.setCellValue("□PCB   □SMT   " + new String(code, 1, 1) + "COL   □快速接头   □金属结构件   □电连接器");
				break;
			case 4:
				row7cell2.setCellValue("□PCB   □SMT   □COL   " + new String(code, 1, 1) + "快速接头   □金属结构件   □电连接器");
				break;
			case 5:
				row7cell2.setCellValue("□PCB   □SMT   □COL   □快速接头   " + new String(code, 1, 1) + "金属结构件   □电连接器");
				break;
			case 6:
				row7cell2.setCellValue("□PCB   □SMT   □COL   □快速接头   □金属结构件   " + new String(code, 1, 1) + "电连接器");
				break;
			}
		}

		// 产品等级
		if (map.get("product_level") != null) {
			Row row8 = sheet.getRow(8);
			Cell row8cell2 = row8.getCell(2);
			CellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFont(font);
			cellStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
			row8cell2.setCellStyle(cellStyle);

			int productLevel = Integer.parseInt(map.get("product_level").toString());
			switch (productLevel) {
			case 1:
				row8cell2.setCellValue("□宇航级          □上天件          □军品          " + new String(code, 1, 1) + "民品");
				break;
			case 2:
				row8cell2.setCellValue("□宇航级          " + new String(code, 1, 1) + "上天件          □军品          □民品");
				break;
			case 4:
				row8cell2.setCellValue("□宇航级          □上天件          " + new String(code, 1, 1) + "军品          □民品");
				break;
			case 5:
				row8cell2.setCellValue(new String(code, 1, 1) + "宇航级          □上天件          □军品          □民品");
				break;
			case 7:
				row8cell2.setCellValue("□宇航级          □上天件          □军品          " + new String(code, 1, 1) + "民品");
				break;
			case 8:
				row8cell2.setCellValue("□宇航级          □上天件          □军品          " + new String(code, 1, 1) + "民品");
				break;
			}
		}
		// 不合格品描述
		Row row10 = sheet.getRow(10);
		row10.getCell(0)
				.setCellValue(map.get("unqualified_desc") == null ? "" : "" + map.get("unqualified_desc").toString());

		// 插入图片
		BufferedImage bufferImg;
		ByteArrayOutputStream byteArrayOut;
		Drawing drawingPatriarch;
		try {
			int index = 0;
			for (PcbFile pcbFile : pcbFiles) {
				byteArrayOut = new ByteArrayOutputStream();
				bufferImg = ImageIO.read(new File(pcbFile.getPath()));
				ImageIO.write(bufferImg, pcbFile.getSuffix().replace(".", ""), byteArrayOut);
				drawingPatriarch = sheet.createDrawingPatriarch();
				// 设置位置
				XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 500, 500, (short) index, 11, (short) index + 1,
						12);
				// 设置位置参数
				anchor.setAnchorType(2);
				// 插入图片
				drawingPatriarch.createPicture(anchor,
						workbook.addPicture(byteArrayOut.toByteArray(), XSSFWorkbook.PICTURE_TYPE_JPEG));
				index += 1;
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		// 检验员和日期
		Row row12 = sheet.getRow(12);
		row12.getCell(5).setCellValue(map.get("username") == null ? "" : "" + map.get("username").toString());
		row12.getCell(7).setCellValue(map.get("submit_time") == null ? ""
				: "" + DateTimeUtil.date2dateStr(DateTimeUtil.dateStr2date(map.get("submit_time").toString())));

		if (status == 2) {
			// 如果是2，则不需要审理，如果状态是3，则说明审理过了。
			return;
		}
		// 审理意见
		Row row14 = sheet.getRow(14);
		row14.getCell(0).setCellValue(
				map.get("adjudicating_opinions") == null ? "" : "" + map.get("adjudicating_opinions").toString());

		if (map.get("disposal_method") != null) {
			// 处置方式
			Row row16 = sheet.getRow(16);

			Cell row16cell0 = row16.getCell(0);
			row16cell0.getCellStyle().setFont(font);

			String disposal = map.get("disposal_method").toString();
			StringBuffer disposalBuffer = new StringBuffer();
			if (disposal.contains("1")) {
				disposalBuffer.append(new String(code, 1, 1) + "返工");
			} else {
				disposalBuffer.append("□返工");
			}
			// 加入空格、间隙
			disposalBuffer.append("     ");
			if (disposal.contains("2")) {
				disposalBuffer.append(new String(code, 1, 1) + "返修");
			} else {
				disposalBuffer.append("□返修");
			}
			disposalBuffer.append("     ");
			if (disposal.contains("3")) {
				disposalBuffer.append(new String(code, 1, 1) + "报废");
			} else {
				disposalBuffer.append("□报废");
			}
			disposalBuffer.append("     ");
			if (disposal.contains("4")) {
				disposalBuffer.append(new String(code, 1, 1) + "退货");
			} else {
				disposalBuffer.append("□退货");
			}
			disposalBuffer.append("     ");
			if (disposal.contains("5")) {
				disposalBuffer.append(new String(code, 1, 1) + "让步接收");
			} else {
				disposalBuffer.append("□让步接收");
			}
			disposalBuffer.append("     ");
			disposalBuffer.append("□无需处置(因误判，实际产品合格）");

			row16cell0.setCellValue(disposalBuffer.toString());
		}

		// 审理人员和日期
		Row row17 = sheet.getRow(17);
		row17.getCell(2)
				.setCellValue(map.get("trial_personnel") == null ? "" : "" + map.get("trial_personnel").toString());
		row17.getCell(3).setCellValue(map.get("trial_time") == null ? ""
				: "" + DateTimeUtil.date2dateStr(DateTimeUtil.dateStr2date(map.get("trial_time").toString())));
		row17.getCell(6).setCellValue(
				(map.get("approval_personnel") == null ? "" : "" + map.get("approval_personnel").toString()) + "    "
						+ (map.get("approval_time") == null ? ""
								: "" + DateTimeUtil
										.date2dateStr(DateTimeUtil.dateStr2date(map.get("approval_time").toString()))));
	}

    /**
     * 把一些枚举的值转换成字符串给前端展现
     *
     * @param map
     */
    private void intValueToStringValue(Map<String, Object> map) {
        if (map.get("disposal_method") != null) {
            StringBuffer descBuffer = new StringBuffer();
            //把处置方式转换成列表
            String disposal_method = map.get("disposal_method").toString().replace("[", "").replace("]", "");
            String[] split = disposal_method.split(",");
            for (String index : split) {
                //循环处置方式，然后取出描述
                for (DisposalMethodEnum disposalMethodEnum : DisposalMethodEnum.values()) {
                    if (disposalMethodEnum.getIndex() == Integer.parseInt(index.trim() + "")) {
                        descBuffer.append(disposalMethodEnum.getDesc()).append(",");
                    }
                }
            }
            if (descBuffer.length() > 0) {
                descBuffer.deleteCharAt(descBuffer.length() - 1);
            }
            map.put("disposal_method", descBuffer.toString());
        }
        //产品等级转成字符串
        if (map.get("product_level") != null) {
            Integer productLevel = (Integer) map.get("product_level");
            Dictionary dictGrade = cacheService.getDictionary(DictionaryEnum.CATEGORY_GRADE, productLevel);
            map.put("product_level", dictGrade.getValueStr());
        }
        //产品类型转成字符串
        if (map.get("product_type") != null) {
            Integer productType = Integer.valueOf(map.get("product_type").toString());
            for (ProductTypeEnum productTypeEnum : ProductTypeEnum.values()) {
                if (productTypeEnum.getIndex() == productType) {
                    map.put("product_type", productTypeEnum.getDesc());
                    break;
                }
            }

        }
    }
    
}

