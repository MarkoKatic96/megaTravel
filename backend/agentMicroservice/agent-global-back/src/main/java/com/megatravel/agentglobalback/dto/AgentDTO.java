package com.megatravel.agentglobalback.dto;

import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.megatravel.agentglobalback.model.Agent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agentDTO", propOrder = {
    "datumClanstva",
    "email",
    "idAgenta",
    "ime",
    "poslovniMaticniBroj",
    "prezime"
})
public class AgentDTO {
	
	@XmlSchemaType(name = "dateTime")
    @XmlElement(namespace="https://megatravel.com/datumClanstva")
    protected XMLGregorianCalendar datumClanstva;
    
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
	
    public AgentDTO(Long idAgenta, String ime, String prezime, Long poslovniMaticniBroj, XMLGregorianCalendar datumClanstva,
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
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(agent.getDatumClanstva());
		try {
			this.datumClanstva = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (DatatypeConfigurationException e) {
			this.datumClanstva = null;
		}
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

	public XMLGregorianCalendar getDatumClanstva() {
		return datumClanstva;
	}

	public void setDatumClanstva(XMLGregorianCalendar datumClanstva) {
		this.datumClanstva = datumClanstva;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
    
}