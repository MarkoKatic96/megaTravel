package com.megatravel.smestajservice.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.smestajservice.dto.SmestajKorisnikDTO;
import com.megatravel.smestajservice.jwt.JwtTokenUtils;
import com.megatravel.smestajservice.model.Korisnik;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.SmestajiRestTemplate;
import com.megatravel.smestajservice.service.SmestajService;

@RestController
@RequestMapping("/smestaj-korisnik/")
public class SmestajKorisnikController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<SmestajiRestTemplate> getAllSmestaji(Pageable page) {		

		//ne treba ovde autorizacija svako moze da pregleda smestaje
		Page<Smestaj> smestaji = smestajService.getSmestaji(page);
		
		HttpHeaders headers = new HttpHeaders();
		long korisniciTotal = smestaji.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(korisniciTotal));

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}
		
		SmestajiRestTemplate srt = new SmestajiRestTemplate();
		srt.setSmestajiList(retVal);

		return new ResponseEntity<>(srt, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajKorisnikDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getSmestaj()");
		
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

		SmestajKorisnikDTO smestaj = smestajService.findOne(id);
		
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(smestaj, HttpStatus.OK);
	}
	
	//ovo mi je samo za testiranje ostavi ovako ne zasticeno kasnije cemo zastititi
	@RequestMapping(value="/dodaj-uslugu/{uslugaId}/{smestajId}", method = RequestMethod.GET)
	public ResponseEntity<SmestajKorisnikDTO> dodajUslugu(@PathVariable("uslugaId") Long uslugaId,@PathVariable("smestajId") Long smestajId){
		SmestajKorisnikDTO smestaj = smestajService.dodajUslugu(uslugaId, smestajId);
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(smestaj, HttpStatus.OK);
	}
	
	
	/*@RequestMapping(value = "/grad/{grad}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajKorisnikDTO>> getSmestajUGradu(@PathVariable String grad, Pageable page, HttpServletRequest req) {
		System.out.println("getSmestajUGradu(" + grad + ")");
		
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

		Page<Smestaj> smestaji = smestajService.getAllInGrad(grad, page);
		
		HttpHeaders headers = new HttpHeaders();
		long korisniciTotal = smestaji.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(korisniciTotal));

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/tip/{tip}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajKorisnikDTO>> getSmestajTipa(@PathVariable Long tip, Pageable page, HttpServletRequest req) {
		System.out.println("getSmestajTipa()");
		
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

		Page<Smestaj> smestaji = smestajService.getAllOfTip(tip, page);
		
		HttpHeaders headers = new HttpHeaders();
		long korisniciTotal = smestaji.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(korisniciTotal));

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/kategorija/{kategorija}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajKorisnikDTO>> getSmestajKategorije(@PathVariable Long kategorija, Pageable page, HttpServletRequest req) {
		System.out.println("getSmestajKategorije()");
		
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

		Page<Smestaj> smestaji = smestajService.getAllOfKategorija(kategorija, page);
		
		HttpHeaders headers = new HttpHeaders();
		long korisniciTotal = smestaji.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(korisniciTotal));

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}

		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}*/
}
