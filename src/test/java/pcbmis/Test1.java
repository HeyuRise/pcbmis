package pcbmis;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.alibaba.fastjson.JSONObject;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.CheckItemRequest;
import com.pcbwx.pcbmis.bean.CheckTemplate;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.channel.PcbGrade;
import com.pcbwx.pcbmis.bean.channel.UmsUser;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.FileUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;
import com.pcbwx.pcbmis.utils.PcbmisUtil;

public class Test1 {

	public static void main(String[] args) throws Exception {
		System.out.println(PcbmisUtil.operate(new BigDecimal(0.75), new BigDecimal(0.0075), new BigDecimal(2.56)));
	}

	public static void excel() {
		try {
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet();
			Row row = sheet.createRow(0);
			Cell cell = row.createCell(0);
			cell.setCellValue(UnderLineIndex(new StringBuffer("审核员日期审核员日期审核员日期"), getFont(workbook)));
			OutputStream outputStream = new FileOutputStream(new File("e:/ccc.xlsx"));
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Font getFont(Workbook workbook) {
		Font font = workbook.createFont();
		font.setUnderline(Font.U_SINGLE); // 下划线
		return font;
	}

	// 加入下划线
	public static XSSFRichTextString UnderLineIndex(StringBuffer detail, Font font) {
		XSSFRichTextString richString = null;
		richString = new XSSFRichTextString(detail.toString());
		richString.applyFont(1, 6, font); // 下划线的起始位置，结束位置
		richString.applyFont(6, 11, font);
		return richString;
	}

	public static void test4() {
		PcbGrade gt = new PcbGrade();
		gt.setValueStr("aaaa");
		String json = JsonUtil.obj2json(gt);
		System.out.println(json);

		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/grade.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		PcbGrade grade = (PcbGrade) JsonUtil.json2obj(jsonStr, PcbGrade.class);
		System.out.println(grade.getInnerUpdateTime());
	}

	public static void testGrade() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/grades.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);

		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);

		List<PcbGrade> gradelist = JsonUtil.json2array(obj2, PcbGrade.class);
		for (PcbGrade gg : gradelist) {
			System.out.println(gg.getValueStr());
		}
	}

	public static void testUser() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/users.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);

		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);

		List<UmsUser> gradelist = JsonUtil.json2array(obj2, UmsUser.class);
		for (UmsUser gg : gradelist) {
			System.out.println(gg.getUsername());
		}
	}

	public static void test1() {
		CheckDetail checkDetail = new CheckDetail();
		List<SizeAndWarpDegree> sizeAndWarpDegrees = new ArrayList<SizeAndWarpDegree>();
		SizeAndWarpDegree sizeAndWarpDegree = new SizeAndWarpDegree();
		sizeAndWarpDegree.setBoardLong(55.55);
		sizeAndWarpDegree.setBoardNum("sssss");
		sizeAndWarpDegree.setBoardPly(20.11);
		sizeAndWarpDegree.setBoardWide(30.22);
		sizeAndWarpDegree.setJudge("合格");
		sizeAndWarpDegree.setWarpingDegree("0.75%");
		sizeAndWarpDegrees.add(sizeAndWarpDegree);
		sizeAndWarpDegree = new SizeAndWarpDegree();
		sizeAndWarpDegree.setBoardLong(55.55);
		sizeAndWarpDegree.setBoardNum("sssss");
		sizeAndWarpDegree.setBoardPly(20.11);
		sizeAndWarpDegree.setBoardWide(30.22);
		sizeAndWarpDegree.setJudge("合格");
		sizeAndWarpDegree.setWarpingDegree("0.75%");
		sizeAndWarpDegrees.add(sizeAndWarpDegree);
		checkDetail.setSizeAndWarpDegree(sizeAndWarpDegrees);
		String json = JsonUtil.obj2json(checkDetail);
		System.out.println(json);
	}

	@SuppressWarnings("unchecked")
	public static void test2() {
		CheckTemplate template = new CheckTemplate();

		CheckItemRequest burrs = new CheckItemRequest();
		burrs.setRequire("aaaaaaaaaa");
		template.setBurrs(burrs);

		Map<String, Object> fieldList = null;
		try {
			fieldList = DataUtil.fetchField2list(template);
		} catch (NoSuchMethodException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (fieldList == null) {
			return;
		}
		// for (Map.Entry<String, Object> entry : fieldList.entrySet()) {
		// CheckItem value = (CheckItem)entry.getValue();
		// if (value == null) {
		// System.out.println("" + entry.getKey() + "," + value);
		// continue;
		// }
		// System.out.println("" + entry.getKey() + "," + value.getRequire());
		// }

		String obj2json = JsonUtil.obj2json(template);
		System.out.println(obj2json);
		Map<String, Object> fieldMap = (Map<String, Object>) JSONObject.parse(obj2json);
		if (fieldMap == null) {
			return;
		}
		for (String string : fieldMap.keySet()) {
			System.out.println(string + fieldMap.get(string));
			if (fieldMap.get(string) != null) {

				String s = (String) fieldMap.get(string);
				CheckItemRequest checkItem = (CheckItemRequest) JsonUtil.json2obj(s, CheckItemRequest.class);
				System.out.println(checkItem.getRequire());
			}
		}
		// for (Map.Entry<String, CheckItem> entry : fieldMap.entrySet()) {
		//// CheckItem value = entry.getValue();
		// if (entry.getValue() == null) {
		// System.out.println("" + entry.getKey() + "," + entry.getValue());
		// continue;
		// }
		// System.out.println("" + entry.getKey() + ",>>" +
		// entry.getValue().getRequire());
		// }
	}
}
