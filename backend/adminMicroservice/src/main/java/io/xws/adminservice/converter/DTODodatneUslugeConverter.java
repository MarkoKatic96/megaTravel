package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.model.DodatneUsluge;

@Component
public class DTODodatneUslugeConverter 
{
	public DodatneUslugeDTO convertToDTO(DodatneUsluge model)
	{
		DodatneUslugeDTO dto = new DodatneUslugeDTO();
		
		dto.setIdDodatneUsluge(model.getIdDodatneUsluge());
		dto.setNaziv(model.getNaziv());
		
		return dto;
	}
	
	public DodatneUsluge convertFromDTO(DodatneUslugeDTO dto)
	{
		DodatneUsluge bean = new DodatneUsluge(); 
		
		bean.setIdDodatneUsluge(dto.getIdDodatneUsluge());
		bean.setNaziv(dto.getNaziv());
		
		return bean;
	}
}
