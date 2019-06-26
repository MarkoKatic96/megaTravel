package com.megatravel.ratingservice.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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

import com.megatravel.ratingservice.dto.NoviKomentarDTO;
import com.megatravel.ratingservice.dto.RezervacijaDTO;
import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.model.Ocena;
import com.megatravel.ratingservice.model.StatusKomentara;
import com.megatravel.ratingservice.model.StatusRezervacije;
import com.megatravel.ratingservice.service.KomentarService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rating-service/komentar")
public class KomentarController {

	@Autowired
	KomentarService komentarService;
	
	@Autowired
	RestTemplate restTemplate;
	
	RestTemplate rt = new RestTemplate();

	// ovo je samo za korisnike, koristim ja
	@RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createKomentar(@RequestBody NoviKomentarDTO noviKomentar){
		System.out.println("createKomentar()");
		
		/*ResponseEntity<RezervacijaDTO> rezervacijaEntity = restTemplate.getForEntity("http://reservation-service/reservation-service/rezervacija/status/"+noviKomentar.getIdRezervacije(), RezervacijaDTO.class);
		if (rezervacijaEntity.getStatusCode() != HttpStatus.OK) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		RezervacijaDTO rezervacija = rezervacijaEntity.getBody();
		if (rezervacija == null) {			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		if (rezervacija.getSmestajId()!=noviKomentar.getIdSmestaja() ||
				rezervacija.getRezervacijaId()!=noviKomentar.getIdRezervacije() ||
				rezervacija.getKorisnikId()!=noviKomentar.getIdKorisnika() || rezervacija.getStatusRezervacije()!=StatusRezervacije.POTVRDJENA) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}*/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Komentar komentar = new Komentar(null, noviKomentar.getIdSmestaja(), noviKomentar.getIdRezervacije(), noviKomentar.getIdKorisnika(), noviKomentar.getKomentar(), StatusKomentara.NEOBJAVLJEN);
		komentar.setTimestamp(null);
		//komentar = komentarService.save(komentar);
		String url = "http://localhost:8010/cloud-demo/us-central1/createKomentar";
		HttpEntity<Komentar> request = new HttpEntity<>(komentar, headers);
		ResponseEntity<String> response = rt
		  .exchange(url, HttpMethod.POST, request, String.class);
		  
		String s = response.getBody();
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}
	
	// ovo je samo za admine
	/*@RequestMapping(value = "/{id}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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
	}*/
	
	//samo za admina
	/*@RequestMapping(value = "/neprocitane", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Komentar>> getNeobjavljeniKomentari(Pageable page) {
		System.out.println("getNeobjavljeniKomentari()");
		
		Page<Komentar> neobjavljeniKomentari = komentarService.findAllNeobjavljenji(page);
		
		HttpHeaders headers = new HttpHeaders();
		long komentariTotal = neobjavljeniKomentari.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(komentariTotal));

		return new ResponseEntity<>(neobjavljeniKomentari.getContent(), headers, HttpStatus.OK);
	}*/
	
	//koristim ja
	@RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Komentar>> getAllKomentari() {
		
		String url = "http://localhost:8010/cloud-demo/us-central1/getObjavljeneKomentare";
		ResponseEntity<Komentar[]> response = rt.getForEntity(url, Komentar[].class);
		Komentar[] k = response.getBody();
		List<Komentar> lista = Arrays.asList(k);
		return new ResponseEntity<List<Komentar>>(lista, HttpStatus.OK);
	}
	
}
