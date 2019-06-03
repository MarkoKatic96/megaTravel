package com.megatravel.smestajservice.model;

import java.util.Date;

public class Agent {
	private Long idAgenta;
	private String ime;
	private String prezime;
	private Long poslovniMaticniBroj;
	private Date datumClanstva;
	private String email;
	private String lozinka;
	
    public Agent() {
		super();
	}

	/**
     * Gets the value of the idAgenta property.
     * 
     */
    public Long getIdAgenta() {
        return idAgenta;
    }

    /**
     * Sets the value of the idAgenta property.
     * 
     */
    public void setIdAgenta(Long value) {
        this.idAgenta = value;
    }

    /**
     * Gets the value of the ime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIme() {
        return ime;
    }

    /**
     * Sets the value of the ime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIme(String value) {
        this.ime = value;
    }

    /**
     * Gets the value of the prezime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrezime() {
        return prezime;
    }

    /**
     * Sets the value of the prezime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrezime(String value) {
        this.prezime = value;
    }

    /**
     * Gets the value of the poslovniMaticniBroj property.
     * 
     */
    public Long getPoslovniMaticniBroj() {
        return poslovniMaticniBroj;
    }

    /**
     * Sets the value of the poslovniMaticniBroj property.
     * 
     */
    public void setPoslovniMaticniBroj(Long value) {
        this.poslovniMaticniBroj = value;
    }

    /**
     * Gets the value of the datumClanstva property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public Date getDatumClanstva() {
        return datumClanstva;
    }

    /**
     * Sets the value of the datumClanstva property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumClanstva(Date value) {
        this.datumClanstva = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the lozinka property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLozinka() {
        return lozinka;
    }

    /**
     * Sets the value of the lozinka property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLozinka(String value) {
        this.lozinka = value;
    }
}
