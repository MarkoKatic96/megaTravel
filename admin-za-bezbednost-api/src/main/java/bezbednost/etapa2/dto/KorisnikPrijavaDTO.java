package bezbednost.etapa2.dto;

public class KorisnikPrijavaDTO {
	
	private String email;
	private String lozinka;

	public KorisnikPrijavaDTO() {

	}

	public KorisnikPrijavaDTO(String email, String lozinka) {
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
