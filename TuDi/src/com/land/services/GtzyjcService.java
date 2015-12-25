package com.land.services;

import java.util.List;

import com.land.domain.Gtzyjc;

public interface GtzyjcService {

	public List<Gtzyjc> searchBy(String conditions,int start, int end) throws Exception;
	public Gtzyjc getGtzyjcById(String id) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
