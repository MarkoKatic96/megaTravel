package com.megatravel.ratingservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.model.StatusKomentara;
import com.megatravel.ratingservice.repository.KomentarRepository;

@Component
public class KomentarService {

	@Autowired
	private KomentarRepository komentarRepository;
	
	public Komentar save(Komentar komentar) {
		return komentarRepository.save(komentar);
	}
	
	public void remove(Long komentarId) {
		komentarRepository.deleteById(komentarId);
	}
	
	public Page<Komentar> findAllNeobjavljenji(Pageable page) {
		return komentarRepository.findAllNeobjavljenji(page);
	}
	
	public Page<Komentar> findAllObjavljenjiForSmestaj(Long idSmestaja, Pageable page) {
		return komentarRepository.findAllObjavljenjiForSmestaj(idSmestaja, page);
	}
	
	public List<Komentar> findAllObjavljenji() {
		List<Komentar> lista = komentarRepository.findAll();
		List<Komentar> returnList = new ArrayList<Komentar>();
		for (Komentar komentar : lista) {
			if(komentar.getStatus()==StatusKomentara.OBJAVLJEN) {
				returnList.add(komentar);
			}
		}
		return returnList;
	}

	public Optional<Komentar> findById(Long id) {
		return komentarRepository.findById(id);
	}
}
