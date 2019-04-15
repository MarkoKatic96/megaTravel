package megatravel.agentlocal.https.ssl;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.PrivateKeyDetails;
import org.apache.http.ssl.PrivateKeyStrategy;
import org.apache.http.ssl.SSLContexts;
import org.apache.log4j.BasicConfigurator;

import com.fasterxml.jackson.databind.ObjectMapper;

public class SSLMutualAuth {
	private static final String filepathIdentity = "./files/repository/keystores/identity.jks";
	private static final String filepathTrust = "./files/repository/keystores/myTrustStore.jks";
	static {
		  // Setup the trustStore location and password
		  System.setProperty("javax.net.ssl.trustStore",filepathIdentity);
		  System.setProperty("javax.net.ssl.trustStore",filepathTrust);
		  System.setProperty("javax.net.ssl.trustStorePassword", "secretpassword");

		  // for localhost testing only
		  javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(new javax.net.ssl.HostnameVerifier() {
		        public boolean verify(String hostname, javax.net.ssl.SSLSession sslSession) {
		          return hostname.equals("localhost");
		        }

		  });
	}
	
	  SSLMutualAuth() {
		  
	  }
	
	  public static CloseableHttpClient getHttpClient() throws Exception {
		  BasicConfigurator.configure();

		  String CERT_ALIAS = "1", CERT_PASSWORD = "secretpassword";
		      
		  KeyStore identityKeyStore = KeyStore.getInstance("jks");
		  FileInputStream identityKeyStoreFile = new FileInputStream(new File(filepathIdentity));
		  identityKeyStore.load(identityKeyStoreFile, CERT_PASSWORD.toCharArray());
		
		  KeyStore trustKeyStore = KeyStore.getInstance("jks");
		  FileInputStream trustKeyStoreFile = new FileInputStream(new File(filepathTrust));
		  trustKeyStore.load(trustKeyStoreFile, CERT_PASSWORD.toCharArray());
		      
		  SSLContext sslContext = SSLContexts.custom().loadKeyMaterial(identityKeyStore, CERT_PASSWORD.toCharArray(), new PrivateKeyStrategy() {
			  @Override
		      public String chooseAlias(Map<String, PrivateKeyDetails> aliases, Socket socket) {
				  return CERT_ALIAS;
		      }
		  }).loadTrustMaterial(trustKeyStore, null).build();
		      
		  SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, new String[]{"TLSv1.2", "TLSv1.1"}, null, SSLConnectionSocketFactory.getDefaultHostnameVerifier());

		  CloseableHttpClient client = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();
		      
		  return client;
		      
		      /*// Call a SSL-endpoint
		      callEndPoint (client, "https://localhost:8443/api/login", 
		          new JSONObject()
		          .put("email", "agent@megatravel.com")
		          .put("lozinka", "secretpassword")
		          );
		         */
	  }
	  
	@SuppressWarnings("unchecked")
	public static <T> List<T> callPost(String url, String token, String params, Class<T> cls, boolean isList) throws Exception {

	      CloseableHttpClient aHTTPClient = getHttpClient();

	      HttpPost post = new HttpPost(url);
	      post.setHeader("Accept", "application/json");
	      post.setHeader("Content-type", "application/json");
	      if (!token.isEmpty())
	    	  post.setHeader("Authorization", token);
	      
	      StringEntity entity = new StringEntity(params);
	      post.setEntity(entity);

	      HttpResponse response = aHTTPClient.execute(post);
	  
	      //int responseCode = response.getStatusLine().getStatusCode();

	      InputStream in = response.getEntity().getContent();
			
	      @SuppressWarnings("resource")
			Scanner s = new Scanner(in).useDelimiter("\\A");
			String result = s.hasNext() ? s.next() : "";
			ObjectMapper mapper = new ObjectMapper();
			
			if (!isList) {
				//Object obj = cls.getClass().newInstance();
				Object obj = Class.forName(cls.getName()).getConstructor().newInstance();
				if (cls == String.class) {
					List<T> list = new ArrayList<>();
					list.add((T) result);
					return list;
				}
				obj = mapper.readValue(result, cls.getClass());
				
				List<T> list = new ArrayList<>();
				list.add((T) obj);
				return (List<T>) list;
			} else {
				ArrayList<T> obj = new ArrayList<>();
				obj = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, cls));
				
				return (List<T>) obj;
			}
	  }  

	@SuppressWarnings("unchecked")
	public static <T> List<T> callGet(String url, String token, Class<T> cls, boolean isList) throws Exception {

	      CloseableHttpClient aHTTPClient = getHttpClient();

	      HttpGet get = new HttpGet(url);
	      get.setHeader("Accept", "application/json");
	      get.setHeader("Content-type", "application/json");
	      get.setHeader("Authorization", token);
	      
	      //StringEntity entity = new StringEntity(params);
	      //get.setHeader(entity.getContentType());
	      
	      HttpResponse response = aHTTPClient.execute(get);
	  
	      //int responseCode = response.getStatusLine().getStatusCode();
	      
	      InputStream in = response.getEntity().getContent();
			
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
	
	@SuppressWarnings("unchecked")
	public static <T> List<T> callPut(String url, String token, String params, Class<T> cls, boolean isList) throws Exception {

	      CloseableHttpClient aHTTPClient = getHttpClient();

	      HttpPut put = new HttpPut(url);
	      put.setHeader("Accept", "application/json");
	      put.setHeader("Content-type", "application/json");
	      put.setHeader("Authorization", token);
	      
	      StringEntity entity = new StringEntity(params);
	      put.setEntity(entity);

	      HttpResponse response = aHTTPClient.execute(put);
	  
	      //int responseCode = response.getStatusLine().getStatusCode();
	      
	      InputStream in = response.getEntity().getContent();
			
	      @SuppressWarnings("resource")
			Scanner s = new Scanner(in).useDelimiter("\\A");
			String result = s.hasNext() ? s.next() : "";
			ObjectMapper mapper = new ObjectMapper();
			
			if (!isList) {
				//Object obj = cls.getClass().newInstance();
				Object obj = Class.forName(cls.getName()).getConstructor().newInstance();
				if (cls == String.class) {
					List<T> list = new ArrayList<>();
					list.add((T) result);
					return list;
				}
				obj = mapper.readValue(result, cls.getClass());
				
				List<T> list = new ArrayList<>();
				list.add((T) obj);
				return (List<T>) list;
			} else {
				ArrayList<T> obj = new ArrayList<>();
				obj = mapper.readValue(result, mapper.getTypeFactory().constructCollectionType(List.class, cls));
				
				return (List<T>) obj;
			}
	  }
	
	public static <T> boolean callDelete(String url, String token, String params, Class<T> cls, boolean isList) throws Exception {

	      CloseableHttpClient aHTTPClient = getHttpClient();

	      HttpPost delete = new HttpPost(url);
	      delete.setHeader("Accept", "application/json");
	      delete.setHeader("Content-type", "application/json");
	      delete.setHeader("Authorization", token);
	      
	      StringEntity entity = new StringEntity(params);
	      delete.setEntity(entity);

	      HttpResponse response = aHTTPClient.execute(delete);
	  
	      int responseCode = response.getStatusLine().getStatusCode();
	      
	      if (responseCode>=200 && responseCode<400) {
	    	  return true;
	      } else {
	    	  return false;
	      }
	  }
}