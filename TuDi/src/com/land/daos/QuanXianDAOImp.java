package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.domain.Permission;
import com.land.domain.Role;
import com.land.domain.User;
import com.land.domain.UserPermission;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class QuanXianDAOImp implements QuanXianDAO {
	private Logger log = Logger.getLogger(QuanXianDAOImp.class);

	@Override
	public String getuserid() {
		String sql = "select max(id) from users where id like 'u%'";
		Connection conn = DbUtil.getConnection();
		String userid="u000001";
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1)==null) {
					return userid;
				}
				userid = rs.getString(1).substring(1);
				int id = Integer.parseInt(userid);
				id = id+1;
				userid=id+"";
				String pre="";
				for(int i=0;i<6-userid.length();i++) {
					pre=pre+"0";
				}
				userid="u"+pre+id;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return userid;
	}

	@Override
	public List<Permission> getpermissions() {
		// TODO Auto-generated method stub
		List<Permission> pers = new ArrayList<Permission>();
		String sql = "select * from permissions";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				Permission p = new Permission();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				pers.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		return pers;
	}

	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into users(id,username,password,sex,department,birthday,sywz) values (?,?,?,?,?,?,?)";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, user.getId());
			pct.setString(2, user.getUserName());
			pct.setString(3, user.getPassword());
			pct.setString(4, user.getSex());
			pct.setString(5, user.getDepartment());
			pct.setString(6, user.getBirthday());
			pct.setString(7, user.getSywz());
			pct.execute();
		} catch (SQLException e) {
			log.error("添加用户失败。");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return true;
	}

	@Override
	public boolean addUserPermissions(List<UserPermission> userPermissions) throws SQLException{
		log.info("开始添加用户权限......");
		String sql = "insert into userpermissions(userid,permissionid,available) values(?,?,?)";
		Connection conn = DbUtil.getConnection();
		
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<userPermissions.size();i++) {
			UserPermission userPermission = userPermissions.get(i);
			pct.setString(1, userPermission.getUserId());
			pct.setInt(2, userPermission.getPermissionId());
			pct.setString(3, userPermission.getAvailable());
			pct.addBatch();
		}
		pct.executeBatch();
		
		/*try {
			pct.setString(1, userid);
			pct.setInt(2, permissionid);
			pct.setString(3, available);
			pct.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:插入用户权限失败");
			return false;
		}finally {
			
		}*/
		DbUtil.closeConnection(conn);
		return true;
	}
	
	/*@Test
	public void TE() {
		String userid="u000001";
		String available="N";
		for(int i=10; i<=23; i++) {
			addUserPermissions(userid, i, available);
		}
	}*/

	@Override
	public List<User> getAllUsers(int start, int end) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "select * from (select u.*,rownum r from users u) where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setInt(1, start);
			pct.setInt(2, end);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setBirthday(rs.getString("birthday"));
				user.setDepartment(rs.getString("department"));
				user.setSex(rs.getString("sex"));
				user.setUserName(rs.getString("username"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return users;
	}

	@Override
	public int countUsers() {
		// TODO Auto-generated method stub
		int total=1;
		String sql = "select count(*) from users";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			if(rs.next()) {
				total = rs.getInt(1);
				total = (total-1)/Constant.ROWSOFSEARCHRESULT+1;
				return total;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("统计用户数量失败");
			return 1;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return 1;
	}

	@Override
	public List<User> queryUsers(String userName) {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();
		String sql = "select * from users where username=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userName);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setBirthday(rs.getString("birthday"));
				user.setDepartment(rs.getString("department"));
				user.setSex(rs.getString("sex"));
				user.setUserName(rs.getString("username"));
				users.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return users;
	}

	@Override
	public boolean deleteUser(String userid) {
		log.info("--------开始删除用户【"+userid+"】---------");

		if(deleteFromApplies(userid)&& deleteFromApplog(userid)&&deleteUserPermissions(userid)&&deleteFromUserRoles(userid)) {
			String sql = "delete from users where id=?";
			Connection conn = DbUtil.getConnection();
			try {
				PreparedStatement pct = conn.prepareStatement(sql);
				pct.setString(1, userid);
				pct.execute();
				log.info("--------删除用户【"+userid+"】成功---------");
				return true;
			} catch (SQLException e) {
				System.out.println("[ERROR]:删除用户失败。");
				return false;
			}finally {
				DbUtil.closeConnection(conn);
			}
		}
		return false;
	}
	
	private boolean deleteFromUserRoles(String userid) {
		log.info("开始删除用户角色......");
		String sql = "delete from user_role where userid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userid);
			pct.execute();
		} catch (SQLException e) {
			log.error("删除用户角色失败");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除用户角色成功");
		return true;
	}
	
	private boolean deleteFromApplies(String userid) {
		log.info("开始删除用户申请......");
		String sql = "delete from applies where userid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userid);
			pct.execute();
			
		} catch (SQLException e) {
			log.error("删除用户申请失败");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除用户申请成功");
		return true;
	}
	
	private boolean deleteFromApplog(String userid) {
		log.info("开始删除用户日志......");
		String sql = "delete from applog where userid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userid);
			pct.execute();
			
		} catch (SQLException e) {
			log.error("删除用户日志失败");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除用户日志成功");
		return true;
	}
	
	private boolean deleteUserPermissions(String userid) {
		log.info("开始删除用户权限......");
		String sql = "delete from userpermissions where userid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userid);
			pct.execute();
			
		} catch (SQLException e) {
			log.error("删除用户权限失败");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除用户权限成功");
		return true;
	}

	@Override
	public boolean updateUserPermissions(String userid, int permissionid,
			String available) {
		log.info("开始更新用户权限......");
		String sql = "update userpermissions set available=? where userid=? and permissionid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, available);
			pct.setString(2, userid);
			pct.setInt(3, permissionid);
			pct.execute();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
	}

	@Override
	public List<Role> getRoles() throws SQLException {
		log.info("开始获取所有角色......");
		String sql = "select roleid, rolename, createtime from role order by roleid";
		List<Role> roles = new ArrayList<Role>();
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				Role role = new Role();
				role.setRoleId(rs.getString(1));
				role.setRoleName(rs.getString(2));
				role.setCreateTime(rs.getDate(3));
				roles.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return roles;
	}

	@Override
	public List<Permission> getRolePermissions(String roleId) throws SQLException {
		log.info("开始获取角色权限......");
		List<Permission> pers = new ArrayList<Permission>();
		String sql = "select PERMISSIONID from role_permission where roleid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleId);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				Permission per = new Permission();
				per.setId(rs.getInt(1));
				pers.add(per);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return pers;
	}

	@Override
	public boolean deleteRole(String roleId) throws SQLException {
		// TODO Auto-generated method stub
		log.info("开始删除角色......角色Id："+roleId);
		if(deleteUserRole(roleId) && deleteRolePermissions(roleId)) {
			String sql = "delete from role where roleid=?";
			Connection conn = DbUtil.getConnection();
			try {
				PreparedStatement pct = conn.prepareStatement(sql);
				pct.setString(1, roleId);
				pct.execute();
				return true;
			} catch (SQLException e) {
				log.error("删除角色失败。");
				log.error(e);
				return false;
			}finally {
				DbUtil.closeConnection(conn);
			}
		}
		return false;
	}
	
	private boolean deleteUserRole(String roleId) {
		log.info("开始删除用户角色......");
		String sql="delete from user_role where roleid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleId);
			pct.execute();
		} catch (SQLException e) {
			log.error("删除用户角色失败。");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除用户角色成功。");
		return true;
	}
	
	private boolean deleteRolePermissions(String roleId) {
		log.info("开始删除角色权限......");
		String sql="delete from role_permission where roleid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleId);
			pct.execute();
		} catch (SQLException e) {
			log.error("删除角色权限失败。");
			log.error(e);
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		log.info("删除角色权限成功。");
		return true;
	}

	@Override
	public void insertRole(String roleName, String desc, String[] pers)
			throws SQLException {
		log.info("开始新建角色......");
		String sql = "insert into role(roleid,rolename,createtime,description) values(TUDI_SEQ.nextval,?,sysdate,?)";
		Connection conn = DbUtil.getConnection();
		try {
			
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleName);
			pct.setString(2, desc);
			pct.execute();
			String roleId = getRoleId(roleName);
			if(roleId.trim().length()!=0) {
				insertRolePermissions(roleId, pers);
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		
	}
	
	private String getRoleId(String roleName) throws SQLException{
		String sql = "select roleid from role where rolename=?";
		Connection conn = DbUtil.getConnection();
		String roleId;
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleName);
			ResultSet rs = pct.executeQuery();
			roleId = "";
			while(rs.next()) {
				roleId=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return roleId;
	}
	
	private void insertRolePermissions(String roleid, String[] pers) throws SQLException {
		log.info("插入角色权限......");
		String sql = "insert into ROLE_PERMISSION(roleid,permissionid) values(?,?) ";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			for(int i=0;i<pers.length;i++) {
				pct.setString(1, roleid);
				pct.setInt(2, Integer.parseInt(pers[i]));
				pct.addBatch();
			}
			pct.executeBatch();
		} catch (SQLException e) {
			log.error("插入角色权限失败.");
			log.error(e);
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
	}

	@Override
	public List<User> getRoleUserList(String roleName) throws SQLException {
		// TODO Auto-generated method stub
		log.info("获取角色用户列表......");
		List<User> users = new ArrayList<User>();
		String sql = "select username,sex,department,birthday from users where id in (select u.userid from user_role u,role r where u.roleid=r.roleid and r.rolename=?)";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleName);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setUserName(rs.getString(1));
				user.setSex(rs.getString(2));
				user.setDepartment(rs.getString(3));
				user.setBirthday(rs.getString(4));
				users.add(user);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return users;
	}

	@Override
	public String getRoleName(String roleId) throws SQLException {
		String sql = "select rolename from role where roleid=?";
		Connection conn = DbUtil.getConnection();
		String rolename;
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, roleId);
			ResultSet rs = pct.executeQuery();
			rolename = "";
			while(rs.next()) {
				rolename=rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return rolename;
	}
	
	public void addUserRole(String userId, String roleId) {
		log.info("开始插入用户角色......");
		String sql = "insert into user_role(userid,roleid) values (?,?)";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userId);
			pct.setString(2, roleId);
			pct.execute();
			log.info("用户角色插入成功。");
		} catch (SQLException e) {
			
			log.error("用户角色插入失败");
			log.error(e);
			
		}finally {
			DbUtil.closeConnection(conn);
		}
		
	}

}
