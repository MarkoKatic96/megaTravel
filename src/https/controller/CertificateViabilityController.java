package https.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import https.model.CertificateViabilityDTO;
import https.requests.GetRequest;
import https.requests.PostRequest;
import https.requests.PutRequest;

public class CertificateViabilityController 
{
	AdminController adm = null;
	
	public CertificateViabilityController()
	{
		adm = new AdminController();
		if(adm.getToken().equals(null)) //za sad samo ova provera, treba dodati verifikaciju za prijavljenog korisnika
		{
			JOptionPane.showMessageDialog(null, "Nedozvoljena operacija, nemate ovlascenje!", "Greska", JOptionPane.OK_OPTION);
		}
	}
	
	public String getStatus(BigInteger serijskiBroj)
	{
		String cert = null;
		try
		{
			cert = GetRequest.execute("https://localhost:8443/api/viability/" + serijskiBroj, adm.getToken(), new String());
			JOptionPane.showMessageDialog(null, "Trenutni status sertifikata je:" + cert.toString(), "Status", JOptionPane.OK_OPTION);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public List<CertificateViabilityDTO> getAllStatus()
	{
		List<CertificateViabilityDTO> certs = null;
		try
		{
			certs = GetRequest.execute("https://localhost:8443/api/viability/all", adm.getToken(), new ArrayList<CertificateViabilityDTO>());
			System.out.println(certs);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return certs;
	}
	
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	//?????????????????????????????????????????????????????//
	public CertificateViabilityDTO newStatus()
	{
		CertificateViabilityDTO cert = null;
		try
		{
			cert = PostRequest.execute("https://localhost:8443/api/viability", "",  new CertificateViabilityDTO(), null);
			System.out.println(cert);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	
	public CertificateViabilityDTO editStatus()
	{
		CertificateViabilityDTO cert = null;
		try
		{
			cert = PutRequest.execute("https://localhost:8443/api/viability", "",  new CertificateViabilityDTO(), null);
			System.out.println(cert);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
}
