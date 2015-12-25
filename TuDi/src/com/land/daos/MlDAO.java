package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.ML;

public interface MlDAO {
	public  List<ML> getDjMlByID(String dah) throws Exception;
	public void insertDjmls(List<ML> djljs) throws Exception;
	public String getFirstDjMl(String dah,String tableName) throws SQLException;
}
