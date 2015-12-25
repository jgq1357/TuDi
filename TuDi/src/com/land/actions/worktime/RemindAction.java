package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import com.land.actions.LandSupport;
import com.land.daos.UserDAO;
import com.land.daos.UserDAOImpl;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.User;

public class RemindAction extends LandSupport{
	private List<String> processes = new ArrayList<String>();
	private int numOfRemind=0;
	private String link="../worktime/list";
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	public String execute() {
		User user = (User) session.get("user");
		produceProcess(user);
		UserDAO udao = new UserDAOImpl();
		try {
			user.setPermissions(udao.getPermissions(user));
			session.put("user", user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
	
	private void produceProcess(User user) {
		
		processes.addAll(dao.getApproveResult(user));
		if("Y".equals(user.includePermission("借阅审批"))) {
			processes = dao.getAllApplies();
			link="../worktime/approvelist";
		}
	}
	
	public String countRemindNum() {
		User user = (User) session.get("user");
		produceProcess(user);
		numOfRemind = processes.size();
		return "success";
	}
	
	
	@JSON(serialize=false)
	public String getLink() {
		return link;
	}



	public void setLink(String link) {
		this.link = link;
	}



	public int getNumOfRemind() {
		return numOfRemind;
	}



	public void setNumOfRemind(int numOfRemind) {
		this.numOfRemind = numOfRemind;
	}


	@JSON(serialize=false)
	public List<String> getProcesses() {
		return processes;
	}

	public void setProcesses(List<String> processes) {
		this.processes = processes;
	}

	
}
