package com.land.actions.main;

import com.land.actions.LandSupport;
import com.land.domain.Jsyd;
import com.land.services.JsydService;
import com.land.services.JsydServiceImpl;
import com.land.util.LogUtil;

public class JsydInfoAction extends LandSupport{
	private JsydService jsydService = new JsydServiceImpl();
	private Jsyd jsyd;
	private String daId;
	
	private Logger log = Logger.getLogger(.class);
	
	public String execute() {
		try {
			jsyd = jsydService.getJsydById(daId);
			
		} catch (Exception e) {
			logIntoDb("系统出错", "读取建设用地档案["+daId+"]信息出错。");
			log.error("读取建设用地档案["+daId+"]信息出错。"+e.getMessage());
			log.error(e);
		}
		return "success";
	}

	public Jsyd getJsyd() {
		return jsyd;
	}

	public void setJsyd(Jsyd jsyd) {
		this.jsyd = jsyd;
	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}
	
	
}
