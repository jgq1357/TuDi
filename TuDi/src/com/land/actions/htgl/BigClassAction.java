package com.land.actions.htgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.domain.Dictionary;

public class BigClassAction extends LandSupport{
	private String daName;
	private String daAlpha;
	private String subName;
	private String fields;
	private String searchFields;
	private String fieldNames;
	private String alphaNames;
	private String searchFields2;
	private String roles;
	private String vaild="";
	private RecordDAO dao = new RecordDAOImp(); 
	Logger log = Logger.getLogger(BigClassAction.class.getName()); 
	public String execute() {
		if(daName.trim().length()==0) {
			vaild="档案名字不能为空！";
			return "success";
		}
		if(daAlpha.trim().length()==0) {
			vaild="数据库表名不能为空！";
			return "success";
		}else {
	        if(!checkAlpha(daAlpha)) {
	        	vaild="数据库表名必须为字母！";
	        	return "success";
	        }
		}
		if(subName.trim().length()==0) {
			subName = daName.trim();
		}
		if(checkString(roles)) {
			vaild="请赋予权限！";
			return "success";
		}
		if(checkString(fields)&& checkString(fieldNames)) {
			vaild="至少指定一个字段名！";
			return "success";
		}
		if (checkString(searchFields)&& checkString(searchFields2)) {
			vaild="至少指定一个搜索条件！";
			return "success";
		}
		
		if(!checkString(alphaNames)) {
			String[] alp = alphaNames.trim().split(",");
			for(int i=0;i<alp.length;i++) {
				if(!checkAlpha(alp[i])) {
					vaild="字段简称必须为字母或数字！";
					return "success";
				}
			}
		}
		
		String allFields = fields+","+alphaNames;
		String allSearchFields = searchFields+","+searchFields2;
		String[] allS = allSearchFields.split(",");
		String[] allF = allFields.split(",");
		boolean flag = false;
		for(int i=0;i<allS.length;i++) {
			if(allS[i].trim().length()!=0) {
				continue;
			}
			for(int j=0;j<allF.length;j++) {
				if(allS[i].equals(allF[j])) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				vaild="搜索条件必须在已选字段中！";
				return "success";
			}
		}
		try {
			Map<String, Dictionary> fieldNameMaps = dao.getDics("NAME");
			
			List<Dictionary> creatDics = new ArrayList<Dictionary>();
			List<String> creatFns = new ArrayList<String>();
			String[] aLs = alphaNames.split(",");
			String[] sFs = fieldNames.split(",");
			for(int i=0;i<aLs.length;i++) {
				if(aLs[i].trim().length()!=0) {
					if(fieldNameMaps.containsKey(aLs[i].trim().toLowerCase())) {
						vaild="字段简称" +aLs[i].trim()+" 已经存在，对应"+fieldNameMaps.get(aLs[i].trim()).getRealName();
						return "success";
					}
					if("dah".equals(aLs[i])) {
						vaild="字段简称" +aLs[i].trim()+" 已经存在，对应档案号！";
						return "success";
					}
					Dictionary d = new Dictionary();
					d.setAlpha(aLs[i].toUpperCase());
					d.setRealName(sFs[i]);
					creatFns.add(aLs[i]);
					creatDics.add(d);
				}
			}
			
			dao.updateDictionary(creatDics, "NAME");
			Map<String, Dictionary> searchConditionMaps = dao.getDics("COL");
			List<String> creatScs = new ArrayList<String>();
			for(int i=0;i<allS.length;i++) {
				if(allS[i].trim().length()!=0) {
					if(!searchConditionMaps.containsKey(allS[i].trim().toLowerCase())) {
						creatScs.add(allS[i]);
					}
					
				}
			}
			
			
			
			
			List<Dictionary> creatSfDics = new ArrayList<Dictionary>();
			for(int i=0;i<creatScs.size();i++) {
				boolean f = true;
				for(int j=0;j<creatDics.size();j++) {
					if(creatScs.get(i).equalsIgnoreCase(creatDics.get(j).getAlpha())) {
						Dictionary d = creatDics.get(j);
						creatSfDics.add(d);
						f = false;
					}
				}
				if(f) {
					Dictionary d = fieldNameMaps.get(creatScs.get(i).toLowerCase());
					creatSfDics.add(d);
				}
				
			}
			
			dao.updateDictionary(creatSfDics, "COL");
			List<String> fns = new ArrayList<String>();
			boolean containLbh = false;
			for(int i=0;i<allF.length;i++) {
				if(allF[i].trim().length()!=0) {
					if("dah".equalsIgnoreCase(allF[i])) {
						fns.add("qzh");
						fns.add("mlh");
						fns.add("flh");
						fns.add("ajh");
					}else {
						fns.add(allF[i]);
					}
					if("lbh".equalsIgnoreCase(allF[i])) {
						containLbh = true;
					}
				}
			}
			if(!containLbh) {
				fns.add("lbh");
			}
			
			searchConditionMaps = dao.getDics("COL");
			List<String> sfs = new ArrayList<String>();
			for(int i=0;i<allS.length;i++) {
				if(allS[i].trim().length()!=0) {
					Dictionary d = searchConditionMaps.get(allS[i].trim().toLowerCase());
					sfs.add(d.getCol_name().toLowerCase());
				}
			}
			
			List<String> rolesDics = new ArrayList<String>();
			String[] r = roles.split(",");
			for(int i=0;i<r.length;i++) {
				if(r[i].trim().length()!=0) {
					rolesDics.add(r[i].trim());
				}
			}
			dao.createRecordTable(daAlpha, fns, daName, sfs,subName,rolesDics);
		} catch (SQLException e) {
			vaild = "档案创建失败！请联系admin";
			log.error("档案创建失败！");
			log.error(e);
			return "success";
		}
		
		return "success";
	}
	
	private boolean checkString(String str) {
		String[] s = str.trim().split(",");
		boolean flag = true;
		for(int i=0;i<s.length;i++) {
			if(s[i].trim().length()!=0) {
				flag = false;
				break;
			}
		}
		return flag;
	}
	
	private boolean checkAlpha(String str) {
		Pattern p = Pattern.compile("[a-zA-Z0-9]+");
        Matcher m = p.matcher(str.trim());
        return m.matches();
	}

	@JSON(serialize=false)
	public String getDaName() {
		return daName;
	}

	public void setDaName(String daName) {
		this.daName = daName;
	}

	@JSON(serialize=false)
	public String getDaAlpha() {
		return daAlpha;
	}

	public void setDaAlpha(String daAlpha) {
		this.daAlpha = daAlpha;
	}

	@JSON(serialize=false)
	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}

	@JSON(serialize=false)
	public String getFields() {
		return fields;
	}

	public void setFields(String fields) {
		this.fields = fields;
	}

	@JSON(serialize=false)
	public String getSearchFields() {
		return searchFields;
	}

	public void setSearchFields(String searchFields) {
		this.searchFields = searchFields;
	}

	@JSON(serialize=false)
	public String getFieldNames() {
		return fieldNames;
	}

	public void setFieldNames(String fieldNames) {
		this.fieldNames = fieldNames;
	}

	@JSON(serialize=false)
	public String getAlphaNames() {
		return alphaNames;
	}

	public void setAlphaNames(String alphaNames) {
		this.alphaNames = alphaNames;
	}

	@JSON(serialize=false)
	public String getSearchFields2() {
		return searchFields2;
	}

	public void setSearchFields2(String searchFields2) {
		this.searchFields2 = searchFields2;
	}

	@JSON(serialize=false)
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getVaild() {
		return vaild;
	}

	public void setVaild(String vaild) {
		this.vaild = vaild;
	}
	
	
}
