package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 建设用地类
 * @author Administrator
 *
 */
public class Jsydl {
	
	private String qzh;//全宗号
	private String mlh;//目录号
	private String flh;//分类号
	private String ajh;//案卷号
	private String zrf;//转让方
	private String srf;//受让方
	private String tdzl;//土地坐落
	private String wh;//文号
	private String pfwh;//批复文号
	private String bz;//备注
	private String sqr;//申请人
	private String yddw;//用地单位
	private String yt;//用途
	private String id;//
	private String tdsyz;//土地使用者
	private String tdzh;//土地证号
	private String zdh;//宗地号
	private String pzwh;//批准文号
	private String lbh;//类别号
	
	
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
	public String getFlh() {
		return flh;
	}
	public void setFlh(String flh) {
		this.flh = flh;
	}
	public String getAjh() {
		return ajh;
	}
	public void setAjh(String ajh) {
		this.ajh = ajh;
	}
	public String getZrf() {
		return zrf;
	}
	public void setZrf(String zrf) {
		this.zrf = zrf;
	}
	public String getSrf() {
		return srf;
	}
	public void setSrf(String srf) {
		this.srf = srf;
	}
	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getPfwh() {
		return pfwh;
	}
	public void setPfwh(String pfwh) {
		this.pfwh = pfwh;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getSqr() {
		return sqr;
	}
	public void setSqr(String sqr) {
		this.sqr = sqr;
	}
	public String getYddw() {
		return yddw;
	}
	public void setYddw(String yddw) {
		this.yddw = yddw;
	}
	public String getYt() {
		return yt;
	}
	public void setYt(String yt) {
		this.yt = yt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTdsyz() {
		return tdsyz;
	}
	public void setTdsyz(String tdsyz) {
		this.tdsyz = tdsyz;
	}
	public String getTdzh() {
		return tdzh;
	}
	public void setTdzh(String tdzh) {
		this.tdzh = tdzh;
	}
	public String getZdh() {
		return zdh;
	}
	public void setZdh(String zdh) {
		this.zdh = zdh;
	}
	public String getPzwh() {
		return pzwh;
	}
	public void setPzwh(String pzwh) {
		this.pzwh = pzwh;
	}
	public String getLbh() {
		return lbh;
	}
	public void setLbh(String lbh) {
		this.lbh = lbh;
	}
	public Jsydl() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public Jsydl(Map<String,Object> parameters){
		Class<Jsydl> c = Jsydl.class;
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
		String sql="insert into jsydl(#) values (%)";
		Class<Djyw> c = Djyw.class;
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
	
	
	
}
