package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Permission;
import com.land.domain.Role;
import com.land.domain.User;
import com.land.domain.UserPermission;

public interface QuanXianDAO {
	public boolean addUser(User user);
	public void addUserRole(String userId, String roleId);
	public boolean addUserPermissions(List<UserPermission> userPermissions) throws SQLException;
	public String getuserid();
	public List<Permission> getpermissions();
	public List<User> getAllUsers(int start, int end);
	public int countUsers();
	public List<User> queryUsers(String userName);
	public boolean deleteUser(String userid);
	public boolean updateUserPermissions(String userid,int permissionid,String available);
	public List<Role> getRoles() throws SQLException;
	public List<Permission> getRolePermissions(String roleId) throws SQLException;
	public boolean deleteRole(String roleId) throws SQLException;
	public void insertRole(String roleName,String desc, String[] pers) throws SQLException;
	public List<User> getRoleUserList(String roleName) throws SQLException;
	public String getRoleName(String roleId) throws SQLException;
}
