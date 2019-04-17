package megatravel.bezbednost.dto;

import megatravel.bezbednost.model.AdminModel;

public class AdminDTO {
	private Long id;
	private String ime;
	private String prezime;
	private String email;
	private boolean aktiviranNalog;
	
	public AdminDTO(String ime, String prezime, String email, boolean aktiviranNalog) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.aktiviranNalog = aktiviranNalog;
	}

	public AdminDTO(AdminModel korisnik) {
		super();
		this.id = korisnik.getId();
		this.ime = korisnik.getIme();
		this.prezime = korisnik.getPrezime();
		this.email = korisnik.getEmail();
		this.aktiviranNalog = korisnik.isAktiviranNalog();
	}
	
	public AdminDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isAktiviranNalog() {
		return aktiviranNalog;
	}

	public void setAktiviranNalog(boolean aktiviranNalog) {
		this.aktiviranNalog = aktiviranNalog;
	}
}
