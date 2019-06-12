//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package com.megatravel.agentglobalback.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "PotvrdaRezervacije")
@Entity
public class PotvrdaRezervacije {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
	private Long potvrdaRezervacijeId;
    
	@XmlElement(required = true)
	private Long rezervacijaId;
    
	@XmlElement(required = true)
    private StatusRezervacije statusRezervacije;
 
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    private Date timestamp;

    
    public PotvrdaRezervacije() {
	}
    
    
    public PotvrdaRezervacije(Long potvrdaRezervacijeId, Long rezervacijaId,
			StatusRezervacije statusRezervacije) {
		super();
		this.potvrdaRezervacijeId = potvrdaRezervacijeId;
		this.rezervacijaId = rezervacijaId;
		this.statusRezervacije = statusRezervacije;
		this.timestamp = new Date(System.currentTimeMillis());
	}


	public Long getPotvrdaRezervacijeId() {
		return potvrdaRezervacijeId;
	}


	public void setPotvrdaRezervacijeId(Long potvrdaRezervacijeId) {
		this.potvrdaRezervacijeId = potvrdaRezervacijeId;
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
    }

    /**
     * Gets the value of the statusRezervacije property.
     * 
     */
    public StatusRezervacije getStatusRezervacije() {
        return statusRezervacije;
    }

    /**
     * Sets the value of the statusRezervacije property.
     * 
     */
    public void setStatusRezervacije(StatusRezervacije value) {
        this.statusRezervacije = value;
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
    }

}
