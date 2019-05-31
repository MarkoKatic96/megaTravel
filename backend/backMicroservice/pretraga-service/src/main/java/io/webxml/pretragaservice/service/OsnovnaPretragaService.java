package io.webxml.pretragaservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.webxml.pretragaservice.model.DodatneUsluge;
import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Rezervacija;
import io.webxml.pretragaservice.model.RezervacijeRestTemplate;
import io.webxml.pretragaservice.model.Smestaj;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;


@Service
public class OsnovnaPretragaService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<Smestaj> osnovnaPretragaSmestaji(OsnovnaPretraga op, SmestajiRestTemplate srt, RezervacijeRestTemplate rrt){
		List<Smestaj> lista = new ArrayList<Smestaj>();
		List<Smestaj> listaSmestaja = new ArrayList<Smestaj>();
		List<Smestaj> returnLista = new ArrayList<Smestaj>();
		List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
		rezervacije = rrt.getRezervacijaList();//sve rezervacije
		lista = srt.getSmestajiList(); //svi smestaji
		for (Smestaj smestaj : lista) {
			returnLista.add(smestaj);
		}
		
		if(op.getMesto()!=null && !op.getMesto().isEmpty()) {
			for (Smestaj smestaj : returnLista) {
				if(smestaj.getAdresa().getGrad().equals(op.getMesto())) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getBrojOsoba()>0) {
			for (Smestaj smestaj : returnLista) {
				if(smestaj.getMaxOsoba()>=op.getBrojOsoba()) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getDatumDolaska()!=null && op.getDatumPolaska()!=null && op.getDatumDolaska().before(op.getDatumPolaska())) {
			int zauzeto=0;
			for (Smestaj smestaj : returnLista) {
				for (Rezervacija rezervacija1 : rezervacije) {
					if(rezervacija1.getSmestajId()==smestaj.getIdSmestaja()) {
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
			List<Smestaj> sst = new ArrayList<Smestaj>();
			Long tipSmestaja = op.getTipSmestaja();
			SmestajiRestTemplate smestajiSaTipom = restTemplate.getForObject("http://korisnik-service/api/smestajiTipa/" + tipSmestaja, SmestajiRestTemplate.class);
			sst = smestajiSaTipom.getSmestajiList();
			for (Smestaj smestaj : sst) {
				for(Smestaj smestaj2 : returnLista) {
					if(smestaj.getIdSmestaja()==smestaj2.getIdSmestaja()) {
						listaSmestaja.add(smestaj2);
					}
				}
			}			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getKategorijaSmestaja()!=null) {
			List<Smestaj> ssk = new ArrayList<Smestaj>();
			Long kategorijaSmestaja = op.getKategorijaSmestaja();
			SmestajiRestTemplate smestajiSaKategorijom = restTemplate.getForObject("http://korisnik-service/api/smestajiKategorije/" + kategorijaSmestaja, SmestajiRestTemplate.class);
			ssk = smestajiSaKategorijom.getSmestajiList();
			for (Smestaj smestaj : ssk) {
				for(Smestaj smestaj2 : returnLista) {
					if(smestaj.getIdSmestaja()==smestaj2.getIdSmestaja()) {
						listaSmestaja.add(smestaj2);
					}
				}
			}			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		//nije jos testirano da li radi
		if(!op.getDodatneUsluge().isEmpty()) {
			for(int i = 0; i<op.getDodatneUsluge().size(); i++) { 
				for (Smestaj smestaj : returnLista) { 
					for(int g = 0; g<smestaj.getListaDodatnihUsluga().size(); g++) { 
						if(op.getDodatneUsluge().get(i) == ((List<DodatneUsluge>) smestaj.getListaDodatnihUsluga()).get(g).getIdDodatneUsluge()) {
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
