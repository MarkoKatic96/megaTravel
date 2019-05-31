package io.webxml.korisnikservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.webxml.korisnikservice.model.Smestaj;
import io.webxml.korisnikservice.model.SmestajiRestTemplate;
import io.webxml.korisnikservice.service.SmestajService;

@RestController
public class SmestajController {
	
	@Autowired
	private SmestajService smestajService;
	
	@RequestMapping("api/smestaji")
	public ResponseEntity<SmestajiRestTemplate> getSmestaji(){
		List<Smestaj> lista = smestajService.getSmestaji();
		SmestajiRestTemplate srt = new SmestajiRestTemplate();
		srt.setSmestajiList(lista);
		return new ResponseEntity<SmestajiRestTemplate>(srt, HttpStatus.OK);
	}

	@RequestMapping("api/smestajiTipa/{id}")
	public ResponseEntity<SmestajiRestTemplate> getSmestajiTipa(@PathVariable("id") Long id){
		List<Smestaj> lista = smestajService.getSmestajiOdredjenogTipa(id);
		SmestajiRestTemplate srt = new SmestajiRestTemplate();
		srt.setSmestajiList(lista);
		return new ResponseEntity<SmestajiRestTemplate>(srt, HttpStatus.OK);
	}
	
	@RequestMapping("api/smestajiKategorije/{id}")
	public ResponseEntity<SmestajiRestTemplate> getSmestajiKategorije(@PathVariable("id") Long id){
		List<Smestaj> lista = smestajService.getSmestajiOdredjeneKategorije(id);
		SmestajiRestTemplate srt = new SmestajiRestTemplate();
		srt.setSmestajiList(lista);
		return new ResponseEntity<SmestajiRestTemplate>(srt, HttpStatus.OK);
	}
	
}
