package pcbmis;

import java.lang.reflect.Field;

import com.pcbwx.pcbmis.bean.ReportSpecialImpedance;

public class MakeClassDesc {
	public static void main(String[] args) {
		Object obj = new ReportSpecialImpedance();
		testAnnotation(obj);
	}
	public static void testAnnotation(Object obj) {
		char splitChar = 9;
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			String title = field.getName();
			String type = field.getType().toString();
			final String[] split = type.split("\\.");
			
//			System.out.println(type + ">>" + split.length);
			System.out.println(title + splitChar + split[split.length - 1]);
		}
	}
}
