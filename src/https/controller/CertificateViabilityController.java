package https.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.swing.JOptionPane;

import app.main.Singleton;
import https.model.CertificateViabilityDTO;
import https.requests.GetRequest;
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
		List<String> cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/viability/" + serijskiBroj, getToken(), String.class, false);
		//JOptionPane.showMessageDialog(null, "Trenutni status sertifikata je:" + cert.toString(), "Status", JOptionPane.OK_OPTION);
		return cert.get(0);
	}
	
	public List<CertificateViabilityDTO> getAllStatus() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertificateViabilityDTO> certs = null;
		certs = GetRequest.execute("https://localhost:8443/api/viability/all", getToken(), CertificateViabilityDTO.class, true);
		//System.out.println(certs);
		
		return certs;
	}
	
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	public CertificateViabilityDTO newStatus(CertificateViabilityDTO certif) throws KeyManagementException, CredentialException, KeyStoreException, NoSuchAlgorithmException, CertificateException, InstantiationException, IllegalAccessException, IOException, ServerNotActiveException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		List<CertificateViabilityDTO> cert = null;
		cert = PutRequest.execute("https://localhost:8443/api/certificate/viability", getToken(), certif, CertificateViabilityDTO.class, false);
		//System.out.println(cert);
		
		return cert.get(0);
	}
	
	
	public CertificateViabilityDTO editStatus(CertificateViabilityDTO certif) throws KeyManagementException, CredentialException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, ServerNotActiveException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException
	{
		List<CertificateViabilityDTO> cert = null;
		cert = PutRequest.execute("https://localhost:8443/api/viability", getToken(), certif, CertificateViabilityDTO.class, false);
		//System.out.println(cert);
		
		return cert.get(0);
	}
	
	public static String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
