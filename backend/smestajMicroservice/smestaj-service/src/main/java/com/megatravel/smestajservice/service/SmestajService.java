package com.megatravel.smestajservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.smestajservice.dto.SmestajKorisnikDTO;
import com.megatravel.smestajservice.model.DodatneUsluge;
import com.megatravel.smestajservice.model.KategorijaSmestaja;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.repository.DodatnaUslugaRepository;
import com.megatravel.smestajservice.repository.KategorijaSmestajaRepository;
import com.megatravel.smestajservice.repository.SmestajRepository;
import com.megatravel.smestajservice.repository.TipSmestajaRepository;

@Component
public class SmestajService {

	@Autowired
	private SmestajRepository smestajRepository;
	
	@Autowired
	private DodatnaUslugaRepository dodatnaUslugaRepository;
	
	@Autowired
	private TipSmestajaRepository tipSmestajaRepository;
	
	@Autowired
	private KategorijaSmestajaRepository kategorijaSmestajaRepository;
	
	public Smestaj findOne(Long idSmestaja, Long idVlasnika) {
		Smestaj s = smestajRepository.getOne(idSmestaja);
		if (s!=null) {
			if (s.getVlasnik()==idVlasnika) {
				return s;
			}
		}
		return null;
	}
	
	public SmestajKorisnikDTO findOne(Long idSmestaja) {
		Smestaj s = smestajRepository.getOne(idSmestaja);
		if (s == null) {
			return null;
		} else {
			return new SmestajKorisnikDTO(s);
		}
	}
	
	public List<Smestaj> getSmestaji(){
		List<Smestaj> lista = smestajRepository.pronadjiSve();
		if(lista.isEmpty()) {
			return new ArrayList<Smestaj>();
		}
		return lista;
	} 
	
	public Page<Smestaj> getAll(Long idVlasnika,Pageable page) {
		return smestajRepository.findAllFromMe(idVlasnika, page);
	}
	
	public Page<Smestaj> getAllInGrad(String grad,Pageable page) {
		return smestajRepository.getAllInGrad(grad, page);
	}
	
	public Smestaj save(Smestaj smestaj) {
		return smestajRepository.save(smestaj);
	}
	
	public void remove(Long id) {
		smestajRepository.deleteById(id);
	}

	public Page<Smestaj> getSmestaji(Pageable page) {
		return smestajRepository.findAll(page);
	}

	public Page<Smestaj> getAllOfTip(Long tip, Pageable page) {
		return smestajRepository.getAllOfTip(tip,page);
	}
	
	public Page<Smestaj> getAllOfKategorija(Long kategorija, Pageable page) {
		return smestajRepository.getAllOfKategorija(kategorija, page);
	}
	
	public List<TipSmestaja> getAllTipove() {
		return tipSmestajaRepository.getAll();
	}
	
	public List<KategorijaSmestaja> getAllKategorije() {
		return kategorijaSmestajaRepository.getAll();
	}
	
	public List<DodatneUsluge> getAllUsluge(){
		return dodatnaUslugaRepository.getAll();
	}
	
	//ovo mi je samo za testiranje ostavi ovako ne zasticeno kasnije cemo zastititi
	public SmestajKorisnikDTO dodajUslugu(Long uslugaId, Long smestajId) {
		Optional<DodatneUsluge> usluga = dodatnaUslugaRepository.findById(uslugaId);
		Optional<Smestaj> smestaj = smestajRepository.findById(smestajId);
		if(usluga.isPresent() && smestaj.isPresent()) {
			Set<DodatneUsluge> usluge = smestaj.get().getListaDodatnihUsluga();
			for (DodatneUsluge dodatneUsluge : usluge) {
				if(dodatneUsluge.getIdDodatneUsluge()==uslugaId) {
					//ima vec tu dodatnu uslugu
					break;
				}
			}
			usluge.add(usluga.get());
			smestaj.get().setListaDodatnihUsluga(usluge);
			smestajRepository.save(smestaj.get());
			SmestajKorisnikDTO smestajDTO = findOne(smestajId);
			return smestajDTO;
		}
		
		return null;
	}
}
