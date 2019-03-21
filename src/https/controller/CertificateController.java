package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import app.main.Singleton;
import https.model.CertifikatDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;

public class CertificateController 
{
	public CertificateController()
	{
		if(getToken().equals(null)) //za sad samo ova provera, treba dodati verifikaciju za prijavljenog korisnika
		{
			JOptionPane.showMessageDialog(null, "Nedozvoljena operacija, nemate ovlascenje!", "Greska", JOptionPane.OK_OPTION);
		}
	}
	
	public CertifikatDTO getCertifikatById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		CertifikatDTO cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/id/" + id, getToken(), new CertifikatDTO());
		if(cert == null)
		{
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		
		return cert;
	}
	
	public CertifikatDTO getX509CertificateById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		CertifikatDTO cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/x509/" + id, getToken(), new CertifikatDTO());
		if(cert == null)
		{
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		System.out.println(cert);
		
		return cert;
	}
	
	public CertifikatDTO getCertifikatBySN(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		CertifikatDTO cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/sn/" + id, getToken(), new CertifikatDTO());
		if(cert == null)
		{
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		System.out.println(cert);
		
		return cert;
	}
	
	
	public void removeById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		DeleteRequest.execute("https://localhost:8443/api/certificate/id/" + id, getToken());
	}
	
	public void removeBySN(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		DeleteRequest.execute("https://localhost:8443/api/certificate/sn/" + id, getToken());
	}
	
	public boolean exists(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		boolean cert = false;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/exists/" + id, getToken(), new Boolean(cert));
		System.out.println(cert);
		if(!cert)
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		else
			JOptionPane.showMessageDialog(null, "Postoji sertifikat sa tim id-jem", "Uspesno", JOptionPane.OK_OPTION);
		
		return cert;
	}
	
	public List<CertifikatDTO> getSubCertificates(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertifikatDTO> certs = null;
		certs = GetRequest.execute("https://localhost:8443/api/certificate/subcert/" + id, getToken(), new ArrayList<CertifikatDTO>()); 
		if(certs.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Nema podsertifikata za izabrani parametar", "Greska", JOptionPane.OK_OPTION);
		}

		return certs;
	}
	
	public static String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
