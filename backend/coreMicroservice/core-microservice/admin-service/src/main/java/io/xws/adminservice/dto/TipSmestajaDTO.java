package io.xws.adminservice.dto;

import lombok.Data;

@Data
public class TipSmestajaDTO 
{
	protected long idTipaSmestaja;
	protected String naziv;
	public long getIdTipaSmestaja() {
		return idTipaSmestaja;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setIdTipaSmestaja(long idTipaSmestaja) {
		this.idTipaSmestaja = idTipaSmestaja;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
