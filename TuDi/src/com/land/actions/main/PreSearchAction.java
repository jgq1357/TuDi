package com.land.actions.main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.land.actions.LandSupport;
import com.land.daos.PreSearchDAO;
import com.land.daos.PreSearchDAOImp;
import com.land.domain.Condition;
import com.land.domain.Dalb;

public class PreSearchAction extends LandSupport {

	private int daType;
	private List<Condition> conditions = new ArrayList<Condition>();
	private List<Dalb> dalbs = new ArrayList<Dalb>();
	private PreSearchDAO dao = new PreSearchDAOImp();
	private String allsc = "";
	Logger log = Logger.getLogger(PreSearchAction.class);
	private String shortName;

	public String execute() {
		try {
			conditions = dao.getConditions(daType);
			dalbs = dao.getDalbs(daType);
			shortName = dao.getShortName(daType);
			resetSearchCondition();
			calAllsc();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error("获取搜索条件失败。");
			log.error(e);
			return "error";
		}
		return "success";
	}

	private void resetSearchCondition() {
		for (int i = 0; i < dalbs.size(); i++) {
			String[] cols = dalbs.get(i).getSearchCondition().split(",");
			String sc = "";
			for (int k = 0; k < conditions.size(); k++) {
				String con = conditions.get(k).getAlpha() + "_line,N";
				for (int j = 0; j < cols.length; j++) {
					if (cols[j].trim().equalsIgnoreCase(
							conditions.get(k).getAlias().trim())) {
						con = conditions.get(k).getAlpha() + "_line,Y";
						break;
					}
				}
				sc = sc + con;
				sc = sc + "|";
			}
			sc = sc + dalbs.get(i).getLbh();
			dalbs.get(i).setSearchCondition(sc);
		}
	}

	private void calAllsc() {
		for (int i = 0; i < conditions.size(); i++) {
			if ("S".equalsIgnoreCase(conditions.get(i).getValue())) {
				allsc = allsc + conditions.get(i).getAlpha() + "_line,Y";
			} else {
				allsc = allsc + conditions.get(i).getAlpha() + "_line,N";
			}
			allsc = allsc + "|";
		}
		allsc = allsc + "0";
	}

	@Test
	public void test() throws SQLException {
		/*
		 * PreSearchAction p = new PreSearchAction(); p.setDaType(3);
		 * p.execute(); System.out.println(); for(int i=0;i<p.dalbs.size();i++)
		 * { System.out.println(p.dalbs.get(i)); }
		 */
		for (int i = 1; i < 101; i++) {
			System.out.println("private String col" + i + "m;");
		}
	}

	
	
	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getAllsc() {
		return allsc;
	}

	public void setAllsc(String allsc) {
		this.allsc = allsc;
	}

	public int getDaType() {
		return daType;
	}

	public void setDaType(int daType) {
		this.daType = daType;
	}

	public List<Condition> getConditions() {
		return conditions;
	}

	public void setConditions(List<Condition> conditions) {
		this.conditions = conditions;
	}

	public List<Dalb> getDalbs() {
		return dalbs;
	}

	public void setDalbs(List<Dalb> dalbs) {
		this.dalbs = dalbs;
	}

}
