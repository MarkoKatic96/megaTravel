package io.xws.adminservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.xws.adminservice.converter.DTOAdminConverter;
import io.xws.adminservice.dto.AdminDTO;
import io.xws.adminservice.dto.RegisterDTO;
import io.xws.adminservice.jwt.JwtTokenUtils;
import io.xws.adminservice.model.Admin;
import io.xws.adminservice.repository.AdminRepository;

@Service
public class AdminServiceImpl implements IAdminService
{
	@Autowired
	private AdminRepository adminRepo;
	
	@Autowired
	private DTOAdminConverter adminConv;
	
	@Autowired
	private JwtTokenUtils jwt;
	
//	@Autowired
//	private AuthenticationManager authManager;

//	@Autowired
//	private PasswordEncoder passEnc;
	
	@Override
	public Admin findByEmail(String email) 
	{
		return adminRepo.findByEmail(email).get();
	}

	@Override
	public String signin(String email, String lozinka) 
	{
		Admin k = adminRepo.findByEmail(email).get();
		if(k==null) {
			return null;
		}
		try {
			if(k.getLozinka().equals(lozinka)) {
//				if (k.isBlokiran()) {
//					return null;
//				}
				String jwtRet = jwt.createToken(email);
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(jwtRet);
			}
		}catch (Exception e) {
			return null;
		}
		
		return null;
		
	}

	@Override
	public AdminDTO register(RegisterDTO registracija) 
	{
//		AdminDTO admin = new AdminDTO();
//		
//		if(!registracija.getLozinka().equals(registracija.getPotvrdaLozinke()))
//		{
//			admin.setIme("passerr");
//			return admin;
//		}
//		else if(adminRepo.existsByEmail(registracija.getEmail()))
//		{
//			
//			admin.setIme("uniqueerr");
//			return admin;
//		}
//		
//		admin.setLozinka(passEnc.encode(registracija.getLozinka()));
//		admin.setEmail(registracija.getEmail());
//		admin.setIme(registracija.getIme());
//		admin.setPrezime(registracija.getPrezime());
//		return adminConv.convertToDTO(adminRepo.save(adminConv.convertFromDTO(admin)));
		
		return null;
	}

	public Admin getAdminByEmail(String email) {
		
		return adminRepo.findByEmail(email).get();
	}
		
		
	
}
