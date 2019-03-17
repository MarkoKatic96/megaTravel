package model;

public class CertifikatCA extends Certifikat {
	private String organizacija;
	
	public CertifikatCA() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param organizacija
	 */
	public CertifikatCA(String organizacija) {
		super();
		this.organizacija = organizacija;
	}


	public String getOrganizacija() {
		return organizacija;
	}
	
}