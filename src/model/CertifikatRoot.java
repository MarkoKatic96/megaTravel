package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatRoot extends Certifikat {
	private String organizacija;
	
	public CertifikatRoot() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param organizacija
	 */
	public CertifikatRoot(String organizacija, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.organizacija = organizacija;
		super.setTipCertifikata(TipCertifikata.ROOT);
	}


	public String getOrganizacija() {
		return organizacija;
	}
	
}
