package com.land.daos;

import java.sql.SQLException;
import java.util.List;

import com.land.domain.Zhwsl;


public interface ZhwslDAO {
	public List<Zhwsl> getAllZhwsl(int start, int end) throws Exception;
	public Zhwsl getZhwslByDah(String dah) throws Exception;
	public List<Zhwsl> searchZhwslBy(String conditions, int start, int end) throws Exception;
	public int countTotal(String conditions) throws Exception;
	public boolean insertZhwsl(List<Zhwsl> Zhwsls) throws SQLException;
}
