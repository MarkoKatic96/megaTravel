package https.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import https.model.CertificateCommunicationDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;

public class CertificateCommunicationController
{
	AdminController admContr;
	
	public CertificateCommunicationController()
	{
		admContr = new AdminController();
		if(admContr.getToken().equals(null)) //za sad samo ova provera, treba dodati verifikaciju za prijavljenog korisnika
		{
			JOptionPane.showMessageDialog(null, "Nedozvoljena operacija, nemate ovlascenje!", "Greska", JOptionPane.OK_OPTION);
		}
	}
	
	public CertificateCommunicationDTO getCommunicationById(Long id)
	{
		CertificateCommunicationDTO cert = null;
		try
		{
			
			cert = GetRequest.execute("https://localhost:8443/api/communication/id/" + id, admContr.getToken(), new CertificateCommunicationDTO());
			if(cert == null)
			{
				System.out.println("Invalid operation");
				JOptionPane.showMessageDialog(null, "Nije ostvarena komunikacija", "Greska", JOptionPane.OK_OPTION);
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
			if(cert)
			{
				JOptionPane.showMessageDialog(null, "Komunikacija uspesno prekinuta", "Uspesno", JOptionPane.OK_OPTION);
			}
			
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
			cert = GetRequest.execute("https://localhost:8443/api/communication", admContr.getToken(), new Boolean(cert));
			if(!cert)
			{
				JOptionPane.showMessageDialog(null, "Komunikacija nije odobrena!", "Greska", JOptionPane.OK_OPTION);
				System.out.println("Invalid operation");
				return false;
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Komunikacija odobrena", "Uspesno", JOptionPane.OK_OPTION);
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
			
			certs = GetRequest.execute("https://localhost:8443/api/communication/all", admContr.getToken(), new ArrayList<CertificateCommunicationDTO>());
			if(certs.isEmpty())
			{
				JOptionPane.showMessageDialog(null, "Nema komunikacija!", "Greska", JOptionPane.OK_OPTION);
			}
				
			
		} catch (KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException
				| InstantiationException | IllegalAccessException | IOException e) {
			
			e.printStackTrace();
		}
		
		return certs;
	}
	
	
	
}
