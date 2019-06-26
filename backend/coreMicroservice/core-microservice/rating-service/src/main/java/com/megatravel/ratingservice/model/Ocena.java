package com.megatravel.ratingservice.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ocena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="idOcena")
    protected Long idOcena;
    
    @NotNull
    @Column(name="idSmestaj")
    protected Long idSmestaj;
    
    @NotNull
    @Column(name="idRezervacija")
    protected Long idRezervacija;
    
    @NotNull
    @Column(name="idKorisnik")
    protected Long idKorisnik;
    
    @NotNull
    protected short ocena;
    
    protected Date timestamp;

    public Ocena() { }
    
    public Ocena(Long idOcena, @NotNull Long idSmestaj, @NotNull Long idRezervacija, @NotNull Long idKorisnik,
			@NotNull short ocena) {
		super();
		this.idOcena = idOcena;
		this.idSmestaj = idSmestaj;
		this.idRezervacija = idRezervacija;
		this.idKorisnik = idKorisnik;
		this.ocena = ocena;
		this.timestamp = new Date(System.currentTimeMillis());
	}

	public Date getTimestamp() {
		return timestamp;
	}
	
	public void setIdOcena(Long idOcena) {
		this.idOcena = idOcena;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
     * Gets the value of the idOcena property.
     * 
     */
    public Long getIdOcena() {
        return idOcena;
    }

    /**
     * Gets the value of the idSmestaj property.
     * 
     */
    public Long getIdSmestaj() {
        return idSmestaj;
    }

    /**
     * Gets the value of the idRezervacija property.
     * 
     */
    public Long getIdRezervacija() {
        return idRezervacija;
    }

    /**
     * Gets the value of the idKorisnik property.
     * 
     */
    public Long getIdKorisnik() {
        return idKorisnik;
    }

    /**
     * Gets the value of the ocena property.
     * 
     */
    public short getOcena() {
        return ocena;
    }

    /**
     * Sets the value of the ocena property.
     * 
     */
    public void setOcena(short value) {
        this.ocena = value;
        this.timestamp = new Date(System.currentTimeMillis());
    }

}
