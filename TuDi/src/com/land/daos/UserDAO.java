package com.land.daos;

import java.util.ArrayList;
import java.util.List;

import com.land.domain.Menu;
import com.land.domain.Permission;
import com.land.domain.User;

public interface UserDAO {
	public ArrayList<Permission> getPermissions(User user)throws Exception;
	public ArrayList<Permission> getPermissionsByRoleId(String roleId) throws Exception;
	public ArrayList<Permission> getPermissionsByUser(User user) throws Exception;
	public String getRoleId(String userId)throws Exception;
	public User findLoginInfo(String userId, String password) throws Exception;
	public void updateLastLoginTimeAndIp(User user)throws Exception;
	public List<Menu> getMenus(User user) throws Exception;
	public boolean updatePassword(String userid,String pas);
	public User getUserById(String userId);
}
