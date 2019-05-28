package io.webxml.pretragaservice.model;

public class DodatneUsluge {

	private long idDodatneUsluge;
	private String nazivDodatneUsluge;
	private Smestaj smestaj;
	
	
	public DodatneUsluge() {}
	
	public DodatneUsluge(long idDodatneUsluge, String nazivDodatneUsluge, Smestaj smestaj) {
		super();
		this.idDodatneUsluge = idDodatneUsluge;
		this.nazivDodatneUsluge = nazivDodatneUsluge;
		this.smestaj = smestaj;
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

	public Smestaj getSmestaj() {
		return smestaj;
	}

	public void setSmestaj(Smestaj smestaj) {
		this.smestaj = smestaj;
	}
	
	
	
	
	
}
