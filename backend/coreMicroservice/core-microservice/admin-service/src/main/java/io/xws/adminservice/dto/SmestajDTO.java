package io.xws.adminservice.dto;

import java.util.List;

import lombok.Data;

@Data
public class SmestajDTO 
{
	protected long idSmestaja;
    protected TAdresaDTO adresa;
    protected TKoordinateDTO koordinate;
    protected TipSmestajaDTO tipSmestaja;
    protected KategorijaSmestajaDTO kategorijaSmestaja;
    protected String opis;
    protected int maxOsoba;
    protected int maxDanaZaOtkazivanje;
    protected float cenaProlece;
    protected float cenaLeto;
    protected float cenaJesen;
    protected float cenaZima;
    protected long vlasnik;
    protected List<DodatneUslugeDTO> listaDodatnihUsluga;
//    protected Smestaj.ListaSlika listaSlika;
	public long getIdSmestaja() {
		return idSmestaja;
	}
	public TAdresaDTO getAdresa() {
		return adresa;
	}
	public TKoordinateDTO getKoordinate() {
		return koordinate;
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
	public int getMaxOsoba() {
		return maxOsoba;
	}
	public int getMaxDanaZaOtkazivanje() {
		return maxDanaZaOtkazivanje;
	}
	public float getCenaProlece() {
		return cenaProlece;
	}
	public float getCenaLeto() {
		return cenaLeto;
	}
	public float getCenaJesen() {
		return cenaJesen;
	}
	public float getCenaZima() {
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
	public void setAdresa(TAdresaDTO adresa) {
		this.adresa = adresa;
	}
	public void setKoordinate(TKoordinateDTO koordinate) {
		this.koordinate = koordinate;
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
	public void setMaxOsoba(int maxOsoba) {
		this.maxOsoba = maxOsoba;
	}
	public void setMaxDanaZaOtkazivanje(int maxDanaZaOtkazivanje) {
		this.maxDanaZaOtkazivanje = maxDanaZaOtkazivanje;
	}
	public void setCenaProlece(float cenaProlece) {
		this.cenaProlece = cenaProlece;
	}
	public void setCenaLeto(float cenaLeto) {
		this.cenaLeto = cenaLeto;
	}
	public void setCenaJesen(float cenaJesen) {
		this.cenaJesen = cenaJesen;
	}
	public void setCenaZima(float cenaZima) {
		this.cenaZima = cenaZima;
	}
	public void setVlasnik(long vlasnik) {
		this.vlasnik = vlasnik;
	}
	public void setListaDodatnihUsluga(List<DodatneUslugeDTO> listaDodatnihUsluga) {
		this.listaDodatnihUsluga = listaDodatnihUsluga;
	}
}
