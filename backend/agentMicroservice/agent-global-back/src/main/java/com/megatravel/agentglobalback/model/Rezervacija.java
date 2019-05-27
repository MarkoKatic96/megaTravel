//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modDatumaifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package com.megatravel.agentglobalback.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rezervacijaId",
    "smestajId",
    "korisnikId",
    "odDatuma",
    "doDatuma",
    "potvrdjenaRezervacija",
    "timestamp"
})
@XmlRootElement(name = "Rezervacija")
@Entity
public class Rezervacija {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long rezervacijaId;
    
	@NotNull
	protected Long smestajId;
	
	@NotNull
	private Long vlasnikId;
    
	@NotNull
	protected Long korisnikId;
	
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    protected Date odDatuma;
    
    @XmlElement(name = "do", required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    protected Date doDatuma;
    
    @XmlElement(defaultValue = "false")
    @NotNull
    protected String statusRezervacije;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    @CreationTimestamp
    protected Date timestamp;
    
    @NotNull
    private Date updateTimestamp;

    public Rezervacija() {
	}
    
    public Rezervacija(Long rezervacijaId, @NotNull Long smestajId, @NotNull Long vlasnikId, @NotNull Long korisnikId, @NotNull Date odDatuma,
			@NotNull Date doDatuma, @NotNull StatusRezervacije statusRezervacije) {
		super();
		this.rezervacijaId = rezervacijaId;
		this.smestajId = smestajId;
		this.vlasnikId = vlasnikId;
		this.korisnikId = korisnikId;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.statusRezervacije = statusRezervacije.toString();
		this.timestamp = new Date(System.currentTimeMillis());
		this.updateTimestamp = new Date(System.currentTimeMillis());
	}

	public Date getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(Date odDatuma) {
		this.odDatuma = odDatuma;
		this.updateTimestamp = new Date(System.currentTimeMillis());
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public Date getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(Date doDatuma) {
		this.doDatuma = doDatuma;
		this.updateTimestamp = new Date(System.currentTimeMillis());
	}

	/**
     * Gets the value of the rezervacijaId property.
     * 
     */
    public Long getRezervacijaId() {
        return rezervacijaId;
    }

    /**
     * Sets the value of the rezervacijaId property.
     * 
     */
    public void setRezervacijaId(Long value) {
        this.rezervacijaId = value;
        this.updateTimestamp = new Date(System.currentTimeMillis());
    }

    /**
     * Gets the value of the smestajId property.
     * 
     */
    public Long getSmestajId() {
        return smestajId;
    }

    /**
     * Sets the value of the smestajId property.
     * 
     */
    public void setSmestajId(Long value) {
        this.smestajId = value;
        this.updateTimestamp = new Date(System.currentTimeMillis());
    }

    /**
     * Gets the value of the korisnikId property.
     * 
     */
    public Long getKorisnikId() {
        return korisnikId;
    }

    /**
     * Sets the value of the korisnikId property.
     * 
     */
    public void setKorisnikId(Long value) {
        this.korisnikId = value;
        this.updateTimestamp = new Date(System.currentTimeMillis());
    }

    /**
     * Gets the value of the statusRezervacije property.
     * 
     */
    public StatusRezervacije getStatusRezervacije() {
        return StatusRezervacije.valueOf(statusRezervacije);
    }

    /**
     * Sets the value of the statusRezervacije property.
     * 
     */
    public void setStatusRezervacije(StatusRezervacije value) {
        this.statusRezervacije = value.toString();
        this.updateTimestamp = new Date(System.currentTimeMillis());
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setTimestamp(Date value) {
        this.timestamp = value;
        this.updateTimestamp = new Date(System.currentTimeMillis());
    }

	public Long getVlasnikId() {
		return vlasnikId;
	}

	public void setVlasnikId(Long vlasnikId) {
		this.vlasnikId = vlasnikId;
	}

}
