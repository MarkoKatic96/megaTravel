package app.main;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import https.model.CertifikatDTO;

public class Singleton {
	private static Singleton instance = null;
	
	private String token = "";
	private List<CertifikatDTO> listaCertifikata = new ArrayList<>();
	
	private Singleton() {
	}
	
	public static Singleton getInstance() {
		if (instance == null) 
			instance = new Singleton(); 
  
        return instance; 
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public List<CertifikatDTO> getListaCertifikata() {
		return listaCertifikata;
	}

	public void setListaCertifikata(List<CertifikatDTO> listaCertifikata) {
		this.listaCertifikata = listaCertifikata;
	}

	public static void setInstance(Singleton instance) {
		Singleton.instance = instance;
	}
	
	public X509Certificate getX509Certificate(byte[] certifikat) {
		CertificateFactory certFactory;
		try {
			certFactory = CertificateFactory.getInstance("X.509");
		
			InputStream in = new ByteArrayInputStream(certifikat);
			X509Certificate cert = (X509Certificate)certFactory.generateCertificate(in);
			return cert;
		} catch (CertificateException e) {
			return null;
		}
	}
	
}