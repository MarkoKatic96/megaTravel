package io.webxml.korisnikservice.controller;

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

import io.webxml.korisnikservice.model.Korisnik;
import io.webxml.korisnikservice.service.AdminService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/korisnik-service/admin")
public class AdminController
{
	@Autowired
	private AdminService adminService;
	
	//pazi da na osnovu spiska koji povuces koristis te i te operacije
	
	/*
	 * Vraca sve korisnike, generalni spisak
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Korisnik>> getAllKorisnici()
	{
		System.out.println("getAllKorisnici()");
		
		List<Korisnik> neaktivirani = adminService.getAllKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<Korisnik>>(neaktivirani, HttpStatus.OK);
	}
	
	/*
	 * Vraca sve aktivirane neblokirane (ZA BLOKIRANJE, BRISANJE)
	 * Moraju biti aktivirani!
	 */
	@GetMapping("/allactivated")
	public ResponseEntity<List<Korisnik>> getAllAktiviraniKorisnici()
	{
		System.out.println("getAllAktiviraniKorisnici()");
		
		List<Korisnik> neaktivirani = adminService.getAllAktiviraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<Korisnik>>(neaktivirani, HttpStatus.OK);
	}
	
	/*
	 * Vraca sve neaktivirane korisnike (ZA AKTIVIRANJE, BRISANJE)
	 */
	@GetMapping("/allnotactivated")
	public ResponseEntity<List<Korisnik>> getAllNeaktiviraniKorisnici()
	{
		System.out.println("getAllNeaktiviraniKorisnici()");
		
		List<Korisnik> neaktivirani = adminService.getAllNeaktiviraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<Korisnik>>(neaktivirani, HttpStatus.OK);
	}

	/*
	 * Vraca sve blokirane (ZA DEBLOKIRANJE, BRISANJE)
	 */
	@GetMapping("/allblocked")
	public ResponseEntity<List<Korisnik>> getAllBlokiraniKorisnici()
	{
		System.out.println("getAllBlokiraniKorisnici()");
		
		List<Korisnik> neaktivirani = adminService.getAllBlokiraniKorisnici();
		
		return (neaktivirani.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<Korisnik>>(neaktivirani, HttpStatus.OK);
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
		
		return (!adminService.updateAktivirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Blokiranje korisnika
	 * Vraca TRUE ako je uspesno blokiran
	 */
	@PutMapping("/blockuser/{id}")
	public ResponseEntity<Boolean> updateBlokirajKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("updateBlokirajKorisnika()");
		
		return (!adminService.updateBlokirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Deblokiranje korisnika
	 * Vraca TRUE ako je uspesno deblokiran
	 */
	@PutMapping("/unblockuser/{id}")
	public ResponseEntity<Boolean> updateDeblokirajKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("updateDeblokirajKorisnika()");

		return (!adminService.updateDeblokirajKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	/*
	 * Brisanje korisnika
	 * Vraca TRUE ako je uspesno obrisan
	 */
	@DeleteMapping("/removeuser/{id}")
	public ResponseEntity<Boolean> deleteKorisnika(@PathVariable("id") Long id)
	{
		System.out.println("deleteKorisnika()");
		
		return (!adminService.deleteKorisnika(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
}
