package https.model;

import java.math.BigInteger;
import java.util.Date;

import model.TipCertifikata;

public class CertifikatDTO
{
	private Long id;
	private byte[] certifikat;
	private Date datumPocetka;
	private Date datumKraja;
	private Date datumKreiranja;
	private BigInteger serijskiBroj;
	private TipCertifikata tipCertifikata;
	private BigInteger nadcertifikat;
	
	public CertifikatDTO() {
	}

	public CertifikatDTO(Long id, byte[] certifikat, Date datumPocetka, Date datumKraja, Date datumKreiranja,
			BigInteger serijskiBroj, TipCertifikata tipCertifikata, BigInteger nadcertifikat) {
		super();
		this.id = id;
		this.certifikat = certifikat;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
		this.datumKreiranja = datumKreiranja;
		this.serijskiBroj = serijskiBroj;
		this.tipCertifikata = tipCertifikata;
		this.nadcertifikat = nadcertifikat;
	}

	public Date getDatumKreiranja() {
		return datumKreiranja;
	}

	public void setDatumKreiranja(Date datumKreiranja) {
		this.datumKreiranja = datumKreiranja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public byte[] getCertifikat() {
		return certifikat;
	}

	public void setCertifikat(byte[] certifikat) {
		this.certifikat = certifikat;
	}

	public Date getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(Date datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public Date getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(Date datumKraja) {
		this.datumKraja = datumKraja;
	}

	public BigInteger getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(BigInteger serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public TipCertifikata getTipCertifikata() {
		return tipCertifikata;
	}

	public void setTipCertifikata(TipCertifikata tipCertifikata) {
		this.tipCertifikata = tipCertifikata;
	}

	public BigInteger getNadcertifikat() {
		return nadcertifikat;
	}

	public void setNadcertifikat(BigInteger nadcertifikat) {
		this.nadcertifikat = nadcertifikat;
	}

	
}
