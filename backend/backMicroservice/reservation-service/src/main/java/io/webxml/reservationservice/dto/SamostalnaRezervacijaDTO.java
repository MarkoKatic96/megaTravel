package io.webxml.reservationservice.dto;

import java.util.Date;

import io.webxml.reservationservice.model.SamostalnaRezervacija;

public class SamostalnaRezervacijaDTO {
	
	private Long samostalnaRezervacijaId;
    private Long smestajId;
    private Date odDatuma;
    private Date doDatuma;
    private Date timestamp;
	
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
