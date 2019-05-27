package com.megatravel.agentglobalback.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.agentglobalback.dto.AgentDTO;
import com.megatravel.agentglobalback.dto.AgentPrijavaDTO;
import com.megatravel.agentglobalback.dto.AgentRegistracijaDTO;
import com.megatravel.agentglobalback.jwt.JwtTokenUtils;
import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.RevokedTokens;
import com.megatravel.agentglobalback.repository.RevokedTokensRepository;
import com.megatravel.agentglobalback.service.AgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	private RevokedTokensRepository revokedTokensRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
		System.out.println("getAgent(" + id + ")");
		
		Agent agent = agentService.findOne(id);
		if (agent == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new AgentDTO(agent), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody AgentPrijavaDTO agentPrijavaDTO) {
		System.out.println("login(" + agentPrijavaDTO.getEmail() + "," + agentPrijavaDTO.getLozinka() + ")");
		
		Agent agent = agentService.findByEmail(agentPrijavaDTO.getEmail());
		if(agent == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		agentService.save(agent);
		
		try {
			String jwt = agentService.signin(agentPrijavaDTO.getEmail(), agentPrijavaDTO.getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public ResponseEntity<AgentDTO> signup(@RequestBody AgentRegistracijaDTO agentRegistracijaDTO) {
		System.out.println("signup()");
		
		if (!agentRegistracijaDTO.getPotvrdaLozinke().equals(agentRegistracijaDTO.getLozinka())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Agent tempKorisnik = agentService.findByEmail(agentRegistracijaDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		tempKorisnik = agentService.findByPMB(agentRegistracijaDTO.getPoslovniMaticniBroj());
		if(tempKorisnik != null) {
			//mora biti jedinstveni PIM za korisnika
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		Agent agent = new Agent(null, agentRegistracijaDTO.getIme(), agentRegistracijaDTO.getPrezime(), agentRegistracijaDTO.getPoslovniMaticniBroj(), agentRegistracijaDTO.getEmail(), agentRegistracijaDTO.getLozinka());
		Agent retValue = agentService.signup(agent);
		/*
		try {
			emailService.sendNotificaitionAsync(retValue);
		} catch (MailException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		return new ResponseEntity<>(new AgentDTO(retValue), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ResponseEntity<Void> signout(HttpServletRequest req) {
		System.out.println("signout()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		revokedTokensRepository.save(new RevokedTokens(null, token));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
}
