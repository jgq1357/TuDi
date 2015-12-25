package com.land.actions.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.SearchDAO;
import com.land.daos.SearchDAOImp;

public class ShowAllInfoAction extends LandSupport{
	private String dah;
	private Map<String,String> recordField1 = new HashMap<String, String>();
	private Map<String,String> recordField2 = new HashMap<String, String>();;
	private String dalb;
	private String shortName;
	Logger log = Logger.getLogger(ShowAllInfoAction.class);

	
	public String execute() {
		
		SearchDAO dao = new SearchDAOImp();
		System.out.println("dalb="+dalb);
	
		
		try {
			Map<String,String> recordField = dao.getRecordFieldByDah(dah, dalb);
			Set<String> keyset = recordField.keySet();
			Iterator<String> it = keyset.iterator();
			int size1 = recordField.size()/2+1;
			for(int i=0;i<size1;i++) {
				String key = it.next();
				recordField1.put(key, recordField.get(key));
			}
			for(int i=size1;i<recordField.size();i++) {
				String key = it.next();
				recordField2.put(key, recordField.get(key));
			}
			
			shortName = dao.getShortName(dalb);
			System.out.println("ShowAllInfoAction excute() shortname = " + shortName);
		
			if(dalb.equalsIgnoreCase("djyw"))
			{
				return "djyw";
			}
			else if(dalb.equalsIgnoreCase("gtzycjl"))
			{
				return "gtzyjcl";
			}
			
			
		} catch (SQLException e) {
			log.error("系统出错"+"读取档案["+dah+"]信息出错。");
			log.error("读取档案["+dah+"]信息出错。"+e.getMessage());
			log.error(e);
			return "error";
		}
		return "success";
	}

	

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getDah() {
		return dah;
	}

	public void setDah(String dah) {
		this.dah = dah;
	}



	
	public Map<String, String> getRecordField1() {
		return recordField1;
	}

	public void setRecordField1(Map<String, String> recordField1) {
		this.recordField1 = recordField1;
	}

	public Map<String, String> getRecordField2() {
		return recordField2;
	}

	public void setRecordField2(Map<String, String> recordField2) {
		this.recordField2 = recordField2;
	}

	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}
	
}
