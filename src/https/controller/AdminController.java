package https.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.security.sasl.AuthenticationException;

import app.main.Singleton;
import https.model.AdminDTO;
import https.model.AdminPrijavaDTO;
import https.requests.GetRequest;
import https.requests.PostRequest;

public class AdminController
{
	public AdminController()
	{
	}
	
	public AdminDTO getAdmin(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<AdminDTO> adm = null;
		adm = GetRequest.execute("https://localhost:8443/api/adminCert/id/" + id, getToken(), AdminDTO.class, false);
		
		return adm.get(0);
	}
	
	public AdminDTO getAdminByEmail(String email) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{

		if(getToken().equals("")) {
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		List<AdminDTO> adm = null;
		adm = GetRequest.execute("https://localhost:8443/api/adminCert/email/" + email, getToken(), AdminDTO.class, false);
		return adm.get(0);
	}
	
	public String login(String email, String psw) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, CredentialException, ServerNotActiveException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		List<String> loggedL = null;
		loggedL = PostRequest.execute("https://localhost:8443/api/login", "", new AdminPrijavaDTO(email, psw), String.class, false);
		String logged = loggedL.get(0);
		logged = "Bearer " + logged.substring(1, logged.length()-1);
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
