package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.xws.adminservice.converter.DTOKomentarConverter;
import io.xws.adminservice.dto.KomentarDTO;
import io.xws.adminservice.model.Komentar;
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
				if(kom.isObjavljen())
					continue;
				else
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
			komentar.get().setObjavljen(true);
			komentRepo.save(komentar.get());
			return true;
		}
		else
			return false;
	}

	@Override
	public boolean deleteKomentar(Long id)
	{
		Optional<Komentar> komentar = komentRepo.findById(id);
		
		if(komentar.isPresent())
		{
			//moze se obrisati samo ako nije objavljen
			if(!komentar.get().isObjavljen())
			{
				komentRepo.delete(komentar.get());
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
}
