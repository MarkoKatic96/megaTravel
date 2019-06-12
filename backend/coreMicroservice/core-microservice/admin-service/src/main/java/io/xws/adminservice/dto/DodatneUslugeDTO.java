package io.xws.adminservice.dto;

import lombok.Data;

@Data
public class DodatneUslugeDTO 
{
	protected long idDodatneUsluge;
    protected String naziv;
	public long getIdDodatneUsluge() {
		return idDodatneUsluge;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setIdDodatneUsluge(long idDodatneUsluge) {
		this.idDodatneUsluge = idDodatneUsluge;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
