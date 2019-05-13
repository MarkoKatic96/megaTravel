package io.webxml.pretragaservice.model;

public class Smestaj {

	private Long id;
	
	private String adresa;
	
	private float cena;

	public Smestaj() {

	}
	
	public Smestaj(Long id, String adresa, float cena) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.cena = cena;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	
	
	
}
