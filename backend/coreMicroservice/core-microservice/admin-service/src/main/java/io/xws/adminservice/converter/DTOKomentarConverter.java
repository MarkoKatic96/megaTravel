package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.KomentarDTO;
import io.xws.adminservice.model.Komentar;

@Component
public class DTOKomentarConverter
{
	public KomentarDTO convertToDTO(Komentar model)
	{
		KomentarDTO dto = new KomentarDTO();
		
		dto.setIdKomentara(model.getIdKomentara());
		dto.setIdSmestaja(model.getIdSmestaja());
		dto.setIdRezervacije(model.getIdRezervacije());
		dto.setIdKorisnika(model.getIdKorisnika());
		dto.setKomentar(model.getKomentar());
		dto.setTimestamp(model.getTimestamp());
		dto.setStatus(model.getStatus());
		
		return dto;
	}
	
	public Komentar convertFromDTO(KomentarDTO dto)
	{
		Komentar bean = new Komentar();
		
		bean.setIdKomentara(dto.getIdKomentara());
		bean.setIdSmestaja(dto.getIdSmestaja());
		bean.setIdRezervacije(dto.getIdRezervacije());
		bean.setIdKorisnika(dto.getIdKorisnika());
		bean.setKomentar(dto.getKomentar());
		bean.setTimestamp(dto.getDatum());
		bean.setStatus(dto.getStatus());
		
		return bean;
	}
}
