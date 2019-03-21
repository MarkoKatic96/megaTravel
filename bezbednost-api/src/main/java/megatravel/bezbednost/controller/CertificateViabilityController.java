package megatravel.bezbednost.controller;

import java.math.BigInteger;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import megatravel.bezbednost.dto.CertificateViabilityDTO;
import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.model.CertificateViabilityModel;
import megatravel.bezbednost.model.StatusCertifikata;
import megatravel.bezbednost.service.AdminService;
import megatravel.bezbednost.service.CertificateViabilityService;
import megatravel.bezbednost.token.JwtTokenUtils;

@RestController
public class CertificateViabilityController {
	
	@Autowired
	CertificateViabilityService certificateViabilityService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/api/certificate/viability/{serijskiBroj}", method = RequestMethod.GET)
	public ResponseEntity<String> getStatus(@PathVariable("serijskiBroj") BigInteger serijskiBroj, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		StatusCertifikata status = certificateViabilityService.getStatus(serijskiBroj);
		if (status==null) {
			return new ResponseEntity<String>("", HttpStatus.OK);
		}
		return new ResponseEntity<String>(status.toString(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability/all", method = RequestMethod.GET)
	public ResponseEntity<List<CertificateViabilityModel>> getAllStatus(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<CertificateViabilityModel> statusi = certificateViabilityService.findAll();
		return new ResponseEntity<List<CertificateViabilityModel>>(statusi, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability/allViable", method = RequestMethod.GET)
	public ResponseEntity<List<CertificateViabilityModel>> getAllViable(HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		List<CertificateViabilityModel> statusi = certificateViabilityService.getValidCertificates();
		return new ResponseEntity<List<CertificateViabilityModel>>(statusi, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateViabilityDTO> newStatus(@RequestBody CertificateViabilityDTO commDTO, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		BigInteger sn = commDTO.getSerijskiBroj();
		StatusCertifikata sc = commDTO.getStatus();
		CertificateViabilityModel noviStatus = certificateViabilityService.newStatus(new CertificateViabilityModel(sn, sc));
		return new ResponseEntity<CertificateViabilityDTO>(new CertificateViabilityDTO(noviStatus.getId(),noviStatus.getSerijskiBroj(),noviStatus.getStatus()), HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/api/certificate/viability", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateViabilityDTO> editStatus(@RequestBody CertificateViabilityDTO commDTO, HttpServletRequest req){
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		BigInteger sn = commDTO.getSerijskiBroj();
		StatusCertifikata sc = commDTO.getStatus();
		CertificateViabilityModel noviStatus = certificateViabilityService.editStatus(sn, sc);
		return new ResponseEntity<CertificateViabilityDTO>(new CertificateViabilityDTO(noviStatus.getId(),noviStatus.getSerijskiBroj(),noviStatus.getStatus()), HttpStatus.OK);
		
	}
	
	
	
}
