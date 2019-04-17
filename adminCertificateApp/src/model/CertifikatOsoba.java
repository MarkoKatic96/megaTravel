package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

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
	public CertifikatOsoba(String ime, String prezime, String drzava, String organizacija, String suborganizacija, String email, String idZaposlenog, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.ime = ime;
		this.prezime = prezime;
		this.drzava = drzava;
		this.organizacija = organizacija;
		this.suborganizacija = suborganizacija;
		this.email = email;
		this.idZaposlenog = idZaposlenog;
		super.setTipCertifikata(TipCertifikata.OSOBA);
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
