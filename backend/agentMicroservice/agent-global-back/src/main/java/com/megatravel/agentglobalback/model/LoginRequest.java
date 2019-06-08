package com.megatravel.agentglobalback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentglobalback.dto.AgentPrijavaDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "loginRequest")
public class LoginRequest {

	@XmlElement(required = true)
	protected AgentPrijavaDTO agentPrijavaDTO;

	public AgentPrijavaDTO getAgentPrijavaDTO() {
		return agentPrijavaDTO;
	}

	public void setAgentPrijavaDTO(AgentPrijavaDTO agentPrijavaDTO) {
		this.agentPrijavaDTO = agentPrijavaDTO;
	}
	
	
	
}
