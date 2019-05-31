//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:00:08 PM CEST 
//


package io.webxml.pretragaservice.model;

import java.sql.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



/*
  {
  	"mesto": "",
  	"datumDolaska": "",
  	"datumPolaska": "",
  	"brojOsoba": "",
  	"tipSmestaja": "",
  	"kategorijaSmestaja": "",
  	dodatneUsluge: [
  		{....}
  	]
  }
 * 
 * 
 * 
 * */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "mesto",
    "datumDolaska",
    "datumPolaska",
    "cena"
})
@XmlRootElement(name = "OsnovnaPretraga")
public class OsnovnaPretraga {

    @XmlElement(required = true)
    private String mesto;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private Date datumDolaska;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private Date datumPolaska;
    @XmlElement(required = true)
    private float cena;
    @XmlElement(required = true)
    private int brojOsoba;
    @XmlElement(required = true)
    private Long tipSmestaja;
    @XmlElement(required = true)
    private Long kategorijaSmestaja;
    private List<Long> dodatneUsluge;
    
    public OsnovnaPretraga() {

  	}
    
    public OsnovnaPretraga(String mesto, Date datumDolaska, Date datumPolaska, float cena, int brojOsoba, Long tipSmestaja, Long kategorijaSmestaja, List<Long> dodatneUsluge) {
		super();
		this.mesto = mesto;
		this.datumDolaska = datumDolaska;
		this.datumPolaska = datumPolaska;
		this.cena = cena;
		this.brojOsoba = brojOsoba;
		this.tipSmestaja = tipSmestaja;
		this.kategorijaSmestaja = kategorijaSmestaja;
		this.dodatneUsluge = dodatneUsluge;
	}

	/**
     * Gets the value of the mesto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMesto() {
        return mesto;
    }

    /**
     * Sets the value of the mesto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMesto(String value) {
        this.mesto = value;
    }

    /**
     * Gets the value of the datumDolaska property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDatumDolaska() {
        return datumDolaska;
    }

    /**
     * Sets the value of the datumDolaska property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDatumDolaska(Date value) {
        this.datumDolaska = value;
    }

    /**
     * Gets the value of the datumPolaska property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDatumPolaska() {
        return datumPolaska;
    }

    /**
     * Sets the value of the datumPolaska property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDatumPolaska(Date value) {
        this.datumPolaska = value;
    }

    /**
     * Gets the value of the cena property.
     * 
     * @return
     *     possible object is
     *     {@link float }
     *     
     */
    public int getBrojOsoba() {
        return brojOsoba;
    }

    /**
     * Sets the value of the cena property.
     * 
     * @param value
     *     allowed object is
     *     {@link float }
     *     
     */
    public void setBrojOsoba(int value) {
        this.brojOsoba = value;
    }

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Long getTipSmestaja() {
		return tipSmestaja;
	}

	public void setTipSmestaja(Long tipSmestaja) {
		this.tipSmestaja = tipSmestaja;
	}

	public Long getKategorijaSmestaja() {
		return kategorijaSmestaja;
	}

	public void setKategorijaSmestaja(Long kategorijaSmestaja) {
		this.kategorijaSmestaja = kategorijaSmestaja;
	}

	public List<Long> getDodatneUsluge() {
		return dodatneUsluge;
	}

	public void setDodatneUsluge(List<Long> dodatneUsluge) {
		this.dodatneUsluge = dodatneUsluge;
	}
    
    

}
