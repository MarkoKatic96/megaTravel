package io.webxml.pretragaservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class DodatneUsluge {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idDodatneUsluge;
	
	@NotNull
	private String nazivDodatneUsluge;
	
	@ManyToMany(mappedBy = "listaDodatnihUsluga")
	private List<Smestaj> listaSmestaja;
	
	public DodatneUsluge() {
	
	}
	
	public DodatneUsluge(long idDodatneUsluge, String nazivDodatneUsluge) {
		super();
		this.idDodatneUsluge = idDodatneUsluge;
		this.nazivDodatneUsluge = nazivDodatneUsluge;
		listaSmestaja = new ArrayList<>();
	}

	public Long getIdDodatneUsluge() {
		return idDodatneUsluge;
	}

	public void setIdDodatneUsluge(Long idDodatneUsluge) {
		this.idDodatneUsluge = idDodatneUsluge;
	}

	public String getNazivDodatneUsluge() {
		return nazivDodatneUsluge;
	}

	public void setNazivDodatneUsluge(String nazivDodatneUsluge) {
		this.nazivDodatneUsluge = nazivDodatneUsluge;
	}

	public List<Smestaj> getListaSmestaja() {
		return listaSmestaja;
	}

	public void setListaSmestaja(List<Smestaj> listaSmestaja) {
		this.listaSmestaja = listaSmestaja;
	}	
}
