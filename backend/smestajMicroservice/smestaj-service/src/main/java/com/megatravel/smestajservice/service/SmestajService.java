package com.megatravel.smestajservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.repository.SmestajRepository;

@Component
public class SmestajService {

	@Autowired
	private SmestajRepository smestajRepository;
	
	public Smestaj findOne(Long idSmestaja, Long idVlasnika) {
		Smestaj s = smestajRepository.getOne(idSmestaja);
		if (s!=null) {
			if (s.getVlasnik()==idVlasnika) {
				return s;
			}
		}
		return null;
	}
	
	public Page<Smestaj> getAll(Long idVlasnika,Pageable page) {
		return smestajRepository.findAllFromMe(idVlasnika, page);
	}
	
	public Smestaj save(Smestaj smestaj) {
		return smestajRepository.save(smestaj);
	}
	
	public void remove(Long id) {
		smestajRepository.deleteById(id);
	}
}
