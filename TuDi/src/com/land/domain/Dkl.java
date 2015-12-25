package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/***
 * 地矿类
 * @author Administrator
 *	
 */
public class Dkl {
	public String qzh;//全宗号
	public String mlh;//目录号
	public String ajh;//案卷号
	public String flh;//分类号
	public String nd;//年度
	public String sqdw;//申请单位
	public String ckqr;//采矿权人
	public String ksdz;//矿山地址
	public String kkxkz;//开矿许可证号
	public String ksmc;//矿山名称
	public String jjlx;//经济类型
	public String fzjg;//发证机关
	public String fzrq;//发证日期
	public String yxqx;//有效期限
	
	public Dkl() {
		// TODO Auto-generated constructor stub
	}
	@SuppressWarnings("unchecked")
	public Dkl(Map<String,Object> parameters){
		Class<Dkl> c = Dkl.class;
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
		String sql="insert into dkl(#) values (%)";
		Class<Dkl> c = Dkl.class;
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
	public String getSqdw() {
		return sqdw;
	}
	public void setSqdw(String sqdw) {
		this.sqdw = sqdw;
	}
	public String getCkqr() {
		return ckqr;
	}
	public void setCkqr(String ckqr) {
		this.ckqr = ckqr;
	}
	public String getKsdz() {
		return ksdz;
	}
	public void setKsdz(String ksdz) {
		this.ksdz = ksdz;
	}

	public String getKkxkz() {
		return kkxkz;
	}
	public void setKkxkz(String kkxkz) {
		this.kkxkz = kkxkz;
	}
	public String getKsmc() {
		return ksmc;
	}
	public void setKsmc(String ksmc) {
		this.ksmc = ksmc;
	}
	public String getJjlx() {
		return jjlx;
	}
	public void setJjlx(String jjlx) {
		this.jjlx = jjlx;
	}
	public String getFzjg() {
		return fzjg;
	}
	public void setFzjg(String fzjg) {
		this.fzjg = fzjg;
	}
	public String getFzrq() {
		return fzrq;
	}
	public void setFzrq(String fzrq) {
		this.fzrq = fzrq;
	}
	public String getYxqx() {
		return yxqx;
	}
	public void setYxqx(String yxqx) {
		this.yxqx = yxqx;
	}

}
