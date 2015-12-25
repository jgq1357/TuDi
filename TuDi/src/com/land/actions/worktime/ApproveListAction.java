package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Approve;

public class ApproveListAction extends LandSupport{
	private List<Approve> approves = new ArrayList<Approve>();
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	public String execute() {
		approves = dao.getAllApproves();
		return "success";
	}
	public List<Approve> getApproves() {
		return approves;
	}
	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}
	
	
}
