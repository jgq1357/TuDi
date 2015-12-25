package com.land.actions.quanxian;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Permission;

public class UpdateUserAction extends LandSupport{
	private String pers;
	private String userId;
	private String flag="N";
	private Logger log = Logger.getLogger(UpdateUserAction.class);
	
	private QuanXianDAO dao = new QuanXianDAOImp();
	private WorkTimeDAO dao2 = new WorkTimeDAOImp();
	public String execute() {
		if(splitPermissions()) {
			log.info("更新用户"+"更新用户["+userId+"]权限成功");
			flag = "Y";
		}else {
			log.error("更新用户"+ "更新用户["+userId+"]权限失败");
			log.error("更新用户["+userId+"]权限失败");
			return "error";
		}
		return "success";
	}


	private boolean splitPermissions() {
		try {
			List<Permission> permissions = dao2.getAllPermissions();
			String[] ps = pers.split(",");
			for(int i=0;i<permissions.size();i++) {
				Permission p = permissions.get(i);
				String available = "N";
				if(pers.trim().length()!=0) {
					for(int j=0;j<ps.length;j++) {
						int id = Integer.parseInt(ps[j]);
						if(p.getId()==id) {
							available = "Y";
							break;
						}
					}
				}
				if(!dao.updateUserPermissions(userId, p.getId(), available)) {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			return false;
		}
	}
	
	@JSON(serialize=false)
	public String getPers() {
		return pers;
	}


	public void setPers(String pers) {
		this.pers = pers;
	}


	@JSON(serialize=false)
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getFlag() {
		return flag;
	}


	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	
}
