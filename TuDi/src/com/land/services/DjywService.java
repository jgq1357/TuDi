package com.land.services;

import java.util.List;

import com.land.domain.Djyw;

public interface DjywService {
	
	public List<Djyw> searchBy(String conditions,int start, int end) throws Exception;
	public Djyw getDjywByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
