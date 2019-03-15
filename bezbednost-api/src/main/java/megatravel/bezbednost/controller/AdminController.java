package megatravel.bezbednost.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import megatravel.bezbednost.dto.AdminDTO;
import megatravel.bezbednost.dto.AdminPrijavaDTO;
import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.service.AdminService;

@RestController
public class AdminController {
	
	@Autowired
	AdminService adminService;
	
	//@Autowired
	//JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "api/adminCert/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<AdminDTO> getAdmin(@PathVariable Long id) {
		System.out.println("getAdmin()");
		
		AdminModel korisnik = adminService.findOne(id);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		AdminDTO adminDTO = new AdminDTO(korisnik);

		return new ResponseEntity<>(adminDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/adminCert/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<AdminDTO> getAdminByEmail(@PathVariable String email) {
		email = email + ".com";
		System.out.println("getAdminByEmail(" + email + ")");
		
		AdminModel korisnik = adminService.findByEmail(email);
		if (korisnik == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		
		AdminDTO korisnikDTO = new AdminDTO(korisnik);
		
		return new ResponseEntity<>(korisnikDTO, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/login", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> login(@RequestBody AdminPrijavaDTO adminPrijavaDTO) {
		System.out.println("login()");
		
		AdminModel korisnik = adminService.findByEmail(adminPrijavaDTO.getEmail());
		if(korisnik == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		if (!korisnik.isAktiviranNalog()) {
			// ne moze da se uloguje posto nije aktiviran mail
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		try {
			String jwt = adminService.signin(adminPrijavaDTO.getEmail(), adminPrijavaDTO.getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			return new ResponseEntity<>(mapper.writeValueAsString(jwt), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		}
	}
	
	
	
}
