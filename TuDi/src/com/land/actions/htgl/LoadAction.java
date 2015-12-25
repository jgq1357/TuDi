package com.land.actions.htgl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.land.actions.LandSupport;
import com.land.daos.MlDAO;
import com.land.daos.MlDAOImpl;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.daos.SearchDAOImp;
import com.land.domain.ML;
import com.land.domain.Record;
import com.land.domain.User;
import com.land.util.Constant;
import com.land.util.ExcelUtil;
import com.land.util.ParseZipUtil;

public class LoadAction extends LandSupport {
	private String daExcelPath;
	private String ljExcelPath;
	private String yxPath;
	private String type;
	private File daFile;
	private File ljFile;
	private File yxFile;
	private String daFileContentType;
	private String ljFileContentType;
	private String yxFileContentType;
	private String daFileFileName;
	private String ljFileFileName;
	private String yxFileFileName;
	private String resultMessage;
	Logger log = Logger.getLogger(LoadAction.class.getName()); 
	private User user;
	private Map<String, String> prePathMap = Constant.PREPATHMAP;

	public String execute() {

		user = (User) session.get("user");

		if (!init()) {
			resultMessage = "文件初始化出错！请稍候再传。";
			log.error(resultMessage);
			deleteTmpFile();
			return "input";
		}

		if (!loadDangAn()) {
			deleteTmpFile();
			return "input";
		}

		if (!loadDjlj()) {
			deleteTmpFile();
			return "input";
		}

		if (!loadYingXiang()) {
			deleteTmpFile();
		}

		deleteTmpFile();
		resultMessage = "数据导入成功。";
		return "success";
	}

	private void deleteTmpFile() {
		log.info("开始删除上传文件。。。。");

		if (daExcelPath != null && daExcelPath.trim().length() != 0) {
			log.info("删除档案文件。 " + daExcelPath);
			deleteFile(daExcelPath);
		}
		if (ljExcelPath != null && ljExcelPath.trim().length() != 0) {
			log.info("删除路径文件。 " + daExcelPath);
			deleteFile(ljExcelPath);
		}
		if (yxPath != null && yxPath.trim().length() != 0) {
			log.info("删除影像文件。 " + daExcelPath);
			deleteFile(yxPath);
		}
		log.info("删除上传文件成功。");
	}

	/**
	 * 导入影像文件
	 * 
	 * @return
	 */
	private boolean loadYingXiang() {
		try {
			if (yxPath != null && yxPath.trim().length() != 0) {
				String targetdir = prePathMap.get(type);
				System.out.println("targetdir = " + targetdir);
				System.out.println("yxPath = " + yxPath);
				log.info("-------------开始解压地籍管理影像 文件---------------");
				
				ParseZipUtil.unzip(yxPath, targetdir);
				log.info("-------------解压完成----------------");
				/*if ("djyw".equals(type)) {
					log.info("-------------开始解压地籍管理影像 文件---------------");
					targetdir = Constant.PREPATH;
					ParseZipUtil.unzip(yxPath, targetdir);
					log.info("-------------解压完成----------------");
				} else if ("jsyd".equals(type)) {
					log.info("-------------开始解压建设用地影像 文件---------------");
					targetdir = Constant.JSYD_PREPATH;
					ParseZipUtil.unzip(yxPath, targetdir);
					log.info("-------------解压完成----------------");
				} else if ("gtzyjc".equals(type)) {
					log.info("-------------开始解压国土资源监察影像 文件---------------");
					targetdir = Constant.GUZYJC_PREPATH;
					ParseZipUtil.unzip(yxPath, targetdir);
					log.info("-------------解压完成----------------");
				}*/
			}
		} catch (Exception e) {
			log.error("解压影像文件失败" + e.getMessage());
			log.error("解压影像文件失败 (" + yxPath + ")");
			log.error(e);

		}
		return true;
	}

	/**
	 * 读取路径文件
	 * 
	 * @return
	 */
	private boolean loadDjlj() {
		// List<List<String>> list = null;
		if (ljExcelPath != null && ljExcelPath.trim().length() != 0) {
			/*
			 * list = null; try { list = ExcelUtil.readExcel(ljExcelPath); }
			 * catch (Exception e) { errorMessage = "读取路径excel文件失败。";
			 * logIntoDb("系统出错", "读取路径excel文件失败。" + e.getMessage());
			 * log.error("读取路径excel文件失败。" + e.getMessage()); log.error(e);
			 * return false; } }
			 * 
			 * if (list != null) {
			 */
			try {
				// List<Djlj> djljs = generateDjljs(list);
				List<ML> djljs = ExcelUtil
						.readExcel(ljExcelPath, ML.class,null);
				MlDAO dao = new MlDAOImpl();
				dao.insertDjmls(djljs);
				// list = null;
				djljs = null;
			} catch (Exception e) {
				resultMessage = "插入档案路径失败。";
				log.error("系统错误"+resultMessage);
				log.error(resultMessage + e.getMessage() + "....");
				log.error(e);
				return false;
			}
		}
		return true;
	}

	/*
	 * private List<Djlj> generateDjljs(List<List<String>> list) { List<Djlj>
	 * djljs = new ArrayList<Djlj>(); List<String> fields = list.get(0); for
	 * (int i = 1; i < list.size(); i++) { Djlj djlj = new Djlj(); List<String>
	 * values = list.get(i); for (int j = 0; j < fields.size(); j++) { if
	 * ("ORDER_NUM".equalsIgnoreCase(fields.get(j))) {
	 * djlj.setOrder_num(Integer.parseInt(values.get(j))); } else if
	 * ("DAH".equalsIgnoreCase(fields.get(j))) { djlj.setDah(values.get(j)); }
	 * else if ("LJ".equalsIgnoreCase(fields.get(j))) {
	 * djlj.setLj(values.get(j)); } else if
	 * ("TM".equalsIgnoreCase(fields.get(j))) { djlj.setTm(values.get(j)); } }
	 * djljs.add(djlj); } list = null; return djljs; }
	 */
	/**
	 * 读取档案文件
	 * 
	 * @return
	 */
	private boolean loadDangAn() {
		// List<List<String>> list = null;
		if (daExcelPath != null && daExcelPath.trim().length() != 0) {
			/*
			 * // ... } else { try { list = ExcelUtil.readExcel(daExcelPath); }
			 * catch (Exception e) { errorMessage = "读取档案excel文件失败。";
			 * logIntoDb("系统出错", "读取档案excel文件失败。"); log.error("读取档案excel文件失败。" +
			 * e.getMessage()); log.error(e); return false; } }
			 * 
			 * if (list != null) {
			 */
			try {
				
				List<Record> records = ExcelUtil.readExcel(daExcelPath,Record.class,new SearchDAOImp().getDics("NAME"));
				RecordDAO dao = new RecordDAOImp();
				dao.insertRecord(records, type);
				/*if ("djyw".equals(type)) {
					List<Djyw> djyws = ExcelUtil.readExcel(daExcelPath,
							Djyw.class);
					DjywDAO dao = new DjywDAOImpl();
					log.info("开始导入地籍档案......");
					dao.insertDjyw(djyws);
					logIntoDb("档案入库", "地籍管理档案入库成功。", "C");
				} else if ("jsyd".equals(type)) {
					List<Jsyd> jsyds = ExcelUtil.readExcel(daExcelPath,
							Jsyd.class);
					JsydDAO dao = new JsydDAOImp();
					log.info("开始导入建设用地档案......");
					dao.insertJsyd(jsyds);
					logIntoDb("档案入库", "建设用地档案入库成功。", "E");
				} else if ("gtzyjc".equals(type)) {
					List<Gtzyjc> gtzyjcs = ExcelUtil.readExcel(daExcelPath,
							Gtzyjc.class);
					GtzyjcDAO dao = new GtzyjcDAOImp();
					log.info("开始导入国土资源档案......");
					dao.insertGtzyjc(gtzyjcs);
					logIntoDb("档案入库", "国土资源档案入库成功。", "F");
				}*/
			} catch (SQLException e) {
				resultMessage = "数据插入失败。";
				log.error("系统出错"+resultMessage);
				log.error(resultMessage + e.getMessage());
				log.error(e);
				return false;
			} catch (Exception e) {
				resultMessage = "所选档案类型与上传文件不匹配，请检查文件或档案类型是否正确。";
				log.error("系统出错"+resultMessage);
				log.error(resultMessage + e.getMessage());
				log.error(e);
				return false;
			}
		}

		return true;
	}

	private boolean init() {
		try {
			if (this.getDaFileFileName() != null
					&& this.getDaFileFileName().length() != 0) {
				log.info("上传档案文件(" + this.getDaFileFileName()+")");
				log.info("[" + user.getId() + "]" + user.getUserName()
						+ "上传档案文件" + this.getDaFileFileName());
				InputStream isDa = new FileInputStream(daFile);
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/upload");
				File toFile = new File(uploadPath, this.getDaFileFileName());
				OutputStream os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024];
				int length = 0;
				while ((length = isDa.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				isDa.close();
				os.close();
				daExcelPath = uploadPath + "/" + this.getDaFileFileName();
			}
			if (this.getLjFileFileName() != null
					&& this.getLjFileFileName().length() != 0) {
				log.info("[" + user.getId() + "]" + user.getUserName()
						+ "上传路径文件(" + this.getLjFileFileName()+")");
				log.info("上传路径文件" + this.getLjFileFileName());

				InputStream isLj = new FileInputStream(ljFile);
				String uploadPath = ServletActionContext.getServletContext()
						.getRealPath("/upload");
				File toFile = new File(uploadPath, this.getLjFileFileName());
				OutputStream os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024];
				int length = 0;
				while ((length = isLj.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				isLj.close();
				os.close();
				ljExcelPath = uploadPath + "/" + this.getLjFileFileName();
			}
			if (this.getYxFileFileName() != null
					&& this.getYxFileFileName().length() != 0) {
				log.info("[" + user.getId() + "]" + user.getUserName()
						+ "上传影像文件(" + this.getYxFileFileName()+")");
				log.info("上传影像文件" + this.getYxFileFileName());
				InputStream isYx = new FileInputStream(yxFile);
				String uploadPath = /*Constant.PREPATH*/prePathMap.get("djyw");
				File toFile = new File(uploadPath, this.getYxFileFileName());
				OutputStream os = new FileOutputStream(toFile);
				byte[] buffer = new byte[1024];
				int length = 0;
				while ((length = isYx.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
				isYx.close();
				os.close();
				yxPath = uploadPath + "" + this.getYxFileFileName();
			}

		} catch (FileNotFoundException e) {
			resultMessage = "文件未找到--[" + this.getDaFileFileName() + "]";
			log.error("系统出错"+resultMessage);
			log.error(resultMessage + e.getMessage());
			log.error(e);
			return false;
		} catch (IOException e) {
			resultMessage = "系统出错请稍候再试......";
			log.error("系统出错"+resultMessage);
			log.error(resultMessage + e.getMessage());
			log.error(e);
			return false;
		}
		return true;
	}

	private void deleteFile(String filename) {
		File file = new File(filename);
		file.delete();
	}

	/*
	 * private List<Djyw> generateDjyws(List<List<String>> list) throws
	 * Exception { List<String> fields = list.get(0); List<Djyw> djyws = new
	 * ArrayList<Djyw>(); for (int i = 1; i < list.size(); i++) { List<String>
	 * values = list.get(i); Djyw djyw = new Djyw(); Class<Djyw> c = Djyw.class;
	 * Field fs[] = c.getDeclaredFields(); for (int j = 0; j < values.size();
	 * j++) { for (int k = 0; k < fs.length; k++) { if
	 * (fs[k].getName().equalsIgnoreCase(fields.get(j))) { PropertyDescriptor pd
	 * = new PropertyDescriptor(fs[k] .getName(), c); Method method =
	 * pd.getWriteMethod();
	 * 
	 * if (fs[k].getType() == int.class) { int v = 0; if
	 * (values.get(j).trim().length() != 0) { v =
	 * Integer.parseInt(values.get(j)); } method.invoke(djyw, v); } else if
	 * (fs[k].getType() == String.class) { String s = values.get(j); if
	 * (s.contains(".")) { s = paseString(s); } method.invoke(djyw, s); } else
	 * if (fs[k].getType() == float.class) { float f = 0.0f; if
	 * (values.get(j).trim().length() != 0) { f =
	 * Float.parseFloat(values.get(j)); } method.invoke(djyw, f); } else if
	 * (fs[k].getType() == Date.class) { Date d = null; if
	 * (values.get(j).trim().length() != 0) { d =
	 * Date.valueOf(values.get(j).trim()); } method.invoke(djyw, d); } } } }
	 * djyws.add(djyw); } return djyws; }
	 * 
	 * private List<Jsyd> generateJsyds(List<List<String>> list) throws
	 * Exception { List<String> fields = list.get(0); List<Jsyd> jsyds = new
	 * ArrayList<Jsyd>(); for (int i = 1; i < list.size(); i++) { List<String>
	 * values = list.get(i); Jsyd jsyd = new Jsyd(); Class<Jsyd> c = Jsyd.class;
	 * Field fs[] = c.getDeclaredFields(); for (int j = 0; j < values.size();
	 * j++) { for (int k = 0; k < fs.length; k++) { if
	 * (fs[k].getName().equalsIgnoreCase(fields.get(j))) { PropertyDescriptor pd
	 * = new PropertyDescriptor(fs[k] .getName(), c); Method method =
	 * pd.getWriteMethod();
	 * 
	 * if (fs[k].getType() == int.class) { int v = 0; if
	 * (values.get(j).trim().length() != 0) { v =
	 * Integer.parseInt(values.get(j)); } method.invoke(jsyd, v); } else if
	 * (fs[k].getType() == String.class) { String s = values.get(j); if
	 * (s.contains(".")) { s = paseString(s); } method.invoke(jsyd, s); } else
	 * if (fs[k].getType() == float.class) { float f = 0.0f; if
	 * (values.get(j).trim().length() != 0) { f =
	 * Float.parseFloat(values.get(j)); } method.invoke(jsyd, f); } else if
	 * (fs[k].getType() == Date.class) { Date d = null; if
	 * (values.get(j).trim().length() != 0) { d =
	 * Date.valueOf(values.get(j).trim()); } method.invoke(jsyd, d); } } } }
	 * jsyds.add(jsyd); } return jsyds; }
	 * 
	 * private List<Gtzyjc> generateGtzyjcs(List<List<String>> list) throws
	 * Exception { List<String> fields = list.get(0); List<Gtzyjc> gtzyjcs = new
	 * ArrayList<Gtzyjc>(); for (int i = 1; i < list.size(); i++) { List<String>
	 * values = list.get(i); Gtzyjc gtzyjc = new Gtzyjc(); Class<Gtzyjc> c =
	 * Gtzyjc.class; Field fs[] = c.getDeclaredFields(); for (int j = 0; j <
	 * values.size(); j++) { for (int k = 0; k < fs.length; k++) { if
	 * (fs[k].getName().equalsIgnoreCase(fields.get(j))) { PropertyDescriptor pd
	 * = new PropertyDescriptor(fs[k] .getName(), c); Method method =
	 * pd.getWriteMethod();
	 * 
	 * if (fs[k].getType() == int.class) { int v = 0; if
	 * (values.get(j).trim().length() != 0) { v =
	 * Integer.parseInt(values.get(j)); } method.invoke(gtzyjc, v); } else if
	 * (fs[k].getType() == String.class) { String s = values.get(j); if
	 * (s.contains(".")) { s = paseString(s); } method.invoke(gtzyjc, s); } else
	 * if (fs[k].getType() == float.class) { float f = 0.0f; if
	 * (values.get(j).trim().length() != 0) { f =
	 * Float.parseFloat(values.get(j)); } method.invoke(gtzyjc, f); } else if
	 * (fs[k].getType() == Date.class) { Date d = null; if
	 * (values.get(j).trim().length() != 0) { d =
	 * Date.valueOf(values.get(j).trim()); } method.invoke(gtzyjc, d); } } } }
	 * gtzyjcs.add(gtzyjc); } return gtzyjcs; }
	 */

	/*
	 * private String paseString(String s) { String result = s; String str =
	 * s.substring(s.lastIndexOf(".") + 1, s.length()); if (str.contains("0")) {
	 * result = ""; for (int i = 0; i < str.length(); i++) { result = result +
	 * "0"; } } else { return result; }
	 * 
	 * if (str.equals(result)) { result = s.substring(0, s.lastIndexOf(".")); }
	 * 
	 * return result; }
	 */
	public File getDaFile() {
		return daFile;
	}

	public void setDaFile(File daFile) {
		this.daFile = daFile;
	}

	public File getLjFile() {
		return ljFile;
	}

	public void setLjFile(File ljFile) {
		this.ljFile = ljFile;
	}

	public String getDaFileContentType() {
		return daFileContentType;
	}

	public String getLjExcelPath() {
		return ljExcelPath;
	}

	public void setLjExcelPath(String ljExcelPath) {
		this.ljExcelPath = ljExcelPath;
	}

	public File getYxFile() {
		return yxFile;
	}

	public void setYxFile(File yxFile) {
		this.yxFile = yxFile;
	}

	public String getYxFileContentType() {
		return yxFileContentType;
	}

	public void setYxFileContentType(String yxFileContentType) {
		this.yxFileContentType = yxFileContentType;
	}

	public String getYxFileFileName() {
		return yxFileFileName;
	}

	public void setYxFileFileName(String yxFileFileName) {
		this.yxFileFileName = yxFileFileName;
	}

	public void setDaFileContentType(String daFileContentType) {
		this.daFileContentType = daFileContentType;
	}

	public String getLjFileContentType() {
		return ljFileContentType;
	}

	public void setLjFileContentType(String ljFileContentType) {
		this.ljFileContentType = ljFileContentType;
	}

	public String getDaFileFileName() {
		return daFileFileName;
	}

	public void setDaFileFileName(String daFileFileName) {
		this.daFileFileName = daFileFileName;
	}

	public String getLjFileFileName() {
		return ljFileFileName;
	}

	public void setLjFileFileName(String ljFileFileName) {
		this.ljFileFileName = ljFileFileName;
	}

	public String getDaExcelPath() {
		return daExcelPath;
	}

	public void setDaExcelPath(String daExcelPath) {
		this.daExcelPath = daExcelPath;
	}

	

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static void main(String args[]) {
		/*
		 * LoadAction l = new LoadAction(); l.setDaExcelPath(
		 * "C:\\Documents and Settings\\Administrator\\桌面\\dev\\djyw.xlsx");
		 * l.setType("djyw"); l.execute();
		 */
		/*
		 * String str = "263.0"; str =
		 * str.substring(0,str.lastIndexOf("."),str.length());
		 * System.out.println(str);
		 */
		double d = 2.706;
		float v = (float) d;
		System.out.println(v);
	}

}
