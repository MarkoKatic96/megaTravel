package megatravel.bezbednost.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.model.CertificateModel;
import megatravel.bezbednost.service.AdminService;
import megatravel.bezbednost.service.CertificateService;
import megatravel.bezbednost.token.JwtTokenUtils;

@RestController
public class CertificateController {
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	CertificateService certificateService;
	
	@RequestMapping(value = "api/certificate/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<CertificateModel> getCertifikatById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getCertifikatById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		
		if (cert==null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		return new ResponseEntity<>(cert, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/x509/{id}", method = RequestMethod.GET)
	public ResponseEntity<X509Certificate> getX509CertificateById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getX509CertificateById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		if (cert==null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		
		X509Certificate certificate;
		try {
			certificate = getX509Certificate(cert.getCertifikat());
			return new ResponseEntity<>(certificate, HttpStatus.OK);
		} catch (CertificateException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		}
		
		return new ResponseEntity<>(null, HttpStatus.I_AM_A_TEAPOT);
	}
	
	private X509Certificate getX509Certificate(byte[] certifikat) throws CertificateException {
		CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
		InputStream in = new ByteArrayInputStream(certifikat);
		X509Certificate cert = (X509Certificate)certFactory.generateCertificate(in);
		return cert;
	}
	
	@RequestMapping(value = "api/certificate/sn/{id}", method = RequestMethod.GET)
	public ResponseEntity<CertificateModel> getCertifikatBySN(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("getCertifikatBySN()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findBySerijskiBroj(id);
		
		return new ResponseEntity<>(cert, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/certificate/id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("removeById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findOne(id);
		if (cert != null) {
			certificateService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value = "api/certificate/sn/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeBySN(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("removeBySN()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateModel cert = certificateService.findBySerijskiBroj(id);
		if (cert != null) {
			certificateService.remove(cert.getId());
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value = "api/certificate/exists/{id}", method = RequestMethod.GET)
	public ResponseEntity<Void> exists(@PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("exists()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		boolean exists = certificateService.existsBySerijskiBroj(id);
		if (exists) {
			return new ResponseEntity<>(HttpStatus.FOUND);
		} else {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	@RequestMapping(value = "api/certificate/subcert/{id}", method = RequestMethod.GET, produces = {	MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CertificateModel>> getSubCertificates(Pageable page, @PathVariable BigInteger id, HttpServletRequest req) {
		System.out.println("getSubCertificates()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		final Page<CertificateModel> certifikati = certificateService.findSubCertifikates(id, page);
		
		HttpHeaders headers = new HttpHeaders();
		long certTotal = certifikati.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(certTotal));

		List<CertificateModel> lista = new ArrayList<>();
		for (CertificateModel c : certifikati) {
			lista.add(c);
		}
		
		return new ResponseEntity<>(lista, headers, HttpStatus.OK);
	}
	
}
