package megatravel.agentlocal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import megatravel.agentlocal.dto.AgentPrijavaDTO;
import megatravel.agentlocal.dto.AgentRegistracijaDTO;
import megatravel.agentlocal.model.AgentModel;
import megatravel.agentlocal.service.AgentService;
import megatravel.agentlocal.token.JwtTokenUtils;

@RestController
public class AgentController {
	@Autowired
	AgentService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody AgentPrijavaDTO agentPrijavaDTO) {
		System.out.println("login()");
		
		AgentModel korisnik = agentService.findByEmail(agentPrijavaDTO.getEmail());
		if(korisnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!korisnik.isAktiviranNalog()) {
			// ne moze da se uloguje posto nije aktiviran mail
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			String jwt = agentService.signin(agentPrijavaDTO.getEmail(), agentPrijavaDTO.getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "api/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody AgentRegistracijaDTO agentDTO) {
		
		AgentModel tempKorisnik = agentService.findByEmail(agentDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		AgentModel korisnik = new AgentModel(null, agentDTO.getIme(), agentDTO.getPrezime(), agentDTO.getPoslovniMaticniBroj(), null, agentDTO.getLozinka(), agentDTO.getEmail(), true);
		
		try{
			AgentModel retValue = agentService.signup(korisnik);
			if (retValue!=null) {
				return login(new AgentPrijavaDTO(retValue.getEmail(), retValue.getLozinka()));
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
