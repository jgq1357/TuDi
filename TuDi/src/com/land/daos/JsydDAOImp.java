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

import com.land.domain.Jsyd;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class JsydDAOImp implements JsydDAO{

	@Override
	public List<Jsyd> getAllJsyd(int start, int end) throws Exception {
		List <Jsyd> lists = new ArrayList<Jsyd>();
		String sql = "select * from (select d.*,rownum r from JSYD d) where r between ? and ?";
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
			Jsyd jsyd = new Jsyd(params);
			lists.add(jsyd);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public Jsyd getJsydById(String id) throws Exception{
		String sql = "select * from JSYD where daid = ?";
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
			Jsyd jsyd = new Jsyd(params);
			DbUtil.closeConnection(conn);
			return jsyd;
		}
		DbUtil.closeConnection(conn);
		return null;
	}

	@Override
	public List<Jsyd> searchJsydBy(String conditions, int start, int end) throws Exception {
		List <Jsyd> lists = new ArrayList<Jsyd>();
		String sql = "select * from (select t.*,rownum r from JSYD t where "+conditions+") where r between ? and ?";
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
			Jsyd jsyd = new Jsyd(params);
			lists.add(jsyd);
		}
		DbUtil.closeConnection(conn);
		return lists;
	}

	@Override
	public int countTotal(String conditions) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select count(*) from JSYD ";
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
	public boolean insertJsyd(List<Jsyd> jsyds) throws SQLException{
		// TODO Auto-generated method stub
		Jsyd d = new Jsyd();
		String sql= d.getInsertSql();
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		//int id = getMaxDjywId() + 1;
		for (int i = 0; i < jsyds.size(); i++) {
			d = jsyds.get(i);
			//pct.setInt(1, id);
			setparames(pct, d);

			pct.addBatch();
			//id++;
		}
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		return false;
	}
	
	
	private void setparames(PreparedStatement pct, Jsyd jsyd) throws SQLException{
		Class<Jsyd> c = Jsyd.class;
		Field fields[] = c.getDeclaredFields();
		
		try {
			for (int i = 1; i < fields.length; i++) {
				Field f = fields[i];
				PropertyDescriptor pd = new PropertyDescriptor(f.getName(), c);
				Method method = pd.getReadMethod();
				if (f.getType() == float.class) {
					pct.setFloat(i, (Float) method.invoke(jsyd));
				} else if (f.getType() == String.class) {
					pct.setString(i, (String) method.invoke(jsyd));
				} else if (f.getType() == int.class) {
					pct.setInt(i, (Integer) method.invoke(jsyd));
				} else if (f.getType() == Date.class) {
					pct.setDate(i, (Date) method.invoke(jsyd));
				}
			}
		} catch (Exception e) {
			throw new SQLException(e.getMessage());
		}
		
	}
	
	/*
	 * private int getMaxJsydId() { int id=0; String sql =
	 * "select max(daid) from JSYD"; Connection conn = DbUtil.getConnection();
	 * try { Statement pct = conn.createStatement(); ResultSet rs =
	 * pct.executeQuery(sql); if(rs.next()) { id = rs.getInt(1); } } catch
	 * (SQLException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); }finally { DbUtil.closeConnection(conn); } return
	 * id; }
	 * 
	 * @Test public void Tes() {
	 * 
	 * 
	 * }
	 */
	
}
