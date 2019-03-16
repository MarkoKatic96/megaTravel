package megatravel.bezbednost.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import megatravel.bezbednost.service.CertificateViabilityService;

@RestController
@RequestMapping("/api/certification/viability")
public class CertificateViabilityController {
	
	@Autowired
	CertificateViabilityService certificateViabilityService;
	
	@GetMapping("/{serijskiBroj}")
	public ResponseEntity<String> getStatus(@PathVariable("serijskiBroj") BigInteger serijskiBroj){
		
		String status = certificateViabilityService.getStatus(serijskiBroj);
		return new ResponseEntity<String>(status, HttpStatus.OK);
		
	}
	

}
