package com.megatravel.agentlocalbackend.controller;

import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ResponseEntity<AgentDTO> getAgent() {
		System.out.println("getAgent()");
		
		//uzima samo tog jednog koji se ulogovao jer je jedini u bazu
		Agent agent = agentService.findOne();
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
		
		String loginUrl = "https://agent-global-service/agent/login"; 
		
		HttpEntity<AgentPrijavaDTO> request = new HttpEntity<>(agentPrijavaDTO);
		String token = restTemplate.postForObject(loginUrl, request, String.class);
		
		System.out.println("tokencina: " + token);
		
		if(token != null) {
			String getAgentUrl = "https://agent-global-service/agent/e/" + agentPrijavaDTO.getEmail();
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
	public ResponseEntity<Void> signup(@RequestBody AgentRegistracijaDTO agentRegistracijaDTO) {
		System.out.println("signup()");

		RestTemplate restTemplate = config.createRestTemplate();
		
		String registerUrl = "https://agent-global-service/agent/register"; 
		
		HttpEntity<AgentRegistracijaDTO> body = new HttpEntity<>(agentRegistracijaDTO);

		ResponseEntity<NeaktiviranAgent> exchange = restTemplate.exchange(registerUrl,
                HttpMethod.POST,
                new HttpEntity<>(body),
                NeaktiviranAgent.class);
		
		return new ResponseEntity<>(exchange.getStatusCode());
		
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ResponseEntity<Void> signout(HttpServletRequest request) {
		System.out.println("signout()");
		
		String signOutUrl = "https://agent-global-service/agent/signout";
		
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
