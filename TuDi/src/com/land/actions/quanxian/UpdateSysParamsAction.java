package com.land.actions.quanxian;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.SysParamsDAO;
import com.land.daos.SysParamsDAOImp;
import com.land.domain.Sys;

public class UpdateSysParamsAction extends LandSupport{

	private Sys sys;
	private String update="N";
	private String errorMessage;
	SysParamsDAO dao = new SysParamsDAOImp();
	public String execute() {
		try {
			if("N".equalsIgnoreCase(update)) {
				sys = dao.getParams();
			}else {
				Logger log = Logger.getLogger(UpdateSysParamsAction.class);
				//log.setLevel(sys.getLogLevel());
				//log.setLogFilePath(sys.getLogPath());
				dao.updateParams(sys);
			}
		} catch (SQLException e) {
			errorMessage="更新或获取系统参数失败！";
			
			return "error";
		}
		return "success";
	}
	
	

	public String getUpdate() {
		return update;
	}



	public void setUpdate(String update) {
		this.update = update;
	}



	public String getErrorMessage() {
		return errorMessage;
	}



	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public Sys getSys() {
		return sys;
	}

	public void setSys(Sys sys) {
		this.sys = sys;
	}
	
	
}
