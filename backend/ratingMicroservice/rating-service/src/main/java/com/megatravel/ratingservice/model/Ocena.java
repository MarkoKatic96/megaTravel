package com.megatravel.ratingservice.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Ocena {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long idOcena;
    
    @NotNull
    protected Long idSmestaj;
    
    @NotNull
    protected Long idRezervacija;
    
    @NotNull
    protected Long idKorisnik;
    
    @NotNull
    protected short ocena;
    
    @NotNull
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
