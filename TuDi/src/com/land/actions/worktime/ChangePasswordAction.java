package com.land.actions.worktime;

import com.land.actions.LandSupport;
import com.land.daos.UserDAO;
import com.land.daos.UserDAOImpl;
import com.land.domain.User;
import com.land.util.DigestUtil;

public class ChangePasswordAction extends LandSupport{
	private String oldPassword;
	private String newPassword;
	private String repassword;
	
	private UserDAO dao = new UserDAOImpl();
	
	public String execute() {
		if(newPassword.trim().length()==0) {
			setErrors("newpasw", "密码不能为空");
		}
		if(!newPassword.trim().equals(repassword.trim())) {
			setErrors("pasw", "新密码与确认密码不一致");
		}
		if(hasErrorMessages()) {
			return "input";
		}
		
		User user = (User) session.get("user");
		try {
			User u =dao.findLoginInfo(user.getUserName(), DigestUtil.digestMd5(oldPassword));
			if(u==null) {
				setErrors("oldpasw", "旧密码输入不正确");
				return "input";
			}
			
			if(dao.updatePassword(user.getId(),DigestUtil.digestMd5(newPassword))) {
				return "success";
			}else {
				setErrors("meg", "修改密码失败，");
				return "input";
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}


	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public String getNewPassword() {
		return newPassword;
	}


	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	public String getRepassword() {
		return repassword;
	}


	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	
	
	
}
