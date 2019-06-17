package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.KomentarDTO;
import io.xws.adminservice.model.Komentar;

public interface IKomentarService {

	public List<KomentarDTO> getAllNeobjavljeniKomentari();

	public boolean updateObjaviKomentar(Long id);

	public boolean blockKomentar(Long id);
	
	public Komentar createKomentar(KomentarDTO komentar);

	public List<KomentarDTO> getAllObjavljeniKomentari();

}
