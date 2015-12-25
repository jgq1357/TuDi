package com.land.actions.main;

import java.util.ArrayList;
import java.util.List;

import com.land.actions.LandSupport;
import com.land.domain.Qzl;

public class QzlAction extends LandSupport{
	private List<Qzl> qzls = new ArrayList<Qzl>();

	public String execute() {
		return "success";
	}
}
