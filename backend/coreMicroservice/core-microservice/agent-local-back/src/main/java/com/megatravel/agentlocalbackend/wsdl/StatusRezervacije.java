//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.12 at 12:36:07 PM CEST 
//


package com.megatravel.agentlocalbackend.wsdl;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for statusRezervacije.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="statusRezervacije"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="KREIRANA"/&gt;
 *     &lt;enumeration value="OTKAZANA"/&gt;
 *     &lt;enumeration value="U_TOKU"/&gt;
 *     &lt;enumeration value="ISTEKLA"/&gt;
 *     &lt;enumeration value="POTVRDJENA"/&gt;
 *     &lt;enumeration value="NEIZVRSENA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "statusRezervacije")
@XmlEnum
public enum StatusRezervacije {

    KREIRANA,
    OTKAZANA,
    U_TOKU,
    ISTEKLA,
    POTVRDJENA,
    NEIZVRSENA;

    public String value() {
        return name();
    }

    public static StatusRezervacije fromValue(String v) {
        return valueOf(v);
    }

}
