package io.webxml.pretragaservice.model;

import java.util.List;

public class SamostalnaRezervacijaRestTemplate {
	
	private List<SamostalnaRezervacija> samostalnaRezervacijaList;

	public SamostalnaRezervacijaRestTemplate() {}
	
	public List<SamostalnaRezervacija> getSamostalnaRezervacijaList() {
		return samostalnaRezervacijaList;
	}

	public void setSamostalnaRezervacijaList(List<SamostalnaRezervacija> samostalnaRezervacijaList) {
		this.samostalnaRezervacijaList = samostalnaRezervacijaList;
	}
	
	

}
