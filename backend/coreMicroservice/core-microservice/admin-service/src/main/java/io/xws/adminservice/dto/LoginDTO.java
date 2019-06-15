package io.xws.adminservice.dto;

import lombok.Data;

@Data
public class LoginDTO
{
	protected String email;
    protected String lozinka;
	public String getEmail() {
		return email;
	}
	public String getLozinka() {
		return lozinka;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}
}
