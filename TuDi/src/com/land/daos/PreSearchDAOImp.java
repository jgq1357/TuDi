package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.land.domain.Condition;
import com.land.domain.Dalb;
import com.land.util.DbUtil;

public class PreSearchDAOImp implements PreSearchDAO {

	@Override
	public List<Condition> getConditions(int lm_id) throws SQLException{
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
		if(!rs.next()) {
			DbUtil.closeConnection(conn);
			throw new SQLException("未找到搜索条件。");
			
		}
		sql = "select col_name,realname,alpha from dictionary where col_name like 'COL%'";
		pst = conn.prepareStatement(sql);
		ResultSet rs2 = pst.executeQuery();
		String colName="";
		List<Condition> cons = new ArrayList<Condition>();
		while(rs2.next()) {
			Condition con = new Condition();
			colName=rs2.getString("col_name").toLowerCase();
			if(rs.getString(colName)!=null&&rs.getString(colName).length()!=0) {
				con.setValue(rs.getString(colName));
				con.setAlpha(rs2.getString("alpha"));
				con.setDisplay();
				con.setName(rs2.getString("realname"));
				con.setAlias(rs2.getString("col_name").toLowerCase());
				cons.add(con);
			}
		}
		DbUtil.closeConnection(conn);
		return cons;
	}

	@Override
	public List<Dalb> getDalbs(int lm_id) throws SQLException{
		String sql = "select lbh,lbname,search_condition from dalb where lm_id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, lm_id);
		ResultSet rs = pst.executeQuery();
		List<Dalb> dalbs = new ArrayList<Dalb>();
		while(rs.next()) {
			Dalb dalb = new Dalb();
			dalb.setLbh(rs.getInt("lbh"));
			dalb.setLbName(rs.getString("lbname"));
			dalb.setLmId(lm_id);
			dalb.setSearchCondition(rs.getString("search_condition"));
			dalbs.add(dalb);
		}
		DbUtil.closeConnection(conn);
		return dalbs;
	}
	
	@Test
	public void test() throws SQLException {
		/*List<Condition> cons = new PreSearchDAOImp().getConditions(3);
		for(int i=0;i<cons.size();i++) {
			System.out.println(cons.get(i));
		}*/
		
		System.out.println(new PreSearchDAOImp().getShortName(3));
	}

	@Override
	public String getShortName(int daType) throws SQLException {
		String sql = "select shortname from menu where id=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, daType);
		ResultSet rs = pst.executeQuery();
		String shortName="";
		while(rs.next()) {
			shortName = rs.getString("shortname");
		}
		DbUtil.closeConnection(conn);
		return shortName;
	}

}
