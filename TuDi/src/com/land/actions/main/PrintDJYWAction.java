package com.land.actions.main;

import com.land.actions.LandSupport;
import com.land.domain.Djyw;
import com.land.services.DjywService;
import com.land.services.DjywServiceImpl;

public class PrintDJYWAction extends LandSupport {
	private DjywService djywService = new DjywServiceImpl();
	public String ljxx;//立卷信息
	public String jbxx;//基本信息
	public String zrxx;//转让信息
	public String splc;//审批流程
	public Djyw djyw;//地籍信息
	public String dah;//
	
	
	public String getDah() {
		return dah;
	}
	public void setDah(String dah) {
		this.dah = dah;
	}
	public Djyw getDjyw() {
		return djyw;
	}
	public void setDjyw(Djyw djyw) {
		this.djyw = djyw;
	}
	@Override
	public String execute() throws Exception {
		if(dah != null && !dah.trim().equalsIgnoreCase(""))
		{
			djyw = djywService.getDjywByDah(dah);
		}
		return "success";
	}
	public String getLjxx() {
		return ljxx;
	}
	public void setLjxx(String ljxx) {
		this.ljxx = ljxx;
	}
	public String getJbxx() {
		return jbxx;
	}
	public void setJbxx(String jbxx) {
		this.jbxx = jbxx;
	}
	public String getZrxx() {
		return zrxx;
	}
	public void setZrxx(String zrxx) {
		this.zrxx = zrxx;
	}
	public String getSplc() {
		return splc;
	}
	public void setSplc(String splc) {
		this.splc = splc;
	}


	

}
