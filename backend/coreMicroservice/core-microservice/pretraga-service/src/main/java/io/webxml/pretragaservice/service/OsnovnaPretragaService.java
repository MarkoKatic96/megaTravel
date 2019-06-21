package io.webxml.pretragaservice.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.webxml.pretragaservice.dto.SmestajKorisnikDTO;
import io.webxml.pretragaservice.model.DodatneUsluge;
import io.webxml.pretragaservice.model.OsnovnaPretraga;
import io.webxml.pretragaservice.model.Rezervacija;
import io.webxml.pretragaservice.model.RezervacijeRestTemplate;
import io.webxml.pretragaservice.model.SamostalnaRezervacija;
import io.webxml.pretragaservice.model.SamostalnaRezervacijaRestTemplate;
import io.webxml.pretragaservice.model.SmestajiRestTemplate;
import io.webxml.pretragaservice.model.StatusRezervacije;


@Service
public class OsnovnaPretragaService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public List<SmestajKorisnikDTO> osnovnaPretragaSmestaji(OsnovnaPretraga op){
		SmestajiRestTemplate srt = restTemplate.getForObject("http://smestaj-service/smestaj-service/smestaj-korisnik/all", SmestajiRestTemplate.class);
		List<SmestajKorisnikDTO> lista = new ArrayList<SmestajKorisnikDTO>();
		List<SmestajKorisnikDTO> listaSmestaja = new ArrayList<SmestajKorisnikDTO>();
		List<SmestajKorisnikDTO> returnLista = new ArrayList<SmestajKorisnikDTO>();
		List<Rezervacija> rezervacije = new ArrayList<Rezervacija>();
		List<SamostalnaRezervacija> samostalne = new ArrayList<SamostalnaRezervacija>();
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
			RezervacijeRestTemplate rrt = restTemplate.getForObject("http://reservation-service/reservation-service/rezervacije", RezervacijeRestTemplate.class);
			rezervacije = rrt.getRezervacijaList();//sve rezervacije
			SamostalnaRezervacijaRestTemplate srrt = restTemplate.getForObject("http://reservation-service/reservation-service/samostalneRezervacije", SamostalnaRezervacijaRestTemplate.class);
			samostalne = srrt.getSamostalnaRezervacijaList();//sve rezervacije
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
				
				for (SamostalnaRezervacija rezervacija1 : samostalne) {
					//uzimam sve rezervacije koje imaju isti smestaj
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
		
		if(op.getUdaljenost()>0) {
			for (SmestajKorisnikDTO smestaj : returnLista) {
				BigDecimal bd = new BigDecimal(0);
				bd = restTemplate.getForObject("http://smestaj-service/smestaj-service/smestaj-korisnik/rastojanje/" + smestaj.getIdSmestaja(), BigDecimal.class);
				Double big = bd.doubleValue();
				int bdInt = big.intValue();
				if(bdInt<=op.getUdaljenost()) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getDanaZaOtkazivanje()>0) {
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getMaxDanaZaOtkazivanje()>=op.getDanaZaOtkazivanje()) {
					listaSmestaja.add(smestaj);
				}
			}
			
			returnLista.clear();
			returnLista.addAll(listaSmestaja);
			listaSmestaja.clear();
		}
		
		if(op.getTipSmestaja()!=null) {
			String tipSmestaja = op.getTipSmestaja();
			for (SmestajKorisnikDTO smestaj : returnLista) {
				if(smestaj.getTipSmestaja().getTipSmestajaId().toString().equals(tipSmestaja)) {
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
		
		if(op.getDodatneUsluge()!=null) {
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
	
	public List<SmestajKorisnikDTO> sortSmestaji(List<SmestajKorisnikDTO> returnLista, String sort){
		if(sort!=null || !sort.equals("")) {
			if(sort.equals("cena rastuca")) {
				Collections.sort(returnLista, new Comparator<SmestajKorisnikDTO>() {
					@Override public int compare(SmestajKorisnikDTO p1, SmestajKorisnikDTO p2) {
			            return (int) (p1.getCena() - p2.getCena()); // Ascending
			        }
					
				});
			}else if(sort.equals("cena opadajuca")) {
				Collections.sort(returnLista, new Comparator<SmestajKorisnikDTO>() {
					@Override public int compare(SmestajKorisnikDTO p1, SmestajKorisnikDTO p2) {
			            return (int) (p2.getCena() - p1.getCena()); // Ascending
			        }
				});
			}else if(sort.equals("kategorija opadajuca")) {
				returnLista = compareCategoryDescending(returnLista);
			}else if(sort.equals("kategorija rastuca")) {
				returnLista = compareCategoryAscending(returnLista);
			}else if(sort.equals("udaljenost")) {
				for (SmestajKorisnikDTO smestajKorisnikDTO : returnLista) {
					BigDecimal srt = restTemplate.getForObject("http://smestaj-service/smestaj-service/smestaj-korisnik/rastojanje/" + smestajKorisnikDTO.getIdSmestaja(), BigDecimal.class);
					smestajKorisnikDTO.setLatitude(srt);
				}
				Collections.sort(returnLista, new Comparator<SmestajKorisnikDTO>() {
					@Override public int compare(SmestajKorisnikDTO p1, SmestajKorisnikDTO p2) {
			            return (int) (p1.getLatitude().intValue() - p2.getLatitude().intValue()); // Ascending
			        }
					
				});
			}else if(sort.equals("ocena")) {
				Float prosecna = new Float(0);
				float f;
				for (SmestajKorisnikDTO smestajKorisnikDTO : returnLista) {
					prosecna = restTemplate.getForObject("http://rating-service/rating-service/ocena/" + smestajKorisnikDTO.getIdSmestaja(), Float.class);
					if(prosecna!=null) {
						f = prosecna.floatValue();
					}else {
						f=0f;
					}
					smestajKorisnikDTO.setCena(f);
				}
				/*Collections.sort(returnLista, new Comparator<SmestajKorisnikDTO>() {
				    @Override
				    public int compare(SmestajKorisnikDTO t, SmestajKorisnikDTO t1) {
				        return Float.compare(t1.getCena(), t.getCena());
				    }
				});*/
				returnLista.sort(Comparator.comparing(SmestajKorisnikDTO::getCena).reversed());
			}
		}
		
		return returnLista;
	}
	
	public List<SmestajKorisnikDTO> compareCategoryDescending(List<SmestajKorisnikDTO> lista) {
		List<SmestajKorisnikDTO> rLista = new ArrayList<SmestajKorisnikDTO>();
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("platinum")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("gold")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("silver")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("bronze")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		return rLista;
	}
	
	public List<SmestajKorisnikDTO> compareCategoryAscending(List<SmestajKorisnikDTO> lista) {
		List<SmestajKorisnikDTO> rLista = new ArrayList<SmestajKorisnikDTO>();
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("bronze")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("silver")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("gold")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		for (SmestajKorisnikDTO smestajKorisnikDTO : lista) {
			if(smestajKorisnikDTO.getKategorijaSmestaja().getNaziv().equals("platinum")) {
				rLista.add(smestajKorisnikDTO);
			}
		}
		return rLista;
	}
}
