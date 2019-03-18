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

import com.fasterxml.jackson.databind.ObjectMapper;

public class PostRequest {
	
	@SuppressWarnings("unchecked")
	public static <T> T execute(String path, String token, Object postData, T cls) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, InstantiationException, IllegalAccessException {
		// Load CAs from an InputStream
		InputStream certIs = new FileInputStream("resources/cert-chain.p12");
		KeyStore ks = KeyStore.getInstance("PKCS12");
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
		URL url = new URL(path);
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		if (!token.equals("")) {
			connection.setRequestProperty("Authorization", token);
		}
		connection.setSSLSocketFactory(context.getSocketFactory());
		connection.setDoInput(true);
		connection.setDoOutput(true);
		
		// Get object to byte[] using ObjectMapper and write it to OutputStream
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(connection.getOutputStream(), postData);
		
		// Read data received from Server - InputStream
		InputStream in = connection.getInputStream();
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		
		Object obj = cls.getClass().newInstance();
		obj = mapper.readValue(result, cls.getClass());
		
		return (T) obj;
	}
}