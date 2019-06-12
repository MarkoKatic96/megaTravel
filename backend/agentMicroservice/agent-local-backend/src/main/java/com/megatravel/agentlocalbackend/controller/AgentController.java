package com.megatravel.agentlocalbackend.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentlocalbackend.configuration.RestTemplateConfiguration;
import com.megatravel.agentlocalbackend.service.AgentService;
import com.megatravel.agentlocalbackend.service.RezervacijaService;
import com.megatravel.agentlocalbackend.soap.AgentClient;
import com.megatravel.agentlocalbackend.wsdl.AgentDTO;
import com.megatravel.agentlocalbackend.wsdl.AgentPrijavaDTO;
import com.megatravel.agentlocalbackend.wsdl.AgentRegistracijaDTO;
import com.megatravel.agentlocalbackend.wsdl.GetAgentByEmailResponse;
import com.megatravel.agentlocalbackend.wsdl.GetAgentResponse;
import com.megatravel.agentlocalbackend.wsdl.LoginResponse;
import com.megatravel.agentlocalbackend.wsdl.SignUpResponse;
import com.megatravel.agentlocalbackend.wsdl.SignUpResponse.NeaktiviranAgent;


@RestController
@RequestMapping("/agent-local-service/agent")
public class AgentController {
	
	@Autowired
	RestTemplateConfiguration config;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	AgentClient agentClient;
	
	//@Autowired
	//JwtTokenUtils jwtTokenUtils;
	
	//@Autowired
	//private RevokedTokensRepository revokedTokensRepository;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<AgentDTO> getAgent(@PathVariable Long id) {
		System.out.println("getAgent(" + id + ")");
		
		com.megatravel.agentlocalbackend.model.Agent agent = agentService.findOne(id);
		if (agent == null) {
			GetAgentResponse agentResponse = agentClient.getAgent(id);
			AgentDTO agentDTO = agentResponse.getAgent();
			return new ResponseEntity<>(agentDTO, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(new AgentDTO(), HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "/e/{email}", method = RequestMethod.GET)
	public ResponseEntity<com.megatravel.agentlocalbackend.model.Agent> getAgentByEmail(@PathVariable String email) {
		System.out.println("getAgentByEmail(" + email + ")");
		
		com.megatravel.agentlocalbackend.model.Agent agent = agentService.findByEmail(email);
		if (agent == null) {
			GetAgentByEmailResponse agentByEmailResponse = agentClient.getAgentByEmail(email);
			com.megatravel.agentlocalbackend.wsdl.GetAgentByEmailResponse.Agent agentNovi = agentByEmailResponse.getAgent(); 
			
			if (agentNovi==null) {
				return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
			} else {
				agent = new com.megatravel.agentlocalbackend.model.Agent(agentNovi.getIdAgenta(), agentNovi.getIme(), agentNovi.getPrezime(), agentNovi.getPoslovniMaticniBroj(), agentNovi.getEmail(), agentNovi.getLozinka());
				agent.setDatumClanstva(agentNovi.getDatumClanstva().toGregorianCalendar().getTime());
				agentService.deleteAll();
				agentService.save(agent);
			}
			
		}

		return new ResponseEntity<>(agent, HttpStatus.OK);
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody AgentPrijavaDTO agentPrijavaDTO) {
		System.out.println("login(" + agentPrijavaDTO.getEmail() + "," + agentPrijavaDTO.getLozinka() + ")");
		 /*
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
		*/
		LoginResponse loginResponse = agentClient.getLogin(agentPrijavaDTO);
		String jwt = loginResponse.getJwt();
		if (jwt.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		} else {
			getAgentByEmail(agentPrijavaDTO.getEmail());
			return new ResponseEntity<>(jwt, HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ResponseEntity<NeaktiviranAgent> signup(@RequestBody AgentRegistracijaDTO agentRegistracijaDTO) {
		System.out.println("signup()");
		/*
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
		*/
		
		SignUpResponse signupResponse = agentClient.getSignUp(agentRegistracijaDTO);
		NeaktiviranAgent agent = signupResponse.getNeaktiviranAgent();
		
		try{
			if (agent.getIdNeaktiviranogAgenta()==0 || agent==null) {
				return new ResponseEntity<>(HttpStatus.CONFLICT);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		
		return new ResponseEntity<>(agent, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/signout", method = RequestMethod.GET)
	public ResponseEntity<Void> signout(HttpServletRequest request) {
		System.out.println("signout()");
		/*
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
	    */
		rezervacijaService.deleteAll();
		agentService.deleteAll();
		
		
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	public ResponseEntity<Boolean> validateToken(@RequestBody String token) {
		System.out.println("validateToken()");
	
		return new ResponseEntity<>(new Boolean(true), HttpStatus.OK);
	}
	
}
