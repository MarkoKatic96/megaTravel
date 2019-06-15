//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modDatumaifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package io.xws.adminservice.model;

import java.util.Date;

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
@XmlType(name = "")
@XmlRootElement(name = "Samostalna_rezervacija")
public class SamostalnaRezervacija {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long samostalnaRezervacijaId;
	
	@NotNull
    private Long smestajId;
	
	@NotNull
	private Long vlasnikId;
	
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    private Date odDatuma;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    private Date doDatuma;
    
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    @NotNull
    @CreationTimestamp
    private Date timestamp;

    public SamostalnaRezervacija() {
	}
    
    public SamostalnaRezervacija(Long samostalnaRezervacijaId, @NotNull Long smestajId, @NotNull Long vlasnikId, @NotNull Date odDatuma,
			@NotNull Date doDatuma) {
		super();
		this.samostalnaRezervacijaId = samostalnaRezervacijaId;
		this.smestajId = smestajId;
		this.vlasnikId = vlasnikId;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.timestamp = new Date(System.currentTimeMillis());
	}

	public Date getOdDatuma() {
		return odDatuma;
	}

	public void setOdDatuma(Date odDatuma) {
		this.odDatuma = odDatuma;
	}

	public Date getDoDatuma() {
		return doDatuma;
	}

	public void setDoDatuma(Date doDatuma) {
		this.doDatuma = doDatuma;
	}

	public Long getVlasnikId() {
		return vlasnikId;
	}

	public void setVlasnikId(Long vlasnikId) {
		this.vlasnikId = vlasnikId;
	}

	/**
     * Gets the value of the samostalnaRezervacijaId property.
     * 
     */
    public Long getSamostalnaRezervacijaId() {
        return samostalnaRezervacijaId;
    }

    /**
     * Sets the value of the samostalnaRezervacijaId property.
     * 
     */
    public void setSamostalnaRezervacijaId(Long value) {
        this.samostalnaRezervacijaId = value;
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
