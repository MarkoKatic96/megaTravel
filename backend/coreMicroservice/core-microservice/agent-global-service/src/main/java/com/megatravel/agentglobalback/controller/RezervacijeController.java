package com.megatravel.agentglobalback.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.agentglobalback.dto.LokalneRezervacijeDTO;
import com.megatravel.agentglobalback.dto.RezervacijaDTO;
import com.megatravel.agentglobalback.dto.SamostalnaRezervacijaDTO;
import com.megatravel.agentglobalback.jwt.JwtTokenUtils;
import com.megatravel.agentglobalback.model.Agent;
import com.megatravel.agentglobalback.model.PotvrdaRezervacije;
import com.megatravel.agentglobalback.model.Rezervacija;
import com.megatravel.agentglobalback.model.SamostalnaRezervacija;
import com.megatravel.agentglobalback.service.AgentService;
import com.megatravel.agentglobalback.service.RezervacijaService;
import com.megatravel.agentglobalback.service.SamostalnaRezervacijaService;

@RestController
@RequestMapping("/agent-global-service/rezervacije")
public class RezervacijeController {

	@Autowired
	SamostalnaRezervacijaService samostalnaRezervacijaService;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SamostalnaRezervacijaDTO> createRezervacija(@RequestBody SamostalnaRezervacijaDTO rezDTO, HttpServletRequest req) {
		System.out.println("createRezervacija()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SamostalnaRezervacija s = new SamostalnaRezervacija(null, rezDTO.getSmestajId(), agent.getIdAgenta(), rezDTO.getOdDatuma(), rezDTO.getDoDatuma());
		SamostalnaRezervacija retVal = samostalnaRezervacijaService.save(s);
		
		return new ResponseEntity<>(new SamostalnaRezervacijaDTO(retVal), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("deleteRezervacija()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SamostalnaRezervacija rez = samostalnaRezervacijaService.findOne(id,agent.getIdAgenta());
		if (rez != null) {
			samostalnaRezervacijaService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/potvrdi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> potvrdiRezervacija(@RequestBody PotvrdaRezervacije potvrda, HttpServletRequest req) {
		System.out.println("potvrdiRezervacija()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Rezervacija rezervacija = rezervacijaService.findOne(potvrda.getRezervacijaId(), agent.getIdAgenta());
		if (rezervacija==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		rezervacija.setStatusRezervacije(potvrda.getStatusRezervacije());
		Rezervacija retVal = rezervacijaService.save(rezervacija);
		
		return new ResponseEntity<>(new RezervacijaDTO(retVal), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update/{timestamp}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> getRezervacijeUpdate(@PathVariable Date timestamp, HttpServletRequest req) {
		System.out.println("getRezervacijeUpdate()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rezervacija> rezervacije = rezervacijaService.findAllAfter(timestamp, agent.getIdAgenta());
		List<RezervacijaDTO> retVal = new ArrayList<>();
		
		for (Rezervacija rez : rezervacije) {
			RezervacijaDTO rezDTO = new RezervacijaDTO(rez);
			retVal.add(rezDTO);
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LokalneRezervacijeDTO>> sendRezervacijeUpdate(@RequestBody List<LokalneRezervacijeDTO> listaLokalnihRezervacija, HttpServletRequest req) {
		System.out.println("sendRezervacijeUpdate()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Agent agent = agentService.findByEmail(email);
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		ArrayList<LokalneRezervacijeDTO> retVal = new ArrayList<>();
		
		for (LokalneRezervacijeDTO lokalneRezervacijeDTO : listaLokalnihRezervacija) {
			if (rezervacijaService.konfliktRezervacijaExists(agent.getIdAgenta(), lokalneRezervacijeDTO.getSmestajId(), lokalneRezervacijeDTO.getOdDatuma(), lokalneRezervacijeDTO.getDoDatuma())) {
				// postoji rezervacija koja zauzima smestaj u tom periodu
				// ne smem dodati tu rezervaciju u globalnu bazu
				// vracam lokalnoj bazi globalId = null da je obavestim da je rezervacija nevazeca
				
				lokalneRezervacijeDTO.setGlobalniId(null);
				retVal.add(lokalneRezervacijeDTO);
			} else {
				// dodajem rezervaciju u globalnu bazu i vracam lokalnoj bazi id te rezervacije
				
				Rezervacija rez = new Rezervacija(lokalneRezervacijeDTO.getGlobalniId(), lokalneRezervacijeDTO.getSmestajId(), lokalneRezervacijeDTO.getVlasnikId(), lokalneRezervacijeDTO.getKorisnikId(), lokalneRezervacijeDTO.getOdDatuma(), lokalneRezervacijeDTO.getDoDatuma(), lokalneRezervacijeDTO.getStatusRezervacije());
				retVal.add(new LokalneRezervacijeDTO(rezervacijaService.save(rez), lokalneRezervacijeDTO.getLokalniId()));
			}
		}
		
		return new ResponseEntity<>(retVal, HttpStatus.OK);
	}
}
