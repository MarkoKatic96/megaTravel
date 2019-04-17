package bezbednost.etapa2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class SmestajModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(min=1, max=100)
	private String adresa;
	
	@NotNull
	@ManyToOne()
	private Korisnik vlasnik;
	
	@NotNull
	private Double cena;
	
	@NotNull
	private String opis;
	
	@NotNull
	private Integer maxOsoba;

	public SmestajModel(Long id, String adresa, Korisnik vlasnik, Double cena, String opis, Integer maxOsoba) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.vlasnik = vlasnik;
		this.cena = cena;
		this.opis = opis;
		this.maxOsoba = maxOsoba;
	}
	
	public SmestajModel() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Integer getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(Integer maxOsoba) {
		this.maxOsoba = maxOsoba;
	}
	
}

