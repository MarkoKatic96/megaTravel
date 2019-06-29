package io.webxml.reservationservice.service;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.webxml.reservationservice.model.SamostalnaRezervacija;
import io.webxml.reservationservice.repository.SamostalnaRezervacijaRepository;

@Component
public class SamostalnaRezervacijaService {

	@Autowired
	private SamostalnaRezervacijaRepository rezervacijaRepository;

	public SamostalnaRezervacija save(SamostalnaRezervacija s) {
		return rezervacijaRepository.save(s);
	}

	public SamostalnaRezervacija findOne(Long idRezervacije, Long idAgenta) {
		SamostalnaRezervacija rez = rezervacijaRepository.getOne(idRezervacije);
		if (rez!=null) {
			if (rez.getVlasnikId()==idAgenta) {
				return rez;
			}
		}
		return null;
	}

	public boolean konfliktRezervacijaExists(Long idAgenta, Long smestajId, Date odDatuma, Date doDatuma) {
		ArrayList<SamostalnaRezervacija> konfliktRezervacije = rezervacijaRepository.findKonfliktRezervacije(idAgenta, smestajId, odDatuma, doDatuma);
		return !konfliktRezervacije.isEmpty();
	}
	
	public void remove(Long id) {
		rezervacijaRepository.deleteById(id);
	}
	
	
}
