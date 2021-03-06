package com.megatravel.porukeservice.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.megatravel.porukeservice.model.Korisnik;

@Service
public class UserDetails implements UserDetailsService{
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{
		//pronadji korisnika po emailu
		ResponseEntity<Korisnik> korisnikEntity = restTemplate.getForEntity("http://korisnik-service/korisnik/"+email, Korisnik.class);
		if (korisnikEntity.getStatusCode() != HttpStatus.OK) {
			return null;
		}
		
		Korisnik korisnik = korisnikEntity.getBody();
		if(korisnik == null) {
			//ako user nije pronadjen cepi ga
			throw new UsernameNotFoundException("User '" + email + "' not found");
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority(korisnik.getRola()));
		
		//novi ulogovani korisnik sa svojim autoritetima
		return new org.springframework.security.core.userdetails.User(korisnik.getEmail(), korisnik.getLozinka(),
				true, true, true, true, grantedAuthorities);	
	}
	
	   //vraca sve servise(privilegije) koji su dostupni roli koju ulogovani korisnik ima
  /* private Collection<? extends GrantedAuthority> getAuthorities(Collection<Rola> roles) {		  
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
   }*/

}
