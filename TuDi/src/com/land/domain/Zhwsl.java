package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Zhwsl {
	private String qzh;//全宗号，
	private String mlh;//目录号，
	private String ajh;//案卷号，
	private String flh;//分类号
	private String nd;//年度，
	private String tm;//题名，
	private String wh;//文号，
	private String zrz;//责任者，
	private String gjc;//关键词，
	private String ztc;//主题词，
	private String xcsj;//形成时间。
	private String bgqx;//保管期限，
	private String jh;//件号，
	private String xlb;//小类别。综合文书类下面有两类。ajj：表示案卷级，wjj：表示文件集
	
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
	public String getTm() {
		return tm;
	}
	public void setTm(String tm) {
		this.tm = tm;
	}
	public String getWh() {
		return wh;
	}
	public void setWh(String wh) {
		this.wh = wh;
	}
	public String getZrz() {
		return zrz;
	}
	public void setZrz(String zrz) {
		this.zrz = zrz;
	}
	public String getGjc() {
		return gjc;
	}
	public void setGjc(String gjc) {
		this.gjc = gjc;
	}
	public String getZtc() {
		return ztc;
	}
	public void setZtc(String ztc) {
		this.ztc = ztc;
	}
	public String getXcsj() {
		return xcsj;
	}
	public void setXcsj(String xcsj) {
		this.xcsj = xcsj;
	}

	public String getBgqx() {
		return bgqx;
	}
	public void setBgqx(String bgqx) {
		this.bgqx = bgqx;
	}
	public String getJh() {
		return jh;
	}
	public void setJh(String jh) {
		this.jh = jh;
	}
	public String getXlb() {
		return xlb;
	}
	public void setXlb(String xlb) {
		this.xlb = xlb;
	}
	public Zhwsl() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public Zhwsl(Map<String,Object> parameters){
		Class<Zhwsl> c = Zhwsl.class;
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
		String sql="insert into zhwsl(#) values (%)";
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
