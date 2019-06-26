package com.megatravel.ratingservice.controller;

import java.util.Arrays;
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
import org.springframework.web.client.RestTemplate;

import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.service.AdminKomentarService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/rating-service/admin")
public class AdminKomentarController 
{
	@Autowired
	private AdminKomentarService adminService;
	
	RestTemplate rt = new RestTemplate();
	//prikazi sve neobjavljene komentare
	@GetMapping("/unpublcomms")
	public ResponseEntity<List<Komentar>> getAllNeobjavljeniKomentari()
	{
		String url = "http://localhost:8010/cloud-demo/us-central1/getNeobjavljeneKomentare";
		ResponseEntity<Komentar[]> response = rt.getForEntity(url, Komentar[].class);
		Komentar[] k = response.getBody();
		List<Komentar> lista = Arrays.asList(k);
		return new ResponseEntity<List<Komentar>>(lista, HttpStatus.OK);
	}
	
	
	/*
	 * Objavljivanje komentara (jedan po jedan)
	 * Vraca TRUE ako je uspesno objavljen
	 */
	@PutMapping("/publcomm/{id}")
	public ResponseEntity<String> updateObjaviKomentar(@PathVariable("id") Long id)
	{
		System.out.println("updateObjaviKomentar()");
		String url = "http://localhost:8010/cloud-demo/us-central1/objaviKomentar?id=" + id;
		ResponseEntity<String> response = rt.getForEntity(url, String.class);
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
	}
	
	
	/*
	 * Blokiranje komentara (zapravo je brisanje komentara)
	 * Vraca TRUE ako je uspesno blokiran (obrisan)
	 */
	@DeleteMapping("/blockcomm/{id}")
	public ResponseEntity<String> blockKomentar(@PathVariable("id") Long id)
	{
		System.out.println("deleteKomentar()");
		String url = "http://localhost:8010/cloud-demo/us-central1/blokirajKomentar?id=" + id;
		ResponseEntity<String> response = rt.getForEntity(url, String.class);
		return new ResponseEntity<String>(response.getBody(), HttpStatus.OK);
		
	}
	
/*	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public Komentar getFilteredSmestaj(@RequestBody Komentar dto){	
		return adminService.createKomentar(dto);
	}

	@RequestMapping(value = "/all")
	public List<Komentar> getAll(){
		return adminService.getAllObjavljeniKomentari();
	}*/
	
	
}
