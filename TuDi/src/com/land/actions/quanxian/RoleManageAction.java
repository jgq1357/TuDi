package com.land.actions.quanxian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.Role;

public class RoleManageAction extends LandSupport{
	private List<Role> roles = new ArrayList<Role>();
	private Logger log = Logger.getLogger(RoleManageAction.class);
	private QuanXianDAO dao = new QuanXianDAOImp();
	public String execute() {
		try {
			roles = dao.getRoles();
		} catch (SQLException e) {
			log.error("系统出错"+"获取角色名称出错。");
			log.error("获取角色名称出错");
			log.error(e);
		}
		
		return "success";
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
