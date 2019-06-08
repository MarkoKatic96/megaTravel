package com.megatravel.agentlocalbackend.controller;

import java.nio.charset.Charset;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.agentlocalbackend.configuration.RestTemplateConfiguration;
import com.megatravel.agentlocalbackend.dto.NovaPorukaDTO;
import com.megatravel.agentlocalbackend.dto.PorukaDTO;
import com.megatravel.agentlocalbackend.service.AgentService;
import com.megatravel.agentlocalbackend.service.PorukeService;

@RestController
@RequestMapping("/poruke")
public class PorukeController {
	@Autowired
	RestTemplateConfiguration config;
	
	//@Autowired
	//JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	PorukeService porukeService;
	
	@Autowired
	AgentService agentService;
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getPorukeWithKorisnik(@PathVariable Long userId, Pageable page, HttpServletRequest req) {
		System.out.println("getPorukeWithKorisnik()");
		
		//String token = jwtTokenUtils.resolveToken(req);
		//String email = jwtTokenUtils.getUsername(token);
		String getPorukeUrl = "https://localhost:8400/poruke/" + userId; 
		
		RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<List<PorukaDTO>> exchange = restTemplate.exchange(getPorukeUrl,
	        		HttpMethod.GET,
	                new HttpEntity<>(body),
	                new ParameterizedTypeReference<List<PorukaDTO>>(){});
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	@RequestMapping(value = "/neprocitane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getNeprocitanePoruke(Pageable page, HttpServletRequest req) {
		System.out.println("getNeprocitanePoruke()");
		
		String getPorukeUrl = "https://localhost:8400/neprocitane";
		
		RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<List<PorukaDTO>> exchange = restTemplate.exchange(getPorukeUrl,
	        		HttpMethod.GET,
	                new HttpEntity<>(body),
	                new ParameterizedTypeReference<List<PorukaDTO>>(){});
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/{userId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setProcitanePorukeFromUser(@PathVariable Long userId, HttpServletRequest req) {
		System.out.println("setProcitanePorukeFromUser()");
		
		String postPorukeUrl = "https://localhost:8400/poruke/" + userId; 
		
		RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<Void> exchange = restTemplate.exchange(postPorukeUrl,
	        		HttpMethod.POST,
	                new HttpEntity<>(body),
	                Void.class);
	        
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorukaDTO> sendPoruka(@RequestBody NovaPorukaDTO novaPoruka, HttpServletRequest req) {
		System.out.println("sendPoruka()");
		
		String postPorukeUrl = "https://localhost:8400/poruke";
		
		RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<PorukaDTO> exchange = restTemplate.exchange(postPorukeUrl,
	        		HttpMethod.POST,
	                new HttpEntity<>(body),
	                PorukaDTO.class);
	        
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
}
