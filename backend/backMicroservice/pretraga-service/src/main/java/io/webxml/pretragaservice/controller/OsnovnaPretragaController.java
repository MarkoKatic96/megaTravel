package io.webxml.pretragaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.webxml.pretragaservice.dto.SmestajKorisnikDTO;
import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.service.OsnovnaPretragaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("pretraga-service")
public class OsnovnaPretragaController {

	@Autowired
	private OsnovnaPretragaService osnovnaPretragaService;
	
	@RequestMapping(value = "/pretraga", method = RequestMethod.POST, consumes = "application/json")
	public List<SmestajKorisnikDTO> getFilteredSmestaj(@RequestBody OsnovnaPretraga op){	
		//uzimam sve smestaje iz korisnik-api
		//RezervacijeRestTemplate rrt = restTemplate.getForObject("http://reservation-service/rezervacije", RezervacijeRestTemplate.class);
		return osnovnaPretragaService.osnovnaPretragaSmestaji(op);
	}
	
	@RequestMapping(value = "/sort/{tip}", method = RequestMethod.POST, consumes = "application/json")
	public List<SmestajKorisnikDTO> sortSmestaj(@RequestBody List<SmestajKorisnikDTO> lista, @PathVariable("tip") String sort){	
		//uzimam sve smestaje iz korisnik-api
		//RezervacijeRestTemplate rrt = restTemplate.getForObject("http://reservation-service/rezervacije", RezervacijeRestTemplate.class);
		return osnovnaPretragaService.sortSmestaji(lista, sort);
	}
	
}
