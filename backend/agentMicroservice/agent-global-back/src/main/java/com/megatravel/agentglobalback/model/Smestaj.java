package com.megatravel.agentglobalback.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.megatravel.agentglobalback.dto.SmestajDTO;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "idSmestaja",
    "adresa",
    "koordinate",
    "tipSmestaja",
    "kategorijaSmestaja",
    "opis",
    "maxOsoba",
    "maxDanaZaOtkazivanje",
    "cenaProlece",
    "cenaLeto",
    "cenaJesen",
    "cenaZima",
    "vlasnik",
    "listaDodatnihUsluga",
    "listaSlika"
})
@XmlRootElement(name = "Smestaj")
@Entity
public class Smestaj {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSmestaja;
	
    @XmlElement(required = true)
    @OneToOne
    @JoinColumn(name = "adresa_id")
    private TAdresa adresa;
    
    @XmlElement(required = true)
    private BigDecimal latitude;
    
    @XmlElement(required = true)
    private BigDecimal longitude;
    
    @XmlElement(required = true)
    @Enumerated(EnumType.STRING)
    @ManyToOne
    @JoinColumn(name="tipsmestaja_id", nullable = false)
    private TipSmestaja tipSmestaja;
    
    @XmlElement(required = true)
    @ManyToOne
    @JoinColumn(name="kategorijasmestaja_id", nullable = false)
    private KategorijaSmestaja kategorijaSmestaja;
    
    @XmlElement(required = true)
    private String opis;
    
    @XmlElement(required = true)
    private int maxOsoba;
    
    @XmlElement(required = true)
    private int maxDanaZaOtkazivanje;
    
    @XmlElement(required = true)
    private float cenaProlece;
    
    @XmlElement(required = true)
    private float cenaLeto;
    
    @XmlElement(required = true)
    private float cenaJesen;
    
    @XmlElement(required = true)
    private float cenaZima;
    
    @NotNull
    private Long vlasnik;
    
    @XmlList
    @XmlElement(required = true)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "smestaj_usluge", joinColumns = @JoinColumn(name="smestaj_id"), inverseJoinColumns = @JoinColumn(name="tipsmestaja_id"))
    private Set<DodatneUsluge> listaDodatnihUsluga = new HashSet<>();
    
    @XmlList
    @XmlElement(required = true)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "smestaj")
    private Set<TImage> listaSlika = new HashSet<>();
    
    public Smestaj() {
	}

	public Smestaj(Long idSmestaja, TAdresa adresa, BigDecimal latitude, BigDecimal longitude, TipSmestaja tipSmestaja,
			KategorijaSmestaja kategorijaSmestaja, String opis, int maxOsoba, int maxDanaZaOtkazivanje, int cenaProlece,
			int cenaLeto, int cenaJesen, int cenaZima, @NotNull Long vlasnik, Set<DodatneUsluge> listaDodatnihUsluga,
			Set<TImage> listaSlika) {
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

	/**
     * Gets the value of the vlasnik property.
     * 
     */
    public Long getVlasnik() {
        return vlasnik;
    }

    /**
     * Sets the value of the vlasnik property.
     * 
     */
    public void setVlasnik(Long value) {
        this.vlasnik = value;
    }

    /**
     * Gets the value of the listaDodatnihUsluga property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listaDodatnihUsluga property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListaDodatnihUsluga().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DodatneUsluge }
     * 
     * 
     */
    public Set<DodatneUsluge> getListaDodatnihUsluga() {
        if (listaDodatnihUsluga == null) {
            listaDodatnihUsluga = new HashSet<DodatneUsluge>();
        }
        return this.listaDodatnihUsluga;
    }

    public void setListaDodatnihUsluga(Set<DodatneUsluge> listaDodatnihUsluga) {
		this.listaDodatnihUsluga = listaDodatnihUsluga;
	}

	/**
     * Gets the value of the listaSlika property.
     * 
     * @return
     *     possible object is
     *     {@link Smestaj.ListaSlika }
     *     
     */
    public Set<TImage> getListaSlika() {
        return listaSlika;
    }

    /**
     * Sets the value of the listaSlika property.
     * 
     * @param value
     *     allowed object is
     *     {@link Smestaj.ListaSlika }
     *     
     */
    public void setListaSlika(Set<TImage> value) {
        this.listaSlika = value;
    }

    public void update(SmestajDTO s) {
		this.idSmestaja = s.getIdSmestaja();
		this.adresa = s.getAdresa();
		this.longitude = s.getLongitude();
		this.latitude = s.getLatitude();
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
}
