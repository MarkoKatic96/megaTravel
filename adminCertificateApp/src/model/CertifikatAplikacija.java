package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatAplikacija extends Certifikat {
	private String nazivAplikacije;
	private String organizacija;
	private String verzija;

	public CertifikatAplikacija() {
		super();
	}

	/**
	 * @param nazivAplikacije
	 * @param organizacija
	 * @param verzija
	 */
	public CertifikatAplikacija(String nazivAplikacije, String organizacija, String verzija, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.nazivAplikacije = nazivAplikacije;
		this.organizacija = organizacija;
		this.verzija = verzija;
		super.setTipCertifikata(TipCertifikata.APLIKACIJA);
	}

	public String getNazivAplikacije() {
		return nazivAplikacije;
	}

	public String getOrganizacija() {
		return organizacija;
	}

	public String getVerzija() {
		return verzija;
	}
	
}
