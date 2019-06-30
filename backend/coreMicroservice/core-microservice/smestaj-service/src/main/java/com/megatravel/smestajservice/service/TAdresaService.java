package com.megatravel.smestajservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.smestajservice.model.TAdresa;
import com.megatravel.smestajservice.repository.AdresaRepository;

@Service
public class TAdresaService {
	
	@Autowired
	AdresaRepository tAdresaRepository;
	
	public TAdresa save(TAdresa ta) {
		return tAdresaRepository.save(ta);
	}
}
