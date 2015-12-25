package com.land.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.land.domain.Sys;
import com.land.util.DbUtil;

public class SysParamsDAOImp implements SysParamsDAO {

	@Override
	public Sys getParams() throws SQLException {
		String sql = "select param_name,param_value from land_params";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pst = conn.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		Sys sys = new Sys();
		while(rs.next()) {
			if("ROWSOFLOG".equalsIgnoreCase(rs.getString("param_name"))) {
				sys.setRowsOfLog(rs.getString("param_value"));
			}else if("ROWSOFSEARCHRESULT".equalsIgnoreCase(rs.getString("param_name"))) {
				sys.setRowsOfSearch(rs.getString("param_value"));
			}else if("LOGFILEPATH".equalsIgnoreCase(rs.getString("param_name"))) {
				sys.setLogPath(rs.getString("param_value"));
			}else if("LOGLEVEL".equalsIgnoreCase(rs.getString("param_name"))) {
				sys.setLogLevel(rs.getString("param_value"));
			}
		}
		DbUtil.closeConnection(conn);
		return sys;
	}

	@Override
	public void updateParams(Sys sys) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update land_params set param_value=? where param_name=?";
		Connection conn = DbUtil.getConnection();
		PreparedStatement pct = conn.prepareStatement(sql,
				ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
		pct.setString(1, sys.getLogLevel());
		pct.setString(2, "LOGLEVEL");
		pct.addBatch();
		pct.setString(1, sys.getLogPath());
		pct.setString(2, "LOGFILEPATH");
		pct.addBatch();
		pct.setString(1, sys.getRowsOfLog());
		pct.setString(2, "ROWSOFLOG");
		pct.addBatch();
		pct.setString(1, sys.getRowsOfSearch());
		pct.setString(2, "ROWSOFSEARCHRESULT");
		pct.addBatch();
		pct.executeBatch();
		DbUtil.closeConnection(conn);
	}

}
