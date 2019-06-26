package com.megatravel.ratingservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.megatravel.ratingservice.dto.NovaOcenaDTO;
import com.megatravel.ratingservice.dto.RezervacijaDTO;
import com.megatravel.ratingservice.model.Ocena;
import com.megatravel.ratingservice.model.StatusRezervacije;
import com.megatravel.ratingservice.repository.OcenaRepository;
import com.megatravel.ratingservice.service.OcenaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rating-service/ocena")
public class OcenaController {
	
	@Autowired
	OcenaService ocenaService;
	
	@Autowired
	OcenaRepository ocenaRepository;
	
	@Autowired
	RestTemplate restTemplate;
	
	RestTemplate rt = new RestTemplate();

	@RequestMapping(value = "/new", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> createOcena(@RequestBody NovaOcenaDTO novaOcena){
		
		/*ResponseEntity<RezervacijaDTO> rezervacijaEntity = restTemplate.getForEntity("http://reservation-service/reservation-service/rezervacija/status/"+novaOcena.getIdRezervacija(), RezervacijaDTO.class);
		if (rezervacijaEntity.getStatusCode() != HttpStatus.OK) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		RezervacijaDTO rezervacija = rezervacijaEntity.getBody();
		if (rezervacija == null) {			
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		if (rezervacija.getSmestajId()!=novaOcena.getIdSmestaj() ||
				rezervacija.getRezervacijaId()!=novaOcena.getIdRezervacija() ||
				rezervacija.getKorisnikId()!=novaOcena.getIdKorisnik() || rezervacija.getStatusRezervacije()!=StatusRezervacije.POTVRDJENA) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		List<Ocena> ocene = ocenaRepository.findAll();
		for (Ocena ocena : ocene) {
			if(ocena.getIdRezervacija()==novaOcena.getIdRezervacija()) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
		}
		
		Ocena ocena = new Ocena(null, novaOcena.getIdSmestaj(), novaOcena.getIdRezervacija(), novaOcena.getIdKorisnik(), novaOcena.getOcena());
		if (ocena.getOcena()<0 || ocena.getOcena()>5) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

		ocena = ocenaService.save(ocena);*/ 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Ocena ocena = new Ocena(null, novaOcena.getIdSmestaj(), novaOcena.getIdRezervacija(), novaOcena.getIdKorisnik(), novaOcena.getOcena());
		ocena.setTimestamp(null);
		String url = "http://localhost:8010/cloud-demo/us-central1/createOcena";
		HttpEntity<Ocena> request = new HttpEntity<>(ocena, headers);
		ResponseEntity<String> response = rt
		  .exchange(url, HttpMethod.POST, request, String.class);
		  
		String s = response.getBody();
		  
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> editDeleteOcena(@RequestBody Ocena novaOcena){
		
		/*if (novaOcena.getOcena()<0 || novaOcena.getOcena()>5) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		
		if (novaOcena.getOcena()==0) {
			//brisanje ocene
			if (ocenaService.remove(novaOcena)) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
			
		//editovanje ocene
		Ocena ocena = ocenaService.findOne(novaOcena.getIdKorisnik());
		if (ocena==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		ocena.setOcena(novaOcena.getOcena());
		ocena = ocenaService.save(ocena);
		return new ResponseEntity<>(ocena, HttpStatus.CREATED);*/
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		novaOcena.setTimestamp(null);
		String url = "http://localhost:8010/cloud-demo/us-central1/editDeleteOcena";
		HttpEntity<Ocena> request = new HttpEntity<>(novaOcena, headers);
		ResponseEntity<String> response = rt
		  .exchange(url, HttpMethod.POST, request, String.class);
		  
		String s = response.getBody();
		  
		return new ResponseEntity<String>(s, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Float> getAverageOcenaForSmestaj(@PathVariable Long id) {
		
		String url = "http://localhost:8010/cloud-demo/us-central1/getOcene?id=" + id;
		ResponseEntity<Ocena[]> response = rt.getForEntity(url, Ocena[].class);
		Ocena[] o = response.getBody();
		List<Ocena> lista = Arrays.asList(o);
		int broj = lista.size();
		float zbir = 0;
		for (Ocena ocena : lista) {
			zbir+=ocena.getOcena();
		}
		float prosek = zbir/broj;
		Float returnFloat = new Float(prosek); 
		return new ResponseEntity<Float>(returnFloat, HttpStatus.OK);
		
	}
	
	/*@RequestMapping(value = "/getOcena", method = RequestMethod.GET)
	public ResponseEntity<Ocena[]> getOcena() {
		
		String url = "http://localhost:8010/cloud-demo/us-central1/getOcene";
		ResponseEntity<Ocena[]> response = rt.getForEntity(url, Ocena[].class);
		return new ResponseEntity<Ocena[]>(response.getBody(), HttpStatus.OK);
		
	}*/
}
