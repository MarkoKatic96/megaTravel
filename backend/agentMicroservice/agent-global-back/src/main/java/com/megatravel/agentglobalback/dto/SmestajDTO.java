package com.megatravel.agentglobalback.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import com.megatravel.agentglobalback.model.DodatneUsluge;
import com.megatravel.agentglobalback.model.KategorijaSmestaja;
import com.megatravel.agentglobalback.model.Smestaj;
import com.megatravel.agentglobalback.model.TAdresa;
import com.megatravel.agentglobalback.model.TImage;
import com.megatravel.agentglobalback.model.TipSmestaja;

public class SmestajDTO {
	
	private Long idSmestaja;
    private TAdresa adresa;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private TipSmestaja tipSmestaja;
    private KategorijaSmestaja kategorijaSmestaja;
    private String opis;
    private BigInteger maxOsoba;
    private BigInteger maxDanaZaOtkazivanje;
    private BigDecimal cenaProlece;
    private BigDecimal cenaLeto;
    private BigDecimal cenaJesen;
    private BigDecimal cenaZima;
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
			TipSmestaja tipSmestaja, KategorijaSmestaja kategorijaSmestaja, String opis, BigInteger maxOsoba,
			BigInteger maxDanaZaOtkazivanje, BigDecimal cenaProlece, BigDecimal cenaLeto, BigDecimal cenaJesen,
			BigDecimal cenaZima, Long vlasnik, Set<DodatneUsluge> listaDodatnihUsluga, Set<TImage> listaSlika) {
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

	public BigInteger getMaxOsoba() {
		return maxOsoba;
	}

	public void setMaxOsoba(BigInteger maxOsoba) {
		this.maxOsoba = maxOsoba;
	}

	public BigInteger getMaxDanaZaOtkazivanje() {
		return maxDanaZaOtkazivanje;
	}

	public void setMaxDanaZaOtkazivanje(BigInteger maxDanaZaOtkazivanje) {
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
	}

	public BigDecimal getCenaProlece() {
		return cenaProlece;
	}

	public void setCenaProlece(BigDecimal cenaProlece) {
		this.cenaProlece = cenaProlece;
	}

	public BigDecimal getCenaLeto() {
		return cenaLeto;
	}

	public void setCenaLeto(BigDecimal cenaLeto) {
		this.cenaLeto = cenaLeto;
	}

	public BigDecimal getCenaJesen() {
		return cenaJesen;
	}

	public void setCenaJesen(BigDecimal cenaJesen) {
		this.cenaJesen = cenaJesen;
	}

	public BigDecimal getCenaZima() {
		return cenaZima;
	}

	public void setCenaZima(BigDecimal cenaZima) {
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
