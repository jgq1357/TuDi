package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Apply;
import com.land.domain.User;

public class RequestAction extends LandSupport{
	private int applyPermission;
	private String description;
	
	private List<Apply> applies = new ArrayList<Apply>();
	
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	private Logger log = Logger.getLogger(RequestAction.class);
	public String execute() {
		User user = (User) session.get("user");
		if(dao.insertRequest(user.getId(), applyPermission, description)){
			try {
				applies=dao.getapplies(user);
			} catch (Exception e) {
				log.error("系统出错"+"提交借阅申请失败。");
				e.printStackTrace();
				return "error";
			}
			log.info("借阅申请"+"提交借阅申请成功。");
			return "success";
		}else {
			return "error";
		}
		
	}

	

	public List<Apply> getApplies() {
		return applies;
	}



	public void setApplies(List<Apply> applies) {
		this.applies = applies;
	}



	public int getApplyPermission() {
		return applyPermission;
	}



	public void setApplyPermission(int applyPermission) {
		this.applyPermission = applyPermission;
	}



	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
