package io.webxml.korisnikservice.model;

import java.math.BigDecimal;
import java.math.BigInteger;
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
    private BigInteger maxOsoba;
    
    @XmlElement(required = true)
    private BigInteger maxDanaZaOtkazivanje;
    
    @XmlElement(required = true)
    private BigDecimal cenaProlece;
    
    @XmlElement(required = true)
    private BigDecimal cenaLeto;
    
    @XmlElement(required = true)
    private BigDecimal cenaJesen;
    
    @XmlElement(required = true)
    private BigDecimal cenaZima;
    
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
			KategorijaSmestaja kategorijaSmestaja, String opis, BigInteger maxOsoba, BigInteger maxDanaZaOtkazivanje,
			BigDecimal cenaProlece, BigDecimal cenaLeto, BigDecimal cenaJesen, BigDecimal cenaZima,
			@NotNull Long vlasnik, Set<DodatneUsluge> listaDodatnihUsluga, Set<TImage> listaSlika) {
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
	
	/**
     * Gets the value of the idSmestaja property.
     * 
     */
    public Long getIdSmestaja() {
        return idSmestaja;
    }

    /**
     * Sets the value of the idSmestaja property.
     * 
     */
    public void setIdSmestaja(Long value) {
        this.idSmestaja = value;
    }

    /**
     * Gets the value of the adresa property.
     * 
     * @return
     *     possible object is
     *     {@link TAdresa }
     *     
     */
    public TAdresa getAdresa() {
        return adresa;
    }

    /**
     * Sets the value of the adresa property.
     * 
     * @param value
     *     allowed object is
     *     {@link TAdresa }
     *     
     */
    public void setAdresa(TAdresa value) {
        this.adresa = value;
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

	/**
     * Gets the value of the tipSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link TipSmestaja }
     *     
     */
    public TipSmestaja getTipSmestaja() {
        return tipSmestaja;
    }

    /**
     * Sets the value of the tipSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link TipSmestaja }
     *     
     */
    public void setTipSmestaja(TipSmestaja value) {
        this.tipSmestaja = value;
    }

    /**
     * Gets the value of the kategorijaSmestaja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public KategorijaSmestaja getKategorijaSmestaja() {
        return kategorijaSmestaja;
    }

    /**
     * Sets the value of the kategorijaSmestaja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKategorijaSmestaja(KategorijaSmestaja value) {
        this.kategorijaSmestaja = value;
    }

    /**
     * Gets the value of the opis property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpis() {
        return opis;
    }

    /**
     * Sets the value of the opis property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpis(String value) {
        this.opis = value;
    }

    /**
     * Gets the value of the maxOsoba property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxOsoba() {
        return maxOsoba;
    }

    /**
     * Sets the value of the maxOsoba property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxOsoba(BigInteger value) {
        this.maxOsoba = value;
    }

    /**
     * Gets the value of the maxDanaZaOtkazivanje property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxDanaZaOtkazivanje() {
        return maxDanaZaOtkazivanje;
    }

    /**
     * Sets the value of the maxDanaZaOtkazivanje property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxDanaZaOtkazivanje(BigInteger value) {
        this.maxDanaZaOtkazivanje = value;
    }

    /**
     * Gets the value of the cenaProlece property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCenaProlece() {
        return cenaProlece;
    }

    /**
     * Sets the value of the cenaProlece property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCenaProlece(BigDecimal value) {
        this.cenaProlece = value;
    }

    /**
     * Gets the value of the cenaLeto property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCenaLeto() {
        return cenaLeto;
    }

    /**
     * Sets the value of the cenaLeto property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCenaLeto(BigDecimal value) {
        this.cenaLeto = value;
    }

    /**
     * Gets the value of the cenaJesen property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCenaJesen() {
        return cenaJesen;
    }

    /**
     * Sets the value of the cenaJesen property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCenaJesen(BigDecimal value) {
        this.cenaJesen = value;
    }

    /**
     * Gets the value of the cenaZima property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCenaZima() {
        return cenaZima;
    }

    /**
     * Sets the value of the cenaZima property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCenaZima(BigDecimal value) {
        this.cenaZima = value;
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
}
