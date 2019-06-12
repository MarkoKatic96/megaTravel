package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.TipSmestajaDTO;
import io.xws.adminservice.model.TipSmestaja;

@Component
public class DTOTipSmestajaConverter 
{
	public TipSmestajaDTO convertToDTO(TipSmestaja model)
	{
		TipSmestajaDTO dto = new TipSmestajaDTO();
		
		dto.setIdTipaSmestaja(model.getIdTipaSmestaja());
		dto.setNaziv(model.getNaziv());
		
		return dto;
	}
	
	public TipSmestaja convertFromDTO(TipSmestajaDTO dto)
	{
		TipSmestaja bean = new TipSmestaja();
		
		bean.setIdTipaSmestaja(dto.getIdTipaSmestaja());
		bean.setNaziv(dto.getNaziv());
		
		return bean;
	}
}
