package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.AgentDTO;
import io.xws.adminservice.dto.NeregistrovaniAgentDTO;
import io.xws.adminservice.model.NeregistrovaniAgent;

public interface IAgentService
{
	
	List<NeregistrovaniAgent> getAllZahteviNeregAgenata();

	public String createPotvrdiZahtev(Long id);

	public boolean deleteOdbijZahtev(Long id);

}
