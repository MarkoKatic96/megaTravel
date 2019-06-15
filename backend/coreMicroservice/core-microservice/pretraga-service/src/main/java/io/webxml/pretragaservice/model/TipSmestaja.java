package io.webxml.pretragaservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TipSmestaja")
public class TipSmestaja {
	
	private Long tipSmestajaId;
	
	private String nazivTipaSmestaja;
	
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
