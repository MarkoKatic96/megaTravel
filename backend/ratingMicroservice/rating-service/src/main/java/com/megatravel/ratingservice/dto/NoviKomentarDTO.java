package com.megatravel.ratingservice.dto;

public class NoviKomentarDTO {

	protected Long idSmestaja;
    protected Long idRezervacije;
    protected Long idKorisnika;
    protected String komentar;
	
    public NoviKomentarDTO(Long idSmestaja, Long idRezervacije, Long idKorisnika, String komentar) {
		super();
		this.idSmestaja = idSmestaja;
		this.idRezervacije = idRezervacije;
		this.idKorisnika = idKorisnika;
		this.komentar = komentar;
	}
    
    public NoviKomentarDTO() {	}

	public Long getIdSmestaja() {
		return idSmestaja;
	}

	public void setIdSmestaja(Long idSmestaja) {
		this.idSmestaja = idSmestaja;
	}

	public Long getIdRezervacije() {
		return idRezervacije;
	}

	public void setIdRezervacije(Long idRezervacije) {
		this.idRezervacije = idRezervacije;
	}

	public Long getIdKorisnika() {
		return idKorisnika;
	}

	public void setIdKorisnika(Long idKorisnika) {
		this.idKorisnika = idKorisnika;
	}

	public String getKomentar() {
		return komentar;
	}

	public void setKomentar(String komentar) {
		this.komentar = komentar;
	}    
}
