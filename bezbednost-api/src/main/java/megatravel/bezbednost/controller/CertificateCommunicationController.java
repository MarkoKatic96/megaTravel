package megatravel.bezbednost.controller;

import java.math.BigInteger;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import megatravel.bezbednost.dto.CertificateCommunicationDTO;
import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.model.CertificateCommunicationModel;
import megatravel.bezbednost.model.CertificateModel;
import megatravel.bezbednost.service.AdminService;
import megatravel.bezbednost.service.CertificateCommunicationService;
import megatravel.bezbednost.service.CertificateService;
import megatravel.bezbednost.token.JwtTokenUtils;

@RestController
public class CertificateCommunicationController {

	@Autowired
	AdminService adminService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@Autowired
	CertificateCommunicationService communicationService;
	
	@Autowired
	CertificateService certificateService;
	
	
	@RequestMapping(value = "api/communication/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<CertificateCommunicationModel> getCommunicationById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("getCommunicationById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateCommunicationModel comm = communicationService.findOne(id);
		
		if (comm==null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}
		return new ResponseEntity<>(comm, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/communication/id/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removeCommunicationById(@PathVariable Long id, HttpServletRequest req) {
		System.out.println("removeCommunicationById()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		CertificateCommunicationModel comm = communicationService.findOne(id);
		if (comm != null) {
			communicationService.remove(id);
			return new ResponseEntity<>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	@RequestMapping(value = "api/communication", method = RequestMethod.GET, params = {"sn1","sn2"})
	public ResponseEntity<Boolean> isCommunicationApproved(@RequestParam("sn1") BigInteger sn1, @RequestParam("sn2") BigInteger sn2, HttpServletRequest req) {
		System.out.println("isCommunicationApproved()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		boolean app = communicationService.communicationApproved(sn1, sn2);
		
		return new ResponseEntity<>(app, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/communication", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CertificateCommunicationModel> setCommunication(@RequestBody CertificateCommunicationDTO commDTO, HttpServletRequest req) {
		System.out.println("setCommunication()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		BigInteger sn1 = commDTO.getSerijskiBroj1();
		BigInteger sn2 = commDTO.getSerijskiBroj2();
		
		boolean app = communicationService.communicationApproved(sn1, sn2);
		if (app) {
			CertificateCommunicationModel comm = communicationService.findBySerialNumbers(sn1, sn2).get(0);
			return new ResponseEntity<>(comm, HttpStatus.OK);
		}
		
		CertificateModel c1 = certificateService.findBySerijskiBroj(sn1);
		CertificateModel c2 = certificateService.findBySerijskiBroj(sn2);
		if (c1 == null || c2 == null) {
			return new ResponseEntity<>(null, HttpStatus.UNPROCESSABLE_ENTITY);
		}

		CertificateCommunicationModel comm = communicationService.save(new CertificateCommunicationModel(sn1,sn2));
		
		return new ResponseEntity<>(comm, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/communication/all", method = RequestMethod.GET, produces = {	MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<List<CertificateCommunicationModel>> getAllCommunications(Pageable page, HttpServletRequest req) {
		System.out.println("getAllCommunications()");
		
		String token = jwtTokenUtils.resolveToken(req);
		String email = jwtTokenUtils.getUsername(token);
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
		}
		
		final Page<CertificateCommunicationModel> comm = communicationService.findAll(page);
		
		HttpHeaders headers = new HttpHeaders();
		long certTotal = comm.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(certTotal));

		List<CertificateCommunicationModel> lista = new ArrayList<>();
		for (CertificateCommunicationModel c : comm) {
			lista.add(c);
		}
		
		return new ResponseEntity<>(lista, headers, HttpStatus.OK);
	}
}
