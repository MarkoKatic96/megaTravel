package io.xws.adminservice.dto;

import java.util.Date;

import io.xws.adminservice.model.StatusKomentara;
import lombok.Data;

@Data
public class KomentarDTO 
{
	protected long idKomentara;
    protected long idSmestaja;
    protected long idRezervacije;
    protected long idKorisnika;
    protected String komentar;
    protected Date timestamp;
    protected StatusKomentara status;
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
		return timestamp;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public StatusKomentara getStatus() {
		return status;
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
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public void setStatus(StatusKomentara status) {
		this.status = status;
	}

}
