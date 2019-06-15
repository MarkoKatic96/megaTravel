//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package com.megatravel.agentlocalbackend.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "DodatneUsluge")
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
	
	public DodatneUsluge(Long id, @NotNull String nazivDodatneUsluge) {
		super();
		this.idDodatneUsluge = id;
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
