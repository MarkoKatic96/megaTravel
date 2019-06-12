package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.AdminDTO;
import io.xws.adminservice.model.Admin;

@Component
public class DTOAdminConverter
{
	public AdminDTO convertToDTO(Admin model)
	{
		AdminDTO dto = new AdminDTO();
		
		dto.setIdAdmina(model.getIdAdmina());
		dto.setIme(model.getIme());
		dto.setPrezime(model.getPrezime());
		dto.setEmail(model.getEmail());
		dto.setLozinka(model.getLozinka());
		
		return dto;
	}
	
	public Admin convertFromDTO(AdminDTO dto)
	{
		Admin bean = new Admin(); 
		
		bean.setIdAdmina(dto.getIdAdmina());
		bean.setIme(dto.getIme());
		bean.setPrezime(dto.getPrezime());
		bean.setEmail(dto.getEmail());
		bean.setLozinka(dto.getLozinka());
		
		return bean;
	}
}
