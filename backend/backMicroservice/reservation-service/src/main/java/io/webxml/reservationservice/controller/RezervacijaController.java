package io.webxml.reservationservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.webxml.reservationservice.model.Rezervacija;
import io.webxml.reservationservice.model.RezervacijeRestTemplate;
import io.webxml.reservationservice.service.RezervacijaService;

@RestController
public class RezervacijaController {
	
	@Autowired
	private RezervacijaService rezervacijaService;
	
	@RequestMapping(value = "/rezervacije")
	public ResponseEntity<RezervacijeRestTemplate> getAllReservations(){
		List<Rezervacija> rezervacije = rezervacijaService.getAllReservations();
		RezervacijeRestTemplate rrt = new RezervacijeRestTemplate();
		rrt.setRezervacijaList(rezervacije);
		return (!rezervacije.isEmpty()) ? new ResponseEntity<RezervacijeRestTemplate>(rrt, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/rezervacije/{korisnikId}")
	public ResponseEntity<List<Rezervacija>> getAllReservationsFromUser(@PathVariable("korisnikId") Long korisnikId){
		List<Rezervacija> rezervacije = rezervacijaService.getAllReservationsFromUser(korisnikId);
		return (!rezervacije.isEmpty()) ? new ResponseEntity<List<Rezervacija>>(rezervacije, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/rezervisi", method = RequestMethod.POST)
	public ResponseEntity<Rezervacija> reserve(@RequestBody Rezervacija rezervacija){
		Rezervacija r = rezervacijaService.reserve(rezervacija);
		return (r!=null) ? new ResponseEntity<Rezervacija>(r, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
	
	@RequestMapping(value = "/otkazi/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Rezervacija> otkazi(@PathVariable("id") Long id){
		Rezervacija r = rezervacijaService.otkaziRezervaciju(id);
		return (r!=null) ? new ResponseEntity<Rezervacija>(r, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);		
	}
}
