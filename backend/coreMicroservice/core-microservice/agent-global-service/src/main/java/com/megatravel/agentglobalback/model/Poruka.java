package com.megatravel.agentglobalback.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.hibernate.annotations.CreationTimestamp;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Poruka")
@XmlRootElement(name = "Poruka")
@Entity
public class Poruka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@XmlElement(required = true)
	private Long idPoruke;
	
	@NotNull
	@CreationTimestamp
	@XmlElement(required = true)
	private Date datumSlanja;
	
	@NotNull
	@XmlElement(required = true)
	private Long posiljalac;
	
	@XmlElement(required = true)
	private String tipPosiljaoca;
	
	@NotNull
	@XmlElement(required = true)
	private Long primalac;
	
	@XmlElement(required = true)
	private String tipPrimaoca;
	
	@XmlElement(required = true)
	private String sadrzaj;
	
	@XmlElement(required = true)
	private String status;

	public Poruka() {
	
	}

	public Poruka(Long idPoruke, @NotNull Long posiljalac, TipOsobe tipPosiljaoca,
			@NotNull Long primalac, TipOsobe tipPrimaoca, String sadrzaj, StatusPoruke status) {
		super();
		this.idPoruke = idPoruke;
		this.datumSlanja = new Date(System.currentTimeMillis());
		this.posiljalac = posiljalac;
		this.tipPosiljaoca = tipPosiljaoca.toString();
		this.primalac = primalac;
		this.tipPrimaoca = tipPrimaoca.toString();
		this.sadrzaj = sadrzaj;
		this.status = status.toString();
	}

	public Long getIdPoruke() {
		return idPoruke;
	}

	public void setIdPoruke(Long idPoruke) {
		this.idPoruke = idPoruke;
	}

	public Date getDatumSlanja() {
		return datumSlanja;
	}

	public void setDatumSlanja(Date datumSlanja) {
		this.datumSlanja = datumSlanja;
	}

	public Long getPosiljalac() {
		return posiljalac;
	}

	public void setPosiljalac(Long posiljalac) {
		this.posiljalac = posiljalac;
	}

	public String getTipPosiljaoca() {
		return tipPosiljaoca;
	}

	public void setTipPosiljaoca(TipOsobe tipPosiljaoca) {
		this.tipPosiljaoca = tipPosiljaoca.toString();
	}

	public Long getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Long primalac) {
		this.primalac = primalac;
	}

	public String getTipPrimaoca() {
		return tipPrimaoca;
	}

	public void setTipPrimaoca(TipOsobe tipPrimaoca) {
		this.tipPrimaoca = tipPrimaoca.toString();
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public StatusPoruke getStatus() {
		return StatusPoruke.valueOf(status);
	}

	public void setStatus(StatusPoruke status) {
		this.status = status.toString();
	}
}
