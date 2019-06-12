package com.megatravel.ratingservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.ratingservice.model.Komentar;
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

	public Optional<Komentar> findById(Long id) {
		return komentarRepository.findById(id);
	}
}
