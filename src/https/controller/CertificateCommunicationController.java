package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.security.sasl.AuthenticationException;

import app.main.Singleton;
import https.model.CertificateCommunicationDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;

public class CertificateCommunicationController
{	
	public CertificateCommunicationDTO getCommunicationById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		CertificateCommunicationDTO cert = null;
		if(!getToken().equals("")) {
			cert = GetRequest.execute("https://localhost:8443/api/communication/id/" + id, getToken(), new CertificateCommunicationDTO());
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return cert;
	}

	public boolean removeCommunicationById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		boolean cert = false;
		if(!getToken().equals("")) {
			cert = DeleteRequest.execute("https://localhost:8443/api/communication/id/" + id, getToken());
			
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return cert;
	}
	
	public boolean isCommunicationApproved() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		boolean cert = false;
		if(!getToken().equals("")) {
			cert = GetRequest.execute("https://localhost:8443/api/communication", getToken(), new Boolean(cert));
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return cert;
	}
	//////////////////////////////////////
	//PUT REQUEST/////////////////////////
	//////////////////////////////////////
	
	
	public List<CertificateCommunicationDTO> getAllCommunications() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertificateCommunicationDTO> certs = null;

		if(!getToken().equals("")) {
			certs = GetRequest.execute("https://localhost:8443/api/communication/all", getToken(), new ArrayList<CertificateCommunicationDTO>());
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return certs;
	}
	
	
	private String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
