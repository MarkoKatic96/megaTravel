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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.porukeservice.dto.NovaPorukaDTO;
import com.megatravel.porukeservice.dto.PorukaDTO;
import com.megatravel.porukeservice.jwt.JwtTokenUtils;
import com.megatravel.porukeservice.model.Korisnik;
import com.megatravel.porukeservice.model.Poruka;
import com.megatravel.porukeservice.model.StatusPoruke;
import com.megatravel.porukeservice.model.TipOsobe;
import com.megatravel.porukeservice.service.PorukeService;

@RestController
@RequestMapping("/poruke-korisnik-service/poruke")
@CrossOrigin(origins = "http://localhost:3000")
public class PorukeKorisnikController {
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	PorukeService porukeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value = "/{agentId}/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getPorukeWithAgent(@PathVariable("agentId") Long agentId, @PathVariable("token") String token, Pageable page) {
		System.out.println("getPorukeWithAgent()");
	
		ResponseEntity<Korisnik> korisnikEntity = restTemplate.getForEntity("http://korisnik-service/korisnik-service/getKorisnikByToken/"+token, Korisnik.class);
		
		if (korisnikEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Korisnik korisnik = korisnikEntity.getBody();
		if (korisnik == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Page<Poruka> poruke = porukeService.findAllWithAgent(agentId, korisnik.getIdKorisnik(), page);
		
		HttpHeaders headers = new HttpHeaders();
		long porukeTotal = poruke.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(porukeTotal));

		List<PorukaDTO> retVal = new ArrayList<>();
		
		for (Poruka p : poruke) {
			retVal.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(retVal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/neprocitane/{token}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PorukaDTO>> getNeprocitanePoruke(Pageable page, @PathVariable("token") String token) {
		System.out.println("getNeprocitanePoruke()");
		
		ResponseEntity<Korisnik> korisnikEntity = restTemplate.getForEntity("http://korisnik-service/korisnik-service/getKorisnikByToken/"+token, Korisnik.class);
		
		if (korisnikEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Korisnik korisnik = korisnikEntity.getBody();
		if (korisnik == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Page<Poruka> poruke = porukeService.findAllNeprocitane(korisnik.getIdKorisnik(), page);
		
		HttpHeaders headers = new HttpHeaders();
		long porukeTotal = poruke.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(porukeTotal));

		List<PorukaDTO> retVal = new ArrayList<>();
		
		for (Poruka p : poruke) {
			retVal.add(new PorukaDTO(p));
		}
		
		return new ResponseEntity<>(retVal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{agentId}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> setProcitanePorukeFromAgent(@PathVariable Long agentId, HttpServletRequest req) {
		System.out.println("setProcitanePorukeFromAgent()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Korisnik> korisnikEntity = restTemplate.getForEntity("http://korisnik-service/korisnik/"+email, Korisnik.class);
		if (korisnikEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Korisnik korisnik = korisnikEntity.getBody();
		if (korisnik == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Poruka> poruke = porukeService.findAllNeprocitaneWithAgent(agentId, korisnik.getIdKorisnik());
		
		for (Poruka p : poruke) {
			p.setStatus(StatusPoruke.PROCITANA);
			porukeService.save(p);
		}
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/posalji/{token}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PorukaDTO> sendPoruka(@RequestBody NovaPorukaDTO novaPoruka, @PathVariable("token") String token) {
		System.out.println("sendPoruka()");
		
		ResponseEntity<Korisnik> korisnikEntity = restTemplate.getForEntity("http://korisnik-service/korisnik-service/getKorisnikByToken/"+token, Korisnik.class);
		if (korisnikEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Korisnik korisnik = korisnikEntity.getBody();
		if (korisnik == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Poruka poruka = new Poruka(null, korisnik.getIdKorisnik(), TipOsobe.KORISNIK, novaPoruka.getPrimalac(), TipOsobe.AGENT, novaPoruka.getSadrzaj(), StatusPoruke.POSLATA);
		Poruka retVal = porukeService.save(poruka);
		
		return new ResponseEntity<>(new PorukaDTO(retVal), HttpStatus.CREATED);
	}
	
}
