//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:00:08 PM CEST 
//


package io.webxml.korisnikservice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idKorisnik">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;minInclusive value="0"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="email" type="{https://github.com/MarkoKatic96/megaTravel/xmlkorisnik}TEmail"/>
 *         &lt;element name="ime">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="60"/>
 *               &lt;minLength value="1"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="prezime">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;minLength value="1"/>
 *               &lt;maxLength value="60"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="lozinka" type="{https://github.com/MarkoKatic96/megaTravel/xmlkorisnik}TLozinka"/>
 *         &lt;element name="datumClanstva" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="registrovan" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="listaRezervacija">
 *           &lt;simpleType>
 *             &lt;list itemType="{http://www.w3.org/2001/XMLSchema}long" />
 *           &lt;/simpleType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idKorisnik",
    "email",
    "ime",
    "prezime",
    "lozinka",
    "datumClanstva",
    "registrovan",
    "listaRezervacija"
})
@XmlRootElement(name = "Korisnik")
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idKorisnik;
    @XmlElement(required = true)
    private String email;
    @XmlElement(required = true)
    private String ime;
    @XmlElement(required = true)
    private String prezime;
    @XmlElement(required = true)
    private String lozinka;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    private Date datumClanstva;
    @XmlElement(defaultValue = "false")
    private boolean registrovan;
    @XmlElement(defaultValue = "KORISNIK")
    private String rola;
    /*@XmlList
    @XmlElement(type = Long.class)
    private List<Long> listaRezervacija;*/

    /**
     * Gets the value of the idKorisnik property.
     * 
     */
    public long getIdKorisnik() {
        return idKorisnik;
    }

    /**
     * Sets the value of the idKorisnik property.
     * 
     */
    public void setIdKorisnik(long value) {
        this.idKorisnik = value;
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

    /**
     * Gets the value of the datumClanstva property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
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
     *     {@link Date }
     *     
     */
    public void setDatumClanstva(Date value) {
        this.datumClanstva = value;
    }

    /**
     * Gets the value of the registrovan property.
     * 
     */
    public boolean isRegistrovan() {
        return registrovan;
    }

    /**
     * Sets the value of the registrovan property.
     * 
     */
    public void setRegistrovan(boolean value) {
        this.registrovan = value;
    }

	public String getRola() {
		return rola;
	}

	public void setRola(String rola) {
		this.rola = rola;
	}

    /**
     * Gets the value of the listaRezervacija property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaRezervacija property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaRezervacija().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Long }
     * 
     * 
     */
    
    
    
    /*public List<Long> getListaRezervacija() {
        if (listaRezervacija == null) {
            listaRezervacija = new ArrayList<Long>();
        }
        return this.listaRezervacija;
    }*/

}
