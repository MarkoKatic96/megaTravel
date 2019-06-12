package com.megatravel.agentglobalback.dto;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentglobalback.model.Rezervacija;
import com.megatravel.agentglobalback.model.StatusRezervacije;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "lokalneRezervacijeDTO", propOrder = {
    "doDatuma",
    "globalniId",
    "korisnikId",
    "lokalniId",
    "odDatuma",
    "smestajId",
    "statusRezervacije",
    "timestamp",
    "updateTimestamp",
    "vlasnikId"
})
public class LokalneRezervacijeDTO {
	
	@XmlElement(namespace="https://megatravel.com/doDatuma")
    @XmlSchemaType(name = "dateTime")
    protected Date doDatuma;
	
	@XmlElement(namespace="https://megatravel.com/globalniId")
    protected Long globalniId;
	
	@XmlElement(namespace="https://megatravel.com/korisnikId")
    protected Long korisnikId;
	
	@XmlElement(namespace="https://megatravel.com/lokalniId")
    protected Long lokalniId;
	
	@XmlElement(namespace="https://megatravel.com/odDatuma")
    @XmlSchemaType(name = "dateTime")
    protected Date odDatuma;
	
	@XmlElement(namespace="https://megatravel.com/smestajId")
    protected Long smestajId;
	
	@XmlElement(namespace="https://megatravel.com/statusRezervacije")
	@XmlSchemaType(name = "string")
    protected StatusRezervacije statusRezervacije;
	
	@XmlElement(namespace="https://megatravel.com/timestamp")
    @XmlSchemaType(name = "dateTime")
    protected Date timestamp;
	
	@XmlElement(namespace="https://megatravel.com/updateTimestamp")
    @XmlSchemaType(name = "dateTime")
    protected Date updateTimestamp;
	
	@XmlElement(namespace="https://megatravel.com/vlasnikId")
    protected Long vlasnikId;
	
    public LokalneRezervacijeDTO(Long globalniId, Long lokalniId, Long smestajId, Long vlasnikId, Long korisnikId, Date odDatuma, Date doDatuma,
			StatusRezervacije statusRezervacije, Date timestamp, Date updateTimestamp) {
		super();
		this.globalniId = globalniId;
		this.lokalniId = lokalniId;
		this.smestajId = smestajId;
		this.korisnikId = korisnikId;
		this.vlasnikId = vlasnikId;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.statusRezervacije = statusRezervacije;
		this.timestamp = timestamp;
		this.updateTimestamp = updateTimestamp;
	}
    
    public LokalneRezervacijeDTO() {
	}

	public LokalneRezervacijeDTO(Rezervacija rez, Long lokalniId) {
		super();
		this.globalniId = rez.getRezervacijaId();
		this.lokalniId = lokalniId;
		this.smestajId = rez.getSmestajId();
		this.korisnikId = rez.getKorisnikId();
		this.vlasnikId = rez.getVlasnikId();
		this.odDatuma = rez.getOdDatuma();
		this.doDatuma = rez.getDoDatuma();
		this.statusRezervacije = rez.getStatusRezervacije();
		this.timestamp = rez.getTimestamp();
		this.updateTimestamp = rez.getUpdateTimestamp();
	}

	public Date getUpdateTimestamp() {
		return updateTimestamp;
	}

	public void setUpdateTimestamp(Date updateTimestamp) {
		this.updateTimestamp = updateTimestamp;
	}

	public Long getVlasnikId() {
		return vlasnikId;
	}

	public void setVlasnikId(Long vlasnikId) {
		this.vlasnikId = vlasnikId;
	}

	public Long getGlobalniId() {
		return globalniId;
	}

	public void setGlobalniId(Long globalniId) {
		this.globalniId = globalniId;
	}

	public Long getLokalniId() {
		return lokalniId;
	}

	public void setLokalniId(Long lokalniId) {
		this.lokalniId = lokalniId;
	}

	public Long getSmestajId() {
		return smestajId;
	}

	public void setSmestajId(Long smestajId) {
		this.smestajId = smestajId;
	}

	public Long getKorisnikId() {
		return korisnikId;
	}

	public void setKorisnikId(Long korisnikId) {
		this.korisnikId = korisnikId;
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

	public StatusRezervacije getStatusRezervacije() {
		return statusRezervacije;
	}

	public void setStatusRezervacije(StatusRezervacije statusRezervacije) {
		this.statusRezervacije = statusRezervacije;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
}
