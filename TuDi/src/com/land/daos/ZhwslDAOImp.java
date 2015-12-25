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
import com.land.domain.Zhwsl;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class ZhwslDAOImp implements ZhwslDAO {

	@Override
	public int countTotal(String conditions) throws Exception {
		String sql = "select count(*) from zhwsl ";
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
	public List<Zhwsl> getAllZhwsl(int start, int end) throws Exception {
		List<Zhwsl> lists = new ArrayList<Zhwsl>();
		String sql = "select * from (select d.*,rownum r from zhwsl d) where r between ? and ?";
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
			Zhwsl zhwsl = new Zhwsl(params);
			lists.add(zhwsl);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public Zhwsl getZhwslByDah(String dah) throws Exception {
		String[] str = dah.split("-");
		String sql = "select * from zhwsl where qzh='"+str[0]+"' and mlh='"+str[1]+"' and flh='"+str[2]+"' and ajh='"+str[3]+"'";
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
			Zhwsl zhwsl = new Zhwsl(params);
			DbUtil.closeConnection(conn);
			return zhwsl;
		}
		//
		//如果案卷级综合类没有找到，继续查找文件级
		//
		sql = "select * from zhwsl where qzh='"+str[0]+"' and nd='"+str[1]+"' and bgqx='"+str[2]+"' and jh='"+str[3]+"'";
		
		
		pct = conn.prepareStatement(sql);
		rs = pct.executeQuery();
		rsmd = rs.getMetaData();
		if (rs.next()) {
			Map<String, Object> params = new HashMap<String, Object>();
			int count = rsmd.getColumnCount();
			for (int i = 1; i <= count; i++) {
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Zhwsl zhwsl = new Zhwsl(params);
			DbUtil.closeConnection(conn);
			return zhwsl;
		}
		
		DbUtil.closeConnection(conn);
		return null;
	}

	@Override
	public boolean insertZhwsl(List<Zhwsl> zhwsls) throws SQLException {
		// TODO Auto-generated method stub
		Zhwsl d = new Zhwsl();
		String sql = d.getInsertSql();
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//int id = getMaxDjywId() + 1;
		for (int i = 0; i < zhwsls.size(); i++) {
			d = zhwsls.get(i);
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
	public List<Zhwsl> searchZhwslBy(String conditions, int start, int end)
			throws Exception {
		List<Zhwsl> lists = new ArrayList<Zhwsl>();
		String sql = "select * from (select t.*,rownum r from zhwsl t where "
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
			Zhwsl zhwsl = new Zhwsl(params);
			lists.add(zhwsl);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	private void setparames(PreparedStatement pct, Zhwsl zhwsl) throws SQLException{
		Class<Zhwsl> c = Zhwsl.class;
		Field fields[] = c.getDeclaredFields();

		try {
			for (int i = 1; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				Method method = pd.getReadMethod();
				if (f.getType() == float.class) {
					pct.setFloat(i, (Float) method.invoke(zhwsl));
				} else if (f.getType() == String.class) {
					pct.setString(i, (String) method.invoke(zhwsl));
				} else if (f.getType() == int.class) {
					pct.setInt(i, (Integer) method.invoke(zhwsl));
				} else if (f.getType() == Date.class) {
					pct.setDate(i, (Date) method.invoke(zhwsl));
				}
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}

}
