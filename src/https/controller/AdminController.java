package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

import javax.swing.JOptionPane;

import https.model.AdminDTO;
import https.model.AdminPrijavaDTO;
import https.requests.GetRequest;
import https.requests.PostRequest;

public class AdminController
{
	private String token = null; 
	
	public AdminController()
	{
		
	}
	
	public AdminDTO getAdmin(Long id)
	{
		AdminDTO adm = null;
		try
		{
			
			adm = GetRequest.execute("https://localhost:8443/api/adminCert/id/" + id, this.getToken(), new AdminDTO());
			if(this.getToken().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Nepostojeci admin", "Greska", JOptionPane.OK_OPTION);
			}
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return adm;
	}
	
	public AdminDTO getAdminByEmail(String email)
	{
		AdminDTO adm = null;
		try
		{
			adm = GetRequest.execute("https://localhost:8443/api/adminCert/email/" + email, this.getToken(), new AdminDTO());
			if(this.getToken().isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Nepostojeca email adresa", "Greska", JOptionPane.OK_OPTION);
			}
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return adm;
	}
	
	public String login()
	{
		String logged = null;
		try
		{
			logged = PostRequest.execute("https://localhost:8443/api/login", "", new AdminPrijavaDTO("borisbibic1996@gmail.com", "stefan"), new String());
			this.setToken("Bearer" + logged);
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return logged;
	}
	
	
	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}
}
