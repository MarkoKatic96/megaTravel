package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class KorisnikDTO 
{
	protected long idKorisnik;
    protected String email;
    protected String ime;
    protected String prezime;
    protected String lozinka;
    protected Date datumClanstva;
    protected boolean registrovan;
    protected boolean blokiran;
    protected boolean aktiviran;
	public long getIdKorisnik() {
		return idKorisnik;
	}
	public String getEmail() {
		return email;
	}
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public String getLozinka() {
		return lozinka;
	}
	public Date getDatumClanstva() {
		return datumClanstva;
	}
	public boolean isRegistrovan() {
		return registrovan;
	}
	public boolean isBlokiran() {
		return blokiran;
	}
	public boolean isAktiviran() {
		return aktiviran;
	}
	public void setIdKorisnik(long idKorisnik) {
		this.idKorisnik = idKorisnik;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
	public void setDatumClanstva(Date datumClanstva) {
		this.datumClanstva = datumClanstva;
	}
	public void setRegistrovan(boolean registrovan) {
		this.registrovan = registrovan;
	}
	public void setBlokiran(boolean blokiran) {
		this.blokiran = blokiran;
	}
	public void setAktiviran(boolean aktiviran) {
		this.aktiviran = aktiviran;
	}
}
