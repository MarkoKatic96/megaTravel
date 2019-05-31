//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.05.16 at 08:55:09 PM CEST 
//


package io.webxml.pretragaservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TKoordinate", propOrder = {
    "latitude",
    "Longitude"
})
@Entity
public class TKoordinate {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long koordinateId;
	
	@OneToOne
	private Smestaj smestaj;
	
    protected int latitude;
    protected int Longitude;

    /**
     * Gets the value of the latitude property.
     * 
     */
    public int getLatitude() {
        return latitude;
    }

    /**
     * Sets the value of the latitude property.
     * 
     */
    public void setLatitude(int value) {
        this.latitude = value;
    }

    /**
     * Gets the value of the Longitude property.
     * 
     */
    public int getLongitude() {
        return Longitude;
    }

    /**
     * Sets the value of the Longitude property.
     * 
     */
    public void setLongitude(int value) {
        this.Longitude = value;
    }

	public Long getKoordinateId() {
		return koordinateId;
	}

	public void setKoordinateId(Long koordinateId) {
		this.koordinateId = koordinateId;
	}

}
