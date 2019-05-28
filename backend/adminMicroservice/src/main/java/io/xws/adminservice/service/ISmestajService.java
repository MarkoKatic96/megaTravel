package io.xws.adminservice.service;

import io.xws.adminservice.dto.DodatneUslugeDTO;
import io.xws.adminservice.dto.KategorijaSmestajaDTO;
import io.xws.adminservice.dto.TipSmestajaDTO;

public interface ISmestajService 
{

	public TipSmestajaDTO createTipSmestaja(TipSmestajaDTO dto);

	public TipSmestajaDTO updateTipSmestaja(Long id, TipSmestajaDTO updateDto);
	
	public boolean deleteTipSmestaja(Long idToDelete);

	public KategorijaSmestajaDTO createKategorijaSmestaja(KategorijaSmestajaDTO dto);

	public KategorijaSmestajaDTO updateKategorijaSmestaja(Long id, KategorijaSmestajaDTO updateDto);

	public boolean deleteKategorijaSmestaja(Long idToDelete);

	public DodatneUslugeDTO createDodatnaUsluga(DodatneUslugeDTO dto);

	public DodatneUslugeDTO updateDodatnaUsluga(Long id, DodatneUslugeDTO updateDto);

	public boolean deleteDodatnaUsluga(Long idToDelete);
	
}
