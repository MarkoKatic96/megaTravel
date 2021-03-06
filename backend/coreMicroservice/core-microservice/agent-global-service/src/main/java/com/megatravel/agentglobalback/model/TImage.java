//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package com.megatravel.agentglobalback.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TImage")
@Entity
public class TImage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idImage;
	
	@ManyToOne
	@NotNull
    private Smestaj smestaj;
    
	@XmlElement(required = true)
	@NotNull
    private String name;
    
    @XmlElement(required = true)
    @NotNull
    @Lob
    private byte[] bytes;

    /**
     * Gets the value of the idImage property.
     * 
     */
    public Long getIdImage() {
        return idImage;
    }

    /**
     * Sets the value of the idImage property.
     * 
     */
    public void setIdImage(Long value) {
        this.idImage = value;
    }

    /**
     * Gets the value of the idSmestaja property.
     * 
     */
    public Smestaj getIdSmestaja() {
        return smestaj;
    }

    /**
     * Sets the value of the idSmestaja property.
     * 
     */
    public void setSmestaj(Smestaj value) {
        this.smestaj = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the bytes property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getBytes() {
        return bytes;
    }

    /**
     * Sets the value of the bytes property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setBytes(byte[] value) {
        this.bytes = value;
    }

}
