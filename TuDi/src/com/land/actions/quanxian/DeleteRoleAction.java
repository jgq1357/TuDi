package com.land.actions.quanxian;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;

public class DeleteRoleAction extends LandSupport{
	private String roleId;
	private String flag="N";
	private QuanXianDAO dao = new QuanXianDAOImp();
	private Logger log = Logger.getLogger(DeleteRoleAction.class);
	@JSON(serialize=false)
	public String getRoleId() {
		return roleId;
	}



	public String execute() {
		try {
			if(dao.deleteRole(roleId)) {
				flag = "Y";
				log.info("删除角色"+"删除角色["+roleId+"]成功");
			}
		} catch (SQLException e) {
			log.error("系统错误"+"删除角色["+roleId+"]失败");
			
		}
		return "success";
	}
	

	
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getFlag() {
		return flag;
	}
	
	public void setFlag(String flag) {
		this.flag = flag;
	}
}
