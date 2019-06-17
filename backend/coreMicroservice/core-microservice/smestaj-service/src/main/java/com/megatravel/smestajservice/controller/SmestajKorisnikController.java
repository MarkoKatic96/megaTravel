package com.megatravel.smestajservice.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.megatravel.smestajservice.dto.SmestajKorisnikDTO;
import com.megatravel.smestajservice.jwt.JwtTokenUtils;
import com.megatravel.smestajservice.model.DodatneUsluge;
import com.megatravel.smestajservice.model.KategorijaSmestaja;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.SmestajiRestTemplate;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.service.SmestajService;

@RestController
@RequestMapping("/smestaj-service/smestaj-korisnik/")
@CrossOrigin(origins = "http://localhost:3000")
public class SmestajKorisnikController {
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	SmestajService smestajService;
	
	@Autowired
	JwtTokenUtils jwtTokenUtils;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<SmestajiRestTemplate> getAllSmestaji(Pageable page) {		

		//ne treba ovde autorizacija svako moze da pregleda smestaje
		Page<Smestaj> smestaji = smestajService.getSmestaji(page);
		
		HttpHeaders headers = new HttpHeaders();
		long korisniciTotal = smestaji.getTotalElements();
		headers.add("X-Total-Count", String.valueOf(korisniciTotal));

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}
		
		SmestajiRestTemplate srt = new SmestajiRestTemplate();
		srt.setSmestajiList(retVal);

		return new ResponseEntity<>(srt, headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/bll", method = RequestMethod.GET)
	public ResponseEntity<List<SmestajKorisnikDTO>> getBllSmestaji() {		

		//ne treba ovde autorizacija svako moze da pregleda smestaje
		List<Smestaj> smestaji = smestajService.getSmestaji();

		List<SmestajKorisnikDTO> retVal = new ArrayList<SmestajKorisnikDTO>();

		for (Smestaj s : smestaji) {
			SmestajKorisnikDTO smestajDTO = new SmestajKorisnikDTO(s);
			retVal.add(smestajDTO);
		}
		
		return new ResponseEntity<List<SmestajKorisnikDTO>>(retVal, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
	public ResponseEntity<SmestajKorisnikDTO> getSmestaj(@PathVariable Long id) {

		SmestajKorisnikDTO smestaj = smestajService.findOne(id);
		
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(smestaj, HttpStatus.OK);
	}
	
	@RequestMapping(value="/allTipovi", method = RequestMethod.GET)
	public ResponseEntity<List<TipSmestaja>> getAllTipove(){
		List<TipSmestaja> lista = smestajService.getAllTipove();
		return new ResponseEntity<List<TipSmestaja>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/allKategorije", method = RequestMethod.GET)
	public ResponseEntity<List<KategorijaSmestaja>> getAllKategorije(){
		List<KategorijaSmestaja> lista = smestajService.getAllKategorije();
		return new ResponseEntity<List<KategorijaSmestaja>>(lista, HttpStatus.OK);
	}
	
	@RequestMapping(value="/allUsluge", method = RequestMethod.GET)
	public ResponseEntity<List<DodatneUsluge>> getAllUsluge(){
		List<DodatneUsluge> lista = smestajService.getAllUsluge();
		return new ResponseEntity<List<DodatneUsluge>>(lista, HttpStatus.OK);
	}
	
	//ovo mi je samo za testiranje ostavi ovako ne zasticeno kasnije cemo zastititi
	@RequestMapping(value="/dodaj-uslugu/{uslugaId}/{smestajId}", method = RequestMethod.GET)
	public ResponseEntity<SmestajKorisnikDTO> dodajUslugu(@PathVariable("uslugaId") Long uslugaId,@PathVariable("smestajId") Long smestajId){
		SmestajKorisnikDTO smestaj = smestajService.dodajUslugu(uslugaId, smestajId);
		if (smestaj==null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(smestaj, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prosekLat/{grad}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> prosekLat(@PathVariable("grad") String grad){
		BigDecimal bd = smestajService.prosekLatitude(grad);
		return new ResponseEntity<BigDecimal>(bd, HttpStatus.OK);
	}
	
	@RequestMapping(value="/prosekLong/{grad}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> prosekLong(@PathVariable("grad") String grad){
		BigDecimal bd = smestajService.prosekLongitude(grad);
		return new ResponseEntity<BigDecimal>(bd, HttpStatus.OK);
	}
	
	@RequestMapping(value="/rastojanje/{smestajId}", method = RequestMethod.GET)
	public ResponseEntity<BigDecimal> rastojanje(@PathVariable("smestajId") Long smestajId){
		BigDecimal bd = smestajService.rastojanje(smestajId);
		return new ResponseEntity<BigDecimal>(bd, HttpStatus.OK);
	}
	
}
