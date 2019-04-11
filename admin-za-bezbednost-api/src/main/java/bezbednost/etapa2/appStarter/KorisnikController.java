package bezbednost.etapa2.appStarter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import bezbednost.etapa2.dto.KorisnikDTO;
import bezbednost.etapa2.dto.RolaDTO;
import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;

@RestController
public class KorisnikController {
	
	@Autowired
	private KorisnikService korisnikService;
	
	@RequestMapping("api/getAllUsers")
	public ResponseEntity<List<Korisnik>> getAllUsers(){
		return new ResponseEntity<List<Korisnik>>(korisnikService.getAllUsers(), HttpStatus.OK);
	}
	
	@RequestMapping("api/getUserById/{email}")
	public ResponseEntity<Korisnik> getKorisnikById(@PathVariable String email){
		Korisnik r = korisnikService.findByEmail(email);
		return new ResponseEntity<Korisnik>(r, HttpStatus.OK);
	}
	
	@RequestMapping("api/dodajRolu/{email}/{id}")
	public ResponseEntity<Korisnik> dodajRolu(@PathVariable("email") String email, @PathVariable("id") Long id){
		Korisnik k = korisnikService.dodajRolu(email, id);
		return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
	}
	
	@RequestMapping("api/ukloniRolu/{email}/{id}")
	public ResponseEntity<Korisnik> ukloniRolu(@PathVariable("email") String email, @PathVariable("id") Long id){
		Korisnik k = korisnikService.ukloniRolu(email, id);
		return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
	}
	
	@RequestMapping(value = "api/createKorisnik", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Korisnik> createKorisnik(@RequestBody KorisnikDTO dto){
		String username = dto.getUsername();
		String password = dto.getPassword();
		Korisnik k = korisnikService.createKorisnik(new Korisnik(username, password));
		return new ResponseEntity<Korisnik>(k, HttpStatus.OK);
	}

}
