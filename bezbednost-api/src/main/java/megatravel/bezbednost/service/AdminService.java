package megatravel.bezbednost.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.repository.AdminRepository;
import megatravel.bezbednost.token.JwtTokenUtils;
import megatravel.bezbednost.validation.CustomException;

@Service
public class AdminService {
	
	@Autowired
	AdminRepository adminRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
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
	
	public AdminModel findByEmail(String email) {
		return adminRepository.findByEmail(email);
	}
	
	public Page<AdminModel> findAll(Pageable page) {
		return adminRepository.findAll(page);
	}

	public AdminModel findOne(Long id) {
		return adminRepository.findOne(id);
	}

	public AdminModel save(AdminModel korisnik) {
		return adminRepository.save(korisnik);
	}

	public void remove(Long id) {
		adminRepository.delete(id);
	}
}
