package io.xws.adminservice.model;

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
import javax.xml.datatype.XMLGregorianCalendar;

import org.hibernate.annotations.CreationTimestamp;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idAgenta",
    "ime",
    "prezime",
    "poslovniMaticniBroj",
    "datumClanstva",
    "email",
    "lozinka"
})
@XmlRootElement(name = "Agent")
@Entity
public class Agent {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
    private Long idAgenta;
	
	@NotNull
    @XmlElement(required = true)
    private String ime;
	
	@NotNull
    @XmlElement(required = true)
    private String prezime;
	
	@NotNull
    private Long poslovniMaticniBroj;
	
	@NotNull
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
	@CreationTimestamp
    private Date datumClanstva;
	
	@NotNull
    @XmlElement(required = true)
    private String email;
	
	@NotNull
    @XmlElement(required = true)
    private String lozinka;
	
    public Agent() {
		super();
	}

	public Agent(Long idAgenta, @NotNull String ime, @NotNull String prezime, @NotNull Long poslovniMaticniBroj,
			@NotNull String email, @NotNull String lozinka) {
		super();
		this.idAgenta = idAgenta;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = new Date(System.currentTimeMillis());
		this.email = email;
		this.lozinka = lozinka;
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
    public @NotNull Date getDatumClanstva() {
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
    public void setDatumClanstva(@NotNull Date value) {
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
