package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.land.domain.AppLog;
import com.land.domain.Tjlog;
import com.land.util.Constant;
import com.land.util.DbUtil;

public class AppLogDAOImp implements AppLogDAO {

	@Override
	public void writeToDb(AppLog applog) {
		// TODO Auto-generated method stub
		String sql = "insert into applog(userid, username, ways, ip, pcname, category, context, otime) values (?,?,?,?,?,?,?,sysdate)";
		Connection conn = DbUtil.getConnection();
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, applog.getUserId());
			pst.setString(2, applog.getUserName());
			pst.setString(3, applog.getWays());
			pst.setString(4, applog.getIp());
			pst.setString(5, applog.getPcName());
			//pst.setString(6, applog.getoTime());
			pst.setString(6, applog.getCategory());
			pst.setString(7, applog.getContext());
			pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("插入记录失败 ["+applog+"]");
			e.printStackTrace();
		}finally {
			DbUtil.closeConnection(conn);
		}

	}

	@Override
	public void writeToFile(AppLog applog) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<AppLog> getLogs(int start, int end) throws Exception {
		// TODO Auto-generated method stub
		String sql = "select userid,username,ways,ip,pcname," +
				"otime,category,context from(select t.*,rownum r " +
				"from (select * from applog order by otime desc) t) " +
				"where r between ? and ?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		pst.setInt(1, start);
		pst.setInt(2, end);
		ResultSet rs = pst.executeQuery();
		List<AppLog> applogs = new ArrayList<AppLog>();
		while(rs.next()) {
			AppLog applog = new AppLog();
			 applog.setUserId(rs.getString("userid"));
			 applog.setUserName(rs.getString("username"));
			 applog.setWays(rs.getString("ways"));
			 applog.setIp(rs.getString("ip"));
			 applog.setPcName(rs.getString("pcname"));
			 applog.setoTime(rs.getTimestamp("otime"));
			 applog.setCategory(rs.getString("category"));
			 applog.setContext(rs.getString("context"));
			 applogs.add(applog);
		}
		DbUtil.closeConnection(conn);
		return applogs;
	}

	@Override
	public int getTotal() throws Exception {
		String sql = "select count(*) from applog";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			int total = rs.getInt(1);
			int every = Constant.ROWSOFLOG;
			total = (total-1)/every+1;
			DbUtil.closeConnection(conn);
			return total;
		}
		DbUtil.closeConnection(conn);
		return 1;
	}

	@Override
	public List<Tjlog> getTjLogs(String dateType, String conditions) throws SQLException {
		String sql = "select count(*) c,d,category from ( select to_char(otime,'%') d,category from applog # ) group by d,category order by d desc";
		sql =sql.replaceAll("%", dateType);
		sql = sql.replaceAll("#", conditions);
		Connection conn = DbUtil.getConnection();
		List<Tjlog> tjlogs;
		try {
			PreparedStatement pst = conn.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			tjlogs = new ArrayList<Tjlog>();
			while(rs.next()) {
				Tjlog tjlog = new Tjlog();
				tjlog.setNum(rs.getInt(1));
				tjlog.setDate(rs.getString(2));
				tjlog.setCategory(rs.getString(3));
				tjlogs.add(tjlog);
			}
		} catch (SQLException e) {
			throw e;
		}finally {
			DbUtil.closeConnection(conn);
		}
		return tjlogs;
	}

}
