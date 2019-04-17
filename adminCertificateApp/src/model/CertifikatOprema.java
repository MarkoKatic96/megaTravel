package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public class CertifikatOprema extends Certifikat {
	private String MAC;
	private String nazivOpreme;
	private String drzava;
	private String organizacija;
	private String suborganizacija;
	private String idOpreme;

	public CertifikatOprema() {
		super();
	}
	
	/**
	 * @param mAC
	 * @param nazivOpreme
	 * @param drzava
	 * @param organizacija
	 * @param suborganizacija
	 * @param idOpreme
	 */
	public CertifikatOprema(String MAC, String nazivOpreme, String drzava, String organizacija, String suborganizacija,
			String idOpreme, X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super(nadcertifikat, pocetak, kraj, naziv, publicKey, tipCertifikata, seriskiBroj);
		this.MAC = MAC;
		this.nazivOpreme = nazivOpreme;
		this.drzava = drzava;
		this.organizacija = organizacija;
		this.suborganizacija = suborganizacija;
		this.idOpreme = idOpreme;
		super.setTipCertifikata(TipCertifikata.OPREMA);
	}

	public String getMAC() {
		return MAC;
	}
	
	public String getNazivOpreme() {
		return nazivOpreme;
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

	public String getIdOpreme() {
		return idOpreme;
	}
	
	
}
