package io.webxml.reservationservice.model;

import java.util.List;

public class SmestajiRestTemplate {
	
	private List<SmestajKorisnikDTO> smestajiList;

	public SmestajiRestTemplate() {

	}

	public List<SmestajKorisnikDTO> getSmestajiList() {
		return smestajiList;
	}

	public void setSmestajiList(List<SmestajKorisnikDTO> smestajiList) {
		this.smestajiList = smestajiList;
	}
	
}
