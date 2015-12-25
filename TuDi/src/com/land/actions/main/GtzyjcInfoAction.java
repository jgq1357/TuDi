package com.land.actions.main;

import com.land.actions.LandSupport;
import com.land.domain.Gtzyjc;
import com.land.services.GtzyjcService;
import com.land.services.GtzyjcServiceImpl;
import com.land.util.LogUtil;

public class GtzyjcInfoAction extends LandSupport{
	private String daId;
	private GtzyjcService djywService = new GtzyjcServiceImpl();
	private Gtzyjc gtzyjc;
	private Logger log = Logger.getLogger(.class);
	
	public String execute() {
		try {
			gtzyjc = djywService.getGtzyjcById(daId);
			
		} catch (NumberFormatException e) {
			log.error("档案Id不为整数。国土资源档案["+daId+"]"+e.getMessage());
			log.error(e);
		} catch (Exception e) {
			logIntoDb("系统出错", "读取国土资源档案["+daId+"]信息出错。");
			log.error("读取国土资源档案["+daId+"]信息出错。"+e.getMessage());
			log.error(e);
		}
		return "success";
	}

	public String getDaId() {
		return daId;
	}

	public void setDaId(String daId) {
		this.daId = daId;
	}

	public Gtzyjc getGtzyjc() {
		return gtzyjc;
	}

	public void setGtzyjc(Gtzyjc gtzyjc) {
		this.gtzyjc = gtzyjc;
	}
	
	
}
