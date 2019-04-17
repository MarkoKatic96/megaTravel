package megatravel.bezbednost.dto;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;

import megatravel.bezbednost.model.StatusCertifikata;

public class CertificateViabilityDTO {
	
	private Long id;
	private BigInteger serijskiBroj;
	private Long random;
	private StatusCertifikata status;
	private Date timestamp;
	
	public CertificateViabilityDTO() {
		timestamp = new Date(System.currentTimeMillis());
		random = new SecureRandom().nextLong();
	}
	
	public CertificateViabilityDTO(Long id, BigInteger serijskiBroj, StatusCertifikata status) {
		super();
		this.id = id;
		this.serijskiBroj = serijskiBroj;
		this.status = status;
		timestamp = new Date(System.currentTimeMillis());
		random = new SecureRandom().nextLong();
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

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Long getRandom() {
		return random;
	}

	public void setRandom(Long random) {
		this.random = random;
	}

}
