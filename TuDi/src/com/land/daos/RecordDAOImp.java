package com.land.daos;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.land.domain.Condition;
import com.land.domain.DaDesc;
import com.land.domain.Dalb;
import com.land.domain.Dictionary;
import com.land.domain.Menu;
import com.land.domain.Record;
import com.land.domain.User;
import com.land.util.DbUtil;

public class RecordDAOImp implements RecordDAO {

	@Override
	public boolean insertRecord(List<Record> records, String tableName) throws SQLException {
		Record record = new Record();
		String sql = "Insert into "+tableName+"(#SRC_ADDTIME) values(%sysdate)";
		List<String> cols = getTableColumns(tableName);
		String names = "";
		String vals = "";
		for(int i=0;i<cols.size();i++) {
			names = names+cols.get(i)+", ";
			vals = vals + "?, ";
		}

		sql = sql.replaceAll("#", names);
		sql = sql.replaceAll("%", vals);
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for(int i=0;i<records.size();i++) {
			record = records.get(i);
			setparames(pct, record, cols);
			pct.addBatch();
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return true;
	}
	
	@Test
	public void test() throws SQLException {
		RecordDAOImp dao = new RecordDAOImp();
		//List<String> strs = new ArrayList<String>();
		List<String> sf = new ArrayList<String>();
		
		sf.add("col1");
		sf.add("col2");
		sf.add("col4");
		sf.add("col5");

		dao.updateSearchConditions(77, sf);
		
	}
	
	private void setparames(PreparedStatement pct, Record record, List<String> cols) throws SQLException{
		Class<Record> c = Record.class;
		Map<String, Dictionary> dic = getDics("NAME");
		try {
			for (int i = 0; i < cols.size(); i++) {
				System.out.println(cols.get(i)+"=="+dic.get(cols.get(i)).getCol_name());
				PropertyDescriptor pd = new PropertyDescriptor(dic.get(cols.get(i)).getCol_name().toLowerCase(), c);
				Method method = pd.getReadMethod();
				pct.setString(i+1, (String) method.invoke(record));
			}
			
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		
	}
	
	public List<String> getTableColumns(String tableName) throws SQLException {
		List<String> columns = new ArrayList<String>();
		String sql = "SELECT COLUMN_NAME FROM user_tab_columns WHERE Lower(TABLE_NAME)=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setString(1, tableName.toLowerCase());
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			if(!"src_addtime".equalsIgnoreCase(rs.getString("COLUMN_NAME"))) {
				columns.add(rs.getString("COLUMN_NAME").toLowerCase());
			}
			
		}
		DbUtil.closeConnection(conn);
		return columns;
	}
	
	public Map<String, Dictionary> getDics(String prefix) throws SQLException{
		// TODO Auto-generated method stub
		String sql="select col_name,realname,alpha from DICTIONARY where col_name like '"+prefix+"%'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Map<String, Dictionary> dics = new HashMap<String, Dictionary>();
		while(rs.next()) {
			Dictionary d = new Dictionary();
			d.setAlpha(rs.getString("alpha"));
			d.setCol_name(rs.getString("col_name"));
			d.setRealName(rs.getString("realname"));
			dics.put(rs.getString("alpha").toLowerCase(), d);
		}
		DbUtil.closeConnection(conn);
		return dics;
	}

	@Override
	public List<Menu> getDaMenu() throws SQLException {
		String sql = "select shortname,tablename from menu where tablename is not null";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		List<Menu> menus = new ArrayList<Menu>();
		while(rs.next()){
			Menu menu = new Menu();
			menu.setShortName(rs.getString("shortname"));
			menu.setTableName(rs.getString("tablename"));
			menus.add(menu);
		}
		DbUtil.closeConnection(conn);
		return menus;
	}

	@Override
	public boolean createRecordTable(String tableName, List<String> fieldNames, String recordName, List<String> sf, String shortName, List<String> roles)
			throws SQLException {
		String sql = "create table % (#)";
		String fieldStr = "";
		for(int i=0;i<fieldNames.size();i++) {
			if("lbh".equalsIgnoreCase(fieldNames.get(i))) {
				fieldStr +=fieldNames.get(i)+" number(5,0)";
			}else {
				fieldStr +=fieldNames.get(i)+" varchar2(255)";
			}
			
			fieldStr+=",";
		}
		fieldStr = fieldStr+"SRC_ADDTIME TIMESTAMP";
		sql = sql.replaceAll("%", tableName);
		sql = sql.replaceAll("#", fieldStr);
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.executeUpdate(sql);
		DbUtil.closeConnection(conn);
		int id = getSeqId();
		updateMenuTable(tableName, recordName, id, shortName);
		
		insertSeachConditions(id, sf,true);
		updatePermissions(recordName, roles);
		DbUtil.closeConnection(conn);
		return true;
	}
	
	private boolean updateMenuTable(String tablename, String recordName, int id, String shortName) throws SQLException{
		String sql = "insert into menu(id,name,subname,link,shortname,tablename) values (?,?,?,?,?,?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		pst.setString(2, "档案检索");
		pst.setString(3, recordName);
		pst.setString(4, "../main/presearch?daType="+id);
		pst.setString(5, shortName);
		pst.setString(6, tablename.toLowerCase());
		pst.execute();
		DbUtil.closeConnection(conn);
		return true;
	}
	
	private int getSeqId() throws SQLException{
		String sql = "select TuDi_Seq.nextval id from dual";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int id = 0;
		while(rs.next()) {
			id = rs.getInt("id");
		}
		DbUtil.closeConnection(conn);
		return id;
	}
	
	
	private boolean insertSeachConditions(int id, List<String> sf, boolean isHomePage) throws SQLException{
		//update Search table
		//update dalb table
		String vals="Y";
		if(isHomePage) {
			vals="S";
		}
		String sql = "insert into SEARCH(LM_ID,#) values(?,%)";
		String col = "col1";
		String val = "?";
		for(int i=2; i<101; i++) {
			col = col+",col"+i;
			val = val+",?";
		}
		sql = sql.replaceAll("#", col);
		sql = sql.replaceAll("%", val);
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		try {
			for(int i=1;i<101;i++) {
				boolean flag = false;
				for(int j=0;j<sf.size();j++) {
					if(sf.get(j).equalsIgnoreCase("col"+i)) {
						flag=true;
					}
					
				}
				if(flag) {
					pst.setString(i+1, vals);
				}else {
					pst.setString(i+1, null);
				}
				
			}
		} catch (IllegalArgumentException e) {
			throw new SQLException(e.getMessage());
		} 
		pst.execute();
		DbUtil.closeConnection(conn);
		return true;
		
	}
	
	private boolean updatePermissions(String recordName, List<String> roles) throws SQLException{
		String sql = "insert into permissions(id,name) values (?,?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		int id = getSeqId();
		pst.setInt(1, id);
		pst.setString(2, recordName);
		pst.execute();
		DbUtil.closeConnection(conn);
		updateRolePermission(id, roles);
		updateUserPermission(roles,id);
		return true;
	}
	
	private void updateRolePermission(int pid, List<String> roles) throws SQLException {
		String sql = "insert into role_permission(roleid, permissionid) values(?,?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for(int i=0;i<roles.size();i++) {
			pct.setString(1, roles.get(i));
			pct.setInt(2, pid);
			//updateUserPermission(roles.get(i), pid);
			pct.addBatch();
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
	}
	
	private void updateUserPermission(List<String> roles, int pid) throws SQLException {
		List<User> users = getRoleUsers();
		String sql = "insert into userpermissions(userid,permissionid,available) values (?,?,?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for(int i=0;i<users.size();i++) {
			User u = users.get(i);
			boolean f = false;
			for(int j=0;j<roles.size();j++) {
				if(u.getRoleId().equals(roles.get(j))) {
					f = true;
					break;
				}
			}
			pct.setString(1, u.getId());
			pct.setInt(2, pid);
			if(f) {
				pct.setString(3, "Y");
			}else {
				pct.setString(3, "N");
			}
			pct.addBatch();
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
	}
	
	private List<User> getRoleUsers() throws SQLException{
		List<User> userIds = new ArrayList<User>();
		String sql = "select userid, roleId from user_role";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			User u = new User();
			u.setId(rs.getString(1));
			u.setRoleId(rs.getString(2));
			userIds.add(u);
		}
		DbUtil.closeConnection(conn);
		return userIds;
	}

	@Override
	public List<Condition> getAllFields() throws SQLException {
		String sql = "select col_name,realname,alpha from dictionary where col_name like 'NAME%' and to_number(substr(col_name,5))>4 order by to_number(substr(col_name,5))";
		List<Condition> list = new ArrayList<Condition>();
		Condition c1 = new Condition();
		c1.setAlpha("dah");
		c1.setName("档案号");
		list.add(c1);
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while(rs.next()) {
			Condition c = new Condition();
			c.setAlias(rs.getString("col_name"));
			c.setName(rs.getString("realname"));
			c.setAlpha(rs.getString("alpha"));
			list.add(c);
		}
		DbUtil.closeConnection(conn);
		return list;
	}

	@Override
	public boolean updateDictionary(List<Dictionary> dics, String isField) throws SQLException {
		String sql = "insert into dictionary(col_name,realname,alpha) values (?,?,?)";
		Connection conn = DbUtil.getConnection();
		int index = getindex(isField)+1;
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		for(int i=0;i<dics.size();i++) {
			Dictionary dic = dics.get(i);
			pct.setString(1, isField.toUpperCase()+index);
			pct.setString(2, dic.getRealName());
			pct.setString(3, dic.getAlpha());
			pct.addBatch();
			index++;
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return true;
	}
	
	private int getindex(String isField) throws SQLException{
		int length = isField.length()+1;
		String sql = "select max(to_number(substr(col_name,"+length+"))) from dictionary where col_name like '"+isField+"%'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int index = 0;
		if(rs.next()) {
			index = rs.getInt(1);
		}
		DbUtil.closeConnection(conn);
		return index;
	}
	
	public boolean updateDalb(Dalb dalb) throws SQLException {
		String sql = "insert into dalb(lbh,lbname,search_condition,lm_id) values (?,?,?,?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		
			pct.setInt(1, dalb.getLbh());
			pct.setString(2, dalb.getLbName());
			pct.setString(3, dalb.getSearchCondition());
			pct.setInt(4, dalb.getLmId());
		
		pct.execute();
		DbUtil.closeConnection(conn);
		return true;
	}

	@Override
	public int generateLbh() throws SQLException {
		String sql = "select max(lbh) from dalb";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		int lbh = 0;
		if(rs.next()) {
			lbh = rs.getInt(1)+1;
		}
		DbUtil.closeConnection(conn);
		return lbh;
	}
	
	public List<DaDesc> getDaDesc() throws SQLException {
		List<DaDesc> da = new ArrayList<DaDesc>();
		String sql = "select id,subname,tablename from menu where name='档案检索'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			DaDesc dadesc = new DaDesc();
			dadesc.setLmId(rs.getInt("id"));
			dadesc.setName(rs.getString("subname"));
			dadesc.setTablename(rs.getString("tablename"));
			da.add(dadesc);
		}
		DbUtil.closeConnection(conn);
		return da;
	}
	
	public String getTableName(int id) throws SQLException {
		String sql = "select tablename from menu where id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		String tableName="";
		if(rs.next()) {
			tableName = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tableName;
	}

	@Override
	public boolean updateSearchConditions(int id, List<String> sf)
			throws SQLException {
		// TODO Auto-generated method stub
		List<String> upsf = new ArrayList<String>();
		List<String> sc = getSearchCondition(id);
		for(int i=0;i<sf.size();i++) {
			boolean f = false;
			for(int j=0;j<sc.size();j++) {
				if(sf.get(i).equalsIgnoreCase(sc.get(j))) {
					f=true;
				}
			}
			if(!f) {
				upsf.add(sf.get(i));
			}
		}
		
		String sql = "update search set ";
		Connection conn = DbUtil.getConnection();
				for(int j=0;j<upsf.size();j++) {
					
					sql +=upsf.get(j)+"='Y'";
					if(j!=upsf.size()-1) {
						sql +=",";
					}
				}
				
		sql +=" where lm_id="+id;
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.execute();
		DbUtil.closeConnection(conn);
		return false;
	}
	
	private List<String> getSearchCondition(int lm_id) throws SQLException{
		List<String> sc = new ArrayList<String>();
		String sql = "select ";
		for(int i=1;i<=100;i++) {
			if(i==100) {
				sql=sql+"col"+i+" ";
			}else {
				sql=sql+"col"+i+", ";
			}
			
		}
		sql = sql+" from search where lm_id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, lm_id);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			for(int i=1;i<101;i++) {
				if(rs.getString("col"+i)!=null) {
					sc.add("col"+i);
				}
			}
		}
		DbUtil.closeConnection(conn);
		return sc;
	}
	
}
