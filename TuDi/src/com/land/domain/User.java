package com.land.domain;

import java.sql.Timestamp;
import java.util.List;

public class User {
	private String id;
	private String userName;
	private String password;
	private String lastIp;
	private Timestamp lastlogin;
	private List<Permission> permissions;
	private String sex;
	private String department;
	private String birthday;
	private String sywz;
	private String roleId;
	
	
	
	public String getRoleId() {
		return roleId;
	}


	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}


	public User() {
	}
	
	
	public User(String id, String userName, String lastIp,
			Timestamp lastlogin, List<Permission> permissions, String sex,
			String department, String birthday) {
		super();
		this.id = id;
		this.userName = userName;
		this.lastIp = lastIp;
		this.lastlogin = lastlogin;
		this.permissions = permissions;
		this.sex = sex;
		this.department = department;
		this.birthday = birthday;
	}


	public String includePermission(String permission) {
		
		for(int i=0;i<permissions.size();i++) {
			if(permission.equals(permissions.get(i).getName())) {
				return "Y";
			}
		}
		return "N";
	}
	
	
	public String getSywz() {
		return sywz;
	}


	public void setSywz(String sywz) {
		this.sywz = sywz;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getSex() {
		return sex;
	}


	public void setSex(String sex) {
		this.sex = sex;
	}


	public String getDepartment() {
		return department;
	}


	public void setDepartment(String department) {
		this.department = department;
	}


	public String getBirthday() {
		return birthday;
	}


	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLastIp() {
		return lastIp;
	}
	public void setLastIp(String lastIp) {
		this.lastIp = lastIp;
	}
	public Timestamp getLastlogin() {
		return lastlogin;
	}
	public void setLastlogin(Timestamp lastlogin) {
		this.lastlogin = lastlogin;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}


	@Override
	public String toString() {
		
		return id+"-"+userName+"-"+department;
	}
	
	
	
	
	
}
