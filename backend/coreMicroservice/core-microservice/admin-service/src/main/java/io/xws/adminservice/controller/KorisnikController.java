package io.xws.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.xws.adminservice.dto.KorisnikDTO;
import io.xws.adminservice.service.IKorisnikService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-service/user")
@Api(tags = "")
public class KorisnikController
{
	@Autowired
	private IKorisnikService korService;
	
	//pazi da na osnovu spiska koji povuces koristis te i te operacije
	
	/*
	 * Vraca sve korisnike, generalni spisak
	 */
	@GetMapping("/all")
	public ResponseEntity<List<KorisnikDTO>> getAllKorisnici()
	{
		System.out.println("getAllKorisnici()");
		
		List<KorisnikDTO> neaktivirani = korService.getAllKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KorisnikDTO>>(neaktivirani, HttpStatus.OK);
	}
	
	/*
	 * Vraca sve aktivirane neblokirane (ZA BLOKIRANJE, BRISANJE)
	 * Moraju biti aktivirani!
	 */
	@GetMapping("/allactivated")
	public ResponseEntity<List<KorisnikDTO>> getAllAktiviraniKorisnici()
	{
		System.out.println("getAllAktiviraniKorisnici()");
		
		List<KorisnikDTO> neaktivirani = korService.getAllAktiviraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KorisnikDTO>>(neaktivirani, HttpStatus.OK);
	}
	
	/*
	 * Vraca sve neaktivirane korisnike (ZA AKTIVIRANJE, BRISANJE)
	 */
	@GetMapping("/allnotactivated")
	public ResponseEntity<List<KorisnikDTO>> getAllNeaktiviraniKorisnici()
	{
		System.out.println("getAllNeaktiviraniKorisnici()");
		
		List<KorisnikDTO> neaktivirani = korService.getAllNeaktiviraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KorisnikDTO>>(neaktivirani, HttpStatus.OK);
	}

	/*
	 * Vraca sve blokirane (ZA DEBLOKIRANJE, BRISANJE)
	 */
	@GetMapping("/allblocked")
	public ResponseEntity<List<KorisnikDTO>> getAllBlokiraniKorisnici()
	{
		System.out.println("getAllBlokiraniKorisnici()");
		
		List<KorisnikDTO> neaktivirani = korService.getAllBlokiraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KorisnikDTO>>(neaktivirani, HttpStatus.OK);
	}
	
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	
	/*
	 * Aktiviranje korisnika
	 * Vraca TRUE ako je uspesno aktiviran
	 */
	@PutMapping("/activateuser/{id}")
	public ResponseEntity<Boolean> updateAktivirajKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("updateAktivirajKorisnika()");
		
		return (!korService.updateAktivirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Blokiranje korisnika
	 * Vraca TRUE ako je uspesno blokiran
	 */
	@PutMapping("/blockuser/{id}")
	public ResponseEntity<Boolean> updateBlokirajKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("updateBlokirajKorisnika()");
		
		return (!korService.updateBlokirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Deblokiranje korisnika
	 * Vraca TRUE ako je uspesno deblokiran
	 */
	@PutMapping("/unblockuser/{id}")
	public ResponseEntity<Boolean> updateDeblokirajKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("updateDeblokirajKorisnika()");

		return (!korService.updateDeblokirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Brisanje korisnika
	 * Vraca TRUE ako je uspesno obrisan
	 */
	@DeleteMapping("/removeuser/{id}")
	public ResponseEntity<Boolean> deleteKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("deleteKorisnika()");
		
		return (!korService.deleteKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
}
