package io.webxml.reservationservice.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

public class KategorijaSmestaja {
	
	private Long id;
	
	@XmlElement(required = true)
	@NotNull
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
