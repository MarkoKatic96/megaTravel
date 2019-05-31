package com.megatravel.agentglobalback.dto;

import java.util.Date;

import com.megatravel.agentglobalback.model.Rezervacija;
import com.megatravel.agentglobalback.model.StatusRezervacije;

public class RezervacijaDTO {
	
	private Long rezervacijaId;
	private Long smestajId;
	private Long korisnikId;
	private Long vlasnikId;
    private Date odDatuma;
    private Date doDatuma;
    private StatusRezervacije statusRezervacije;
    private Date timestamp;
    private Date updateTimestamp;
	
    public RezervacijaDTO(Long rezervacijaId, Long smestajId, Long vlasnikId, Long korisnikId, Date odDatuma, Date doDatuma,
			StatusRezervacije statusRezervacije, Date timestamp, Date updateTimestamp) {
		super();
		this.rezervacijaId = rezervacijaId;
		this.smestajId = smestajId;
		this.korisnikId = korisnikId;
		this.vlasnikId = vlasnikId;
		this.odDatuma = odDatuma;
		this.doDatuma = doDatuma;
		this.statusRezervacije = statusRezervacije;
		this.timestamp = timestamp;
		this.updateTimestamp = updateTimestamp;
	}
    
    public RezervacijaDTO() {
	}

	public RezervacijaDTO(Rezervacija rez) {
		super();
		this.rezervacijaId = rez.getRezervacijaId();
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

	public Long getRezervacijaId() {
		return rezervacijaId;
	}

	public void setRezervacijaId(Long rezervacijaId) {
		this.rezervacijaId = rezervacijaId;
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
