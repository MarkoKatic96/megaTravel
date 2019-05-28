package io.webxml.pretragaservice.model;

import java.util.List;


public class TipSmestaja {
	
	private long idTipaSmestaja;
	private String nazivTipaSmestaja;
	private List<Smestaj> smestaji;
	
	public TipSmestaja() {}
	
	public TipSmestaja(long idTipaSmestaja, String nazivTipaSmestaja, List<Smestaj> smestaji) {
		super();
		this.idTipaSmestaja = idTipaSmestaja;
		this.nazivTipaSmestaja = nazivTipaSmestaja;
		this.smestaji = smestaji;
	}

	public long getIdTipaSmestaja() {
		return idTipaSmestaja;
	}

	public void setIdTipaSmestaja(long idTipaSmestaja) {
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
