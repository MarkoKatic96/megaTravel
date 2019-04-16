package megatravel.bezbednost.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

//Tabela koja drzi trenutni status sertifikata
@Entity
@Table(name="dostupnost_sertifikata")
public class CertificateViabilityModel {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private BigInteger serijskiBroj;
	
	@NotNull
	private StatusCertifikata status;
	
	

	public CertificateViabilityModel(BigInteger serijskiBroj, StatusCertifikata status) {
		super();
		this.serijskiBroj = serijskiBroj;
		this.status = status;
	}

	public CertificateViabilityModel() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getSerijskiBroj() {
		return serijskiBroj;
	}

	public void setSerijskiBroj(BigInteger serijskiBroj) {
		this.serijskiBroj = serijskiBroj;
	}

	public StatusCertifikata getStatus() {
		return status;
	}

	public void setStatus(StatusCertifikata status) {
		this.status = status;
	}
	
	

}
