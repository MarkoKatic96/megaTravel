package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AgentDTO 
{
    protected Long idAgenta;
    protected String ime;
    protected String prezime;
    protected Long poslovniMaticniBroj;
    protected Date datumClanstva;
    protected String email;
    protected String lozinka;
	public Long getIdAgenta() {
		return idAgenta;
	}
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public Long getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}
	public Date getDatumClanstva() {
		return datumClanstva;
	}
	public String getEmail() {
		return email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setIdAgenta(Long idAgenta) {
		this.idAgenta = idAgenta;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public void setPoslovniMaticniBroj(Long long1) {
		this.poslovniMaticniBroj = long1;
	}
	public void setDatumClanstva(Date datumClanstva) {
		this.datumClanstva = datumClanstva;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
