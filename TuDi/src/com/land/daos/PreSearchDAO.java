package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Condition;
import com.land.domain.Dalb;

public interface PreSearchDAO {
	public List<Condition> getConditions(int lm_id) throws SQLException;
	public List<Dalb> getDalbs(int lm_id) throws SQLException;
	public String getShortName(int daType) throws SQLException;
}
