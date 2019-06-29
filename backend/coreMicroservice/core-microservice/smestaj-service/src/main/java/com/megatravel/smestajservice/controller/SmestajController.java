package com.megatravel.smestajservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.smestajservice.dto.SmestajDTO;
import com.megatravel.smestajservice.jwt.JwtTokenUtils;
import com.megatravel.smestajservice.model.Agent;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.service.SmestajService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/smestaj-service/smestaj")
public class SmestajController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/all/{email}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(@PathVariable String email, HttpServletRequest req) {
		System.out.println("getAllSmestaj()");
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}

		List<Smestaj> smestaji = smestajService.getAll(agent.getIdAgenta());

		List<SmestajDTO> retVal = new ArrayList<SmestajDTO>();

		for (Smestaj s : smestaji) {
			SmestajDTO smestajDTO = new SmestajDTO(s);
			retVal.add(smestajDTO);
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getSmestaj()");
		
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

		Smestaj smestaj = smestajService.findOne(id, agent.getIdAgenta());
		
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> create(@PathVariable String email, @RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("create()");
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		/*
		this.idSmestaja = idSmestaja;
		this.adresa = adresa;
		this.koordinate = koordinate;
		this.tipSmestaja = tipSmestaja;
		this.kategorijaSmestaja = kategorijaSmestaja;
		this.opis = opis;
		this.maxOsoba = maxOsoba;
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
		this.cenaProlece = cenaProlece;
		this.cenaLeto = cenaLeto;
		this.cenaJesen = cenaJesen;
		this.cenaZima = cenaZima;
		this.vlasnik = vlasnik;
		this.listaDodatnihUsluga = listaDodatnihUsluga;
		this.listaSlika = listaSlika;
		 */
		Optional<TipSmestaja> tip =smestajService.getTip(smestajDTO.getTipSmestaja().getTipSmestajaId());
		if(!tip.isPresent()) {
			return new ResponseEntity<SmestajDTO>(HttpStatus.NOT_FOUND);
		}
		
		Smestaj s = new Smestaj();
		s.setAdresa(smestajDTO.getAdresa());
		s.setLatitude(smestajDTO.getLatitude());
		s.setLongitude(smestajDTO.getLongitude());
		s.setTipSmestaja(tip.get());
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

		return new ResponseEntity<>(new SmestajDTO(retVal), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> update(@PathVariable Long id, @RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("update()");
		
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
		
		Smestaj smestaj = smestajService.findOne(id, agent.getIdAgenta());
		if (smestaj == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		smestaj.update(smestajDTO);

		Smestaj retVal = smestajService.save(smestaj);

		return new ResponseEntity<>(new SmestajDTO(retVal), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("delete()");
		
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
		
		Smestaj smestaj = smestajService.findOne(id,agent.getIdAgenta());
		if (smestaj != null) {
			smestajService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);

		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
