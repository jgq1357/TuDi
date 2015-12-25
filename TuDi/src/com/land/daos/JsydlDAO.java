package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Jsydl;


public interface JsydlDAO {
	public List<Jsydl> getAllJsydl(int start, int end) throws Exception;
	public Jsydl getJsydlByDah(String dah) throws Exception;
	public List<Jsydl> searchJsydlBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertJsydl(List<Jsydl> djyws) throws SQLException;
	
}
