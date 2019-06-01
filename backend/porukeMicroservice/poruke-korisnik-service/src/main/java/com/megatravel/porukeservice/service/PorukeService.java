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
	
	public Page<Poruka> findAllWithAgent(Long agentId, Long userId, Pageable page) {
		return porukaRepository.findAllWithAgent(agentId, userId, page);
	}
	
	public Page<Poruka> findAllNeprocitane(Long userId, Pageable page) {
		return porukaRepository.findAllNeprocitane(userId, page);
	}

	public List<Poruka> findAllNeprocitaneWithAgent(Long agentId, Long userId) {
		return porukaRepository.findAllNeprocitaneWithAgent(agentId, userId);
	}

	public Poruka save(Poruka p) {
		return porukaRepository.save(p);
	}

}
