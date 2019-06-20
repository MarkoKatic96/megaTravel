package com.megatravel.ratingservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.service.AdminKomentarService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rating-service/admin")
public class AdminKomentarController 
{
	@Autowired
	private AdminKomentarService adminService;
	
	//prikazi sve neobjavljene komentare
	@GetMapping("/unpublcomms")
	public ResponseEntity<List<Komentar>> getAllNeobjavljeniKomentari()
	{
		System.out.println("getAllNeobjavljeniKomentar()");
		
		List<Komentar> komentari = adminService.getAllNeobjavljeniKomentari();
		
		return (komentari.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<Komentar>>(komentari, HttpStatus.OK);
	}
	
	
	/*
	 * Objavljivanje komentara (jedan po jedan)
	 * Vraca TRUE ako je uspesno objavljen
	 */
	@PutMapping("/publcomm/{id}")
	public ResponseEntity<Boolean> updateObjaviKomentar(@PathVariable("id") Long id)
	{
		System.out.println("updateObjaviKomentar()");
		
		return (!adminService.updateObjaviKomentar(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	
	/*
	 * Blokiranje komentara (zapravo je brisanje komentara)
	 * Vraca TRUE ako je uspesno blokiran (obrisan)
	 */
	@DeleteMapping("/blockcomm/{id}")
	public ResponseEntity<Boolean> blockKomentar(@PathVariable("id") Long id)
	{
		System.out.println("deleteKomentar()");
		
		return (!adminService.blockKomentar(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public Komentar getFilteredSmestaj(@RequestBody Komentar dto){	
		return adminService.createKomentar(dto);
	}

	@RequestMapping(value = "/all")
	public List<Komentar> getAll(){
		return adminService.getAllObjavljeniKomentari();
	}
	
	
}
