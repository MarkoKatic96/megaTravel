package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import io.xws.adminservice.converter.DTOKorisnikConverter;
import io.xws.adminservice.dto.KorisnikDTO;
import io.xws.adminservice.model.Korisnik;
import io.xws.adminservice.repository.KorisnikRepository;

@Service
public class KorisnikServiceImpl implements IKorisnikService
{
	
	@Autowired
	private KorisnikRepository korRepo;
	
	@Autowired
	private DTOKorisnikConverter korConv;
	
	
	@Override
	public List<KorisnikDTO> getAllKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<KorisnikDTO> sviKor = new ArrayList<KorisnikDTO>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				sviKor.add(korConv.convertToDTO(kor));
			}
			return sviKor;
		}
		else
			return null;
	}
	
	
	@Override
	public List<KorisnikDTO> getAllAktiviraniKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<KorisnikDTO> aktivirani = new ArrayList<KorisnikDTO>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(!kor.isAktiviran())
					continue;
				else
					aktivirani.add(korConv.convertToDTO(kor));
			}
			return aktivirani;
		}
		else
			return null;
	}
	

	@Override
	public List<KorisnikDTO> getAllNeaktiviraniKorisnici() 
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<KorisnikDTO> neaktivirani = new ArrayList<KorisnikDTO>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(kor.isAktiviran())
					continue;
				else
					neaktivirani.add(korConv.convertToDTO(kor));
			}
			return neaktivirani;
		}
		else
			return null;
	}
	
	
	@Override
	public List<KorisnikDTO> getAllBlokiraniKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<KorisnikDTO> blokirani = new ArrayList<KorisnikDTO>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(kor.isBlokiran())
					continue;
				else
					blokirani.add(korConv.convertToDTO(kor));
			}
			return blokirani;
		}
		else
			return null;
	}
	
	
	@Override
	public boolean updateAktivirajKorisnika(Long id)
	{
		Optional<Korisnik> korisnik = korRepo.findById(id);
		
		if(korisnik.isPresent() && !korisnik.get().isAktiviran())
		{
			korisnik.get().setAktiviran(true);
			korRepo.save(korisnik.get());
			return true;
		}
		else
			return false;
	}
	

	@Override
	public boolean updateBlokirajKorisnika(Long id)
	{
		Optional<Korisnik> korisnik = korRepo.findById(id);
		
		if(korisnik.isPresent() && !korisnik.get().isBlokiran())
		{
			korisnik.get().setBlokiran(true);
			korRepo.save(korisnik.get());
			return true;
		}
		else
			return false;
	}
	

	@Override
	public boolean deleteKorisnika(Long id)
	{
		Optional<Korisnik> korisnik = korRepo.findById(id);
		
		if(korisnik.isPresent())
		{
			korRepo.delete(korisnik.get());
			return true;
		}
		else
			return false;
	}
	
}
