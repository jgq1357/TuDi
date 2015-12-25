package com.land.actions.quanxian;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.Permission;

public class AddRolePageAction extends LandSupport{
	
	private List<Permission> allPermissions = new ArrayList<Permission>();
	
	QuanXianDAO dao = new QuanXianDAOImp();
	public String execute() {
		allPermissions = dao.getpermissions();
		return "success";
	}
	public List<Permission> getAllPermissions() {
		return allPermissions;
	}
	public void setAllPermissions(List<Permission> allPermissions) {
		this.allPermissions = allPermissions;
	}
	
	
}
