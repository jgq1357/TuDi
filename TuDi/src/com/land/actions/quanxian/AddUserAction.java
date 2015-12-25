package com.land.actions.quanxian;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.Permission;
import com.land.domain.Role;
import com.land.domain.User;
import com.land.domain.UserPermission;
import com.land.util.DigestUtil;

public class AddUserAction extends LandSupport {
	private User user;
	private String userid;
	private String roleId;
	private List<Role> roles = new ArrayList<Role>();
	// private List<Permission> permissions;
	// private List<Integer> pers = new ArrayList<Integer>();
	private QuanXianDAO dao = new QuanXianDAOImp();
	private String errorMessage;
	private Logger log = Logger.getLogger(AddUserAction.class);

	public String execute() {
		userid = user.getId();
		// permissions = dao.getpermissions();

		if (user.getUserName().trim().length() == 0) {
			setErrors("userName", "用户名不能为空");
		}

		if (user.getSex().trim().length() == 0) {
			setErrors("sex", "性别不能为空");
		}
		if (user.getPassword().trim().length() == 0) {
			setErrors("password", "密码不能为空");
		}
		if (user.getSywz().trim().length() == 0) {
			setErrors("miss_sy", "水印文字不能为空");
		}

		if (roleId == null || roleId.trim().length() == 0) {
			setErrors("role", "请选择角色");
		}

		if (hasErrorMessages()) {
			setparams();
			return "input";
		}

		user.setPassword(DigestUtil.digestMd5(user.getPassword()));
		if (!dao.addUser(user)) {
			dao.deleteUser(userid);
			errorMessage = "添加用户失败。";
			return "error";
		} else {
			dao.addUserRole(userid, roleId);
		}

		if (insertUserPermissions()) {
			log.info("添加用户"+"添加用户[" + userid + "]成功");
			return "success";
		} else {
			dao.deleteUser(userid);
			errorMessage = "添加用户失败";
			return "error";
		}

	}

	private void setparams() {

		try {
			roles = dao.getRoles();
		} catch (SQLException e) {
			log.error("系统出错"+ "读取角色名称失败。");
			log.error("读取角色名称失败。");
			log.error(e);
			errorMessage = "系统出错,请检查错误并稍后再试。";
		}
	}

	private boolean insertUserPermissions() {
		try {
			List<Permission> allPermissions = dao.getpermissions();
			List<Permission> rolePermissions = dao.getRolePermissions(roleId);
			List<UserPermission> userPermissions = new ArrayList<UserPermission>();

			for (int i = 0; i < allPermissions.size(); i++) {
				UserPermission userPermission = new UserPermission();
				Permission p = allPermissions.get(i);
				String available = "N";
				for (int j = 0; j < rolePermissions.size(); j++) {
					if (rolePermissions.get(j).getId() == p.getId()) {
						available = "Y";
						break;
					}
				}

				userPermission.setUserId(userid);
				userPermission.setPermissionId(p.getId());
				userPermission.setAvailable(available);

				userPermissions.add(userPermission);
			}
			dao.addUserPermissions(userPermissions);

		} catch (SQLException e) {
			log.error("系统出错"+ "插入用户权限失败。");
			log.error("插入用户权限失败。");
			log.error(e);
			return false;
		}
		return true;
	}

	/*
	 * private boolean updateUserPermissions(List<Permission> permissions) {
	 * for(int i=0;i<permissions.size();i++) { Permission p =
	 * permissions.get(i);
	 * 
	 * String available = "N"; for(int j=0;j<pers.size();j++) {
	 * if(pers.get(j)==null) { continue; } if(pers.get(j)==p.getId()) {
	 * available="Y"; break; } }
	 * if(!dao.addUserPermissions(user.getId(),p.getId(),available)) { return
	 * false; } } return true; }
	 */

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
