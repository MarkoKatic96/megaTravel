package com.megatravel.agentglobalback.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "KategorijaSmestaja")
@XmlRootElement(name = "KategorijaSmestaja")
@Entity
public class KategorijaSmestaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
	private Long id;
	
	@XmlElement(required = true)
	@NotNull
	private String naziv;
	
	@OneToMany(mappedBy="kategorijaSmestaja", cascade=CascadeType.ALL)
	@XmlElement(required = true)
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
