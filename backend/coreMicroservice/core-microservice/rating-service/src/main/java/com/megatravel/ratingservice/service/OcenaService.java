package com.megatravel.ratingservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.ratingservice.model.Ocena;
import com.megatravel.ratingservice.repository.OcenaRepository;

@Component
public class OcenaService {

	@Autowired
	private OcenaRepository ocenaRepository;
	
	public Ocena save(Ocena ocena) {
		return ocenaRepository.save(ocena);
	}
	
	public Page<Ocena> findAllForSmestaj(Long idSmestaja,Pageable page) {
		return ocenaRepository.findAllForSmestaj(idSmestaja, page);
	}
	
	public float getAverageOcenaForSmestaj(Long idSmestaja) {
		return ocenaRepository.getAvgForSmestaj(idSmestaja);
	}
	
	public boolean remove(Ocena ocena) {
		Optional<Ocena> trazena = ocenaRepository.findById(ocena.getIdOcena());
		if (trazena.isPresent()) {
			ocenaRepository.delete(ocena);
			return true;
		}
		
		return false;
	}
	
	public Ocena findOne(Long id) {
		try{
			return ocenaRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}
}
