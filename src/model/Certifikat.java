package model;

import java.math.BigInteger;
import java.security.PublicKey;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import sun.security.x509.X500Name;

public abstract class Certifikat {
	private X509Certificate nadcertifikat;
	private BigInteger seriskiBrojNadSert;
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
	public Certifikat(X509Certificate nadcertifikat, Date pocetak, Date kraj, X500Name naziv, PublicKey publicKey, TipCertifikata tipCertifikata, BigInteger seriskiBroj) {
		super();
		this.nadcertifikat = nadcertifikat;
		this.seriskiBrojNadSert = seriskiBroj;
		
		String pattern  = "yyyy-MM-dd";
		DateFormat formatter = new SimpleDateFormat(pattern);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		try {
			this.pocetak = formatter.parse(sdf.format(pocetak));
		} catch (ParseException e) {
			this.pocetak = pocetak;
		}
		try {
			this.kraj = formatter.parse(sdf.format(kraj));
		} catch (ParseException e) {
			this.kraj = kraj;
		}
		this.naziv = naziv;
		this.publicKey = publicKey;
		this.tipCertifikata = tipCertifikata;
	}

	public X509Certificate getNadcertifikat() {
		return nadcertifikat;
	}

	public BigInteger getSeriskiBrojNadSert() {
		return seriskiBrojNadSert;
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
	
	public void setTipCertifikata(TipCertifikata tip) {
		this.tipCertifikata = tip;
	}
}
