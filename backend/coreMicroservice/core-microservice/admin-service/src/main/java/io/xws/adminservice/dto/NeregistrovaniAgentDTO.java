package io.xws.adminservice.dto;

import java.util.Date;

import lombok.Data;

@Data
public class NeregistrovaniAgentDTO 
{
	protected String ime;
    protected String prezime;
    protected String poslovniMaticniBroj;
    protected String email;
	public String getIme() {
		return ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public String getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}
	public String getEmail() {
		return email;
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
	public void setEmail(String email) {
		this.email = email;
	}
}
