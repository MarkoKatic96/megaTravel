package com.megatravel.agentlocalbackend.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TipSmestaja")
public class TipSmestaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long tipSmestajaId;
	
	@NotNull
	private String nazivTipaSmestaja;
	
	@OneToMany(mappedBy="tipSmestaja", cascade=CascadeType.ALL)
	private List<Smestaj> smestaji;
	
	public TipSmestaja() {}

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

	public Long getTipSmestajaId() {
		return tipSmestajaId;
	}

	public void setTipSmestajaId(Long tipSmestajaId) {
		this.tipSmestajaId = tipSmestajaId;
	}
}
