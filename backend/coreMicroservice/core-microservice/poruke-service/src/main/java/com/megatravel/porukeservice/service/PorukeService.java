package com.megatravel.porukeservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.repository.PorukaRepository;

@Component
public class PorukeService {

	@Autowired
	private PorukaRepository porukaRepository;
	
	/****************** AGENTSKE METODE ***********************/
	public Page<Poruka> findAllWithUser(Long userId, Long agentId, Pageable page) {
		return porukaRepository.findAllWithUser(userId, agentId, page);
	}
	
	public Page<Poruka> findAllNeprocitaneZaAgenta(Long agentId, Pageable page) {
		return porukaRepository.findAllNeprocitaneZaAgenta(agentId, page);
	}

	public List<Poruka> findAllNeprocitaneWithUser(Long userId, Long agentId) {
		return porukaRepository.findAllNeprocitaneWithUser(userId, agentId);
	}
	
	/****************** KORISNICKE METODE ***********************/
	public Page<Poruka> findAllWithAgent(Long agentId, Long userId, Pageable page) {
		return porukaRepository.findAllWithAgent(agentId, userId, page);
	}
	
	public Page<Poruka> findAllNeprocitaneZaKorisnika(Long userId, Pageable page) {
		return porukaRepository.findAllNeprocitaneZaKorisnika(userId, page);
	}

	public List<Poruka> findAllNeprocitaneWithAgent(Long agentId, Long userId) {
		return porukaRepository.findAllNeprocitaneWithAgent(agentId, userId);
	}
	
	/****************** ZAJEDNICKE METODE ***********************/
	public Poruka save(Poruka p) {
		return porukaRepository.save(p);
	}

}
