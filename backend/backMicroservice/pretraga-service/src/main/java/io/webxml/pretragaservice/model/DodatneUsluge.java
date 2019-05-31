package io.webxml.pretragaservice.model;

public class DodatneUsluge {

	private long idDodatneUsluge;
	private String nazivDodatneUsluge;
	
	
	public DodatneUsluge() {}
	
	public DodatneUsluge(long idDodatneUsluge, String nazivDodatneUsluge) {
		super();
		this.idDodatneUsluge = idDodatneUsluge;
		this.nazivDodatneUsluge = nazivDodatneUsluge;
	}

	public long getIdDodatneUsluge() {
		return idDodatneUsluge;
	}

	public void setIdDodatneUsluge(long idDodatneUsluge) {
		this.idDodatneUsluge = idDodatneUsluge;
	}

	public String getNazivDodatneUsluge() {
		return nazivDodatneUsluge;
	}

	public void setNazivDodatneUsluge(String nazivDodatneUsluge) {
		this.nazivDodatneUsluge = nazivDodatneUsluge;
	}
	
	
	
	
	
}
