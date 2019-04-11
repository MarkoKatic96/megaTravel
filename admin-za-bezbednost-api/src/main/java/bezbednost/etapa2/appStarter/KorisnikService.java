package bezbednost.etapa2.appStarter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;

@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private RolaRepository rolaRepository;
	
	public List<Korisnik> getAllUsers(){
		return korisnikRepository.findAll();
	}
	
	public Korisnik findByEmail(String email) {
		List<Korisnik> lista = korisnikRepository.findAll();
		for (Korisnik korisnik : lista) {
			if(korisnik.getUsername().equals(email)) {
				return korisnik;
			}
		}
		return null;
	}
	
	public Korisnik createKorisnik(Korisnik k) {
		List<Korisnik> lista = korisnikRepository.findAll();
		for (Korisnik korisnik : lista) {
			if(korisnik.getUsername().equals(k.getUsername())) {
				return null;
			}
		}
		
		return korisnikRepository.save(k);
	}
	
	public Korisnik dodajRolu(String email, Long rolaId) {
		Korisnik k = findByEmail(email);
		List<Rola> listaKorisnikovihRola = new ArrayList<Rola>();
		if(k.getRoles()!=null) {
			listaKorisnikovihRola = (List<Rola>) k.getRoles();//sve role koje korisnik trenutno ima
		}
		List<Rola> listaRola = rolaRepository.findAll();//sve role
		for (Rola rola : listaRola) { //prolazimo kroz sve role i nalazimo onu koju zelimo da dodamo korisniku
			if(rola.getId()==rolaId) {
				for(int i = 0; i<listaKorisnikovihRola.size(); i++) { //proveravamo da li korisnik vec ima tu rolu
					if(listaKorisnikovihRola.get(i).getId() == rola.getId()) {
						return null;
					}
				}
				listaKorisnikovihRola.add(rola);//ako je nema dodajemo je
				k.setRoles(listaKorisnikovihRola);
				break;
			}
		}
		return korisnikRepository.save(k);
		
	}
	
	public Korisnik ukloniRolu(String email, Long rolaId) {
		Korisnik k = findByEmail(email);
		List<Rola> listaKorisnikovihRola = (List<Rola>) k.getRoles();//role koje korisnik ima
		if(k.getRoles()==null || k.getRoles().isEmpty()) {
			return null;//nema sta da se ukloni
		}
		
		for (Rola rola : listaKorisnikovihRola) {
			if(rola.getId()==rolaId) {
				listaKorisnikovihRola.remove(rola);
				break;
			}
		}
		
		return korisnikRepository.save(k);
	}

}
