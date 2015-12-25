package com.land.actions.user;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;

public class LoginOutAction extends LandSupport{
	private Logger log = Logger.getLogger(LoginOutAction.class);
	public String execute() {
		log.info("退出成功。");
		session.remove("user");
		session = null;
 		return "success";
	}
}
