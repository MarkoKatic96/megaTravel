package com.megatravel.smestajservice.dto;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import com.megatravel.smestajservice.model.DodatneUsluge;
import com.megatravel.smestajservice.model.KategorijaSmestaja;
import com.megatravel.smestajservice.model.Smestaj;
import com.megatravel.smestajservice.model.TAdresa;
import com.megatravel.smestajservice.model.TImage;
import com.megatravel.smestajservice.model.TipSmestaja;

public class SmestajDTO {
	
	private Long idSmestaja;
    private TAdresa adresa;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private TipSmestaja tipSmestaja;
    private KategorijaSmestaja kategorijaSmestaja;
    private String opis;
    private int maxOsoba;
    private int maxDanaZaOtkazivanje;
    private float cenaProlece;
    private float cenaLeto;
    private float cenaJesen;
    private float cenaZima;
    private Long vlasnik;
    private Set<DodatneUsluge> listaDodatnihUsluga = new HashSet<>();
    private Set<TImage> listaSlika = new HashSet<>();
    
    public SmestajDTO() {
	}

    public SmestajDTO(Smestaj s) {
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
		this.cenaProlece = s.getCenaProlece();
		this.cenaLeto = s.getCenaLeto();
		this.cenaJesen = s.getCenaJesen();
		this.cenaZima = s.getCenaZima();
		this.vlasnik = s.getVlasnik();
		this.listaDodatnihUsluga = s.getListaDodatnihUsluga();
		this.listaSlika = s.getListaSlika();
	}
 
	public SmestajDTO(Long idSmestaja, TAdresa adresa, BigDecimal latitude, BigDecimal longitude,
			TipSmestaja tipSmestaja, KategorijaSmestaja kategorijaSmestaja, String opis, int maxOsoba,
			int maxDanaZaOtkazivanje, float cenaProlece, float cenaLeto, float cenaJesen,
			float cenaZima, Long vlasnik, Set<DodatneUsluge> listaDodatnihUsluga, Set<TImage> listaSlika) {
		super();
		this.idSmestaja = idSmestaja;
		this.adresa = adresa;
		this.latitude = latitude;
		this.longitude = longitude;
		this.tipSmestaja = tipSmestaja;
		this.kategorijaSmestaja = kategorijaSmestaja;
		this.opis = opis;
		this.maxOsoba = maxOsoba;
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
		this.cenaProlece = cenaProlece;
		this.cenaLeto = cenaLeto;
		this.cenaJesen = cenaJesen;
		this.cenaZima = cenaZima;
		this.vlasnik = vlasnik;
		this.listaDodatnihUsluga = listaDodatnihUsluga;
		this.listaSlika = listaSlika;
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

	public float getCenaProlece() {
		return cenaProlece;
	}

	public void setCenaProlece(float cenaProlece) {
		this.cenaProlece = cenaProlece;
	}

	public float getCenaLeto() {
		return cenaLeto;
	}

	public void setCenaLeto(float cenaLeto) {
		this.cenaLeto = cenaLeto;
	}

	public float getCenaJesen() {
		return cenaJesen;
	}

	public void setCenaJesen(float cenaJesen) {
		this.cenaJesen = cenaJesen;
	}

	public float getCenaZima() {
		return cenaZima;
	}

	public void setCenaZima(float cenaZima) {
		this.cenaZima = cenaZima;
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
