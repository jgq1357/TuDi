package com.land.actions.worktime;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Approve;

public class WaitApproveAction extends LandSupport {
	private String id;
	private int pid;
	private Approve approve;
	
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public Approve getApprove() {
		return approve;
	}

	public void setApprove(Approve approve) {
		this.approve = approve;
	}

	public String execute() {
		approve = dao.getApprove(id,pid);
		return "success";
	}
}
