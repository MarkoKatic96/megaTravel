package io.xws.adminservice.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.xws.adminservice.model.Admin;
import io.xws.adminservice.model.TipOsobe;
import io.xws.adminservice.repository.AdminRepository;

@Service
public class AdminDetails implements UserDetailsService
{
	@Autowired
	private AdminRepository adminRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
	{
		Optional<Admin> admin = adminRepo.findByEmail(email);
		
		if(admin.isPresent())
			return null;
		
		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		
		grantedAuthorities.add(new SimpleGrantedAuthority(TipOsobe.ADMIN.toString()));
		
		return new User(email, admin.get().getLozinka(), true, true, true, true, grantedAuthorities);
		
	}
	
}
