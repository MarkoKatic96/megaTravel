package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.AgentDTO;
import io.xws.adminservice.dto.NeregistrovaniAgentDTO;

public interface IAgentService
{

	public List<AgentDTO> getAllZahteviAgenata();

	public boolean createPotvrdiZahtev(NeregistrovaniAgentDTO zahtev);

	public boolean deleteOdbijZahtev(NeregistrovaniAgentDTO zahtev);

}
