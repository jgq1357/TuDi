package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Jsyd;

public interface JsydDAO {

	public List<Jsyd> getAllJsyd(int start, int end) throws Exception;
	public Jsyd getJsydById(String id) throws Exception;
	public List<Jsyd> searchJsydBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertJsyd(List<Jsyd> jsyds) throws SQLException;
}
