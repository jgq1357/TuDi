package com.land.domain;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class Djyw {
	private String id;
	private String qzh;
	private String mlh;
	private String ajh;
	private String flh;
	private String fldh;
	private String tdsyz;
	private String zdh;
	private String tdzh;
	private String tfh;
	private String djlb;
	private String syzxz;
	private String qsxz;
	private String syqxz;
	private String syqlx;
	private String tdyt;
	private String dl;
	private String synx;
	private String zzrq;
	private String jzrjl;
	private String jzmd;
	private String jzlx;
	private String jzwqs;
	private String tddj;
	private String bddj;
	private String sbdj;
	private String zjbh;
	private String dzmj;
	private String ftmj;
	private String syqmj;
	private String jzmj;
	private String jzzdmj;
	private String tdzl;
	private String east;
	private String south;
	private String west;
	private String north;
	private String lxdh;
	private String txdz;
	private String ytdsyz;
	private String ytdzh;
	private String ylxdh;
	private String yzjbh;
	private String ytxdz;
	private String jbr;
	private String jbryj;
	private String jbrq;
	private String csryj;
	private String csr;
	private String csrq;
	private String shryj;
	private String shr;
	private String shrq;
	private String qsm;
	private String yqsm;
	private int lbh;
	private String wtdw;
	private String bpgdw;
	private String gjbgbh;
	private String sqr;
	private String dyzsh;
	private String ZH;
	private String DJH;
	private String bz;

	private String spr;
	private String spryj;
	private String sprrq;
	
	
	
	


	public String getSprrq() {
		return sprrq;
	}

	public void setSprrq(String sprrq) {
		this.sprrq = sprrq;
	}

	public String getSpr() {
		return spr;
	}

	public void setSpr(String spr) {
		this.spr = spr;
	}

	public String getSpryj() {
		return spryj;
	}

	public void setSpryj(String spryj) {
		this.spryj = spryj;
	}



	public String getBz() {
		return bz;
	}

	public void setBz(String bz) {
		this.bz = bz;
	}

	public String getZH() {
		return ZH;
	}

	public void setZH(String zH) {
		ZH = zH;
	}

	public String getDJH() {
		return DJH;
	}

	public void setDJH(String dJH) {
		DJH = dJH;
	}


	
	public Djyw(){
		
	}
	
	@SuppressWarnings("unchecked")
	public Djyw(Map<String,Object> parameters){
		Class<Djyw> c = Djyw.class;
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
		String sql="insert into djyw(#) values (%)";
		Class<Djyw> c = Djyw.class;
		Field fields[] = c.getDeclaredFields();
		String f = "";
		String v = "DJYW_SEQ.nextval";
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
	
	

	

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}

	public String getQsm() {
		return qsm;
	}

	public void setQsm(String qsm) {
		this.qsm = qsm;
	}

	public String getYqsm() {
		return yqsm;
	}

	public void setYqsm(String yqsm) {
		this.yqsm = yqsm;
	}

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
	public String getFlh() {
		return flh;
	}
	public void setFlh(String flh) {
		this.flh = flh;
	}
	public String getFldh() {
		return fldh;
	}
	public void setFldh(String fldh) {
		this.fldh = fldh;
	}
	public String getTdsyz() {
		return tdsyz;
	}
	public void setTdsyz(String tdsyz) {
		this.tdsyz = tdsyz;
	}
	public String getZdh() {
		return zdh;
	}
	public void setZdh(String zdh) {
		this.zdh = zdh;
	}
	public String getTdzh() {
		return tdzh;
	}
	public void setTdzh(String tdzh) {
		this.tdzh = tdzh;
	}
	public String getTfh() {
		return tfh;
	}
	public void setTfh(String tfh) {
		this.tfh = tfh;
	}
	public String getDjlb() {
		return djlb;
	}
	public void setDjlb(String djlb) {
		this.djlb = djlb;
	}
	public String getSyzxz() {
		return syzxz;
	}
	public void setSyzxz(String syzxz) {
		this.syzxz = syzxz;
	}
	public String getQsxz() {
		return qsxz;
	}
	public void setQsxz(String qsxz) {
		this.qsxz = qsxz;
	}
	public String getSyqxz() {
		return syqxz;
	}
	public void setSyqxz(String syqxz) {
		this.syqxz = syqxz;
	}
	public String getSyqlx() {
		return syqlx;
	}
	public void setSyqlx(String syqlx) {
		this.syqlx = syqlx;
	}
	public String getTdyt() {
		return tdyt;
	}
	public void setTdyt(String tdyt) {
		this.tdyt = tdyt;
	}
	public String getDl() {
		return dl;
	}
	public void setDl(String dl) {
		this.dl = dl;
	}
	public String getSynx() {
		return synx;
	}
	public void setSynx(String synx) {
		this.synx = synx;
	}
	public String getZzrq() {
		return zzrq;
	}
	public void setZzrq(String zzrq) {
		this.zzrq = zzrq;
	}
	public String getJzrjl() {
		return jzrjl;
	}
	public void setJzrjl(String jzrjl) {
		this.jzrjl = jzrjl;
	}
	public String getJzmd() {
		return jzmd;
	}
	public void setJzmd(String jzmd) {
		this.jzmd = jzmd;
	}
	public String getJzlx() {
		return jzlx;
	}
	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}
	public String getJzwqs() {
		return jzwqs;
	}
	public void setJzwqs(String jzwqs) {
		this.jzwqs = jzwqs;
	}
	public String getTddj() {
		return tddj;
	}
	public void setTddj(String tddj) {
		this.tddj = tddj;
	}
	public String getBddj() {
		return bddj;
	}
	public void setBddj(String bddj) {
		this.bddj = bddj;
	}
	public String getSbdj() {
		return sbdj;
	}
	public void setSbdj(String sbdj) {
		this.sbdj = sbdj;
	}
	public String getZjbh() {
		return zjbh;
	}
	public void setZjbh(String zjbh) {
		this.zjbh = zjbh;
	}
	public String getDzmj() {
		return dzmj;
	}
	public void setDzmj(String dzmj) {
		this.dzmj = dzmj;
	}
	public String getFtmj() {
		return ftmj;
	}
	public void setFtmj(String ftmj) {
		this.ftmj = ftmj;
	}
	public String getSyqmj() {
		return syqmj;
	}
	public void setSyqmj(String syqmj) {
		this.syqmj = syqmj;
	}
	public String getJzmj() {
		return jzmj;
	}
	public void setJzmj(String jzmj) {
		this.jzmj = jzmj;
	}
	public String getJzzdmj() {
		return jzzdmj;
	}
	public void setJzzdmj(String jzzdmj) {
		this.jzzdmj = jzzdmj;
	}
	public String getTdzl() {
		return tdzl;
	}
	public void setTdzl(String tdzl) {
		this.tdzl = tdzl;
	}
	
	public String getEast() {
		return east;
	}

	public void setEast(String east) {
		this.east = east;
	}

	public String getSouth() {
		return south;
	}

	public void setSouth(String south) {
		this.south = south;
	}

	public String getWest() {
		return west;
	}

	public void setWest(String west) {
		this.west = west;
	}

	public String getNorth() {
		return north;
	}

	public void setNorth(String north) {
		this.north = north;
	}

	public String getLxdh() {
		return lxdh;
	}
	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}
	public String getTxdz() {
		return txdz;
	}
	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}
	public String getYtdsyz() {
		return ytdsyz;
	}
	public void setYtdsyz(String ytdsyz) {
		this.ytdsyz = ytdsyz;
	}
	public String getYtdzh() {
		return ytdzh;
	}
	public void setYtdzh(String ytdzh) {
		this.ytdzh = ytdzh;
	}
	public String getYlxdh() {
		return ylxdh;
	}
	public void setYlxdh(String ylxdh) {
		this.ylxdh = ylxdh;
	}
	public String getYtxdz() {
		return ytxdz;
	}
	public void setYtxdz(String ytxdz) {
		this.ytxdz = ytxdz;
	}
	public String getJbryj() {
		return jbryj;
	}
	public void setJbryj(String jbryj) {
		this.jbryj = jbryj;
	}
	public String getJbrq() {
		return jbrq;
	}
	public void setJbrq(String jbrq) {
		this.jbrq = jbrq;
	}
	public String getCsryj() {
		return csryj;
	}
	public void setCsryj(String csryj) {
		this.csryj = csryj;
	}
	public String getCsr() {
		return csr;
	}
	public void setCsr(String csr) {
		this.csr = csr;
	}
	public String getCsrq() {
		return csrq;
	}
	public void setCsrq(String csrq) {
		this.csrq = csrq;
	}
	public String getShryj() {
		return shryj;
	}
	public void setShryj(String shryj) {
		this.shryj = shryj;
	}
	public String getShr() {
		return shr;
	}
	public void setShr(String shr) {
		this.shr = shr;
	}

	public String getShrq() {
		return shrq;
	}

	public void setShrq(String shrq) {
		this.shrq = shrq;
	}

	public String getYzjbh() {
		return yzjbh;
	}

	public void setYzjbh(String yzjbh) {
		this.yzjbh = yzjbh;
	}

	public String getJbr() {
		return jbr;
	}

	public void setJbr(String jbr) {
		this.jbr = jbr;
	}

	public String getWtdw() {
		return wtdw;
	}

	public void setWtdw(String wtdw) {
		this.wtdw = wtdw;
	}

	public String getBpgdw() {
		return bpgdw;
	}

	public void setBpgdw(String bpgdw) {
		this.bpgdw = bpgdw;
	}

	public String getGjbgbh() {
		return gjbgbh;
	}

	public void setGjbgbh(String gjbgbh) {
		this.gjbgbh = gjbgbh;
	}

	public String getSqr() {
		return sqr;
	}

	public void setSqr(String sqr) {
		this.sqr = sqr;
	}

	public String getDyzsh() {
		return dyzsh;
	}

	public void setDyzsh(String dyzsh) {
		this.dyzsh = dyzsh;
	}

	
}
