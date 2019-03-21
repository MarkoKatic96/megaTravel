package https.controller;

import java.io.IOException;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.security.auth.login.CredentialException;
import javax.security.sasl.AuthenticationException;

import app.main.Singleton;
import https.model.AdminDTO;
import https.model.AdminPrijavaDTO;
import https.requests.GetRequest;
import https.requests.PostRequest;

public class AdminController
{
	public static AdminDTO getAdmin(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		if(getToken().equals("")) {
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		AdminDTO adm = null;
		adm = GetRequest.execute("https://localhost:8443/api/adminCert/id/" + id, getToken(), new AdminDTO());
		
		return adm;
	}
	
	public AdminDTO getAdminByEmail(String email) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		if(getToken().equals("")) {
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		AdminDTO adm = null;
		adm = GetRequest.execute("https://localhost:8443/api/adminCert/email/" + email, getToken(), new AdminDTO());
		return adm;
	}
	
	public String login(String email, String psw) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, CredentialException, ServerNotActiveException
	{
		String logged = null;
		logged = PostRequest.execute("https://localhost:8443/api/login", "", new AdminPrijavaDTO(email, psw), new String());
		logged = "Bearer " + logged;
		this.setToken(logged);
		return logged;
	}
	
	
	public static String getToken() {
		return Singleton.getInstance().getToken();
	}


	public void setToken(String token) {
		Singleton.getInstance().setToken(token);
	}
}
