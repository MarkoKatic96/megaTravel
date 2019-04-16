package megatravel.agentlocal.certificate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class CertificateTrust {
	
	public CertificateTrust() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean checkValidityOfCertificate(HttpServletRequest req)  {
		try {
			return check(req);
		} catch (KeyStoreException e) {
			return false;
		}
	}

	private boolean check(HttpServletRequest req) throws KeyStoreException {
		X509Certificate[] certs = (X509Certificate[]) req.getAttribute("javax.servlet.request.X509Certificate");
		
		if (certs[0] == null) {
			return false;
		}

		KeyStore keyStore;
		try {
			keyStore = KeyStore.getInstance("JKS");
		} catch (KeyStoreException e1) {
			return false;
		}

		// Provide location of Java Keystore and password for access
		URL u = getClass().getClassLoader().getResource("serverTrustStore.jks");
		String s = u.getPath().replaceAll("%20", " ");
		//String s = u.getPath().replaceFirst("/", "");
		
		
		File file = new File(s);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			keyStore.load(fis, "secretpassword".toCharArray());
		} catch (NoSuchAlgorithmException | CertificateException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// iterate over all aliases
		Enumeration<String> es = keyStore.aliases();
		String alias = "";
		while (es.hasMoreElements()) {
			alias = (String) es.nextElement();
			// if alias refers to a private key break at that point
			// as we want to use that certificate
			Certificate chain = keyStore.getCertificate(alias);
			X509Certificate trusted = (X509Certificate) chain;
			if (certs[0]==trusted) {
				return true;
			}
		}
		
		return false;
	}
}
