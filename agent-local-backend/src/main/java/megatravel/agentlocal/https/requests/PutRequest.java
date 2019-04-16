package megatravel.agentlocal.https.requests;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.rmi.server.ServerNotActiveException;
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
import javax.security.auth.login.CredentialException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class PutRequest
{
	@SuppressWarnings("unchecked")
	public static <T> List<T> execute(String path, String token, Object postData, Class<T> cls, boolean isList) throws IOException, CertificateException, KeyStoreException, NoSuchAlgorithmException, KeyManagementException, InstantiationException, IllegalAccessException, ServerNotActiveException, CredentialException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		// Load CAs from an InputStream
		InputStream certIs = new FileInputStream("resources/user.pfx");
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
		connection.setRequestMethod("PUT");
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
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		try{
			mapper.writeValue(connection.getOutputStream(), postData);
		} catch (Exception e) {
			//System.out.println(e);
			System.out.println("Ups, server nije aktivan");
			throw new ServerNotActiveException();
		}
				
		// Read data received from Server - InputStream
		InputStream in = null;
		try {
			in = connection.getInputStream();
		} catch (Exception e) {
			throw new CredentialException();
		}
				
		@SuppressWarnings("resource")
		Scanner s = new Scanner(in).useDelimiter("\\A");
		String result = s.hasNext() ? s.next() : "";
		
		if (!isList) {
			Object obj = null;
			try{
				obj = cls.getClass().newInstance();
			} catch (Exception e) {
				 obj = Class.forName(cls.getName()).getConstructor().newInstance();
			}
			//Object obj = Class.forName(cls.getName()).getConstructor().newInstance();
			obj = mapper.readValue(result, cls);
			
			List<T> list = new ArrayList<>();
			list.add((T) obj);
			return (List<T>) list;
		} else {
			ArrayList<T> obj = new ArrayList<>();
			obj = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, cls));
			
			return (List<T>) obj;
		}
	}
}
