package io.webxml.pretragaservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.webxml.pretragaservice.dto.SmestajKorisnikDTO;
import io.webxml.pretragaservice.model.DodatneUsluge;
import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Rezervacija;
import io.webxml.pretragaservice.model.RezervacijeRestTemplate;
import io.webxml.pretragaservice.model.Smestaj;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;
import io.webxml.pretragaservice.model.StatusRezervacije;


@Service
public class OsnovnaPretragaService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<SmestajKorisnikDTO> osnovnaPretragaSmestaji(OsnovnaPretraga op){
		SmestajiRestTemplate srt = restTemplate.getForObject("http://smestaj-service/smestaj-korisnik/all", SmestajiRestTemplate.class);
		List<SmestajKorisnikDTO> lista = new ArrayList<SmestajKorisnikDTO>();
		List<SmestajKorisnikDTO> listaSmestaja = new ArrayList<SmestajKorisnikDTO>();
		List<SmestajKorisnikDTO> returnLista = new ArrayList<SmestajKorisnikDTO>();
		List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
		lista = srt.getSmestajiList(); //svi smestaji
		returnLista.addAll(lista);
	
		if(op.getMesto()!=null && !op.getMesto().isEmpty()) {
			
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getAdresa().getGrad().equals(op.getMesto())) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getBrojOsoba()>0) {
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getMaxOsoba()>=op.getBrojOsoba()) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getDatumDolaska()!=null && op.getDatumPolaska()!=null && op.getDatumDolaska().before(op.getDatumPolaska())) {
			RezervacijeRestTemplate rrt = restTemplate.getForObject("http://reservation-service/rezervacije", RezervacijeRestTemplate.class);
			rezervacije = rrt.getRezervacijaList();//sve rezervacije
			int zauzeto=0;
			for (SmestajKorisnikDTO smestaj : returnLista) {
				for (Rezervacija rezervacija1 : rezervacije) {
					if(rezervacija1.getSmestajId()==smestaj.getIdSmestaja() && rezervacija1.getStatusRezervacije()!=StatusRezervacije.OTKAZANA) {
						//op.getDatumDolaska/Polaska je onaj sto se unosi u search, ovaj getDoDatuma/Od je u bazi
						if((op.getDatumDolaska().equals(rezervacija1.getDoDatuma()) || op.getDatumDolaska().after(rezervacija1.getDoDatuma()))
								|| (rezervacija1.getOdDatuma().equals(op.getDatumPolaska()) || rezervacija1.getOdDatuma().after(op.getDatumPolaska()))) {
							System.out.println("SLOBODNO!");
						}else {
							zauzeto=1;
							break;
						}
					}
				}
				if(zauzeto==0) {
					listaSmestaja.add(smestaj);
				}
				zauzeto=0;
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
			
		}
		
		if(op.getTipSmestaja()!=null) {
			Long tipSmestaja = op.getTipSmestaja();
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getTipSmestaja().getIdTipaSmestaja()==tipSmestaja) {
					listaSmestaja.add(smestaj);
				}
			}			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getKategorijaSmestaja()!=null) {
			Long kategorijaSmestaja = op.getKategorijaSmestaja();
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getKategorijaSmestaja().getId()==kategorijaSmestaja) {
					listaSmestaja.add(smestaj);
				}
			}			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(!op.getDodatneUsluge().isEmpty() || op.getDodatneUsluge()!=null) {
			for(int i = 0; i<op.getDodatneUsluge().size(); i++) { 
				for (SmestajKorisnikDTO smestaj : returnLista) { 
					List<DodatneUsluge> usluge = new ArrayList<DodatneUsluge>(smestaj.getListaDodatnihUsluga());
					for(int g = 0; g<usluge.size(); g++) { 
						if(op.getDodatneUsluge().get(i) == usluge.get(g).getIdDodatneUsluge()) {
							listaSmestaja.add(smestaj);
							break;
						}
					}
				}
				returnLista.clear();
				returnLista.addAll(listaSmestaja);
				listaSmestaja.clear();
			}
		}
		
		return returnLista;
	}

}
