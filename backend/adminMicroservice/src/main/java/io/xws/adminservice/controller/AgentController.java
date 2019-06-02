package io.xws.adminservice.controller;

import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.xws.adminservice.dto.AgentDTO;
import io.xws.adminservice.dto.NeregistrovaniAgentDTO;
import io.xws.adminservice.service.IAgentService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/agent")
@Api(tags = "")
public class AgentController 
{
	@Autowired
	private IAgentService agentService;
	
	/*
	 * Vraca sve agente koji su poslali zahtev (cekaju na odobrenje admina)
	 * To znamo na osnovu toga sto im je lozinka prazno polje
	 */
	@GetMapping("/allrequests")
	public ResponseEntity<List<AgentDTO>> getAllZahteviAgenata()
	{
		System.out.println("getAllZahteviAgenata()");
		
		List<AgentDTO> agenti = agentService.getAllZahteviAgenata();
		
		return (agenti.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<AgentDTO>>(agenti, HttpStatus.OK);
	}
	
	/*
	 * Registrovanje novog agenta tj. dodavanje u sistem
	 * Zbog ispisa poruke na view moze se prebaciti da vraca string, mada treba na agentu da se proveri prvo
	 */
	@PostMapping("/confirmrequest")
	public ResponseEntity<Boolean> createPotvrdiZahtev(@RequestBody NeregistrovaniAgentDTO zahtev)
	{
		System.out.println("createPotvrdiZahtev()");
		
		return (!agentService.createPotvrdiZahtev(zahtev)) ? new ResponseEntity<Boolean>(false, HttpStatus.CONFLICT) : new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
		
	}
	
	/*
	 * Odbijanje zahteva za agenta (brisanje zahteva)
	 */
	@DeleteMapping("/refuserequest")
	public ResponseEntity<Boolean> deleteOdbijZahtev(@RequestBody NeregistrovaniAgentDTO zahtev)
	{
		System.out.println("deleteOdbijZahtev()");
		
		return (!agentService.deleteOdbijZahtev(zahtev)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
}
