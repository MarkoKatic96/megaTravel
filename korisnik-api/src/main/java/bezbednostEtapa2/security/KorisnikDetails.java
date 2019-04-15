package bezbednostEtapa2.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import bezbednostEtapa2.model.Korisnik;
import bezbednostEtapa2.repository.KorisnikRepository;
import bezbednostEtapa2.validation.StaticData;

@Service
public class KorisnikDetails implements UserDetailsService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Korisnik k = korisnikRepository.findByEmail(email);
		
		if (k == null) {
			throw new UsernameNotFoundException("Korisnik '" + email + "' not found");
		}
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority(StaticData.tipKorisnika));
		return new org.springframework.security.core.userdetails.User(email, k.getLozinka(), true, true, true,
				true, grantedAuthorities);
	}

}
