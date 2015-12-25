package com.land.daos;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.land.domain.Record;

public interface SearchDAO {
	public Map<String, String> getDics(String prefix) throws SQLException;
	public String getTableName(int daType) throws SQLException;
	public List<Record> getRecords(String tableName, String conditions, int start, int end) throws SQLException;
	public int countTotalPage(String conditions,String tableName) throws SQLException;
	public int countTotalSize(String conditions,String tableName) throws SQLException;
	public Map<String,String> getRecordFieldByDah(String dah, String tableName) throws SQLException;
	public String getShortName(String daType) throws SQLException;
	public Map<String, String> getRealNames() throws SQLException;
}
