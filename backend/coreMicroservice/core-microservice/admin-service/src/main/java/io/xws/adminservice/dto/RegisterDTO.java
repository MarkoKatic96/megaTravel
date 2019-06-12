package io.xws.adminservice.dto;

import javax.xml.bind.annotation.XmlElement;

import lombok.Data;

@Data
public class RegisterDTO 
{
    protected String email;
    protected String ime;
    protected String prezime;
    protected String lozinka;
    protected String potvrdaLozinke;
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
	public String getPotvrdaLozinke() {
		return potvrdaLozinke;
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
	public void setPotvrdaLozinke(String potvrdaLozinke) {
		this.potvrdaLozinke = potvrdaLozinke;
	}

}
