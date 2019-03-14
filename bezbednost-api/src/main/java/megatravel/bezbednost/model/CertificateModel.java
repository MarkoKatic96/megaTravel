package megatravel.bezbednost.model;

import java.security.cert.X509Certificate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
//@Table (name="sertifikati")
public class CertificateModel {

	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private X509Certificate certifikat;
	
	@NotNull
	private String datumPocetka;
	
	@NotNull
	private String datumKraja;

	public CertificateModel() {
		
	}
	
	public CertificateModel(Long id, X509Certificate certifikat, String datumPocetka, String datumKraja) {
		super();
		this.id = id;
		this.certifikat = certifikat;
		this.datumPocetka = datumPocetka;
		this.datumKraja = datumKraja;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public X509Certificate getCertifikat() {
		return certifikat;
	}

	public void setCertifikat(X509Certificate certifikat) {
		this.certifikat = certifikat;
	}

	public String getDatumPocetka() {
		return datumPocetka;
	}

	public void setDatumPocetka(String datumPocetka) {
		this.datumPocetka = datumPocetka;
	}

	public String getDatumKraja() {
		return datumKraja;
	}

	public void setDatumKraja(String datumKraja) {
		this.datumKraja = datumKraja;
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
		return id == other.id;
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	
}
