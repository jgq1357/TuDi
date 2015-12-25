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

import com.land.domain.Djyw;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class DjywDAOImpl implements DjywDAO {

	@Override
	public List<Djyw> getAllDjyw(int start, int end) throws Exception {
		List<Djyw> lists = new ArrayList<Djyw>();
		String sql = "select * from (select d.*,rownum r from DJYW d) where r between ? and ?";
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
			Djyw djyw = new Djyw(params);
			lists.add(djyw);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public Djyw getDjywByDah(String dah) throws Exception {
		String[] str = dah.split("-");
		String sql = "select * from DJYW where qzh='"+str[0]+"' and mlh='"+str[1]+"' and flh='"+str[2]+"' and ajh='"+str[3]+"'";
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
			Djyw djyw = new Djyw(params);
			DbUtil.closeConnection(conn);
			return djyw;
		}
		DbUtil.closeConnection(conn);
		return null;
	}

	@Override
	public List<Djyw> searchDjywsBy(String conditions, int start, int end)
			throws Exception {
		List<Djyw> lists = new ArrayList<Djyw>();
		String sql = "select * from (select t.*,rownum r from DJYW t where "
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
			Djyw djyw = new Djyw(params);
			lists.add(djyw);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from djyw ";
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
	public boolean insertDjyw(List<Djyw> djyws) throws SQLException {
		// TODO Auto-generated method stub
		Djyw d = new Djyw();
		String sql = d.getInsertSql();
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//int id = getMaxDjywId() + 1;
		for (int i = 0; i < djyws.size(); i++) {
			d = djyws.get(i);
			//pct.setInt(1, id);
			setparames(pct, d);

			pct.addBatch();
			//id++;
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return false;
	}

	private void setparames(PreparedStatement pct, Djyw djyw) throws SQLException{
		Class<Djyw> c = Djyw.class;
		Field fields[] = c.getDeclaredFields();

		try {
			for (int i = 1; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				Method method = pd.getReadMethod();
				if (f.getType() == float.class) {
					pct.setFloat(i, (Float) method.invoke(djyw));
				} else if (f.getType() == String.class) {
					pct.setString(i, (String) method.invoke(djyw));
				} else if (f.getType() == int.class) {
					pct.setInt(i, (Integer) method.invoke(djyw));
				} else if (f.getType() == Date.class) {
					pct.setDate(i, (Date) method.invoke(djyw));
				}
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}

	/*
	 * @Test public void Tes() throws SQLException { List<Djyw> djyws = new
	 * ArrayList<Djyw>(); Djyw djyw1 = new Djyw();
	 * //System.out.println(djyw1.getInsertSql()); djyw1.setQzh("456");
	 * djyw1.setMlh("123"); djyw1.setFlh("333"); djyw1.setAjh("555");
	 * djyw1.setZzrq(new Date(2012, 31, 15)); djyw1.setLbh(2); djyws.add(djyw1);
	 * 
	 * Djyw djyw2 = new Djyw(); djyw2.setQzh("777"); djyw2.setMlh("888");
	 * djyw2.setFlh("999"); djyw2.setAjh("000"); djyw2.setLbh(4);
	 * djyws.add(djyw2); insertDjyw(djyws);
	 * 
	 * }
	 */

}
