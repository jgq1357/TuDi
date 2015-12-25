package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jboss.logging.Logger;

import com.land.actions.LandSupport;
import com.land.daos.SearchDAO;
import com.land.daos.SearchDAOImp;
import com.land.domain.Record;
import com.land.domain.User;
import com.land.util.Constant;

public class SearchAction extends LandSupport {
	private String lbhStr;
	private String view;
	private String flag;
	private int pageNum = 1;
	private List<Integer> totals = new ArrayList<Integer>();
	private List<Record> records;
	private int total;
	private SearchDAO dao = new SearchDAOImp();
	private Map<String, String> dics;
	private int lbh;
	private int recordsSize;
	private Logger log = Logger.getLogger(SearchAction.class);
	public int getRecordsSize() {
		return recordsSize;
	}

	public void setRecordsSize(int recordsSize) {
		this.recordsSize = recordsSize;
	}

	public int getLbh() {
		return lbh;
	}

	public void setLbh(int lbh) {
		this.lbh = lbh;
	}

	private int daType = 3;
	private String all = "N";
	private String dalb;

	public String execute() {

		if (lbhStr == null && lbh == 0) {
			return "success";
		}

		User user = (User) session.get("user");
		view = user.includePermission("档案浏览");
		records = new ArrayList<Record>();

		String conditions;

		int every = Constant.ROWSOFSEARCHRESULT;

		int end = every * pageNum;
		int start = end - every + 1;
		try {
			String tableName = dao.getTableName(daType);
			dalb = tableName.toLowerCase();
			dics = dao.getDics("COL");

			if ("Y".equals(flag)) {
				conditions = (String) session.get("conditions");
				System.out.println("conditions = " + conditions);
			} else {
				lbhStr = lbhStr.substring(lbhStr.lastIndexOf("|") + 1);
				if (lbhStr != null && lbhStr.trim().length() != 0) {
					lbh = Integer.parseInt(lbhStr);
				}
				conditions = getConditions();
				System.out.println("conditions = " + conditions);
			}

			System.out.println("tableName = " + tableName);
			System.out.println("conditions = " + conditions);

			records = dao.getRecords(tableName, conditions, start, end);
			recordsSize = dao.countTotalSize(conditions, tableName);

			total = (recordsSize - 1) / Constant.ROWSOFSEARCHRESULT + 1;

			countTotals();
			log.info("档案搜索"+"搜索成功");
		} catch (Exception e) {
			log.error("搜索出错"+e.getMessage());
			e.printStackTrace();
			return "error";
		}

		return "success";
	}

	private void countTotals() {
		for (int i = 1; i <= total; i++) {
			totals.add(i);
		}
	}

	private String getConditions() {

		String conditions = " 1=1 and";
		if (col1 != null && col1.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col1"))) {
				if ("on".equals(col1m)) {
					conditions = conditions + getFuzzyConditionsByDah(col1);
				} else {
					conditions = conditions + getExactConditionsByDah(col1);
				}
			} else {
				if ("on".equals(col1m)) {
					conditions = conditions + " " + dics.get("col1")
							+ " like '%" + col1 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col1") + " = '"
							+ col1 + "' and";
				}
			}
		}
		if (col2 != null && col2.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col2"))) {
				if ("on".equals(col2m)) {
					conditions = conditions + getFuzzyConditionsByDah(col2);
				} else {
					conditions = conditions + getExactConditionsByDah(col2);
				}
			} else {
				if ("on".equals(col2m)) {
					conditions = conditions + " " + dics.get("col2")
							+ " like '%" + col2 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col2") + " = '"
							+ col2 + "' and";
				}
			}
		}
		if (col3 != null && col3.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col3"))) {
				if ("on".equals(col3m)) {
					conditions = conditions + getFuzzyConditionsByDah(col3);
				} else {
					conditions = conditions + getExactConditionsByDah(col3);
				}
			} else {
				if ("on".equals(col3m)) {
					conditions = conditions + " " + dics.get("col3")
							+ " like '%" + col3 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col3") + " = '"
							+ col3 + "' and";
				}
			}
		}
		if (col4 != null && col4.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col4"))) {
				if ("on".equals(col4m)) {
					conditions = conditions + getFuzzyConditionsByDah(col4);
				} else {
					conditions = conditions + getExactConditionsByDah(col4);
				}
			} else {
				if ("on".equals(col4m)) {
					conditions = conditions + " " + dics.get("col4")
							+ " like '%" + col4 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col4") + " = '"
							+ col4 + "' and";
				}
			}
		}
		if (col5 != null && col5.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col5"))) {
				if ("on".equals(col5m)) {
					conditions = conditions + getFuzzyConditionsByDah(col5);
				} else {
					conditions = conditions + getExactConditionsByDah(col5);
				}
			} else {
				if ("on".equals(col5m)) {
					conditions = conditions + " " + dics.get("col5")
							+ " like '%" + col5 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col5") + " = '"
							+ col5 + "' and";
				}
			}
		}
		if (col6 != null && col6.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col6"))) {
				if ("on".equals(col6m)) {
					conditions = conditions + getFuzzyConditionsByDah(col6);
				} else {
					conditions = conditions + getExactConditionsByDah(col6);
				}
			} else {
				if ("on".equals(col6m)) {
					conditions = conditions + " " + dics.get("col6")
							+ " like '%" + col6 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col6") + " = '"
							+ col6 + "' and";
				}
			}
		}
		if (col7 != null && col7.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col7"))) {
				if ("on".equals(col7m)) {
					conditions = conditions + getFuzzyConditionsByDah(col7);
				} else {
					conditions = conditions + getExactConditionsByDah(col7);
				}
			} else {
				if ("on".equals(col7m)) {
					conditions = conditions + " " + dics.get("col7")
							+ " like '%" + col7 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col7") + " = '"
							+ col7 + "' and";
				}
			}
		}
		if (col8 != null && col8.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col8"))) {
				if ("on".equals(col8m)) {
					conditions = conditions + getFuzzyConditionsByDah(col8);
				} else {
					conditions = conditions + getExactConditionsByDah(col8);
				}
			} else {
				if ("on".equals(col8m)) {
					conditions = conditions + " " + dics.get("col8")
							+ " like '%" + col8 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col8") + " = '"
							+ col8 + "' and";
				}
			}
		}
		if (col9 != null && col9.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col9"))) {
				if ("on".equals(col9m)) {
					conditions = conditions + getFuzzyConditionsByDah(col9);
				} else {
					conditions = conditions + getExactConditionsByDah(col9);
				}
			} else {
				if ("on".equals(col9m)) {
					conditions = conditions + " " + dics.get("col9")
							+ " like '%" + col9 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col9") + " = '"
							+ col9 + "' and";
				}
			}
		}
		if (col10 != null && col10.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col10"))) {
				if ("on".equals(col10m)) {
					conditions = conditions + getFuzzyConditionsByDah(col10);
				} else {
					conditions = conditions + getExactConditionsByDah(col10);
				}
			} else {
				if ("on".equals(col10m)) {
					conditions = conditions + " " + dics.get("col10")
							+ " like '%" + col10 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col10") + " = '"
							+ col10 + "' and";
				}
			}
		}
		if (col11 != null && col11.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col11"))) {
				if ("on".equals(col11m)) {
					conditions = conditions + getFuzzyConditionsByDah(col11);
				} else {
					conditions = conditions + getExactConditionsByDah(col11);
				}
			} else {
				if ("on".equals(col11m)) {
					conditions = conditions + " " + dics.get("col11")
							+ " like '%" + col11 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col11") + " = '"
							+ col11 + "' and";
				}
			}
		}
		if (col12 != null && col12.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col12"))) {
				if ("on".equals(col12m)) {
					conditions = conditions + getFuzzyConditionsByDah(col12);
				} else {
					conditions = conditions + getExactConditionsByDah(col12);
				}
			} else {
				if ("on".equals(col12m)) {
					conditions = conditions + " " + dics.get("col12")
							+ " like '%" + col12 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col12") + " = '"
							+ col12 + "' and";
				}
			}
		}
		if (col13 != null && col13.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col13"))) {
				if ("on".equals(col13m)) {
					conditions = conditions + getFuzzyConditionsByDah(col13);
				} else {
					conditions = conditions + getExactConditionsByDah(col13);
				}
			} else {
				if ("on".equals(col13m)) {
					conditions = conditions + " " + dics.get("col13")
							+ " like '%" + col13 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col13") + " = '"
							+ col13 + "' and";
				}
			}
		}
		if (col14 != null && col14.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col14"))) {
				if ("on".equals(col14m)) {
					conditions = conditions + getFuzzyConditionsByDah(col14);
				} else {
					conditions = conditions + getExactConditionsByDah(col14);
				}
			} else {
				if ("on".equals(col14m)) {
					conditions = conditions + " " + dics.get("col14")
							+ " like '%" + col14 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col14") + " = '"
							+ col14 + "' and";
				}
			}
		}
		if (col15 != null && col15.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col15"))) {
				if ("on".equals(col15m)) {
					conditions = conditions + getFuzzyConditionsByDah(col15);
				} else {
					conditions = conditions + getExactConditionsByDah(col15);
				}
			} else {
				if ("on".equals(col15m)) {
					conditions = conditions + " " + dics.get("col15")
							+ " like '%" + col15 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col15") + " = '"
							+ col15 + "' and";
				}
			}
		}
		if (col16 != null && col16.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col16"))) {
				if ("on".equals(col16m)) {
					conditions = conditions + getFuzzyConditionsByDah(col16);
				} else {
					conditions = conditions + getExactConditionsByDah(col16);
				}
			} else {
				if ("on".equals(col16m)) {
					conditions = conditions + " " + dics.get("col16")
							+ " like '%" + col16 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col16") + " = '"
							+ col16 + "' and";
				}
			}
		}
		if (col17 != null && col17.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col17"))) {
				if ("on".equals(col17m)) {
					conditions = conditions + getFuzzyConditionsByDah(col17);
				} else {
					conditions = conditions + getExactConditionsByDah(col17);
				}
			} else {
				if ("on".equals(col17m)) {
					conditions = conditions + " " + dics.get("col17")
							+ " like '%" + col17 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col17") + " = '"
							+ col17 + "' and";
				}
			}
		}
		if (col18 != null && col18.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col18"))) {
				if ("on".equals(col18m)) {
					conditions = conditions + getFuzzyConditionsByDah(col18);
				} else {
					conditions = conditions + getExactConditionsByDah(col18);
				}
			} else {
				if ("on".equals(col18m)) {
					conditions = conditions + " " + dics.get("col18")
							+ " like '%" + col18 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col18") + " = '"
							+ col18 + "' and";
				}
			}
		}
		if (col19 != null && col19.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col19"))) {
				if ("on".equals(col19m)) {
					conditions = conditions + getFuzzyConditionsByDah(col19);
				} else {
					conditions = conditions + getExactConditionsByDah(col19);
				}
			} else {
				if ("on".equals(col19m)) {
					conditions = conditions + " " + dics.get("col19")
							+ " like '%" + col19 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col19") + " = '"
							+ col19 + "' and";
				}
			}
		}
		if (col20 != null && col20.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col20"))) {
				if ("on".equals(col20m)) {
					conditions = conditions + getFuzzyConditionsByDah(col20);
				} else {
					conditions = conditions + getExactConditionsByDah(col20);
				}
			} else {
				if ("on".equals(col20m)) {
					conditions = conditions + " " + dics.get("col20")
							+ " like '%" + col20 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col20") + " = '"
							+ col20 + "' and";
				}
			}
		}
		if (col21 != null && col21.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col21"))) {
				if ("on".equals(col21m)) {
					conditions = conditions + getFuzzyConditionsByDah(col21);
				} else {
					conditions = conditions + getExactConditionsByDah(col21);
				}
			} else {
				if ("on".equals(col21m)) {
					conditions = conditions + " " + dics.get("col21")
							+ " like '%" + col21 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col21") + " = '"
							+ col21 + "' and";
				}
			}
		}
		if (col22 != null && col22.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col22"))) {
				if ("on".equals(col22m)) {
					conditions = conditions + getFuzzyConditionsByDah(col22);
				} else {
					conditions = conditions + getExactConditionsByDah(col22);
				}
			} else {
				if ("on".equals(col22m)) {
					conditions = conditions + " " + dics.get("col22")
							+ " like '%" + col22 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col22") + " = '"
							+ col22 + "' and";
				}
			}
		}
		if (col23 != null && col23.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col23"))) {
				if ("on".equals(col23m)) {
					conditions = conditions + getFuzzyConditionsByDah(col23);
				} else {
					conditions = conditions + getExactConditionsByDah(col23);
				}
			} else {
				if ("on".equals(col23m)) {
					conditions = conditions + " " + dics.get("col23")
							+ " like '%" + col23 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col23") + " = '"
							+ col23 + "' and";
				}
			}
		}
		if (col24 != null && col24.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col24"))) {
				if ("on".equals(col24m)) {
					conditions = conditions + getFuzzyConditionsByDah(col24);
				} else {
					conditions = conditions + getExactConditionsByDah(col24);
				}
			} else {
				if ("on".equals(col24m)) {
					conditions = conditions + " " + dics.get("col24")
							+ " like '%" + col24 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col24") + " = '"
							+ col24 + "' and";
				}
			}
		}
		if (col25 != null && col25.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col25"))) {
				if ("on".equals(col25m)) {
					conditions = conditions + getFuzzyConditionsByDah(col25);
				} else {
					conditions = conditions + getExactConditionsByDah(col25);
				}
			} else {
				if ("on".equals(col25m)) {
					conditions = conditions + " " + dics.get("col25")
							+ " like '%" + col25 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col25") + " = '"
							+ col25 + "' and";
				}
			}
		}
		if (col26 != null && col26.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col26"))) {
				if ("on".equals(col26m)) {
					conditions = conditions + getFuzzyConditionsByDah(col26);
				} else {
					conditions = conditions + getExactConditionsByDah(col26);
				}
			} else {
				if ("on".equals(col26m)) {
					conditions = conditions + " " + dics.get("col26")
							+ " like '%" + col26 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col26") + " = '"
							+ col26 + "' and";
				}
			}
		}
		if (col27 != null && col27.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col27"))) {
				if ("on".equals(col27m)) {
					conditions = conditions + getFuzzyConditionsByDah(col27);
				} else {
					conditions = conditions + getExactConditionsByDah(col27);
				}
			} else {
				if ("on".equals(col27m)) {
					conditions = conditions + " " + dics.get("col27")
							+ " like '%" + col27 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col27") + " = '"
							+ col27 + "' and";
				}
			}
		}
		if (col28 != null && col28.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col28"))) {
				if ("on".equals(col28m)) {
					conditions = conditions + getFuzzyConditionsByDah(col28);
				} else {
					conditions = conditions + getExactConditionsByDah(col28);
				}
			} else {
				if ("on".equals(col28m)) {
					conditions = conditions + " " + dics.get("col28")
							+ " like '%" + col28 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col28") + " = '"
							+ col28 + "' and";
				}
			}
		}
		if (col29 != null && col29.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col29"))) {
				if ("on".equals(col29m)) {
					conditions = conditions + getFuzzyConditionsByDah(col29);
				} else {
					conditions = conditions + getExactConditionsByDah(col29);
				}
			} else {
				if ("on".equals(col29m)) {
					conditions = conditions + " " + dics.get("col29")
							+ " like '%" + col29 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col29") + " = '"
							+ col29 + "' and";
				}
			}
		}
		if (col30 != null && col30.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col30"))) {
				if ("on".equals(col30m)) {
					conditions = conditions + getFuzzyConditionsByDah(col30);
				} else {
					conditions = conditions + getExactConditionsByDah(col30);
				}
			} else {
				if ("on".equals(col30m)) {
					conditions = conditions + " " + dics.get("col30")
							+ " like '%" + col30 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col30") + " = '"
							+ col30 + "' and";
				}
			}
		}
		if (col31 != null && col31.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col31"))) {
				if ("on".equals(col31m)) {
					conditions = conditions + getFuzzyConditionsByDah(col31);
				} else {
					conditions = conditions + getExactConditionsByDah(col31);
				}
			} else {
				if ("on".equals(col31m)) {
					conditions = conditions + " " + dics.get("col31")
							+ " like '%" + col31 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col31") + " = '"
							+ col31 + "' and";
				}
			}
		}
		if (col32 != null && col32.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col32"))) {
				if ("on".equals(col32m)) {
					conditions = conditions + getFuzzyConditionsByDah(col32);
				} else {
					conditions = conditions + getExactConditionsByDah(col32);
				}
			} else {
				if ("on".equals(col32m)) {
					conditions = conditions + " " + dics.get("col32")
							+ " like '%" + col32 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col32") + " = '"
							+ col32 + "' and";
				}
			}
		}
		if (col33 != null && col33.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col33"))) {
				if ("on".equals(col33m)) {
					conditions = conditions + getFuzzyConditionsByDah(col33);
				} else {
					conditions = conditions + getExactConditionsByDah(col33);
				}
			} else {
				if ("on".equals(col33m)) {
					conditions = conditions + " " + dics.get("col33")
							+ " like '%" + col33 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col33") + " = '"
							+ col33 + "' and";
				}
			}
		}
		if (col34 != null && col34.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col34"))) {
				if ("on".equals(col34m)) {
					conditions = conditions + getFuzzyConditionsByDah(col34);
				} else {
					conditions = conditions + getExactConditionsByDah(col34);
				}
			} else {
				if ("on".equals(col34m)) {
					conditions = conditions + " " + dics.get("col34")
							+ " like '%" + col34 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col34") + " = '"
							+ col34 + "' and";
				}
			}
		}
		if (col35 != null && col35.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col35"))) {
				if ("on".equals(col35m)) {
					conditions = conditions + getFuzzyConditionsByDah(col35);
				} else {
					conditions = conditions + getExactConditionsByDah(col35);
				}
			} else {
				if ("on".equals(col35m)) {
					conditions = conditions + " " + dics.get("col35")
							+ " like '%" + col35 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col35") + " = '"
							+ col35 + "' and";
				}
			}
		}
		if (col36 != null && col36.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col36"))) {
				if ("on".equals(col36m)) {
					conditions = conditions + getFuzzyConditionsByDah(col36);
				} else {
					conditions = conditions + getExactConditionsByDah(col36);
				}
			} else {
				if ("on".equals(col36m)) {
					conditions = conditions + " " + dics.get("col36")
							+ " like '%" + col36 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col36") + " = '"
							+ col36 + "' and";
				}
			}
		}
		if (col37 != null && col37.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col37"))) {
				if ("on".equals(col37m)) {
					conditions = conditions + getFuzzyConditionsByDah(col37);
				} else {
					conditions = conditions + getExactConditionsByDah(col37);
				}
			} else {
				if ("on".equals(col37m)) {
					conditions = conditions + " " + dics.get("col37")
							+ " like '%" + col37 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col37") + " = '"
							+ col37 + "' and";
				}
			}
		}
		if (col38 != null && col38.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col38"))) {
				if ("on".equals(col38m)) {
					conditions = conditions + getFuzzyConditionsByDah(col38);
				} else {
					conditions = conditions + getExactConditionsByDah(col38);
				}
			} else {
				if ("on".equals(col38m)) {
					conditions = conditions + " " + dics.get("col38")
							+ " like '%" + col38 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col38") + " = '"
							+ col38 + "' and";
				}
			}
		}
		if (col39 != null && col39.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col39"))) {
				if ("on".equals(col39m)) {
					conditions = conditions + getFuzzyConditionsByDah(col39);
				} else {
					conditions = conditions + getExactConditionsByDah(col39);
				}
			} else {
				if ("on".equals(col39m)) {
					conditions = conditions + " " + dics.get("col39")
							+ " like '%" + col39 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col39") + " = '"
							+ col39 + "' and";
				}
			}
		}
		if (col40 != null && col40.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col40"))) {
				if ("on".equals(col40m)) {
					conditions = conditions + getFuzzyConditionsByDah(col40);
				} else {
					conditions = conditions + getExactConditionsByDah(col40);
				}
			} else {
				if ("on".equals(col40m)) {
					conditions = conditions + " " + dics.get("col40")
							+ " like '%" + col40 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col40") + " = '"
							+ col40 + "' and";
				}
			}
		}
		if (col41 != null && col41.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col41"))) {
				if ("on".equals(col41m)) {
					conditions = conditions + getFuzzyConditionsByDah(col41);
				} else {
					conditions = conditions + getExactConditionsByDah(col41);
				}
			} else {
				if ("on".equals(col41m)) {
					conditions = conditions + " " + dics.get("col41")
							+ " like '%" + col41 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col41") + " = '"
							+ col41 + "' and";
				}
			}
		}
		if (col42 != null && col42.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col42"))) {
				if ("on".equals(col42m)) {
					conditions = conditions + getFuzzyConditionsByDah(col42);
				} else {
					conditions = conditions + getExactConditionsByDah(col42);
				}
			} else {
				if ("on".equals(col42m)) {
					conditions = conditions + " " + dics.get("col42")
							+ " like '%" + col42 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col42") + " = '"
							+ col42 + "' and";
				}
			}
		}
		if (col43 != null && col43.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col43"))) {
				if ("on".equals(col43m)) {
					conditions = conditions + getFuzzyConditionsByDah(col43);
				} else {
					conditions = conditions + getExactConditionsByDah(col43);
				}
			} else {
				if ("on".equals(col43m)) {
					conditions = conditions + " " + dics.get("col43")
							+ " like '%" + col43 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col43") + " = '"
							+ col43 + "' and";
				}
			}
		}
		if (col44 != null && col44.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col44"))) {
				if ("on".equals(col44m)) {
					conditions = conditions + getFuzzyConditionsByDah(col44);
				} else {
					conditions = conditions + getExactConditionsByDah(col44);
				}
			} else {
				if ("on".equals(col44m)) {
					conditions = conditions + " " + dics.get("col44")
							+ " like '%" + col44 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col44") + " = '"
							+ col44 + "' and";
				}
			}
		}
		if (col45 != null && col45.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col45"))) {
				if ("on".equals(col45m)) {
					conditions = conditions + getFuzzyConditionsByDah(col45);
				} else {
					conditions = conditions + getExactConditionsByDah(col45);
				}
			} else {
				if ("on".equals(col45m)) {
					conditions = conditions + " " + dics.get("col45")
							+ " like '%" + col45 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col45") + " = '"
							+ col45 + "' and";
				}
			}
		}
		if (col46 != null && col46.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col46"))) {
				if ("on".equals(col46m)) {
					conditions = conditions + getFuzzyConditionsByDah(col46);
				} else {
					conditions = conditions + getExactConditionsByDah(col46);
				}
			} else {
				if ("on".equals(col46m)) {
					conditions = conditions + " " + dics.get("col46")
							+ " like '%" + col46 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col46") + " = '"
							+ col46 + "' and";
				}
			}
		}
		if (col47 != null && col47.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col47"))) {
				if ("on".equals(col47m)) {
					conditions = conditions + getFuzzyConditionsByDah(col47);
				} else {
					conditions = conditions + getExactConditionsByDah(col47);
				}
			} else {
				if ("on".equals(col47m)) {
					conditions = conditions + " " + dics.get("col47")
							+ " like '%" + col47 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col47") + " = '"
							+ col47 + "' and";
				}
			}
		}
		if (col48 != null && col48.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col48"))) {
				if ("on".equals(col48m)) {
					conditions = conditions + getFuzzyConditionsByDah(col48);
				} else {
					conditions = conditions + getExactConditionsByDah(col48);
				}
			} else {
				if ("on".equals(col48m)) {
					conditions = conditions + " " + dics.get("col48")
							+ " like '%" + col48 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col48") + " = '"
							+ col48 + "' and";
				}
			}
		}
		if (col49 != null && col49.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col49"))) {
				if ("on".equals(col49m)) {
					conditions = conditions + getFuzzyConditionsByDah(col49);
				} else {
					conditions = conditions + getExactConditionsByDah(col49);
				}
			} else {
				if ("on".equals(col49m)) {
					conditions = conditions + " " + dics.get("col49")
							+ " like '%" + col49 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col49") + " = '"
							+ col49 + "' and";
				}
			}
		}
		if (col50 != null && col50.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col50"))) {
				if ("on".equals(col50m)) {
					conditions = conditions + getFuzzyConditionsByDah(col50);
				} else {
					conditions = conditions + getExactConditionsByDah(col50);
				}
			} else {
				if ("on".equals(col50m)) {
					conditions = conditions + " " + dics.get("col50")
							+ " like '%" + col50 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col50") + " = '"
							+ col50 + "' and";
				}
			}
		}
		if (col51 != null && col51.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col51"))) {
				if ("on".equals(col51m)) {
					conditions = conditions + getFuzzyConditionsByDah(col51);
				} else {
					conditions = conditions + getExactConditionsByDah(col51);
				}
			} else {
				if ("on".equals(col51m)) {
					conditions = conditions + " " + dics.get("col51")
							+ " like '%" + col51 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col51") + " = '"
							+ col51 + "' and";
				}
			}
		}
		if (col52 != null && col52.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col52"))) {
				if ("on".equals(col52m)) {
					conditions = conditions + getFuzzyConditionsByDah(col52);
				} else {
					conditions = conditions + getExactConditionsByDah(col52);
				}
			} else {
				if ("on".equals(col52m)) {
					conditions = conditions + " " + dics.get("col52")
							+ " like '%" + col52 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col52") + " = '"
							+ col52 + "' and";
				}
			}
		}
		if (col53 != null && col53.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col53"))) {
				if ("on".equals(col53m)) {
					conditions = conditions + getFuzzyConditionsByDah(col53);
				} else {
					conditions = conditions + getExactConditionsByDah(col53);
				}
			} else {
				if ("on".equals(col53m)) {
					conditions = conditions + " " + dics.get("col53")
							+ " like '%" + col53 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col53") + " = '"
							+ col53 + "' and";
				}
			}
		}
		if (col54 != null && col54.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col54"))) {
				if ("on".equals(col54m)) {
					conditions = conditions + getFuzzyConditionsByDah(col54);
				} else {
					conditions = conditions + getExactConditionsByDah(col54);
				}
			} else {
				if ("on".equals(col54m)) {
					conditions = conditions + " " + dics.get("col54")
							+ " like '%" + col54 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col54") + " = '"
							+ col54 + "' and";
				}
			}
		}
		if (col55 != null && col55.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col55"))) {
				if ("on".equals(col55m)) {
					conditions = conditions + getFuzzyConditionsByDah(col55);
				} else {
					conditions = conditions + getExactConditionsByDah(col55);
				}
			} else {
				if ("on".equals(col55m)) {
					conditions = conditions + " " + dics.get("col55")
							+ " like '%" + col55 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col55") + " = '"
							+ col55 + "' and";
				}
			}
		}
		if (col56 != null && col56.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col56"))) {
				if ("on".equals(col56m)) {
					conditions = conditions + getFuzzyConditionsByDah(col56);
				} else {
					conditions = conditions + getExactConditionsByDah(col56);
				}
			} else {
				if ("on".equals(col56m)) {
					conditions = conditions + " " + dics.get("col56")
							+ " like '%" + col56 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col56") + " = '"
							+ col56 + "' and";
				}
			}
		}
		if (col57 != null && col57.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col57"))) {
				if ("on".equals(col57m)) {
					conditions = conditions + getFuzzyConditionsByDah(col57);
				} else {
					conditions = conditions + getExactConditionsByDah(col57);
				}
			} else {
				if ("on".equals(col57m)) {
					conditions = conditions + " " + dics.get("col57")
							+ " like '%" + col57 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col57") + " = '"
							+ col57 + "' and";
				}
			}
		}
		if (col58 != null && col58.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col58"))) {
				if ("on".equals(col58m)) {
					conditions = conditions + getFuzzyConditionsByDah(col58);
				} else {
					conditions = conditions + getExactConditionsByDah(col58);
				}
			} else {
				if ("on".equals(col58m)) {
					conditions = conditions + " " + dics.get("col58")
							+ " like '%" + col58 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col58") + " = '"
							+ col58 + "' and";
				}
			}
		}
		if (col59 != null && col59.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col59"))) {
				if ("on".equals(col59m)) {
					conditions = conditions + getFuzzyConditionsByDah(col59);
				} else {
					conditions = conditions + getExactConditionsByDah(col59);
				}
			} else {
				if ("on".equals(col59m)) {
					conditions = conditions + " " + dics.get("col59")
							+ " like '%" + col59 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col59") + " = '"
							+ col59 + "' and";
				}
			}
		}
		if (col60 != null && col60.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col60"))) {
				if ("on".equals(col60m)) {
					conditions = conditions + getFuzzyConditionsByDah(col60);
				} else {
					conditions = conditions + getExactConditionsByDah(col60);
				}
			} else {
				if ("on".equals(col60m)) {
					conditions = conditions + " " + dics.get("col60")
							+ " like '%" + col60 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col60") + " = '"
							+ col60 + "' and";
				}
			}
		}
		if (col61 != null && col61.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col61"))) {
				if ("on".equals(col61m)) {
					conditions = conditions + getFuzzyConditionsByDah(col61);
				} else {
					conditions = conditions + getExactConditionsByDah(col61);
				}
			} else {
				if ("on".equals(col61m)) {
					conditions = conditions + " " + dics.get("col61")
							+ " like '%" + col61 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col61") + " = '"
							+ col61 + "' and";
				}
			}
		}
		if (col62 != null && col62.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col62"))) {
				if ("on".equals(col62m)) {
					conditions = conditions + getFuzzyConditionsByDah(col62);
				} else {
					conditions = conditions + getExactConditionsByDah(col62);
				}
			} else {
				if ("on".equals(col62m)) {
					conditions = conditions + " " + dics.get("col62")
							+ " like '%" + col62 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col62") + " = '"
							+ col62 + "' and";
				}
			}
		}
		if (col63 != null && col63.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col63"))) {
				if ("on".equals(col63m)) {
					conditions = conditions + getFuzzyConditionsByDah(col63);
				} else {
					conditions = conditions + getExactConditionsByDah(col63);
				}
			} else {
				if ("on".equals(col63m)) {
					conditions = conditions + " " + dics.get("col63")
							+ " like '%" + col63 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col63") + " = '"
							+ col63 + "' and";
				}
			}
		}
		if (col64 != null && col64.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col64"))) {
				if ("on".equals(col64m)) {
					conditions = conditions + getFuzzyConditionsByDah(col64);
				} else {
					conditions = conditions + getExactConditionsByDah(col64);
				}
			} else {
				if ("on".equals(col64m)) {
					conditions = conditions + " " + dics.get("col64")
							+ " like '%" + col64 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col64") + " = '"
							+ col64 + "' and";
				}
			}
		}
		if (col65 != null && col65.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col65"))) {
				if ("on".equals(col65m)) {
					conditions = conditions + getFuzzyConditionsByDah(col65);
				} else {
					conditions = conditions + getExactConditionsByDah(col65);
				}
			} else {
				if ("on".equals(col65m)) {
					conditions = conditions + " " + dics.get("col65")
							+ " like '%" + col65 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col65") + " = '"
							+ col65 + "' and";
				}
			}
		}
		if (col66 != null && col66.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col66"))) {
				if ("on".equals(col66m)) {
					conditions = conditions + getFuzzyConditionsByDah(col66);
				} else {
					conditions = conditions + getExactConditionsByDah(col66);
				}
			} else {
				if ("on".equals(col66m)) {
					conditions = conditions + " " + dics.get("col66")
							+ " like '%" + col66 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col66") + " = '"
							+ col66 + "' and";
				}
			}
		}
		if (col67 != null && col67.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col67"))) {
				if ("on".equals(col67m)) {
					conditions = conditions + getFuzzyConditionsByDah(col67);
				} else {
					conditions = conditions + getExactConditionsByDah(col67);
				}
			} else {
				if ("on".equals(col67m)) {
					conditions = conditions + " " + dics.get("col67")
							+ " like '%" + col67 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col67") + " = '"
							+ col67 + "' and";
				}
			}
		}
		if (col68 != null && col68.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col68"))) {
				if ("on".equals(col68m)) {
					conditions = conditions + getFuzzyConditionsByDah(col68);
				} else {
					conditions = conditions + getExactConditionsByDah(col68);
				}
			} else {
				if ("on".equals(col68m)) {
					conditions = conditions + " " + dics.get("col68")
							+ " like '%" + col68 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col68") + " = '"
							+ col68 + "' and";
				}
			}
		}
		if (col69 != null && col69.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col69"))) {
				if ("on".equals(col69m)) {
					conditions = conditions + getFuzzyConditionsByDah(col69);
				} else {
					conditions = conditions + getExactConditionsByDah(col69);
				}
			} else {
				if ("on".equals(col69m)) {
					conditions = conditions + " " + dics.get("col69")
							+ " like '%" + col69 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col69") + " = '"
							+ col69 + "' and";
				}
			}
		}
		if (col70 != null && col70.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col70"))) {
				if ("on".equals(col70m)) {
					conditions = conditions + getFuzzyConditionsByDah(col70);
				} else {
					conditions = conditions + getExactConditionsByDah(col70);
				}
			} else {
				if ("on".equals(col70m)) {
					conditions = conditions + " " + dics.get("col70")
							+ " like '%" + col70 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col70") + " = '"
							+ col70 + "' and";
				}
			}
		}
		if (col71 != null && col71.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col71"))) {
				if ("on".equals(col71m)) {
					conditions = conditions + getFuzzyConditionsByDah(col71);
				} else {
					conditions = conditions + getExactConditionsByDah(col71);
				}
			} else {
				if ("on".equals(col71m)) {
					conditions = conditions + " " + dics.get("col71")
							+ " like '%" + col71 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col71") + " = '"
							+ col71 + "' and";
				}
			}
		}
		if (col72 != null && col72.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col72"))) {
				if ("on".equals(col72m)) {
					conditions = conditions + getFuzzyConditionsByDah(col72);
				} else {
					conditions = conditions + getExactConditionsByDah(col72);
				}
			} else {
				if ("on".equals(col72m)) {
					conditions = conditions + " " + dics.get("col72")
							+ " like '%" + col72 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col72") + " = '"
							+ col72 + "' and";
				}
			}
		}
		if (col73 != null && col73.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col73"))) {
				if ("on".equals(col73m)) {
					conditions = conditions + getFuzzyConditionsByDah(col73);
				} else {
					conditions = conditions + getExactConditionsByDah(col73);
				}
			} else {
				if ("on".equals(col73m)) {
					conditions = conditions + " " + dics.get("col73")
							+ " like '%" + col73 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col73") + " = '"
							+ col73 + "' and";
				}
			}
		}
		if (col74 != null && col74.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col74"))) {
				if ("on".equals(col74m)) {
					conditions = conditions + getFuzzyConditionsByDah(col74);
				} else {
					conditions = conditions + getExactConditionsByDah(col74);
				}
			} else {
				if ("on".equals(col74m)) {
					conditions = conditions + " " + dics.get("col74")
							+ " like '%" + col74 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col74") + " = '"
							+ col74 + "' and";
				}
			}
		}
		if (col75 != null && col75.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col75"))) {
				if ("on".equals(col75m)) {
					conditions = conditions + getFuzzyConditionsByDah(col75);
				} else {
					conditions = conditions + getExactConditionsByDah(col75);
				}
			} else {
				if ("on".equals(col75m)) {
					conditions = conditions + " " + dics.get("col75")
							+ " like '%" + col75 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col75") + " = '"
							+ col75 + "' and";
				}
			}
		}
		if (col76 != null && col76.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col76"))) {
				if ("on".equals(col76m)) {
					conditions = conditions + getFuzzyConditionsByDah(col76);
				} else {
					conditions = conditions + getExactConditionsByDah(col76);
				}
			} else {
				if ("on".equals(col76m)) {
					conditions = conditions + " " + dics.get("col76")
							+ " like '%" + col76 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col76") + " = '"
							+ col76 + "' and";
				}
			}
		}
		if (col77 != null && col77.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col77"))) {
				if ("on".equals(col77m)) {
					conditions = conditions + getFuzzyConditionsByDah(col77);
				} else {
					conditions = conditions + getExactConditionsByDah(col77);
				}
			} else {
				if ("on".equals(col77m)) {
					conditions = conditions + " " + dics.get("col77")
							+ " like '%" + col77 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col77") + " = '"
							+ col77 + "' and";
				}
			}
		}
		if (col78 != null && col78.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col78"))) {
				if ("on".equals(col78m)) {
					conditions = conditions + getFuzzyConditionsByDah(col78);
				} else {
					conditions = conditions + getExactConditionsByDah(col78);
				}
			} else {
				if ("on".equals(col78m)) {
					conditions = conditions + " " + dics.get("col78")
							+ " like '%" + col78 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col78") + " = '"
							+ col78 + "' and";
				}
			}
		}
		if (col79 != null && col79.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col79"))) {
				if ("on".equals(col79m)) {
					conditions = conditions + getFuzzyConditionsByDah(col79);
				} else {
					conditions = conditions + getExactConditionsByDah(col79);
				}
			} else {
				if ("on".equals(col79m)) {
					conditions = conditions + " " + dics.get("col79")
							+ " like '%" + col79 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col79") + " = '"
							+ col79 + "' and";
				}
			}
		}
		if (col80 != null && col80.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col80"))) {
				if ("on".equals(col80m)) {
					conditions = conditions + getFuzzyConditionsByDah(col80);
				} else {
					conditions = conditions + getExactConditionsByDah(col80);
				}
			} else {
				if ("on".equals(col80m)) {
					conditions = conditions + " " + dics.get("col80")
							+ " like '%" + col80 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col80") + " = '"
							+ col80 + "' and";
				}
			}
		}
		if (col81 != null && col81.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col81"))) {
				if ("on".equals(col81m)) {
					conditions = conditions + getFuzzyConditionsByDah(col81);
				} else {
					conditions = conditions + getExactConditionsByDah(col81);
				}
			} else {
				if ("on".equals(col81m)) {
					conditions = conditions + " " + dics.get("col81")
							+ " like '%" + col81 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col81") + " = '"
							+ col81 + "' and";
				}
			}
		}
		if (col82 != null && col82.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col82"))) {
				if ("on".equals(col82m)) {
					conditions = conditions + getFuzzyConditionsByDah(col82);
				} else {
					conditions = conditions + getExactConditionsByDah(col82);
				}
			} else {
				if ("on".equals(col82m)) {
					conditions = conditions + " " + dics.get("col82")
							+ " like '%" + col82 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col82") + " = '"
							+ col82 + "' and";
				}
			}
		}
		if (col83 != null && col83.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col83"))) {
				if ("on".equals(col83m)) {
					conditions = conditions + getFuzzyConditionsByDah(col83);
				} else {
					conditions = conditions + getExactConditionsByDah(col83);
				}
			} else {
				if ("on".equals(col83m)) {
					conditions = conditions + " " + dics.get("col83")
							+ " like '%" + col83 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col83") + " = '"
							+ col83 + "' and";
				}
			}
		}
		if (col84 != null && col84.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col84"))) {
				if ("on".equals(col84m)) {
					conditions = conditions + getFuzzyConditionsByDah(col84);
				} else {
					conditions = conditions + getExactConditionsByDah(col84);
				}
			} else {
				if ("on".equals(col84m)) {
					conditions = conditions + " " + dics.get("col84")
							+ " like '%" + col84 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col84") + " = '"
							+ col84 + "' and";
				}
			}
		}
		if (col85 != null && col85.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col85"))) {
				if ("on".equals(col85m)) {
					conditions = conditions + getFuzzyConditionsByDah(col85);
				} else {
					conditions = conditions + getExactConditionsByDah(col85);
				}
			} else {
				if ("on".equals(col85m)) {
					conditions = conditions + " " + dics.get("col85")
							+ " like '%" + col85 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col85") + " = '"
							+ col85 + "' and";
				}
			}
		}
		if (col86 != null && col86.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col86"))) {
				if ("on".equals(col86m)) {
					conditions = conditions + getFuzzyConditionsByDah(col86);
				} else {
					conditions = conditions + getExactConditionsByDah(col86);
				}
			} else {
				if ("on".equals(col86m)) {
					conditions = conditions + " " + dics.get("col86")
							+ " like '%" + col86 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col86") + " = '"
							+ col86 + "' and";
				}
			}
		}
		if (col87 != null && col87.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col87"))) {
				if ("on".equals(col87m)) {
					conditions = conditions + getFuzzyConditionsByDah(col87);
				} else {
					conditions = conditions + getExactConditionsByDah(col87);
				}
			} else {
				if ("on".equals(col87m)) {
					conditions = conditions + " " + dics.get("col87")
							+ " like '%" + col87 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col87") + " = '"
							+ col87 + "' and";
				}
			}
		}
		if (col88 != null && col88.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col88"))) {
				if ("on".equals(col88m)) {
					conditions = conditions + getFuzzyConditionsByDah(col88);
				} else {
					conditions = conditions + getExactConditionsByDah(col88);
				}
			} else {
				if ("on".equals(col88m)) {
					conditions = conditions + " " + dics.get("col88")
							+ " like '%" + col88 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col88") + " = '"
							+ col88 + "' and";
				}
			}
		}
		if (col89 != null && col89.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col89"))) {
				if ("on".equals(col89m)) {
					conditions = conditions + getFuzzyConditionsByDah(col89);
				} else {
					conditions = conditions + getExactConditionsByDah(col89);
				}
			} else {
				if ("on".equals(col89m)) {
					conditions = conditions + " " + dics.get("col89")
							+ " like '%" + col89 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col89") + " = '"
							+ col89 + "' and";
				}
			}
		}
		if (col90 != null && col90.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col90"))) {
				if ("on".equals(col90m)) {
					conditions = conditions + getFuzzyConditionsByDah(col90);
				} else {
					conditions = conditions + getExactConditionsByDah(col90);
				}
			} else {
				if ("on".equals(col90m)) {
					conditions = conditions + " " + dics.get("col90")
							+ " like '%" + col90 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col90") + " = '"
							+ col90 + "' and";
				}
			}
		}
		if (col91 != null && col91.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col91"))) {
				if ("on".equals(col91m)) {
					conditions = conditions + getFuzzyConditionsByDah(col91);
				} else {
					conditions = conditions + getExactConditionsByDah(col91);
				}
			} else {
				if ("on".equals(col91m)) {
					conditions = conditions + " " + dics.get("col91")
							+ " like '%" + col91 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col91") + " = '"
							+ col91 + "' and";
				}
			}
		}
		if (col92 != null && col92.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col92"))) {
				if ("on".equals(col92m)) {
					conditions = conditions + getFuzzyConditionsByDah(col92);
				} else {
					conditions = conditions + getExactConditionsByDah(col92);
				}
			} else {
				if ("on".equals(col92m)) {
					conditions = conditions + " " + dics.get("col92")
							+ " like '%" + col92 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col92") + " = '"
							+ col92 + "' and";
				}
			}
		}
		if (col93 != null && col93.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col93"))) {
				if ("on".equals(col93m)) {
					conditions = conditions + getFuzzyConditionsByDah(col93);
				} else {
					conditions = conditions + getExactConditionsByDah(col93);
				}
			} else {
				if ("on".equals(col93m)) {
					conditions = conditions + " " + dics.get("col93")
							+ " like '%" + col93 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col93") + " = '"
							+ col93 + "' and";
				}
			}
		}
		if (col94 != null && col94.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col94"))) {
				if ("on".equals(col94m)) {
					conditions = conditions + getFuzzyConditionsByDah(col94);
				} else {
					conditions = conditions + getExactConditionsByDah(col94);
				}
			} else {
				if ("on".equals(col94m)) {
					conditions = conditions + " " + dics.get("col94")
							+ " like '%" + col94 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col94") + " = '"
							+ col94 + "' and";
				}
			}
		}
		if (col95 != null && col95.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col95"))) {
				if ("on".equals(col95m)) {
					conditions = conditions + getFuzzyConditionsByDah(col95);
				} else {
					conditions = conditions + getExactConditionsByDah(col95);
				}
			} else {
				if ("on".equals(col95m)) {
					conditions = conditions + " " + dics.get("col95")
							+ " like '%" + col95 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col95") + " = '"
							+ col95 + "' and";
				}
			}
		}
		if (col96 != null && col96.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col96"))) {
				if ("on".equals(col96m)) {
					conditions = conditions + getFuzzyConditionsByDah(col96);
				} else {
					conditions = conditions + getExactConditionsByDah(col96);
				}
			} else {
				if ("on".equals(col96m)) {
					conditions = conditions + " " + dics.get("col96")
							+ " like '%" + col96 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col96") + " = '"
							+ col96 + "' and";
				}
			}
		}
		if (col97 != null && col97.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col97"))) {
				if ("on".equals(col97m)) {
					conditions = conditions + getFuzzyConditionsByDah(col97);
				} else {
					conditions = conditions + getExactConditionsByDah(col97);
				}
			} else {
				if ("on".equals(col97m)) {
					conditions = conditions + " " + dics.get("col97")
							+ " like '%" + col97 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col97") + " = '"
							+ col97 + "' and";
				}
			}
		}
		if (col98 != null && col98.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col98"))) {
				if ("on".equals(col98m)) {
					conditions = conditions + getFuzzyConditionsByDah(col98);
				} else {
					conditions = conditions + getExactConditionsByDah(col98);
				}
			} else {
				if ("on".equals(col98m)) {
					conditions = conditions + " " + dics.get("col98")
							+ " like '%" + col98 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col98") + " = '"
							+ col98 + "' and";
				}
			}
		}
		if (col99 != null && col99.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col99"))) {
				if ("on".equals(col99m)) {
					conditions = conditions + getFuzzyConditionsByDah(col99);
				} else {
					conditions = conditions + getExactConditionsByDah(col99);
				}
			} else {
				if ("on".equals(col99m)) {
					conditions = conditions + " " + dics.get("col99")
							+ " like '%" + col99 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col99") + " = '"
							+ col99 + "' and";
				}
			}
		}
		if (col100 != null && col100.trim().length() != 0) {
			if ("dah".equalsIgnoreCase(dics.get("col100"))) {
				if ("on".equals(col100m)) {
					conditions = conditions + getFuzzyConditionsByDah(col100);
				} else {
					conditions = conditions + getExactConditionsByDah(col100);
				}
			} else {
				if ("on".equals(col100m)) {
					conditions = conditions + " " + dics.get("col100")
							+ " like '%" + col100 + "%' and";
				} else {
					conditions = conditions + " " + dics.get("col100") + " = '"
							+ col100 + "' and";
				}
			}
		}
		conditions = conditions.substring(0, conditions.length() - 3);
		if (!conditions.contains("and")) {
			conditions = " 1=2";
		}
		if ("Y".equalsIgnoreCase(all)) {
			conditions = " 1=1";

		}
		if (lbh != 0) {
			conditions = conditions + " and lbh=" + lbh;
		}
		session.put("conditions", conditions);
		return conditions;
	}

	private String getExactConditionsByDah(String dah) {
		String vals[] = dah.trim().toUpperCase().split("-");
		if (vals.length != 4) {
			return " 1=2 and";
		}
		String condition = " upper(qzh)='" + vals[0] + "' and upper(mlh)='"
				+ vals[1] + "' and upper(flh)='" + vals[2]
				+ "' and upper(ajh)='" + vals[3] + "' and";
		return condition;
	}

	private String getFuzzyConditionsByDah(String dah) {
		String vals[] = dah.trim().toUpperCase().split("-");
		String condition = "";
		if (vals.length == 1) {
			condition = " (upper(qzh) like '%" + vals[0]
					+ "%' or upper(mlh) like '%" + vals[0]
					+ "%' or upper(flh) like '%" + vals[0]
					+ "%' or upper(ajh) like '%" + vals[0] + "%') and";
		} else if (vals.length == 2) {
			condition = " ((upper(qzh) like '%" + vals[0]
					+ "' and upper(mlh) like '" + vals[1]
					+ "%') or (upper(mlh) like '%" + vals[0]
					+ "' and upper(flh) like '" + vals[1]
					+ "%') or (upper(flh) like '%" + vals[0]
					+ "' and upper(ajh) like '" + vals[1] + "%')) and";
		} else if (vals.length == 3) {
			condition = " ((upper(qzh) like '%" + vals[0]
					+ "' and upper(mlh) ='" + vals[1]
					+ "' and upper(flh) like '" + vals[2]
					+ "%') or (upper(mlh) like '%" + vals[0]
					+ "' and upper(flh) ='" + vals[1]
					+ "' and upper(ajh) like '" + vals[2] + "%')) and";
		} else if (vals.length == 4) {
			condition = " ((upper(qzh) like '%" + vals[0]
					+ "' and upper(mlh) ='" + vals[1] + "' and upper(flh) = '"
					+ vals[2] + "' and upper(ajh) like '" + vals[3]
					+ "%')) and";
		}
		return condition;
	}

	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String col5;
	private String col6;
	private String col7;
	private String col8;
	private String col9;
	private String col10;
	private String col11;
	private String col12;
	private String col13;
	private String col14;
	private String col15;
	private String col16;
	private String col17;
	private String col18;
	private String col19;
	private String col20;
	private String col21;
	private String col22;
	private String col23;
	private String col24;
	private String col25;
	private String col26;
	private String col27;
	private String col28;
	private String col29;
	private String col30;
	private String col31;
	private String col32;
	private String col33;
	private String col34;
	private String col35;
	private String col36;
	private String col37;
	private String col38;
	private String col39;
	private String col40;
	private String col41;
	private String col42;
	private String col43;
	private String col44;
	private String col45;
	private String col46;
	private String col47;
	private String col48;
	private String col49;
	private String col50;
	private String col51;
	private String col52;
	private String col53;
	private String col54;
	private String col55;
	private String col56;
	private String col57;
	private String col58;
	private String col59;
	private String col60;
	private String col61;
	private String col62;
	private String col63;
	private String col64;
	private String col65;
	private String col66;
	private String col67;
	private String col68;
	private String col69;
	private String col70;
	private String col71;
	private String col72;
	private String col73;
	private String col74;
	private String col75;
	private String col76;
	private String col77;
	private String col78;
	private String col79;
	private String col80;
	private String col81;
	private String col82;
	private String col83;
	private String col84;
	private String col85;
	private String col86;
	private String col87;
	private String col88;
	private String col89;
	private String col90;
	private String col91;
	private String col92;
	private String col93;
	private String col94;
	private String col95;
	private String col96;
	private String col97;
	private String col98;
	private String col99;
	private String col100;

	private String col1m;
	private String col2m;
	private String col3m;
	private String col4m;
	private String col5m;
	private String col6m;
	private String col7m;
	private String col8m;
	private String col9m;
	private String col10m;
	private String col11m;
	private String col12m;
	private String col13m;
	private String col14m;
	private String col15m;
	private String col16m;
	private String col17m;
	private String col18m;
	private String col19m;
	private String col20m;
	private String col21m;
	private String col22m;
	private String col23m;
	private String col24m;
	private String col25m;
	private String col26m;
	private String col27m;
	private String col28m;
	private String col29m;
	private String col30m;
	private String col31m;
	private String col32m;
	private String col33m;
	private String col34m;
	private String col35m;
	private String col36m;
	private String col37m;
	private String col38m;
	private String col39m;
	private String col40m;
	private String col41m;
	private String col42m;
	private String col43m;
	private String col44m;
	private String col45m;
	private String col46m;
	private String col47m;
	private String col48m;
	private String col49m;
	private String col50m;
	private String col51m;
	private String col52m;
	private String col53m;
	private String col54m;
	private String col55m;
	private String col56m;
	private String col57m;
	private String col58m;
	private String col59m;
	private String col60m;
	private String col61m;
	private String col62m;
	private String col63m;
	private String col64m;
	private String col65m;
	private String col66m;
	private String col67m;
	private String col68m;
	private String col69m;
	private String col70m;
	private String col71m;
	private String col72m;
	private String col73m;
	private String col74m;
	private String col75m;
	private String col76m;
	private String col77m;
	private String col78m;
	private String col79m;
	private String col80m;
	private String col81m;
	private String col82m;
	private String col83m;
	private String col84m;
	private String col85m;
	private String col86m;
	private String col87m;
	private String col88m;
	private String col89m;
	private String col90m;
	private String col91m;
	private String col92m;
	private String col93m;
	private String col94m;
	private String col95m;
	private String col96m;
	private String col97m;
	private String col98m;
	private String col99m;
	private String col100m;

	public String getCol1() {
		return col1;
	}

	public void setCol1(String col1) {
		this.col1 = col1;
	}

	public String getCol2() {
		return col2;
	}

	public void setCol2(String col2) {
		this.col2 = col2;
	}

	public String getCol3() {
		return col3;
	}

	public void setCol3(String col3) {
		this.col3 = col3;
	}

	public String getCol4() {
		return col4;
	}

	public void setCol4(String col4) {
		this.col4 = col4;
	}

	public String getCol5() {
		return col5;
	}

	public void setCol5(String col5) {
		this.col5 = col5;
	}

	public String getCol6() {
		return col6;
	}

	public void setCol6(String col6) {
		this.col6 = col6;
	}

	public String getCol7() {
		return col7;
	}

	public void setCol7(String col7) {
		this.col7 = col7;
	}

	public String getCol8() {
		return col8;
	}

	public void setCol8(String col8) {
		this.col8 = col8;
	}

	public String getCol9() {
		return col9;
	}

	public void setCol9(String col9) {
		this.col9 = col9;
	}

	public String getCol10() {
		return col10;
	}

	public void setCol10(String col10) {
		this.col10 = col10;
	}

	public String getCol11() {
		return col11;
	}

	public void setCol11(String col11) {
		this.col11 = col11;
	}

	public String getCol12() {
		return col12;
	}

	public void setCol12(String col12) {
		this.col12 = col12;
	}

	public String getCol13() {
		return col13;
	}

	public void setCol13(String col13) {
		this.col13 = col13;
	}

	public String getCol14() {
		return col14;
	}

	public void setCol14(String col14) {
		this.col14 = col14;
	}

	public String getCol15() {
		return col15;
	}

	public void setCol15(String col15) {
		this.col15 = col15;
	}

	public String getCol16() {
		return col16;
	}

	public void setCol16(String col16) {
		this.col16 = col16;
	}

	public String getCol17() {
		return col17;
	}

	public void setCol17(String col17) {
		this.col17 = col17;
	}

	public String getCol18() {
		return col18;
	}

	public void setCol18(String col18) {
		this.col18 = col18;
	}

	public String getCol19() {
		return col19;
	}

	public void setCol19(String col19) {
		this.col19 = col19;
	}

	public String getCol20() {
		return col20;
	}

	public void setCol20(String col20) {
		this.col20 = col20;
	}

	public String getCol21() {
		return col21;
	}

	public void setCol21(String col21) {
		this.col21 = col21;
	}

	public String getCol22() {
		return col22;
	}

	public void setCol22(String col22) {
		this.col22 = col22;
	}

	public String getCol23() {
		return col23;
	}

	public void setCol23(String col23) {
		this.col23 = col23;
	}

	public String getCol24() {
		return col24;
	}

	public void setCol24(String col24) {
		this.col24 = col24;
	}

	public String getCol25() {
		return col25;
	}

	public void setCol25(String col25) {
		this.col25 = col25;
	}

	public String getCol26() {
		return col26;
	}

	public void setCol26(String col26) {
		this.col26 = col26;
	}

	public String getCol27() {
		return col27;
	}

	public void setCol27(String col27) {
		this.col27 = col27;
	}

	public String getCol28() {
		return col28;
	}

	public void setCol28(String col28) {
		this.col28 = col28;
	}

	public String getCol29() {
		return col29;
	}

	public void setCol29(String col29) {
		this.col29 = col29;
	}

	public String getCol30() {
		return col30;
	}

	public void setCol30(String col30) {
		this.col30 = col30;
	}

	public String getCol31() {
		return col31;
	}

	public void setCol31(String col31) {
		this.col31 = col31;
	}

	public String getCol32() {
		return col32;
	}

	public void setCol32(String col32) {
		this.col32 = col32;
	}

	public String getCol33() {
		return col33;
	}

	public void setCol33(String col33) {
		this.col33 = col33;
	}

	public String getCol34() {
		return col34;
	}

	public void setCol34(String col34) {
		this.col34 = col34;
	}

	public String getCol35() {
		return col35;
	}

	public void setCol35(String col35) {
		this.col35 = col35;
	}

	public String getCol36() {
		return col36;
	}

	public void setCol36(String col36) {
		this.col36 = col36;
	}

	public String getCol37() {
		return col37;
	}

	public void setCol37(String col37) {
		this.col37 = col37;
	}

	public String getCol38() {
		return col38;
	}

	public void setCol38(String col38) {
		this.col38 = col38;
	}

	public String getCol39() {
		return col39;
	}

	public void setCol39(String col39) {
		this.col39 = col39;
	}

	public String getCol40() {
		return col40;
	}

	public void setCol40(String col40) {
		this.col40 = col40;
	}

	public String getCol41() {
		return col41;
	}

	public void setCol41(String col41) {
		this.col41 = col41;
	}

	public String getCol42() {
		return col42;
	}

	public void setCol42(String col42) {
		this.col42 = col42;
	}

	public String getCol43() {
		return col43;
	}

	public void setCol43(String col43) {
		this.col43 = col43;
	}

	public String getCol44() {
		return col44;
	}

	public void setCol44(String col44) {
		this.col44 = col44;
	}

	public String getCol45() {
		return col45;
	}

	public void setCol45(String col45) {
		this.col45 = col45;
	}

	public String getCol46() {
		return col46;
	}

	public void setCol46(String col46) {
		this.col46 = col46;
	}

	public String getCol47() {
		return col47;
	}

	public void setCol47(String col47) {
		this.col47 = col47;
	}

	public String getCol48() {
		return col48;
	}

	public void setCol48(String col48) {
		this.col48 = col48;
	}

	public String getCol49() {
		return col49;
	}

	public void setCol49(String col49) {
		this.col49 = col49;
	}

	public String getCol50() {
		return col50;
	}

	public void setCol50(String col50) {
		this.col50 = col50;
	}

	public String getCol51() {
		return col51;
	}

	public void setCol51(String col51) {
		this.col51 = col51;
	}

	public String getCol52() {
		return col52;
	}

	public void setCol52(String col52) {
		this.col52 = col52;
	}

	public String getCol53() {
		return col53;
	}

	public void setCol53(String col53) {
		this.col53 = col53;
	}

	public String getCol54() {
		return col54;
	}

	public void setCol54(String col54) {
		this.col54 = col54;
	}

	public String getCol55() {
		return col55;
	}

	public void setCol55(String col55) {
		this.col55 = col55;
	}

	public String getCol56() {
		return col56;
	}

	public void setCol56(String col56) {
		this.col56 = col56;
	}

	public String getCol57() {
		return col57;
	}

	public void setCol57(String col57) {
		this.col57 = col57;
	}

	public String getCol58() {
		return col58;
	}

	public void setCol58(String col58) {
		this.col58 = col58;
	}

	public String getCol59() {
		return col59;
	}

	public void setCol59(String col59) {
		this.col59 = col59;
	}

	public String getCol60() {
		return col60;
	}

	public void setCol60(String col60) {
		this.col60 = col60;
	}

	public String getCol61() {
		return col61;
	}

	public void setCol61(String col61) {
		this.col61 = col61;
	}

	public String getCol62() {
		return col62;
	}

	public void setCol62(String col62) {
		this.col62 = col62;
	}

	public String getCol63() {
		return col63;
	}

	public void setCol63(String col63) {
		this.col63 = col63;
	}

	public String getCol64() {
		return col64;
	}

	public void setCol64(String col64) {
		this.col64 = col64;
	}

	public String getCol65() {
		return col65;
	}

	public void setCol65(String col65) {
		this.col65 = col65;
	}

	public String getCol66() {
		return col66;
	}

	public void setCol66(String col66) {
		this.col66 = col66;
	}

	public String getCol67() {
		return col67;
	}

	public void setCol67(String col67) {
		this.col67 = col67;
	}

	public String getCol68() {
		return col68;
	}

	public void setCol68(String col68) {
		this.col68 = col68;
	}

	public String getCol69() {
		return col69;
	}

	public void setCol69(String col69) {
		this.col69 = col69;
	}

	public String getCol70() {
		return col70;
	}

	public void setCol70(String col70) {
		this.col70 = col70;
	}

	public String getCol71() {
		return col71;
	}

	public void setCol71(String col71) {
		this.col71 = col71;
	}

	public String getCol72() {
		return col72;
	}

	public void setCol72(String col72) {
		this.col72 = col72;
	}

	public String getCol73() {
		return col73;
	}

	public void setCol73(String col73) {
		this.col73 = col73;
	}

	public String getCol74() {
		return col74;
	}

	public void setCol74(String col74) {
		this.col74 = col74;
	}

	public String getCol75() {
		return col75;
	}

	public void setCol75(String col75) {
		this.col75 = col75;
	}

	public String getCol76() {
		return col76;
	}

	public void setCol76(String col76) {
		this.col76 = col76;
	}

	public String getCol77() {
		return col77;
	}

	public void setCol77(String col77) {
		this.col77 = col77;
	}

	public String getCol78() {
		return col78;
	}

	public void setCol78(String col78) {
		this.col78 = col78;
	}

	public String getCol79() {
		return col79;
	}

	public void setCol79(String col79) {
		this.col79 = col79;
	}

	public String getCol80() {
		return col80;
	}

	public void setCol80(String col80) {
		this.col80 = col80;
	}

	public String getCol81() {
		return col81;
	}

	public void setCol81(String col81) {
		this.col81 = col81;
	}

	public String getCol82() {
		return col82;
	}

	public void setCol82(String col82) {
		this.col82 = col82;
	}

	public String getCol83() {
		return col83;
	}

	public void setCol83(String col83) {
		this.col83 = col83;
	}

	public String getCol84() {
		return col84;
	}

	public void setCol84(String col84) {
		this.col84 = col84;
	}

	public String getCol85() {
		return col85;
	}

	public void setCol85(String col85) {
		this.col85 = col85;
	}

	public String getCol86() {
		return col86;
	}

	public void setCol86(String col86) {
		this.col86 = col86;
	}

	public String getCol87() {
		return col87;
	}

	public void setCol87(String col87) {
		this.col87 = col87;
	}

	public String getCol88() {
		return col88;
	}

	public void setCol88(String col88) {
		this.col88 = col88;
	}

	public String getCol89() {
		return col89;
	}

	public void setCol89(String col89) {
		this.col89 = col89;
	}

	public String getCol90() {
		return col90;
	}

	public void setCol90(String col90) {
		this.col90 = col90;
	}

	public String getCol91() {
		return col91;
	}

	public void setCol91(String col91) {
		this.col91 = col91;
	}

	public String getCol92() {
		return col92;
	}

	public void setCol92(String col92) {
		this.col92 = col92;
	}

	public String getCol93() {
		return col93;
	}

	public void setCol93(String col93) {
		this.col93 = col93;
	}

	public String getCol94() {
		return col94;
	}

	public void setCol94(String col94) {
		this.col94 = col94;
	}

	public String getCol95() {
		return col95;
	}

	public void setCol95(String col95) {
		this.col95 = col95;
	}

	public String getCol96() {
		return col96;
	}

	public void setCol96(String col96) {
		this.col96 = col96;
	}

	public String getCol97() {
		return col97;
	}

	public void setCol97(String col97) {
		this.col97 = col97;
	}

	public String getCol98() {
		return col98;
	}

	public void setCol98(String col98) {
		this.col98 = col98;
	}

	public String getCol99() {
		return col99;
	}

	public void setCol99(String col99) {
		this.col99 = col99;
	}

	public String getCol100() {
		return col100;
	}

	public void setCol100(String col100) {
		this.col100 = col100;
	}

	public String getCol1m() {
		return col1m;
	}

	public void setCol1m(String col1m) {
		this.col1m = col1m;
	}

	public String getCol2m() {
		return col2m;
	}

	public void setCol2m(String col2m) {
		this.col2m = col2m;
	}

	public String getCol3m() {
		return col3m;
	}

	public void setCol3m(String col3m) {
		this.col3m = col3m;
	}

	public String getCol4m() {
		return col4m;
	}

	public void setCol4m(String col4m) {
		this.col4m = col4m;
	}

	public String getCol5m() {
		return col5m;
	}

	public void setCol5m(String col5m) {
		this.col5m = col5m;
	}

	public String getCol6m() {
		return col6m;
	}

	public void setCol6m(String col6m) {
		this.col6m = col6m;
	}

	public String getCol7m() {
		return col7m;
	}

	public void setCol7m(String col7m) {
		this.col7m = col7m;
	}

	public String getCol8m() {
		return col8m;
	}

	public void setCol8m(String col8m) {
		this.col8m = col8m;
	}

	public String getCol9m() {
		return col9m;
	}

	public void setCol9m(String col9m) {
		this.col9m = col9m;
	}

	public String getCol10m() {
		return col10m;
	}

	public void setCol10m(String col10m) {
		this.col10m = col10m;
	}

	public String getCol11m() {
		return col11m;
	}

	public void setCol11m(String col11m) {
		this.col11m = col11m;
	}

	public String getCol12m() {
		return col12m;
	}

	public void setCol12m(String col12m) {
		this.col12m = col12m;
	}

	public String getCol13m() {
		return col13m;
	}

	public void setCol13m(String col13m) {
		this.col13m = col13m;
	}

	public String getCol14m() {
		return col14m;
	}

	public void setCol14m(String col14m) {
		this.col14m = col14m;
	}

	public String getCol15m() {
		return col15m;
	}

	public void setCol15m(String col15m) {
		this.col15m = col15m;
	}

	public String getCol16m() {
		return col16m;
	}

	public void setCol16m(String col16m) {
		this.col16m = col16m;
	}

	public String getCol17m() {
		return col17m;
	}

	public void setCol17m(String col17m) {
		this.col17m = col17m;
	}

	public String getCol18m() {
		return col18m;
	}

	public void setCol18m(String col18m) {
		this.col18m = col18m;
	}

	public String getCol19m() {
		return col19m;
	}

	public void setCol19m(String col19m) {
		this.col19m = col19m;
	}

	public String getCol20m() {
		return col20m;
	}

	public void setCol20m(String col20m) {
		this.col20m = col20m;
	}

	public String getCol21m() {
		return col21m;
	}

	public void setCol21m(String col21m) {
		this.col21m = col21m;
	}

	public String getCol22m() {
		return col22m;
	}

	public void setCol22m(String col22m) {
		this.col22m = col22m;
	}

	public String getCol23m() {
		return col23m;
	}

	public void setCol23m(String col23m) {
		this.col23m = col23m;
	}

	public String getCol24m() {
		return col24m;
	}

	public void setCol24m(String col24m) {
		this.col24m = col24m;
	}

	public String getCol25m() {
		return col25m;
	}

	public void setCol25m(String col25m) {
		this.col25m = col25m;
	}

	public String getCol26m() {
		return col26m;
	}

	public void setCol26m(String col26m) {
		this.col26m = col26m;
	}

	public String getCol27m() {
		return col27m;
	}

	public void setCol27m(String col27m) {
		this.col27m = col27m;
	}

	public String getCol28m() {
		return col28m;
	}

	public void setCol28m(String col28m) {
		this.col28m = col28m;
	}

	public String getCol29m() {
		return col29m;
	}

	public void setCol29m(String col29m) {
		this.col29m = col29m;
	}

	public String getCol30m() {
		return col30m;
	}

	public void setCol30m(String col30m) {
		this.col30m = col30m;
	}

	public String getCol31m() {
		return col31m;
	}

	public void setCol31m(String col31m) {
		this.col31m = col31m;
	}

	public String getCol32m() {
		return col32m;
	}

	public void setCol32m(String col32m) {
		this.col32m = col32m;
	}

	public String getCol33m() {
		return col33m;
	}

	public void setCol33m(String col33m) {
		this.col33m = col33m;
	}

	public String getCol34m() {
		return col34m;
	}

	public void setCol34m(String col34m) {
		this.col34m = col34m;
	}

	public String getCol35m() {
		return col35m;
	}

	public void setCol35m(String col35m) {
		this.col35m = col35m;
	}

	public String getCol36m() {
		return col36m;
	}

	public void setCol36m(String col36m) {
		this.col36m = col36m;
	}

	public String getCol37m() {
		return col37m;
	}

	public void setCol37m(String col37m) {
		this.col37m = col37m;
	}

	public String getCol38m() {
		return col38m;
	}

	public void setCol38m(String col38m) {
		this.col38m = col38m;
	}

	public String getCol39m() {
		return col39m;
	}

	public void setCol39m(String col39m) {
		this.col39m = col39m;
	}

	public String getCol40m() {
		return col40m;
	}

	public void setCol40m(String col40m) {
		this.col40m = col40m;
	}

	public String getCol41m() {
		return col41m;
	}

	public void setCol41m(String col41m) {
		this.col41m = col41m;
	}

	public String getCol42m() {
		return col42m;
	}

	public void setCol42m(String col42m) {
		this.col42m = col42m;
	}

	public String getCol43m() {
		return col43m;
	}

	public void setCol43m(String col43m) {
		this.col43m = col43m;
	}

	public String getCol44m() {
		return col44m;
	}

	public void setCol44m(String col44m) {
		this.col44m = col44m;
	}

	public String getCol45m() {
		return col45m;
	}

	public void setCol45m(String col45m) {
		this.col45m = col45m;
	}

	public String getCol46m() {
		return col46m;
	}

	public void setCol46m(String col46m) {
		this.col46m = col46m;
	}

	public String getCol47m() {
		return col47m;
	}

	public void setCol47m(String col47m) {
		this.col47m = col47m;
	}

	public String getCol48m() {
		return col48m;
	}

	public void setCol48m(String col48m) {
		this.col48m = col48m;
	}

	public String getCol49m() {
		return col49m;
	}

	public void setCol49m(String col49m) {
		this.col49m = col49m;
	}

	public String getCol50m() {
		return col50m;
	}

	public void setCol50m(String col50m) {
		this.col50m = col50m;
	}

	public String getCol51m() {
		return col51m;
	}

	public void setCol51m(String col51m) {
		this.col51m = col51m;
	}

	public String getCol52m() {
		return col52m;
	}

	public void setCol52m(String col52m) {
		this.col52m = col52m;
	}

	public String getCol53m() {
		return col53m;
	}

	public void setCol53m(String col53m) {
		this.col53m = col53m;
	}

	public String getCol54m() {
		return col54m;
	}

	public void setCol54m(String col54m) {
		this.col54m = col54m;
	}

	public String getCol55m() {
		return col55m;
	}

	public void setCol55m(String col55m) {
		this.col55m = col55m;
	}

	public String getCol56m() {
		return col56m;
	}

	public void setCol56m(String col56m) {
		this.col56m = col56m;
	}

	public String getCol57m() {
		return col57m;
	}

	public void setCol57m(String col57m) {
		this.col57m = col57m;
	}

	public String getCol58m() {
		return col58m;
	}

	public void setCol58m(String col58m) {
		this.col58m = col58m;
	}

	public String getCol59m() {
		return col59m;
	}

	public void setCol59m(String col59m) {
		this.col59m = col59m;
	}

	public String getCol60m() {
		return col60m;
	}

	public void setCol60m(String col60m) {
		this.col60m = col60m;
	}

	public String getCol61m() {
		return col61m;
	}

	public void setCol61m(String col61m) {
		this.col61m = col61m;
	}

	public String getCol62m() {
		return col62m;
	}

	public void setCol62m(String col62m) {
		this.col62m = col62m;
	}

	public String getCol63m() {
		return col63m;
	}

	public void setCol63m(String col63m) {
		this.col63m = col63m;
	}

	public String getCol64m() {
		return col64m;
	}

	public void setCol64m(String col64m) {
		this.col64m = col64m;
	}

	public String getCol65m() {
		return col65m;
	}

	public void setCol65m(String col65m) {
		this.col65m = col65m;
	}

	public String getCol66m() {
		return col66m;
	}

	public void setCol66m(String col66m) {
		this.col66m = col66m;
	}

	public String getCol67m() {
		return col67m;
	}

	public void setCol67m(String col67m) {
		this.col67m = col67m;
	}

	public String getCol68m() {
		return col68m;
	}

	public void setCol68m(String col68m) {
		this.col68m = col68m;
	}

	public String getCol69m() {
		return col69m;
	}

	public void setCol69m(String col69m) {
		this.col69m = col69m;
	}

	public String getCol70m() {
		return col70m;
	}

	public void setCol70m(String col70m) {
		this.col70m = col70m;
	}

	public String getCol71m() {
		return col71m;
	}

	public void setCol71m(String col71m) {
		this.col71m = col71m;
	}

	public String getCol72m() {
		return col72m;
	}

	public void setCol72m(String col72m) {
		this.col72m = col72m;
	}

	public String getCol73m() {
		return col73m;
	}

	public void setCol73m(String col73m) {
		this.col73m = col73m;
	}

	public String getCol74m() {
		return col74m;
	}

	public void setCol74m(String col74m) {
		this.col74m = col74m;
	}

	public String getCol75m() {
		return col75m;
	}

	public void setCol75m(String col75m) {
		this.col75m = col75m;
	}

	public String getCol76m() {
		return col76m;
	}

	public void setCol76m(String col76m) {
		this.col76m = col76m;
	}

	public String getCol77m() {
		return col77m;
	}

	public void setCol77m(String col77m) {
		this.col77m = col77m;
	}

	public String getCol78m() {
		return col78m;
	}

	public void setCol78m(String col78m) {
		this.col78m = col78m;
	}

	public String getCol79m() {
		return col79m;
	}

	public void setCol79m(String col79m) {
		this.col79m = col79m;
	}

	public String getCol80m() {
		return col80m;
	}

	public void setCol80m(String col80m) {
		this.col80m = col80m;
	}

	public String getCol81m() {
		return col81m;
	}

	public void setCol81m(String col81m) {
		this.col81m = col81m;
	}

	public String getCol82m() {
		return col82m;
	}

	public void setCol82m(String col82m) {
		this.col82m = col82m;
	}

	public String getCol83m() {
		return col83m;
	}

	public void setCol83m(String col83m) {
		this.col83m = col83m;
	}

	public String getCol84m() {
		return col84m;
	}

	public void setCol84m(String col84m) {
		this.col84m = col84m;
	}

	public String getCol85m() {
		return col85m;
	}

	public void setCol85m(String col85m) {
		this.col85m = col85m;
	}

	public String getCol86m() {
		return col86m;
	}

	public void setCol86m(String col86m) {
		this.col86m = col86m;
	}

	public String getCol87m() {
		return col87m;
	}

	public void setCol87m(String col87m) {
		this.col87m = col87m;
	}

	public String getCol88m() {
		return col88m;
	}

	public void setCol88m(String col88m) {
		this.col88m = col88m;
	}

	public String getCol89m() {
		return col89m;
	}

	public void setCol89m(String col89m) {
		this.col89m = col89m;
	}

	public String getCol90m() {
		return col90m;
	}

	public void setCol90m(String col90m) {
		this.col90m = col90m;
	}

	public String getCol91m() {
		return col91m;
	}

	public void setCol91m(String col91m) {
		this.col91m = col91m;
	}

	public String getCol92m() {
		return col92m;
	}

	public void setCol92m(String col92m) {
		this.col92m = col92m;
	}

	public String getCol93m() {
		return col93m;
	}

	public void setCol93m(String col93m) {
		this.col93m = col93m;
	}

	public String getCol94m() {
		return col94m;
	}

	public void setCol94m(String col94m) {
		this.col94m = col94m;
	}

	public String getCol95m() {
		return col95m;
	}

	public void setCol95m(String col95m) {
		this.col95m = col95m;
	}

	public String getCol96m() {
		return col96m;
	}

	public void setCol96m(String col96m) {
		this.col96m = col96m;
	}

	public String getCol97m() {
		return col97m;
	}

	public void setCol97m(String col97m) {
		this.col97m = col97m;
	}

	public String getCol98m() {
		return col98m;
	}

	public void setCol98m(String col98m) {
		this.col98m = col98m;
	}

	public String getCol99m() {
		return col99m;
	}

	public void setCol99m(String col99m) {
		this.col99m = col99m;
	}

	public String getCol100m() {
		return col100m;
	}

	public void setCol100m(String col100m) {
		this.col100m = col100m;
	}

	/*
	 * public int getLbh() { return lbh; }
	 * 
	 * public void setLbh(int lbh) { this.lbh = lbh; }
	 */

	public String getView() {
		return view;
	}

	public void setView(String view) {
		this.view = view;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public List<Integer> getTotals() {
		return totals;
	}

	public void setTotals(List<Integer> totals) {
		this.totals = totals;
	}

	public List<Record> getRecords() {
		return records;
	}

	public void setRecords(List<Record> records) {
		this.records = records;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getLbhStr() {
		return lbhStr;
	}

	public void setLbhStr(String lbhStr) {
		this.lbhStr = lbhStr;
	}

	public int getDaType() {
		return daType;
	}

	public void setDaType(int daType) {
		this.daType = daType;
	}

	public String getAll() {
		return all;
	}

	public void setAll(String all) {
		this.all = all;
	}

	public String getDalb() {
		return dalb;
	}

	public void setDalb(String dalb) {
		this.dalb = dalb;
	}

}
