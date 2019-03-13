package model;

public class CertifikatOsoba extends Certifikat {
	private String ime;
	private String prezime;
	private String drzava;
	private String organizacija;
	private String suborganizacija;
	private String email;
	private String idZaposlenog;

	public CertifikatOsoba() {
		super();
	}

	/**
	 * @param ime
	 * @param prezime
	 * @param drzava
	 * @param organizacija
	 * @param suborganizacija
	 * @param email
	 * @param uid
	 */
	public CertifikatOsoba(String ime, String prezime, String drzava, String organizacija, String suborganizacija, String email, String idZaposlenog) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.drzava = drzava;
		this.organizacija = organizacija;
		this.suborganizacija = suborganizacija;
		this.email = email;
		this.idZaposlenog = idZaposlenog;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public String getDrzava() {
		return drzava;
	}

	public String getOrganizacija() {
		return organizacija;
	}

	public String getSuborganizacija() {
		return suborganizacija;
	}

	public String getEmail() {
		return email;
	}

	public String getIdZaposlenog() {
		return idZaposlenog;
	}
	
}
