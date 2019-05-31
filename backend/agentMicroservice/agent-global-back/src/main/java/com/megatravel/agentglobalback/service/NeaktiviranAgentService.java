package com.megatravel.agentglobalback.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.agentglobalback.model.NeaktiviranAgent;
import com.megatravel.agentglobalback.repository.NeaktiviranAgentRepository;

@Component
public class NeaktiviranAgentService {
	
	@Autowired
	private NeaktiviranAgentRepository agentRepository;
	
	public NeaktiviranAgent findByEmail(String email) {
		return agentRepository.findByEmail(email);
	}
	
	public NeaktiviranAgent findByPMB(Long poslovniMaticniBroj) {
		return agentRepository.findByPoslovniMaticniBroj(poslovniMaticniBroj);
	}

	public NeaktiviranAgent findOne(Long id) {
		try{
			return agentRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	public NeaktiviranAgent save(NeaktiviranAgent agent) {
		return agentRepository.save(agent);
	}
	
	public void delete(NeaktiviranAgent agent) {
		agentRepository.delete(agent);
	}
	
}
