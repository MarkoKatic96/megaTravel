package io.webxml.korisnikservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.webxml.korisnikservice.model.Korisnik;
import io.webxml.korisnikservice.repository.KorisnikRepository;

@Service
public class AdminService
{
	@Autowired
	private KorisnikRepository korRepo;
	
	
	
	public List<Korisnik> getAllKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<Korisnik> sviKor = new ArrayList<Korisnik>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				sviKor.add(kor);
			}
			return sviKor;
		}
		else
			return null;
	}
	
	
	public List<Korisnik> getAllAktiviraniKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<Korisnik> aktivirani = new ArrayList<Korisnik>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(!kor.isRegistrovan() || kor.isBlokiran())
					continue;
				else
					aktivirani.add(kor);
			}
			return aktivirani;
		}
		else
			return null;
	}
	

	public List<Korisnik> getAllNeaktiviraniKorisnici() 
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<Korisnik> neaktivirani = new ArrayList<Korisnik>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(!kor.isRegistrovan() || kor.isBlokiran())
					continue;
				else
					neaktivirani.add(kor);
			}
			return neaktivirani;
		}
		else
			return null;
	}
	
	
	public List<Korisnik> getAllBlokiraniKorisnici()
	{
		Optional<List<Korisnik>> korisnici = Optional.of(korRepo.findAll());
		List<Korisnik> blokirani = new ArrayList<Korisnik>();
		
		if(korisnici.isPresent())
		{
			for(Korisnik kor : korisnici.get())
			{
				if(!kor.isBlokiran())
					continue;
				else
					blokirani.add(kor);
			}
			return blokirani;
		}
		else
			return null;
	}
	
	
	public boolean updateAktivirajKorisnika(Long id)
	{
		Optional<Korisnik> korisnik = korRepo.findById(id);
		
		if(korisnik.isPresent() && !korisnik.get().isRegistrovan())
		{
			korisnik.get().setRegistrovan(true);
			korRepo.save(korisnik.get());
			return true;
		}
		else
			return false;
	}
	

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
	
	public boolean updateDeblokirajKorisnika(Long id) 
	{
		Optional<Korisnik> korisnik = korRepo.findById(id);

		if(korisnik.isPresent() && korisnik.get().isBlokiran())
		{
			korisnik.get().setBlokiran(false);
			korRepo.save(korisnik.get());
			return true;
		}
		else
			return false;
	}	

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
