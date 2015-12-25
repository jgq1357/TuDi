package com.land.actions.quanxian;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.UserDAO;
import com.land.daos.UserDAOImpl;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Permission;
import com.land.domain.User;

public class UpdateUserPageAction extends LandSupport{
	
	private String userId;
	private User user;
	private List<Permission> existPermissions = new ArrayList<Permission>();
	private List<Permission> noPermissions = new ArrayList<Permission>();
	private UserDAO dao = new UserDAOImpl();
	private WorkTimeDAO dao2 = new WorkTimeDAOImp();
	
	public String execute() {
		user = dao.getUserById(userId);
		try {
			existPermissions = dao.getPermissions(user);
			noPermissions = dao2.getAllPermissions();
			updateNoPermissions();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[ERROR]:获取权限失败");
		}
		
		return "success";
	}
	
	private void updateNoPermissions() {
		for(int i=noPermissions.size()-1;i>=0;i--) {
			Permission p = noPermissions.get(i);
			for(int j=0;j<existPermissions.size();j++) {
				if(p.getId()==existPermissions.get(j).getId()) {
					noPermissions.remove(i);
				}
			}
		}
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<Permission> getExistPermissions() {
		return existPermissions;
	}

	public void setExistPermissions(List<Permission> existPermissions) {
		this.existPermissions = existPermissions;
	}

	public List<Permission> getNoPermissions() {
		return noPermissions;
	}

	public void setNoPermissions(List<Permission> noPermissions) {
		this.noPermissions = noPermissions;
	}
	
	
}
