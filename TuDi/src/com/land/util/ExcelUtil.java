package com.land.util;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

	public static<T> List<T> readExcel(String fileName, Class<T> c, Map<String, String> dic)
			throws Exception {
		File f = new File(fileName);
		List<String> fields = new ArrayList<String>();
		List<T> objs = new ArrayList<T>();
		FileInputStream is = new FileInputStream(f);
		XSSFWorkbook wbs = new XSSFWorkbook(is);
		XSSFSheet childSheet = wbs.getSheetAt(0);
		XSSFRow r = childSheet.getRow(1);
		for (int i = 0; i < r.getLastCellNum(); i++) {
			XSSFCell cell = r.getCell(i);
			if(dic==null) {
				fields.add(cell.getStringCellValue());
			}else {
				fields.add(dic.get(cell.getStringCellValue().toLowerCase()));
			}
			
		}
		int size = fields.size();
		for (int j = 2; j <= childSheet.getLastRowNum(); j++) {
			T obj = (T)c.newInstance();
			Field fs[] = c.getDeclaredFields();
			XSSFRow row = childSheet.getRow(j);
			
			List<String> values = new ArrayList<String>();
			int total = 0;
			if (null != row) {
				for (int k = 0; k < size; k++) {
					for (int i = 0; i < fs.length; i++) {
						if (fs[i].getName().equalsIgnoreCase(fields.get(k))) {
							XSSFCell cell = row.getCell(k);
							PropertyDescriptor pd = new PropertyDescriptor(
									fs[i].getName(), c);
							Method method = pd.getWriteMethod();

							String value = "";
							if (null != cell) {
								switch (cell.getCellType()) {
								case XSSFCell.CELL_TYPE_STRING: // 字符串
									value = cell.getStringCellValue();
									setClassValue(obj, value, fs[i]
											.getType(), method);
									break;
								case XSSFCell.CELL_TYPE_NUMERIC: // 数字
									setClassValue(obj, cell
											.getNumericCellValue(), fs[i]
											.getType(), method);
									
									break;

								case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
									value = cell.getBooleanCellValue() + "";
									break;
								case XSSFCell.CELL_TYPE_FORMULA: // 公式
									try {
										setClassValue(obj, cell
												.getNumericCellValue(), fs[i]
												.getType(), method);
									} catch (IllegalStateException e) {
										value = cell.getStringCellValue();
										method.invoke(obj, value);
									}

									break;
								case XSSFCell.CELL_TYPE_BLANK: // 空值
									total++;
									break;
								case XSSFCell.CELL_TYPE_ERROR: // 故障
									System.out.println("[ERROR]:读取出错。");
									break;
								default:
									System.out.print("[ERROR]:未知类型。");
									break;
								}
							} else {
								total++;
							}
							values.add(value);
						}
					}
				}
				if (total != size) {
					objs.add(obj);
				}
			}
		}
		return objs;
	}

	private static<T> void setClassValue(T obj, double value, Class<?> type,
			Method method) throws Exception {
		if (type == String.class) {
			method.invoke(obj, paseString(value+""));
		} else if (type == int.class) {
			int v = (int) value;
			method.invoke(obj, v);
		
		} else if (type == float.class) {
			float v = (float) value;

			method.invoke(obj, v);
		} else if (type == Date.class) {
			Date d = null;
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
			String a = s.format(HSSFDateUtil.getJavaDate(value));
			d = Date.valueOf(a);
			method.invoke(obj, d);
		}
//		System.out.println(value);
//		System.out.println(((Djyw)obj).getMlh());
	}
	
	private static String paseString(String s) {
		String result = s;
		String str = s.substring(s.lastIndexOf(".") + 1, s.length());
		if (str.contains("0")) {
			result = "";
			for (int i = 0; i < str.length(); i++) {
				result = result + "0";
			}
		} else {
			return result;
		}

		if (str.equals(result)) {
			result = s.substring(0, s.lastIndexOf("."));
		}

		return result;
	}
	
	private static<T> void setClassValue(T obj, String value, Class<?> type,
			Method method) throws Exception {
		if (type == String.class) {
			method.invoke(obj, value);
		} else if (type == int.class) {
			int v = Integer.parseInt(value);
			method.invoke(obj, v);
		} else if (type == float.class) {
			float v = Float.parseFloat(value);

			method.invoke(obj, v);
		} else if (type == Date.class) {
			Date d = null;
			d = Date.valueOf(value);
			method.invoke(obj, d);
		}
	}
	
	
	public static void main(String[] args) {
		/*try {
			List<Djlj> l = readExcel(
					"C:\\Documents and Settings\\Administrator\\桌面\\dev\\jsyd.xlsx",
					Djlj.class);
			for (int i = 0; i < l.size(); i++) {
				System.out.println(l.get(i).getOrder_num());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
