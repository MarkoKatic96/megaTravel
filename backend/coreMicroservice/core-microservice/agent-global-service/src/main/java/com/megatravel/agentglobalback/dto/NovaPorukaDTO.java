package com.megatravel.agentglobalback.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "novaPorukaDTO", propOrder = {
    "primalac",
    "sadrzaj"
})
public class NovaPorukaDTO {
	@XmlElement(namespace="https://megatravel.com/primalac")
    protected Long primalac;
	
	@XmlElement(namespace="https://megatravel.com/sadrzaj")
    protected String sadrzaj;
	
	public NovaPorukaDTO() {
	}
	
	public NovaPorukaDTO(Long primalac, String sadrzaj) {
		super();
		this.primalac = primalac;
		this.sadrzaj = sadrzaj;
	}

	public Long getPrimalac() {
		return primalac;
	}

	public void setPrimalac(Long primalac) {
		this.primalac = primalac;
	}

	public String getSadrzaj() {
		return sadrzaj;
	}

	public void setSadrzaj(String sadrzaj) {
		this.sadrzaj = sadrzaj;
	}

}
