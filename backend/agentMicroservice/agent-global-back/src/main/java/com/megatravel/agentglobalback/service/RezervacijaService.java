package com.megatravel.agentglobalback.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.agentglobalback.model.Rezervacija;
import com.megatravel.agentglobalback.model.Smestaj;
import com.megatravel.agentglobalback.repository.RezervacijaRepository;
import com.megatravel.agentglobalback.repository.SmestajRepository;

@Component
public class RezervacijaService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	public Rezervacija save(Rezervacija r) {
		return rezervacijaRepository.save(r);
	}
	
	public Rezervacija findOne(Long idRezervacije, Long idAgenta) {
		Rezervacija rez = rezervacijaRepository.getOne(idRezervacije);
		if (rez!=null) {
			Smestaj smestaj = smestajRepository.getOne(rez.getSmestajId());
			if (smestaj!=null) {
				if (smestaj.getVlasnik()==idAgenta) {
					return rez;
				}
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
}
