package megatravel.bezbednost.dto;

public class AdminPrijavaDTO {
	private String email;
	private String lozinka;

	public AdminPrijavaDTO() {

	}

	public AdminPrijavaDTO(String email, String lozinka) {
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
