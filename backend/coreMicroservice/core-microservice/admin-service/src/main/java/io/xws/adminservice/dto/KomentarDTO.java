package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KomentarDTO 
{
	protected long idKomentara;
    protected long idSmestaja;
    protected long idRezervacije;
    protected long idKorisnika;
    protected String komentar;
    protected Date datum;
    protected boolean objavljen;
	public long getIdKomentara() {
		return idKomentara;
	}
	public long getIdSmestaja() {
		return idSmestaja;
	}
	public long getIdRezervacije() {
		return idRezervacije;
	}
	public long getIdKorisnika() {
		return idKorisnika;
	}
	public String getKomentar() {
		return komentar;
	}
	public Date getDatum() {
		return datum;
	}
	public boolean isObjavljen() {
		return objavljen;
	}
	public void setIdKomentara(long idKomentara) {
		this.idKomentara = idKomentara;
	}
	public void setIdSmestaja(long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}
	public void setIdRezervacije(long idRezervacije) {
		this.idRezervacije = idRezervacije;
	}
	public void setIdKorisnika(long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}
	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}
	public void setDatum(Date datum) {
		this.datum = datum;
	}
	public void setObjavljen(boolean objavljen) {
		this.objavljen = objavljen;
	}

}
