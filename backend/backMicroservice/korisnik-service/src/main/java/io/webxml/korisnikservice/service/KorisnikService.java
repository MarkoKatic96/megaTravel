package io.webxml.korisnikservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.webxml.korisnikservice.jwt.JwtTokenUtils;
import io.webxml.korisnikservice.model.Korisnik;
import io.webxml.korisnikservice.repository.KorisnikRepository;

@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private JwtTokenUtils jwtTokenProvider;
	
	public List<Korisnik> getAllKorisnici(){
		List<Korisnik> returnList = korisnikRepository.findAllKorisnici();
		return returnList;
	}
	
	public Korisnik getKorisnikById(Long id) {
		Optional<Korisnik> k = korisnikRepository.findById(id);
		if(k.isPresent()) {
			return k.get();
		}
		return null;
	}
	
	public Korisnik getKorisnikByEmail(String email) {
		Korisnik k = korisnikRepository.nadjiKorisnikaPoEmail(email);
		return k;
	}
	
	public Korisnik register(Korisnik korisnik) {
		Korisnik k = korisnikRepository.nadjiKorisnikaPoEmail(korisnik.getEmail());
		if(k==null) {
			korisnik.setRola("KORISNIK");
			return korisnikRepository.save(korisnik);
		}
		return null;
	}
	
	public String login(String email, String lozinka) {
		Korisnik k = korisnikRepository.nadjiKorisnikaPoEmail(email);
		if(k==null) {
			return null;
		}
		try {
			if(k.getLozinka().equals(lozinka)) {
				String jwt = jwtTokenProvider.createToken(email);
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(jwt);
			}
		}catch (Exception e) {
			return null;
		}
		
		return null;
	}
	
	/*public String signin(String email, String lozinka) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, lozinka));
			return jwtTokenProvider.createToken(email);
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}*/
	
}
