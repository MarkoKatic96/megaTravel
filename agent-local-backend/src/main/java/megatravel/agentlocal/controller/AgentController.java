package megatravel.agentlocal.controller;

import java.util.List;

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
import megatravel.agentlocal.https.ssl.SSLMutualAuth;
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
		System.out.println("LOCAL: login()");
		
		List<String> loggedL = null;
		try {
			loggedL = SSLMutualAuth.callPost("https://localhost:8443/api/login", "", new String("{\"email\":\"" +  agentPrijavaDTO.getEmail() + "\",\"lozinka\": \"" + agentPrijavaDTO.getLozinka() + "\"}"), String.class, false);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String logged = loggedL.get(0);
		if (logged.equals("")) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		//logged = "Bearer " + logged.substring(1, logged.length()-1);
		
		return new ResponseEntity<>(logged, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody AgentRegistracijaDTO agentDTO) {
		System.out.println("LOCAL: signup()");
		
		List<String> loggedL = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			loggedL = SSLMutualAuth.callPost("https://localhost:8443/api/signup", "", mapper.writeValueAsString(agentDTO), String.class, false);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		String logged = loggedL.get(0);
		if (logged.equals("")) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		AgentModel korisnik = new AgentModel(null, agentDTO.getIme(), agentDTO.getPrezime(), agentDTO.getPoslovniMaticniBroj(), null, agentDTO.getLozinka(), agentDTO.getEmail(), true);
		korisnik.setDatumClanstva(new java.sql.Date(System.currentTimeMillis()));
		agentService.signup(korisnik);
		
		return new ResponseEntity<>(logged, HttpStatus.CREATED);
	}
}
