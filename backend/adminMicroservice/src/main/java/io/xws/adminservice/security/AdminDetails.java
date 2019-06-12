package io.xws.adminservice.security;

import org.springframework.stereotype.Service;

@Service
public class AdminDetails //implements UserDetailsService
{
//	@Autowired
//	private AdminRepository adminRepo;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException 
//	{
//		Optional<Admin> admin = adminRepo.findByEmail(email);
//		
//		if(admin.isPresent())
//			return null;
//		
//		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//		
//		grantedAuthorities.add(new SimpleGrantedAuthority(TipOsobe.ADMIN.toString()));
//		
//		return new User(email, admin.get().getLozinka(), true, true, true, true, grantedAuthorities);
//		
//	}
	
}
