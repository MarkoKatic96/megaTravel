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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.xws.adminservice.dto.KomentarDTO;
import io.xws.adminservice.model.Komentar;
import io.xws.adminservice.service.IKomentarService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-service/comment")
@Api(tags = "")
public class KomentarController 
{
	@Autowired
	private IKomentarService komentService;
	
	//prikazi sve neobjavljene komentare
	@GetMapping("/unpublcomms")
	public ResponseEntity<List<KomentarDTO>> getAllNeobjavljeniKomentari()
	{
		System.out.println("getAllNeobjavljeniKomentar()");
		
		List<KomentarDTO> komentari = komentService.getAllNeobjavljeniKomentari();
		
		return (komentari.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KomentarDTO>>(komentari, HttpStatus.OK);
	}
	
	
	/*
	 * Objavljivanje komentara (jedan po jedan)
	 * Vraca TRUE ako je uspesno objavljen
	 */
	@PutMapping("/publcomm/{id}")
	public ResponseEntity<Boolean> updateObjaviKomentar(@PathVariable("id") Long id)
	{
		System.out.println("updateObjaviKomentar()");
		
		return (!komentService.updateObjaviKomentar(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.ACCEPTED);
	}
	
	
	/*
	 * Blokiranje komentara (zapravo je brisanje komentara)
	 * Vraca TRUE ako je uspesno blokiran (obrisan)
	 */
	@DeleteMapping("/blockcomm/{id}")
	public ResponseEntity<Boolean> blockKomentar(@PathVariable("id") Long id)
	{
		System.out.println("deleteKomentar()");
		
		return (!komentService.blockKomentar(id)) ? new ResponseEntity<Boolean>(false, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, consumes = "application/json")
	public Komentar getFilteredSmestaj(@RequestBody KomentarDTO dto){	
		return komentService.createKomentar(dto);
	}

	@RequestMapping(value = "/all")
	public List<KomentarDTO> getAll(){
		return komentService.getAllObjavljeniKomentari();
	}
	
	
}
