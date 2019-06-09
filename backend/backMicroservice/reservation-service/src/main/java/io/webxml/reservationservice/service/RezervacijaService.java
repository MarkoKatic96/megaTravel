package io.webxml.reservationservice.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.webxml.reservationservice.model.Rezervacija;
import io.webxml.reservationservice.model.StatusRezervacije;
import io.webxml.reservationservice.repository.RezervacijaRepository;

@Service
public class RezervacijaService {

	private @Autowired
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
		//List<Rezervacija> rezervacijeSaId = new ArrayList<Rezervacija>();
		if(rezervacije.isPresent()) {
			for (Rezervacija rezervacija1 : rezervacije.get()) {
				//uzimam sve rezervacije koje imaju isti smestaj
				if(rezervacija1.getSmestajId()==rezervacija.getSmestajId()) {
					if((rezervacija.getOdDatuma().equals(rezervacija1.getDoDatuma()) || rezervacija.getOdDatuma().after(rezervacija1.getDoDatuma()))
							|| (rezervacija1.getOdDatuma().equals(rezervacija.getDoDatuma()) || rezervacija1.getOdDatuma().after(rezervacija.getDoDatuma()))) {
						System.out.println("Slobodna rezervacija");
					}else {
						zauzeto = 1;
						break;
					}
				}
			}
			if(zauzeto==0) {
				rezervacija.setStatusRezervacije(StatusRezervacije.KREIRANA);
				rezervacija.setVlasnikId((long) 1);//dodaj ovo da radi kako treba
				rezervacija.setTimestamp(new Date());//i dodati setupdatetimestampe
				return rezervacijaRepository.save(rezervacija);
			}
		}
		
		return null;
	}
	
	public Rezervacija otkaziRezervaciju(Long id) {
		Optional<Rezervacija> rezervacija = rezervacijaRepository.findById(id);
		if(rezervacija.isPresent()) {
			rezervacija.get().setStatusRezervacije(StatusRezervacije.OTKAZANA);
			rezervacijaRepository.save(rezervacija.get());
			return rezervacija.get();
		}
		return null;
	}
	
	public Rezervacija findOne(Long idRezervacije, Long idAgenta) {
		Rezervacija rez = rezervacijaRepository.getOne(idRezervacije);
		if (rez!=null) {
			if (rez.getVlasnikId()==idAgenta) {
				return rez;
			}
		}
		return null;
	}

	public void remove(Long id) {
		rezervacijaRepository.deleteById(id);
	}

	public List<Rezervacija> findAllAfter(Date timestamp, Long idAgenta) {
		return rezervacijaRepository.findAllAfter(timestamp,idAgenta);
	}

	public boolean konfliktRezervacijaExists(Long idAgenta, Long smestajId, Date odDatuma, Date doDatuma) {
		ArrayList<Rezervacija> konfliktRezervacije = rezervacijaRepository.findKonfliktRezervacije(idAgenta, smestajId, odDatuma, doDatuma);
		return !konfliktRezervacije.isEmpty();
	}
	
	public Rezervacija save(Rezervacija r) {
		return rezervacijaRepository.save(r);
	}
	
}
