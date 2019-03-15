package model;

public class CertifikatAplikacija extends Certifikat {
	private String nazivAplikacije;
	private String organizacija;
	private String verzija;

	public CertifikatAplikacija() {
		super();
	}

	/**
	 * @param nazivAplikacije
	 * @param organizacija
	 * @param verzija
	 */
	public CertifikatAplikacija(String nazivAplikacije, String organizacija, String verzija) {
		super();
		this.nazivAplikacije = nazivAplikacije;
		this.organizacija = organizacija;
		this.verzija = verzija;
	}

	public String getNazivAplikacije() {
		return nazivAplikacije;
	}

	public String getOrganizacija() {
		return organizacija;
	}

	public String getVerzija() {
		return verzija;
	}
	
}
