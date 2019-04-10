package com.pcbwx.pcbmis.utils;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFShape;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

/**
 * Created with IntelliJ IDEA.
 * Author: Duelsol
 * Date: 14-7-18
 * Time: 下午4:05
 */
public class POIUtil {

//    private static Logger logger = Logger.getLogger(POIUtil.class);

    private POIUtil() {}

    /**
     * 复制工作表
     * 此方法主要用于复制2个不同HSSFWorkbook间的工作表
     */
    public static void copySheet(HSSFWorkbook fromWorkbook, HSSFWorkbook toWorkbook, int fromSheetIndex, int toSheetIndex) {
        toWorkbook.setSheetName(toSheetIndex, fromWorkbook.getSheetName(fromSheetIndex));
        HSSFSheet fromSheet = fromWorkbook.getSheetAt(fromSheetIndex);
        for (int i = fromSheet.getFirstRowNum(); i <= fromSheet.getLastRowNum(); i++) {
            copyRows(fromWorkbook, toWorkbook, fromSheetIndex, toSheetIndex, i, i, i);
        }
    }

    /**
     * 复制行
     * 此方法主要用于复制2个不同HSSFWorkbook间的行
     */
    public static void copyRows(HSSFWorkbook fromWorkbook, HSSFWorkbook toWorkbook, int fromSheetIndex, int toSheetIndex, int startRow, int endRow, int position) {
        HSSFSheet fromSheet = fromWorkbook.getSheetAt(fromSheetIndex);
        HSSFSheet toSheet = toWorkbook.getSheetAt(toSheetIndex);
        int i;
        int j;

        if ((startRow == -1) || (endRow == -1)) {
            return;
        }

        List<CellRangeAddress> oldRanges = new ArrayList<CellRangeAddress>();
        for (i = 0; i < fromSheet.getNumMergedRegions(); i++) {
            oldRanges.add(fromSheet.getMergedRegion(i));
        }

        // 拷贝合并的单元格。原理：复制当前合并单元格后，原位置的格式会移动到新位置，需在原位置生成旧格式
        for (CellRangeAddress oldRange : oldRanges) {
            CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
                    oldRange.getFirstColumn(), oldRange.getLastColumn());

            if (oldRange.getFirstRow() >= startRow && oldRange.getLastRow() <= endRow) {
                int targetRowFrom = oldRange.getFirstRow() - startRow + position;
                int targetRowTo = oldRange.getLastRow() - startRow + position;
                oldRange.setFirstRow(targetRowFrom);
                oldRange.setLastRow(targetRowTo);
                toSheet.addMergedRegion(oldRange);
                fromSheet.addMergedRegion(newRange);
            }
        }

        // 设置列宽
        for (i = startRow; i <= endRow; i++) {
            HSSFRow fromRow = fromSheet.getRow(i);
            if (fromRow != null) {
                for (j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                    toSheet.setColumnWidth(j, fromSheet.getColumnWidth(j));
                    toSheet.setColumnHidden(j, false);
                }
                break;
            }
        }

        // 拷贝行并填充数据
        for (; i <= endRow; i++) {
            HSSFRow fromRow = fromSheet.getRow(i);
            if (fromRow == null) {
                continue;
            }
            HSSFRow toRow = toSheet.createRow(i - startRow + position);
            toRow.setHeight(fromRow.getHeight());
            for (j = fromRow.getFirstCellNum(); j <= fromRow.getPhysicalNumberOfCells(); j++) {
                HSSFCell fromCell = fromRow.getCell(j);
                if (fromCell == null) {
                    continue;
                }
                HSSFCell toCell = toRow.createCell(j);
                HSSFCellStyle toStyle = toWorkbook.createCellStyle();
                copyCellStyle(fromWorkbook, toWorkbook, fromCell.getCellStyle(), toStyle);
                toCell.setCellStyle(toStyle);
                int cType = fromCell.getCellType();
                toCell.setCellType(cType);
                switch (cType) {
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        toCell.setCellValue(fromCell.getBooleanCellValue());
                        // System.out.println("--------TYPE_BOOLEAN:" +
                        // targetCell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        toCell.setCellErrorValue(fromCell.getErrorCellValue());
                        // System.out.println("--------TYPE_ERROR:" +
                        // targetCell.getErrorCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        toCell.setCellFormula(parseFormula(fromCell.getCellFormula()));
                        // System.out.println("--------TYPE_FORMULA:" +
                        // targetCell.getCellFormula());
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        toCell.setCellValue(fromCell.getNumericCellValue());
                        // System.out.println("--------TYPE_NUMERIC:" +
                        // targetCell.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        toCell.setCellValue(fromCell.getRichStringCellValue());
                        // System.out.println("--------TYPE_STRING:" + i +
                        // targetCell.getRichStringCellValue());
                        break;
                }
            }
        }
    }

    /**
     * 复制行
     * 如果是同一个HSSFWorkbook中的行请用此方法
     */
    public static void copyRows(Workbook workbook, int fromSheetIndex, int toSheetIndex, int startRow, int endRow, Integer position) {
        XSSFSheet fromSheet = (XSSFSheet) workbook.getSheetAt(fromSheetIndex);
        XSSFSheet toSheet = (XSSFSheet) workbook.getSheetAt(toSheetIndex);
        int i;
        int j;

        if ((startRow == -1) || (endRow == -1)) {
            return;
        }

        List<CellRangeAddress> oldRanges = new ArrayList<CellRangeAddress>();
        for (i = 0; i < fromSheet.getNumMergedRegions(); i++) {
            oldRanges.add(fromSheet.getMergedRegion(i));
        }

        // 拷贝合并的单元格。原理：复制当前合并单元格后，原位置的格式会移动到新位置，需在原位置生成旧格式
        for (CellRangeAddress oldRange : oldRanges) {
//            CellRangeAddress newRange = new CellRangeAddress(oldRange.getFirstRow(), oldRange.getLastRow(),
//                    oldRange.getFirstColumn(), oldRange.getLastColumn());

            if (oldRange.getFirstRow() >= startRow && oldRange.getLastRow() <= endRow) {
                int targetRowFrom = oldRange.getFirstRow() - startRow + position;
                int targetRowTo = oldRange.getLastRow() - startRow + position;
                oldRange.setFirstRow(targetRowFrom);
                oldRange.setLastRow(targetRowTo);
                toSheet.addMergedRegion(oldRange);
                //fromSheet.addMergedRegion(newRange);
            }
        }

        // 设置列宽
        for (i = startRow; i <= endRow; i++) {
            XSSFRow fromRow = fromSheet.getRow(i);
            if (fromRow != null) {
                for (j = fromRow.getLastCellNum(); j >= fromRow.getFirstCellNum(); j--) {
                    toSheet.setColumnWidth(j, fromSheet.getColumnWidth(j));
                    toSheet.setColumnHidden(j, false);
                }
                break;
            }
        }

        // 拷贝行并填充数据
        for (; i <= endRow; i++) {
        	if (i < 0) {
				continue;
			}
        	XSSFRow fromRow = fromSheet.getRow(i);
            if (fromRow == null) {
                continue;
            }
            XSSFRow toRow = toSheet.createRow(i - startRow + position);
            toRow.setHeight(fromRow.getHeight());
            for (j = fromRow.getFirstCellNum(); j <= fromRow.getPhysicalNumberOfCells(); j++) {
            	if (j < 0) {
    				continue;
    			}
            	XSSFCell fromCell = fromRow.getCell(j);
                if (fromCell == null) {
                    continue;
                }
                XSSFCell toCell = toRow.createCell(j);
                toCell.setCellStyle(fromCell.getCellStyle());
                int cType = fromCell.getCellType();
                toCell.setCellType(cType);
                switch (cType) {
                    case HSSFCell.CELL_TYPE_BOOLEAN:
                        toCell.setCellValue(fromCell.getBooleanCellValue());
                        // System.out.println("--------TYPE_BOOLEAN:" +
                        // targetCell.getBooleanCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_ERROR:
                        toCell.setCellErrorValue(fromCell.getErrorCellValue());
                        // System.out.println("--------TYPE_ERROR:" +
                        // targetCell.getErrorCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_FORMULA:
                        toCell.setCellFormula(parseFormula(fromCell.getCellFormula()));
                        // System.out.println("--------TYPE_FORMULA:" +
                        // targetCell.getCellFormula());
                        break;
                    case HSSFCell.CELL_TYPE_NUMERIC:
                        toCell.setCellValue(fromCell.getNumericCellValue());
                        // System.out.println("--------TYPE_NUMERIC:" +
                        // targetCell.getNumericCellValue());
                        break;
                    case HSSFCell.CELL_TYPE_STRING:
                        toCell.setCellValue(fromCell.getRichStringCellValue());
                        // System.out.println("--------TYPE_STRING:" + i +
                        // targetCell.getRichStringCellValue());
                        break;
                }
            }
        }
    }

    /**
     * 复制单元格样式
     * 此方法主要用于复制2个不同HSSFWorkbook间的单元格样式
     */
    public static void copyCellStyle(HSSFWorkbook fromWorkbook, HSSFWorkbook toWorkbook, HSSFCellStyle fromStyle, HSSFCellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());

        // 边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        // 字体
        HSSFFont tofont = toWorkbook.createFont();
        copyFont(fromStyle.getFont(fromWorkbook), tofont);
        toStyle.setFont(tofont);

        // 背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());
    }

    /**
     * 复制字体
     * 此方法主要用于复制2个不同HSSFWorkbook间的字体
     */
    public static void copyFont(HSSFFont fromFont, HSSFFont toFont) {
        toFont.setBoldweight(fromFont.getBoldweight());
        toFont.setCharSet(fromFont.getCharSet());
        toFont.setColor(fromFont.getColor());
        toFont.setFontHeight(fromFont.getFontHeight());
        toFont.setFontHeightInPoints(fromFont.getFontHeightInPoints());
        toFont.setFontName(fromFont.getFontName());
        toFont.setItalic(fromFont.getItalic());
        toFont.setStrikeout(fromFont.getStrikeout());
        toFont.setTypeOffset(fromFont.getTypeOffset());
        toFont.setUnderline(fromFont.getUnderline());
    }

    private static String parseFormula(String pPOIFormula) {
        final String cstReplaceString = "ATTR(semiVolatile)"; //$NON-NLS-1$
        StringBuffer result;
        int index;

        result = new StringBuffer();
        index = pPOIFormula.indexOf(cstReplaceString);
        if (index >= 0) {
            result.append(pPOIFormula.substring(0, index));
            result.append(pPOIFormula.substring(index + cstReplaceString.length()));
        } else {
            result.append(pPOIFormula);
        }

        return result.toString();
    }

    /**
     * 根据单元格信息动态插入图片，如果单元格有文字，图片的位置会在文字之后，如果同样的位置已有图片则会往下插入
     *
     * @param workbook Excel
     * @param cell 单元格信息
     * @param inputStream 图片输入流
     * @param scale 图片缩放，传入null表示原始尺寸，其余表示图片高于行高的比（例如传入1.5，表示该图片占1.5个行高）
     */
    public static void createPicture(Workbook workbook, HSSFCell cell, InputStream inputStream, Double scale) {
        ByteArrayOutputStream byteArrayOut = null;
        try {
            byteArrayOut = new ByteArrayOutputStream();
            BufferedImage bufferImg = ImageIO.read(inputStream);
            ImageIO.write(bufferImg, "png", byteArrayOut);

            if (cell != null && (cell.getCellType() == HSSFCell.CELL_TYPE_STRING || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)) {
                HSSFSheet sheet = cell.getSheet();
                HSSFRow row = cell.getRow();
                HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
                String cellValue = cell.getStringCellValue().contains("#{") ? cell.getStringCellValue().split("#\\{")[0] : cell.getStringCellValue();

                int i = row.getRowNum();
                short j = (short) cell.getColumnIndex();

                int colWidth = sheet.getColumnWidth(cell.getColumnIndex()) / 32; // 单元格像素宽度
                int wordWidth = cellValue.getBytes("GBK").length == 0 ? 0 : ((cellValue.getBytes("GBK").length + 2) * 8); // 单元格文本大致像素宽度
                double pert = new BigDecimal(wordWidth).divide(new BigDecimal(colWidth), 10, BigDecimal.ROUND_HALF_UP).doubleValue();

                int dx1 = new BigDecimal(pert * 1023).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
                int dy1 = 0;

                List<HSSFShape> shapes = sheet.getDrawingPatriarch().getChildren();
                for (HSSFShape shape : shapes) {
                    HSSFClientAnchor anchor = (HSSFClientAnchor) shape.getAnchor();
                    if (anchor.getRow1() == i && anchor.getCol1() == j && anchor.getDx1() == dx1 && anchor.getDy1() == dy1) {
                        if (anchor.getDy2() >= 255) {
                            i = anchor.getRow2() + 1;
                            dy1 = 0;
                        } else {
                            i = anchor.getRow2();
                            dy1 = anchor.getDy2() + 1;
                        }
                    }
                }

                HSSFClientAnchor anchor = new HSSFClientAnchor(dx1, dy1, 0, 0, j, i, j, i + 1); // 由于用了getPreferredSize所以dx2,dy2无效
                anchor.setAnchorType(3);
                if (scale == null) {
                    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG)).getPreferredSize(1.0);
                } else {
                    double zoom = new BigDecimal(row.getHeight() / 15).divide(new BigDecimal(bufferImg.getHeight()), 10, BigDecimal.ROUND_HALF_UP).doubleValue(); // 行高像素与图片高度像素比例
                    patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_PNG)).getPreferredSize(zoom * scale);
                }
            }
        } catch (IOException ioe) {
            //logger.error("插入图片失败", ioe);
        } finally {
            if (byteArrayOut != null) {
                try {
                    byteArrayOut.close();
                } catch (IOException e) {
                    //logger.error("关闭ByteArrayOutputStream失败", e);
                }
            }
        }
    }
    
    public static void copyRows(int startRow, int endRow, int pPosition, XSSFSheet currentSheet) {  
        int pStartRow = startRow;  
        int pEndRow = endRow;  
        int targetRowFrom;  
        int targetRowTo;  
        int columnCount;  
        CellRangeAddress region = null;  
        int i;  
        int j;  
        if (pStartRow == -1 || pEndRow == -1) {  
            return;  
        }  
        for (i = 0; i < currentSheet.getNumMergedRegions(); i++) {  
            region = currentSheet.getMergedRegion(i);  
            if ((region.getFirstRow() >= pStartRow)  
                    && (region.getLastRow() <= pEndRow)) {  
                targetRowFrom = region.getFirstRow() - pStartRow + pPosition;  
                targetRowTo = region.getLastRow() - pStartRow + pPosition;  
                CellRangeAddress newRegion = region.copy();  
                newRegion.setFirstRow(targetRowFrom);  
                newRegion.setFirstColumn(region.getFirstColumn());  
                newRegion.setLastRow(targetRowTo);  
                newRegion.setLastColumn(region.getLastColumn());  
                currentSheet.addMergedRegion(newRegion);  
            }  
        }  
        for (i = pStartRow; i <= pEndRow; i++) {  
            XSSFRow sourceRow = currentSheet.getRow(i);  
            columnCount = sourceRow.getLastCellNum();  
            if (sourceRow != null) {  
            	XSSFRow newRow = currentSheet.createRow(pPosition - pStartRow  
                        + i);  
                newRow.setHeight(sourceRow.getHeight());  
                for (j = 0; j < columnCount; j++) {  
                	XSSFCell templateCell = sourceRow.getCell(j);  
                    if (templateCell != null) {  
                        XSSFCell newCell = newRow.createCell(j);  
                        copyCell(templateCell, newCell);  
                    }  
                }  
            }  
        }  
    } 
    
    private static void copyCell(XSSFCell srcCell, XSSFCell distCell) {  
        distCell.setCellStyle(srcCell.getCellStyle());  
        if (srcCell.getCellComment() != null) {  
            distCell.setCellComment(srcCell.getCellComment());  
        }  
        int srcCellType = srcCell.getCellType();  
        distCell.setCellType(srcCellType);  
        if (srcCellType == HSSFCell.CELL_TYPE_NUMERIC) {  
            if (HSSFDateUtil.isCellDateFormatted(srcCell)) {  
                distCell.setCellValue(srcCell.getDateCellValue());  
            } else {  
                distCell.setCellValue(srcCell.getNumericCellValue());  
            }  
        } else if (srcCellType == HSSFCell.CELL_TYPE_STRING) {  
            distCell.setCellValue(srcCell.getRichStringCellValue());  
        } else if (srcCellType == HSSFCell.CELL_TYPE_BLANK) {  
            // nothing21  
        } else if (srcCellType == HSSFCell.CELL_TYPE_BOOLEAN) {  
            distCell.setCellValue(srcCell.getBooleanCellValue());  
        } else if (srcCellType == HSSFCell.CELL_TYPE_ERROR) {  
            distCell.setCellErrorValue(srcCell.getErrorCellValue());  
        } else if (srcCellType == HSSFCell.CELL_TYPE_FORMULA) {  
            distCell.setCellFormula(srcCell.getCellFormula());  
        } else { // nothing29  
  
        }  
    } 
    
    // 新建行
    public static void createRow(XSSFSheet sheet, Integer rowIndex) {  
        if (sheet.getRow(rowIndex) != null) {  
            int lastRowNo = sheet.getLastRowNum();  
            sheet.shiftRows(rowIndex, lastRowNo, 1);  
        }  
        sheet.createRow(rowIndex);  
    }   
    
    public static void operate(Workbook workbook, Sheet sheet, Integer row, Integer cell, InputStream is, int type) throws IOException{
    	byte[] bytes = IOUtils.toByteArray(is);  
    	int pictureIdx = workbook.addPicture(bytes, type);  
    	CreationHelper helper = workbook.getCreationHelper();  
    	Drawing drawing = sheet.createDrawingPatriarch();  
    	ClientAnchor anchor = helper.createClientAnchor();  
    	// 图片插入坐标  
    	anchor.setRow1(row);  
    	anchor.setCol1(cell);  
    	// 插入图片  
    	Picture pict = drawing.createPicture(anchor, pictureIdx);  
    	pict.resize();
    } 
}
