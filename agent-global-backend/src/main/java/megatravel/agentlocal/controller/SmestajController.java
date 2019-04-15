package megatravel.agentlocal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.agentlocal.dto.SmestajDTO;
import megatravel.agentlocal.model.AgentModel;
import megatravel.agentlocal.model.SmestajModel;
import megatravel.agentlocal.service.AgentService;
import megatravel.agentlocal.service.SmestajService;
import megatravel.agentlocal.token.JwtTokenUtils;

@RestController
public class SmestajController {
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "api/smestaj/synchronize", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> getSynchronized(HttpServletRequest req) {
		System.out.println("GLOBAL: getSynchronized()");
		return getAllSmestaji(req);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(HttpServletRequest req) {
		System.out.println("GLOBAL: getAllSmestaji()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<SmestajModel> smestaji = smestajService.findAll(korisnik.getId());
		
		HttpHeaders headers = new HttpHeaders();
		long hoteliTotal = smestaji.size();
		headers.add("X-Total-Count", String.valueOf(hoteliTotal));

		List<SmestajDTO> retVal = new ArrayList<SmestajDTO>();

		for (SmestajModel s : smestaji) {
			retVal.add(new SmestajDTO(s));
		}

		return new ResponseEntity<>(retVal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("GLOBAL: getSmestaj()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = smestajService.findOne(id);
		if (smestaj==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		SmestajDTO smestajDTO = new SmestajDTO(smestaj);
		
		if (smestajDTO.getVlasnik().getId()!=korisnik.getId()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(smestajDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> create(@RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		System.out.println("GLOBAL: create()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = new SmestajModel(smestajDTO.getId(), smestajDTO.getAdresa(), korisnik, smestajDTO.getCena(), smestajDTO.getOpis(), smestajDTO.getMaxOsoba());
		smestaj = smestajService.save(smestaj);
		
		korisnik.addSmestaj(smestaj);
		agentService.save(korisnik);

		return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("GLOBAL: delete()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = smestajService.findOne(id);
		if (smestaj != null) {
			
			if (smestaj.getVlasnik().getId()!=korisnik.getId()) {
				return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
			}
			
			smestajService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
			
		} 
		else 
		{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
