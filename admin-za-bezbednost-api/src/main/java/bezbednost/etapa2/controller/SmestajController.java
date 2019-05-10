package bezbednost.etapa2.controller;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import bezbednost.etapa2.dto.SmestajDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;
import bezbednost.etapa2.model.SmestajModel;
import bezbednost.etapa2.service.KorisnikService;
import bezbednost.etapa2.service.ServisService;
import bezbednost.etapa2.service.SmestajService;
import bezbednost.etapa2.token.JwtTokenUtils;

@RestController
@CrossOrigin(origins = "https://localhost:3000")
public class SmestajController {
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	KorisnikService korisnikService;
	
	@Autowired
	private ServisService servisService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	Logger logger = LogManager.getLogger(KorisnikController.class);
	
	@RequestMapping(value = "api/smestaj/synchronize/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajDTO>> getSynchronized(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("LOCAL: getSynchronized()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<SmestajDTO> loggedL = null;
		
		return new ResponseEntity<>(loggedL, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<SmestajModel>> getAllSmestaji(HttpServletRequest req) {
		
		return new ResponseEntity<List<SmestajModel>>(smestajService.findAll(), HttpStatus.OK);

	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajDTO> getSmestaj(@PathVariable Long id, HttpServletRequest req) {
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		SmestajModel smestaj = smestajService.findOne(id);
		if (smestaj==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
		}
		
		SmestajDTO smestajDTO = new SmestajDTO(smestaj);
		
		if (smestajDTO.getVlasnik().getId()!=korisnik.getId()) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		return new ResponseEntity<>(smestajDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/smestaj", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SmestajDTO> create(@RequestBody SmestajDTO smestajDTO, HttpServletRequest req) {
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), req.getRemoteAddr(), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		
		for (Servis servis : listaServisa) {
			if(servis.getNaziv().equals("createSmestaj")) {
				SmestajModel smestaj = new SmestajModel(smestajDTO.getId(), smestajDTO.getAdresa(), korisnik, smestajDTO.getCena(), smestajDTO.getOpis(), smestajDTO.getMaxOsoba());
				smestaj = smestajService.save(smestaj);
				if(req.getHeader("X-FORWARDED-FOR")==null)
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());
				else
					logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());

				return new ResponseEntity<>(new SmestajDTO(smestaj), HttpStatus.CREATED);
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Adresa: {} - Cena: {} - MaxOsoba: {} - Opis: {}", servisService.getServisByName("createSmestaj"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), email, smestajDTO.getAdresa(), smestajDTO.getCena(), smestajDTO.getMaxOsoba(), smestajDTO.getOpis());

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);		
		
	}
	
	@RequestMapping(value = "api/smestaj/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Long id, HttpServletRequest req) {
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		Korisnik korisnik = korisnikService.findByEmail(email);
		if (korisnik == null) {
			if(req.getHeader("X-FORWARDED-FOR")==null)
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Obrisi smestaj {}", servisService.getServisByName("deleteSmestaj"), req.getRemoteAddr(), req.getMethod(), id);
			else
				logger.error("Failed - ProcessID: {} - IPAddress: {} - Type: {} - Obrisi smestaj {}", servisService.getServisByName("deleteSmestaj"), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), id);

			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<Rola> listaRola = (List<Rola>) korisnik.getRoles();
		List<Servis> listaServisa = new ArrayList<Servis>();
		for (Rola rola : listaRola) {
			listaServisa.addAll(rola.getServisi());
		}
		SmestajModel s = smestajService.findOne(id);
		String vlasnikId = s.getVlasnik().getUsername();
		if(korisnik.getUsername().equals(vlasnikId)) {
			for (Servis servis : listaServisa) {
				if(servis.getNaziv().equals("deleteSmestaj")) {
					smestajService.remove(id);
					if(req.getHeader("X-FORWARDED-FOR")==null)
						logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Obrisi smestaj {}", servisService.getServisByName("deleteSmestaj"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), id);
					else
						logger.info("Success - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - Obrisi smestaj {}", servisService.getServisByName("deleteSmestaj"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), id);

					return new ResponseEntity<>(HttpStatus.OK);
				}
			}
		}
		if(req.getHeader("X-FORWARDED-FOR")==null)
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - SmestajID {}", servisService.getServisByName("deleteSmestaj"), korisnik.getUsername(), req.getRemoteAddr(), req.getMethod(), id);
		else
			logger.error("Failed - ProcessID: {} - UserID: {} - IPAddress: {} - Type: {} - SmestajID {}", servisService.getServisByName("deleteSmestaj"), korisnik.getUsername(), req.getHeader("X-FORWARDED-FOR"), req.getMethod(), id);

		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
	}
	
	@RequestMapping("api/getSmestajiByOwner")
	public ResponseEntity<List<SmestajModel>> getSmestajiByOwner(HttpServletRequest req){
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
			if(servis.getNaziv().equals("getSmestajiByOwner")) {
				return new ResponseEntity<List<SmestajModel>>(smestajService.findAllByOwner(korisnik.getUsername()), HttpStatus.OK);
			}
			
		}
		return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);		
			
	}
}
