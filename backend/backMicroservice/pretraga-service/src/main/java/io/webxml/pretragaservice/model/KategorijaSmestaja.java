package io.webxml.pretragaservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;

@Entity
public class KategorijaSmestaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@XmlElement(required = true)
	@NotNull
	private String naziv;
	
	@OneToMany(mappedBy="kategorijaSmestaja", cascade=CascadeType.ALL)
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
