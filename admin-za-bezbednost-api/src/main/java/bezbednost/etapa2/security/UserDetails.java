package bezbednost.etapa2.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.Rola;
import bezbednost.etapa2.model.Servis;
import bezbednost.etapa2.repository.KorisnikRepository;
import bezbednost.etapa2.repository.RolaRepository;
import bezbednost.etapa2.service.KorisnikService;

@Service
public class UserDetails implements UserDetailsService{
	
	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private RolaRepository rolaRepository;
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		//pronadji korisnika po emailu
		Korisnik korisnik = korisnikService.findByEmail(email);
		if(korisnik == null) {
			//ako user nije pronadjen cepi ga
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}
		
		//novi ulogovani korisnik sa svojim autoritetima
		return new org.springframework.security.core.userdetails.User(korisnik.getUsername(), korisnik.getPassword(),
				true, true, true, true, getAuthorities(korisnik.getRoles()));	
	}
	
	   //vraca sve servise(privilegije) koji su dostupni roli koju ulogovani korisnik ima
   private Collection<? extends GrantedAuthority> getAuthorities(Collection<Rola> roles) {		  
	   return getGrantedAuthorities(getPrivileges(roles));
   }
   
   //nalazi sve privilegije(servise) svake prosledjene role
   private List<String> getPrivileges(Collection<Rola> roles) {
	   
       List<String> privileges = new ArrayList<>();
       List<Servis> collection = new ArrayList<>();
       for (Rola role : roles) {
           collection.addAll(role.getServisi());
       }
       for (Servis item : collection) {
           privileges.add(item.getNaziv());
       }
       return privileges;
   }
   
   private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
       List<GrantedAuthority> authorities = new ArrayList<>();
       for (String privilege : privileges) {
           authorities.add(new SimpleGrantedAuthority(privilege));
       }
       return authorities;
   }

}
