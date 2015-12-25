package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Apply;
import com.land.domain.User;

public class ListAction extends LandSupport{
	private String description;
	
	private List<Apply> applies = new ArrayList<Apply>();
	
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	private Logger log = Logger.getLogger(ListAction.class);
	public String execute() {
		User user = (User) session.get("user");
			try {
				applies=dao.getapplies(user);
				dao.deleteApplies(user);
			} catch (Exception e) {
				log.error("系统出错"+"提交借阅申请失败。");
				e.printStackTrace();
				return "error";
			}
			log.info("借阅申请"+"提交借阅申请成功。");
			return "success";
		
		
	}
	

	public List<Apply> getApplies() {
		return applies;
	}


	public void setApplies(List<Apply> applies) {
		this.applies = applies;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
