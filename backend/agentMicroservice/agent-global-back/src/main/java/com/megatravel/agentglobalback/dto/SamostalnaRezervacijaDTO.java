package com.megatravel.agentglobalback.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentglobalback.model.SamostalnaRezervacija;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "samostalnaRezervacijaDTO", propOrder = {
    "doDatuma",
    "odDatuma",
    "samostalnaRezervacijaId",
    "smestajId",
    "timestamp"
})
public class SamostalnaRezervacijaDTO {
	@XmlSchemaType(name = "dateTime")
    @XmlElement(namespace="https://megatravel.com/doDatuma")
    protected Date doDatuma;
    
    @XmlElement(namespace="https://megatravel.com/odDatuma")
    @XmlSchemaType(name = "dateTime")
    protected Date odDatuma;
    
    @XmlElement(namespace="https://megatravel.com/samostalnaRezervacijaId")
    protected Long samostalnaRezervacijaId;
    
    @XmlElement(namespace="https://megatravel.com/smestajId")
    protected Long smestajId;
    
    @XmlElement(namespace="https://megatravel.com/timestamp")
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;
	
    public SamostalnaRezervacijaDTO(Long samostalnaRezervacijaId, Long smestajId, Date odDatuma, Date doDatuma,
			Date timestamp) {
		super();
		this.samostalnaRezervacijaId = samostalnaRezervacijaId;
		this.smestajId = smestajId;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.timestamp = timestamp;
	}
    
    public SamostalnaRezervacijaDTO(SamostalnaRezervacija rez) {
		super();
		this.samostalnaRezervacijaId = rez.getSamostalnaRezervacijaId();
		this.smestajId = rez.getSmestajId();
		this.odDatuma = rez.getOdDatuma();
		this.doDatuma = rez.getDoDatuma();
		this.timestamp = rez.getTimestamp();
	}
    
    public SamostalnaRezervacijaDTO() {
	}

	public Long getSamostalnaRezervacijaId() {
		return samostalnaRezervacijaId;
	}

	public void setSamostalnaRezervacijaId(Long samostalnaRezervacijaId) {
		this.samostalnaRezervacijaId = samostalnaRezervacijaId;
	}

	public Long getSmestajId() {
		return smestajId;
	}

	public void setSmestajId(Long smestajId) {
		this.smestajId = smestajId;
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
