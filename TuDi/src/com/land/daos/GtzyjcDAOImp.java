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

import com.land.domain.Gtzyjc;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class GtzyjcDAOImp implements GtzyjcDAO{

	@Override
	public List<Gtzyjc> getAllGtzyjc(int start, int end) throws Exception {
		List <Gtzyjc> lists = new ArrayList<Gtzyjc>();
		String sql = "select * from (select d.*,rownum r from gtzyjc d) where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setInt(1, start);
		pct.setInt(2, end);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()){
			Map <String,Object> params = new HashMap<String,Object>();
			int count = rsmd.getColumnCount();
			for(int i=1;i<=count;i++){
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Gtzyjc gtzyjc = new Gtzyjc(params);
			lists.add(gtzyjc);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public Gtzyjc getGtzyjcById(String id) throws Exception{
		String sql = "select * from gtzyjc where daid = ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, id);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		if(rs.next()){
			Map <String,Object> params = new HashMap<String,Object>();
			int count = rsmd.getColumnCount();
			for(int i=1;i<=count;i++){
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Gtzyjc gtzyjc = new Gtzyjc(params);
			DbUtil.closeConnection(conn);
			return gtzyjc;
		}
		DbUtil.closeConnection(conn);
		return null;
	}

	@Override
	public List<Gtzyjc> searchGtzyjcBy(String conditions, int start, int end) throws Exception {
		List <Gtzyjc> lists = new ArrayList<Gtzyjc>();
		String sql = "select * from (select t.*,rownum r from Gtzyjc t where "+conditions+") where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setInt(1, start);
		pct.setInt(2, end);
		ResultSet rs = pct.executeQuery();
		ResultSetMetaData rsmd = rs.getMetaData();
		while(rs.next()){
			Map <String,Object> params = new HashMap<String,Object>();
			int count = rsmd.getColumnCount();
			for(int i=1;i<=count;i++){
				String key = rsmd.getColumnName(i);
				Object value = rs.getObject(key);
				params.put(key, value);
			}
			Gtzyjc gtzyjc = new Gtzyjc(params);
			lists.add(gtzyjc);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from gtzyjc ";
		if(!"All".equals(conditions)) {
			sql = sql + "where "+conditions;
		}
		Connection conn = DbUtil.getConnection();
		Statement pct = conn.createStatement();
		ResultSet rs = pct.executeQuery(sql);
		if(rs.next()) {
			int total = rs.getInt(1);
			int every = Constant.ROWSOFSEARCHRESULT;
			
			total = (total-1)/every+1;
			DbUtil.closeConnection(conn);
			return total;
		}
		DbUtil.closeConnection(conn);
		return 1;
	}

	@Override
	public boolean insertGtzyjc(List<Gtzyjc> gtzyjcs) throws SQLException{
		// TODO Auto-generated method stub
		Gtzyjc d = new Gtzyjc();
		String sql= d.getInsertSql();
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//int id = getMaxDjywId() + 1;
		for (int i = 0; i < gtzyjcs.size(); i++) {
			d = gtzyjcs.get(i);
			//pct.setInt(1, id);
			setparames(pct, d);

			pct.addBatch();
			//id++;
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return false;
	}
	
	
	private void setparames(PreparedStatement pct, Gtzyjc gtzyjc) throws SQLException{
		Class<Gtzyjc> c = Gtzyjc.class;
		Field fields[] = c.getDeclaredFields();
		
		try {
			for (int i = 1; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				Method method = pd.getReadMethod();
				if (f.getType() == float.class) {
					pct.setFloat(i, (Float) method.invoke(gtzyjc));
				} else if (f.getType() == String.class) {
					pct.setString(i, (String) method.invoke(gtzyjc));
				} else if (f.getType() == int.class) {
					pct.setInt(i, (Integer) method.invoke(gtzyjc));
				} else if (f.getType() == Date.class) {
					pct.setDate(i, (Date) method.invoke(gtzyjc));
				}
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}

	}
	
	/*
	 * private int getMaxGtzyjcId() { int id=0; String sql =
	 * "select max(daid) from Gtzyjc"; Connection conn = DbUtil.getConnection();
	 * try { Statement pct = conn.createStatement(); ResultSet rs =
	 * pct.executeQuery(sql); if(rs.next()) { id = rs.getInt(1); } } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { DbUtil.closeConnection(conn); } return
	 * id; }
	 */
}
