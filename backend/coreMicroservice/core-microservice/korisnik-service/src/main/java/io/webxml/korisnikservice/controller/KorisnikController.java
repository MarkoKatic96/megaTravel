package io.webxml.korisnikservice.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.webxml.korisnikservice.jwt.JwtTokenUtils;
import io.webxml.korisnikservice.model.Korisnik;
import io.webxml.korisnikservice.model.Login;
import io.webxml.korisnikservice.service.KorisnikService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/korisnik-service")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping("/korisnici")
	public ResponseEntity<List<Korisnik>> getKorisnici(){
		
		List<Korisnik> korisnici = korisnikService.getAllKorisnici();
		return (korisnici!=null) ? new ResponseEntity<List<Korisnik>>(korisnici, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/korisnici/{id}")
	public ResponseEntity<Korisnik> getKorisnik(@PathVariable("id") Long id){
		Korisnik k = korisnikService.getKorisnikById(id);
		return (k!=null) ? new ResponseEntity<Korisnik>(k, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping("/korisnik/{email}")
	public ResponseEntity<Korisnik> getKorisnikByEmail(@PathVariable("email") String email){
		Korisnik k = korisnikService.getKorisnikByEmail(email);
		return (k!=null) ? new ResponseEntity<Korisnik>(k, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Korisnik> register(@RequestBody Korisnik korisnik){
		Korisnik k = korisnikService.register(korisnik);
		return (k!=null) ? new ResponseEntity<Korisnik>(k, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody Login login){
		String email = login.getEmail();
		String lozinka = login.getPassword();
		String jwt = korisnikService.login(email, lozinka);
		return (jwt!=null) ? new ResponseEntity<String>(jwt, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/getKorisnikByToken/{token}")
	public ResponseEntity<Korisnik> getKorisnikByToken(@PathVariable("token") String token){
		
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.getKorisnikByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		return new ResponseEntity<Korisnik>(korisnik, HttpStatus.OK);
	}
	
}
