package io.webxml.reservationservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import io.webxml.reservationservice.jwt.JwtTokenUtils;

import io.webxml.reservationservice.model.Korisnik;
import io.webxml.reservationservice.model.Rezervacija;
import io.webxml.reservationservice.model.RezervacijeRestTemplate;
import io.webxml.reservationservice.service.RezervacijaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	//ovo samo admin moze da vidi. Treba zastititi
	@RequestMapping(value = "/rezervacije")
	public ResponseEntity<RezervacijeRestTemplate> getAllReservations(){
		List<Rezervacija> rezervacije = rezervacijaService.getAllReservations();
		RezervacijeRestTemplate rrt = new RezervacijeRestTemplate();
		rrt.setRezervacijaList(rezervacije);
		return (!rezervacije.isEmpty()) ? new ResponseEntity<RezervacijeRestTemplate>(rrt, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/rezervacije/{token}")
	public ResponseEntity<List<Rezervacija>> getAllReservationsFromUser(@PathVariable("token") String token){
		
		Korisnik k = restTemplate.getForObject("http://korisnik-service/getKorisnikByToken/" + token, Korisnik.class);
		
		if(k!=null) {
			List<Rezervacija> rezervacije = rezervacijaService.getAllReservationsFromUser(k.getIdKorisnik());
			return (!rezervacije.isEmpty()) ? new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//ovako radi ali vrv nije dobra praksa ovako raditi
	@RequestMapping(value = "/rezervisi/{token}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Rezervacija> reserve(@PathVariable("token") String token, @RequestBody Rezervacija rezervacija){
		
		Korisnik k = restTemplate.getForObject("http://korisnik-service/getKorisnikByToken/" + token, Korisnik.class);

		if(k!=null) {
			Rezervacija r = rezervacijaService.reserve(rezervacija);
			return (r!=null) ? new ResponseEntity<Rezervacija>(r, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);	
		}else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
	
	@RequestMapping(value = "/otkazi/{id}/{token}", method = RequestMethod.DELETE)
	public ResponseEntity<Rezervacija> otkazi(@PathVariable("id") Long id, @PathVariable("token") String token, HttpServletRequest req){
		
		Korisnik k = restTemplate.getForObject("http://korisnik-service/getKorisnikByToken/" + token, Korisnik.class);

		if(k!=null) {
			Rezervacija r = rezervacijaService.otkaziRezervaciju(id);
			return (r!=null) ? new ResponseEntity<Rezervacija>(r, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}else {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
	}
}
