package com.megatravel.agentglobalback.dto;

public class AgentRegistracijaDTO {

    private Long idAgenta;	
    private String ime;
    private String prezime;
    private Long poslovniMaticniBroj;
    private String email;
    //private String lozinka;
    //private String potvrdaLozinke;
    	
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