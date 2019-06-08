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
import com.megatravel.agentlocalbackend.dto.SmestajDTO;
import com.megatravel.agentlocalbackend.service.AgentService;
import com.megatravel.agentlocalbackend.service.SmestajService;

@RestController
@RequestMapping("/smestaj")
public class SmestajController {
	
	@Autowired
	RestTemplateConfiguration config;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	SmestajService smestajService;
	
	//@Autowired
	//JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(HttpServletRequest req, Pageable page) {
		System.out.println("getAllSmestaj()");
		
		String url = "https://smestaj-service/smestaj/all"; 
		
		RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<List<SmestajDTO>> exchange = restTemplate.exchange(url,
	        		HttpMethod.GET,
	                new HttpEntity<>(body),
	                new ParameterizedTypeReference<List<SmestajDTO>>(){});
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getSmestaj()");
		/*
		String url = "https://smestaj-service/smestaj/" + id; 
		
		RestTemplate restTemplate = config.createRestTemplate();
		
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.OK);*/return null;
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> create(@RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("create()");
	
		String url = "https://smestaj-service/smestaj"; 
		/*
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Smestaj s = new Smestaj();
		s.setAdresa(smestajDTO.getAdresa());
		s.setLatitude(smestajDTO.getLatitude());
		s.setLongitude(smestajDTO.getLongitude());
		s.setTipSmestaja(smestajDTO.getTipSmestaja());
		s.setKategorijaSmestaja(smestajDTO.getKategorijaSmestaja());
		s.setOpis(smestajDTO.getOpis());
		s.setMaxOsoba(smestajDTO.getMaxOsoba());
		s.setMaxDanaZaOtkazivanje(smestajDTO.getMaxDanaZaOtkazivanje());
		s.setCenaProlece(smestajDTO.getCenaProlece());
		s.setCenaLeto(smestajDTO.getCenaLeto());
		s.setCenaJesen(smestajDTO.getCenaJesen());
		s.setCenaZima(smestajDTO.getCenaZima());
		s.setVlasnik(agent.getIdAgenta());
		s.setListaDodatnihUsluga(smestajDTO.getListaDodatnihUsluga());
		s.setListaSlika(smestajDTO.getListaSlika());

		Smestaj retVal = smestajService.save(s);

		return new ResponseEntity<>(new SmestajDTO(retVal), HttpStatus.CREATED);*/return null;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> update(@PathVariable Long id, @RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("update()");
		/*
		String url = "https://smestaj-service/smestaj/" + id; 
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Smestaj smestaj = smestajService.findOne(id, agent.getIdAgenta());
		if (smestaj == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		smestaj.update(smestajDTO);

		Smestaj retVal = smestajService.save(smestaj);

		return new ResponseEntity<>(new SmestajDTO(retVal), HttpStatus.OK);*/return null;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("delete()");
		/*
		String url = "https://smestaj-service/smestaj/" + id; 
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Smestaj smestaj = smestajService.findOne(id,agent.getIdAgenta());
		if (smestaj != null) {
			smestajService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}*/return null;
	}
}
