package https.model;

import java.math.BigInteger;

import model.StatusCertifikata;

public class CertificateViabilityDTO {
	
	private Long id;
	private BigInteger serijskiBroj;
	private StatusCertifikata status;
	
	public CertificateViabilityDTO() {
	}
	
	public CertificateViabilityDTO(Long id, BigInteger serijskiBroj, StatusCertifikata status) {
		super();
		this.id = id;
		this.serijskiBroj = serijskiBroj;
		this.status = status;
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