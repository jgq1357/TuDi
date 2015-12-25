package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 土地监察
 * @author Administrator
 *
 */
public class Gtzyjcl {
	public String qzh;//全宗号
	public String mlh;//目录号
	public String ajh;//案卷号
	public String flh;//分类号
	public String nd;//年度
	public String wfdw;//违法单位
	public String wfmj;//违法面积
	public String tdzl;//土地坐落
	public String ay;//案由
	public String bajg;//办案机关
	public String jasj;//结案时间
	public Gtzyjcl() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unchecked")
	public Gtzyjcl(Map<String,Object> parameters){
		Class<Gtzyjcl> c = Gtzyjcl.class;
		Field fields[] = c.getDeclaredFields();
		Set<String> keySet = parameters.keySet();
		Iterator<String> iterator = keySet.iterator();
		while(iterator.hasNext()){
			String key = iterator.next();
			for(int i=0;i<fields.length;i++){
				if(fields[i].getName().equalsIgnoreCase(key)){
					try {
						Object value = parameters.get(key);
						if(value != null){
							if(value instanceof BigDecimal) {
								BigDecimal bd = (BigDecimal)value;
								Class cla = fields[i].getType();
								if("float".equals(cla.toString())) {
									fields[i].set(this, bd.floatValue());
								}else {
									fields[i].set(this, bd.intValue());
								}
								
							}else {
								fields[i].set(this, value);
							}
							
						}else{
							Class cla = fields[i].getType();
							if(cla == String.class){
								fields[i].set(this, "");
							}
						}
						
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
			
		}
	}
	
	public String getInsertSql() {
		String sql="insert into gtzyjcl(#) values (%)";
		Class<Gtzyjcl> c = Gtzyjcl.class;
		Field fields[] = c.getDeclaredFields();
		String f = "";
		String v = "TUDI_SEQ.nextval";
		for(int i=0;i<fields.length;i++) {
			f = f+fields[i].getName();
			if(i!=0) {
				v = v + "?";
			}
			
				f=f+", ";
				v=v+", ";
		}
		f=f+"SRC_ADDTIME";
		v=v+"sysdate";
		sql = sql.replaceAll("#", f);
		sql = sql.replaceAll("%", v);
		return sql;
	}
	
	public String getFlh() {
		return flh;
	}

	public void setFlh(String flh) {
		this.flh = flh;
	}

	public String getQzh() {
		return qzh;
	}
	public void setQzh(String qzh) {
		this.qzh = qzh;
	}
	public String getMlh() {
		return mlh;
	}
	public void setMlh(String mlh) {
		this.mlh = mlh;
	}
	public String getAjh() {
		return ajh;
	}
	public void setAjh(String ajh) {
		this.ajh = ajh;
	}
	public String getNd() {
		return nd;
	}
	public void setNd(String nd) {
		this.nd = nd;
	}
	public String getWfdw() {
		return wfdw;
	}
	public void setWfdw(String wfdw) {
		this.wfdw = wfdw;
	}

	public String getWfmj() {
		return wfmj;
	}

	public void setWfmj(String wfmj) {
		this.wfmj = wfmj;
	}

	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	public String getAy() {
		return ay;
	}
	public void setAy(String ay) {
		this.ay = ay;
	}
	public String getBajg() {
		return bajg;
	}
	public void setBajg(String bajg) {
		this.bajg = bajg;
	}
	public String getJasj() {
		return jasj;
	}
	public void setJasj(String jasj) {
		this.jasj = jasj;
	}
	

}
