package bezbednost.etapa2.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import bezbednost.etapa2.dto.KorisnikPrijavaDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.SmestajModel;
import bezbednost.etapa2.repository.KorisnikRepository;
import bezbednost.etapa2.repository.RolaRepository;
import bezbednost.etapa2.token.JwtTokenUtils;
import bezbednost.etapa2.validation.CustomException;


@Service
public class KorisnikService {
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private RolaRepository rolaRepository;
	
	@Autowired
	private RolaService rolaService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtils jwtTokenProvider;
	
	public List<Korisnik> getAllUsers(){
		return korisnikRepository.findAll();
	}
	
	public Korisnik findByEmail(String email) {
		List<Korisnik> lista = getAllUsers();
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
			if(korisnik.getUsername().equals(k.getUsername()) || k.getPassword().length()<5) {
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
	
	public String signin(String email, String lozinka) {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, lozinka));
			return jwtTokenProvider.createToken(email);
		} catch (AuthenticationException e) {
			throw new CustomException("Invalid username/password supplied", HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}
	
	public String rezervacija() {
			return "Uspesno rezervisan smestaj.";
	}
	
	public List<Rola> getRoleFromUser(Korisnik k){
		if(k!=null) {
			List<Rola> listaKorisnikovihRola = (List<Rola>) k.getRoles();
			return listaKorisnikovihRola;
		}
		return null;
	}
	
	/*public List<Rola> getRoleKojeIma(String username){
		List<Rola> lista = rolaService.getAllRoles();
		List<Rola> roleKojeNema = new ArrayList<Rola>();
		List<Rola> roleKojeIma = getRoleFromUser(username);
		for (Rola rola2 : roleKojeIma) {
			for (Rola rola : lista) {
				if()
			}
		}
	}*/
	
	public String login(KorisnikPrijavaDTO dto) {
		
		Korisnik korisnik = findByEmail(dto.getEmail());
		if(korisnik == null) {
			return null;
		}
		
		try {
			String jwt = signin(dto.getEmail(),  dto.getLozinka());
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(jwt);
		}catch (Exception e) {
			return "Nesto je poslo po zlu.";
		}
	}

}
