package com.megatravel.smestajservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.megatravel.smestajservice.model.DodatneUsluge;
import com.megatravel.smestajservice.model.KategorijaSmestaja;
import com.megatravel.smestajservice.model.TipSmestaja;
import com.megatravel.smestajservice.repository.DodatnaUslugaRepository;
import com.megatravel.smestajservice.repository.KategorijaSmestajaRepository;
import com.megatravel.smestajservice.repository.SmestajRepository;
import com.megatravel.smestajservice.repository.TipSmestajaRepository;

/**
 * Funkcionalnosti koje admin koristi a vezane su za smestaj
 * @author Nikola
 *
 */

@Service
public class AdminRestService
{	
	@Autowired
	private DodatnaUslugaRepository uslugeRepo;
	
	@Autowired
	private TipSmestajaRepository tipRepo;
	
	@Autowired
	private KategorijaSmestajaRepository katRepo;
	
	
	public TipSmestaja createTipSmestaja(TipSmestaja dto)
	{
		TipSmestaja tip = tipRepo.findByNazivTipaSmestaja(dto.getNazivTipaSmestaja());
		
		if(tip != null)
			return null;
		else
		{
			tipRepo.save(dto);
			return dto;
		}
	}
	
	
	public TipSmestaja updateTipSmestaja(Long id, TipSmestaja updateDto)
	{
		Optional<TipSmestaja> bean = tipRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNazivTipaSmestaja(updateDto.getNazivTipaSmestaja());
			
			tipRepo.save(bean.get());
			
			return bean.get();
		}
		else
			return null;
	}


	public boolean deleteTipSmestaja(Long idToDelete) 
	{
		Optional<TipSmestaja> deleteTip = tipRepo.findById(idToDelete);
		
		if(deleteTip.isPresent())
		{
			tipRepo.deleteById(idToDelete);
			return true;
		}
		else
			return false;
	}
	
	
	

	public KategorijaSmestaja createKategorijaSmestaja(KategorijaSmestaja dto)
	{
		Optional<KategorijaSmestaja> kategorija = katRepo.findById(dto.getId());
		
		if(kategorija.isPresent())
			return null;
		else
		{
			katRepo.save(dto);
			return dto;
		}
	}
	
	
	public KategorijaSmestaja updateKategorijaSmestaja(Long id, KategorijaSmestaja updateDto)
	{
		Optional<KategorijaSmestaja> bean = katRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNaziv(updateDto.getNaziv());
			
			katRepo.save(bean.get());
			
			return bean.get();
		}
		else
			return null;
	}


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
	
	
	
	

	public DodatneUsluge createDodatnaUsluga(DodatneUsluge dto)
	{
		Optional<DodatneUsluge> usluga = uslugeRepo.findById(dto.getIdDodatneUsluge());
		
		if(usluga.isPresent())
			return null;
		else
		{
			uslugeRepo.save(dto);
			return dto;
		}
	}


	public DodatneUsluge updateDodatnaUsluga(Long id, DodatneUsluge updateDto)
	{
		Optional<DodatneUsluge> bean = uslugeRepo.findById(id);
		
		if(bean.isPresent())
		{
			bean.get().setNazivDodatneUsluge(updateDto.getNazivDodatneUsluge());
			
			uslugeRepo.save(bean.get());
			
			return bean.get();
		}
		else
			return null;
	}


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
