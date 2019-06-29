package io.webxml.reservationservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.webxml.reservationservice.dto.LokalneRezervacijeDTO;
import io.webxml.reservationservice.dto.RezervacijaDTO;
import io.webxml.reservationservice.dto.SamostalnaRezervacijaDTO;
import io.webxml.reservationservice.jwt.JwtTokenUtils;
import io.webxml.reservationservice.model.Agent;
import io.webxml.reservationservice.model.PotvrdaRezervacije;
import io.webxml.reservationservice.model.Rezervacija;
import io.webxml.reservationservice.model.SamostalnaRezervacija;
import io.webxml.reservationservice.service.RezervacijaService;
import io.webxml.reservationservice.service.SamostalnaRezervacijaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/reservation-service/agent")
public class RezervacijaAgentController {

	@Autowired
	SamostalnaRezervacijaService samostalnaRezervacijaService;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SamostalnaRezervacijaDTO> createRezervacija(@PathVariable String email,@RequestBody SamostalnaRezervacijaDTO rezDTO, HttpServletRequest req) {
		System.out.println("createRezervacija()");
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SamostalnaRezervacija s = new SamostalnaRezervacija(null, rezDTO.getSmestajId(), agent.getIdAgenta(), rezDTO.getOdDatuma(), rezDTO.getDoDatuma());
		if(samostalnaRezervacijaService.konfliktRezervacijaExists(agent.getIdAgenta(), rezDTO.getSmestajId(), rezDTO.getOdDatuma(), rezDTO.getDoDatuma())) {
			return new ResponseEntity<SamostalnaRezervacijaDTO>(HttpStatus.CONFLICT);
		}
		
		SamostalnaRezervacija retVal = samostalnaRezervacijaService.save(s);
		return new ResponseEntity<>(new SamostalnaRezervacijaDTO(retVal), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("deleteRezervacija()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
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
	
	@RequestMapping(value = "/potvrdi/{email}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> potvrdiRezervacija(@PathVariable String email, @RequestBody PotvrdaRezervacije potvrda, HttpServletRequest req) {
		System.out.println("potvrdiRezervacija()");
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
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
	public ResponseEntity<List<RezervacijaDTO>> getRezervacijeUpdate(@PathVariable String timestamp, 
			HttpServletRequest req) {
		System.out.println("getRezervacijeUpdate()");
		
		/*
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		*/
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date;
		try {
			date = format.parse ( timestamp );
		} catch (ParseException e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		} 
		
		List<Rezervacija> rezervacije = rezervacijaService.findAllAfter(date, (long)1);//agent.getIdAgenta());
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
		
		ResponseEntity<Agent> agentEntity = restTemplate.getForEntity("http://agent-global-service/agent/e/"+email, Agent.class);
		if (agentEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Agent agent = agentEntity.getBody();
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
