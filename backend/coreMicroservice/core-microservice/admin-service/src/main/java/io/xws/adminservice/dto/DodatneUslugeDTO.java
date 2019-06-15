package io.xws.adminservice.dto;

import lombok.Data;

@Data
public class DodatneUslugeDTO 
{
	protected long idDodatneUsluge;
    protected String nazivDodatneUsluge;
	public long getIdDodatneUsluge() {
		return idDodatneUsluge;
	}
	public String getNazivDodatneUsluge() {
		return nazivDodatneUsluge;
	}
	public void setIdDodatneUsluge(long idDodatneUsluge) {
		this.idDodatneUsluge = idDodatneUsluge;
	}
	public void setNazivDodatneUsluge(String naziv) {
		this.nazivDodatneUsluge = naziv;
	}
}
