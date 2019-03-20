package app.main;

import java.awt.EventQueue;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;

import gui.MainGUI;
import https.model.AdminPrijavaDTO;
import https.model.CertificateCommunicationDTO;
import https.requests.DeleteRequest;
import https.requests.GetRequest;
import https.requests.PostRequest;

public class Main {
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGUI window = new MainGUI();
					window.getMainFrame().setVisible(true);
					
					
					try {
						// PRIMER POZIVA GET, DELETE I POST REQUEST-a
						
						//POST
						String token = PostRequest.execute("https://localhost:8443/api/login","", new AdminPrijavaDTO("borisbibic1996@gmail.com", "stefan"), new String());
						token = "Bearer " + token;
						
						//GET
						List<CertificateCommunicationDTO> cco = GetRequest.execute("https://localhost:8443/api/communication/all",token, new ArrayList<CertificateCommunicationDTO>());
						System.out.println(cco);
						
						//DELETE
						boolean b = DeleteRequest.execute("https://localhost:8443/api/certificate/id/1", token);
						System.out.println(b);
						
					} catch (IllegalAccessException | KeyManagementException | CertificateException | KeyStoreException | NoSuchAlgorithmException | InstantiationException | IOException e) {
						System.out.println("Pogledati kod servera: ako je 422 znaci da je server primio poruku, ali nije nasao zeljeni objekat (npr. certifikat sa datim id ne postoji). Ako je neki drugi kod pogledati HTTPS Status Code listu.");
						System.out.println("Moguce je (slucaj greske 'InstantiationException') i da je neki od parametara pogresan, verovatno nije pozvana adekvatna klasa (npr. treba lista klasa T, a umesto toga je pozvana samo klasa T)!");
						e.printStackTrace();
					}
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
