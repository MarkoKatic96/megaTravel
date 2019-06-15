package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.TKoordinateDTO;
import io.xws.adminservice.model.TKoordinate;

@Component
public class DTOTKoordinateConverter 
{
	public TKoordinateDTO convertToDTO(TKoordinate model)
	{
		TKoordinateDTO dto = new TKoordinateDTO();
		
		dto.setKoordinateId(model.getKoordinateId());
		dto.setSmestaj(model.getSmestaj());
		dto.setLatitude(model.getLatitude());
		dto.setLongitude(model.getLongitude());
		
		return dto;
	}
	
	public TKoordinate convertFromDTO(TKoordinateDTO dto)
	{
		TKoordinate bean = new TKoordinate();
		
		bean.setKoordinateId(dto.getKoordinateId());
		bean.setSmestaj(dto.getSmestaj());
		bean.setLatitude(dto.getLatitude());
		bean.setLongitude(dto.getLongitude());
		return bean;
	}
	
}