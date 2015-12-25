package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 土地利用规划
 * @author Administrator
 *
 */
public class Tdlyghl {
	public String qzh; //全宗号
	public String mlh;//目录号
	public String ajh;//案卷号
	public String flh;//分类号
	public String nd;//年度
	public String yddw;//用地单位
	public String tdzl;//土地坐落
	public String pzwh;//批准文号
	public String pzmj;//批准面积
	public String xmmc;//项目名称
	public String pzjg;//批准机关
	public String pzrq;//批准日期
	
	public Tdlyghl() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public Tdlyghl(Map<String,Object> parameters){
		Class<Tdlyghl> c = Tdlyghl.class;
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
		String sql="insert into tdlyghl(#) values (%)";
		Class<Tdlyghl> c = Tdlyghl.class;
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
	public String getYddw() {
		return yddw;
	}
	public void setYddw(String yddw) {
		this.yddw = yddw;
	}
	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getPzmj() {
		return pzmj;
	}
	public void setPzmj(String pzmj) {
		this.pzmj = pzmj;
	}
	public String getXmmc() {
		return xmmc;
	}
	public void setXmmc(String xmmc) {
		this.xmmc = xmmc;
	}
	public String getPzjg() {
		return pzjg;
	}
	public void setPzjg(String pzjg) {
		this.pzjg = pzjg;
	}
	public String getPzrq() {
		return pzrq;
	}
	public void setPzrq(String pzrq) {
		this.pzrq = pzrq;
	}
	

}
