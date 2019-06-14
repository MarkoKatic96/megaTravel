//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.12 at 12:36:07 PM CEST 
//


package com.megatravel.agentlocalbackend.wsdl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Poruka complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Poruka"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idPoruke" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="datumSlanja" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="posiljalac" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tipPosiljaoca" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="primalac" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="tipPrimaoca" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="sadrzaj" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Poruka", propOrder = {
    "idPoruke",
    "datumSlanja",
    "posiljalac",
    "tipPosiljaoca",
    "primalac",
    "tipPrimaoca",
    "sadrzaj",
    "status"
})
public class Poruka {

    protected long idPoruke;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected Date datumSlanja;
    protected long posiljalac;
    @XmlElement(required = true)
    protected String tipPosiljaoca;
    protected long primalac;
    @XmlElement(required = true)
    protected String tipPrimaoca;
    @XmlElement(required = true)
    protected String sadrzaj;
    @XmlElement(required = true)
    protected StatusPoruke status;

    /**
     * Gets the value of the idPoruke property.
     * 
     */
    public long getIdPoruke() {
        return idPoruke;
    }

    /**
     * Sets the value of the idPoruke property.
     * 
     */
    public void setIdPoruke(long value) {
        this.idPoruke = value;
    }

    /**
     * Gets the value of the datumSlanja property.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getDatumSlanja() {
        return datumSlanja;
    }

    /**
     * Sets the value of the datumSlanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setDatumSlanja(Date value) {
        this.datumSlanja = value;
    }

    /**
     * Gets the value of the posiljalac property.
     * 
     */
    public long getPosiljalac() {
        return posiljalac;
    }

    /**
     * Sets the value of the posiljalac property.
     * 
     */
    public void setPosiljalac(long value) {
        this.posiljalac = value;
    }

    /**
     * Gets the value of the tipPosiljaoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPosiljaoca() {
        return tipPosiljaoca;
    }

    /**
     * Sets the value of the tipPosiljaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPosiljaoca(String value) {
        this.tipPosiljaoca = value;
    }

    /**
     * Gets the value of the primalac property.
     * 
     */
    public long getPrimalac() {
        return primalac;
    }

    /**
     * Sets the value of the primalac property.
     * 
     */
    public void setPrimalac(long value) {
        this.primalac = value;
    }

    /**
     * Gets the value of the tipPrimaoca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipPrimaoca() {
        return tipPrimaoca;
    }

    /**
     * Sets the value of the tipPrimaoca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipPrimaoca(String value) {
        this.tipPrimaoca = value;
    }

    /**
     * Gets the value of the sadrzaj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSadrzaj() {
        return sadrzaj;
    }

    /**
     * Sets the value of the sadrzaj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSadrzaj(String value) {
        this.sadrzaj = value;
    }

    /**
     * Gets the value of the status property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public StatusPoruke getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatus(StatusPoruke value) {
        this.status = value;
    }

}