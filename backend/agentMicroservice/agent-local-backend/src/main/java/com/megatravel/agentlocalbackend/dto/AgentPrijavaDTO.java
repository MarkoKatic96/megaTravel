package com.megatravel.agentlocalbackend.dto;

public class AgentPrijavaDTO {
	private String email;
	private String lozinka;

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
