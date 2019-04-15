package bezbednostEtapa2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bezbednostEtapa2.dto.SmestajDTO;
import bezbednostEtapa2.model.Korisnik;
import bezbednostEtapa2.model.Smestaj;
import bezbednostEtapa2.service.KorisnikService;
import bezbednostEtapa2.service.SmestajService;
import bezbednostEtapa2.token.JwtTokenUtils;


public class SmestajController {
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	KorisnikService agentService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	
	@RequestMapping(value = "api/smestaj/welcome", method = RequestMethod.GET, produces = {	MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<String> getWelcomeMessage(HttpServletRequest req) {
		System.out.println("getWelcomeMessage()");
		return new ResponseEntity<>("<html><body>Hello world</body></html>", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET, produces = { MediaType.TEXT_HTML_VALUE })
	public ResponseEntity<String> getErrorMessage(HttpServletRequest req) {
		System.out.println("getErrorMessage()");
		return new ResponseEntity<>("<html><body>Bad error, very bad :(</body></html>", HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmestajDTO>> getAllSmestaji(HttpServletRequest req) {
		System.out.println("getAllSmestaji()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		
		List<Smestaj> smestaji = smestajService.findAll();
		
		
		HttpHeaders headers = new HttpHeaders();
		long hoteliTotal = smestaji.size();
		headers.add("X-Total-Count", String.valueOf(hoteliTotal));

		List<SmestajDTO> retVal = new ArrayList<SmestajDTO>();

		for (Smestaj s : smestaji) {
			retVal.add(new SmestajDTO(s));
		}

		return new ResponseEntity<>(retVal, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getSmestaj()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = agentService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		Smestaj smestaj = smestajService.findOne(id);
		if (smestaj==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		SmestajDTO smestajDTO = new SmestajDTO(smestaj);
		
		return new ResponseEntity<>(smestajDTO, HttpStatus.OK);
	}
}
