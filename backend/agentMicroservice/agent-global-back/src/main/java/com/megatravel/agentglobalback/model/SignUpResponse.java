package com.megatravel.agentglobalback.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "signUpResponse")
public class SignUpResponse {
	
	@XmlElement(required = true)
	protected NeaktiviranAgent neaktiviranAgent;

	public NeaktiviranAgent getNeaktiviranAgent() {
		return neaktiviranAgent;
	}

	public void setNeaktiviranAgent(NeaktiviranAgent neaktiviranAgent) {
		this.neaktiviranAgent = neaktiviranAgent;
	}
	
	
	
}
