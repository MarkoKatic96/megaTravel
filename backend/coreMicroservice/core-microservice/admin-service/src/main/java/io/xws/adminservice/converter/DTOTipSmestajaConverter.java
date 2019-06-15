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
		
		dto.setIdTipaSmestaja(model.getTipSmestajaId());
		dto.setNaziv(model.getNazivTipaSmestaja());
		
		return dto;
	}
	
	public TipSmestaja convertFromDTO(TipSmestajaDTO dto)
	{
		TipSmestaja bean = new TipSmestaja();
		
		bean.setTipSmestajaId(dto.getIdTipaSmestaja());
		bean.setNazivTipaSmestaja(dto.getNaziv());
		
		return bean;
	}
}
