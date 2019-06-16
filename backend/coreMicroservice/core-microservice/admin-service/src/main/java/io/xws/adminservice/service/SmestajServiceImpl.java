package io.xws.adminservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.xws.adminservice.converter.DTODodatneUslugeConverter;
import io.xws.adminservice.converter.DTOKategorijaSmestajaConverter;
import io.xws.adminservice.converter.DTOTipSmestajaConverter;
import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.dto.KategorijaSmestajaDTO;
import io.xws.adminservice.dto.TipSmestajaDTO;
import io.xws.adminservice.model.DodatneUsluge;
import io.xws.adminservice.model.KategorijaSmestaja;
import io.xws.adminservice.model.TipSmestaja;
import io.xws.adminservice.repository.DodatneUslugeRepository;
import io.xws.adminservice.repository.KategorijaSmestajaRepository;
import io.xws.adminservice.repository.TipSmestajaRepository;

@Service
public class SmestajServiceImpl implements ISmestajService
{
//	@Autowired
//	private SmestajRepository smRepo;
//	
//	@Autowired
//	private DTOSmestajConverter smConv;
	
	@Autowired
	private TipSmestajaRepository tipSmRepo;
	
	@Autowired
	private DTOTipSmestajaConverter tipSmConv;
	
	@Autowired
	private KategorijaSmestajaRepository katRepo;
	
	@Autowired
	private DTOKategorijaSmestajaConverter katConv;
	
	@Autowired
	private DodatneUslugeRepository uslugeRepo;
	
	@Autowired
	private DTODodatneUslugeConverter uslugeConv;
	
	
	@Override
	public List<TipSmestajaDTO> getAllTipoviSmestaja() 
	{
		List<TipSmestaja> lista = tipSmRepo.findAll();
		
		List<TipSmestajaDTO> dtos = new ArrayList<TipSmestajaDTO>();
		
		for(TipSmestaja tip : lista)
		{
			dtos.add(tipSmConv.convertToDTO(tip));
		}
		
		return dtos;
	}
	
	@Override
	public TipSmestajaDTO createTipSmestaja(TipSmestajaDTO dto)
	{
		TipSmestaja tip = tipSmRepo.findByNazivTipaSmestaja(dto.getNaziv());
		
		if(tip != null)
			return null;
		else
		{
			tipSmRepo.save(tipSmConv.convertFromDTO(dto));
			return dto;
		}
	}
	
	@Override
	public TipSmestajaDTO updateTipSmestaja(Long id, TipSmestajaDTO updateDto)
	{
		Optional<TipSmestaja> bean = tipSmRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNazivTipaSmestaja(tipSmConv.convertFromDTO(updateDto).getNazivTipaSmestaja());
			
			tipSmRepo.save(bean.get());
			
			return tipSmConv.convertToDTO(bean.get());
		}
		else
			return null;
	}


	@Override
	public boolean deleteTipSmestaja(Long idToDelete) 
	{
		Optional<TipSmestaja> deleteTip = tipSmRepo.findById(idToDelete);
		
		if(deleteTip.isPresent())
		{
			tipSmRepo.deleteById(idToDelete);
			return true;
		}
		else
			return false;
	}
	
	
	@Override
	public List<KategorijaSmestajaDTO> getAllKategorijeSmestaja() 
	{
		List<KategorijaSmestaja> lista = katRepo.findAll();
		
		List<KategorijaSmestajaDTO> dtos = new ArrayList<KategorijaSmestajaDTO>();
		
		for(KategorijaSmestaja kat : lista)
		{
			dtos.add(katConv.convertToDTO(kat));
		}
		
		return dtos;
	}

	@Override
	public KategorijaSmestajaDTO createKategorijaSmestaja(KategorijaSmestajaDTO dto)
	{
		Optional<KategorijaSmestaja> kategorija = katRepo.findById(dto.getIdKategorijeSmestaja());
		
		if(kategorija.isPresent())
			return null;
		else
		{
			katRepo.save(katConv.convertFromDTO(dto));
			return dto;
		}
	}
	
	@Override
	public KategorijaSmestajaDTO updateKategorijaSmestaja(Long id, KategorijaSmestajaDTO updateDto)
	{
		Optional<KategorijaSmestaja> bean = katRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNaziv(katConv.convertFromDTO(updateDto).getNaziv());
			
			katRepo.save(bean.get());
			
			return katConv.convertToDTO(bean.get());
		}
		else
			return null;
	}


	@Override
	public boolean deleteKategorijaSmestaja(Long idToDelete) 
	{
		Optional<KategorijaSmestaja> deleteTip = katRepo.findById(idToDelete);
		
		if(deleteTip.isPresent())
		{
			katRepo.deleteById(idToDelete);
			return true;
		}
		else
			return false;
	}
	
	
	
	@Override
	public List<DodatneUslugeDTO> getAllDodatneUsluge()
	{
		List<DodatneUsluge> lista = uslugeRepo.findAll();
		
		List<DodatneUslugeDTO> dtos = new ArrayList<DodatneUslugeDTO>();
		
		for(DodatneUsluge kat : lista)
		{
			dtos.add(uslugeConv.convertToDTO(kat));
		}
		
		return dtos;
	}

	@Override
	public DodatneUslugeDTO createDodatnaUsluga(DodatneUslugeDTO dto)
	{
		Optional<DodatneUsluge> usluga = uslugeRepo.findById(dto.getIdDodatneUsluge());
		
		if(usluga.isPresent())
			return null;
		else
		{
			uslugeRepo.save(uslugeConv.convertFromDTO(dto));
			return dto;
		}
	}

	@Override
	public DodatneUslugeDTO updateDodatnaUsluga(Long id, DodatneUslugeDTO updateDto)
	{
		Optional<DodatneUsluge> bean = uslugeRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNazivDodatneUsluge(uslugeConv.convertFromDTO(updateDto).getNazivDodatneUsluge());
			
			uslugeRepo.save(bean.get());
			
			return uslugeConv.convertToDTO(bean.get());
		}
		else
			return null;
	}

	@Override
	public boolean deleteDodatnaUsluga(Long idToDelete) 
	{
		Optional<DodatneUsluge> deleteTip = uslugeRepo.findById(idToDelete);
		
		if(deleteTip.isPresent())
		{
			uslugeRepo.deleteById(idToDelete);
			return true;
		}
		else
			return false;
	}

}
