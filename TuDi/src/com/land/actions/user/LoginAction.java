package com.land.actions.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.UserDAO;
import com.land.daos.UserDAOImpl;
import com.land.domain.Permission;
import com.land.domain.User;
import com.land.util.DigestUtil;

public class LoginAction extends LandSupport{
	private User user;
	Logger log = Logger.getLogger(LoginAction.class.getName()); 
	private UserDAO dao = new UserDAOImpl();
	public String execute() {
		
		try {
			if(session.get("user")!=null) {
				User u = (User)session.get("user");
				List<Permission> permissions = dao.getPermissions(u);
				if(permissions.size()!=0) {
					u.setPermissions(permissions);
				}
				session.put("user", u);
				return "success";
			}
			if(user!=null){
				String pas = DigestUtil.digestMd5(user.getPassword().trim());
				User u =dao.findLoginInfo(user.getUserName().trim(), pas);
				if(u!=null) {
					List<Permission> permissions = dao.getPermissions(u);
					if(permissions.size()!=0) {
						u.setPermissions(permissions);
					}
					u.setLastIp(request.getRemoteAddr());
					session.put("user", u);
					dao.updateLastLoginTimeAndIp(u);
					log.debug("用户"+u.getUserName()+"登录成功。");
					return "success";
				}
				log.debug("用户名或密码错误");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	

	
	public UserDAO getDao() {
		return dao;
	}
	public void setDao(UserDAO dao) {
		this.dao = dao;
	}
	

	
}
