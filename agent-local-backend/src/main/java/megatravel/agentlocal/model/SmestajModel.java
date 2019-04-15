package megatravel.agentlocal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import megatravel.agentlocal.validation.StaticData;

@Entity
public class SmestajModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
    @Size(min=StaticData.minimalnaDuzinaImena, max=StaticData.maximalnaDuzinaImena)
	private String adresa;
	
	@NotNull
	@ManyToOne()
	private AgentModel vlasnik;
	
	@NotNull
	private Double cena;
	
	@NotNull
	private String opis;
	
	@NotNull
	private Integer maxOsoba;

	public SmestajModel(Long id, String adresa, AgentModel vlasnik, Double cena, String opis, Integer maxOsoba) {
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

	public AgentModel getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(AgentModel vlasnik) {
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
