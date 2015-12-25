package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.land.domain.Record;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class SearchDAOImp implements SearchDAO {

	@Override
	public Map<String, String> getDics(String prefix) throws SQLException{
		// TODO Auto-generated method stub
		String sql="select col_name,realname,alpha from DICTIONARY where col_name like '"+prefix+"%'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Map<String, String> dics = new HashMap<String, String>();
		while(rs.next()) {
			if("col".equalsIgnoreCase(prefix)){
				dics.put(rs.getString("col_name").toLowerCase(), rs.getString("alpha").toLowerCase());
			}else if("name".equalsIgnoreCase(prefix)) {
				dics.put(rs.getString("alpha").toLowerCase(), rs.getString("col_name").toLowerCase());
			}
				
		}
		DbUtil.closeConnection(conn);
		return dics;
	}

	@Override
	public String getTableName(int daType) throws SQLException {
		String sql="select tablename from menu where id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, daType);
		ResultSet rs = pst.executeQuery();
		String tableName="";
		while(rs.next()) {
			tableName = rs.getString("tablename");
		}
		DbUtil.closeConnection(conn);
		return tableName;
	}
	
	@Test
	public void test() throws SQLException {
		/*List<Condition> cons = new PreSearchDAOImp().getConditions(3);
		for(int i=0;i<cons.size();i++) {
			System.out.println(cons.get(i));
		}*/
		List<Record> records = new SearchDAOImp().getRecords("djyw", "1=1", 1, 10);
		for(int i=0;i<records.size();i++) {
			Record c = records.get(i);
			System.out.println(c.getName7());
		}
		System.out.println();
		
		System.out.println();
	}
	
//	
//	//
//	//综合文书类有两个子类别，档案号的构成不同，需要特别处理
//	//
//	public List<Record> getZhwslRecords(String tableName, String conditions,
//			int start, int end) throws SQLException {
//		
//		List<Record> records = new ArrayList<Record>();
//		String sql="select * from (select t.*,rownum r from "+tableName+" t where "+conditions+") where r between ? and ?";
//		Connection conn = DbUtil.getConnection();
//		PreparedStatement pct = conn.prepareStatement(sql);
//		pct.setInt(1, start);
//		pct.setInt(2, end);
//		ResultSet rs = pct.executeQuery();
//		ResultSetMetaData rsmd = rs.getMetaData();
//		Map<String, String> dics =new SearchDAOImp().getDics("NAME");
//		while (rs.next()) {
//			Map<String, String> params = new HashMap<String, String>();
//			int count = rsmd.getColumnCount();
//			for (int i = 1; i <= count; i++) {
//				String key = rsmd.getColumnName(i);
//				String value = rs.getString(key);
//				params.put(key.toLowerCase(), value);
//			}
//			Record r = new Record(params,dics);
//			records.add(r);
//		}
//		DbUtil.closeConnection(conn);
//		return records;
//	}
//	
	@Override
	public List<Record> getRecords(String tableName, String conditions,
			int start, int end) throws SQLException {
//		//
//		//综合文书类有两个子类别，档案号的构成不同，需要特别处理
//		//
//		if(tableName.equalsIgnoreCase("zhwsl"))
//		{
//			
//		}
		
		List<Record> records = new ArrayList<Record>();
		String sql="select * from (select t.*,rownum r from "+tableName+" t where "+conditions+") where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setInt(1, start);
		pct.setInt(2, end);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		Map<String, String> dics =new SearchDAOImp().getDics("NAME");
		while (rs.next()) {
			Map<String, String> params = new HashMap<String, String>();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				String value = rs.getString(key);
				params.put(key.toLowerCase(), value);
			}
			Record r = new Record(params,dics);
			records.add(r);
		}
		DbUtil.closeConnection(conn);
		return records;
	}

	@Override
	public int countTotalPage(String conditions, String tableName)
			throws SQLException {
		String sql = "select count(*) from "+tableName+" ";
		if (!"All".equals(conditions)) {
			sql = sql + "where " + conditions;
		}
		Connection conn = DbUtil.getConnection();
		Statement pct = conn.createStatement();
		ResultSet rs = pct.executeQuery(sql);
		if (rs.next()) {
			int total = rs.getInt(1);
			int every = Constant.ROWSOFSEARCHRESULT;
			total = (total - 1) / every + 1;
			DbUtil.closeConnection(conn);
			return total;
		}
		DbUtil.closeConnection(conn);
		return 1;
	}
	@Override
	public int countTotalSize(String conditions, String tableName)
			throws SQLException {
		String sql = "select count(*) from "+tableName+" ";
		if (!"All".equals(conditions)) {
			sql = sql + "where " + conditions;
		}
		Connection conn = DbUtil.getConnection();
		Statement pct = conn.createStatement();
		ResultSet rs = pct.executeQuery(sql);
		if (rs.next()) {
			int total = rs.getInt(1);
			DbUtil.closeConnection(conn);
			return total;
		}
		DbUtil.closeConnection(conn);
		return 1;
	}

	@Override
	public Map<String,String> getRecordFieldByDah(String dah, String tableName) throws SQLException {
		String[] str = dah.split("-");
		String sql = "select * from "+tableName+" where qzh='"+str[0]+"' and mlh='"+str[1]+"' and flh='"+str[2]+"' and ajh='"+str[3]+"'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		Map<String, String> dics =new SearchDAOImp().getRealNames();
		Map<String, String> params = new HashMap<String, String>();
		
		while (rs.next()) {
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				String value = rs.getString(key);
				key = dics.get(key.toLowerCase());
				if(key==null) {
					continue;
				}
				params.put(key.toLowerCase(), value);
			}
		}
		DbUtil.closeConnection(conn);
		return params;
	}

	@Override
	public String getShortName(String daType) throws SQLException {
		String sql = "select shortname from menu where tablename=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, daType);
		ResultSet rs = pct.executeQuery();
		String shortname="";
		while(rs.next()) {
			shortname = rs.getString("shortname");
		}
		DbUtil.closeConnection(conn);
		return shortname;
	}

	@Override
	public Map<String, String> getRealNames() throws SQLException {
		String sql="select col_name,realname,alpha from DICTIONARY where col_name like 'NAME%'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Map<String, String> dics = new HashMap<String, String>();
		while(rs.next()) {
			dics.put(rs.getString("alpha").toLowerCase(), rs.getString("realname").toLowerCase());
			
		}
		DbUtil.closeConnection(conn);
		
		return dics;
	}

}
