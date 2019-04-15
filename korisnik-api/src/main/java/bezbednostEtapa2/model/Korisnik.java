package bezbednostEtapa2.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import bezbednostEtapa2.validation.EmailValidation;
import bezbednostEtapa2.validation.StaticData;


@Entity
public class Korisnik {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
	private String ime;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
	private String prezime;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaPMB, max=StaticData.maximalnaDuzinaPMB)
	private Long poslovniMaticniBroj;
	
	@NotNull
	@CreationTimestamp
	private Date datumClanstva;
	
	@NotNull
	private String lozinka;
	
	@EmailValidation
	@NotNull
    @Size(min=10, max=60)
	private String email;
	
	private boolean aktiviranNalog;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vlasnik")
	private Set<Smestaj> smestaji = new HashSet<>();

	public Korisnik() {}

	public Korisnik(Long id, String ime, String prezime, Long poslovniMaticniBroj, Date datumClanstva, String lozinka,
			String email, boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = datumClanstva;
		this.lozinka = lozinka;
		this.email = email;
		this.aktiviranNalog = aktiviranNalog;
		this.smestaji = new HashSet<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Set<Smestaj> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(Set<Smestaj> smestaji) {
		this.smestaji = smestaji;
	}
	
}
