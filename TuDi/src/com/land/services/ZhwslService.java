package com.land.services;

import java.util.List;

import com.land.domain.Zhwsl;


public interface ZhwslService {
	public List<Zhwsl> searchBy(String conditions,int start, int end) throws Exception;
	public Zhwsl getZhwslByDah(String dah) throws Exception;
	public int countTotal(String conditions) throws Exception;
}
