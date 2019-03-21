package model;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatCA extends Certifikat {
	private String organizacija;
	
	public CertifikatCA() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param organizacija
	 */
	public CertifikatCA(String organizacija, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata);
		this.organizacija = organizacija;
	}


	public String getOrganizacija() {
		return organizacija;
	}
	
}