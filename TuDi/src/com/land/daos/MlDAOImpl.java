package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.land.domain.ML;
import com.land.util.DbUtil;

public class MlDAOImpl implements MlDAO{

	//
	//地籍目录
	//
	@Override
	public List<ML> getDjMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM DJLJ WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	


	@Override
	public void insertDjmls(List<ML> djmls) throws Exception {

		String sql = "insert into djlj(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}

	@Override
	public String getFirstDjMl(String dah,String tableName) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from "+tableName+" where dah=? and order_num=(select min(order_num) from "+tableName+" where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
	
	
	
	//
	//建设用地目录
	//

	public List<ML> getJsydlMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM JSYDLML WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	



	public void insertJsydlmls(List<ML> djmls) throws Exception {

		String sql = "insert into jsydlml(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}


	public String getFirstJsydlMl(String dah) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from jsydlml where dah=? and order_num=(select min(order_num) from jsydlml where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
	
	//
	//地矿目录
	//

	public List<ML> getDklMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM DKLML WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	



	public void insertDklmls(List<ML> djmls) throws Exception {

		String sql = "insert into dklml(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}


	public String getFirstDklMl(String dah) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from dklml where dah=? and order_num=(select min(order_num) from dklml where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
	
	//
	//国土资源监察
	//
	public List<ML> getGtzyjclMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM GTZYJCLML WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	



	public void insertGtzyjclmls(List<ML> djmls) throws Exception {

		String sql = "insert into gtzyjclml(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}


	public String getFirstGtzyjclMl(String dah) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from gtzyjclml where dah=? and order_num=(select min(order_num) from gtzyjclml where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
	//
	//土地利用规划类目录
	//
	
	public List<ML> getTdlyghlMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM TDLYGHLML WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	



	public void insertTdlyghlmls(List<ML> djmls) throws Exception {

		String sql = "insert into tdlyghlml(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}


	public String getFirstTdlyghlMl(String dah) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from tdlyghlml where dah=? and order_num=(select min(order_num) from tdlyghlml where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
	//
	//综合文书类目录
	//

	public List<ML> getZhwslMlByID(String dah)throws Exception {
		List<ML> djljs = new ArrayList<ML>();
		String sql = "SELECT DAH,LJ,TM FROM ZHWSLML WHERE DAH=? ORDER BY ORDER_NUM";
		Connection conn = DbUtil.getConnection();
	
			PreparedStatement pct = conn.prepareStatement(sql);
			pct.setString(1, dah);
			ResultSet rs = pct.executeQuery();
			int index = 0;
			while(rs.next()){
				ML lj = new ML();
				lj.setOrder_num(index);
				lj.setDah(rs.getString("DAH"));
				lj.setLj(rs.getString("LJ"));
				lj.setTm(rs.getString("TM"));
				djljs.add(lj);
				index++;
			}
			DbUtil.closeConnection(conn);
		
		return djljs;
	}
	



	public void insertZhwslmls(List<ML> djmls) throws Exception {

		String sql = "insert into zhwslml(order_num,dah,lj,tm) values(?,?,?,?)";
		Connection conn = DbUtil.getConnection();

		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		for(int i=0;i<djmls.size();i++) {
			ML djlj = djmls.get(i);
			pct.setInt(1, djlj.getOrder_num());
			pct.setString(2, djlj.getDah());
			pct.setString(3, djlj.getLj());
			pct.setString(4, djlj.getTm());
			pct.addBatch();
		}
		
		pct.executeBatch();
		DbUtil.closeConnection(conn);
		
	}


	public String getFirstZhwslMl(String dah) throws SQLException {
		// TODO Auto-generated method stub
		String tm="";
		String sql = "select lj from zhwslml where dah=? and order_num=(select min(order_num) from zhwslml where dah=?)";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql);
		pct.setString(1, dah);
		pct.setString(2, dah);
		ResultSet rs = pct.executeQuery();
		if (rs.next()) {
			tm = rs.getString(1);
		}
		DbUtil.closeConnection(conn);
		return tm;
	}
}
