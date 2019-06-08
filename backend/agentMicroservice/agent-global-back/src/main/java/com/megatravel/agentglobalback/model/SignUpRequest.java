package com.megatravel.agentglobalback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentglobalback.dto.AgentRegistracijaDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "signUpRequest")
public class SignUpRequest {

	@XmlElement(required = true)
	protected AgentRegistracijaDTO agentRegistracijaDTO;

	public AgentRegistracijaDTO getAgentRegistracijaDTO() {
		return agentRegistracijaDTO;
	}

	public void setAgentRegistracijaDTO(AgentRegistracijaDTO agentRegistracijaDTO) {
		this.agentRegistracijaDTO = agentRegistracijaDTO;
	}
	
	
	
}
