package com.megatravel.agentglobalback.dto;

import java.util.Date;

import com.megatravel.agentglobalback.model.Agent;

public class AgentDTO {

    protected Long idAgenta;	
    protected String ime;
    protected String prezime;
    protected Long poslovniMaticniBroj;
    protected Date datumClanstva;	
    protected String email;
	
    public AgentDTO(Long idAgenta, String ime, String prezime, Long poslovniMaticniBroj, Date datumClanstva,
			String email) {
		super();
		this.idAgenta = idAgenta;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = datumClanstva;
		this.email = email;
	}
    
    public AgentDTO() {
    	
	}

    public AgentDTO(Agent agent) {
		super();
		this.idAgenta = agent.getIdAgenta();
		this.ime = agent.getIme();
		this.prezime = agent.getPrezime();
		this.poslovniMaticniBroj = agent.getPoslovniMaticniBroj();
		this.datumClanstva = agent.getDatumClanstva();
		this.email = agent.getEmail();
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

	public Date getDatumClanstva() {
		return datumClanstva;
	}

	public void setDatumClanstva(Date datumClanstva) {
		this.datumClanstva = datumClanstva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}