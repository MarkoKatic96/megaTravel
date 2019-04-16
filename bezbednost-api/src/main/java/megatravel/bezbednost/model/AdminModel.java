package megatravel.bezbednost.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import megatravel.bezbednost.validation.EmailValidation;
import megatravel.bezbednost.validation.StaticData;

@Entity
public class AdminModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
	private String ime;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
	private String prezime;
	
	@NotNull
	private String lozinka;
	
	@EmailValidation
	@NotNull
    @Size(min=10, max=60)
	private String email;
	
	private boolean aktiviranNalog;

	public AdminModel(Long id, String ime, String prezime, String lozinka, String email, boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.lozinka = lozinka;
		this.email = email;
		this.aktiviranNalog = aktiviranNalog;
	}
	
	public AdminModel() {
	
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

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
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
