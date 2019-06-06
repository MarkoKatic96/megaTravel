package com.megatravel.agentlocalbackend.controller;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.TrustStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.megatravel.agentlocalbackend.configuration.RestTemplateConfiguration;
import com.megatravel.agentlocalbackend.dto.AgentDTO;
import com.megatravel.agentlocalbackend.dto.AgentPrijavaDTO;
import com.megatravel.agentlocalbackend.dto.AgentRegistracijaDTO;
import com.megatravel.agentlocalbackend.jwt.JwtTokenUtils;
import com.megatravel.agentlocalbackend.model.Agent;
import com.megatravel.agentlocalbackend.model.NeaktiviranAgent;
import com.megatravel.agentlocalbackend.repository.RevokedTokensRepository;
import com.megatravel.agentlocalbackend.service.AgentService;
import com.megatravel.agentlocalbackend.service.NeaktiviranAgentService;

@RestController
@RequestMapping("/agent")
public class AgentController {
	
	@Autowired
	RestTemplateConfiguration config;
	
	@Autowired
	NeaktiviranAgentService neaktiviranAgentService;

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
/*
	@RequestMapping(value = "/e/{email}", method = RequestMethod.GET)
	public ResponseEntity<Agent> getAgentByEmail(@PathVariable String email) {
		System.out.println("getAgentByEmail(" + email + ")");
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(agent, HttpStatus.OK);
	}
*/	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody AgentPrijavaDTO agentPrijavaDTO) {
		System.out.println("login(" + agentPrijavaDTO.getEmail() + "," + agentPrijavaDTO.getLozinka() + ")");
		 
		RestTemplate restTemplate = config.createRestTemplate();
		
		String loginUrl = "https://localhost:8400/agent/login"; 
		
		HttpEntity<AgentPrijavaDTO> request = new HttpEntity<>(agentPrijavaDTO);
		String token = restTemplate.postForObject(loginUrl, request, String.class);
		
		System.out.println("tokencina: " + token);
		
		if(token != null) {
			String getAgentUrl = "https://localhost:8400/agent/e/" + agentPrijavaDTO.getEmail();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", "Bearer "+token);
			HttpEntity<String> entity = new HttpEntity<String>(null,headers);
			ResponseEntity<Agent> agent = restTemplate.exchange(getAgentUrl, HttpMethod.GET, entity, Agent.class);
			
			if(!agent.hasBody()) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			agentService.save(agent.getBody());
			
			ObjectMapper mapper = new ObjectMapper();
			try {
				return new ResponseEntity<>(mapper.writeValueAsString(token), HttpStatus.OK);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
			
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<NeaktiviranAgent> signup(@RequestBody AgentRegistracijaDTO agentRegistracijaDTO) {
		System.out.println("signup()");

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
		
		NeaktiviranAgent tempKorisnik2 = neaktiviranAgentService.findByEmail(agentRegistracijaDTO.getEmail());
		if(tempKorisnik2 != null) {
			//mora biti jedinstveni mail za korisnika
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		tempKorisnik2 = neaktiviranAgentService.findByPMB(agentRegistracijaDTO.getPoslovniMaticniBroj());
		if(tempKorisnik2 != null) {
			//mora biti jedinstveni PIM za korisnika
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		NeaktiviranAgent agent = new NeaktiviranAgent(null, agentRegistracijaDTO.getIme(), agentRegistracijaDTO.getPrezime(), agentRegistracijaDTO.getPoslovniMaticniBroj(), agentRegistracijaDTO.getEmail());
		NeaktiviranAgent retValue = neaktiviranAgentService.save(agent);

		return new ResponseEntity<>(retValue, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ResponseEntity<Void> signout(HttpServletRequest request) {
		System.out.println("signout()");
		
		String signOutUrl = "https://localhost:8400/agent/signout";
		
		RestTemplate restTemplate = config.createRestTemplate();
	    try {
	    	String body = IOUtils.toString(request.getInputStream(), Charset.forName(request.getCharacterEncoding()));
	        ResponseEntity<Void> exchange = restTemplate.exchange(signOutUrl,
	                HttpMethod.valueOf(request.getMethod()),
	                new HttpEntity<>(body),
	                Void.class,
	                request.getParameterMap());
	        
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
		System.out.println("validateToken()");
	
		return new ResponseEntity<>(new Boolean(true), HttpStatus.OK);
	}
	
}
