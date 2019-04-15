package bezbednostEtapa2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import bezbednostEtapa2.model.Korisnik;
import bezbednostEtapa2.repository.KorisnikRepository;
import bezbednostEtapa2.token.JwtTokenUtils;
import bezbednostEtapa2.validation.CustomException;



@Service
public class KorisnikService {
	
	@Autowired
	KorisnikRepository korisnikRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtTokenUtils jwtTokenProvider;
	
	public String signin(String email, String lozinka) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, lozinka));
			return jwtTokenProvider.createToken(email);
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public Korisnik findByEmail(String email) {
		return korisnikRepository.findByEmail(email);
	}
	
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	public Korisnik findOne(Long id) {
		return korisnikRepository.findOne(id);
	}

	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

	public void remove(Long id) {
		korisnikRepository.delete(id);
	}

	public Korisnik signup(Korisnik korisnik) {
		if (!korisnikRepository.existsByEmail(korisnik.getEmail())) {
			korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
			return korisnikRepository.save(korisnik);
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
