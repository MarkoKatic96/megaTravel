package com.megatravel.agentlocalbackend.controller;

import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
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

import com.megatravel.agentlocalbackend.Singleton;
import com.megatravel.agentlocalbackend.dto.LokalneRezervacijeDTO;
import com.megatravel.agentlocalbackend.dto.RezervacijaDTO;
import com.megatravel.agentlocalbackend.dto.SamostalnaRezervacijaDTO;
import com.megatravel.agentlocalbackend.model.PotvrdaRezervacije;
import com.megatravel.agentlocalbackend.model.Rezervacija;
import com.megatravel.agentlocalbackend.service.AgentService;
import com.megatravel.agentlocalbackend.service.RezervacijaService;
import com.megatravel.agentlocalbackend.service.SamostalnaRezervacijaService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/agent-local-service/rezervacije")
public class RezervacijeController {
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	SamostalnaRezervacijaService samostalnaRezervacijaService;
	
	@Autowired
	RezervacijaService rezervacijaService;
	
	@Autowired
	AgentService agentService;
	
	//@Autowired
	//JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SamostalnaRezervacijaDTO> createRezervacija(@RequestBody SamostalnaRezervacijaDTO rezDTO, HttpServletRequest req) {
		System.out.println("createRezervacija()");
		String url = "https://reservation-service/agent"; 
		
		//RestTemplate restTemplate = config.createRestTemplate();
		
	    try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<SamostalnaRezervacijaDTO> exchange = restTemplate.exchange(url,
	        		HttpMethod.POST,
	                new HttpEntity<>(body),
	                SamostalnaRezervacijaDTO.class);
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteRezervacija(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("deleteRezervacija()");
		
		String url = "https://reservation-service/agent/" + id; 
		//RestTemplate restTemplate = config.createRestTemplate();
		
		try {
	    	String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	        ResponseEntity<Void> exchange = restTemplate.exchange(url,
	        		HttpMethod.DELETE,
	                new HttpEntity<>(body),
	                Void.class);
	        return exchange;
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
	@RequestMapping(value = "/potvrdi", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RezervacijaDTO> potvrdiRezervacija(@RequestBody PotvrdaRezervacije potvrda, HttpServletRequest req) {
		System.out.println("potvrdiRezervacija()");
		
		/*Agent agent = agentService.findOne();
		if (agent == null) {			
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}*/
		
		//Rezervacija rezervacija = rezervacijaService.findOne(potvrda.getRezervacijaId(), agent.getIdAgenta());
		Rezervacija rezervacija = rezervacijaService.findOne(potvrda.getRezervacijaId());
		if (rezervacija==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		rezervacija.setStatusRezervacije(potvrda.getStatusRezervacije());
		Rezervacija retVal = rezervacijaService.save(rezervacija);
		Singleton.getInstance().getRezZaUpdate().add(retVal);

		return new ResponseEntity<>(new RezervacijaDTO(retVal), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/updatedb", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RezervacijaDTO>> getRezervacijeUpdate(HttpServletRequest req) {
		System.out.println("getRezervacijeUpdate()");
		
		Date oldestDate = rezervacijaService.findOldestDate();
		
		//ako nema nicega u bazi inicijalizuj na najmanju vrednost
		if(oldestDate == null) {
			oldestDate = new Date(0L);
		}
		
		System.out.println("oldestDate(): " + oldestDate.toString());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		String dateString = format.format( oldestDate );
		System.out.println(dateString);
		//String getRezervacijeUrl = "http://reservation-service/reservation-service/agent/update/null";// + dateString;
		
		String getRezervacijeUrl = "http://reservation-service/reservation-service/agent/update/1999-01-01-00-00-00";// + dateString;
		
	    try {
	    	ResponseEntity<List<RezervacijaDTO>> response = restTemplate.exchange(
	    			  getRezervacijeUrl,
	    			  HttpMethod.GET,
	    			  null,
	    			  new ParameterizedTypeReference<List<RezervacijaDTO>>(){});
	        List<RezervacijaDTO> ret = response.getBody();
	        
	        if(ret.isEmpty()) {
	        	return new ResponseEntity<>(HttpStatus.OK);
	        }
	        //cuva se promenjene rezervacije ili povlaci Rezervacije u lokalnu bazu. Ako se nesto novo procitalo vraca true sto klijentu
	        //govori da refresuje view i onda povlaci rezervacije iz lokalne baze
	        rezervacijaService.saveAll(ret);
	        
	        List<RezervacijaDTO> rezList = new ArrayList<>();
	        List<Rezervacija> rezervacije = rezervacijaService.findAll();
	        for (Rezervacija rezervacija : rezervacije) {
	        	rezList.add(new RezervacijaDTO(rezervacija));
			}
	        
	        return new ResponseEntity<>(rezList, HttpStatus.OK);
	        
	    } catch (Exception e) {
	    	e.printStackTrace();
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
		
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LokalneRezervacijeDTO>> sendRezervacijeUpdate(HttpServletRequest req) {
		System.out.println("sendRezervacijeUpdate()");
		
		String url = "https://reservation-service/agent/update"; 
		//RestTemplate restTemplate = config.createRestTemplate();
		
		List<LokalneRezervacijeDTO> send = new ArrayList<>();
		List<Rezervacija> zaUpdate = Singleton.getInstance().getRezZaUpdate();
		for (Rezervacija rezervacija : zaUpdate) {
			send.add(new LokalneRezervacijeDTO(rezervacija, rezervacija.getRezervacijaId()));
		}
		Singleton.getInstance().getRezZaUpdate().clear();
		
		try {
	    	//String body = IOUtils.toString(req.getInputStream(), Charset.forName(req.getCharacterEncoding()));
	    	HttpEntity<Object> requestEntity = new HttpEntity<Object>(send);
	        ResponseEntity<List<LokalneRezervacijeDTO>> exchange = restTemplate.exchange(url,
	        		HttpMethod.POST,
	        		requestEntity,
	        		new ParameterizedTypeReference<List<LokalneRezervacijeDTO>>() {});

	        send.clear();

	        //Obavestavamo client o rezervacijama koje nisu prosle i zahtevamo updejt lokalne baze i refresh stranice
	        for (LokalneRezervacijeDTO rez : exchange.getBody()) {
				if(rez.getGlobalniId() == null) {
					send.add(rez);
				}
			}
	        return new ResponseEntity<List<LokalneRezervacijeDTO>>(send, exchange.getStatusCode());
	    } catch (Exception e) {
	        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	}
	
}
