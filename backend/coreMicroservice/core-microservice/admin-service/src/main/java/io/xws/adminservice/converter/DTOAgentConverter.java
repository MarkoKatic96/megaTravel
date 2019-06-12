package io.xws.adminservice.converter;

import org.springframework.stereotype.Component;

import io.xws.adminservice.dto.AgentDTO;
import io.xws.adminservice.model.Agent;

@Component
public class DTOAgentConverter
{
	public AgentDTO convertToDTO(Agent model)
	{
		AgentDTO dto = new AgentDTO();
		
		dto.setIdAgenta(model.getIdAgenta());
		dto.setIme(model.getIme());
		dto.setPrezime(model.getPrezime());
		dto.setPoslovniMaticniBroj(model.getPoslovniMaticniBroj());
		dto.setDatumClanstva(model.getDatumClanstva());
		dto.setEmail(model.getEmail());
		dto.setLozinka(model.getLozinka());
		
		return dto;
	}
	
	public Agent convertFromDTO(AgentDTO dto)
	{
		Agent bean = new Agent(); 
		
		bean.setIdAgenta(dto.getIdAgenta());
		bean.setIme(dto.getIme());
		bean.setPrezime(dto.getPrezime());
		bean.setPoslovniMaticniBroj(dto.getPoslovniMaticniBroj());
		bean.setDatumClanstva(dto.getDatumClanstva());
		bean.setEmail(dto.getEmail());
		bean.setLozinka(dto.getLozinka());
		
		return bean;
	}
}
