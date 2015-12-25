package com.land.actions.quanxian;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;

public class AddRoleAction extends LandSupport{
	
	private String roleName;
	private String description;
	private String pers;
	private String flag="N";
	private String errorMessage;
	private QuanXianDAO dao = new QuanXianDAOImp();
	private Logger log = Logger.getLogger(AddRoleAction.class);
	public String execute() {
		if(roleName==null || roleName.trim().length()==0) {
			errorMessage = "角色名不能为空。";
			return "sucess";
		}
		
		if(pers==null || pers.trim().length()==0) {
			errorMessage = "请给角色赋予权限。";
			return "sucess";
		}
		
		try {
			dao.insertRole(roleName, description, pers.split(","));
			flag="Y";
		} catch (SQLException e) {
			errorMessage="系统出错，请稍后再试！";
			log.error("插入角色失败！");
			log.error(e);
			return "error";
		}
		return "success";
		
	}
	

	
	
	
	
	@JSON(serialize=false)
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	@JSON(serialize=false)
	public String getDescription() {
		return description;
	}
	@JSON(serialize=false)
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@JSON(serialize=false)
	public String getPers() {
		return pers;
	}
	public void setPers(String pers) {
		this.pers = pers;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
