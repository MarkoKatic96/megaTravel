package com.megatravel.agentlocalbackend.model;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;

public class Poruka {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPoruke;
	
	@NotNull
	@CreationTimestamp
	private Date datumSlanja;
	
	@NotNull
	private Long posiljalac;
	
	private String tipPosiljaoca;
	
	@NotNull
	private Long primalac;
	
	private String tipPrimaoca;
	
	private String sadrzaj;
	
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
