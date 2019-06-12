package com.megatravel.porukeservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.porukeservice.dto.NovaPorukaDTO;
import com.megatravel.porukeservice.dto.PorukaDTO;
import com.megatravel.porukeservice.jwt.JwtTokenUtils;
import com.megatravel.porukeservice.model.Agent;
import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.model.StatusPoruke;
import com.megatravel.porukeservice.model.TipOsobe;
import com.megatravel.porukeservice.service.PorukeService;

@RestController
@RequestMapping("/poruke-agent-service/poruke")
public class PorukeController {
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	PorukeService porukeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getPorukeWithKorisnik(@PathVariable Long userId, Pageable page, HttpServletRequest req) {
		System.out.println("getPorukeWithKorisnik()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Page<Poruka> poruke = porukeService.findAllWithUser(userId, agent.getIdAgenta(), page);
		
		HttpHeaders headers = new HttpHeaders();
		long porukeTotal = poruke.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(porukeTotal));

		List<PorukaDTO> retVal = new ArrayList<>();
		
		for (Poruka p : poruke) {
			retVal.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/neprocitane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getNeprocitanePoruke(Pageable page, HttpServletRequest req) {
		System.out.println("getNeprocitanePoruke()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Page<Poruka> poruke = porukeService.findAllNeprocitane(agent.getIdAgenta(), page);
		
		HttpHeaders headers = new HttpHeaders();
		long porukeTotal = poruke.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(porukeTotal));

		List<PorukaDTO> retVal = new ArrayList<>();
		
		for (Poruka p : poruke) {
			retVal.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setProcitanePorukeFromUser(@PathVariable Long userId, HttpServletRequest req) {
		System.out.println("setProcitanePorukeFromUser()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Poruka> poruke = porukeService.findAllNeprocitaneWithUser(userId, agent.getIdAgenta());
		
		for (Poruka p : poruke) {
			p.setStatus(StatusPoruke.PROCITANA);
			porukeService.save(p);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorukaDTO> sendPoruka(@RequestBody NovaPorukaDTO novaPoruka, HttpServletRequest req) {
		System.out.println("sendPoruka()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Poruka poruka = new Poruka(null, agent.getIdAgenta(), TipOsobe.AGENT, novaPoruka.getPrimalac(), TipOsobe.KORISNIK, novaPoruka.getSadrzaj(), StatusPoruke.POSLATA);
		Poruka retVal = porukeService.save(poruka);
		
		return new ResponseEntity<>(new PorukaDTO(retVal), HttpStatus.CREATED);
	}
	
	
	

	
	
}
