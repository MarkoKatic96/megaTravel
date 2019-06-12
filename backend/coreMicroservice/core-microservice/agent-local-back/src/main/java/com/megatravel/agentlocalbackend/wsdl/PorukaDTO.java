package com.megatravel.agentlocalbackend.wsdl;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "porukaDTO", propOrder = {
    "datumSlanja",
    "idPoruke",
    "posiljalac",
    "primalac",
    "sadrzaj",
    "status",
    "tipPosiljaoca",
    "tipPrimaoca"
})
public class PorukaDTO {

    @XmlSchemaType(name = "dateTime")
    @XmlElement(namespace="https://megatravel.com/datumSlanja")
    protected Date datumSlanja;
    
    @XmlElement(namespace="https://megatravel.com/idPoruke")
    protected Long idPoruke;
    
    @XmlElement(namespace="https://megatravel.com/posiljalac")
    protected Long posiljalac;
    
    @XmlElement(namespace="https://megatravel.com/primalac")
    protected Long primalac;
    
    @XmlElement(namespace="https://megatravel.com/sadrzaj")
    protected String sadrzaj;
    
    @XmlElement(namespace="https://megatravel.com/status")
    @XmlSchemaType(name = "string")
    protected StatusPoruke status;

    @XmlElement(namespace="https://megatravel.com/tipPosiljaoca")
    protected String tipPosiljaoca;
    
    @XmlElement(namespace="https://megatravel.com/tipPrimaoca")
    protected String tipPrimaoca;
	
	public PorukaDTO() {
	}
	
	public PorukaDTO(Long idPoruke, Date datumSlanja, Long posiljalac, String tipPosiljaoca, Long primalac,
			String tipPrimaoca, String sadrzaj, StatusPoruke status) {
		super();
		this.idPoruke = idPoruke;
		this.datumSlanja = datumSlanja;
		this.posiljalac = posiljalac;
		this.tipPosiljaoca = tipPosiljaoca;
		this.primalac = primalac;
		this.tipPrimaoca = tipPrimaoca;
		this.sadrzaj = sadrzaj;
		this.status = status;
	}
	
	public PorukaDTO(Poruka p) {
		super();
		this.idPoruke = p.getIdPoruke();
		this.datumSlanja = p.getDatumSlanja();
		this.posiljalac = p.getPosiljalac();
		this.tipPosiljaoca = p.getTipPosiljaoca();
		this.primalac = p.getPosiljalac();
		this.tipPrimaoca = p.getTipPosiljaoca();
		this.sadrzaj = p.getSadrzaj();
		this.status = p.getStatus();
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

	public void setTipPosiljaoca(String tipPosiljaoca) {
		this.tipPosiljaoca = tipPosiljaoca;
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

	public void setTipPrimaoca(String tipPrimaoca) {
		this.tipPrimaoca = tipPrimaoca;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

	public StatusPoruke getStatus() {
		return status;
	}

	public void setStatus(StatusPoruke status) {
		this.status = status;
	}
}
