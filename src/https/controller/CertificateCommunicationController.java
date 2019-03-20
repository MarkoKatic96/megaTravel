package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import https.model.CertificateCommunicationDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;

public class CertificateCommunicationController
{
	AdminController admContr;
	
	public CertificateCommunicationController()
	{
		admContr = new AdminController();
	}
	
	public CertificateCommunicationDTO getCommunicationById(Long id)
	{
		CertificateCommunicationDTO cert = null;
		try
		{
			if(admContr != null)
				cert = GetRequest.execute("https://localhost:8443/api/communication/id/" + id, admContr.getToken(), new CertificateCommunicationDTO());
			else
			{
				System.out.println("Invalid operation");
				return null;
			}
				
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public boolean removeCommunicationById(Long id)
	{
		boolean cert = false;
		try
		{
			cert = DeleteRequest.execute("https://localhost:8443/api/communication/id/" + id, admContr.getToken());
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	
	public boolean isCommunicationApproved()
	{
		boolean cert = false;
		try
		{
			if(admContr != null)
				cert = GetRequest.execute("https://localhost:8443/api/communication", admContr.getToken(), new Boolean(cert));
			else
			{
				System.out.println("Invalid operation");
				return false;
			}
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return cert;
	}
	//////////////////////////////////////
	//PUT REQUEST/////////////////////////
	//////////////////////////////////////
	
	
	public List<CertificateCommunicationDTO> getAllCommunications()
	{
		List<CertificateCommunicationDTO> certs = null;
		try
		{
			if(admContr != null)
				certs = GetRequest.execute("https://localhost:8443/api/communication/all", admContr.getToken(), new ArrayList<CertificateCommunicationDTO>());
			else
			{
				System.out.println("Invalid operation");
				return null;
			}
				
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return certs;
	}
	
	
	
}
