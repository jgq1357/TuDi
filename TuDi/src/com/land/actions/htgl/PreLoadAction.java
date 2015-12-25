package com.land.actions.htgl;

import java.sql.SQLException;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.daos.RecordDAO;
import com.land.daos.RecordDAOImp;
import com.land.domain.Menu;

public class PreLoadAction extends LandSupport{

	private List<Menu> menus;
	public String execute() {
		try {
			RecordDAO dao = new RecordDAOImp();
			menus = dao.getDaMenu();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return "error";
		}
		return "success";
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	
}
