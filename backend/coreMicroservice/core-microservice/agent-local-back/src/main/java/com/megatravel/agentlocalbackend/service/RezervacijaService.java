package com.megatravel.agentlocalbackend.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.megatravel.agentlocalbackend.dto.RezervacijaDTO;
import com.megatravel.agentlocalbackend.model.Rezervacija;
import com.megatravel.agentlocalbackend.model.Smestaj;
import com.megatravel.agentlocalbackend.repository.RezervacijaRepository;
import com.megatravel.agentlocalbackend.repository.SmestajRepository;

@Component
public class RezervacijaService {
	
	@Autowired
	private RezervacijaRepository rezervacijaRepository;
	
	@Autowired
	private SmestajRepository smestajRepository;

	public void saveAll(List<RezervacijaDTO> rezervacije) {
		List<Rezervacija> list = new ArrayList<>();
		for (RezervacijaDTO rDTO : rezervacije) {
			list.add(new Rezervacija(rDTO.getRezervacijaId(), rDTO.getSmestajId(), rDTO.getVlasnikId(),
					rDTO.getKorisnikId(), rDTO.getOdDatuma(), rDTO.getDoDatuma(), rDTO.getStatusRezervacije()));
		}
		
		for (Rezervacija r : list) {
			rezervacijaRepository.save(r);
		}
	}
	
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
	
	public Rezervacija findOne(Long id) {
		return rezervacijaRepository.getOne(id);
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
	
	public Date findOldestDate() {
		return rezervacijaRepository.findOldestDate();
	}
	
	public void deleteAll() {
		rezervacijaRepository.deleteAll();
	}
}
