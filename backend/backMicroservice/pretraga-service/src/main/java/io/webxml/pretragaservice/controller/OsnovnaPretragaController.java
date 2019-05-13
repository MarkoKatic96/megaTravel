package io.webxml.pretragaservice.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Smestaj;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;
import io.webxml.pretragaservice.service.OsnovnaPretragaService;

@RestController
public class OsnovnaPretragaController {

	@Autowired
	private OsnovnaPretragaService osnovnaPretragaService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping(value = "/pretraga", method = RequestMethod.POST, consumes = "application/json")
	public List<Smestaj> getFilteredSmestaj(@RequestBody OsnovnaPretraga op){
		
		List<Smestaj> lista = new ArrayList<Smestaj>();
		List<Smestaj> returnLista = new ArrayList<Smestaj>();
		
		//uzimam sve smestaje iz korisnik-api
		SmestajiRestTemplate srt = restTemplate.getForObject("http://korisnik-service/api/smestaji", SmestajiRestTemplate.class);
		lista = srt.getSmestajiList();
		for (Smestaj smestaj : lista) {
			returnLista.add(smestaj);
		}
		
		return returnLista;
	}
	
}
