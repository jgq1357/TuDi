package com.land.actions.htgl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.land.actions.LandSupport;
import com.land.daos.QuanXianDAO;
import com.land.daos.QuanXianDAOImp;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.domain.Condition;
import com.land.domain.Role;

public class PreBigClassAction extends LandSupport{
	private List<Condition> existFields;
	private RecordDAO dao = new RecordDAOImp();
	private List<Role> roles = new ArrayList<Role>();
	private QuanXianDAO dao2 = new QuanXianDAOImp();
	
	Logger log = Logger.getLogger(PreBigClassAction.class.getName());
	public String execute() {
		try {
			
			existFields = dao.getAllFields();
			roles = dao2.getRoles();
		} catch (SQLException e) {
			log.error("获取字段名失败。");
			log.error(e);
			return "error";
		}
		return "success";
	}
	public List<Condition> getExistFields() {
		return existFields;
	}
	public void setExistFields(List<Condition> existFields) {
		this.existFields = existFields;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	
}
