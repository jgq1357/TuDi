package com.land.actions.quanxian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.Role;

public class FirstPageAction extends LandSupport {
	private String userid;
	private List<Role> roles = new ArrayList<Role>();
	private QuanXianDAO dao = new QuanXianDAOImp();
	
	private Logger log = Logger.getLogger(FirstPageAction.class);
	private String errorMessage;
	public String execute () {
		userid = dao.getuserid();
		try {
			roles = dao.getRoles();
		} catch (SQLException e) {
			log.error("系统出错"+"读取角色名称失败。");
			log.error("读取角色名称失败。");
			log.error(e);
			errorMessage = "系统出错,请检查错误并稍后再试。";
			return "error";
		}
		return "success";
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	
	
}
