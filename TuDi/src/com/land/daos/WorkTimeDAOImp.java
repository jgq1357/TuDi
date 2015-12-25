package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.land.domain.Apply;
import com.land.domain.Approve;
import com.land.domain.Permission;
import com.land.domain.User;
import com.land.util.DbUtil;

public class WorkTimeDAOImp implements WorkTimeDAO{

	@Override
	public List<Permission> getAllPermissions() throws Exception {
		List<Permission> permissions = new ArrayList<Permission>();
		String sql = "select * from permissions";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		ResultSet rs = pct.executeQuery();
		while(rs.next()) {
			Permission permission = new Permission();
			permission.setId(rs.getInt(1));
			permission.setName(rs.getString(2));
			permissions.add(permission);
		}
		DbUtil.closeConnection(conn);
		return permissions;
	}

	@Override
	public boolean insertRequest(String userId, int permissionId,
			String description){
		// TODO Auto-generated method stub
		String sql ="insert into applies(userid,permissionid,applytime,description,ispass) values (?,?,sysdate,?,'A')";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, userId);
			pct.setInt(2, permissionId);
			pct.setString(3, description);
			pct.execute();
		} catch (SQLException e) {
			if(e.getMessage().contains("ORA-00001")) {
				return true;
			}
			System.out.println("[ERROR]:用户申请插入失败。");
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return true;
	}

	@Override
	public List<Apply> getapplies(User user) throws Exception {
		List<Apply> applies = new ArrayList<Apply>();
		String sql = "select a.userid, a.permissionid, a.applytime, a.approveby," +
				"a.approvetime, a.ispass,p.name pname " +
				"from applies a inner join permissions p on a.permissionid=p.id where a.userid=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, user.getId());
		ResultSet rs = pct.executeQuery();
		while(rs.next()) {
			Apply apply = new Apply();
			apply.setUserId(rs.getString("userid"));
			apply.setPermissionId(rs.getInt("permissionid"));
			apply.setApplyTime(rs.getTimestamp("applytime").toString());
			String approveby = rs.getString("approveby");
			if(approveby==null || approveby.trim().length()==0) {
				apply.setApproveBy("--");
				apply.setApproveTime("--");
			}else {
				apply.setApproveBy(rs.getString("approveby"));
				apply.setApproveTime(rs.getTimestamp("approvetime").toString());
			}
			if("A".equals(rs.getString("ispass"))){
				apply.setIsPass("已提交");
			}else if ("N".equals(rs.getString("ispass"))) {
				apply.setIsPass("拒绝");
			} else if("Y".equals(rs.getString("ispass"))) {
				apply.setIsPass("已通过");
			}
			
			apply.setApplyType(rs.getString("pname"));
			applies.add(apply);
		}
		DbUtil.closeConnection(conn);
		return applies;
	}
	
	@Override
	public boolean deleteApplies(User user) {
		String sql = "delete from applies where userid=? and ispass<>'B'";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, user.getId());
			pct.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[WARRING]:删除申请记录失败");
			return false;
		}
		return true;
	}

	@Override
	public List<String> getAllApplies() {
		// TODO Auto-generated method stub
		List<String> pro = new ArrayList<String>();
		String sql = "select u.username, a.applytime,p.name pname " +
				"from applies a inner join permissions p " +
				"on a.permissionid=p.id inner join users u " +
				"on a.userid=u.id where a.ispass='A'";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				String process = rs.getString("username")+" 提交了["+rs.getString("pname")+"]申請 "+rs.getTimestamp("applytime").toString();
				pro.add(process);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		return pro;
	}

	@Override
	public List<String> getApproveResult(User user) {
		List<String> pro = new ArrayList<String>();
		String sql = "select a.ispass,p.name pname " +
				"from applies a inner join permissions p " +
				"on a.permissionid=p.id inner join users u " +
				"on a.userid=u.id where a.userid=? " +
				"and a.ispass<>'A'";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, user.getId());
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				String process ="";
				if("Y".equals(rs.getString("ispass"))){
					process = "您的申請["+rs.getString("pname")+"] 已通过审批";
				}else if ("N".equals(rs.getString("ispass"))) {
					process = "您的申請["+rs.getString("pname")+"] 被拒絕";
				}
				pro.add(process);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		return pro;
	}

	@Override
	public List<Approve> getAllApproves() {
		List<Approve> aps = new ArrayList<Approve>();
		String sql = "select u.id,u.username, a.applytime,p.name pname,p.id pid " +
		"from applies a inner join permissions p " +
		"on a.permissionid=p.id inner join users u " +
		"on a.userid=u.id where a.ispass='A'";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			ResultSet rs = pct.executeQuery();
			while(rs.next()) {
				Approve ap = new Approve();
				ap.setUserId(rs.getString("id"));
				ap.setUserName(rs.getString("username"));
				ap.setPerName(rs.getString("pname"));
				ap.setPerId(rs.getInt("pid"));
				ap.setApplyTime(rs.getTimestamp("applytime").toString());
				aps.add(ap);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}
		return aps;
	}

	@Override
	public Approve getApprove(String uid, int pid) {
		String sql = "select u.department,u.username, a.applytime,p.name pname,a.description " +
		"from applies a inner join permissions p " +
		"on a.permissionid=p.id inner join users u " +
		"on a.userid=u.id where a.userid=? and a.permissionid=?";
		Approve ap = new Approve();
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, uid);
			pct.setInt(2, pid);
			ResultSet rs = pct.executeQuery();
			if(rs.next()) {
				ap.setUserId(uid);
				ap.setUserName(rs.getString("username"));
				ap.setApplyTime(rs.getTimestamp("applytime").toString());
				ap.setDepartment(rs.getString("department"));
				ap.setDesc(rs.getString("description"));
				ap.setPerId(pid);
				ap.setPerName(rs.getString("pname"));
			}
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println("[ERROR]:读取申请失败。");
		}finally {
			DbUtil.closeConnection(conn);
		}
		return ap;
	}

	@Override
	public boolean approve(String uid, int pid, String result ,User user) {
		// TODO Auto-generated method stub
		String sql="update applies set ispass=?,approveby=?,approvetime=sysdate where userid=? and permissionid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, result);
			pct.setString(2, user.getId());
			pct.setString(3, uid);
			pct.setInt(4, pid);
			if("Y".equals(result)) {
				if(!updateUserPermission(uid, pid)) {
					return false;
				}
			}
			pct.execute();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[ERROR]:更新申请失败。");
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		
		return true;
	}
	
	private boolean updateUserPermission(String uid, int pid) {
		String sql="update userpermissions set available='Y' where userid=? and permissionid=?";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, uid);
			pct.setInt(2, pid);
			pct.execute();
		} catch (SQLException e) {
			// TODO: handle exception
			return false;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return true;
	}
	
}
