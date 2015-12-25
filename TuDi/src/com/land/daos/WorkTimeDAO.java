package com.land.daos;

import java.util.List;

import com.land.domain.Apply;
import com.land.domain.Approve;
import com.land.domain.Permission;
import com.land.domain.User;

public interface WorkTimeDAO {
	public List<Permission> getAllPermissions() throws Exception;
	public boolean insertRequest(String userId, int permissionId,String description);
	public List<Apply> getapplies(User user) throws Exception;
	public List<String> getAllApplies();
	public List<String> getApproveResult(User user);
	public List<Approve> getAllApproves();
	public Approve getApprove(String uid, int pid);
	public boolean approve(String uid, int pid, String result, User user);
	public boolean deleteApplies(User user);
}
