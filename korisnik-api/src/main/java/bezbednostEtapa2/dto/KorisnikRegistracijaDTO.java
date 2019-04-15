package bezbednostEtapa2.dto;

import java.sql.Date;

import bezbednostEtapa2.model.Korisnik;


public class KorisnikRegistracijaDTO {
	private Long id;
	private String ime;
	private String prezime;
	private Long poslovniMaticniBroj;
	private Date datumClanstva;
	private String email;
	private String lozinka;
	private boolean aktiviranNalog;
	
	public KorisnikRegistracijaDTO(Long id, String ime, String prezime, Long poslovniMaticniBroj, Date datumClanstva,
			String email, String lozinka, boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = datumClanstva;
		this.email = email;
		this.lozinka = lozinka;
		this.aktiviranNalog = aktiviranNalog;
	}

	public KorisnikRegistracijaDTO(Korisnik agent) {
		super();
		this.id = agent.getId();
		this.ime = agent.getIme();
		this.prezime = agent.getPrezime();
		this.poslovniMaticniBroj = agent.getPoslovniMaticniBroj();
		this.datumClanstva = agent.getDatumClanstva();
		this.email = agent.getEmail();
		this.lozinka = agent.getLozinka();
		this.aktiviranNalog = agent.isAktiviranNalog();
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

	public Long getPoslovniMaticniBroj() {
		return poslovniMaticniBroj;
	}

	public void setPoslovniMaticniBroj(Long poslovniMaticniBroj) {
		this.poslovniMaticniBroj = poslovniMaticniBroj;
	}

	public Date getDatumClanstva() {
		return datumClanstva;
	}

	public void setDatumClanstva(Date datumClanstva) {
		this.datumClanstva = datumClanstva;
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

	public boolean isAktiviranNalog() {
		return aktiviranNalog;
	}

	public void setAktiviranNalog(boolean aktiviranNalog) {
		this.aktiviranNalog = aktiviranNalog;
	}
	
}
