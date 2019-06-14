package com.megatravel.agentlocalbackend.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentlocalbackend.wsdl.GetAgentByEmailResponse.Agent;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "agent"
})
@XmlRootElement(name = "editRequest")
public class EditRequest {

	@XmlElement(required = true)
	protected Agent agent;

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}
}
