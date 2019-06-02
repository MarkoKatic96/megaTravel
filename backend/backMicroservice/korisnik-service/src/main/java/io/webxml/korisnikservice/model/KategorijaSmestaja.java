package io.webxml.korisnikservice.model;

import java.util.List;

public class KategorijaSmestaja {
	
	private Long id;
	
	private String naziv;
	
	private List<Smestaj> smestaji;
		
	public KategorijaSmestaja() {

	}
	
	public KategorijaSmestaja(Long id, String naziv, List<Smestaj> smestaji) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.smestaji = smestaji;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public List<Smestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(List<Smestaj> smestaji) {
		this.smestaji = smestaji;
	}
	
	

}
