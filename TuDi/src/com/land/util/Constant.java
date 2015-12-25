package com.land.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class Constant {
	//避免session、application中key冲突
//	public static final String IMAGE_CODE = "image.code";
   //context区域中使用的key
	public static final String ERRORS = "errors";
	//提高代码可读性
	public static final String SEX_MALE = "M";
	public static final String SEX_FEMALE = "F";
	
	public static final String LOGIN_ACTION = "login";
	
//	public static final String PREPATH;
//	public static final String JSYD_PREPATH;
//	public static final String GTZYJC_PREPATH;
	public static final int ROWSOFLOG;
	public static final int ROWSOFSEARCHRESULT;
	
	public static final int SMALLPIC_WIDTH=250;
	public static final int BIGPIC_WIDTH = 700;
	public static final int PRINTPIC_WIDTH = 600;
	
	public static final String LOGFILEPATH;
	public static final String LOGLEVEL;
	public static final String TESSERACTPATH;
	
	public static final Map<String, String> PREPATHMAP;
	
	static {
		String prePath = "";
		String jsyd_prePath = "";
		String gtzyjc_prePath = "";
		String logpath = "";
		String loglevel="info";
		String tess="";
		int rowsOfLog = 15;
		int rowsOfSearchResult = 15;
		Map<String, String> map = new HashMap<String, String>();
		try {
			Connection conn = DbUtil.getConnection();
			String sql = "select param_name, param_value, param_flag from land_params";
			PreparedStatement pst = conn.prepareCall(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				if(rs.getString("param_flag")!=null) {
					map.put(rs.getString("param_flag").toLowerCase(), rs.getString("param_value"));
					continue;
				}
				/*if("PREPATH".equalsIgnoreCase(rs.getString("param_name"))) {
					prePath = rs.getString("param_value");
				}else */if("ROWSOFLOG".equalsIgnoreCase(rs.getString("param_name"))) {
					rowsOfLog = Integer.parseInt(rs.getString("param_value"));
				}else if("ROWSOFSEARCHRESULT".equalsIgnoreCase(rs.getString("param_name"))) {
					rowsOfSearchResult = Integer.parseInt(rs.getString("param_value"));
				}/*else if("JSYD_PREPATH".equalsIgnoreCase(rs.getString("param_name"))) {
					jsyd_prePath = rs.getString("param_value");
				}else if("GTZYJC_PREPATH".equalsIgnoreCase(rs.getString("param_name"))) {
					gtzyjc_prePath = rs.getString("param_value");
				}*/else if("LOGFILEPATH".equalsIgnoreCase(rs.getString("param_name"))) {
					logpath = rs.getString("param_value");
				}else if("LOGLEVEL".equalsIgnoreCase(rs.getString("param_name"))) {
					loglevel = rs.getString("param_value");
				}else if("TESSERACTPATH".equalsIgnoreCase(rs.getString("param_name"))) {
					tess = rs.getString("param_value");
				}
			}
		}catch (NumberFormatException e) {
			System.out.println("[WARING]: Not or error config the constant in db.");
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:query error when get the Constant.");
		}
//		PREPATH=prePath;
//		JSYD_PREPATH=jsyd_prePath;
//		GTZYJC_PREPATH=gtzyjc_prePath;
		PREPATHMAP = map;
		ROWSOFLOG=rowsOfLog;
		ROWSOFSEARCHRESULT=rowsOfSearchResult;
		TESSERACTPATH = tess;
		if(logpath==null) {
			LOGFILEPATH="";
		}else {
			LOGFILEPATH = logpath.trim();
		}
		
		if(loglevel==null) {
			LOGLEVEL="info";
		}else {
			LOGLEVEL = loglevel.trim();
		}
		
	}
	
	
}
