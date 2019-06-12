package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.KorisnikDTO;

public interface IKorisnikService
{
	
	public List<KorisnikDTO> getAllKorisnici();
	
	public List<KorisnikDTO> getAllAktiviraniKorisnici();

	public List<KorisnikDTO> getAllNeaktiviraniKorisnici();
	
	public List<KorisnikDTO> getAllBlokiraniKorisnici();
	
	public boolean updateAktivirajKorisnika(Long id);

	public boolean updateBlokirajKorisnika(Long id);

	public boolean deleteKorisnika(Long id);

	

	

	

	
	
}
