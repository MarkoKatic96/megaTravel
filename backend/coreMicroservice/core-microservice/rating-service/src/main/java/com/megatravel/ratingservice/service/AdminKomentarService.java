package com.megatravel.ratingservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.megatravel.ratingservice.model.Komentar;
import com.megatravel.ratingservice.model.StatusKomentara;
import com.megatravel.ratingservice.repository.KomentarRepository;


@Service
public class AdminKomentarService
{
	@Autowired
	private KomentarRepository komentRepo;
	

	public List<Komentar> getAllNeobjavljeniKomentari() 
	{
		Optional<List<Komentar>> komentari = Optional.of(komentRepo.findAll());
		
		List<Komentar> dtoList = new ArrayList<Komentar>();
		
		if(komentari.isPresent())
		{
			for(Komentar kom : komentari.get())
			{
				if(kom.getStatus()==StatusKomentara.NEOBJAVLJEN)
					dtoList.add(kom);
			}
			
			return dtoList;
		}
		else
			return null;
	}


	public boolean updateObjaviKomentar(Long id) 
	{
		Optional<Komentar> komentar = komentRepo.findById(id);
		
		if(komentar.isPresent())
		{
			komentar.get().setStatus(StatusKomentara.OBJAVLJEN);
			komentRepo.save(komentar.get());
			return true;
		}
		else
			return false;
	}


	public boolean blockKomentar(Long id)
	{
		Optional<Komentar> komentar = komentRepo.findById(id);
		
		if(komentar.isPresent())
		{
			//moze se blokirati samo ako nije blokiran
			if(komentar.get().getStatus()!=StatusKomentara.BLOKIRAN)
			{
				Komentar k = komentar.get();
				k.setStatus(StatusKomentara.BLOKIRAN);
				komentRepo.save(komentar.get());
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}


	public Komentar createKomentar(Komentar komentar) {
		Komentar k = komentar;
		komentar.setStatus(StatusKomentara.NEOBJAVLJEN);
		komentar.setTimestamp(new Date(System.currentTimeMillis()));
		return komentRepo.save(k);		
	}


	public List<Komentar> getAllObjavljeniKomentari() {
		List<Komentar> lista = komentRepo.findAll();
		List<Komentar> retrunList = new ArrayList<Komentar>();
		for (Komentar komentar : lista) {
			if(komentar.getStatus()==StatusKomentara.OBJAVLJEN) {
				retrunList.add(komentar);
			}
		}
		return retrunList;
	}
	
}
