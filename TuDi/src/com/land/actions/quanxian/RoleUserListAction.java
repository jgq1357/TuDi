package com.land.actions.quanxian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.User;

public class RoleUserListAction extends LandSupport{

	private List<User> users = new ArrayList<User>();
	private String roleId;
	private String roleName;
	private QuanXianDAO dao = new QuanXianDAOImp();
	private Logger log = Logger.getLogger(RoleUserListAction.class);
	public String execute() {
		try {
			roleName = dao.getRoleName(roleId);
			users = dao.getRoleUserList(roleName);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e);
		}
		return "success";
	}


	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public List<User> getUsers() {
		return users;
	}


	public void setUsers(List<User> users) {
		this.users = users;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
		
	}
	
	
}
