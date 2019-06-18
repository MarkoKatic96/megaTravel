package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.xws.adminservice.converter.DTOKomentarConverter;
import io.xws.adminservice.dto.KomentarDTO;
import io.xws.adminservice.model.Komentar;
import io.xws.adminservice.model.StatusKomentara;
import io.xws.adminservice.repository.KomentarRepository;

@Service
public class KomentarServiceImpl implements IKomentarService
{
	@Autowired
	private KomentarRepository komentRepo;
	
	@Autowired
	private DTOKomentarConverter komentConv;

	@Override
	public List<KomentarDTO> getAllNeobjavljeniKomentari() 
	{
		Optional<List<Komentar>> komentari = Optional.of(komentRepo.findAll());
		
		List<KomentarDTO> dtoList = new ArrayList<KomentarDTO>();
		
		if(komentari.isPresent())
		{
			for(Komentar kom : komentari.get())
			{
				if(kom.getStatus()==StatusKomentara.NEOBJAVLJEN)
					dtoList.add(komentConv.convertToDTO(kom));
			}
			
			return dtoList;
		}
		else
			return null;
	}

	@Override
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

	@Override
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
	
	@Override
	public Komentar createKomentar(KomentarDTO komentar) {
		Komentar k = komentConv.convertFromDTO(komentar);
		k.setStatus(StatusKomentara.NEOBJAVLJEN);
		k.setTimestamp(new Date(System.currentTimeMillis()));
		return komentRepo.save(k);		
	}

	@Override
	public List<KomentarDTO> getAllObjavljeniKomentari() {
		List<Komentar> lista = komentRepo.findAll();
		List<KomentarDTO> retrunList = new ArrayList<KomentarDTO>();
		for (Komentar komentar : lista) {
			if(komentar.getStatus()==StatusKomentara.OBJAVLJEN) {
				retrunList.add(komentConv.convertToDTO(komentar));
			}
		}
		return retrunList;
	}
	
}
