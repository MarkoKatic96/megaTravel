package com.megatravel.porukeservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.porukeservice.model.Agent;
import com.megatravel.porukeservice.repository.AgentRepository;

@Component
public class AgentService {
	
	@Autowired
	private AgentRepository agentRepository;
	
	//@Autowired
	//private JwtTokenUtils jwtTokenProvider;

	//@Autowired
	//private AuthenticationManager authenticationManager;
	
	//@Autowired
	//private PasswordEncoder passwordEncoder;
	

	public String signin(String email, String lozinka) {
		/*try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, lozinka));			
			return jwtTokenProvider.createToken(email);
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}*/return null;
	}

	public Agent signup(Agent agent) {
		/*if (!agentRepository.existsByEmail(agent.getEmail())) {
			if (!agentRepository.existsByPoslovniMaticniBroj(agent.getPoslovniMaticniBroj())) {
				agent.setLozinka(passwordEncoder.encode(agent.getLozinka()));
				return agentRepository.save(agent);
			} else {
				throw new CustomException("PMB is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
			}			
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}*/return null;
	}
	
	public Agent findByEmail(String email) {
		return agentRepository.findByEmail(email);
	}
	
	public Agent findByPMB(Long poslovniMaticniBroj) {
		return agentRepository.findByPoslovniMaticniBroj(poslovniMaticniBroj);
	}

	public Agent findOne(Long id) {
		try{
			return agentRepository.findById(id).get();
		} catch (Exception e) {
			return null;
		}
	}

	public Agent save(Agent agent) {
		return agentRepository.save(agent);
	}
	
	public void deleteAll() {
		agentRepository.deleteAll();
	}
	
}
