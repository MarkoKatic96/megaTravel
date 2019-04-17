package megatravel.bezbednost.model;

public class CertifikatDomen extends Certifikat {
	private String organizacija;
	private String https;

	public CertifikatDomen() {
		super();
	}
	
	/**
	 * @param organizacija
	 * @param https
	 */
	public CertifikatDomen(String organizacija, String https) {
		super();
		this.organizacija = organizacija;
		this.https = https;
	}
	
	public String getOrganizacija() {
		return organizacija;
	}
	
	public String getHttps() {
		return https;
	}
	
	
}
