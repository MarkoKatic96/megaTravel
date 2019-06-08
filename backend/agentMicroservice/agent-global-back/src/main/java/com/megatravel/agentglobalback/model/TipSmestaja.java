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
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TipSmestaja")
@Entity
public class TipSmestaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
	private Long idTipaSmestaja;
	
	@NotNull
	@XmlElement(required = true)
	private String nazivTipaSmestaja;
	
	@OneToMany(mappedBy="tipSmestaja", cascade=CascadeType.ALL)
	private List<Smestaj> smestaji;
	
	public TipSmestaja() {}

	public TipSmestaja(Long idTipaSmestaja, @NotNull String nazivTipaSmestaja, List<Smestaj> smestaji) {
		super();
		this.idTipaSmestaja = idTipaSmestaja;
		this.nazivTipaSmestaja = nazivTipaSmestaja;
		this.smestaji = smestaji;
	}

	public Long getIdTipaSmestaja() {
		return idTipaSmestaja;
	}

	public void setIdTipaSmestaja(Long idTipaSmestaja) {
		this.idTipaSmestaja = idTipaSmestaja;
	}

	public String getNazivTipaSmestaja() {
		return nazivTipaSmestaja;
	}

	public void setNazivTipaSmestaja(String nazivTipaSmestaja) {
		this.nazivTipaSmestaja = nazivTipaSmestaja;
	}

	public List<Smestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(List<Smestaj> smestaji) {
		this.smestaji = smestaji;
	}
}
