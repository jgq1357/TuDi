package com.land.actions.worktime;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;
import com.land.daos.WorkTimeDAO;
import com.land.daos.WorkTimeDAOImp;
import com.land.domain.Approve;
import com.land.domain.User;

public class ApproveAction extends LandSupport{
	private String uid;
	private int pid;
	private String approveResult;
	private String errorMessage;
	private List<Approve> approves = new ArrayList<Approve>();
	private WorkTimeDAO dao = new WorkTimeDAOImp();
	private Logger log = Logger.getLogger(ApproveAction.class);
	public String execute() {
		User user = (User) session.get("user");
		if(!dao.approve(uid,pid,approveResult.trim(),user)) {
			errorMessage = "审核失败。";
			return "error";
		}
		approves = dao.getAllApproves();
		log.info("借阅审批"+"批准了用户编号为["+uid+"]的权限["+pid+"]的申请");
		return "success";
	}

	
	public String getErrorMessage() {
		return errorMessage;
	}


	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}


	public List<Approve> getApproves() {
		return approves;
	}


	public void setApproves(List<Approve> approves) {
		this.approves = approves;
	}


	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getApproveResult() {
		return approveResult;
	}

	public void setApproveResult(String approveResult) {
		this.approveResult = approveResult;
	}
	
	
}
