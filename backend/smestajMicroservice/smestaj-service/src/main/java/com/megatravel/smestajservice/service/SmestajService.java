package com.megatravel.smestajservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.repository.SmestajRepository;

@Component
public class SmestajService {

	@Autowired
	private SmestajRepository smestajRepository;
	
	public Smestaj findOne(Long idSmestaja, Long idVlasnika) {
		Smestaj s = smestajRepository.getOne(idSmestaja);
		if (s!=null) {
			if (s.getVlasnik()==idVlasnika) {
				return s;
			}
		}
		return null;
	}
	
	public List<Smestaj> getSmestaji(){
		List<Smestaj> lista = smestajRepository.pronadjiSve();
		if(lista.isEmpty()) {
			return new ArrayList<Smestaj>();
		}
		return lista;
	} 
	
	public List<Smestaj> getSmestajiOdredjenogTipa(Long id){
		List<Smestaj> lista = smestajRepository.findAllSmestajWithType(id);
		return lista;
	}
	
	public List<Smestaj> getSmestajiOdredjeneKategorije(Long id){
		List<Smestaj> lista = smestajRepository.findAllSmestajWithCategory(id);
		return lista;
	}
	
	public List<Smestaj> getSmestajiUGradu(String grad){
		List<Smestaj> lista = smestajRepository.pronadjiSve();
		List<Smestaj> returnLista = new ArrayList<Smestaj>();
		for (Smestaj smestaj : lista) {
			if(smestaj.getAdresa().getGrad().equals(grad)) {
				returnLista.add(smestaj);
			}
		}
		return returnLista;
	}
	
	public Page<Smestaj> getAll(Long idVlasnika,Pageable page) {
		return smestajRepository.findAllFromMe(idVlasnika, page);
	}
	
	public Smestaj save(Smestaj smestaj) {
		return smestajRepository.save(smestaj);
	}
	
	public void remove(Long id) {
		smestajRepository.deleteById(id);
	}
}
