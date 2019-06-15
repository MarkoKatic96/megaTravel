package io.xws.adminservice.converter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.dto.SmestajDTO;
import io.xws.adminservice.model.DodatneUsluge;
import io.xws.adminservice.model.Smestaj;

@Component
public class DTOSmestajConverter 
{
	@Autowired
	private DTOTipSmestajaConverter tipConv;
	
	@Autowired
	private DTODodatneUslugeConverter uslugeConv;
	
	@Autowired
	private DTOTAdresaConverter adrConv;
	
	@Autowired
	private DTOTKoordinateConverter koordConv;
	
	@Autowired
	private DTOKategorijaSmestajaConverter katConv;

	public SmestajDTO convertToDTO(Smestaj model)
	{
		SmestajDTO dto = new SmestajDTO();
		
		dto.setIdSmestaja(model.getIdSmestaja());
		dto.setAdresa(adrConv.convertToDTO(model.getAdresa()));
		dto.setKoordinate(koordConv.convertToDTO(model.getKoordinate()));
		dto.setTipSmestaja(tipConv.convertToDTO(model.getTipSmestaja()));
		dto.setKategorijaSmestaja(katConv.convertToDTO(model.getKategorijaSmestaja()));
		dto.setOpis(model.getOpis());
		dto.setMaxOsoba(model.getMaxOsoba());
		dto.setMaxDanaZaOtkazivanje(model.getMaxDanaZaOtkazivanje());
		dto.setCenaProlece(model.getCenaProlece());
		dto.setCenaLeto(model.getCenaLeto());
		dto.setCenaJesen(model.getCenaJesen());
		dto.setCenaZima(model.getCenaZima());
		dto.setVlasnik(model.getVlasnik());
		
		List<DodatneUslugeDTO> listDto = new ArrayList<DodatneUslugeDTO>();
		
		for(DodatneUsluge tip : model.getListaDodatnihUsluga())
		{
			listDto.add(uslugeConv.convertToDTO(tip));	
		}
		
		dto.setListaDodatnihUsluga(listDto);
		
		return dto;
	}
	
	public Smestaj convertFromDTO(SmestajDTO dto)
	{
		Smestaj bean = new Smestaj(); 
		
		bean.setIdSmestaja(dto.getIdSmestaja());
		bean.setAdresa(adrConv.convertFromDTO(dto.getAdresa()));
		bean.setKoordinate(koordConv.convertFromDTO(dto.getKoordinate()));
		bean.setTipSmestaja(tipConv.convertFromDTO(dto.getTipSmestaja()));
		bean.setKategorijaSmestaja(katConv.convertFromDTO(dto.getKategorijaSmestaja()));
		bean.setOpis(dto.getOpis());
		bean.setMaxOsoba(dto.getMaxOsoba());
		bean.setMaxDanaZaOtkazivanje(dto.getMaxDanaZaOtkazivanje());
		bean.setCenaProlece(dto.getCenaProlece());
		bean.setCenaLeto(dto.getCenaLeto());
		bean.setCenaJesen(dto.getCenaJesen());
		bean.setCenaZima(dto.getCenaZima());
		bean.setVlasnik(dto.getVlasnik());
		
		Set<DodatneUsluge> listDto = new HashSet<DodatneUsluge>();
		
		for(DodatneUslugeDTO tip : dto.getListaDodatnihUsluga())
		{
			listDto.add(uslugeConv.convertFromDTO(tip));	
		}
		
		bean.setListaDodatnihUsluga(listDto);
		
		return bean;
	}
}
