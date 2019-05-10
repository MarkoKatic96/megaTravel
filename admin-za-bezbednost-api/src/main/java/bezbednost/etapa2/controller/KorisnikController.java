package bezbednost.etapa2.controller;

import java.time.Instant;
import java.util.ArrayList;
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

import bezbednost.etapa2.dto.KorisnikDTO;
import bezbednost.etapa2.dto.KorisnikPrijavaDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;
import bezbednost.etapa2.repository.KorisnikRepository;
import bezbednost.etapa2.service.KorisnikService;
import bezbednost.etapa2.service.RolaService;
import bezbednost.etapa2.service.ServisService;
import bezbednost.etapa2.token.JwtTokenUtils;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private ServisService servisService;
	
	@Autowired
	private RolaService rolaService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	Logger logger = LogManager.getLogger(KorisnikController.class);
	
	@RequestMapping("api/getAllUsers")
	public ResponseEntity<List<Korisnik>> getAllUsers(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), req.getRemoteAddr(), req.getMethod());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getAllUsers")) {
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

				return new ResponseEntity<List<Korisnik>>(korisnikService.getAllUsers(), HttpStatus.OK);
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getAllUsers"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getUserById/{email}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable String email, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), req.getRemoteAddr(), req.getMethod());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getUserById")) {
				Korisnik r = korisnikService.findByEmail(email + ".com");
				if(r!= null) {
					if(req.getHeader("X-FORWARDED-FOR")==null)	
						logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
					else
						logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

					return new ResponseEntity<Korisnik>(r, HttpStatus.OK);
				}
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getUserById"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/dodajRolu/{email}/{id}")
	public ResponseEntity<Korisnik> dodajRolu(@PathVariable("email") String email, @PathVariable("id") Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), req.getRemoteAddr(), req.getMethod(), email, id);
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, id);

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("dodajRolu")) {
				Korisnik k = korisnikService.dodajRolu(email, id);
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, id);
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, id);

				return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, id);
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} dodati rolu {}", servisService.getServisByName("dodajRolu"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, id);

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/ukloniRolu/{email}/{id}")
	public ResponseEntity<Korisnik> ukloniRolu(@PathVariable("email") String email, @PathVariable("id") Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), req.getRemoteAddr(), req.getMethod(), email, id);
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, id);

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("ukloniRolu")) {
				Korisnik k = korisnikService.ukloniRolu(email, id);
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, id);
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, id);

				return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, id);
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Korisniku {} ukloniti rolu {}", servisService.getServisByName("ukloniRolu"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, id);

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getRolesFromUser/{username}")
	public ResponseEntity<List<Rola>> getAllRolesFromUser(@PathVariable("username") String email, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		Korisnik k = korisnikService.findByEmail(email + ".com");
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), req.getRemoteAddr(), req.getMethod());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getRolesFromUser")) {
		
				List<Rola> lista = korisnikService.getRoleFromUser(k);
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

				return new ResponseEntity<List<Rola>>(lista, HttpStatus.OK);
				
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRolesFromUser"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	} 
	
	@RequestMapping("api/getRezervation")
	public ResponseEntity<String> rezervacije(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), req.getRemoteAddr(), req.getMethod());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getRezervation")) {
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

				return new ResponseEntity<String>(korisnikService.rezervacija(), HttpStatus.OK);
		
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod());
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {}", servisService.getServisByName("getRezervation"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod());

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/createKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> createKorisnik(@RequestBody KorisnikDTO dto, HttpServletRequest req){
		
				String username = dto.getUsername();
				String password = dto.getPassword();
				Korisnik k = korisnikService.createKorisnik(new Korisnik(username, password));
				if(k!=null) {
					if(req.getHeader("X-FORWARDED-FOR")==null)
						logger.info("Success - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("register"), req.getRemoteAddr(), req.getMethod(), dto.getUsername());
					else
						logger.info("Success - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("register"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), dto.getUsername());

					return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
				}else {
					if(req.getHeader("X-FORWARDED-FOR")==null)
						logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("register"), req.getRemoteAddr(), req.getMethod(), dto.getUsername());
					else
						logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("register"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), dto.getUsername());

					return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
				}
	}
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody KorisnikPrijavaDTO dto, HttpServletRequest req) {
		String jwt = korisnikService.login(dto);
		if(jwt != null) {
			if(req.getHeader("X-FORWARDED-FOR")!=null)
				logger.info("Success - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("login"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), dto.getEmail());
			else
				logger.info("Success - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("login"), req.getRemoteAddr(), req.getMethod(), dto.getEmail());
			return new ResponseEntity<String>(jwt, HttpStatus.OK);
		}else {
			if(req.getHeader("X-FORWARDED-FOR")!=null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("login"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), dto.getEmail());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Username: {}", servisService.getServisByName("login"), req.getRemoteAddr(), req.getMethod(), dto.getEmail());
			return new ResponseEntity<String>(jwt, HttpStatus.OK);
		}
	}

}