package com.land.actions.user;

import java.util.ArrayList;
import java.util.List;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;
import com.land.daos.UserDAO;
import com.land.daos.UserDAOImpl;
import com.land.domain.Menu;
import com.land.domain.Permission;
import com.land.domain.User;

public class MenuAction extends LandSupport{
	private List<Menu> dajss = new ArrayList<Menu>();
	private List<Menu> jygls = new ArrayList<Menu>();
	private List<Menu> daszls = new ArrayList<Menu>();
	private List<Menu> xtwhs = new ArrayList<Menu>();
	private List<Menu> htgls = new ArrayList<Menu>();
	private List<Menu> tjbbs = new ArrayList<Menu>();
	private UserDAO dao = new UserDAOImpl();
	private Logger log = Logger.getLogger(MenuAction.class);
	public String execute() {
		try {
			User user = (User)session.get("user");
			splitMenus(dao.getMenus(user),user);
		} catch (Exception e) {
			log.error("系统出错"+"获取菜单失败。");
			System.out.println("[ERROR]:获取菜单失败。");
		}
		return "success";
	}
	
	private void splitMenus(List<Menu> menus, User user) {
		for(int i=0; i<menus.size(); i++) {
			Menu menu = menus.get(i);
			if("档案检索".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())){
				dajss.add(menu);
			}else if ("借阅管理".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())) {
				jygls.add(menu);
			}else if ("档案室专栏".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())){
				daszls.add(menu);
			}else if ("系统维护".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())){
				xtwhs.add(menu);
			}else if ("后台管理".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())){
				htgls.add(menu);
			}else if ("统计报表".equals(menu.getName()) && checkPermission(menu.getSubname(), user.getPermissions())){
				tjbbs.add(menu);
			}
		}
	}
	
	private boolean checkPermission (String subName,List<Permission> permissions) {
		for(int i=0;i<permissions.size();i++) {
			Permission permission = permissions.get(i);
			if(subName.equals(permission.getName())) {
				return true;
			}
		}
		return false;
	}

	
	
	public List<Menu> getTjbbs() {
		return tjbbs;
	}

	public void setTjbbs(List<Menu> tjbbs) {
		this.tjbbs = tjbbs;
	}

	public List<Menu> getDajss() {
		return dajss;
	}

	public void setDajss(List<Menu> dajss) {
		this.dajss = dajss;
	}

	public List<Menu> getJygls() {
		return jygls;
	}

	public void setJygls(List<Menu> jygls) {
		this.jygls = jygls;
	}

	public List<Menu> getDaszls() {
		return daszls;
	}

	public void setDaszls(List<Menu> daszls) {
		this.daszls = daszls;
	}

	public List<Menu> getXtwhs() {
		return xtwhs;
	}

	public void setXtwhs(List<Menu> xtwhs) {
		this.xtwhs = xtwhs;
	}

	public List<Menu> getHtgls() {
		return htgls;
	}

	public void setHtgls(List<Menu> htgls) {
		this.htgls = htgls;
	}

	
	
	
}
