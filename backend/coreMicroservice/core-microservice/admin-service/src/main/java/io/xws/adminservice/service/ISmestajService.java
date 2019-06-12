package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.dto.KategorijaSmestajaDTO;
import io.xws.adminservice.dto.TipSmestajaDTO;

public interface ISmestajService 
{
	public List<TipSmestajaDTO> getAllTipoviSmestaja();
	
	public TipSmestajaDTO createTipSmestaja(TipSmestajaDTO dto);

	public TipSmestajaDTO updateTipSmestaja(Long id, TipSmestajaDTO updateDto);
	
	public boolean deleteTipSmestaja(Long idToDelete);

	public List<KategorijaSmestajaDTO> getAllKategorijeSmestaja();

	public KategorijaSmestajaDTO createKategorijaSmestaja(KategorijaSmestajaDTO dto);

	public KategorijaSmestajaDTO updateKategorijaSmestaja(Long id, KategorijaSmestajaDTO updateDto);

	public boolean deleteKategorijaSmestaja(Long idToDelete);
	
	public List<DodatneUslugeDTO> getAllDodatneUsluge();

	public DodatneUslugeDTO createDodatnaUsluga(DodatneUslugeDTO dto);

	public DodatneUslugeDTO updateDodatnaUsluga(Long id, DodatneUslugeDTO updateDto);

	public boolean deleteDodatnaUsluga(Long idToDelete);



	
}
