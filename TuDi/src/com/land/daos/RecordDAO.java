package com.land.daos;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.land.domain.Condition;
import com.land.domain.DaDesc;
import com.land.domain.Dalb;
import com.land.domain.Dictionary;
import com.land.domain.Menu;
import com.land.domain.Record;

public interface RecordDAO {
	public boolean insertRecord(List<Record> records, String tableName) throws SQLException;
	public List<Menu> getDaMenu() throws SQLException;
	public List<String> getTableColumns(String tableName) throws SQLException;
	public boolean createRecordTable(String tableName, List<String> fieldNames, String RecordName, List<String> sf, String shortName, List<String> roles) throws SQLException;
	public List<Condition> getAllFields() throws SQLException;
	public Map<String, Dictionary> getDics(String prefix) throws SQLException;
	public boolean updateDictionary(List<Dictionary> dics, String isField) throws SQLException;
	public boolean updateDalb(Dalb dalb) throws SQLException;
	public int generateLbh() throws SQLException;
	public List<DaDesc> getDaDesc() throws SQLException;
	public String getTableName(int id) throws SQLException;
	public boolean updateSearchConditions(int id, List<String> sf) throws SQLException;
}
