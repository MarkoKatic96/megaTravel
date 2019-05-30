package io.webxml.korisnikservice.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class TipSmestaja {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idTipaSmestaja;
	private String nazivTipaSmestaja;
	@OneToMany(mappedBy="tipSmestaja", cascade=CascadeType.ALL)
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
