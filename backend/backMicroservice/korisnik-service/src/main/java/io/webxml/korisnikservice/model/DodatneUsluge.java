package io.webxml.korisnikservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class DodatneUsluge {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idDodatneUsluge;
	private String nazivDodatneUsluge;
	@ManyToOne
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
