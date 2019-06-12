package com.megatravel.smestajservice.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.megatravel.smestajservice.model.DodatneUsluge;
import com.megatravel.smestajservice.model.KategorijaSmestaja;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.TAdresa;
import com.megatravel.smestajservice.model.TImage;
import com.megatravel.smestajservice.model.TipSmestaja;

public class SmestajKorisnikDTO {
	
	private Long idSmestaja;
    private TAdresa adresa;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private TipSmestaja tipSmestaja;
    private KategorijaSmestaja kategorijaSmestaja;
    private String opis;
    private int maxOsoba;
    private int maxDanaZaOtkazivanje;
    private float cena;
    private Long vlasnik;
    private Set<DodatneUsluge> listaDodatnihUsluga = new HashSet<>();
    private Set<TImage> listaSlika = new HashSet<>();
    
    public SmestajKorisnikDTO() {
	}

    public SmestajKorisnikDTO(Smestaj s) {
		super();
		this.idSmestaja = s.getIdSmestaja();
		this.adresa = s.getAdresa();
		this.latitude = s.getLatitude();
		this.longitude = s.getLongitude();
		this.tipSmestaja = s.getTipSmestaja();
		this.kategorijaSmestaja = s.getKategorijaSmestaja();
		this.opis = s.getOpis();
		this.maxOsoba = s.getMaxOsoba();
		this.maxDanaZaOtkazivanje = s.getMaxDanaZaOtkazivanje();
		this.cena = getTrenutnaCena(s.getCenaProlece(), s.getCenaLeto(), s.getCenaJesen(), s.getCenaZima());
		this.vlasnik = s.getVlasnik();
		this.listaDodatnihUsluga = s.getListaDodatnihUsluga();
		this.listaSlika = s.getListaSlika();
	}
 
	private float getTrenutnaCena(float cenaProlece, float cenaLeto, float cenaJesen, float cenaZima) {
		Date now = new Date(System.currentTimeMillis());
		int dayOfYear = Calendar.getInstance().get(Calendar.DAY_OF_YEAR);
		System.out.println("- now: " + now + "\n\t- dan u godini: " + dayOfYear);
		
		//zima
		if (dayOfYear<90 || dayOfYear>334) {
			return cenaZima;
		}
		if (dayOfYear<181) {
			return cenaProlece;
		}
		if (dayOfYear<244) {
			return cenaLeto;
		}
		return cenaJesen;
	}

	public Long getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(Long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public TAdresa getAdresa() {
		return adresa;
	}

	public void setAdresa(TAdresa adresa) {
		this.adresa = adresa;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public TipSmestaja getTipSmestaja() {
		return tipSmestaja;
	}

	public void setTipSmestaja(TipSmestaja tipSmestaja) {
		this.tipSmestaja = tipSmestaja;
	}

	public KategorijaSmestaja getKategorijaSmestaja() {
		return kategorijaSmestaja;
	}

	public void setKategorijaSmestaja(KategorijaSmestaja kategorijaSmestaja) {
		this.kategorijaSmestaja = kategorijaSmestaja;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public int getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(int maxOsoba) {
		this.maxOsoba = maxOsoba;
	}

	public int getMaxDanaZaOtkazivanje() {
		return maxDanaZaOtkazivanje;
	}

	public void setMaxDanaZaOtkazivanje(int maxDanaZaOtkazivanje) {
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

	public Long getVlasnik() {
		return vlasnik;
	}

	public void setVlasnik(Long vlasnik) {
		this.vlasnik = vlasnik;
	}

	public Set<DodatneUsluge> getListaDodatnihUsluga() {
		return listaDodatnihUsluga;
	}

	public void setListaDodatnihUsluga(Set<DodatneUsluge> listaDodatnihUsluga) {
		this.listaDodatnihUsluga = listaDodatnihUsluga;
	}

	public Set<TImage> getListaSlika() {
		return listaSlika;
	}

	public void setListaSlika(Set<TImage> listaSlika) {
		this.listaSlika = listaSlika;
	}
    
    
}
