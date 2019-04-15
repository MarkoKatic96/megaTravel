package megatravel.agentlocal.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import megatravel.agentlocal.dto.SmestajDTO;
import megatravel.agentlocal.https.ssl.SSLMutualAuth;
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
	
	@RequestMapping(value = "api/smestaj/synchronize/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> getSynchronized(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("LOCAL: getSynchronized()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<SmestajDTO> loggedL = null;
		try {
			loggedL = SSLMutualAuth.callGet("https://localhost:8443/api/smestaj/synchronize", "Bearer " + token , SmestajDTO.class, true);
		} catch (Exception e) {
			if (e.getMessage().contains("403")) {
				return new ResponseEntity<>(HttpStatus.FORBIDDEN);
			}
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		if (!loggedL.isEmpty()) {
			for (SmestajModel smestajDTO : smestajService.findAll()) {
				smestajService.remove(smestajDTO.getId());
			}
			
			for (SmestajDTO smestajDTO : loggedL) {
				smestajService.save(new SmestajModel(smestajDTO.getId(), smestajDTO.getAdresa(), new AgentModel(smestajDTO.getVlasnik().getId(), smestajDTO.getVlasnik().getIme(), smestajDTO.getVlasnik().getPrezime(), smestajDTO.getVlasnik().getPoslovniMaticniBroj(), smestajDTO.getVlasnik().getDatumClanstva(), korisnik.getLozinka(), smestajDTO.getVlasnik().getEmail(), smestajDTO.getVlasnik().isAktiviranNalog()), smestajDTO.getCena(), smestajDTO.getOpis(), smestajDTO.getMaxOsoba()));
			}
		}
		
		return new ResponseEntity<>(loggedL, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(HttpServletRequest req) {
		System.out.println("LOCAL: getAllSmestaji()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<SmestajDTO> loggedL = null;
		try {
			loggedL = SSLMutualAuth.callGet("https://localhost:8443/api/smestaj", "Bearer " + token, SmestajDTO.class, true);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(loggedL, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("LOCAL: getSmestaj()");
		
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
		System.out.println("LOCAL: create()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = new SmestajModel(smestajDTO.getId(), smestajDTO.getAdresa(), korisnik, smestajDTO.getCena(), smestajDTO.getOpis(), smestajDTO.getMaxOsoba());
		
		List<SmestajDTO> loggedL = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			loggedL = SSLMutualAuth.callPost("https://localhost:8443/api/signup", "Bearer " + token, mapper.writeValueAsString(smestajDTO), SmestajDTO.class, false);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		SmestajDTO logged = loggedL.get(0);
		if (logged==null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		smestaj = smestajService.save(smestaj);
		
		korisnik.addSmestaj(smestaj);
		agentService.save(korisnik);

		return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("LOCAL: delete()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AgentModel korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		try {
			SSLMutualAuth.callDelete("https://localhost:8443/api/smestaj/" + id, "Bearer " + token, "", Void.class, false);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		smestajService.remove(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
