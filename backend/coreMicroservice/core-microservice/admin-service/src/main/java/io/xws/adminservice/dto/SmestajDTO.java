package io.xws.adminservice.dto;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import lombok.Data;

@Data
public class SmestajDTO 
{
	protected long idSmestaja;
    protected String adresa;
    protected float longitude;
    protected float latitude;
    protected TipSmestajaDTO tipSmestaja;
    protected KategorijaSmestajaDTO kategorijaSmestaja;
    protected String opis;
    protected BigInteger maxOsoba;
    protected BigInteger maxDanaZaOtkazivanje;
    protected BigDecimal cenaProlece;
    protected BigDecimal cenaLeto;
    protected BigDecimal cenaJesen;
    protected BigDecimal cenaZima;
    protected long vlasnik;
    protected List<DodatneUslugeDTO> listaDodatnihUsluga;
//    protected Smestaj.ListaSlika listaSlika;
	public long getIdSmestaja() {
		return idSmestaja;
	}
	public String getAdresa() {
		return adresa;
	}
	public float getLongitude() {
		return longitude;
	}
	public float getLatitude() {
		return latitude;
	}
	public TipSmestajaDTO getTipSmestaja() {
		return tipSmestaja;
	}
	public KategorijaSmestajaDTO getKategorijaSmestaja() {
		return kategorijaSmestaja;
	}
	public String getOpis() {
		return opis;
	}
	public BigInteger getMaxOsoba() {
		return maxOsoba;
	}
	public BigInteger getMaxDanaZaOtkazivanje() {
		return maxDanaZaOtkazivanje;
	}
	public BigDecimal getCenaProlece() {
		return cenaProlece;
	}
	public BigDecimal getCenaLeto() {
		return cenaLeto;
	}
	public BigDecimal getCenaJesen() {
		return cenaJesen;
	}
	public BigDecimal getCenaZima() {
		return cenaZima;
	}
	public long getVlasnik() {
		return vlasnik;
	}
	public List<DodatneUslugeDTO> getListaDodatnihUsluga() {
		return listaDodatnihUsluga;
	}
	public void setIdSmestaja(long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}
	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	public void setTipSmestaja(TipSmestajaDTO tipSmestaja) {
		this.tipSmestaja = tipSmestaja;
	}
	public void setKategorijaSmestaja(KategorijaSmestajaDTO kategorijaSmestaja) {
		this.kategorijaSmestaja = kategorijaSmestaja;
	}
	public void setOpis(String opis) {
		this.opis = opis;
	}
	public void setMaxOsoba(BigInteger maxOsoba) {
		this.maxOsoba = maxOsoba;
	}
	public void setMaxDanaZaOtkazivanje(BigInteger maxDanaZaOtkazivanje) {
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
	}
	public void setCenaProlece(BigDecimal cenaProlece) {
		this.cenaProlece = cenaProlece;
	}
	public void setCenaLeto(BigDecimal cenaLeto) {
		this.cenaLeto = cenaLeto;
	}
	public void setCenaJesen(BigDecimal cenaJesen) {
		this.cenaJesen = cenaJesen;
	}
	public void setCenaZima(BigDecimal cenaZima) {
		this.cenaZima = cenaZima;
	}
	public void setVlasnik(long vlasnik) {
		this.vlasnik = vlasnik;
	}
	public void setListaDodatnihUsluga(List<DodatneUslugeDTO> listaDodatnihUsluga) {
		this.listaDodatnihUsluga = listaDodatnihUsluga;
	}
}
