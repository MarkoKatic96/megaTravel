package com.megatravel.agentglobalback.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "agentPrijavaDTO", propOrder = {
    "email",
    "lozinka"
})
public class AgentPrijavaDTO {
	@XmlElement(namespace="https://megatravel.com/email")
    protected String email;
	@XmlElement(namespace="https://megatravel.com/lozinka")
    protected String lozinka;

	public AgentPrijavaDTO() {
	}

	public AgentPrijavaDTO(String email, String lozinka) {
		super();
		this.email = email;
		this.lozinka = lozinka;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
