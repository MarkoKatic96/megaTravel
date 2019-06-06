package io.webxml.pretragaservice.model;

import java.util.List;

public class RezervacijeRestTemplate {
	
	private List<Rezervacija> rezervacijaList;

	public RezervacijeRestTemplate() {
		
	}

	public List<Rezervacija> getRezervacijaList() {
		return rezervacijaList;
	}

	public void setRezervacijaList(List<Rezervacija> rezervacijaList) {
		this.rezervacijaList = rezervacijaList;
	}
	
	

}
