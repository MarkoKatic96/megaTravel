package com.megatravel.agentlocalbackend.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agentRegistracijaDTO", propOrder = {
    "email",
    "idAgenta",
    "ime",
    "poslovniMaticniBroj",
    "prezime"
})
public class AgentRegistracijaDTO {

	@XmlElement(namespace="https://megatravel.com/email")
    protected String email;
	
	@XmlElement(namespace="https://megatravel.com/idAgenta")
    protected Long idAgenta;
	
	@XmlElement(namespace="https://megatravel.com/ime")
    protected String ime;
	
	@XmlElement(namespace="https://megatravel.com/poslovniMaticniBroj")
    protected Long poslovniMaticniBroj;
	
	@XmlElement(namespace="https://megatravel.com/prezime")
    protected String prezime;
    	
    public AgentRegistracijaDTO() {
	
	}

	public Long getIdAgenta() {
		return idAgenta;
	}

	public void setIdAgenta(Long idAgenta) {
		this.idAgenta = idAgenta;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Long getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(Long poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	/*public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPotvrdaLozinke() {
		return potvrdaLozinke;
	}

	public void setPotvrdaLozinke(String potvrdaLozinke) {
		this.potvrdaLozinke = potvrdaLozinke;
	}
	*/
	
}