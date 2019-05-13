package io.webxml.pretragaservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Smestaj;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;

@Service
public class OsnovnaPretragaService {
	
	public List<Smestaj> osnovnaPretragaSmestaji(OsnovnaPretraga op, SmestajiRestTemplate srt){
		List<Smestaj> lista = new ArrayList<Smestaj>();
		List<Smestaj> listaSmestaja = new ArrayList<Smestaj>();
		List<Smestaj> returnLista = new ArrayList<Smestaj>();
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
		
		return returnLista;
	}

}
