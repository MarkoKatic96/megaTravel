package io.webxml.reservationservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.webxml.reservationservice.model.Rezervacija;
import io.webxml.reservationservice.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	@Autowired
	RezervacijaRepository rezervacijaRepository;
	
	public List<Rezervacija> getAllReservations(){
		
		Optional<List<Rezervacija>> rezervacije = Optional.of(rezervacijaRepository.findAll());
		List<Rezervacija> returnRezervacije = new ArrayList<Rezervacija>();
		if(rezervacije.isPresent()) {
			for (Rezervacija rezervacija : rezervacije.get()) {
				returnRezervacije.add(rezervacija);
			}
			
			return returnRezervacije;
		}
		
		return Collections.emptyList();
	}
	
	public List<Rezervacija> getAllReservationsFromUser(Long korisnikId){
		
		Optional<List<Rezervacija>> rezervacije = Optional.of(rezervacijaRepository.findAll());
		List<Rezervacija> returnRezervacije = new ArrayList<Rezervacija>();
		if(rezervacije.isPresent()) {
			for (Rezervacija rezervacija : rezervacije.get()) {
				if(rezervacija.getKorisnikId()==korisnikId) {
					returnRezervacije.add(rezervacija);
				}
			}
			
			return returnRezervacije;
		}
		
		return Collections.emptyList();
	}
	
	public Rezervacija reserve(Rezervacija rezervacija){
		int zauzeto = 0;
		Optional<List<Rezervacija>> rezervacije = Optional.of(rezervacijaRepository.findAll());
		List<Rezervacija> rezervacijeSaId = new ArrayList<Rezervacija>();
		if(rezervacije.isPresent()) {
			for (Rezervacija rezervacija1 : rezervacije.get()) {
				//uzimam sve rezervacije koje imaju isti smestaj
				if(rezervacija1.getSmestajId()==rezervacija.getSmestajId()) {
					if((rezervacija.getOd().equals(rezervacija1.getDo()) || rezervacija.getOd().after(rezervacija1.getDo()))
							|| (rezervacija1.getOd().equals(rezervacija.getDo()) || rezervacija1.getOd().after(rezervacija.getDo()))) {
						System.out.println("Slobodna rezervacija");
					}else {
						zauzeto = 1;
						break;
					}
				}
			}
			if(zauzeto==0) {
				return rezervacijaRepository.save(rezervacija);
			}
		}
		
		return null;
	}
	
	public Rezervacija otkaziRezervaciju(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);
		if(rezervacija.isPresent()) {
			rezervacijaRepository.deleteById(id);
			return rezervacija.get();
		}
		return null;
	}
	
}
