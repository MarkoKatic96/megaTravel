package io.xws.adminservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.dto.KategorijaSmestajaDTO;
import io.xws.adminservice.dto.TipSmestajaDTO;
import io.xws.adminservice.service.ISmestajService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/admin-service/accommodation")
@Api(tags = "")
public class SmestajController 
{
	
	@Autowired
	private ISmestajService smestajService;
	
	 //////////////////////////////////////////////
	////////OPERACIJE SA TIPOVIMA SMESTAJA////////
   //////////////////////////////////////////////
	
	@GetMapping("/gettypes")
	public ResponseEntity<List<TipSmestajaDTO>> getAllTipoviSmestaja()
	{
		System.out.println("getAllTipoviSmestaja()");
		
		List<TipSmestajaDTO> list = smestajService.getAllTipoviSmestaja();
		
		return (list.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<TipSmestajaDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addtype/")
	public ResponseEntity<TipSmestajaDTO> createTipSmestaja(@RequestBody TipSmestajaDTO dto)
	{
		System.out.println("createTipSmestaja()");
		
		TipSmestajaDTO created = smestajService.createTipSmestaja(dto);
		
		return (created == null) ? new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<TipSmestajaDTO>(created, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatetype/{id}")
	public ResponseEntity<TipSmestajaDTO> updateTipSmestaja(@PathVariable("id") Long id, @RequestBody TipSmestajaDTO updateDto)
	{
		System.out.println("updateTipSmestaja()");
		
		TipSmestajaDTO dto = smestajService.updateTipSmestaja(id, updateDto);
		
		return (dto == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<TipSmestajaDTO>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletetype/{id}")
	public ResponseEntity<Boolean> deleteTipSmestaja(@PathVariable("id") Long idToDelete)
	{
		System.out.println("deleteTipSmestaja()");
		
		return (!smestajService.deleteTipSmestaja(idToDelete)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	
	 //////////////////////////////////////////////
	///////OPERACIJE SA KATEGORIJOM SMESTAJA//////
   //////////////////////////////////////////////
	
	@GetMapping("/getcategories")
	public ResponseEntity<List<KategorijaSmestajaDTO>> getAllKategorijeSmestaja()
	{
		System.out.println("getAllKategorijeSmestaja()");
		
		List<KategorijaSmestajaDTO> list = smestajService.getAllKategorijeSmestaja();
		
		return (list.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<KategorijaSmestajaDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addcategory/")
	public ResponseEntity<KategorijaSmestajaDTO> createKategorijaSmestaja(@RequestBody KategorijaSmestajaDTO dto)
	{
		System.out.println("createKategorijaSmestaja()");
		
		KategorijaSmestajaDTO created = smestajService.createKategorijaSmestaja(dto);
		
		return (created == null) ? new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<KategorijaSmestajaDTO>(created, HttpStatus.CREATED);
	}
	
	@PutMapping("/updatecategory/{id}")
	public ResponseEntity<KategorijaSmestajaDTO> updateKategorijaSmestaja(@PathVariable("id") Long id, @RequestBody KategorijaSmestajaDTO updateDto)
	{
		System.out.println("updateKategorijaSmestaja()");
		
		KategorijaSmestajaDTO dto = smestajService.updateKategorijaSmestaja(id, updateDto);
		
		return (dto == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<KategorijaSmestajaDTO>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deletecategory/{id}")
	public ResponseEntity<Boolean> deleteKategorijaSmestaja(@PathVariable("id") Long idToDelete)
	{
		System.out.println("deleteKategorijaSmestaja()");
		
		return (!smestajService.deleteKategorijaSmestaja(idToDelete)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	
	
	
	 //////////////////////////////////////////////
	////////OPERACIJE SA DODATNIM USLUGAMA////////
   //////////////////////////////////////////////
	
	@GetMapping("/getservices")
	public ResponseEntity<List<DodatneUslugeDTO>> getAllDodatneUsluge()
	{
		System.out.println("getAllDodatneUsluge()");
		
		List<DodatneUslugeDTO> list = smestajService.getAllDodatneUsluge();
		
		return (list.isEmpty()) ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) : new ResponseEntity<List<DodatneUslugeDTO>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/addservice/")
	public ResponseEntity<DodatneUslugeDTO> createDodatnaUsluga(@RequestBody DodatneUslugeDTO dto)
	{
		System.out.println("createDodatnaUsluga()");
		
		DodatneUslugeDTO created = smestajService.createDodatnaUsluga(dto);
		
		return (created == null) ? new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED) : new ResponseEntity<DodatneUslugeDTO>(created, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateservice/{id}")
	public ResponseEntity<DodatneUslugeDTO> updateDodatnaUsluga(@PathVariable("id") Long id, @RequestBody DodatneUslugeDTO updateDto)
	{
		System.out.println("updateDodatnaUsluga()");
		
		DodatneUslugeDTO dto = smestajService.updateDodatnaUsluga(id, updateDto);
		
		return (dto == null) ? new ResponseEntity<>(null, HttpStatus.BAD_REQUEST) : new ResponseEntity<DodatneUslugeDTO>(dto, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/deleteservice/{id}")
	public ResponseEntity<Boolean> deleteDodatnaUsluga(@PathVariable("id") Long idToDelete)
	{
		System.out.println("deleteDodatnaUsluga()");
		
		return (!smestajService.deleteDodatnaUsluga(idToDelete)) ? new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST) : new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
