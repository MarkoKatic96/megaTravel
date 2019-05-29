package io.xws.adminservice.service;

import java.util.List;

import io.xws.adminservice.dto.AgentDTO;

public interface IAgentService
{

	public List<AgentDTO> getAllZahteviAgenata();

	public boolean createPotvrdiZahtev(AgentDTO zahtev);

}
