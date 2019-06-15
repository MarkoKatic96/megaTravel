package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.KorisnikDTO;
import io.xws.adminservice.model.Korisnik;

@Component
public class DTOKorisnikConverter 
{
	public KorisnikDTO convertToDTO(Korisnik model)
	{
		KorisnikDTO dto = new KorisnikDTO();
		
		dto.setIdKorisnik(model.getIdKorisnik());
		dto.setEmail(model.getEmail());
		dto.setIme(model.getIme());
		dto.setPrezime(model.getPrezime());
		dto.setLozinka(model.getLozinka());
		dto.setDatumClanstva(model.getDatumClanstva());
		dto.setRegistrovan(model.isRegistrovan());
		dto.setBlokiran(model.isBlokiran());
		dto.setRola(model.getRola());
		
		return dto;
	}
	
	public Korisnik convertFromDTO(KorisnikDTO dto)
	{
		Korisnik bean = new Korisnik();
		
		bean.setIdKorisnik(dto.getIdKorisnik());
		bean.setEmail(dto.getEmail());
		bean.setIme(dto.getIme());
		bean.setPrezime(dto.getPrezime());
		bean.setLozinka(dto.getLozinka());
		bean.setDatumClanstva(dto.getDatumClanstva());
		bean.setRegistrovan(dto.isRegistrovan());
		bean.setBlokiran(dto.isBlokiran());
		bean.setRola(dto.getRola());
		
		return bean;
	}
}
