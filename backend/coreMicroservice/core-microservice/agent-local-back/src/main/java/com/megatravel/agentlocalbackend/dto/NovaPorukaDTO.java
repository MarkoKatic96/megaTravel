package com.megatravel.agentlocalbackend.dto;

public class NovaPorukaDTO {
	
	private Long primalac;
	private String sadrzaj;
	
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
