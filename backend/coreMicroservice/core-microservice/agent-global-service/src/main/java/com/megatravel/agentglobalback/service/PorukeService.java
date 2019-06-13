package com.megatravel.agentglobalback.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.agentglobalback.model.Poruka;
import com.megatravel.agentglobalback.repository.PorukaRepository;

@Component
public class PorukeService {

	@Autowired
	private PorukaRepository porukaRepository;
	
	public Page<Poruka> findAllWithUser(Long userId, Long agentId, Pageable page) {
		return porukaRepository.findAllWithUser(userId, agentId, page);
	}
	
	public Page<Poruka> findAllNeprocitane(Long agentId, Pageable page) {
		return porukaRepository.findAllNeprocitane(agentId, page);
	}

	public List<Poruka> findAllNeprocitaneWithUser(Long userId, Long agentId) {
		return porukaRepository.findAllNeprocitaneWithUser(userId, agentId);
	}

	public Poruka save(Poruka p) {
		return porukaRepository.save(p);
	}

}
