package io.webxml.pretragaservice.model;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

@XmlType(name = "TipSmestaja")
public class TipSmestaja {
	
	private Long idTipaSmestaja;
	
	private String nazivTipaSmestaja;
	
	private List<Smestaj> smestaji;
	
	public TipSmestaja() {}

	public TipSmestaja(Long idTipaSmestaja, String nazivTipaSmestaja, List<Smestaj> smestaji) {
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
