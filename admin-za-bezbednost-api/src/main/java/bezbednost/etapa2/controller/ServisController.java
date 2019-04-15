package bezbednost.etapa2.controller;

import java.util.ArrayList;
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

import bezbednost.etapa2.dto.ServisDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;
import bezbednost.etapa2.service.KorisnikService;
import bezbednost.etapa2.service.ServisService;
import bezbednost.etapa2.token.JwtTokenUtils;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
public class ServisController {

	@Autowired
	private ServisService servisService;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping("api/getAllServices")
	public ResponseEntity<List<Servis>> getAllServices(HttpServletRequest req){
		
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
			if(servis.getNaziv().equals("api/getAllServices")) {*/
				return new ResponseEntity<List<Servis>>(servisService.getAllServices(), HttpStatus.OK);
			/*}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);*/
	}
	
	@RequestMapping("api/getAllServices/{id}")
	public ResponseEntity<List<Servis>> getAllServicesRola(@PathVariable("id") Long id, HttpServletRequest req){
		return new ResponseEntity<List<Servis>>(servisService.getAllServicesRole(id), HttpStatus.OK);
	}
	
	@RequestMapping("api/getServiceById/{id}")
	public ResponseEntity<Servis> getServisById(@PathVariable Long id, HttpServletRequest req){
		
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
			if(servis.getNaziv().equals("api/getServiceById/{id}")) {
				Servis s = servisService.getServisById(id);
				return new ResponseEntity<Servis>(s, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/deleteService/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteServis(@PathVariable Long id, HttpServletRequest req){
		
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
			if(servis.getNaziv().equals("api/deleteService/{id}")) {
				String poruka = servisService.deleteServis(id);
				return new ResponseEntity<String>(poruka, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping(value = "api/createService", method = RequestMethod.POST)
	public ResponseEntity<Servis> createService(@RequestBody ServisDTO dto, HttpServletRequest req){
		
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
			if(servis.getNaziv().equals("api/createService")) {
				String naziv = dto.getNaziv();
				Servis s = servisService.createServis(new Servis(naziv));
				return new ResponseEntity<Servis>(s, HttpStatus.OK);
			}
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
}
