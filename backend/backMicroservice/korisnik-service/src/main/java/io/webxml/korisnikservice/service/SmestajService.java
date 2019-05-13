package io.webxml.korisnikservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.webxml.korisnikservice.model.Smestaj;
import io.webxml.korisnikservice.repository.SmestajRepository;


@Service
public class SmestajService {

	@Autowired
	private SmestajRepository smestajRepository;
	
	public List<Smestaj> getSmestaji(){
		List<Smestaj> lista = smestajRepository.findAll();
		return lista;
	}
	
}
