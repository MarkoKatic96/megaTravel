package bezbednost.etapa2.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
import bezbednost.etapa2.token.JwtTokenUtils;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private RolaService rolaService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping("api/getAllUsers")
	public ResponseEntity<List<Korisnik>> getAllUsers(HttpServletRequest req){
		
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
			if(servis.getNaziv().equals("getAllUsers")) {
				return new ResponseEntity<List<Korisnik>>(korisnikService.getAllUsers(), HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getUserById/{email}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable String email, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
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
				return new ResponseEntity<Korisnik>(r, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/dodajRolu/{email}/{id}")
	public ResponseEntity<Korisnik> dodajRolu(@PathVariable("email") String email, @PathVariable("id") Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
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
				return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	/*@RequestMapping("api/roleKojeNema/{username}")
	public ResponseEntity<List<Rola>> roleKojeNema(@PathVariable("username") String username){
		List<Rola> lista = rolaService.getAllRoles();
		List<Rola> roleKojeNema = new ArrayList<Rola>();
		List<Rola> roleKojeIma = getAllRolesFromUser(username);
		for (Rola rola : lista) {
			if()
		}
	}*/
	
	@RequestMapping("api/ukloniRolu/{email}/{id}")
	public ResponseEntity<Korisnik> ukloniRolu(@PathVariable("email") String email, @PathVariable("id") Long id, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
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
				return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getRolesFromUser/{username}")
	public ResponseEntity<List<Rola>> getAllRolesFromUser(@PathVariable("username") String email, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		Korisnik k = korisnikService.findByEmail(email + ".com");
		if (korisnik == null) {
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
				return new ResponseEntity<List<Rola>>(lista, HttpStatus.OK);
				
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	} 
	
	@RequestMapping("api/getRezervation")
	public ResponseEntity<String> rezervacije(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email1 = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email1);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("getRezervation")) {
		
				return new ResponseEntity<String>(korisnikService.rezervacija(), HttpStatus.OK);
		
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/createKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> createKorisnik(@RequestBody KorisnikDTO dto, HttpServletRequest req){
		
		/*String token = jwtTokenUtils.resolveToken(req);
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
			if(servis.getNaziv().equals("api/createKorisnik")) {*/
				String username = dto.getUsername();
				String password = dto.getPassword();
				Korisnik k = korisnikService.createKorisnik(new Korisnik(username, password));
				/*List<Rola> lista = (List<Rola>) k.getRoles();
				Rola r = rolaService.getRolaById((long) 2);//nije idealno opravicemo
				lista.add(r);
				k.setRoles(lista);
				korisnikRepository.save(k);*/
				return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
			/*}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);*/
	}
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody KorisnikPrijavaDTO dto) {
		String jwt = korisnikService.login(dto);
		return new ResponseEntity<String>(jwt, HttpStatus.OK);
	}

}