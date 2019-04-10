package com.pcbwx.pcbmis.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 王海龙
 * @date 2013-2-22
 */
public class FileUtil {
    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);
    
	public String dir = "";
	public String temp = "";

	/**
	 * 从硬盘文件中读取数据
	 * @param filename 文件名(包含路径)
	 * @return 文件内容(二进制)
	 */
	public static byte[] readFile(String filename) {
    	File file = new File(filename);

        if(!file.exists()||file.isDirectory()) {
            return null;
        }

        FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			//logger.error(e.getMessage(), e);
			return null;
		}
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		
		try {
			do {
				byte[] data = new byte[1024];
				int size = fis.read(data);
				if (-1 == size) {
					break;
				}
				bout.write(data, 0, size); 		
			} while (true);
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
			bout = null;
		}
		
		try {
			fis.close();
		} catch (IOException e) {
//			logger.error(e.getMessage(), e);
		}
		
		if (bout == null) {
			return null;			
		}
		return bout.toByteArray();
	}

	public static String readFileString(String filename) {
		return readFileString(filename, "UTF-8");
	}
	public static String readFileString(String filename, String charset) {
		byte[] data = FileUtil.readFile(filename);
		String dataStr = null;
		try {
			dataStr = new String(data, charset);
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
		return dataStr;
	}
	/**
	 * 写入硬盘文件信息
	 * @param filename 文件名(包含路径)
	 * @param data 文件的二进制数据
	 * @return
	 */
	public static int writeFile(String filename, byte[] data) {		
		FileOutputStream out = null;
		try {
			out = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			//logger.error(e.getMessage(), e);
			return 1;
		}

		int writeBytes = data.length;
        PrintStream p = new PrintStream(out);
        try {
			p.write(data);
		} catch (IOException e) {
			//logger.error(e.getMessage(), e);
			writeBytes = 0;
		}
        
        try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage(), e);
		}
        
		return writeBytes;
	}
	/**
	 * 搜索目录下的所有文件(包括子目录)
	 * @param dir 目录路径
	 * @return 文件名列表
	 */
	public String[] serachFiles(String dir) {
        File root = new File(dir);

        File[] filesOrDirs = root.listFiles();

        String[] result = new String[10];

        for (int i = 0; i < filesOrDirs.length; i++) {
            if (filesOrDirs[i].isDirectory()) {
                serachFiles(filesOrDirs[i].getAbsolutePath());
            } else {
            	try {
            		result[i] = filesOrDirs[i].getName();
            	} catch (Exception e) {
        			continue;
        		}
                temp += filesOrDirs[i].getName() + ",";
            }
        }
        
        return temp.split(",");
    }

	public static boolean exist(String filename) {
		File targetFile = new File(filename); 
		return targetFile.exists();
	}
	public static boolean delete(String filename) {
		File targetFile = new File(filename); 
		return targetFile.delete();	
	}
	
	/**
	 * 设置Excel列表头
	 * @param sheet 
	 * @param columnName 列名的数组
	 * @param cellStyle 单元格样式
	 * @param rowNum 表头所在的行号(0是第一行)
	 */
	public static void setColumnTitle(Sheet sheet, String[] columnName, CellStyle cellStyle, int rowNum) {
		Row row = sheet.createRow(rowNum);
		Cell cell;
		for (int i = 0; i < columnName.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(columnName[i]);
			cell.setCellStyle(cellStyle);
		}
	}
	
	/**
	 * 生成标题行样式
	 * @param workbook
	 * @return
	 */
	public static CellStyle generateColumnTitleStyle(Workbook workbook){
		//生成一个样式
		CellStyle columnTitlestyle = workbook.createCellStyle();
		//设置样式
		columnTitlestyle.setBorderBottom(XSSFCellStyle.BORDER_THIN);//下边框
		columnTitlestyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setBorderRight(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setBorderTop(XSSFCellStyle.BORDER_THIN);
		columnTitlestyle.setAlignment(XSSFCellStyle.ALIGN_CENTER);
		columnTitlestyle.setFillForegroundColor(HSSFColor.YELLOW.index);
		columnTitlestyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		columnTitlestyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
		
		//生成一个字体
		Font font = workbook.createFont();
		font.setFontName("Arial");
		font.setFontHeightInPoints((short)11);
		//把字体应用到当前的样式
		columnTitlestyle.setFont(font);
		return columnTitlestyle;
	}
	
	/**
	 * 设置列宽
	 * @param sheet
	 * @param width
	 */
	public static void setColumnWidth(Sheet sheet, int width){
		int columnNum = sheet.getRow(1).getPhysicalNumberOfCells();
		for(int j=0;j<columnNum;j++){
			sheet.setColumnWidth(j, width);
		}
	}
	
	/**
	 * 生成样式
	 * @param workbook
	 * @param alignment
	 * @param verticalAlignment
	 * @param fontName
	 * @param fontHeightInPoints
	 * @param boldweight
	 * @param backGroudColor
	 * @param border
	 * @param fontColor
	 * @return
	 */
	public static CellStyle createCellStyle(Workbook workbook, short alignment, short verticalAlignment, String fontName,
			short fontHeightInPoints, short boldweight, short backGroudColor, short border, short fontColor) {
		CellStyle style = workbook.createCellStyle();
		style.setAlignment(alignment);
		style.setVerticalAlignment(verticalAlignment);
		style.setBorderBottom(border);
		style.setBorderLeft(border);
		style.setBorderRight(border);
		style.setBorderTop(border);
		style.setFillForegroundColor(backGroudColor);
		style.setFillPattern(CellStyle.SOLID_FOREGROUND);
		Font font = workbook.createFont();
		font.setFontName(fontName);
		font.setFontHeightInPoints(fontHeightInPoints);
		font.setBoldweight(boldweight);
		font.setColor(fontColor);
		style.setFont(font);
		return style;
	}
	
	public static void main(String[] args) {
//		byte[] data = GFile.readFile("d:/test.jpg");
//		GFile.writeFile("d:/test_out.jpg", data);
		
		byte[] data = FileUtil.readFile("d:/new.txt");
		String dataStr = new String(data);
		System.out.println(dataStr);
		
//		FileUtil file = new FileUtil();
//        String[] files = file.serachFiles("e:/script");//("F:/movies");
//        for (int i = 0; i < files.length; i++) {
//            System.out.println("files[" + i + "]:" + files[i]);
//        }
	}
}
