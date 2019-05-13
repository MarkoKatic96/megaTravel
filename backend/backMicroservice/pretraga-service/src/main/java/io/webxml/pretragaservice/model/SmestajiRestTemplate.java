package io.webxml.pretragaservice.model;

import java.util.List;

public class SmestajiRestTemplate {
	
	private List<Smestaj> smestajiList;

	public SmestajiRestTemplate() {

	}

	public List<Smestaj> getSmestajiList() {
		return smestajiList;
	}

	public void setSmestajiList(List<Smestaj> smestajiList) {
		this.smestajiList = smestajiList;
	}
	
}
