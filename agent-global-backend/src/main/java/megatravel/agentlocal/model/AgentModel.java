package megatravel.agentlocal.model;

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

import megatravel.agentlocal.validation.StaticData;

@Entity
public class AgentModel {
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
    //@Size(min=StaticData.minimalnaDuzinaPMB, max=StaticData.maximalnaDuzinaPMB)
	private Long poslovniMaticniBroj;
	
	@NotNull
	@CreationTimestamp
	private Date datumClanstva;
	
	@NotNull
	private String lozinka;
	
	//@EmailValidation
	@NotNull
    @Size(min=10, max=60)
	private String email;
	
	private boolean aktiviranNalog;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "vlasnik")
	private Set<SmestajModel> smestaji = new HashSet<>();
	
	public AgentModel(Long id, String ime, String prezime, Long poslovniMaticniBroj, Date datumClanstva, String lozinka,
			String email, boolean aktiviranNalog) {
		super();
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.poslovniMaticniBroj = poslovniMaticniBroj;
		this.datumClanstva = datumClanstva;
		if (datumClanstva==null) {
			datumClanstva = new java.sql.Date(System.currentTimeMillis()); 
		}
		this.lozinka = lozinka;
		this.email = email;
		this.aktiviranNalog = aktiviranNalog;
		this.smestaji = new HashSet<>();
	}

	public AgentModel() {
	
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
		if (datumClanstva==null) {
			datumClanstva = new java.sql.Date(System.currentTimeMillis()); 
		}
	}

	public Set<SmestajModel> getSmestaji() {
		return smestaji;
	}

	public void setSmestaji(Set<SmestajModel> smestaji) {
		this.smestaji = smestaji;
	}
	
	public void addSmestaj(SmestajModel s) {
		if (getSmestaj(s.getId())==null)
			this.smestaji.add(s);
	}
	
	public void removeSmestaj(Long id) {
		SmestajModel s = getSmestaj(id);
		if (s!=null) {
			this.smestaji.remove(s);
		}
	}
	
	public SmestajModel getSmestaj(Long id) {
		for (SmestajModel smestajModel : smestaji) {
			if (smestajModel.getId().equals(id)) {
				return smestajModel;
			}
		}
		return null;
	}
}
