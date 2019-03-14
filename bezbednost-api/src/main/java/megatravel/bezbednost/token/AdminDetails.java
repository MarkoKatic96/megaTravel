package megatravel.bezbednost.token;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import megatravel.bezbednost.model.AdminModel;
import megatravel.bezbednost.repository.AdminRepository;
import megatravel.bezbednost.validation.StaticData;

@Service
public class AdminDetails implements UserDetailsService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AdminModel admin = adminRepository.findByEmail(email);

		if (admin == null) {
			throw new UsernameNotFoundException("Administrator '" + email + "' not found");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		grantedAuthorities.add(new SimpleGrantedAuthority(StaticData.tipAdmina));

		return new org.springframework.security.core.userdetails.User(email, admin.getLozinka(), true, true, true,
				true, grantedAuthorities);
	}

}