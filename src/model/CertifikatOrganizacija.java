package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatOrganizacija extends Certifikat {
	private String kategorija;
	private String drzava;
	private String nazivOrganizacije;
	private String PTT;
	private String ulica;

	public CertifikatOrganizacija() {
		super();
	}
	
	/**
	 * @param kategorija
	 * @param drzava
	 * @param nazivOrganizacije
	 * @param pTT
	 * @param ulica
	 */
	public CertifikatOrganizacija(String kategorija, String drzava, String nazivOrganizacije, String PTT,
			String ulica, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.kategorija = kategorija;
		this.drzava = drzava;
		this.nazivOrganizacije = nazivOrganizacije;
		this.PTT = PTT;
		this.ulica = ulica;
	}
	
	public String getKategorija() {
		return kategorija;
	}
	
	public String getDrzava() {
		return drzava;
	}
	
	public String getNazivOrganizacije() {
		return nazivOrganizacije;
	}
	
	public String getPTT() {
		return PTT;
	}
	
	public String getUlica() {
		return ulica;
	}
	
	
}
