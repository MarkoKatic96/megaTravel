//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.22 at 11:00:08 PM CEST 
//


package io.xws.adminservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="rezervacijaId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="smestajId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *               &lt;pattern value="[\-+]?[0-9]+"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="korisnikId">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}long">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="od" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="do" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="potvrdjenaRezervacija">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}boolean">
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "rezervacijaId",
    "smestajId",
    "korisnikId",
    "od",
    "_do",
    "potvrdjenaRezervacija",
    "timestamp"
})
@XmlRootElement(name = "Rezervacija")
public class Rezervacija {

    protected long rezervacijaId;
    protected long smestajId;
    protected long korisnikId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date od;
    @XmlElement(name = "do", required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date _do;
    @XmlElement(defaultValue = "false")
    protected boolean potvrdjenaRezervacija;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;

    /**
     * Gets the value of the rezervacijaId property.
     * 
     */
    public long getRezervacijaId() {
        return rezervacijaId;
    }

    /**
     * Sets the value of the rezervacijaId property.
     * 
     */
    public void setRezervacijaId(long value) {
        this.rezervacijaId = value;
    }

    /**
     * Gets the value of the smestajId property.
     * 
     */
    public long getSmestajId() {
        return smestajId;
    }

    /**
     * Sets the value of the smestajId property.
     * 
     */
    public void setSmestajId(long value) {
        this.smestajId = value;
    }

    /**
     * Gets the value of the korisnikId property.
     * 
     */
    public long getKorisnikId() {
        return korisnikId;
    }

    /**
     * Sets the value of the korisnikId property.
     * 
     */
    public void setKorisnikId(long value) {
        this.korisnikId = value;
    }

    /**
     * Gets the value of the od property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getOd() {
        return od;
    }

    /**
     * Sets the value of the od property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setOd(Date value) {
        this.od = value;
    }

    /**
     * Gets the value of the do property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDo() {
        return _do;
    }

    /**
     * Sets the value of the do property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDo(Date value) {
        this._do = value;
    }

    /**
     * Gets the value of the potvrdjenaRezervacija property.
     * 
     */
    public boolean isPotvrdjenaRezervacija() {
        return potvrdjenaRezervacija;
    }

    /**
     * Sets the value of the potvrdjenaRezervacija property.
     * 
     */
    public void setPotvrdjenaRezervacija(boolean value) {
        this.potvrdjenaRezervacija = value;
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
