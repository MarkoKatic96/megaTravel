package https.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.rmi.server.ServerNotActiveException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.security.auth.login.CredentialException;
import javax.security.sasl.AuthenticationException;

import app.main.Singleton;
import https.model.CertificateCommunicationDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;
import https.requests.PostRequest;
import https.requests.PutRequest;

public class CertificateCommunicationController
{

	public CertificateCommunicationDTO getCommunicationById(Long id) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertificateCommunicationDTO> cert = null;
		if(!getToken().equals("")) {
			cert = GetRequest.execute("https://localhost:8443/api/communication/id/" + id, getToken(), CertificateCommunicationDTO.class, false);
		} else {
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return cert.get(0);
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
	
	public String isCommunicationApproved(BigInteger sn1, BigInteger sn2) throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, CredentialException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, ServerNotActiveException
	{
		List<String> cert = new ArrayList<>();
		if(!getToken().equals("")) {
			cert = PostRequest.execute("https://localhost:8443/api/communication", getToken(), new CertificateCommunicationDTO(null, sn1, sn2), String.class, false);
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		return cert.get(0);
	}

	//////////////////////////////////////
	//PUT REQUEST/////////////////////////
	//////////////////////////////////////
	
	
	public List<CertificateCommunicationDTO> getAllCommunications() throws KeyManagementException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException
	{
		List<CertificateCommunicationDTO> certs = null;

		if(!getToken().equals("")) {
			certs = GetRequest.execute("https://localhost:8443/api/communication/all", getToken(), CertificateCommunicationDTO.class, true);
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return certs;
	}
	
	public CertificateCommunicationDTO setCommunication(BigInteger sn1, BigInteger sn2) throws KeyManagementException, CredentialException, CertificateException, KeyStoreException, NoSuchAlgorithmException, InstantiationException, IllegalAccessException, IOException, ServerNotActiveException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {
		List<CertificateCommunicationDTO> certs = null;

		if(!getToken().equals("")) {
			certs = PutRequest.execute("https://localhost:8443/api/communication", getToken(), new CertificateCommunicationDTO((long) 1, sn1, sn2) , CertificateCommunicationDTO.class, false);
		} else
		{
			System.out.println("Invalid operation");
			throw new AuthenticationException();
		}
		
		return certs.get(0);
	}
	
	
	private String getToken() {
		return Singleton.getInstance().getToken();
	}
	
}
