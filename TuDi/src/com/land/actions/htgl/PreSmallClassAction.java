package com.land.actions.htgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.domain.Condition;
import com.land.domain.DaDesc;
import com.land.domain.Dictionary;

public class PreSmallClassAction extends LandSupport{
	private int lbh;
	private int lmId=1;
	private String fieldStr="";
	private String isJson="N";
	private List<DaDesc> danames;
	private List<Condition> existFields = new ArrayList<Condition>();
	private RecordDAO dao = new RecordDAOImp();
	Logger log = Logger.getLogger(PreSmallClassAction.class.getName());
	public String execute() {
		try {
			String tableName = dao.getTableName(lmId);
			lbh = dao.generateLbh();
			danames = dao.getDaDesc();
			Map<String, Dictionary> dics = dao.getDics("NAME");
			/*for(int i=0;i<danames.size();i++) {
				if (danames.get(i).getTablename()==null) {
					continue;
				}*/
			if(tableName!=null && tableName.trim().length()!=0) {
				List<String> tableColumns = dao.getTableColumns(tableName);
				
				if("Y".equals(isJson)) {
					fieldStr = "dah,档案号|";
					for(int j=0;j<tableColumns.size();j++) {
						String field = tableColumns.get(j);
						if(!checkStr(field)) {
							continue;
						}
						fieldStr+=field+","+dics.get(field.toLowerCase()).getRealName();
						if(j!=tableColumns.size()-1) {
							fieldStr+="|";
						}
						
					}
				}else {
					Condition co = new Condition();
					co.setAlpha("dah");
					co.setName("档案号");
					co.setBelongId(lmId);
					existFields.add(co);
					for(int j=0;j<tableColumns.size();j++) {
						
						String field = tableColumns.get(j);
						if(!checkStr(field)) {
							continue;
						}
						Condition c = new Condition();
						c.setBelongId(lmId);
						c.setAlpha(field);
						c.setName(dics.get(field.toLowerCase()).getRealName());
						
						existFields.add(c);
					}
				}
				
			}
				
				if("Y".equals(isJson)) {
					return "json";
				}
			/*}*/
		} catch (SQLException e) {
			log.error(e);
			return "error";
		}
		return "success";
	}
	
	private boolean checkStr(String str) {
		if("qzh".equalsIgnoreCase(str)||"mlh".equalsIgnoreCase(str)||"flh".equalsIgnoreCase(str)||"ajh".equalsIgnoreCase(str)) {
			return false;
		}
		return true;
	}
	
	public String getIsJson() {
		return isJson;
	}



	public void setIsJson(String isJson) {
		this.isJson = isJson;
	}



	public String getFieldStr() {
		return fieldStr;
	}



	public void setFieldStr(String fieldStr) {
		this.fieldStr = fieldStr;
	}


	@JSON(serialize=false)
	public int getLmId() {
		return lmId;
	}

	public void setLmId(int lmId) {
		this.lmId = lmId;
	}

	@JSON(serialize=false)
	public int getLbh() {
		
		return lbh;
	}
	public void setLbh(int lbh) {
		this.lbh = lbh;
	}
	@JSON(serialize=false)
	public List<DaDesc> getDanames() {
		return danames;
	}
	public void setDanames(List<DaDesc> danames) {
		this.danames = danames;
	}
	@JSON(serialize=false)
	public List<Condition> getExistFields() {
		return existFields;
	}
	public void setExistFields(List<Condition> existFields) {
		this.existFields = existFields;
	}
	
	
}
