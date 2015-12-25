package com.land.daos;

import java.sql.SQLException;

import com.land.domain.Sys;

public interface SysParamsDAO {

	public Sys getParams() throws SQLException;
	public void updateParams(Sys sys) throws SQLException;
}
