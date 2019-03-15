package megatravel.bezbednost.model;

import java.math.BigInteger;
import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

@Entity
//@Table (name="sertifikati")
public class CertificateModel {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Lob
	@NotNull
	private byte[] certifikat;
	
	@NotNull
	private Date datumPocetka;
	
	@NotNull
	private Date datumKraja;
	
	@NotNull
	private BigInteger serijskiBroj;
	
	@NotNull
	private TipCertifikata tipCertifikata;
	
	@NotNull
	private BigInteger nadcertifikat;
	
	@NotNull
	private StatusCertifikata statusCertifikata;

	public CertificateModel() {
		
	}
	
	public CertificateModel(Long id, X509Certificate certifikat, TipCertifikata tipCertifikata, BigInteger nadcertifikat) throws CertificateEncodingException {
		super();
		this.id = id;
		this.certifikat = certifikat.getEncoded();
		this.datumPocetka = certifikat.getNotBefore();
		this.datumKraja = certifikat.getNotAfter();
		this.serijskiBroj = certifikat.getSerialNumber();
		this.tipCertifikata = tipCertifikata;
		if (tipCertifikata == TipCertifikata.ROOT) {
			this.nadcertifikat = certifikat.getSerialNumber();
		} else {
			this.nadcertifikat = nadcertifikat;
		}
		this.statusCertifikata = StatusCertifikata.VALIDAN;
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

	public void setCertifikat(X509Certificate certifikat) throws CertificateEncodingException {
		this.certifikat = certifikat.getEncoded();
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

	public StatusCertifikata getStatusCertifikata() {
		return statusCertifikata;
	}

	public void setStatusCertifikata(StatusCertifikata statusCertifikata) {
		this.statusCertifikata = statusCertifikata;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CertificateModel other = (CertificateModel) obj;
		return (id == other.id && serijskiBroj == other.serijskiBroj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	
}
