package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.model.NeaktiviranAgent;

public interface IAgentService
{
	
	List<NeaktiviranAgent> getAllZahteviNeregAgenata();

	public String createPotvrdiZahtev(Long id);

	public boolean deleteOdbijZahtev(Long id);

}
