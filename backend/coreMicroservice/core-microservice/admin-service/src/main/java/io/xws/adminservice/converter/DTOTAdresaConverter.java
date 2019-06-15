package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.TAdresaDTO;
import io.xws.adminservice.model.TAdresa;

@Component
public class DTOTAdresaConverter 
{
	public TAdresaDTO convertToDTO(TAdresa model)
	{
		TAdresaDTO dto = new TAdresaDTO();
		
		dto.setAdresaId(model.getAdresaId());
		dto.setSmestaj(model.getSmestaj());
		dto.setGrad(model.getGrad());
		dto.setUlica(model.getGrad());
		dto.setBroj(model.getBroj());
		
		return dto;
	}
	
	public TAdresa convertFromDTO(TAdresaDTO dto)
	{
		TAdresa bean = new TAdresa();
		
		bean.setAdresaId(dto.getAdresaId());
		bean.setSmestaj(dto.getSmestaj());
		bean.setGrad(dto.getGrad());
		bean.setUlica(dto.getGrad());
		bean.setBroj(dto.getBroj());
		return bean;
	}
	
}