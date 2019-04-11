package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Korisnik;

public class KorisnikDTO {
	private Long id;
	private String username;
	private String password;
	
	public KorisnikDTO(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public KorisnikDTO(Korisnik k) {
		this.id = k.getId();
		this.username = k.getUsername();
		this.password = k.getPassword();
	}
	
	public KorisnikDTO() {
		
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

	
