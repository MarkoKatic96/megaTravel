package https.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;

import app.main.Singleton;
import https.model.CertificateViabilityDTO;
import https.requests.GetRequest;
import https.requests.PostRequest;
import https.requests.PutRequest;

public class CertificateViabilityController 
{
	public CertificateViabilityController()
	{
		if(getToken().equals("")) //za sad samo ova provera, treba dodati verifikaciju za prijavljenog korisnika
		{
			JOptionPane.showMessageDialog(null, "Nedozvoljena operacija, nemate ovlascenje!", "Greska", JOptionPane.OK_OPTION);
		}
	}
	
	public String getStatus(BigInteger serijskiBroj) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		String cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/viability/" + serijskiBroj, getToken(), new String());
		//JOptionPane.showMessageDialog(null, "Trenutni status sertifikata je:" + cert.toString(), "Status", JOptionPane.OK_OPTION);
		return cert;
	}
	
	public List<CertificateViabilityDTO> getAllStatus() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertificateViabilityDTO> certs = null;
		certs = GetRequest.execute("https://localhost:8443/api/viability/all", getToken(), new ArrayList<CertificateViabilityDTO>());
		System.out.println(certs);
		
		return certs;
	}
	
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	public CertificateViabilityDTO newStatus() throws KeyManagementException, CredentialException, KeyStoreException, NoSuchAlgorithmException, CertificateException, InstantiationException, IllegalAccessException, IOException, ServerNotActiveException
	{
		CertificateViabilityDTO cert = null;
		cert = PostRequest.execute("https://localhost:8443/api/viability", "",  new CertificateViabilityDTO(), null);
		System.out.println(cert);
		
		return cert;
	}
	
	
	public CertificateViabilityDTO editStatus() throws KeyManagementException, CredentialException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, ServerNotActiveException
	{
		CertificateViabilityDTO cert = null;
		cert = PutRequest.execute("https://localhost:8443/api/viability", "",  new CertificateViabilityDTO(), null);
		System.out.println(cert);
		
		return cert;
	}
	
	public static String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
