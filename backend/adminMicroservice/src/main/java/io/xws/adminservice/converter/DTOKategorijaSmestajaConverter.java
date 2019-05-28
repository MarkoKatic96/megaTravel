package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.KategorijaSmestajaDTO;
import io.xws.adminservice.dto.TipSmestajaDTO;
import io.xws.adminservice.model.KategorijaSmestaja;
import io.xws.adminservice.model.TipSmestaja;

@Component
public class DTOKategorijaSmestajaConverter 
{
	
	public KategorijaSmestajaDTO convertToDTO(KategorijaSmestaja model)
	{
		KategorijaSmestajaDTO dto = new KategorijaSmestajaDTO();
		
		dto.setIdKategorijeSmestaja(model.getIdKategorijeSmestaja());
		dto.setNaziv(model.getNaziv());
		
		return dto;
	}
	
	public KategorijaSmestaja convertFromDTO(KategorijaSmestajaDTO dto)
	{
		KategorijaSmestaja bean = new KategorijaSmestaja();
		
		bean.setIdKategorijeSmestaja(dto.getIdKategorijeSmestaja());
		bean.setNaziv(dto.getNaziv());
		
		return bean;
	}
}
