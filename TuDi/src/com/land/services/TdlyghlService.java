package com.land.services;

import java.util.List;

import com.land.domain.Tdlyghl;


public interface TdlyghlService {
	public List<Tdlyghl> searchBy(String conditions,int start, int end) throws Exception;
	public Tdlyghl getTdlyghlByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
