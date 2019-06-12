package com.megatravel.ratingservice.controller;

import java.util.List;
import java.util.Optional;

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

import com.megatravel.ratingservice.dto.NoviKomentarDTO;
import com.megatravel.ratingservice.dto.RezervacijaDTO;
import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.model.StatusKomentara;
import com.megatravel.ratingservice.model.StatusRezervacije;
import com.megatravel.ratingservice.service.KomentarService;

@RestController
@RequestMapping("/komentar")
public class KomentarController {

	@Autowired
	KomentarService komentarService;
	
	@Autowired
	RestTemplate restTemplate;

	// ovo je samo za korisnike
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Komentar> createKomentar(@RequestBody NoviKomentarDTO noviKomentar){
		System.out.println("createKomentar()");
		
		ResponseEntity<RezervacijaDTO> rezervacijaEntity = restTemplate.getForEntity("http://reservation-service/rezervacija/status/"+noviKomentar.getIdRezervacije(), RezervacijaDTO.class);
		if (rezervacijaEntity.getStatusCode() != HttpStatus.OK) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		RezervacijaDTO rezervacija = rezervacijaEntity.getBody();
		if (rezervacija == null) {			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		if (rezervacija.getSmestajId()!=noviKomentar.getIdSmestaja() ||
				rezervacija.getRezervacijaId()!=noviKomentar.getIdRezervacije() ||
				rezervacija.getKorisnikId()!=noviKomentar.getIdRezervacije() || rezervacija.getStatusRezervacije()!=StatusRezervacije.POTVRDJENA) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Komentar komentar = new Komentar(null, rezervacija.getSmestajId(), rezervacija.getRezervacijaId(), rezervacija.getKorisnikId(), noviKomentar.getKomentar(), StatusKomentara.NEOBJAVLJEN);
		komentar = komentarService.save(komentar);
		return new ResponseEntity<Komentar>(komentar, HttpStatus.CREATED);
	}
	
	// ovo je samo za admine
	@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Komentar> blokirajObjaviKomentar(@RequestBody StatusKomentara statusKomentara, @PathVariable Long id){
		System.out.println("blokirajObjaviKomentar(" + statusKomentara + ")");
		
		Optional<Komentar> kom = komentarService.findById(id);
		if (!kom.isPresent()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		Komentar komentar = kom.get();
		komentar.setStatus(statusKomentara);
		komentar = komentarService.save(komentar);
		
		return new ResponseEntity<Komentar>(komentar, HttpStatus.OK);
	}
	
	//samo za admina
	@RequestMapping(value = "/neprocitane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Komentar>> getNeobjavljeniKomentari(Pageable page) {
		System.out.println("getNeobjavljeniKomentari()");
		
		Page<Komentar> neobjavljeniKomentari = komentarService.findAllNeobjavljenji(page);
		
		HttpHeaders headers = new HttpHeaders();
		long komentariTotal = neobjavljeniKomentari.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(komentariTotal));

		return new ResponseEntity<>(neobjavljeniKomentari.getContent(), headers, HttpStatus.OK);
	}
	
}
