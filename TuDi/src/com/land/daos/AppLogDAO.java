package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.AppLog;
import com.land.domain.Tjlog;

public interface AppLogDAO {
	public void writeToDb(AppLog applog);
	public void writeToFile(AppLog applog);
	public List<AppLog> getLogs(int start, int end) throws Exception;
	public int getTotal() throws Exception;
	public List<Tjlog> getTjLogs(String dateType,String conditions) throws SQLException;
}
