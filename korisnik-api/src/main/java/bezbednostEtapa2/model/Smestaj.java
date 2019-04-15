package bezbednostEtapa2.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import bezbednostEtapa2.validation.StaticData;



@Entity
public class Smestaj {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
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
	
	public Smestaj() {}
	
	public Smestaj(Long id, @NotNull @Size(min = 1, max = 50) String adresa, @NotNull Korisnik vlasnik,
			@NotNull Double cena, @NotNull String opis, @NotNull Integer maxOsoba) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.vlasnik = vlasnik;
		this.cena = cena;
		this.opis = opis;
		this.maxOsoba = maxOsoba;
	}

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
