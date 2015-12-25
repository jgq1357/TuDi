package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.land.domain.Menu;
import com.land.domain.Permission;
import com.land.domain.User;
import com.land.util.DbUtil;

public class UserDAOImpl implements UserDAO {
	private Logger log = Logger.getLogger(UserDAOImpl.class);
	@SuppressWarnings("unchecked")
	public ArrayList<Permission> getPermissions(User user)throws Exception{
		ArrayList<Permission> permissions = new ArrayList<Permission>();

		log.debug("***********role-permission**************");
		String roleId = getRoleId(user.getId());
		ArrayList<Permission> rolePermission = getPermissionsByRoleId(roleId);
		for (Iterator iterator = rolePermission.iterator(); iterator.hasNext();) {
			Permission permission = (Permission) iterator.next();
			permissions.add(permission);
			System.out.println(permission.getName());
		}
		
		log.debug("***********user-permission**************");
		ArrayList<Permission> userPermission = getPermissionsByUser(user);
		for (Iterator iterator = userPermission.iterator(); iterator.hasNext();) {
			Permission permission = (Permission) iterator.next();
			permissions.add(permission);
			System.out.println(permission.getName());
		}
		return permissions;
	}
	
	@Override
	public ArrayList<Permission> getPermissionsByRoleId(String roleId)throws Exception {
		ArrayList<Permission> permissions = new ArrayList<Permission>();
		StringBuilder sb = new StringBuilder();
		sb.append("select * from PERMISSIONS where id in (");
		sb.append("select PERMISSIONID from ROLE_PERMISSION where ROLEID = ?");
		sb.append(")");
		String sql = sb.toString();
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, roleId);
		ResultSet rs = pct.executeQuery();
		while(rs.next()) {
			Permission permission = new Permission(rs.getInt(1),rs.getString(2));
			permissions.add(permission);
		}
		rs.close();
		DbUtil.closeConnection(conn);
		return permissions;
	}
	
	@Override
	public ArrayList<Permission> getPermissionsByUser(User user) throws Exception{
		ArrayList<Permission> permissions = new ArrayList<Permission>();
		String sql = "SELECT id,name FROM permissions " +
				"where id in " +
				"(select permissionid from userpermissions " +
				"where available='Y' and userid in " +
				"(select id from users where username=?))";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, user.getUserName());
		ResultSet rs = pct.executeQuery();
		while(rs.next()) {
			Permission permission = new Permission(rs.getInt(1),rs.getString(2));
			permissions.add(permission);
		}
		rs.close();
		DbUtil.closeConnection(conn);
		return permissions;
	}
	
	@Override
	public String getRoleId(String userId) throws Exception{
		StringBuilder sb = new StringBuilder();
		sb.append("select ROLEID from USER_ROLE where USERID = ?");
		String sql = sb.toString();
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, userId);
		ResultSet rs = pct.executeQuery();
		String roleId = null;
		if(rs.next()){
			roleId = rs.getString(1);
		}
		rs.close();
		DbUtil.closeConnection(conn);
		return roleId;
	}
	
	public User findLoginInfo(String userName, String password) throws Exception{
		User user = null;
		String sql = "SELECT id,username,password,lastip,lastlogin,sex,department,birthday,sywz FROM users where username=? and password=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, userName);
		pct.setString(2, password);
		ResultSet rs = pct.executeQuery();
		if(rs.next()) {
			user = new User();
			user.setId(rs.getString(1));
			user.setUserName(rs.getString(2));
			user.setPassword(rs.getString(3));
			user.setLastIp(rs.getString(4));
			user.setLastlogin(rs.getTimestamp(5));
			user.setSex(rs.getString(6));
			user.setDepartment(rs.getString(7));
			user.setBirthday(rs.getString(8));
			user.setSywz(rs.getString(9));
		}
		rs.close();
		DbUtil.closeConnection(conn);
		return user;
		
	}
	
	
	@Override
	public void updateLastLoginTimeAndIp(User user){
		// TODO Auto-generated method stub
		String sql = "update users set lastlogin=sysdate,lastip=? where username=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, user.getLastIp());
			pct.setString(2, user.getUserName());
			pct.execute();
		} catch (SQLException e) {
			System.out.println("[warning]:update LastLogin and ip failed.");
		}finally {
			DbUtil.closeConnection(conn);
		}
	}
	
	@Test
	public void test() throws Exception{
		User user = new User();
		user.setUserName("pxj");
		List<Menu> menus = getMenus(user);
		System.out.println(menus.get(1).getSubname());
	}

	@Override
	public List<Menu> getMenus(User user) throws Exception {
		// TODO Auto-generated method stub
		List<Menu> menus = new ArrayList<Menu>();
		String sql = "select id,name,subname,link from menu order by id";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		ResultSet rs = pct.executeQuery();
		while(rs.next()) {
			Menu menu = new Menu();
			menu.setId(rs.getInt(1));
			menu.setName(rs.getString(2));
			menu.setSubname(rs.getString(3));
			menu.setLink(rs.getString(4));
			menus.add(menu);
		}
		DbUtil.closeConnection(conn);
		return menus;
	}

	@Override
	public boolean updatePassword(String userid, String pas) {
		String sql = "update users set password=? where id=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, pas);
			pct.setString(2, userid);
			pct.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[WARRING]:用户["+userid+"]修改密码失败");
			return false;
		}
		
		return true;
	}

	@Override
	public User getUserById(String userId) {
		User user = new User();
		String sql = "select * from users where id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct;
		try {
			pct = conn.prepareStatement(sql);
			pct.setString(1, userId);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				user.setId(rs.getString("id"));
				user.setBirthday(rs.getString("birthday"));
				user.setDepartment(rs.getString("department"));
				user.setSex(rs.getString("sex"));
				user.setUserName(rs.getString("username"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return user;
	}

	


}
