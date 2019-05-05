package bezbednost.etapa2.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

import bezbednost.etapa2.dto.RolaDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;
import bezbednost.etapa2.service.KorisnikService;
import bezbednost.etapa2.service.RolaService;
import bezbednost.etapa2.token.JwtTokenUtils;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
public class RolaController {
	
	@Autowired
	private RolaService rolaService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	Logger logger = LogManager.getLogger(RolaController.class);
	
	
	@RequestMapping("api/getAllRoles")
	public ResponseEntity<List<Rola>> getAllRoles(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);	
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());	
		String ipAddress = req.getRemoteAddr();
		logger.info("Metoda: getAllRoles (Dobijanje svih rola),  TimeStamp: "+ timeStamp + ",  Source: " + korisnik.getUsername() + ", IP Address: "+ ipAddress +", Success");
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();//lista rola ulogovanog korisnika
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());//uzimamo sve servise svake role
		}
		for (Servis servis : listaServisa) {//prolazimo kroz servise i proveravamo da li ulogovani korisnik ima pravo da izvrsi dati servis
			if(servis.getNaziv().equals("getAllRoles")) {
				return new ResponseEntity<List<Rola>>(rolaService.getAllRoles(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getRoleById/{id}")
	public ResponseEntity<Rola> getRolaById(@PathVariable Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getRolaById")) {
				Rola r = rolaService.getRolaById(id);
				return new ResponseEntity<Rola>(r, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/dodajServis/{rid}/{sid}")
	public ResponseEntity<Rola> dodajServis(@PathVariable("rid") Long rolaId, @PathVariable("sid") Long servisId, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("dodajServis")) {
				Rola r = rolaService.dodajServis(rolaId, servisId);
				return new ResponseEntity<Rola>(r, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/ukloniServis/{rid}/{sid}")
	public ResponseEntity<Rola> ukloniServis(@PathVariable("rid") Long rolaId, @PathVariable("sid") Long servisId, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("ukloniServis")) {
				Rola r = rolaService.ukloniServis(rolaId, servisId);
				return new ResponseEntity<Rola>(r, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/deleteRole/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteRola(@PathVariable Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("deleteRole")) {
				String poruka = rolaService.deleteRola(id);
				return new ResponseEntity<String>(poruka, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/createRole", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Rola> createRola(@RequestBody RolaDTO dto, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("createRole")) {
				String naziv = dto.getNaziv();
				Rola r = rolaService.createRola(new Rola(naziv));
				return new ResponseEntity<Rola>(r, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getServiseRole/{rid}")
	public ResponseEntity<List<Servis>> getServiseRole(@PathVariable("rid") Long rid, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getServiseRole")) {
				List<Servis> lista = rolaService.getServiseRole(rid);
				return new ResponseEntity<List<Servis>>(lista, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		
	}
	
}