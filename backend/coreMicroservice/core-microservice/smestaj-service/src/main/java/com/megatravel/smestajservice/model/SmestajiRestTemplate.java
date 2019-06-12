package com.megatravel.smestajservice.model;

import java.util.List;

import com.megatravel.smestajservice.dto.SmestajKorisnikDTO;


public class SmestajiRestTemplate {
	
	private List<SmestajKorisnikDTO> smestajiList;

	public SmestajiRestTemplate() {

	}

	public List<SmestajKorisnikDTO> getSmestajiList() {
		return smestajiList;
	}

	public void setSmestajiList(List<SmestajKorisnikDTO> smestajiList) {
		this.smestajiList = smestajiList;
	}
	
}
