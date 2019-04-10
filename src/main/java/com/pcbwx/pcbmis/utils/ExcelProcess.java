package com.pcbwx.pcbmis.utils;

import org.apache.poi.ss.usermodel.Row;

import com.pcbwx.pcbmis.bean.CheckInfo;

public class ExcelProcess {
	
	public static void writeCheckInfo(CheckInfo checkInfo , Row row){
		// 流水号
		row.createCell(0).setCellValue(checkInfo.getSerialNumber());
		// 工单号
		row.createCell(1).setCellValue(checkInfo.getOrderNum());
		// 客户
		row.createCell(2).setCellValue(checkInfo.getGuestName());
		// 板名
		row.createCell(3).setCellValue(checkInfo.getBoardName());
		// 电子/科技
		row.createCell(4).setCellValue(checkInfo.getCompanyName());
		// 生产厂家
		row.createCell(5).setCellValue(checkInfo.getFactoryName());
		// 生产数量set
		row.createCell(6).setCellValue(checkInfo.getProductionNumSet() == null ? 0 : checkInfo.getProductionNumSet());
		// 数量
		row.createCell(7).setCellValue(checkInfo.getProductionNumPcs() == null ? 0 : checkInfo.getProductionNumPcs());
		// 等级
		row.createCell(8).setCellValue(checkInfo.getCategoryGrade());
		// 金相要求
		row.createCell(9).setCellValue(checkInfo.getMetallographyRequire());
		// 投产日期
		row.createCell(10).setCellValue(checkInfo.getProductDate());
		// 来料数量（PCS)
		row.createCell(11).setCellValue(checkInfo.getAmountCheckoutPcs() == null ? 0 : checkInfo.getAmountCheckoutPcs());
		// 抽检数量（PCS)
		row.createCell(12).setCellValue(checkInfo.getSpotCheckNumPcs() == null ? 0 : checkInfo.getSpotCheckNumPcs());
		// 收板日期
		row.createCell(13).setCellValue(checkInfo.getReceviceDate());
		// 收板时间
		row.createCell(14).setCellValue(checkInfo.getReceviceTime());
		// 检完日期
		row.createCell(15).setCellValue(checkInfo.getCheckDate());
		// 是否异常
		row.createCell(16).setCellValue(checkInfo.getIsError());
		// 异常数量（PCS)
		if (checkInfo.getErrorNum() != null) {
			row.createCell(17).setCellValue(checkInfo.getErrorNum());
		}
		// 异常问题
		row.createCell(18).setCellValue(checkInfo.getErrorQuestion());
		// 出货日期
		row.createCell(19).setCellValue(checkInfo.getDispatchDate());
		// 检验员
		row.createCell(20).setCellValue(checkInfo.getInspector());
		// 编辑报告人员
		row.createCell(21).setCellValue(checkInfo.getReportMakerName());
		// 备注
		row.createCell(22).setCellValue(checkInfo.getProductNote());
		// 错检,漏检
		row.createCell(23).setCellValue(checkInfo.getMistakeNote());
		// 报告错误
		row.createCell(24).setCellValue(checkInfo.getReportError());
		// 生产日期
		row.createCell(25).setCellValue(checkInfo.getProductDate());
		
		double boardLong;
		double boardWide;
		if (checkInfo.getBoardLong() == null) {
			boardLong = 0.00;
		} else {
			boardLong = checkInfo.getBoardLong().doubleValue();
		}
		if (checkInfo.getBoardWide() == null) {
			boardWide = 0.00;
		} else {
			boardWide = checkInfo.getBoardWide().doubleValue();
		}
		String boardPly = "";
		if (checkInfo.getBoardPly() != null) {
			boardPly = checkInfo.getBoardPly();
		}
		// 长
		row.createCell(26).setCellValue(boardLong);
		// 宽
		row.createCell(27).setCellValue(boardWide);
		// 厚
		row.createCell(28).setCellValue(boardPly);
		// 规格
		row.createCell(29).setCellValue(boardLong + "*" + boardWide + "*" + boardPly);
		// 表面处理
		row.createCell(30).setCellValue(checkInfo.getSurfaceProcess());
		// 拼板方式
		row.createCell(31).setCellValue(checkInfo.getJoinBoardWay());
		// 出货方式
		row.createCell(32).setCellValue(checkInfo.getDeliveryMode());
		row.createCell(33).setCellValue(checkInfo.getReceiveType());
		// 检验内容
		row.createCell(34).setCellValue(checkInfo.getCheckContent());
	}
	
}
