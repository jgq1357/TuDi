package com.land.actions.htgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.domain.Dalb;
import com.land.domain.Dictionary;

public class SmallClassAction extends LandSupport{

	private String vaild="";
	private String name;
	private int lbh;
	private int lmId;
	private String fields;
	private RecordDAO dao = new RecordDAOImp(); 
	Logger log = Logger.getLogger(SmallClassAction.class.getName());
	public String execute() {
		
		if(name.trim().length()==0) {
			vaild="名称不能为空！";
			return "success";
		}
		if(fields.trim().length()==0) {
			vaild="必须选择一个搜索条件！";
			return "success";
		}
		try {
			String fieldstr[] = fields.split(",");
			Map<String, Dictionary> fieldNameMaps = dao.getDics("NAME");
			Map<String, Dictionary> searchConditionMaps = dao.getDics("COL");
			List<Dictionary> creatSfDics = new ArrayList<Dictionary>();
			for(int i=0;i<fieldstr.length;i++) {
				if(searchConditionMaps.containsKey(fieldstr[i].toLowerCase())) {
					continue;
				}else {
					creatSfDics.add(fieldNameMaps.get(fieldstr[i].toLowerCase()));
				}
			}
			dao.updateDictionary(creatSfDics, "COL");
			List<Dalb> dalbs = new ArrayList<Dalb>();
			List<String> sf = new ArrayList<String>();
			searchConditionMaps = dao.getDics("COL");
			String searchCondition = "";
			for(int i=0;i<fieldstr.length;i++) {
				searchCondition += searchConditionMaps.get(fieldstr[i].toLowerCase()).getCol_name();
				if(i!=fieldstr.length-1) {
					searchCondition +=",";
				}
				sf.add(searchConditionMaps.get(fieldstr[i].toLowerCase()).getCol_name());
			}
			Dalb dalb = new Dalb();
			dalb.setLbh(lbh);
			dalb.setLbName(name);
			dalb.setLmId(lmId);
			dalb.setSearchCondition(searchCondition);
			dalbs.add(dalb);
			dao.updateDalb(dalb);
			dao.updateSearchConditions(lmId, sf);
		} catch (SQLException e) {
			log.error("添加小类失败！");
			log.error(e);
		}
		
		
		return "success";
	}

	public String getVaild() {
		return vaild;
	}
	
	

	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	public void setVaild(String vaild) {
		this.vaild = vaild;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}

	public int getLmId() {
		return lmId;
	}

	public void setLmId(int lmId) {
		this.lmId = lmId;
	}

	
	
}
