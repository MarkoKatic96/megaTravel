package bezbednostEtapa2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import bezbednostEtapa2.dto.KorisnikPrijavaDTO;
import bezbednostEtapa2.dto.KorisnikRegistracijaDTO;
import bezbednostEtapa2.model.Korisnik;
import bezbednostEtapa2.service.KorisnikService;
import bezbednostEtapa2.token.JwtTokenUtils;


@RestController
public class KorisnikController {
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody KorisnikPrijavaDTO agentPrijavaDTO) {
		System.out.println("login()");
		
		Korisnik korisnik = korisnikService.findByEmail(agentPrijavaDTO.getEmail());
		if(korisnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!korisnik.isAktiviranNalog()) {
			// ne moze da se uloguje posto nije aktiviran mail
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			String jwt = korisnikService.signin(agentPrijavaDTO.getEmail(), agentPrijavaDTO.getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	@RequestMapping(value = "api/signup", method = RequestMethod.POST)
	public ResponseEntity<String> signup(@RequestBody KorisnikRegistracijaDTO agentDTO) {
		
		Korisnik tempKorisnik = korisnikService.findByEmail(agentDTO.getEmail());
		if(tempKorisnik != null) {
			//mora biti jedinstveni mail za korisnika
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Korisnik korisnik = new Korisnik(null, agentDTO.getIme(), agentDTO.getPrezime(), agentDTO.getPoslovniMaticniBroj(), null, agentDTO.getLozinka(), agentDTO.getEmail(), true);
		
		try{
			Korisnik retValue = korisnikService.signup(korisnik);
			if (retValue!=null) {
				return login(new KorisnikPrijavaDTO(retValue.getEmail(), retValue.getLozinka()));
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}

		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
