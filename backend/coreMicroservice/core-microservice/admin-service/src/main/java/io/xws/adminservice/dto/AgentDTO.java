package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class AgentDTO 
{
    protected Long idAgenta;
    protected String ime;
    protected String prezime;
    protected String poslovniMaticniBroj;
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
	public String getPoslovniMaticniBroj() {
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
	public void setPoslovniMaticniBroj(String poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
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
