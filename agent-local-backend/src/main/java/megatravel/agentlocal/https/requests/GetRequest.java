package megatravel.agentlocal.https.requests;

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
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GetRequest {

	@SuppressWarnings("unchecked")
	public static <T> List<T> execute(String path, String token, Class<T> cls, boolean isList) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, InstantiationException, IllegalAccessException {
		// Load CAs from an InputStream
		InputStream certIs=new FileInputStream("resources/user.pfx");
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
		URL url = new URL(path);
		HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
		connection.setRequestProperty("Accept-Charset", "UTF-8");
		
		if (!token.equals("")) {
			connection.setRequestProperty("Authorization", token);
		}
		connection.setSSLSocketFactory(context.getSocketFactory());
		connection.setDoInput(true);
		connection.setDoOutput(true);

		InputStream in = connection.getInputStream();
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";

		if (!isList) {
			Object obj = cls.getClass().newInstance();
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue(result, cls.getClass());
			
			List<T> list = new ArrayList<>();
			list.add((T) obj);
			return (List<T>) list;
		} else {
			ArrayList<T> obj = new ArrayList<>();
			ObjectMapper mapper = new ObjectMapper();
			obj = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, cls));
			
			return (List<T>) obj;
		}
	}
}
