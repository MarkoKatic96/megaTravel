package com.megatravel.smestajservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.smestajservice.model.TAdresa;
import com.megatravel.smestajservice.model.TKoordinate;
import com.megatravel.smestajservice.repository.TKoordinateRepository;

@Service
public class TKoordinateService {
	@Autowired
	TKoordinateRepository tKorRepository;
	
	public TKoordinate save(TKoordinate ta) {
		return tKorRepository.save(ta);
	}
}
