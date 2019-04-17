package bezbednost.etapa2.dto;

import bezbednost.etapa2.model.Korisnik;
import bezbednost.etapa2.model.SmestajModel;

public class SmestajDTO {

	private Long id;
	private String adresa;
	private Double cena;
	private String opis;
	private Integer maxOsoba;
	private Korisnik vlasnik;
	
	public SmestajDTO(SmestajModel s) {
		super();
		this.id = s.getId();
		this.adresa = s.getAdresa();
		this.cena = s.getCena();
		this.opis = s.getOpis();
		this.maxOsoba = s.getMaxOsoba();
		this.vlasnik = s.getVlasnik();
	}

	public SmestajDTO(Long id, String adresa, Double cena, String opis, Integer maxOsoba, Korisnik vlasnik) {
		super();
		this.id = id;
		this.adresa = adresa;
		this.cena = cena;
		this.opis = opis;
		this.maxOsoba = maxOsoba;
		this.vlasnik = vlasnik;
	}

	public SmestajDTO() {
		// TODO Auto-generated constructor stub
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

	public Korisnik getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Korisnik vlasnik) {
		this.vlasnik = vlasnik;
	}


	
}

