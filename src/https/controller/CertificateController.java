package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import https.model.CertifikatDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;

public class CertificateController 
{
	AdminController adm = null;
	
	public CertificateController()
	{
		adm = new AdminController();
		if(adm.getToken().equals(null)) //za sad samo ova provera, treba dodati verifikaciju za prijavljenog korisnika
		{
			JOptionPane.showMessageDialog(null, "Nedozvoljena operacija, nemate ovlascenje!", "Greska", JOptionPane.OK_OPTION);
		}
	}
	
	public CertifikatDTO getCertifikatById(Long id)
	{
		CertifikatDTO cert = null;
		try
		{
			cert = GetRequest.execute("https://localhost:8443/api/certificate/id/" + id, adm.getToken(), new CertifikatDTO());
			if(cert == null)
			{
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
			}
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public CertifikatDTO getX509CertificateById(Long id)
	{
		CertifikatDTO cert = null;
		try
		{
			cert = GetRequest.execute("https://localhost:8443/api/certificate/x509/" + id, adm.getToken(), new CertifikatDTO());
			if(cert == null)
			{
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
			}
			System.out.println(cert);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public CertifikatDTO getCertifikatBySN(Long id)
	{
		CertifikatDTO cert = null;
		try
		{
			cert = GetRequest.execute("https://localhost:8443/api/certificate/sn/" + id, adm.getToken(), new CertifikatDTO());
			if(cert == null)
			{
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
			}
			System.out.println(cert);
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	
	public void removeById(Long id)
	{
		try
		{
			DeleteRequest.execute("https://localhost:8443/api/certificate/id/" + id, adm.getToken());
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public void removeBySN(Long id)
	{
		try
		{
			DeleteRequest.execute("https://localhost:8443/api/certificate/sn/" + id, adm.getToken());
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public boolean exists(Long id)
	{
		boolean cert = false;
		try
		{
			cert = GetRequest.execute("https://localhost:8443/api/certificate/exists/" + id, adm.getToken(), new Boolean(cert));
			System.out.println(cert);
			if(!cert)
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
			else
				JOptionPane.showMessageDialog(null, "Postoji sertifikat sa tim id-jem", "Uspesno", JOptionPane.OK_OPTION);
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public List<CertifikatDTO> getSubCertificates(Long id)
	{
		List<CertifikatDTO> certs = null;
		try
		{
			certs = GetRequest.execute("https://localhost:8443/api/certificate/subcert/" + id, adm.getToken(), new ArrayList<CertifikatDTO>()); 
			if(certs.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Nema podsertifikata za izabrani parametar", "Greska", JOptionPane.OK_OPTION);
			}
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return certs;
	}
	
	
	
}
