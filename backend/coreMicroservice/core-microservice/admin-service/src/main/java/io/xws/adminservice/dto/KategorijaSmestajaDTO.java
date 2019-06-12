package io.xws.adminservice.dto;

import lombok.Data;

@Data
public class KategorijaSmestajaDTO
{
	protected long idKategorijeSmestaja;
	protected String naziv;
	public long getIdKategorijeSmestaja() {
		return idKategorijeSmestaja;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setIdKategorijeSmestaja(long idKategorijeSmestaja) {
		this.idKategorijeSmestaja = idKategorijeSmestaja;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
}
