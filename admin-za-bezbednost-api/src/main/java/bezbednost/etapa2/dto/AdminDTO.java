package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Admin;

public class AdminDTO {
	
	private Long id;
	private String username;
	private String password;
		
	public AdminDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public AdminDTO(Admin admin) {
		super();
		this.id = admin.getId();
		this.username = admin.getUsername();
		this.password = admin.getPassword();
	}
	
	public AdminDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
