package com.land.daos;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.land.domain.Tdlyghl;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class TdlyghlDAOImp implements TdlyghlDAO {

	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from tdlyghl ";
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
	public List<Tdlyghl> getAllTdlyghl(int start, int end) throws Exception {
		List<Tdlyghl> lists = new ArrayList<Tdlyghl>();
		String sql = "select * from (select d.*,rownum r from TDLYGHL d) where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setInt(1, start);
		pct.setInt(2, end);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> params = new HashMap<String, Object>();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Tdlyghl tdlyghl = new Tdlyghl(params);
			lists.add(tdlyghl);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public Tdlyghl getTdlyghlByDah(String dah) throws Exception {
		String[] str = dah.split("-");
		String sql = "select * from TDLYGHL where qzh='"+str[0]+"' and mlh='"+str[1]+"' and flh='"+str[2]+"' and ajh='"+str[3]+"'";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		if (rs.next()) {
			Map<String, Object> params = new HashMap<String, Object>();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Tdlyghl tdlyghl = new Tdlyghl(params);
			DbUtil.closeConnection(conn);
			return tdlyghl;
		}
		DbUtil.closeConnection(conn);
		return null;
	}

	@Override
	public boolean insertTdlyghl(List<Tdlyghl> Tdlyghls) throws SQLException {
		// TODO Auto-generated method stub
		Tdlyghl d = new Tdlyghl();
		String sql = d.getInsertSql();
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//int id = getMaxDjywId() + 1;
		for (int i = 0; i < Tdlyghls.size(); i++) {
			d = Tdlyghls.get(i);
			//pct.setInt(1, id);
			setparames(pct, d);

			pct.addBatch();
			//id++;
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return false;
	}

	@Override
	public List<Tdlyghl> searchTdlyghlBy(String conditions, int start, int end)
			throws Exception {
		List<Tdlyghl> lists = new ArrayList<Tdlyghl>();
		String sql = "select * from (select t.*,rownum r from TDLYGHL t where "
				+ conditions + ") where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setInt(1, start);
		pct.setInt(2, end);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while (rs.next()) {
			Map<String, Object> params = new HashMap<String, Object>();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Tdlyghl tdlyghl = new Tdlyghl(params);
			lists.add(tdlyghl);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	private void setparames(PreparedStatement pct, Tdlyghl tdlyghl) throws SQLException{
		Class<Tdlyghl> c = Tdlyghl.class;
		Field fields[] = c.getDeclaredFields();

		try {
			for (int i = 1; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				Method method = pd.getReadMethod();
				if (f.getType() == float.class) {
					pct.setFloat(i, (Float) method.invoke(tdlyghl));
				} else if (f.getType() == String.class) {
					pct.setString(i, (String) method.invoke(tdlyghl));
				} else if (f.getType() == int.class) {
					pct.setInt(i, (Integer) method.invoke(tdlyghl));
				} else if (f.getType() == Date.class) {
					pct.setDate(i, (Date) method.invoke(tdlyghl));
				}
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}

}
