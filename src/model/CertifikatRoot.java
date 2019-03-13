package model;

public class CertifikatRoot extends Certifikat {
	private String organizacija;
	
	public CertifikatRoot() {
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @param organizacija
	 */
	public CertifikatRoot(String organizacija) {
		super();
		this.organizacija = organizacija;
	}


	public String getOrganizacija() {
		return organizacija;
	}
	
}
