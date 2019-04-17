package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatDomen extends Certifikat {
	private String organizacija;
	private String https;

	public CertifikatDomen() {
		super();
	}
	
	/**
	 * @param organizacija
	 * @param https
	 */
	public CertifikatDomen(String organizacija, String https, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.organizacija = organizacija;
		this.https = https;
		super.setTipCertifikata(TipCertifikata.DOMEN);
	}
	
	public String getOrganizacija() {
		return organizacija;
	}
	
	public String getHttps() {
		return https;
	}
	
	
}
