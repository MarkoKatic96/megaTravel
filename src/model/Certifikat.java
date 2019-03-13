package model;

import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.util.Date;

import sun.security.x509.X500Name;

public abstract class Certifikat {
	private X509Certificate nadcertifikat;
	private Date pocetak;
	private Date kraj;
	private X500Name naziv;
	private PublicKey publicKey;
	TipCertifikata tipCertifikata;
	
	public Certifikat() {
	}

	/**
	 * @param nadcertifikat
	 * @param pocetak
	 * @param kraj
	 * @param naziv
	 * @param publicKey
	 * @param tipCertifikata
	 */
	public Certifikat(X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata) {
		super();
		this.nadcertifikat = nadcertifikat;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.naziv = naziv;
		this.publicKey = publicKey;
		this.tipCertifikata = tipCertifikata;
	}

	public X509Certificate getNadcertifikat() {
		return nadcertifikat;
	}

	public Date getPocetak() {
		return pocetak;
	}

	public Date getKraj() {
		return kraj;
	}

	public X500Name getNaziv() {
		return naziv;
	}

	public PublicKey getPublicKey() {
		return publicKey;
	}

	public TipCertifikata getTipCertifikata() {
		return tipCertifikata;
	}
}
