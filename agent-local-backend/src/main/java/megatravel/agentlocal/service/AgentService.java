package megatravel.agentlocal.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import megatravel.agentlocal.model.AgentModel;
import megatravel.agentlocal.repository.AgentRepository;
import megatravel.agentlocal.token.JwtTokenUtils;
import megatravel.agentlocal.validation.CustomException;

@Service
public class AgentService {
	
	@Autowired
	AgentRepository agentRepository;
	
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
	
	public AgentModel findByEmail(String email) {
		return agentRepository.findByEmail(email);
	}
	
	public List<AgentModel> findAll() {
		return agentRepository.findAll();
	}

	public AgentModel findOne(Long id) {
		return agentRepository.findOne(id);
	}

	public AgentModel save(AgentModel korisnik) {
		return agentRepository.save(korisnik);
	}

	public void remove(Long id) {
		agentRepository.delete(id);
	}

	public AgentModel signup(AgentModel korisnik) {
		if (!agentRepository.existsByEmail(korisnik.getEmail())) {
			korisnik.setLozinka(passwordEncoder.encode(korisnik.getLozinka()));
			return agentRepository.save(korisnik);
		} else {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
}
