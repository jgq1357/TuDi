package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Permission;
import com.land.domain.User;

public class ApplyAction extends LandSupport{
	private List<Permission>  permissions = new ArrayList<Permission>();
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	public String execute() {
		try {
			permissions = dao.getAllPermissions();
			removeExistPermissions();
		} catch (Exception e) {
			System.out.println("[ERROR]:读取所有权限失败。");
			return "error";
		}
		return "success";
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	private void removeExistPermissions() {
		User user = (User) session.get("user");
		List<Permission> userPer = user.getPermissions();
		for (int i=permissions.size()-1; i>=0; i--) {
			for(int j=0;j<userPer.size();j++) {
				if(permissions.get(i).getId()==userPer.get(j).getId()) {
					permissions.remove(i);
				}
			}
		}
	}
	
}
