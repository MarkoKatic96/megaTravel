package io.webxml.pretragaservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Rezervacija;
import io.webxml.pretragaservice.model.RezervacijeRestTemplate;
import io.webxml.pretragaservice.model.Smestaj;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;


@Service
public class OsnovnaPretragaService {
	
	public List<Smestaj> osnovnaPretragaSmestaji(OsnovnaPretraga op, SmestajiRestTemplate srt, RezervacijeRestTemplate rrt){
		List<Smestaj> lista = new ArrayList<Smestaj>();
		List<Smestaj> listaSmestaja = new ArrayList<Smestaj>();
		List<Smestaj> returnLista = new ArrayList<Smestaj>();
		List<Rezervacija> rezervacijeSaId = new ArrayList<Rezervacija>();
		List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
		rezervacije = rrt.getRezervacijaList();//sve rezervacije
		lista = srt.getSmestajiList(); //svi smestaji
		for (Smestaj smestaj : lista) {
			returnLista.add(smestaj);
		}
		
		if(op.getMesto()!=null && !op.getMesto().isEmpty()) {
			for (Smestaj smestaj : returnLista) {
				if(smestaj.getAdresa().contains(op.getMesto())) {
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
			List<Smestaj> listaZauzetihSmestaja = new ArrayList<Smestaj>();
			for (Smestaj smestaj : returnLista) {
				for (Rezervacija rezervacija1 : rezervacije) {
					if(rezervacija1.getSmestajId()==smestaj.getIdSmestaja()) {
						//op.getDatumDolaska/Polaska je onaj sto se unosi u search, ovaj getDo/Od je u bazi
						if((op.getDatumDolaska().equals(rezervacija1.getDo()) || op.getDatumDolaska().after(rezervacija1.getDo()))
								|| (rezervacija1.getOd().equals(op.getDatumPolaska()) || rezervacija1.getOd().after(op.getDatumPolaska()))) {
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
		
		return returnLista;
	}

}
