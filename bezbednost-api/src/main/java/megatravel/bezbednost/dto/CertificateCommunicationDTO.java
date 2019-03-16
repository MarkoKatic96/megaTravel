package megatravel.bezbednost.dto;

import java.math.BigInteger;

public class CertificateCommunicationDTO {
	
	private Long id;
	private BigInteger serijskiBroj1;
	private BigInteger serijskiBroj2;
	
	public CertificateCommunicationDTO() {
		
	}
	
	public CertificateCommunicationDTO(Long id, BigInteger serijskiBroj1, BigInteger serijskiBroj2) {
		super();
		this.id = id;
		this.serijskiBroj1 = serijskiBroj1;
		this.serijskiBroj2 = serijskiBroj2;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigInteger getSerijskiBroj1() {
		return serijskiBroj1;
	}

	public void setSerijskiBroj1(BigInteger serijskiBroj1) {
		this.serijskiBroj1 = serijskiBroj1;
	}

	public BigInteger getSerijskiBroj2() {
		return serijskiBroj2;
	}

	public void setSerijskiBroj2(BigInteger serijskiBroj2) {
		this.serijskiBroj2 = serijskiBroj2;
	}
	
	
	
	

}
