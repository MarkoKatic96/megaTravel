package https.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
import https.model.CertifikatDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;
import https.requests.PutRequest;
import model.DataSum;

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
		List<CertifikatDTO> cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/id/" + id, getToken(), CertifikatDTO.class, false);
		if(cert == null)
		{
				JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		
		return cert.get(0);
	}
	
	public CertifikatDTO getX509CertificateById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertifikatDTO> cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/x509/" + id, getToken(), CertifikatDTO.class, false);
		if(cert == null)
		{
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		System.out.println(cert);
		
		return cert.get(0);
	}
	
	public CertifikatDTO getCertifikatBySN(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertifikatDTO> cert = null;
		cert = GetRequest.execute("https://localhost:8443/api/certificate/sn/" + id, getToken(), CertifikatDTO.class, false);
		if(cert == null)
		{
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		}
		System.out.println(cert);
		
		return cert.get(0);
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
		List<Boolean> cert = new ArrayList<>();
		cert = GetRequest.execute("https://localhost:8443/api/certificate/exists/" + id, getToken(), Boolean.class, false);
		System.out.println(cert);
		if(!cert.get(0))
			JOptionPane.showMessageDialog(null, "Nepostojeci sertifikat", "Greska", JOptionPane.OK_OPTION);
		else
			JOptionPane.showMessageDialog(null, "Postoji sertifikat sa tim id-jem", "Uspesno", JOptionPane.OK_OPTION);
		
		return cert.get(0);
	}
	
	public List<CertifikatDTO> getSubCertificates(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertifikatDTO> certs = null;
		certs = GetRequest.execute("https://localhost:8443/api/certificate/subcert/" + id, getToken(), CertifikatDTO.class, true); 
		if(certs.isEmpty())
		{
			JOptionPane.showMessageDialog(null, "Nema podsertifikata za izabrani parametar", "Greska", JOptionPane.OK_OPTION);
		}

		return certs;
	}
	
	public List<CertifikatDTO> getAllCertificates() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertifikatDTO> certs = null;
		certs = GetRequest.execute("https://localhost:8443/api/certificate/all", getToken(), CertifikatDTO.class, true); 
		if(certs.isEmpty())
		{
			//JOptionPane.showMessageDialog(null, "Nema kreiranih sertifikata", "Greska", JOptionPane.OK_OPTION);
		}

		return certs;
	}
	
	public CertifikatDTO createCertificate(DataSum dataSum) throws KeyManagementException, CredentialException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, IOException, ServerNotActiveException
	{
		List<CertifikatDTO> cert = null;
		cert = PutRequest.execute("https://localhost:8443/api/certificate/create", getToken(), dataSum, CertifikatDTO.class, false);
		if(cert.isEmpty())
		{
			//JOptionPane.showMessageDialog(null, "Sertifikat nije kreiran", "Greska", JOptionPane.OK_OPTION);
		}
		System.out.println(cert);
		
		return cert.get(0);
	}
	
	public static String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
