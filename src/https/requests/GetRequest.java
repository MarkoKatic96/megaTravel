package https.requests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

public class GetRequest {
	
	public GetRequest() {}
	
	public String execute() throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		// Load CAs from an InputStream
		// (could be from a resource or ByteArrayInputStream or ...)
		
		//CertificateFactory cf = CertificateFactory.getInstance("X.509");
		// From https://www.washington.edu/itconnect/security/ca/load-der.crt
		/*InputStream caInput = new BufferedInputStream(new FileInputStream("resources/cert-chain.p12"));
		Certificate ca;
		try {
		    ca = cf.generateCertificate(caInput);
		    System.out.println("ca=" + ((X509Certificate) ca).getSubjectDN());
		} finally {
		    caInput.close();
		}
		*/
		
		InputStream certIs=new FileInputStream("resources/cert-chain.p12");
		KeyStore ks=KeyStore.getInstance("PKCS12");
		ks.load(certIs,"secretpassword".toCharArray());
		Certificate ca = ks.getCertificate("1");
		
		// Create a KeyStore containing our trusted CAs
		String keyStoreType = KeyStore.getDefaultType();
		KeyStore keyStore = KeyStore.getInstance(keyStoreType);
		keyStore.load(null, null);
		keyStore.setCertificateEntry("ca", ca);

		// Create a TrustManager that trusts the CAs in our KeyStore
		String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
		tmf.init(keyStore);

		// Create an SSLContext that uses our TrustManager
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, tmf.getTrustManagers(), null);

		// Tell the URLConnection to use a SocketFactory from our SSLContext
		URL url = new URL("https://google.com");
		HttpsURLConnection urlConnection = (HttpsURLConnection)url.openConnection();
		urlConnection.setSSLSocketFactory(context.getSocketFactory());
		InputStream in = urlConnection.getInputStream();
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		System.out.println(result);
		
		return result;
		
	}
}
