package com.land.actions.quanxian;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.domain.User;

public class DeleteUserAction extends LandSupport{

	private String userid;
	private String flag="N";
	private Logger log = Logger.getLogger(DeleteUserAction.class);
	private QuanXianDAO dao = new QuanXianDAOImp();
	public String execute() {
		User u = (User)session.get("user");
		if(u.getId().trim().equalsIgnoreCase(userid))
		{
			log.info("删除用户"+"删除用户["+userid+"]失败");
			flag = "N";
			return "success";
		}
		
		if(dao.deleteUser(userid)) {
			flag = "Y";
			log.info("删除用户"+ "删除用户["+userid+"]成功");
		}
		return "success";
	}
	
	@JSON(serialize=false)
	public String getUserid() {
		return userid;
	}
	
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
