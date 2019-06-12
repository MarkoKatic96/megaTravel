package com.megatravel.ratingservice.dto;

import com.megatravel.ratingservice.model.Ocena;

public class NovaOcenaDTO {
	
    protected Long idSmestaj;
    protected Long idRezervacija;
    protected Long idKorisnik;
    protected short ocena;

    public NovaOcenaDTO() { }

	public NovaOcenaDTO(Long idSmestaj, Long idRezervacija, Long idKorisnik, short ocena) {
		super();
		this.idSmestaj = idSmestaj;
		this.idRezervacija = idRezervacija;
		this.idKorisnik = idKorisnik;
		this.ocena = ocena;
	}
	
	public NovaOcenaDTO(Ocena ocena) {
		super();
		this.idSmestaj = ocena.getIdSmestaj();
		this.idRezervacija = ocena.getIdRezervacija();
		this.idKorisnik = ocena.getIdKorisnik();
		this.ocena = ocena.getOcena();
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
    }

}
