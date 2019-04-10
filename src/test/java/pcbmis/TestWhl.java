package pcbmis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.pcbwx.pcbmis.bean.CheckDetail;
import com.pcbwx.pcbmis.bean.CheckItemRequest;
import com.pcbwx.pcbmis.bean.CheckTemplate;
import com.pcbwx.pcbmis.bean.SizeAndWarpDegree;
import com.pcbwx.pcbmis.bean.channel.OissInStorageOrder;
import com.pcbwx.pcbmis.bean.channel.PcbBasemAterial;
import com.pcbwx.pcbmis.bean.channel.PcbBoardPlyTolerance;
import com.pcbwx.pcbmis.bean.channel.PcbCompany;
import com.pcbwx.pcbmis.bean.channel.PcbCraftInfo;
import com.pcbwx.pcbmis.bean.channel.PcbFactory;
import com.pcbwx.pcbmis.bean.channel.PcbFrameTolerances;
import com.pcbwx.pcbmis.bean.channel.PcbGrade;
import com.pcbwx.pcbmis.bean.channel.PcbJoinBoardOrder;
import com.pcbwx.pcbmis.bean.channel.PcbJoinBoardWay;
import com.pcbwx.pcbmis.bean.channel.PcbPreventSmtColour;
import com.pcbwx.pcbmis.bean.channel.PcbSurfaceProcess;
import com.pcbwx.pcbmis.bean.channel.UmsUser;
import com.pcbwx.pcbmis.model.PcbInStorageOrder;
import com.pcbwx.pcbmis.model.WxtbUser;
import com.pcbwx.pcbmis.utils.CommonUtil;
import com.pcbwx.pcbmis.utils.DataUtil;
import com.pcbwx.pcbmis.utils.FileUtil;
import com.pcbwx.pcbmis.utils.JsonUtil;

public class TestWhl {
	public static void main(String[] args) {
//		List<PcbGrade> grades = new ArrayList<PcbGrade>();
//		PcbGrade grade = new PcbGrade();
//		grade.setInnerCreateTime("111111");
//		grade.setValueStr("sfdsgfdg");
//		grades.add(grade);
//		
//		grade = new PcbGrade();
//		grade.setInnerCreateTime("222");
//		grade.setValueStr("hgjkl");
//		grades.add(grade);
//		
//		String json = JsonUtil.obj2json(grades);
//		System.out.println(json);
//		
//		List<PcbGrade> gradelist = JsonUtil.json2array(json, PcbGrade.class);
//		for (PcbGrade gg : gradelist) {
//			System.out.println(gg.getValueStr());
//		}
		
//		testGrade();
//		testUser();
//		testCompany();
//		testFactory();
//		testJoinBoardWay();
//		testSurfaceProcess();
//		testInStorageOrder();
//		testJoinBoardRequire();
//		testJoinBoardOrder();
//		testBoardPlyTolerance();
//		testCraft();
//		testPreventSmtColour();
//		testBasemAterial();
//		testFrameTolerance();
		test5();
	}
//	public static void test4(){
//		PcbGrade gt = new PcbGrade();
//		gt.setValueStr("aaaa");
//		String json = JsonUtil.obj2json(gt);
//		System.out.println(json);
//		
//		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/grade.txt";
//		final String jsonStr = FileUtil.readFileString(jsonFile);
//		System.out.println(jsonStr);
//		PcbGrade grade = (PcbGrade) JsonUtil.json2obj(jsonStr, PcbGrade.class);
//		System.out.println(grade.getInnerUpdateTime());
//	}
	public static void test5() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/in.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		OissInStorageOrder data = (OissInStorageOrder) JsonUtil.json2obj(jsonStr, OissInStorageOrder.class);
		System.out.println(data.getInnerUpdateTime());
		
		PcbInStorageOrder record = null;
		try {
			record = (PcbInStorageOrder) CommonUtil.fetchMapFields(data,
					PcbInStorageOrder.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(record.getInnerUpdateTime());
	}
	public static void testGrade(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/grades.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbGrade> datalist = JsonUtil.json2array(obj2, PcbGrade.class);
		for (PcbGrade data : datalist) {
			System.out.println(data.getValueStr());
		}
	}
	public static void testUser(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/users.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<UmsUser> datalist = JsonUtil.json2array(obj2, UmsUser.class);
		for (UmsUser data : datalist) {
			System.out.println(data.getUsername());
			try {
				WxtbUser record = (WxtbUser) CommonUtil.fetchMapFields(data, WxtbUser.class);
				System.out.println(">>" + record.getUsername());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public static void testCompany(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/companies.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbCompany> datalist = JsonUtil.json2array(obj2, PcbCompany.class);
		for (PcbCompany data : datalist) {
			System.out.println(data.getCompanyName());
		}
	}
	public static void testFactory(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/factories.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbFactory> datalist = JsonUtil.json2array(obj2, PcbFactory.class);
		for (PcbFactory data : datalist) {
			System.out.println(data.getFactoryName());
		}
	}
	public static void testJoinBoardWay(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/join_board_ways.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbJoinBoardWay> datalist = JsonUtil.json2array(obj2, PcbJoinBoardWay.class);
		for (PcbJoinBoardWay data : datalist) {
			System.out.println(data.getJoinName());
		}
	}
	public static void testSurfaceProcess(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/surface_processes.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbSurfaceProcess> datalist = JsonUtil.json2array(obj2, PcbSurfaceProcess.class);
		for (PcbSurfaceProcess data : datalist) {
			System.out.println(data.getProcessName());
		}
	}
	public static void testInStorageOrder(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/in_storage_orders.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<OissInStorageOrder> datalist = JsonUtil.json2array(obj2, OissInStorageOrder.class);
		for (OissInStorageOrder data : datalist) {
			System.out.println(data.getOrderNumber());
		}
	}
	public static void testJoinBoardRequire(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/join_board_requires.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
//		List<PcbJoinBoardRequire> datalist = JsonUtil.json2array(obj2, PcbJoinBoardRequire.class);
//		for (PcbJoinBoardRequire data : datalist) {
//			System.out.println(data.getValueStr());
//		}
	}
	public static void testJoinBoardOrder(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/join_board_order.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbJoinBoardOrder> datalist = JsonUtil.json2array(obj2, PcbJoinBoardOrder.class);
		for (PcbJoinBoardOrder data : datalist) {
			System.out.println(data.getJoinBoardCode());
		}
	}
	public static void testBoardPlyTolerance(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/board_ply_tolerances.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbBoardPlyTolerance> datalist = JsonUtil.json2array(obj2, PcbBoardPlyTolerance.class);
		for (PcbBoardPlyTolerance data : datalist) {
			System.out.println(data.getToleranceName());
		}
	}
	public static void testCraft(){
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/crafts.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbCraftInfo> datalist = JsonUtil.json2array(obj2, PcbCraftInfo.class);
		for (PcbCraftInfo data : datalist) {
			System.out.println(data.getCraftName());
		}
	}
	public static void testPreventSmtColour() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/PreventSmtColour.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbPreventSmtColour> datalist = JsonUtil.json2array(obj2, PcbPreventSmtColour.class);
		for (PcbPreventSmtColour data : datalist) {
			System.out.println(data.getValueStr());
		}
	}
	public static void testBasemAterial() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/BasemAterial.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbBasemAterial> datalist = JsonUtil.json2array(obj2, PcbBasemAterial.class);
		for (PcbBasemAterial data : datalist) {
			System.out.println(data.getBasemAterialName());
		}
	}
	public static void testFrameTolerance() {
		String jsonFile = "D:/workDoc/pcb检验检测系统/数据结构/接口json/FrameTolerance.txt";
		final String jsonStr = FileUtil.readFileString(jsonFile);
		System.out.println(jsonStr);
		
		JSONObject obj = JSONObject.parseObject(jsonStr);
		String obj2 = obj.getString("rows");
		System.out.println(obj2);
		
		List<PcbFrameTolerances> datalist = JsonUtil.json2array(obj2, PcbFrameTolerances.class);
		for (PcbFrameTolerances data : datalist) {
			System.out.println(data.getFtName());
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	public static void test1(){
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
	public static void test2(){
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
//		for (Map.Entry<String, Object> entry : fieldList.entrySet()) {
//			CheckItem value = (CheckItem)entry.getValue();
//			if (value == null) {
//				System.out.println("" + entry.getKey() + "," + value);
//				continue;
//			}
//			System.out.println("" + entry.getKey() + "," + value.getRequire());
//		}
		
		
		
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
//		for (Map.Entry<String, CheckItem> entry : fieldMap.entrySet()) {
////			CheckItem value = entry.getValue();
//			if (entry.getValue() == null) {
//				System.out.println("" + entry.getKey() + "," + entry.getValue());
//				continue;
//			}
//			System.out.println("" + entry.getKey() + ",>>" + entry.getValue().getRequire());
//		}
	}
}
